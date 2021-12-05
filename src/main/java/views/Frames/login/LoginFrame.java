package views.Frames.login;

import views.Frames.dashboard.DashboardFrame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author vcoder
 */
public class LoginFrame extends JFrame {
    public LoginFrame() {
        initComponents();
        createUIComponents();
    }

    private void createUIComponents() {
        //this.setIconImage(new ImageIcon("src/main/java/views/resources/images/logo.ico").getImage());
    }

    private void chBoxShowPassword_Onclick(ActionEvent e) {
        if (txfPassword.getEchoChar() != (char) 0) {
            txfPassword.setEchoChar((char)0);
        } else {
            txfPassword.setEchoChar('\u2022');
        }
    }

    private void btnLogin_Onclick(ActionEvent e) {
        String username = txfUsername.getText() != null ? txfUsername.getText() : "";
        String password = txfPassword.getPassword() != null ? String.valueOf(txfPassword.getPassword()) : "";
        if (username.equals("admin") && password.equals("123456")) {
            new DashboardFrame().setVisible(true);
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect username or password!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void thisWindowClosing(WindowEvent e) {
        /*
        if (JOptionPane.showConfirmDialog(this, "Do you want to exit?", "Warning!", JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE) == 0) {
            System.exit(0);
        }
        */
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Do Quoc Viet
        label1 = new JLabel();
        txfUsername = new JTextField();
        label2 = new JLabel();
        chBoxShowPassword = new JCheckBox();
        btnLogin = new JButton();
        txfPassword = new JPasswordField();

        //======== this ========
        setTitle("Login");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("Username:");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(25, 25), label1.getPreferredSize()));
        contentPane.add(txfUsername);
        txfUsername.setBounds(110, 20, 210, 30);

        //---- label2 ----
        label2.setText("Password:");
        contentPane.add(label2);
        label2.setBounds(25, 70, 70, 16);

        //---- chBoxShowPassword ----
        chBoxShowPassword.setText("Show password");
        chBoxShowPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        chBoxShowPassword.addActionListener(e -> chBoxShowPassword_Onclick(e));
        contentPane.add(chBoxShowPassword);
        chBoxShowPassword.setBounds(110, 105, 205, chBoxShowPassword.getPreferredSize().height);

        //---- btnLogin ----
        btnLogin.setText("Login");
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogin.addActionListener(e -> btnLogin_Onclick(e));
        contentPane.add(btnLogin);
        btnLogin.setBounds(110, 135, 210, btnLogin.getPreferredSize().height);

        //---- txfPassword ----
        txfPassword.setEchoChar('\u2022');
        contentPane.add(txfPassword);
        txfPassword.setBounds(110, 60, 210, 30);

        contentPane.setPreferredSize(new Dimension(355, 220));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Do Quoc Viet
    private JLabel label1;
    private JTextField txfUsername;
    private JLabel label2;
    private JCheckBox chBoxShowPassword;
    private JButton btnLogin;
    private JPasswordField txfPassword;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
