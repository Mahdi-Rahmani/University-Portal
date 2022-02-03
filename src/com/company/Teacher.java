package com.company;
import javax.swing.*;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * This is Teacher class. it holds the information related to teacher
 *
 * @author Mahdi Rahamani
 * @version 1.0
 */
public class Teacher extends User {
    //The name of teacher
    private String name;
    //The classRoom list
    private ArrayList<ClassRoom> classRooms;

    /**
     * This is the constructor of this class
     * Create new Teacher with a given information
     *
     * @param userName the username
     * @param passWord the password
     */
    public Teacher(String userName, String passWord) {
        super(userName, passWord);
        classRooms = new ArrayList<>();
    }

    /**
     * set the name of student
     * @param name the student`s name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the name of student
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * get the list of classes that the teacher has
     * @return classRoom
     */
    public ArrayList<ClassRoom> getClassRooms() {
        return classRooms;
    }

    /**
     * set the classRoom
     * @param classRooms the class
     */
    public void setClassRooms(ClassRoom classRooms) {
        this.classRooms.add(classRooms);
    }

    /**
     * get the class list as a String Array
     * @return class list
     */
    public String[] classList()
    {
        if (classRooms.size() == 0) {
            String[] mySt = {""};
            return mySt;
        }
        String[] mySt = new String[classRooms.size()];
        int i =0;
        for (ClassRoom classRoom : classRooms) {
            mySt[i] = classRoom.getName();
            i++;
        }
      return mySt;
    }

    /**
     * get the lst of students of a specific class as a String array
     * @param className the class name
     * @return the list
     */
    public String[] studentsOfClass(String className)
    {
        String[] mySt = {""};
        if (classRooms.size() == 0) {
            return mySt;
        }
        int i =0;
        for (ClassRoom classRoom : classRooms) {
            if (classRoom.getName().equals(className)) {
                mySt = new String[classRoom.getStudents().size()];
                for (Student student : classRoom.getStudents()) {
                    mySt[i] = student.getUserName();
                    i++;
                }
            }
        }
        return mySt;
    }

    /**
     * in this method we creat a new class for students
     * return the new teacher that contains the class now we create for
     * @param units the units of a class
     * @param capacity the capacity of class
     * @param time the time of class
     * @param name the name of class
     * @return teacher object
     */
    public Teacher createClass(String units , String capacity , HashMap<String,ArrayList<String>> time , String name) {
        boolean success = false;
        String fileAddress1 = "./Data/teachers/" + getUserName() + ".ser";
        Teacher newTeacher = new Teacher(getUserName(), getPassWord());
        //File
        WriteObjectToFile write = null;
        try {
            ClassRoom newClass = new ClassRoom(units, capacity, time, name);
            File teacher = new File(fileAddress1);
            teacher.delete();
            if (getClassRooms().size() != 0) {
                for (ClassRoom classRoom : getClassRooms())
                    newTeacher.setClassRooms(classRoom);
            }
            newTeacher.setClassRooms(newClass);
            newTeacher.setName(getName());
            write = new WriteObjectToFile(fileAddress1);
            write.writeToFile(newTeacher);
            write.closeConnection();
            success = true;
            JOptionPane.showMessageDialog(null, "changes are saved successfully", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ioException) {
            JOptionPane.showMessageDialog(null, ioException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ioException.printStackTrace();
        }
        if (success)
            return newTeacher;
        else
            return this;
    }

    /**
     * if teacher wants to change the username or passWord we call this method
     * @param userName the new username
     * @param passWord the new password
     * @param currentPassword  the current password
     * @return teacher
     */
    public Teacher changePassUser(String userName , String passWord , String currentPassword)
    {
        boolean success = false;
        Teacher newTeacher = new Teacher(userName,passWord);
        if (currentPassword.equals(getPassWord())) {
            try {
                //File
                String fileAddress = "./Data/teachers/" + userName + ".ser";
                File myAdmin = new File(fileAddress);
                if (!myAdmin.exists()) {
                    File oldUser = new File("./Data/teachers/" + getUserName() + ".ser");
                    oldUser.delete();
                    if (getClassRooms().size() != 0) {
                        for (ClassRoom classRoom : getClassRooms())
                            newTeacher.setClassRooms(classRoom);
                    }
                    newTeacher.setName(getName());
                    WriteObjectToFile write = new WriteObjectToFile(fileAddress);
                    write.writeToFile(newTeacher);
                    write.closeConnection();
                    success = true;
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
            JOptionPane.showMessageDialog(null, "Your current  passWord isn`t correct.try again","Result",JOptionPane.ERROR_MESSAGE);
        }
        if (success)
            return newTeacher;
        else
            return this;
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
     * This method set the grade of student
     * @param studentFileName the name of student file
     * @param className the name of class
     */
    public void setGrade(String studentFileName , String className)
    {
        String fileAddress = "./Data/students/" + studentFileName + ".ser";
        ReadObjectFromFile read = null;
        WriteObjectToFile writ = null;
        try {
            read = new ReadObjectFromFile(fileAddress);
            while (true) {
                Student student = (Student) read.readFromFile();
                //read.closeConnection();
                String grade ;
                grade = JOptionPane.showInputDialog("Please enter the grade of student");
                if (!grade.isEmpty()) {
                    if (!isNumeric(grade))
                        JOptionPane.showMessageDialog(null, "The entry grade isn`t valid", "Error", JOptionPane.ERROR_MESSAGE);
                    else {
                        File file = new File(fileAddress);
                        file.delete();
                        int newGrade = Integer.parseInt(grade);
                        student.setNewGrade(student.getClass(className), newGrade);
                        WriteObjectToFile write = new WriteObjectToFile(fileAddress);
                        write.writeToFile(student);
                        write.closeConnection();
                        JOptionPane.showMessageDialog(null, "The grade set successfully", "success", JOptionPane.INFORMATION_MESSAGE);

                    }
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

    /**
     * remove a classroom that we sent to it
     * @param className the class room
     * @return the removed class
     */
    public ClassRoom removeClassRoom(String className) {
        Iterator<ClassRoom> it = classRooms.iterator();
        while (it.hasNext()) {
            ClassRoom classRoom = it.next();
            classRooms.remove(classRoom);
            return classRoom;
        }
        return null;
    }

    /**
     * remove the classroom
     * @param className the name of class
     * @return the new teacher
     */
    public Teacher removeClass(String className) {
        Teacher newTeacher = null;
        ClassRoom removedClass = null;
        String fileAddress1 = "./Data/teachers/" + getUserName() + ".ser";
        ReadObjectFromFile read = null;
        try {
            read = new ReadObjectFromFile(fileAddress1);
            newTeacher = (Teacher) read.readFromFile();

            read.closeConnection();
            removedClass = newTeacher.removeClassRoom(className);

            File myTeacher = new File(fileAddress1);
            myTeacher.delete();
            WriteObjectToFile write = new WriteObjectToFile(fileAddress1);
            write.writeToFile(newTeacher);
            write.closeConnection();
            JOptionPane.showMessageDialog(null, "The class is successfully removed");
        } catch (FileNotFoundException | EOFException | ClassNotFoundException e3) {
            try {
                read.closeConnection();
            } catch (IOException e1) {
                System.out.println("error in closing file!");
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        for (Student student : removedClass.getStudents())
        {
            String fileAddress2 = "./Data/students/" + student.getUserName() + ".ser";
            try {
                Student newStudent = null;

                read = new ReadObjectFromFile(fileAddress2);
                newStudent = (Student) read.readFromFile();

                read.closeConnection();
                newStudent.removeClass(removedClass);

                File myStudent = new File(fileAddress2);
                myStudent.delete();
                WriteObjectToFile write = new WriteObjectToFile(fileAddress2);
                write.writeToFile(newStudent);
                write.closeConnection();
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
        return newTeacher;
    }

    /**
     * set classes
     * @param classRooms the classes
     */
    public void setAllClassRooms(ArrayList<ClassRoom> classRooms) {
        this.classRooms = classRooms;
    }

    /**
     * if we want save changes to file we call this method
     */
    public void saveChanges()
    {
        try {
            Teacher newTeacher= new Teacher(getUserName(),getPassWord());

            newTeacher.setName(getName());
            newTeacher.setAllClassRooms(getClassRooms());
            //File
            String fileAddress = "./Data/teachers/" + getUserName() + ".ser";
            File myStudent = new File(fileAddress);
            myStudent.delete();
            WriteObjectToFile write = new WriteObjectToFile(fileAddress);
            write.writeToFile(newTeacher);
            write.closeConnection();
        } catch (IOException ioException) {
            JOptionPane.showMessageDialog(null, ioException.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
