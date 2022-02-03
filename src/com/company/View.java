package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class holds the Frame of GUI and we add different panels on it
 * first the login panel is shown here and then we change panel
 *
 * @author Mahdi Rahmani
 * @version 1.0
 */
public class View {
    //menubar
    private MenuBar menuBar;
    //AdminGUI
    private AdminGUI adminGUI;
    //Student GUI
    private StudentGUI studentGUI;
    //TeacherGUI
    private TeacherGUI teacherGUI;
    // A JTextField for getting the user name
    private JTextField userNameField;
    // A JPasswordField for getting the password of user
    private JPasswordField passwordField;
    // A JCombo box for choose the type of user
    private JComboBox userTypeField;
    // A button for login after entering the information
    private JButton loginButton;
    // the login panel
    private JPanel loginPanel;
    // the confirm field
    private boolean confirm;
    // the first panel or top panel
    private JPanel firstMain;
    // the second panel or right panel
    private JPanel secondMain;
    // the main panel that we change it
    private JPanel main;
    // the Frame
    private JFrame frame;
    // the return item in menu bar
    private JMenu aReturn;
    // login image
    private BufferedImage image;
    /**
     * the constructor of this class
     * create new view and add login panel to frame
     */
    public View() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        frame = new JFrame("Portal");
        adminGUI = new AdminGUI();
        studentGUI = new StudentGUI();
        teacherGUI = new TeacherGUI();
        menuBar = new MenuBar(frame);


        frame.setLayout(new GridLayout(1,1));
        main = new JPanel(new GridLayout(1,1));
        frame.add(main);

        firstMain = new JPanel(new GridBagLayout());
        firstMain.setBackground(Color.DARK_GRAY);
        secondMain = new JPanel(new GridLayout(1,1));
        GridBagConstraints constraints = new  GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;

        //look and feel
       /* try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}*/

        frame.setSize(1100, 600);
        frame.setLocation(100, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(new Color(40, 41, 37));
        //Icon
        ImageIcon icon = new ImageIcon("./images/portal.png");
        frame.setIconImage(icon.getImage());

        loginPanel = new JPanel(new GridLayout(2,1));
        //*****************************
        // the icon
        try {
            image = ImageIO.read(new File("./images/login5.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel picLabel = new JLabel(new ImageIcon(image));
        picLabel.setSize(150, 160);

        Image imageJLFit = image.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(), Image.SCALE_SMOOTH);
        picLabel.setIcon(new ImageIcon(imageJLFit));
        //***THE LABEL AND FIELDS****
        //user name
        JLabel userNameLabel = new JLabel(" Username : ");
        userNameField = new JTextField("Username");
        userNameLabel.setForeground(Color.BLACK);
        userNameLabel.setBackground(firstMain.getBackground());
        userNameField.setBackground(firstMain.getBackground());
        userNameField.setForeground(Color.BLACK);
        userNameLabel.setOpaque(true);
        userNameField.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, firstMain.getBackground()));
        userNameField.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, firstMain.getBackground()));
        userNameField.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.black));
        //password
        JLabel passWordLabel = new JLabel(" Password : ");
        passWordLabel.setForeground(Color.BLACK);
        passwordField = new JPasswordField();
        passWordLabel.setBackground(firstMain.getBackground());
        passwordField.setBackground(firstMain.getBackground());
        passwordField.setForeground(Color.BLACK);
        passWordLabel.setOpaque(true);
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, firstMain.getBackground()));
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, firstMain.getBackground()));
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.black));
        //type of user

        String [] usersList = {"Student","Teacher","Admin"};
        JLabel userType = new JLabel(" User type:");
        userType.setForeground(Color.BLACK);
        userTypeField = new JComboBox(usersList);
        userType.setBackground(firstMain.getBackground());
        //userTypeField.setBackground(firstMain.getBackground());
        //userTypeField.setFont(new Font("Serif", Font.BOLD, 16));
        userTypeField.setEditable(true);
        userTypeField.getEditor().getEditorComponent().setBackground(firstMain.getBackground());
        userTypeField.setForeground(Color.BLACK);
        UIManager.put("ComboBox.background", new ColorUIResource(firstMain.getBackground()));
        userType.setOpaque(true);
        //A panel with grid layout for place the labels and fields of the UserName and password in it.
        JPanel fieldsPanel = new JPanel(new GridLayout(4, 2, 0, 0));
        fieldsPanel.add(userNameLabel);
        fieldsPanel.add(userNameField);
        fieldsPanel.add(passWordLabel);
        fieldsPanel.add(passwordField);
        JLabel jLabel = new JLabel();
        jLabel.setBackground(firstMain.getBackground());
        jLabel.setOpaque(true);
        JLabel label1 = new JLabel();
        label1.setBackground(firstMain.getBackground());
        label1.setOpaque(true);
        fieldsPanel.add(jLabel);
        fieldsPanel.add(label1);
        fieldsPanel.add(userType);
        fieldsPanel.add(userTypeField);
        //******************************

        //***THE LOGIN BUTTON***
        JPanel loginButPan = new JPanel(new GridBagLayout());
        loginButton = new JButton("Login");
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        //the dimension of warning label
        int buttonWidth = loginButton.getPreferredSize().width +20;
        int buttonHeight = loginButton.getPreferredSize().height + 10;
        loginButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        GridBagConstraints constraints1 = new  GridBagConstraints();
        constraints1.gridx = 0;
        constraints1.gridy = 0;
        loginButPan.add(loginButton,constraints1);
        loginButPan.setBackground(firstMain.getBackground());
        //**********************

        //*** THE ACTIONS****
        ButtonHandler handler = new ButtonHandler();
        userNameField.addActionListener(handler);
        ///userNameField.addFocusListener(handler);

        passwordField.addActionListener(handler);
        //passwordField.addFocusListener(handler);

        loginButton.addActionListener(handler);
        //**********************

        /*JPanel fieldButt = new JPanel(new BorderLayout());
        fieldButt.add(fieldsPanel, BorderLayout.CENTER);
        fieldButt.add(loginButPan, BorderLayout.SOUTH);
        fieldButt.setBackground(firstMain.getBackground());*/

        JPanel fieldButt = new JPanel(new GridLayout(3,1));
        fieldButt.add(fieldsPanel);
        fieldButt.add(loginButPan);
        fieldButt.setBackground(firstMain.getBackground());

        loginPanel.add(picLabel);
        loginPanel.add(fieldButt);
        loginPanel.setBackground(firstMain.getBackground());
        firstMain.add(loginPanel,constraints);
        main.add(firstMain);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }

    /**
     * The handler of login button
     */
    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(loginButton)) {
                System.out.println("Button");
            } else if (e.getSource().equals(userNameField)) {
                System.out.println("User Field");
            } else if (e.getSource().equals(passwordField)) {
                System.out.println("Password Field");
            }



            String user = userNameField.getText();
            String pwd = new String(passwordField.getPassword());
            try{
                user = (String) userTypeField.getItemAt(userTypeField.getSelectedIndex());
                if (user.equals("Student")){
                    Student student = new Student(userNameField.getText(), passwordField.getText());
                    StudentController studentController = new StudentController(student, studentGUI);
                    main.removeAll();
                    secondMain.removeAll();
                    secondMain.add(studentGUI);
                    main.add(secondMain);
                    returnMenu();
                    main.revalidate();
                    main.repaint();
                } else if(user.equals("Teacher")){
                    Teacher teacher = new Teacher(userNameField.getText(), passwordField.getText());
                    TeacherController teacherController = new TeacherController(teacher, teacherGUI);
                    main.removeAll();
                    secondMain.removeAll();
                    secondMain.add(teacherGUI);
                    main.add(secondMain);
                    returnMenu();
                    main.revalidate();
                    main.repaint();
                }else if (user.equals("Admin")){
                    Admin admin = new Admin(userNameField.getText(), passwordField.getText());
                    AdminController adminController= new AdminController(admin, adminGUI);
                    main.removeAll();
                    secondMain.removeAll();
                    secondMain.add(adminGUI);
                    main.add(secondMain);
                    returnMenu();
                    main.revalidate();
                    main.repaint();
                }
            } catch (IOException ioException){
                confirm = false;
                JOptionPane.showMessageDialog(null, ioException.getMessage(), "Result", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * creat return menu for the menu bar
     */
    public void returnMenu()
    {
        aReturn = new JMenu("Return");
        JMenuItem back = new JMenuItem("login page");
        back.setFont(new Font("Arial", Font.PLAIN, 12));
        aReturn.add(back);
        menuBar.add(aReturn);
        back.addActionListener(e -> {
            main.removeAll();
            main.add(firstMain);
            menuBar.remove(aReturn);
            menuBar.revalidate();
            main.revalidate();
            main.repaint();
        });
    }

}
