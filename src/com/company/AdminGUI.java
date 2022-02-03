package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/**
 * This is the GUI that related to Admin class
 * This class extends from MainPanel GUI class that has some main panel
 * that is similar between GUI classes
 *
 * @author Mahdi Rahmani
 * @version 1.0
 */
public class AdminGUI extends MainPanel {
    //The addStudent button for adding student to portal
    private JButton addStudent;
    //The addTeacher button for adding teacher to portal
    private JButton addTeacher;
    //The setMeal button for set the food table of one week
    private JButton setMeal;
    //The studentList button for showing the list of students
    private JButton studentList;
    //The teacherList button for showing the list of teachers
    private JButton teacherList;
    //The classList button for showing the list of classes
    private JButton classesList;
    //The controller of Admin and AdminGUI
    private AdminController adminController;

    /**
     * This is the constructor of this class
     * creat new AdminGUI and the suitable Panels to the frame
     */
    public AdminGUI()
    {
        super();
        getFirstPanel().setBackground(Color.CYAN);
        getSecondPanel().setBackground(Color.blue);
        getThirdPanel().setBackground(Color.YELLOW);
        rightPanel();
        topPanel("./images/admin.png");
    }

    /**
     * set the controller of this class
     * @param adminController the controller of Admin and AdminGUI
     */
    public void setAdminController(AdminController adminController)
    {
        this.adminController = adminController;
    }
    /**
     * The right panel is the panel that the buttons are inside it
     * This method overrides the rightPanel method in MainPanel
     */
    @Override
    public void rightPanel()
    {
        addStudent = new JButton("add student");
        addTeacher = new JButton("add teacher");
        setMeal = new JButton("set meal");
        studentList = new JButton("students list");
        teacherList = new JButton("teachers list");
        classesList = new JButton("classes list");

        addButton(addStudent);
        addButton(addTeacher);
        addButton(setMeal);
        addButton(studentList);
        addButton(teacherList);
        addButton(classesList);
        super.rightPanel();

        ButtonHandler handler = new ButtonHandler();
        getProfile().addActionListener(handler);
        addStudent.addActionListener(handler);
        addTeacher.addActionListener(handler);
        setMeal.addActionListener(handler);
        studentList.addActionListener(handler);
        teacherList.addActionListener(handler);
        classesList.addActionListener(handler);
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
            if (e.getSource().equals(addStudent)) {
                getThirdPanel().removeAll();
                getThirdPanel().add(setAddStudent(),constraints);
                getThirdPanel().revalidate();
                getThirdPanel().repaint();
            } else if (e.getSource().equals(addTeacher)) {
                getThirdPanel().removeAll();
                getThirdPanel().add(setAddTeacher(),constraints);
                getThirdPanel().revalidate();
                getThirdPanel().repaint();
            } else if (e.getSource().equals(setMeal)) {
                getThirdPanel().removeAll();
                getThirdPanel().add(mealSetter(),constraints);
                getThirdPanel().revalidate();
                getThirdPanel().repaint();
            } else if (e.getSource().equals(studentList)){
                getThirdPanel().removeAll();
                getThirdPanel().add(studentListSetter(),constraints);
                getThirdPanel().revalidate();
                getThirdPanel().repaint();
            } else if (e.getSource().equals(teacherList)) {
                getThirdPanel().removeAll();
                getThirdPanel().add(teacherListSetter(),constraints);
                getThirdPanel().revalidate();
                getThirdPanel().repaint();
            } else if (e.getSource().equals(classesList)){
                getThirdPanel().removeAll();
                getThirdPanel().add(classListShow(),constraints);
                getThirdPanel().revalidate();
                getThirdPanel().repaint();
            } else if (e.getSource().equals(getProfile())){
                getThirdPanel().removeAll();
                getThirdPanel().add(profile(),constraints);
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
     * If user click on addStudent button we should update third panel
     * The buttons,Label and ...(GUI) of this action is designed here
     * @return the designed panel
     */
    public JPanel setAddStudent()
    {
        JPanel actionPanel = new JPanel(new BorderLayout(5, 5));
        actionPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        //******THE TOP LABEL******
        JLabel label = new JLabel(" ّFor adding student please enter the information ");
        label.setBackground(Color.cyan);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        //adding border for the label of warning
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        label.setBorder(border);
        //the dimension of warning label
        int labelWidth = label.getPreferredSize().width + 10;
        int labelHeight = label.getPreferredSize().height + 15;
        label.setPreferredSize(new Dimension(labelWidth, labelHeight));
        //*****************************

        //***THE LABEL AND FIELDS****
        JLabel nameLabel = new JLabel(" Name : ");
        JTextField nameField = new JTextField();
        //the student number
        JLabel studentNumber = new JLabel(" student number: ");
        JTextField studentNumberField = new JTextField();
        //user name
        JLabel userNameLabel = new JLabel(" Username : ");
        JTextField userNameField = new JTextField();
        //password
        JLabel passWordLabel = new JLabel(" Password : ");
        JPasswordField passwordField = new JPasswordField();
        //A panel with grid layout for place the labels and fields of the UserName and password in it.
        JPanel fieldsPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        fieldsPanel.add(nameLabel);
        fieldsPanel.add(nameField);
        fieldsPanel.add(studentNumber);
        fieldsPanel.add(studentNumberField);
        fieldsPanel.add(userNameLabel);
        fieldsPanel.add(userNameField);
        fieldsPanel.add(passWordLabel);
        fieldsPanel.add(passwordField);
        //******************************

        //***THE ADD BUTTON***
        JButton add = new JButton("Add student");
        //the dimension of warning label
        int buttonWidth = add.getPreferredSize().width;
        int buttonHeight = add.getPreferredSize().height + 10;
        add.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        //**********************

        //*** THE ACTIONS****
        add.addActionListener((ActionEvent e) -> {
            adminController.addStudent(studentNumberField.getText(), userNameField.getText(), passwordField.getText(), nameField.getText());
        });
        //**********************

        actionPanel.add(label, BorderLayout.NORTH);
        actionPanel.add(fieldsPanel, BorderLayout.CENTER);
        actionPanel.add(add, BorderLayout.SOUTH);
        return actionPanel;
    }

    /**
     * If user click on addTeacher button we should update third panel
     * The buttons,Label and ...(GUI) of this action is designed here
     * @return the designed panel
     */
    public JPanel setAddTeacher()
    {
        JPanel actionPanel = new JPanel(new BorderLayout(5, 5));
        actionPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        //******THE TOP LABEL******
        JLabel label = new JLabel(" ّFor adding teacher please enter the information ");
        label.setBackground(Color.cyan);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        //adding border for the label of warning
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        label.setBorder(border);
        //the dimension of warning label
        int labelWidth = label.getPreferredSize().width + 10;
        int labelHeight = label.getPreferredSize().height + 15;
        label.setPreferredSize(new Dimension(labelWidth, labelHeight));
        //*****************************

        //***THE LABEL AND FIELDS****
        JLabel nameLabel = new JLabel(" Name : ");
        JTextField nameField = new JTextField();
        //user name
        JLabel userNameLabel = new JLabel(" Username : ");
        JTextField userNameField = new JTextField();
        //password
        JLabel passWordLabel = new JLabel(" Password : ");
        JPasswordField passwordField = new JPasswordField();
        //A panel with grid layout for place the labels and fields of the UserName and password in it.
        JPanel fieldsPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        fieldsPanel.add(nameLabel);
        fieldsPanel.add(nameField);
        fieldsPanel.add(userNameLabel);
        fieldsPanel.add(userNameField);
        fieldsPanel.add(passWordLabel);
        fieldsPanel.add(passwordField);
        //******************************

        //***THE ADD BUTTON***
        JButton add = new JButton("Add Teacher");
        //the dimension of warning label
        int buttonWidth = add.getPreferredSize().width;
        int buttonHeight = add.getPreferredSize().height + 10;
        add.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        //**********************

        //*** THE ACTIONS****
        add.addActionListener((ActionEvent e) -> {
            adminController.addTeacher(userNameField.getText(), passwordField.getText(), nameField.getText());
        });
        //**********************

        actionPanel.add(label, BorderLayout.NORTH);
        actionPanel.add(fieldsPanel, BorderLayout.CENTER);
        actionPanel.add(add, BorderLayout.SOUTH);
        return actionPanel;
    }

    /**
     * If user click on set meal button we should update third panel
     * The buttons,Label and ...(GUI) of this action is designed here
     * we have a table that has 8 rows and 4 columns
     * admin can set the plane and the kind and cost of each meal
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
                {"Saturday", "", "", ""},
                {"Sunday", "", "", ""},
                {"Monday", "", "", ""},
                {"Tuesday", "", "", ""},
                {"Wednesday", "", "", ""},
                {"Thursday", "", "", ""},
                {"Friday", "", "", ""}
        };
        for (int i = 0; i < 7; i++) {
            for (int j = 1; j < 4; j++) {
                data[i][j] = adminController.getPrevDataMeal()[i][j];
            }
        }
        JTable foodTable = new JTable(data, columns);
        foodTable.setRowHeight(50);
        foodTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(foodTable);

        //***THE ADD BUTTON***
        JButton add = new JButton("Save Changes");
        //the dimension of warning label
        int buttonWidth = add.getPreferredSize().width;
        int buttonHeight = add.getPreferredSize().height + 10;
        add.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        //**********************
        //*** THE ACTIONS****
        add.addActionListener(e -> {
            String[][] tableData = new String[7][4];
            for (int i = 0; i < 7; i++) {
                for (int j = 1; j < 4; j++) {
                    if (foodTable.getModel().getValueAt(i, j) != null)
                        tableData[i][j] = (String) foodTable.getModel().getValueAt(i, j);
                    else
                        tableData[i][j] ="";
                }
            }
            adminController.setFoodTable(tableData);
        });
        //**********************
        actionPanel.add(label, BorderLayout.NORTH);
        actionPanel.add(scrollPane, BorderLayout.CENTER);
        actionPanel.add(add, BorderLayout.SOUTH);
        return actionPanel;
    }

    /**
     * If user click on student list button we should update third panel
     * The buttons,Label and ...(GUI) of this action is designed here
     * @return the designed panel
     */
    public JPanel studentListSetter() {
        JPanel actionPanel = new JPanel(new BorderLayout(5, 5));
        //******THE TOP LABEL******
        JLabel label = new JLabel(" *** The list of Students ***");
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
        //******List******
        final File folder = new File("./Data/students");
        JList studentList = new JList(adminController.listFilesOfFolder(folder));
        studentList.setSelectedIndex(adminController.listFilesOfFolder(folder).length);
        JScrollPane scrollPane = new JScrollPane(studentList);
        //**********************
        //*** THE ACTIONS****
        studentList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    adminController.showSelectedStudentProfile((String) studentList.getSelectedValue());
                }
            }
        });
        //**********************
        actionPanel.add(label, BorderLayout.NORTH);
        actionPanel.add(scrollPane, BorderLayout.CENTER);
        return actionPanel;
    }
    /**
     * If user click on teacher list button we should update third panel
     * The buttons,Label and ...(GUI) of this action is designed here
     * @return the designed panel
     */
    public JPanel teacherListSetter() {
        JPanel actionPanel = new JPanel(new BorderLayout(5, 5));
        //******THE TOP LABEL******
        JLabel label = new JLabel(" *** The list of Students ***");
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

        //******List******
        final File folder = new File("./Data/teachers");
        JList teacherList = new JList(adminController.listFilesOfFolder(folder));
        teacherList.setSelectedIndex(adminController.listFilesOfFolder(folder).length);
        JScrollPane scrollPane = new JScrollPane(teacherList);

        //**********************

        //*** THE ACTIONS****
        teacherList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    teacherFrame(adminController.showSelectedTeacherProfile((String) teacherList.getSelectedValue()));
                }
            }
        });
        //**********************

        actionPanel.add(label, BorderLayout.NORTH);
        actionPanel.add(scrollPane, BorderLayout.CENTER);
        return actionPanel;
    }

    /**
     * If admin select a teacher in teachers list we open a new frame
     * In this frame we show the profile information of selected teacher
     * @param teacher The teacher object
     */
    public void teacherFrame(Teacher teacher)
    {
        JFrame newFrame = new JFrame();
        JPanel panel = new JPanel(new BorderLayout());
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newFrame.setSize(500, 300);
        newFrame.setLocation(100, 200);
        ImageIcon icon = new ImageIcon("./images/teacher.png");
        newFrame.setIconImage(icon.getImage());

        JLabel nameLabel = new JLabel(" Name : ");
        JLabel nameLabelData = new JLabel(teacher.getName());
        //user name
        JLabel userNameLabel = new JLabel(" Username : ");
        JLabel userNameLabelData = new JLabel(teacher.getUserName());
        //password
        JLabel passWordLabel = new JLabel(" Password : ");
        JLabel passWordLabelData = new JLabel(teacher.getPassWord());
        //class list
        JLabel classList = new JLabel(" classes: ");
        JComboBox classListData = new JComboBox(teacher.classList());
        //student list
        JLabel studentOfClass = new JLabel(" students of chosen class: ");
        JList students = new JList(teacher.studentsOfClass((String) classListData.getItemAt(classListData.getSelectedIndex())));
        JScrollPane scrollPane = new JScrollPane(students);
        //A panel with grid layout for place the labels and fields of the UserName and password in it.
        JPanel fieldsPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        fieldsPanel.add(nameLabel);
        fieldsPanel.add(nameLabelData);
        fieldsPanel.add(userNameLabel);
        fieldsPanel.add(userNameLabelData);
        fieldsPanel.add(passWordLabel);
        fieldsPanel.add(passWordLabelData);
        fieldsPanel.add(classList);
        fieldsPanel.add(classListData);
        fieldsPanel.add(studentOfClass);
        fieldsPanel.add(scrollPane);
        panel.add(fieldsPanel, BorderLayout.CENTER);
        newFrame.add(panel);
        newFrame.setVisible(true);
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
            adminController.changingPassUser(userNameField.getText(),passwordField.getText(),curentPassField.getText());
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
        //area.setHorizontalAlignment(SwingConstants.CENTER);
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
        JLabel nameData = new JLabel(adminController.adminInfo()[0]);
        //user name
        JLabel userNameLabel = new JLabel(" Username : ");
        userNameLabel.setBackground(Color.MAGENTA);
        JLabel userNameData = new JLabel(adminController.adminInfo()[1]);
        //password
        JLabel passWordLabel = new JLabel(" Password : ");
        passWordLabel.setBackground(Color.MAGENTA);
        JLabel passwordData = new JLabel(adminController.adminInfo()[2]);
        //A panel with grid layout for place the labels and fields of the UserName and password in it.
        JPanel fieldsPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        fieldsPanel.add(name);
        fieldsPanel.add(nameData);
        fieldsPanel.add(userNameLabel);
        fieldsPanel.add(userNameData);
        fieldsPanel.add(passWordLabel);
        fieldsPanel.add(passwordData);
        //******************************

        //***THE ADD BUTTON***
        JButton add = new JButton("save");
        //the dimension of warning label
        int buttonWidth = add.getPreferredSize().width;
        int buttonHeight = add.getPreferredSize().height + 10;
        add.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        //**********************

        //**********************
        actionPanel.add(topLabel, BorderLayout.NORTH);
        actionPanel.add(fieldsPanel, BorderLayout.CENTER);
        GridBagConstraints constraints = new  GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        return actionPanel;
    }
    /**
     * If user click on classList button we should update third panel
     * The buttons,Label and ...(GUI) of this action is designed here
     * @return the designed panel
     */
    public JPanel classListShow()
    {
        JPanel actionPanel = new JPanel(new BorderLayout(5, 5));
        //******THE TOP LABEL******
        JTextArea area = new JTextArea(" Please first choose the teacher \n then you can see the classes of that teacher");
        area.setBackground(Color.cyan);
        area.setOpaque(true);
        //adding border for the label of warning
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        area.setBorder(border);
        //the dimension of warning label
        int labelWidth = area.getPreferredSize().width;
        int labelHeight = area.getPreferredSize().height + 15;
        area.setPreferredSize(new Dimension(labelWidth, labelHeight));
        //*****************************
        //******List******
        final File folder = new File("./Data/teachers");
        JComboBox teachersList = new JComboBox(adminController.listFilesOfFolder(folder));
        //final File folder = new File("./Data/students");
        JList classList = new JList();
        JScrollPane scrollPane = new JScrollPane(classList);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(teachersList,BorderLayout.NORTH);
        panel.add(scrollPane,BorderLayout.CENTER);
        //JScrollPane scrollPane = new JScrollPane(studentList);
        //**********************
        //*** THE ACTIONS****
        teachersList.addActionListener(e -> {
            classList.setListData(adminController.classListOfSpecificTeacher((String) teachersList.getItemAt(teachersList.getSelectedIndex())));
        });

        //**********************
        actionPanel.add(area, BorderLayout.NORTH);
        actionPanel.add(panel, BorderLayout.CENTER);
        return actionPanel;
    }

}
