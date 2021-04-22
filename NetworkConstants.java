/**
 *
 * This class defines a set of constants used by the clients and servers in
 * Homework # 1
 *
 */

public interface NetworkConstants {
        // These constants are implicitly public static final

        int BUFFER_LENGTH = 1024;
        int TCP_SERVER_PORT = 60000;
        String TCP_SERVER_IP = "127.0.0.1";

        String SERVER_CONNECTION_MESSAGE = "Client Connection Established";

        String SERVER_STARTED = "Server is ready ...";

        String CHAT_CLIENT_TITLE = "Chat Client";

        String SEND_BUTTON_TITLE = "Send";

}// end class NetworkConstants
