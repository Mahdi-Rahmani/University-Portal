package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This is the GUI that related to Student class
 * This class extends from MainPanel GUI class that has some main panel
 * that is similar between GUI classes
 *
 * @author Mahdi Rahmani
 * @version 1.0
 */
public class StudentGUI extends MainPanel{
    //The balance account button
    private JButton balanceAccount;
    //The reserve meal button
    private JButton reserveMeal;
    // the register class button
    private JButton registerClass;
    // the student controller
    private StudentController studentController;
    /**
     * This is the constructor of this class
     * creat new AdminGUI and the suitable Panels to the frame
     */
    public StudentGUI()
    {
        super();
        getFirstPanel().setBackground(Color.PINK);
        getSecondPanel().setBackground(Color.MAGENTA);
        getThirdPanel().setBackground(Color.YELLOW);
        rightPanel();
        topPanel("./images/student.png");

    }
    /**
     * set the controller of this class
     * @param studentController the controller of Admin and AdminGUI
     */
    public void setAdminController(StudentController studentController)
    {
        this.studentController = studentController;
    }
    /**
     * The right panel is the panel that the buttons are inside it
     * This method overrides the rightPanel method in MainPanel
     */
    @Override
    public void rightPanel()
    {
        balanceAccount = new JButton("balance account");
        reserveMeal = new JButton("reserve meal");
        registerClass = new JButton("register class");
        addButton(balanceAccount);
        addButton(reserveMeal);
        addButton(registerClass);
        super.rightPanel();

        ButtonHandler handler = new ButtonHandler();
        getProfile().addActionListener(handler);
        balanceAccount.addActionListener(handler);
        reserveMeal.addActionListener(handler);
        registerClass.addActionListener(handler);
        getChangePss_User().addActionListener(handler);
    }
    /**
     * The handler of buttons inside the rightPanel
     */
    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            GridBagConstraints constraints = new  GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;

            if (e.getSource().equals(getProfile())) {
                getThirdPanel().removeAll();
                getThirdPanel().add(profile(),constraints);
                getThirdPanel().revalidate();
                getThirdPanel().repaint();
            } else if (e.getSource().equals(balanceAccount)) {
                getThirdPanel().removeAll();
                getThirdPanel().add(increaseMoney(),constraints);
                getThirdPanel().revalidate();
                getThirdPanel().repaint();
            } else if (e.getSource().equals(reserveMeal)) {
                getThirdPanel().removeAll();
                getThirdPanel().add(mealSetter(),constraints);
                getThirdPanel().revalidate();
                getThirdPanel().repaint();
            } else if (e.getSource().equals(registerClass)){
                getThirdPanel().removeAll();
                getThirdPanel().add(register(),constraints);
                getThirdPanel().revalidate();
                getThirdPanel().repaint();
            }else if (e.getSource().equals(getChangePss_User())){
                getThirdPanel().removeAll();
                getThirdPanel().add(changingPassUser(),constraints);
                getThirdPanel().revalidate();
                getThirdPanel().repaint();
            }
        }
    }

    /**
     * If user click on changingPass & User  button we should update third panel
     * The buttons,Label and ...(GUI) of this action is designed here
     * we get the current password of admin and if that is valid we change
     * the pass and user with new info
     * @return the designed panel
     */
    public JPanel changingPassUser()
    {
        JPanel actionPanel = new JPanel(new BorderLayout(5, 5));
        actionPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        //******THE TOP LABEL******
        JTextArea area = new JTextArea(" ّFor changing password or user name \n after entering the current username \n please enter new password and username");
        area.setBackground(Color.cyan);
        //area.setHorizontalAlignment(SwingConstants.CENTER);
        area.setOpaque(true);
        //adding border for the label of warning
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        area.setBorder(border);
        //the dimension of warning label
        int labelWidth = area.getPreferredSize().width+20;
        int labelHeight = area.getPreferredSize().height + 30;
        area.setPreferredSize(new Dimension(labelWidth, labelHeight));
        //*****************************

        //***THE LABEL AND FIELDS****
        JLabel currentPassLabel = new JLabel(" current passWord:");
        JPasswordField curentPassField = new JPasswordField();
        //user name
        JLabel userNameLabel = new JLabel(" Username : ");
        JTextField userNameField = new JTextField();
        //password
        JLabel passWordLabel = new JLabel(" Password : ");
        JPasswordField passwordField = new JPasswordField();
        //A panel with grid layout for place the labels and fields of the UserName and password in it.
        JPanel fieldsPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        fieldsPanel.add(currentPassLabel);
        fieldsPanel.add(curentPassField);
        fieldsPanel.add(userNameLabel);
        fieldsPanel.add(userNameField);
        fieldsPanel.add(passWordLabel);
        fieldsPanel.add(passwordField);
        //******************************

        //***THE ADD BUTTON***
        JButton add = new JButton("save");
        //the dimension of warning label
        int buttonWidth = add.getPreferredSize().width;
        int buttonHeight = add.getPreferredSize().height + 10;
        add.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        //**********************

        //*** THE ACTIONS****
        add.addActionListener((ActionEvent e) -> {
            studentController.changingPassUser(userNameField.getText(),passwordField.getText(),curentPassField.getText());
        });
        //**********************

        actionPanel.add(area, BorderLayout.NORTH);
        actionPanel.add(fieldsPanel, BorderLayout.CENTER);
        actionPanel.add(add, BorderLayout.SOUTH);
        GridBagConstraints constraints = new  GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        return actionPanel;
    }
    /**
     * If user click on profile button we should update third panel
     * The buttons,Label and ...(GUI) of this action is designed here
     * @return the designed panel
     */
    public JPanel profile()
    {
        JPanel actionPanel = new JPanel(new BorderLayout(5, 5));
        actionPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        int panelWidth = actionPanel.getPreferredSize().width + 300;
        int panelHeight = actionPanel.getPreferredSize().height + 300;
        actionPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
        //******THE TOP LABEL******
        JLabel topLabel = new JLabel("** Profile Information **");
        topLabel.setBackground(Color.cyan);
        topLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topLabel.setOpaque(true);
        //adding border for the label of warning
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        topLabel.setBorder(border);
        //the dimension of warning label
        int labelWidth = topLabel.getPreferredSize().width+20;
        int labelHeight = topLabel.getPreferredSize().height + 30;
        topLabel.setPreferredSize(new Dimension(labelWidth, labelHeight));
        //*****************************

        //***THE LABEL AND FIELDS****
        JLabel name = new JLabel(" Name:");
        name.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0,Color.black));
        JLabel nameData = new JLabel(studentController.studentInfo().get(0)[0]);
        nameData.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 2,Color.black));
        //user name
        JLabel userNameLabel = new JLabel(" Username : ");
        userNameLabel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0,Color.black));
        JLabel userNameData = new JLabel(studentController.studentInfo().get(1)[0]);
        userNameData.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 2,Color.black));
        //password
        JLabel passWordLabel = new JLabel(" Password : ");
        passWordLabel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0,Color.black));
        JLabel passwordData = new JLabel(studentController.studentInfo().get(2)[0]);
        passwordData.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 2,Color.black));
        //average Grade
        JLabel gradeLebel = new JLabel(" Average grade : ");
        gradeLebel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0,Color.black));
        JLabel gradeData = new JLabel(studentController.studentInfo().get(3)[0]);
        gradeData.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 2,Color.black));
        //balance account
        JLabel balanceAccountLabel = new JLabel(" Balance account : ");
        balanceAccountLabel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0,Color.black));
        JLabel balanceAccountData= new JLabel(studentController.studentInfo().get(4)[0]);
        balanceAccountData.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 2,Color.black));
        //classes
        JComboBox classList = new JComboBox(studentController.studentInfo().get(5));
        classList.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 2,Color.black));
        JLabel classLabel = new JLabel(" Classes : ");
        classLabel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0,Color.black));

        //A panel with grid layout for place the labels and fields of the UserName and password in it.
        JPanel fieldsPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        fieldsPanel.add(name);
        fieldsPanel.add(nameData);
        fieldsPanel.add(userNameLabel);
        fieldsPanel.add(userNameData);
        fieldsPanel.add(passWordLabel);
        fieldsPanel.add(passwordData);
        fieldsPanel.add(gradeLebel);
        fieldsPanel.add(gradeData);
        fieldsPanel.add(balanceAccountLabel);
        fieldsPanel.add(balanceAccountData);
        fieldsPanel.add(classLabel);
        fieldsPanel.add(classList);
        //**********************
        actionPanel.add(topLabel, BorderLayout.NORTH);
        actionPanel.add(fieldsPanel, BorderLayout.CENTER);
        return actionPanel;
    }
    /**
     * If user click on balanceAccount button we should update third panel
     * The buttons,Label and ...(GUI) of this action is designed here
     * @return the designed panel
     */
    public JPanel increaseMoney()
    {
        JPanel actionPanel = new JPanel(new BorderLayout(5, 5));
        actionPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        //******THE TOP LABEL******
        JTextArea area = new JTextArea(" ّplease enter your card number and the passWord");
        area.setBackground(Color.cyan);
        //area.setHorizontalAlignment(SwingConstants.CENTER);
        area.setOpaque(true);
        //adding border for the label of warning
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        area.setBorder(border);
        //the dimension of warning label
        int labelWidth = area.getPreferredSize().width+20;
        int labelHeight = area.getPreferredSize().height + 30;
        area.setPreferredSize(new Dimension(labelWidth, labelHeight));
        //*****************************

        //***THE LABEL AND FIELDS****
        //CardNumber
        JLabel cardLabel = new JLabel(" Card number: ");
        JTextField cardData = new JTextField();
        //password
        JLabel passWordLabel = new JLabel(" Password : ");
        JPasswordField passwordField = new JPasswordField();
        //cost
        JLabel moneyLabel = new JLabel(" Value: ");
        JTextField moneyValue = new JTextField();
        //A panel with grid layout for place the labels and fields of the UserName and password in it.
        JPanel fieldsPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        fieldsPanel.add(cardLabel);
        fieldsPanel.add(cardData);
        fieldsPanel.add(passWordLabel);
        fieldsPanel.add(passwordField);
        fieldsPanel.add(moneyLabel);
        fieldsPanel.add(moneyValue);
        //******************************

        //***THE ADD BUTTON***
        JButton add = new JButton("OK");
        //the dimension of warning label
        int buttonWidth = add.getPreferredSize().width;
        int buttonHeight = add.getPreferredSize().height + 10;
        add.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        //**********************

        //*** THE ACTIONS****
        add.addActionListener((ActionEvent e) -> {

            try {
                studentController.increaseMoney(moneyValue.getText());
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }
        });
        //**********************
        actionPanel.add(area, BorderLayout.NORTH);
        actionPanel.add(fieldsPanel, BorderLayout.CENTER);
        actionPanel.add(add, BorderLayout.SOUTH);
        GridBagConstraints constraints = new  GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        return actionPanel;
    }
    /**
     * If user click on set meal button we should update third panel
     * The buttons,Label and ...(GUI) of this action is designed here
     * we have a table that has 8 rows and 4 columns
     * student can reserve each meal
     * @return the designed panel
     */
    public JPanel mealSetter() {
        JPanel actionPanel = new JPanel(new BorderLayout(5, 5));
        actionPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        int panelWidth = actionPanel.getPreferredSize().width + 400;
        int panelHeight = actionPanel.getPreferredSize().height + 300;
        actionPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
        //******THE TOP LABEL******
        JLabel label = new JLabel(" *** The Plan of meal ***");
        label.setBackground(Color.cyan);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        //adding border for the label of warning
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        label.setBorder(border);
        //the dimension of warning label
        int labelWidth = label.getPreferredSize().width;
        int labelHeight = label.getPreferredSize().height + 15;
        label.setPreferredSize(new Dimension(labelWidth, labelHeight));
        //*****************************

        //***THE Table****
        String[] columns = {"Day", "Date", "food", "cost"};

        String[][] data = {
                {"Saturday", "", "", "",""},
                {"Sunday", "", "", "",""},
                {"Monday", "", "", "",""},
                {"Tuesday", "", "", "",""},
                {"Wednesday", "", "", "",""},
                {"Thursday", "", "", "",""},
                {"Friday", "", "", "",""}
        };
        for (int i = 0; i < 7; i++) {
            for (int j = 1; j < 4; j++) {
                data[i][j] = studentController.getPrevDataMeal()[i][j];
            }
        }
        JTable foodTable = new JTable(data, columns);
        foodTable.setRowHeight(28);
        foodTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(foodTable);
        //The reserve column
        Checkbox[] checkboxes = new Checkbox[7];
        JLabel reserve = new JLabel("Reserve");
        reserve.setBackground(Color.MAGENTA);
        reserve.setOpaque(true);
        JPanel boxPanel = new JPanel(new GridLayout(8,1));
        boxPanel.setBorder(border);
        boxPanel.add(reserve);

        for (int i = 0; i<7 ; i++)
        {
            checkboxes[i] = new Checkbox();
            checkboxes[i].setState(studentController.getReservation()[i]);
            boxPanel.add(checkboxes[i]);
        }
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane,BorderLayout.CENTER);
        panel.add(boxPanel,BorderLayout.EAST);
        //***THE ADD BUTTON***
        JButton add = new JButton("Save Changes");
        //the dimension of warning label
        int buttonWidth = add.getPreferredSize().width;
        int buttonHeight = add.getPreferredSize().height + 10;
        add.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        //**********************
        //*** THE ACTIONS****
        add.addActionListener(e -> {
            Boolean[] isReserve = new Boolean[7];
            for (int i =0 ; i<7 ; i++)
                isReserve[i] = checkboxes[i].getState();
            try {
                studentController.setReservation(isReserve);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }
        });
        //**********************
        actionPanel.add(label, BorderLayout.NORTH);
        actionPanel.add(panel, BorderLayout.CENTER);
        actionPanel.add(add, BorderLayout.SOUTH);
        return actionPanel;
    }
    /**
     * If user click on registerClass button we should update third panel
     * The buttons,Label and ...(GUI) of this action is designed here
     * student can choose the classes
     * @return the designed panel
     */
    public JPanel register()
    {
        JPanel actionPanel = new JPanel(new BorderLayout(5, 5));
        actionPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        int panelWidth = actionPanel.getPreferredSize().width + 400;
        int panelHeight = actionPanel.getPreferredSize().height + 300;
        actionPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
        //******THE TOP LABEL******
        JLabel label = new JLabel(" *** The Plan of meal ***");
        label.setBackground(Color.cyan);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        //adding border for the label of warning
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        label.setBorder(border);
        //the dimension of warning label
        int labelWidth = label.getPreferredSize().width;
        int labelHeight = label.getPreferredSize().height + 15;
        label.setPreferredSize(new Dimension(labelWidth, labelHeight));
        //*****************************

        //***THE Table****
        String[] columns = {"Day", "time", "units", "ClassName","Teacher name","index"};

        String[][] data = studentController.getData();

        JTable classTable = new JTable(data, columns);
        classTable.setRowHeight(40);
        classTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(classTable);

        // the entry of user
        JLabel entryLabel = new JLabel("Index");
        JTextField entry = new JTextField();

        JPanel panel = new JPanel(new GridLayout(1,2));
        panel.add(entryLabel);
        panel.add(entry);
        //***THE ADD BUTTON***
        JButton add = new JButton("Add class");
        //the dimension of warning label
        int buttonWidth = add.getPreferredSize().width;
        int buttonHeight = add.getPreferredSize().height + 10;
        add.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

        JPanel panel2 = new JPanel(new GridLayout(2,1));
        panel2.add(panel);
        panel2.add(add);
        //**********************
        //*** THE ACTIONS****
        add.addActionListener(e -> {
            if (entry.getText().isEmpty())
                JOptionPane.showMessageDialog(null, "please enter the index!","Warning",JOptionPane.WARNING_MESSAGE);
            else {
                try {
                    studentController.registerClass(entry.getText(), data);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        });
        //**********************
        actionPanel.add(label, BorderLayout.NORTH);
        actionPanel.add(classTable, BorderLayout.CENTER);
        actionPanel.add(panel2, BorderLayout.SOUTH);
        return actionPanel;
    }
}
