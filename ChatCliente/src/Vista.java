
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

/**
 * @author Alejandro Cerrato Espejo
 */
public class Vista {

    private static JFrame jFrame = new JFrame();

    private static JPanel panel = new JPanel();
    private static JSeparator areaSeparator = new JSeparator();

    private static TextArea chatLog = new TextArea();
    private static JTextField chatInputField = new JTextField();
    private static JButton sendBtn = new JButton();

    public static void initComponents() {

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        chatInputField.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        chatInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() != KeyEvent.VK_ENTER) return;
                ConnectionManager.sendMessage();
            }
            @Override
            public void keyTyped(KeyEvent ke) {}
            @Override
            public void keyReleased(KeyEvent ke) {}
        });

        sendBtn.setText("Enviar");
        sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ConnectionManager.sendMessage();
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(panel);
        panel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(areaSeparator, GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(chatInputField, GroupLayout.PREFERRED_SIZE, 471, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sendBtn, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                        .addComponent(chatLog, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(chatLog, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(areaSeparator, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(sendBtn, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                                        .addComponent(chatInputField))
                                .addContainerGap()));

        GroupLayout layout = new GroupLayout(jFrame.getContentPane());
        jFrame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jFrame.pack();

        jFrame.setVisible(true);
        jFrame.setResizable(false);
    }

    public static String getInputMessageAndClear(){
        String line = getInputMessage();
        clearInputField();
        return line;
    }

    public static String getInputMessage() {
        return chatInputField.getText();
    }

    public static void clearInputField() {
        chatInputField.setText("");
    }

    public static void setMessageHistory(List<String> messageHistory) {
        for(String line : messageHistory){
            addMessage(line);
        }
    }

    public static void addMessage(String message){
        chatLog.append(message + "\n");
    }
}
