
import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 *
 * @author Alejandro Cerrato Espejo
 */
public class Vista {

    private static JLabel textoContadorClientes;
    private static JLabel contadorClientesConectados;
    private static TextArea messageList;
    private static JPanel panel;
    private static JScrollPane scrollPanel;
    private static JFrame jFrame = new JFrame();

    public static void initComponents() {
        jFrame.setVisible(true);

        panel = new JPanel();
        scrollPanel = new JScrollPane();

        messageList = new TextArea();
        textoContadorClientes = new JLabel();
        contadorClientesConectados = new JLabel("0");

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        scrollPanel.setViewportView(messageList);

        textoContadorClientes.setText("Clientes conectados:");

        contadorClientesConectados.setForeground(new Color(26, 148, 49));
        GroupLayout jPanel1Layout = new GroupLayout(panel);
        panel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(scrollPanel, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(textoContadorClientes)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(contadorClientesConectados, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap()));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPanel, GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(textoContadorClientes, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(contadorClientesConectados, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        GroupLayout layout = new GroupLayout(jFrame.getContentPane());
        jFrame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap()));

        jFrame.pack();
    }

    public static void addMessage(String line){
        messageList.append(line + "\n");
    }

    public static void updateConnections(){
        int connectedUsers = ConnectionManager.getConnections().size();
        contadorClientesConectados.setText("" + connectedUsers);
    }
}
