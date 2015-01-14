package com.haipeng.cishicike;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.haipeng.util.myInterface.GetMsg;
import com.haipeng.util.myInterface.IsLogin;
import com.haipeng.util.resource.Paramers;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.ChatMessageListener;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;

import java.io.IOException;


/**
 * Created by Tyler on 2015/1/12.
 */
public class MyXMPPConnection {

    private static MyXMPPConnection myXMPPConnection = null;
    static Chat mChat;
    static ChatManager mChatManager;
    public static MyXMPPConnection getInstance() {
        if (myXMPPConnection == null)
            myXMPPConnection = new MyXMPPConnection();

        return myXMPPConnection;
    }


    ConnectionConfiguration configuration;
    XMPPTCPConnection connection = null;

    public class MyConnection extends AsyncTask<String, Integer, Boolean> {
        IsLogin isLogin;

        public MyConnection(IsLogin mIsLogin) {
            isLogin = mIsLogin;
        }

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                configuration = new ConnectionConfiguration(params[0], Integer.parseInt(params[1]), params[2]);
                configuration.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
                connection = new XMPPTCPConnection(configuration);
                connection.connect();
                login(params[3], params[4], connection);
                isLogin.LoginSuccessed();
                return true;
            } catch (SmackException exception) {
                exception.printStackTrace();
                isLogin.LoginFailed();
                return false;
            } catch (XMPPException X) {
                X.printStackTrace();
                isLogin.LoginFailed();
                return false;
            } catch (IOException io) {
                io.printStackTrace();
                isLogin.LoginFailed();
                return false;
            }
        }
    }

    public boolean login(String username, String password, XMPPTCPConnection connection) {
        if (connection != null)
            try {
                connection.login(username, password);
                return false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        return false;
    }

    public void createChat(XMPPTCPConnection connection, String friendName) {

        if (connection != null) {

            try {

                ChatManager chatManager = ChatManager.getInstanceFor(connection);
                Chat chat = chatManager.createChat(friendName + "@3e9ty4tqgbjxh3m", null);
                mChat = chat;
                mChatManager = chatManager;
                //发送的信息
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Chat getChat(){
        return mChat;
    }
    public ChatManager getChatManager(){
        return mChatManager;
    }
    public void sendMsg(Chat chat, ChatManager chatManager,String myMsg) {

        try {
            chat.sendMessage(myMsg);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SmackException.NoConnectionException");
        }


    }
    public void getMsg(Chat chat, ChatManager chatManager, final GetMsg getMsg) {

        //监听获取到的信息
        chatManager.addChatListener(new ChatManagerListener() {
            @Override
            public void chatCreated(Chat chat, boolean createdLocally) {
                chat.addMessageListener(new ChatMessageListener() {
                    @Override
                    public void processMessage(Chat chat, Message message) {
                        getMsg.getMsg(message.getBody());

                    }

                });
            }

        });


    }
    public XMPPTCPConnection getXMPPTCPConnection() {
        return connection;
    }

    public boolean mConnection(String username, String password, IsLogin isLogin) {
        MyConnection myConnection = new MyConnection(isLogin);
        myConnection.execute(new String[]{Paramers.SERVER_HOST, Paramers.SERVER_PORT + "", Paramers.SERVER_NAME, username, password});
        return false;
    }
}
