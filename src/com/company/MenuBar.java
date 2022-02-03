package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

/**
 * This is menu bar class
 * I design the menuBar of his class here
 *
 * @author Mahdi Rahmani
 * @version 1.0
 */
public class MenuBar extends JMenuBar {
    // The frame of programme
    private JFrame frame;
    //frame icon image
    private final ImageIcon icon = new ImageIcon("./images/portal.png");
    //editMenu
    private JMenu optionsMenu;
    //viewMenu
    private JMenu viewMenu;
    //windowMenu
    private JMenu exitMenu;
    //helpMenu
    private JMenu helpMenu;
    /**
     * Create a new MenuBar.
     *
     * @param frame frame.
     */
    public MenuBar(JFrame frame) {
        this.frame = frame;
        Font font = new Font("Times New Roman (Headings CS)", Font.PLAIN, 12);
        exitMenu = new JMenu("Exit");
        exitMenu.setFont(font);
        viewMenu = new JMenu("View");
        viewMenu.setFont(font);
        optionsMenu = new JMenu("Window");
        optionsMenu.setFont(font);
        helpMenu = new JMenu("Help");
        helpMenu.setFont(font);

        makeExitMenu();
        makeOptionsMenu();
        makeViewMenu();
        makeHelpMenu();

        add(exitMenu);
        add(viewMenu);
        //add(optionsMenu);
        add(helpMenu);
    }

    /**
     * Make the exitMenu for programme
     */
    private void makeExitMenu()
    {
        JMenuItem quit = new JMenuItem("Exit All");
        quit.setFont(new Font("Arial", Font.PLAIN, 12));
        quit.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "Do you want to Exit?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });


        exitMenu.add(quit);
    }

    /**
     * the option menu
     */
    private void makeOptionsMenu()
    {
        JMenuItem pssWordChange = new JMenuItem("Changing password");
        JMenuItem userChange = new JMenuItem("Changing userName");

        pssWordChange.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_MASK));
        userChange.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
    }

    /**
     * The view menu operation
     * you can toggle full screen or normal screen
     */
    private void makeViewMenu()
    {
        JMenuItem toggleFull = new JMenuItem("Toggle Full Screen");
        toggleFull.setFont(new Font("Arial", Font.PLAIN, 12));
        toggleFull.addActionListener(e -> {
            if (toggleFull.getText().equals("Toggle Full Screen")) {
                frame.setExtendedState(Frame.MAXIMIZED_BOTH);
                toggleFull.setText("Toggle Normal Screen");
            }
            else {
                frame.setExtendedState(Frame.NORMAL);
                toggleFull.setText("Toggle Full Screen");
            }
        });
        toggleFull.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0));


        JMenuItem toggleSidebar = new JMenuItem("Toggle Sidebar");
        toggleSidebar.setFont(new Font("Arial", Font.PLAIN, 12));
        toggleSidebar.addActionListener(e -> {
            if (toggleSidebar.getText().equals("Toggle Sidebar")) {
                toggleSidebar.setText("Toggle Normal");
                frame.setLocation(0,0);
                frame.setSize(700,1024);
            }
            else {
                toggleSidebar.setText("Toggle Sidebar");
                frame.setSize(1100, 600);
                frame.setLocation(100, 200);
            }
        });
        toggleSidebar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        viewMenu.add(toggleSidebar);
        viewMenu.add(toggleFull);
    }

    /**
     * set the operation of help menu
     * we set (About) menu item that contains the name of programmer
     */
    private void makeHelpMenu() {
        Color aboutColor = new Color(40,41,37);
        JMenuItem about = new JMenuItem("About");
        about.setFont(new Font("Arial", Font.PLAIN, 12));
        about.addActionListener(e -> {
            JFrame aboutFrame = new JFrame();
            aboutFrame.setIconImage(icon.getImage());
            JPanel frame = new JPanel(new BorderLayout());
            frame.setBackground(aboutColor);
            aboutFrame.setBackground(aboutColor);
            aboutFrame.setTitle("About ");
            aboutFrame.setSize(400, 200);
            aboutFrame.setLocation(100, 200);
            aboutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JLabel aboutField = new JLabel("programmer: Mahdi Rahmani©");
            aboutField.setForeground(Color.white);
            aboutField.setHorizontalAlignment(JTextField.CENTER);
            frame.add(aboutField, BorderLayout.CENTER);
            aboutFrame.add(frame, BorderLayout.CENTER);
            aboutFrame.setVisible(true);
        });
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
        JMenuItem help2 = new JMenuItem("Help");
        help2.setFont(new Font("Arial", Font.PLAIN, 12));

        help2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        helpMenu.add(about);
        helpMenu.add(help2);
    }
}
