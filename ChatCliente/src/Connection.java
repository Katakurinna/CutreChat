
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

/**
 * @author Alejandro Cerrato Espejo
 */
public class Connection extends Thread {

    private Socket socket;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;

    private String serverAddress;
    private int serverPort;

    private String username;

    public Connection(String serverAddress, int serverPort, String username) throws IOException {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.username = username;
    }

    @Override
    public void run() {
        while (true) {
            try {
                connect();
            } catch (Exception er) {
                System.out.println("Unable to connect to " + serverAddress + ". (" + er.getMessage() + ")");
            }
        }
    }

    public void connect() throws IOException {
        System.out.println("Connecting to " + serverAddress + "...");
        this.socket = new Socket(serverAddress, serverPort);
        this.inputStream = new DataInputStream(socket.getInputStream());
        this.outputStream = new DataOutputStream(socket.getOutputStream());

        sendUsername();
        System.out.println("Connected!");
        startMessageListener();
    }

    public void startMessageListener() {
        while (socket.isConnected()) {
            try {
                String message = inputStream.readUTF();
                ConnectionManager.printMessage(message);
            } catch (SocketException ex) {
                System.out.println("Connection loss. (" + ex.getMessage() + ")");
                return;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void sendUsername() {
        try {
            outputStream.writeUTF(username);
        } catch (Exception er) {
            er.printStackTrace();
        }
    }

    public void sendMessage(String line) {
        System.out.println("Sending message: " + line);
        try {
            outputStream.writeUTF(line);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
