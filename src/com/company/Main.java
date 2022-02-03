package com.company;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * This is the main class
 * We run the programme from here
 *
 * @author Mahdi Rahmani
 * @version 1.0
 * @since 2020-12-18
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        /*for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            System.out.println(info.getClassName());
        }
        System.out.println(UIManager.getSystemLookAndFeelClassName());
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");*/

        View portalView = new View();
        try {
            Admin admin1 = new Admin("admin1", "1234");
            //File
            String fileAddress = "./Data/admins/admin1.ser";
            File myAdmin = new File(fileAddress);

            WriteObjectToFile write = new WriteObjectToFile(fileAddress);
            write.writeToFile(admin1);
            write.closeConnection();

        } catch (IOException ioException) {
            JOptionPane.showMessageDialog(null, ioException.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}