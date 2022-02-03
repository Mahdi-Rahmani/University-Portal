package com.company;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is the ClassRoom for students
 * teacher can add classes for students
 * The information related to a class is stored here
 *
 * @author  Mahdi Rahmani
 * @version 1.0
 */
public class ClassRoom implements Serializable {
    // the capacity of class
    private int capacity;
    // the units of a class
    private int units;
    // The time and day of the class
    private HashMap<String,ArrayList<String>> time;
    // The class name
    private String name;
    // the student of this class
    private ArrayList<Student> students;

    /**
     * this is the constructor of this class
     * create a new classRoom with a given information
     *
     * @param units the units of class
     * @param capacity the capacity of class
     * @param time the time of class
     * @param name the name of class
     * @throws IOException if teacher dont complete the features of the classRoom
     * we throw an exception and dont creat this class
     */
    public ClassRoom(String units , String capacity , HashMap<String,ArrayList<String>> time , String name) throws IOException {
        if (name.isEmpty() || !isNumeric(capacity) || time.keySet().size() == 0)
            throw new IOException("The data isn`t valid");
        this.units = Integer.parseInt(units);
        this.capacity = Integer.parseInt(capacity);
        this.time = time;
        this.name = name;
        students = new ArrayList<>();
    }

    /**
     * This method check that if the entry String is numeric or not
     * @param str the entry
     * @return boolean value
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
     * get the list of students
     * @return list of students
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * get the name of class
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * get the units of class
     * @return units
     */
    public int getUnits() {
        return units;
    }

    /**
     * get the time of class
     * @return time
     */
    public HashMap<String,ArrayList<String>>  getTime() {
        return time;
    }

    /**
     * add new student to this class
     * @param student student object
     */
    public void addStudent(Student student){
        students.add(student);
    }
}
