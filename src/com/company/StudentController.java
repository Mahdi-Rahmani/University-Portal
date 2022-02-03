package com.company;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This is StudentController class.
 * In this class we control the relation between Admin and AdminGUI class
 *
 * @author Mahdi Rahmani
 * @version 1.0
 */
public class StudentController {

    //The model of this controller
    private Student model;
    //The view of this controller
    private StudentGUI view;
    /**
     * This is the constructor of this class
     * creat new Student controller with a given model and view
     * @param model Student object
     * @param view StudentGUI object
     * @throws IOException in login page if the user enter invalid username or password this
     * class isn`t created and we throw exception
     */
    public StudentController(Student model ,StudentGUI view) throws IOException {
        this.model = model;
        this.view = view;
        if (!loginCheck(model.getUserName(), model.getPassWord()))
            throw new IOException("Your username or password is incorrect!");
        viewinit();
    }
    /**
     * set the StudentController of StudentGUI(view)
     */
    public void viewinit()
    {
        view.setAdminController(this);
    }

    /**
     * check the username or passWord of admin is correct or not
     * @param username the username
     * @param password the password
     * @return boolean value
     */
    public boolean loginCheck(String username , String password)
    {
        String fileAddress = "./Data/students/" + username + ".ser";
        File myStudent = new File(fileAddress);
        if (myStudent.exists()) {
            ReadObjectFromFile read = null;
            try {
                read = new ReadObjectFromFile(fileAddress);
                while (true) {
                    Student student = (Student) read.readFromFile();
                    if (student.getPassWord().equals(password))
                    {
                        model = student;
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
     * change the password and user name of student
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
     * In profile of student we should show the information
     * in this method we get the data from files with help of model class
     * @return An ArrayList contains information
     */
    public ArrayList<String[]> studentInfo()
    {
        ArrayList<String[]> info = new ArrayList<>();
        String[] name = new String[1];
        name[0] = model.getName();
        String[] user = new String[1];
        user[0] = model.getUserName();
        String[] pass = new String[1];
        pass[0] = model.getPassWord();
        String[] grade = new String[1];
        grade[0] = "" + model.getAverageGrade();
        String[] balance = new String[1];
        balance[0] = "" + model.getAccountBalance();
        String[] classes;
        int i = 0;
        if (model.getClasses().size() != 0) {
            classes = new String[model.getClasses().size()];
            for (String st : model.getClasses())
            {
                classes[i] = st;
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
        info.add(grade);
        info.add(balance);
        info.add(classes);

        return info;
    }

    /**
     * if user wants to increase the balance account we increase the account with this method
     * @param value the entered value
     * @throws Exception if the entry isn`t valid throw exception
     */
    public void increaseMoney(String value) throws Exception {
        model.accountBalanceIncrease(value);
        updateModel(model.getUserName());
    }

    /**
     * for updating model after changes we call this method
     * @param username the username of model
     */
    public void updateModel(String username){
        String fileAddress = "./Data/students/" + username + ".ser";
        File myStudent = new File(fileAddress);
        ReadObjectFromFile read = null;
        try {
            read = new ReadObjectFromFile(fileAddress);
            while (true) {
                Student student = (Student) read.readFromFile();
                model = student;
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
     * Get the previous data that is saved in file
     * @return the previous data of table
     */
    public String[][] getPrevDataMeal()
    {
        return model.getPrevDataFoodTable();
    }

    /**
     * set the stat of reservation of food
     * @param reservation the reservation as an entry
     * @throws Exception if the cost of food is more than balance account we throw exception
     */
    public void setReservation(Boolean[] reservation) throws Exception {
        model.setReservation(reservation);
        updateModel(model.getUserName());
    }

    /**
     * get the data of classes
     * @return data
     */
    public String[][] getData()
    {
        return model.classData();
    }

    /**
     * add a new class for student
     * @param number the number in table
     * @param data data of table
     * @throws Exception if the user don`t pay attention to the rules of registering we throw exception
     */
    public void registerClass(String number,String[][] data) throws Exception {
        model.addClass(number, data);
        updateModel(model.getUserName());
    }

    /**
     * get the previous reservation result
     * @return boolean array
     */
    public Boolean[] getReservation()
    {
        return model.getIsReserve();
    }
}
