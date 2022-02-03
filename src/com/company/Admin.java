package com.company;

import javax.swing.*;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This is the admin class
 * in other word this is the model in MVC pattern
 * the operation or the features of admin is saved here
 *
 * @author Mahdi Rahmani
 * @version 1.0
 */
public class Admin extends User {
    //The name of admin
    private String name;

    /**
     * This is the constructor of Admin class
     * Create a new Admin with a given information
     * @param userName the admin username
     * @param passWord the admin passWord
     */
    public Admin(String userName,String passWord) {
        super(userName , passWord);
        name = "Admin";
    }

    /**
     * Get the previous data of food table that is stored in related file
     * @return the data in form 2D String array
     */
    public String[][] getPrevDataFoodTable()
    {
        String[][] data = new String[7][4];
        String fileAddress = "./Data/food/foodTable.ser";
        File myFood = new File(fileAddress);
        if (myFood.exists()) {
            ReadObjectFromFile read = null;
            try {
                read = new ReadObjectFromFile(fileAddress);
                while (true) {
                    FoodData prevData = (FoodData) read.readFromFile();
                    for (int i = 0; i < 7; i++) {
                        for (int j = 1; j < 4; j++) {
                            data[i][j] = prevData.getData()[i][j];
                        }
                    }
                }
            } catch (FileNotFoundException | EOFException | ClassNotFoundException e) {
                try {
                    read.closeConnection();
                } catch (IOException e1) {
                    System.out.println("error in closing file!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            for (int i = 0; i < 7; i++) {
                for (int j = 1; j < 4; j++) {
                    data[i][j] = "";
                }
            }
        }
        return data;
    }

    /**
     * Write the new changes in table to file
     * @param tableData the data that we get from table in GUI
     */
    public void setMealPlan(String[][] tableData ){
        FoodData foodData = new FoodData();
        String fileAddress = "./Data/food/foodTable.ser";
        foodData.setData(tableData);
        //File
        WriteObjectToFile write = null;
        try {
            write = new WriteObjectToFile(fileAddress);
            write.writeToFile(foodData);
            write.closeConnection();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "changes are saved successfully", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Create new teacher object and ad it to file
     * This file is stored in teachers folder
     * @param userName the username of teacher
     * @param passWord the password of teacher
     * @param name the name of teacher
     */
    public void addTeacher(String userName , String passWord , String name)
    {
        try {
            Teacher teacher = new Teacher(userName, passWord);
            teacher.setName(name);
            //File
            String fileAddress = "./Data/teachers/" + userName + ".ser";
            File myTeacher = new File(fileAddress);
            if (!myTeacher.exists()) {
                WriteObjectToFile write = new WriteObjectToFile(fileAddress);
                write.writeToFile(teacher);
                write.closeConnection();
                JOptionPane.showMessageDialog(null, userName+"added successfully","Congratulations",JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null, "This user is registered later","Warning",JOptionPane.WARNING_MESSAGE);
            }
        } catch (IOException ioException) {
            JOptionPane.showMessageDialog(null, ioException.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * add a new student to portal
     * @param studentNumber the student number
     * @param userName the username of student
     * @param passWord the passWord of student
     * @param name the name of student
     */
    public void addStudent(String studentNumber,String userName , String passWord , String name)
    {
        if (isNumeric(studentNumber)) {
            int stNumber = Integer.parseInt(studentNumber);
            try {
                Student student = new Student(userName, passWord);
                student.setStudentNumber(stNumber);
                student.setName(name);
                //File
                String fileAddress = "./Data/students/" + userName+ ".ser";
                File myStudent = new File(fileAddress);
                if (!myStudent.exists()) {
                    WriteObjectToFile write = new WriteObjectToFile(fileAddress);
                    write.writeToFile(student);
                    write.closeConnection();
                    JOptionPane.showMessageDialog(null, userName+"added successfully","Congratulations",JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, "This user is registered later","Warning",JOptionPane.WARNING_MESSAGE);
                }

            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null, ioException.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "the student number must be numeric","Info",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * This method check if the String is numeric or not
     * @param str the entry String
     * @return a boolean value
     */
    public  boolean isNumeric(String str) {
        boolean flag = true;
        char[] stdDigits = str.toCharArray();
        for (char ch : stdDigits)
        {
            if (ch > 57 || ch < 48 )
                flag = false;
        }
        return flag;
    }

    /**
     * This method is return the list of name of files ina specific folder
     * @param folder file of folder
     * @return list of files
     */
    public  String[] listFilesForFolder(final File folder) {
        ArrayList<String> list = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                list.add(fileEntry.getName().split(".ser")[0]);
            }
        }
        String[] finalList = new String[list.size()];
        for (int i = 0; i<list.size();i++)
            finalList[i] = list.get(i);
        return finalList;
    }

    /**
     * show the profile of selected student in form of JOptinPane
     * @param studentFileName The name of student file
     */
    public void showStudentProfile(String studentFileName)
    {
        String fileAddress = "./Data/students/" + studentFileName + ".ser";
        ReadObjectFromFile read = null;
        try {
            read = new ReadObjectFromFile(fileAddress);
            while (true) {
                Student student = (Student) read.readFromFile();
                JOptionPane.showMessageDialog(null, "username: " + student.getUserName() + "\n" +
                        "PassWord: " + student.getPassWord() + "\n" +
                        "Average Grade: " + student.getAverageGrade() + "\n" +
                        "Balance account: " + student.getAccountBalance() + "\n" +
                        "classes:" + classListToString(student));
            }
        } catch (FileNotFoundException | EOFException | ClassNotFoundException e3) {
            try {
                read.closeConnection();
            } catch (IOException e1) {
                System.out.println("error in closing file!");
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    /**
     * the list of classes of the student
     * @param student student object
     * @return classList
     */
    public String classListToString(Student student)
    {
        String classList = "";
        for (String stClass : student.getClasses())
        {
            classList += " " + stClass +"\n";
        }
        return classList;
    }

    /**
     * show the the profile of selected teacher
     * @param teacherFileName name of the teacher file
     * @return the teacher object
     */
    public Teacher showTeacherProfile(String teacherFileName)
    {

        String fileAddress = "./Data/teachers/" + teacherFileName + ".ser";
        ReadObjectFromFile read = null;
        try {
            read = new ReadObjectFromFile(fileAddress);
            while (true) {
                Teacher teacher = (Teacher) read.readFromFile();
                read.closeConnection();
                return teacher;
            }
        } catch (FileNotFoundException | EOFException | ClassNotFoundException e3) {
            try {
                read.closeConnection();
            } catch (IOException e1) {
                System.out.println("error in closing file!");
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        return null;
    }

    /**
     * change the password and user name of admin
     * @param userName admin`s username
     * @param passWord admin`s passWord
     * @param currentPassword admin`s current password
     * @return admin object
     */
    public Admin changePassUser(String userName , String passWord , String currentPassword)
    {
        boolean success = false;
        Admin newAdmin = new Admin(userName,passWord);
        if (currentPassword.equals(getPassWord())) {
            try {
                //File
                String fileAddress = "./Data/admins/" + userName + ".ser";
                File myAdmin = new File(fileAddress);
                if (!myAdmin.exists()) {
                    File oldUser = new File("./Data/admins/" + getUserName() + ".ser");
                    oldUser.delete();
                    WriteObjectToFile write = new WriteObjectToFile(fileAddress);
                    write.writeToFile(newAdmin);
                    write.closeConnection();
                    JOptionPane.showMessageDialog(null, "The changes are successfully done","Congratulations",JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, "This user is registered later","Warning",JOptionPane.WARNING_MESSAGE);
                }
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null, ioException.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
        }else
        {
            JOptionPane.showMessageDialog(null, "Your current passWord isn`t correct.try again","Result",JOptionPane.ERROR_MESSAGE);
        }
        if (success)
            return newAdmin;
        else
            return this;
    }

    /**
     * get the name of admin
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * get the list of the teacher classes
     * @param teacherFileName name of teacher file
     * @return String Array
     */
    public String[] classList(String teacherFileName)
    {
        String[] classes = null;
        String fileAddress = "./Data/teachers/" + teacherFileName + ".ser";
        ReadObjectFromFile read = null;

        try {
            read = new ReadObjectFromFile(fileAddress);
            while (true) {
                Teacher teacher = (Teacher) read.readFromFile();
                //read.closeConnection();
                int i =0;
                if (teacher.getClassRooms().size() != 0) {
                    classes = new String[teacher.getClassRooms().size()];
                    for (ClassRoom classRoom : teacher.getClassRooms()) {
                        classes[i] = classRoom.getName();
                        i++;
                    }
                }
            }
        } catch (FileNotFoundException | EOFException | ClassNotFoundException  e3) {
            try {
                read.closeConnection();
            } catch (IOException e1) {
                System.out.println("error in closing file!");
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        if (classes == null)
            classes = new String[]{"empty"};

        return classes;
    }
}
