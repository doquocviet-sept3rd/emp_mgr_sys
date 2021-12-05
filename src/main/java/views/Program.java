package views;

import views.Frames.login.LoginFrame;

import javax.swing.*;

public class Program {

    public static void main(String[] args) {

        // run program

        // nimbus theme
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        // Show your JFrame
        new LoginFrame().setVisible(true);
    }
}
