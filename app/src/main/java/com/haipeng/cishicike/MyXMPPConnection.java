package com.haipeng.cishicike;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
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


    //    public static final String SERVER_HOST="10.12.49.31";
    //    public static final int SERVER_PORT=5222;
    //    public static final String SERVER_NAME="MS_SYY";
//    public static final String SERVER_HOST = "talk.google.com";
//    public static final int SERVER_PORT = 5222;
//    public static final String SERVER_NAME = "google.com";

    //公司office computer
    public static final String SERVER_HOST = "10.12.49.31";
    public static final int SERVER_PORT = 5222;
    public static final String SERVER_NAME = "mds-sunyiyan";

    String username = "wang";
    String password = "wo83450837";
    String resource = "jadder.org";

    //宿舍网络，my computer
//    public static final String SERVER_HOST = "192.168.1.103";
//    public static final int SERVER_PORT = 5222;
//    public static final String SERVER_NAME = "3e9ty4tqgbjxh3mr";
//
//
//    String username = "admin";
//    String password = "admin";
//    String resource = "jadder.org";

    //google
//    public static final String SERVER_HOST = "127.0.0.1";
//    public static final int SERVER_PORT = 5222;
//    public static final String SERVER_NAME = "mds-sunyiyan";
//
//
//    String username = "admin";
//    String password = "admin";
//    String resource = "jadder.org";

    ConnectionConfiguration configuration;
    XMPPTCPConnection connection;

    public class MyConnection extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            try {
//            connection.login(params[0], params[1]);
                configuration = new ConnectionConfiguration(params[0], Integer.parseInt(params[1]), params[2]);
                configuration.setSecurityMode(ConnectionConfiguration.SecurityMode.enabled);
                configuration.setKeystoreType("jks");

                connection = new XMPPTCPConnection(configuration);

//                connection = new XMPPTCPConnection(params[0]);

                connection.connect();

                connection.login(params[3], params[4]);

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

    public void setnMsg(String username, String txt) {

        if (connection != null) {
            try {

                Chat chat = ChatManager.getInstanceFor(connection).createChat(
                        "1481249319@qq.com/Smack", new ChatMessageListener() {
                            @Override
                            public void processMessage(Chat chat, Message message) {
                                Bundle bundle = new Bundle();
                                bundle.putString("msg", message.getBody());
                                android.os.Message message1 = new android.os.Message();
                                message1.what = 0;
                                message1.setData(bundle);

                            }
                        }
                );
                chat.sendMessage("Hello World!");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public boolean mConnection() {
//        configuration = new ConnectionConfiguration(SERVER_HOST, SERVER_PORT, SERVER_NAME);

        //for connect google

//    configuration
        MyConnection myConnection = new MyConnection();
//        myConnection.execute(SERVER_HOST);
        myConnection.execute(new String[]{SERVER_HOST, SERVER_PORT + "", SERVER_NAME, username, password, resource});
        return false;
    }

//    Handler myhandler = new Handler() {
//        @Override
//        public void handleMessage(android.os.Message msg) {
//            // TODO Auto-generated method stub
//            super.handleMessage(msg);
//            String txt = null;
//            switch (msg.what) {
//                case 0:
//                    txt = msg.getData().getString("msg");
////                    Toast.makeText(this, txt, 1000).show();
//                    System.out.print(txt);
//                    break;
//
//
//                default:
//                    break;
//            }
//        }
//    }

}
