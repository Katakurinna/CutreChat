
import javax.swing.*;
import java.io.IOException;

/**
 * @author Alejandro Cerrato Espejo
 */
public class Aplicacion {

    private static final int DEFAULT_SERVER_PORT = 6000;
    private static final String DEFAULT_SERVER_ADRESS = "127.0.0.1";

    private static String[] applicationLaunchArguments;

    public static void main(String[] args) {
        applicationLaunchArguments = args;

        try {
            System.out.println("Starting user interface...");
            Vista.initComponents();

            System.out.println("Starting server connection...");
            ConnectionManager.startConnection();
        } catch (Exception er) {
            er.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ha habido un fallo, la aplicación se cerrará");
        }
    }

    public static String getServerAddress() {
        if (applicationLaunchArguments.length == 0) return DEFAULT_SERVER_ADRESS;
        return applicationLaunchArguments[0]; // Invalid address could throw an exception later.
    }

    public static int getServerPort() {
        if (applicationLaunchArguments.length <= 1) return DEFAULT_SERVER_PORT;
        return Integer.parseInt(applicationLaunchArguments[1]); // Non integers throws exceptions
    }

}