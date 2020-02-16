
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alejandro Cerrato Espejo
 */
public class ConnectionManager {

    private static ServerSocket serverSocket;

    private static List<String> listaMensajes = new ArrayList<>();
    private static List<Connection> connections = new ArrayList<>();

    public static List<String> getMessageHistory() {
        return listaMensajes;
    }

    public static List<Connection> getConnections() {
        return connections;
    }

    public static void startConnectionListener(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true) {
            Socket socketConnection = serverSocket.accept();
            String ipAddress = socketConnection.getInetAddress().getHostAddress();
            System.out.println("Incomming connection from: "+ipAddress);
            ConnectionManager.addConnection(socketConnection);
        }
    }

    public static void addConnection(Socket socket) throws IOException {
        Connection client = new Connection(socket);
        connections.add(client);

        Vista.updateConnections();
    }

    public static void removeConnection(Connection connection) {
        connections.remove(connection);
        Vista.updateConnections();
    }

    public static void addMessage(Connection connection, String msg) {
        String line = connection.getUsername() + " : " + msg;
        listaMensajes.add(line);
        Vista.addMessage(line);
        sendMessage(line);
    }

    public static void sendMessage(String msg) {
        System.out.println(msg);
        for (Connection connection : connections) {
            connection.sendMessage(msg);
        }
    }

}
