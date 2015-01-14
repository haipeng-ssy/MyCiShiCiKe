package com.haipeng.cishicike;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

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


    //公司office computer
    public static final String SERVER_HOST = "10.12.49.31";
    public static final int SERVER_PORT = 5222;
    public static final String SERVER_NAME = "mds-sunyiyan";

    String username = "admin";
    String password = "admin";

    //宿舍网络，my computer
//    public static final String SERVER_HOST = "192.168.1.103";
//    public static final int SERVER_PORT = 5222;
//    public static final String SERVER_NAME = "3e9ty4tqgbjxh3mr";
//
//
//    String username = "admin";
//    String password = "admin";


    ConnectionConfiguration configuration;
    XMPPTCPConnection connection = null;

    public class MyConnection extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            try {
                configuration = new ConnectionConfiguration(params[0], Integer.parseInt(params[1]), params[2]);
                configuration.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
                connection = new XMPPTCPConnection(configuration);
                connection.connect();
                login(params[3], params[4], connection);
                return true;
            } catch (SmackException exception) {
                exception.printStackTrace();
                return false;
            } catch (XMPPException X) {
                X.printStackTrace();
                return false;
            } catch (IOException io) {
                io.printStackTrace();
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

    public void sendMsg(XMPPTCPConnection connection) {

        if (connection != null) {

            try {

                ChatManager chatManager = ChatManager.getInstanceFor(connection);
                Chat chat = chatManager.createChat("xiaowang@3e9ty4tqgbjxh3m", null);
                //发送的信息
                chat.sendMessage("Hello World!");
                //监听获取到的信息
                chatManager.addChatListener(new ChatManagerListener() {
                    @Override
                    public void chatCreated(Chat chat, boolean createdLocally) {
                        chat.addMessageListener(new ChatMessageListener() {
                            @Override
                            public void processMessage(Chat chat, Message message) {
                                System.out.println(message.getBody());
                            }

                        });
                    }

                });

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public XMPPTCPConnection getXMPPTCPConnection() {
        return connection;
    }

    public boolean mConnection() {
        MyConnection myConnection = new MyConnection();
        myConnection.execute(new String[]{SERVER_HOST, SERVER_PORT + "", SERVER_NAME, username, password});
        return false;
    }
}
