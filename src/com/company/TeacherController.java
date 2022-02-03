package com.company;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is TeacherController class.
 * In this class we control the relation between Teacher and TeacherGUI class
 *
 * @author Mahdi Rahmani
 * @version 1.0
 */
public class TeacherController {
    //The model of this controller
    private Teacher model;
    //The view of this controller
    private TeacherGUI view;

    /**
     * This is the constructor of this class
     * creat new Teacher controller with a given model and view
     * @param model Teacher object
     * @param view TeacherGUI object
     * @throws IOException in login page if the user enter invalid username or password this
     * class isn`t created and we throw exception
     */
    public TeacherController(Teacher model ,TeacherGUI view) throws IOException {
        this.model = model;
        this.view = view;
        if (!loginCheck(model.getUserName(), model.getPassWord()))
            throw new IOException("Your username or password is incorrect!");
        viewinit();
    }
    /**
     * set the TeacherController of TeacherGUI(view)
     */
    public void viewinit()
    {
        view.setTeacherController(this);
    }

    /**
     * check the username or passWord of teacher is correct or not
     * @param username the username
     * @param password the password
     * @return boolean value
     */
    public boolean loginCheck(String username , String password)
    {
        String fileAddress = "./Data/teachers/" + username + ".ser";
        File myAdmin = new File(fileAddress);
        if (myAdmin.exists()) {
            ReadObjectFromFile read = null;
            try {
                read = new ReadObjectFromFile(fileAddress);
                while (true) {
                    Teacher teacher = (Teacher) read.readFromFile();
                    System.out.println(teacher.getUserName()+"  "+teacher.getPassWord());
                    if (teacher.getPassWord().equals(password))
                    {
                        model = teacher;
                        read.closeConnection();
                        return true;
                    }
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
        return false;
    }

    /**
     * In profile of teacher we should show the information
     * in this method we get the data from files with help of model class
     * @return An ArrayList contains information
     */
    public ArrayList<String[]> teacherInfo()
    {
        ArrayList<String[]> info = new ArrayList<>();
        String[] name = new String[1];
        name[0] = model.getName();
        String[] user = new String[1];
        user[0] = model.getUserName();
        String[] pass = new String[1];
        pass[0] = model.getPassWord();
        String[] classes;
        int i = 0;
        if (model.getClassRooms().size() != 0) {
            classes = new String[model.getClassRooms().size()];
            for (ClassRoom classRoom : model.getClassRooms())
            {
                classes[i] = classRoom.getName();
                i++;
            }
        }
        else {
            classes = new String[1];
            classes[0] = "";
        }
        info.add(name);
        info.add(user);
        info.add(pass);
        info.add(classes);

        return info;
    }

    /**
     * If we need to get the list of the students of a specific class
     * we should call this method
     * @param name the className
     * @return The list of students as an String Array
     */
    public String[] studentOfClass(String name)
    {
        return model.studentsOfClass(name);
    }

    /**
     * if teacher want to creat new class we should get the info from GUI
     * and we pass it to model and with (createClass method) creat the class
     * @param units units
     * @param capacity capacity
     * @param time time
     * @param name anme
     */
    public void addClass(String units , String capacity , HashMap<String,ArrayList<String>> time , String name)
    {
        model = model.createClass(units, capacity, time, name);
    }
    /**
     * change the password and user name of admin
     * first we get the current password and if correct we continue
     * @param userName new username
     * @param passWord new password
     * @param currentPassword old passWord
     */
    public void changingPassUser(String userName , String passWord , String currentPassword)
    {
        model = model.changePassUser(userName, passWord, currentPassword);
    }

    /**
     * set the student grade
     * @param studentFileName student file name
     * @param className the class name
     */
    public void setGrade(String studentFileName , String className)
    {
        model.setGrade(studentFileName , className);
    }

    /**
     * This method is using for removing a class
     * @param className name of the class
     */
    public void removeClass(String className)
    {
        model = model.removeClass(className);
    }
}
