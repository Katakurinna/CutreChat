
/**
 * @author Alejandro Cerrato Espejo
 */
public class Aplicacion {

    private static int PORT = 6000;

    public static void main(String[] args) throws Exception{
        System.out.println("Starting user interface.");
        Vista.initComponents();
        System.out.println("Iniciando servidor...");
        ConnectionManager.startConnectionListener(PORT);
    }
}
