
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author Alejandro Cerrato Espejo
 */
public class Connection extends Thread {

    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    private String clientIp;
    private String clientName;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.inputStream = new DataInputStream(socket.getInputStream());
        this.outputStream = new DataOutputStream(socket.getOutputStream());

        this.clientIp = socket.getInetAddress().getHostAddress();

        start();
    }

    @Override
    public void run() {
        try {
            establishConnection();
            while (!socket.isClosed()) {
                String message = inputStream.readUTF();
                ConnectionManager.addMessage(this, message);
            }
        } catch (Exception ex) {
            System.out.println(clientName + " > Connection loss. (" + ex.getMessage() + ")");
        }
        ConnectionManager.removeConnection(this);
    }

    private void establishConnection() throws IOException {
        System.out.println(clientIp + " > has been connected.");
        waitForUsername();
        sendMessageHistory();
        System.out.println("[" + clientName + " > has successful connected!]");
    }

    public void waitForUsername() throws IOException {
        System.out.println(clientIp + " > waiting for username...");
        clientName = inputStream.readUTF();
        System.out.println(clientIp + " > has set '" + clientName + "' has username.");
    }

    public void sendMessageHistory() {
        System.out.println(clientName + " > Sending message history...");
        for (String line : ConnectionManager.getMessageHistory()) {
            sendMessage(line);
        }
    }

    public void sendMessage(String message) {
        try {
            outputStream.writeUTF(message);
        } catch (IOException exception) {
            System.err.println("Error sending message to '" + clientName + "'");
            exception.printStackTrace();
        }
    }

    public String getUsername() {
        return clientName;
    }
}