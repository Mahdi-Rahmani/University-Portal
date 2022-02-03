package com.company;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This is AdminController class.
 * In this class we control the relation between Admin and AdminGUI class
 *
 * @author Mahdi Rahmani
 * @version 1.0
 */
public class AdminController {
    //The model of this controller
    private Admin model;
    //The view of this controller
    private AdminGUI view;

    /**
     * This is the constructor of this class
     * creat new Admin controller with a given model and view
     * @param model admin object
     * @param view AdminGUI object
     * @throws IOException in login page if the user enter invalid username or password this
     * class isn`t created and we throw exception
     */
    public AdminController(Admin model ,AdminGUI view) throws IOException {
        this.model = model;
        this.view = view;
        if (!loginCheck(model.getUserName(), model.getPassWord()))
            throw new IOException("Your username or password is incorrect!");
        viewinit();
    }

    /**
     * set the AdminController of AdminGUI(view)
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
        String fileAddress = "./Data/admins/" + username + ".ser";
        File myAdmin = new File(fileAddress);
        if (myAdmin.exists()) {
            ReadObjectFromFile read = null;
            try {
                read = new ReadObjectFromFile(fileAddress);
                while (true) {
                    Admin admin = (Admin) read.readFromFile();
                    if (admin.getPassWord().equals(password))
                    {
                        model = admin;
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
     * Add a new student to portal with a given information
     * @param studentNumber the student number
     * @param userName the username of new student
     * @param passWord the password of new student
     * @param name the name of the student
     */
    public void addStudent(String studentNumber,String userName , String passWord , String name)
    {
        model.addStudent(studentNumber, userName,passWord, name);
    }

    /**
     * Add a new teacher to portal with a given information
     * @param userName the username of new student
     * @param passWord the password of new student
     * @param name the name of the student
     */
    public void addTeacher(String userName , String passWord , String name)
    {
        model.addTeacher(userName,passWord, name);
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
     * Set the foodTable with the entry data
     * @param foodTable the data of food table
     */
    public void setFoodTable(String[][] foodTable)
    {
        model.setMealPlan(foodTable);
    }

    /**
     * We use the listFilesForFolder of model class to get the
     * name of all files in a specific folder
     * @return the list of files names
     */
    public  String[] listFilesOfFolder(final File folder) {
        return model.listFilesForFolder(folder);
    }

    /**
     * if admin select one student in list we show the profile of student
     * @param studentFileName the file of student info
     */
    public void showSelectedStudentProfile(String studentFileName)
    {
        model.showStudentProfile(studentFileName);
    }

    /**
     * if admin select one teacher in list we show the profile of teacher
     * @param teacherFileName the name of file
     * @return teacher object
     */
    public Teacher showSelectedTeacherProfile(String teacherFileName){
       return model.showTeacherProfile(teacherFileName);
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
     * The information of admin
     * @return String array contains admin`s info
     */
    public String[] adminInfo()
    {
        String[] info = new String[3];
        info[0] = model.getName();
        info[1] = model.getUserName();
        info[2] = model.getPassWord();
        return info;
    }

    public String[] classListOfSpecificTeacher(String teacherFileName)
    {
        return model.classList(teacherFileName);
    }
}
