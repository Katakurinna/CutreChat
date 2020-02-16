
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Alejandro Cerrato Espejo
 */
public class ConnectionManager {

    private static Connection connection;

    public static void startConnection() throws IOException {
        String username = askForUsername();

        connection = new Connection(Aplicacion.getServerAddress(), Aplicacion.getServerPort(), username);
        connection.start();
    }

    public static String askForUsername(){
        System.out.println("Asking client for username...");
        String username = JOptionPane.showInputDialog("Introduce tu nombre de usuario:").trim();
        return (username.length() == 0) ? "Anónimo" : username;
    }

    public static void sendMessage() {
        String line = Vista.getInputMessageAndClear();
        connection.sendMessage(line);
    }

    public static void printMessage(String line) {
        System.out.println(line);
        Vista.addMessage(line);
    }
}
