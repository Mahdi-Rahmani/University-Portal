package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is the GUI that related to Teacher class
 * This class extends from MainPanel GUI class that has some main panel
 * that is similar between GUI classes
 *
 * @author Mahdi Rahmani
 * @version 1.0
 */
public class TeacherGUI extends MainPanel{

    //The creatClass button
    private JButton createClass;
    //The student list button
    private JButton studentList;
    //The remove Class button
    private JButton removeClass;
    //The teacher controller button
    private TeacherController teacherController;

    /**
     * This is the constructor of this class
     * creat new TeacherGuI and the suitable Panels to the frame
     */
    public TeacherGUI()
    {
        super();
        getFirstPanel().setBackground(Color.white);
        getSecondPanel().setBackground(Color.MAGENTA);
        getThirdPanel().setBackground(Color.GREEN);
        rightPanel();
        topPanel("./images/teacher.png");
    }
    /**
     * set the controller of this class
     * @param teacherController the controller of Teacher and TeacherGUI
     */
    public void setTeacherController(TeacherController teacherController)
    {
        this.teacherController = teacherController;
    }

    /**
     * The right panel is the panel that the buttons are inside it
     * This method overrides the rightPanel method in MainPanel
     */
    @Override
    public void rightPanel()
    {
        createClass = new JButton("create class");
        studentList = new JButton("student list");
        removeClass = new JButton("remove class");
        addButton(createClass);
        addButton(studentList);
        addButton(removeClass);
        super.rightPanel();

        ButtonHandler handler = new ButtonHandler();
        getProfile().addActionListener(handler);
        createClass.addActionListener(handler);
        studentList.addActionListener(handler);
        removeClass.addActionListener(handler);
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
            } else if (e.getSource().equals(createClass)) {
                getThirdPanel().removeAll();
                getThirdPanel().add(Class(),constraints);
                getThirdPanel().revalidate();
                getThirdPanel().repaint();
            } else if (e.getSource().equals(studentList)) {
                getThirdPanel().removeAll();
                getThirdPanel().add(studentListShow(),constraints);
                getThirdPanel().revalidate();
                getThirdPanel().repaint();
            } else if (e.getSource().equals(removeClass)){
                getThirdPanel().removeAll();
                getThirdPanel().add(removeClass(),constraints);
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
        JLabel nameData = new JLabel(teacherController.teacherInfo().get(0)[0]);
        nameData.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 2,Color.black));
        //user name
        JLabel userNameLabel = new JLabel(" Username : ");
        userNameLabel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0,Color.black));
        JLabel userNameData = new JLabel(teacherController.teacherInfo().get(1)[0]);
        userNameData.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 2,Color.black));
        //password
        JLabel passWordLabel = new JLabel(" Password : ");
        passWordLabel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0,Color.black));
        JLabel passwordData = new JLabel(teacherController.teacherInfo().get(2)[0]);
        passwordData.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 2,Color.black));
        //classes
        JPanel classes = new JPanel(new GridLayout(1,4));
        int panW = classes.getPreferredSize().width;
        int panH = classes.getPreferredSize().height + 60;
        classes.setPreferredSize(new Dimension(panW, panH));
        classes.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.black));
        JComboBox classList = new JComboBox(teacherController.teacherInfo().get(3));
        JList studentList = new JList(teacherController.studentOfClass((String) classList.getItemAt(classList.getSelectedIndex())));
        JScrollPane scrollPane = new JScrollPane(studentList);
        JLabel classLabel = new JLabel(" Classes : ");
        JLabel studentLabel = new JLabel("Students: ");
        classes.add(classLabel);
        classes.add(classList);
        classes.add(studentLabel);
        classes.add(scrollPane);
        //A panel with grid layout for place the labels and fields of the UserName and password in it.
        JPanel fieldsPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        fieldsPanel.add(name);
        fieldsPanel.add(nameData);
        fieldsPanel.add(userNameLabel);
        fieldsPanel.add(userNameData);
        fieldsPanel.add(passWordLabel);
        fieldsPanel.add(passwordData);
        int pan2W = fieldsPanel.getPreferredSize().width;
        int pan2H = fieldsPanel.getPreferredSize().height - 40;
        fieldsPanel.setPreferredSize(new Dimension(pan2W, pan2H));
        //**********************
        actionPanel.add(topLabel, BorderLayout.NORTH);
        actionPanel.add(fieldsPanel, BorderLayout.CENTER);
        actionPanel.add(classes,BorderLayout.SOUTH);
        return actionPanel;
    }
    /**
     * If user click on creatClass button we should update third panel
     * The buttons,Label and ...(GUI) of this action is designed here
     * @return the designed panel
     */
    public JPanel Class()
    {
        JPanel actionPanel = new JPanel(new BorderLayout(5, 5));
        actionPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        int panelWidth = actionPanel.getPreferredSize().width + 300;
        int panelHeight = actionPanel.getPreferredSize().height + 300;
        actionPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
        //******THE TOP LABEL******
        JLabel topLabel = new JLabel("** Create a new Class **");
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
        //Name
        JLabel name = new JLabel(" Name:");
        name.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0,Color.black));
        JTextField nameData = new JTextField();
        nameData.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 2,Color.black));
        //capacity
        JLabel capacityLabel = new JLabel(" Capacity : ");
        capacityLabel.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0,Color.black));
        JTextField capacityData = new JTextField();
        capacityData.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0,Color.black));
        //units
        JLabel unitLabel = new JLabel(" Units : ");
        unitLabel.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0,Color.black));
        String[] units = {"1","2","3","4"};
        JComboBox unitData = new JComboBox(units);
        unitData.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0,Color.black));
        //class date
        DefaultTableModel model;
        JPanel classes = new JPanel(new GridLayout(1,2));
        JPanel checkBoxPanel = new JPanel(new BorderLayout());
        JPanel labels = new JPanel(new GridLayout(6,1));
        model = new DefaultTableModel(new Object[]{"8 to 10 ", "10 to 12 ", "14 to 16"}, 0) {
            @Override
            public Class getColumnClass(int columnIndex) {
                return Boolean.class;
            }
        };
        for (int index = 0; index < 5; index++) {
            Boolean boll =new Boolean("false");
            model.addRow(new Object[]{ boll});
        }

        JTable classDate = new JTable(model);
        classDate.setRowHeight(30);
        JScrollPane jScrollPane = new JScrollPane(classDate);
        checkBoxPanel.add(jScrollPane,BorderLayout.CENTER);
        JLabel[] days = new JLabel[6];
        days[5] = new JLabel("Day:");
        days[0] = new JLabel("saturday");
        days[1] = new JLabel("sunday");
        days[2] = new JLabel("monday");
        days[3] = new JLabel("tuesday");
        days[4] = new JLabel("wednesday");
        labels.add(days[5]);
        labels.add(days[0]);
        labels.add(days[1]);
        labels.add(days[2]);
        labels.add(days[3]);
        labels.add(days[4]);
        classes.add(labels);
        classes.add(checkBoxPanel);
        //A panel with grid layout for place the labels and fields of the UserName and password in it.
        JPanel fieldsPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        fieldsPanel.add(name);
        fieldsPanel.add(nameData);
        fieldsPanel.add(capacityLabel);
        fieldsPanel.add(capacityData);
        fieldsPanel.add(unitLabel);
        fieldsPanel.add(unitData);
        int pan2W = fieldsPanel.getPreferredSize().width;
        int pan2H = fieldsPanel.getPreferredSize().height - 40;
        fieldsPanel.setPreferredSize(new Dimension(pan2W, pan2H));
        //******************************
        //a panel for adding fieldPanel and table
        JPanel panel = new JPanel(new GridLayout(2,1));
        panel.add(fieldsPanel);
        panel.add(classes);

        JButton add = new JButton("Save");
        int buttonWidth = add.getPreferredSize().width;
        int buttonHeight = add.getPreferredSize().height + 10;
        add.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        //*****ACTION**********
        HashMap<String, ArrayList<String>> times = new HashMap<>();
        add.addActionListener(e -> {
            //System.out.println(model.getValueAt(0, 0));
            ArrayList<String>[] arrayLists =new ArrayList[5];
            for (int i =0 ; i<5 ; i++)
            {
                arrayLists[i] = new ArrayList<>();
            }
            int flag ;
            for (int i = 0 ; i<5 ; i++)
            {
                flag = 0;
                for (int j = 0; j<3 ; j++)
                {
                    if (model.getValueAt(i, j)!=null) {
                        if (model.getValueAt(i, j).equals(true)) {
                            arrayLists[i].add(model.getColumnName(j));
                            flag = 1;
                        }
                    }
                }
                if (flag == 1)
                    times.put(days[i].getText(),arrayLists[i]);
            }
            teacherController.addClass((String) unitData.getItemAt(unitData.getSelectedIndex()), capacityData.getText(),times , nameData.getText());
        });
        //**********************
        actionPanel.add(topLabel, BorderLayout.NORTH);
        actionPanel.add(panel, BorderLayout.CENTER);
        actionPanel.add(add,BorderLayout.SOUTH);
        int panw = actionPanel.getPreferredSize().width + 150;
        int panh = actionPanel.getPreferredSize().height + 130 ;
        actionPanel.setPreferredSize(new Dimension(panw, panh));
        return actionPanel;
    }

    /**
     * If user click on studentList button we should update third panel
     * The buttons,Label and ...(GUI) of this action is designed here
     * @return the designed panel
     */
    public JPanel studentListShow()
    {
        JPanel actionPanel = new JPanel(new BorderLayout(5, 5));
        //******THE TOP LABEL******
        JTextArea area = new JTextArea(" The list of Students. \n for give a grade please double click on the name of student");
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
        JComboBox classList = new JComboBox(teacherController.teacherInfo().get(3));
        //final File folder = new File("./Data/students");
        JList studentList = new JList();
        JScrollPane scrollPane = new JScrollPane(studentList);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(classList,BorderLayout.NORTH);
        panel.add(scrollPane,BorderLayout.CENTER);
        //JScrollPane scrollPane = new JScrollPane(studentList);
        //**********************
        //*** THE ACTIONS****
        classList.addActionListener(e -> {
            studentList.setListData(teacherController.studentOfClass((String) classList.getItemAt(classList.getSelectedIndex())));
            studentList.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {
                    if (evt.getClickCount() == 2 && !studentList.isSelectionEmpty()) {
                        teacherController.setGrade((String) studentList.getSelectedValue(),(String) classList.getItemAt(classList.getSelectedIndex()));
                    }
                }
            });
        });

        //**********************
        actionPanel.add(area, BorderLayout.NORTH);
        actionPanel.add(panel, BorderLayout.CENTER);
        return actionPanel;
    }

    /**
     * If user click on changingPass & User  button we should update third panel
     * The buttons,Label and ...(GUI) of this action is designed here
     * we get the current password of teacher and if that is valid we change
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
            teacherController.changingPassUser(userNameField.getText(),passwordField.getText(),curentPassField.getText());
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
     * If user click on removeClass button we should update third panel
     * The buttons,Label and ...(GUI) of this action is designed here
     * @return the designed panel
     */
    public JPanel removeClass()
    {
        JPanel actionPanel = new JPanel(new BorderLayout(5, 5));
        //******THE TOP LABEL******
        JTextArea area = new JTextArea(" The list of classes is shown below \n if you want To close any one you can double click on it");
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
        JList classList = new JList(teacherController.teacherInfo().get(3));
        JScrollPane scrollPane = new JScrollPane(classList);

        //JScrollPane scrollPane = new JScrollPane(studentList);
        //**********************
        //*** THE ACTIONS****

        classList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    teacherController.removeClass((String) classList.getSelectedValue());
                }
            }
        });

        //**********************
        actionPanel.add(area, BorderLayout.NORTH);
        actionPanel.add(scrollPane, BorderLayout.CENTER);
        return actionPanel;
    }

}
