package com.haipeng.cishicike;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;


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
//    public static final String SERVER_HOST = "10.12.49.31";
//    public static final int SERVER_PORT = 5222;
//    public static final String SERVER_NAME = "mds-sunyiyan";
//
//    String username = "wang";
//    String password = "wo83450837";
//    String resource = "jadder.org";

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
    public static final String SERVER_HOST = "gmail.com";
    public static final int SERVER_PORT = 5222;
//    public static final String SERVER_NAME = "3e9ty4tqgbjxh3mr";


    String username = "admin";
    String password = "admin";
    String resource = "jadder.org";

    ConnectionConfiguration configuration;
    XMPPTCPConnection connection;

    public boolean mConnection() {
//        configuration = new ConnectionConfiguration(SERVER_HOST, SERVER_PORT, SERVER_NAME);

        //for connect google
        configuration = new ConnectionConfiguration(SERVER_HOST, SERVER_PORT);
//    configuration
        connection = new XMPPTCPConnection(configuration);
        try {
//            connection.login(username, password, resource);
            connection.connect();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return connection.isConnected();
    }


}
