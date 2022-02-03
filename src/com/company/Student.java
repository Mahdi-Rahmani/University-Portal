package com.company;

import javax.swing.*;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * this is student class. it contains the the features and fields related to Student
 *
 * @author Mahdi Rahmani
 * @version 1.0
 */
public class Student extends User {
    // the list of classes
    private ArrayList<String> classes;
    // the account balance
    private int accountBalance;
    // the average grade of student
    private float averageGrade;
    // the name of student
    private String name;
    // the student number
    private int studentNumber;
    // the the map of class and grades
    private HashMap<ClassRoom,Integer> class_grade;
    // The numbers of units
    private int units;
    // The reserve State list
    private Boolean[] isReserve;
    // The data row numbers
    transient private int size;
    /**
     * creat new class with a given information
     * @param userName the student username
     * @param password the student passWord
     * @throws IOException if the length of username or passWord less than 4 we throw exception
     */
    public Student(String userName , String password) throws IOException {
        super(userName , password);
        accountBalance = 0;
        classes = new ArrayList<>();
        if (userName.length() == 0 || password.length() == 0)
        {
            throw new IOException("PassWord and UserName must be completed");
        }
        else if (userName.length() < 4 || password.length() < 4)
            throw new IOException("The password or username cant be less than 4 characters");
        class_grade = new HashMap<>();
        setAverageGrade();
        isReserve = new Boolean[7];
        initialReserve();
    }

    /**
     * initial the boolean Array
     */
    public void initialReserve()
    {
        for (int i = 0; i<7 ; i++)
        {
            isReserve[i] = Boolean.FALSE;
        }
    }
    /**
     * We give the classRoom to this method and it removes it
     * @param classRoom the class
     */
    public void removeClass(ClassRoom classRoom)
    {
        class_grade.remove(classRoom);
        setAverageGrade();
        classes.remove(classRoom.getName());
    }
    /**
     * get the number of units
     * @return units
     */
    public int getUnits() {
        return units;
    }

    /**
     * this method set the number of units that student has
     */
    public void setUnits() {
        int myUnits = 0;
        if (!class_grade.isEmpty())
        {
            for (ClassRoom classRoom : class_grade.keySet())
                myUnits += classRoom.getUnits();
        }
        this.units = myUnits;
    }

    /**
     * This method calculate the average grade of student
     * finally set the average grade
     */
    public void setAverageGrade()
    {
        setUnits();
        int gradeSum = 0;
        if (units == 0)
            averageGrade = 0;
        else {

            for (ClassRoom classRoom : class_grade.keySet()) {
                gradeSum += (class_grade.get(classRoom)*classRoom.getUnits());
            }
            averageGrade = gradeSum / units;
        }
    }

    /**
     *increasing the account balance due to the entry
     * @param money the money that we want to add
     * @throws Exception if the entered value isn`t valid throw exception
     */
    public void accountBalanceIncrease(String money) throws Exception {

        if (isNumeric(money)) {
            accountBalance += Integer.parseInt(money);
            saveChanges();
            throw new Exception(" Your account balance is increased");
        }
        else
            throw new Exception("The entered value isn`t valid");
    }

    /**
     * set the name of student
     * @param name student`s name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * set student number
     * @param studentNumber the student number
     */
    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    /**
     * get the accountBalance
     * @return accountBalance
     */
    public int getAccountBalance() {
        return accountBalance;
    }

    /**
     * get the list of classes
     * @return the list of classes
     */
    public ArrayList<String> getClasses() {
        return classes;
    }

    /**
     * get the average of student
     * @return student average
     */
    public float getAverageGrade() {
        return averageGrade;
    }

    /**
     * get students name
     * @return the student`s name
     */
    public String getName() {
        return name;
    }

    /**
     * get the student number
     * @return the student number
     */
    public int getStudentNumber() {
        return studentNumber;
    }

    /**
     * change the password and user name of Student
     * @param userName student`s username
     * @param passWord student`s passWord
     * @param currentPassword student`s current password
     * @return admin object
     */
    public Student changePassUser(String userName , String passWord , String currentPassword)
    {
        boolean success = false;
        Student newStudent = null;
        if (currentPassword.equals(getPassWord())) {
            try {
                newStudent = new Student(userName,passWord);
                //File
                String fileAddress = "./Data/students/" + userName + ".ser";
                File myAdmin = new File(fileAddress);
                if (!myAdmin.exists()) {
                    File oldUser = new File("./Data/students/" + getUserName() + ".ser");
                    oldUser.delete();
                    WriteObjectToFile write = new WriteObjectToFile(fileAddress);
                    write.writeToFile(newStudent);
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
            return newStudent;
        else
            return this;
    }

    /**
     * get the map of class with student grade
     * @return class_grade
     */
    public HashMap<ClassRoom,Integer> getClass_grade() {
        return class_grade;
    }

    /**
     * set class_grade
     * and also set the list of classes as an array list
     * @param classRoom the class object
     * @param grade the grade in that lesson
     */
    public void setClass_grade(ClassRoom classRoom , int grade) {
        this.class_grade.put(classRoom, grade);
        classes.add(classRoom.getName());
    }

    /**
     * get classRoom
     * @param className the name of class
     * @return classRoom
     */
    public ClassRoom getClass(String className)
    {
        for (ClassRoom classRoom: class_grade.keySet()) {
            if (classRoom.getName().equals(className))
                return classRoom;
        }
        return null;
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
     * if we want save changes to file we call this method
     */
    public void saveChanges()
    {
        try {
            Student newStudent = new Student(getUserName(),getPassWord());
            newStudent.studentNumber = getStudentNumber();
            newStudent.setAverageGrade(getAverageGrade());
            newStudent.setUnits(getUnits());
            newStudent.setName(getName());
            newStudent.setAccountBalance(getAccountBalance());
            newStudent.setClass_grade(getClass_grade());
            newStudent.setClasses(getClasses());
            newStudent.setIsReserve(getIsReserve());
            //File
            String fileAddress = "./Data/students/" + getUserName() + ".ser";
            File myStudent = new File(fileAddress);
            myStudent.delete();
            WriteObjectToFile write = new WriteObjectToFile(fileAddress);
            write.writeToFile(newStudent);
            write.closeConnection();
        } catch (IOException ioException) {
            JOptionPane.showMessageDialog(null, ioException.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Set accountBalance
     * @param accountBalance the accountBalance
     */
    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    /**
     * set The average grade
     * @param averageGrade the average grade
     */
    public void setAverageGrade(float averageGrade) {
        this.averageGrade = averageGrade;
    }

    /**
     * set the classes as a String array
     * @param classes the classes
     */
    public void setClasses(ArrayList<String> classes) {
        this.classes = classes;
    }

    /**
     * set the Class_grade map
     * @param class_grade the class_grade field
     */
    public void setClass_grade(HashMap<ClassRoom, Integer> class_grade) {
        this.class_grade = class_grade;
    }

    /**
     * if teacher wants to change the grade and set it can from this
     * @param classRoom the classroom
     * @param newGrade the grade
     */
    public void setNewGrade(ClassRoom classRoom, int newGrade)
    {
        class_grade.replace(classRoom, newGrade);
        setAverageGrade();
    }
    /**
     * set the number of units
     * @param units units
     */
    public void setUnits(int units) {
        this.units = units;
    }

    /**
     * get the reserve state of foods
     * @return the states
     */
    public Boolean[] getIsReserve() {
        return isReserve;
    }

    /**
     * set the reserve state of food
     * @param isReserve the reserve array
     */
    public void setIsReserve(Boolean[] isReserve) {
        this.isReserve = isReserve;
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
     * set the reservation state
     * @param reservation the reservation actions in GUI
     * @throws Exception if the cost of food more than account balance we throw exception
     */
    public void setReservation(Boolean[] reservation) throws Exception {
        int cost = 0;
        for (int i =0 ; i<7 ; i++)
        {
            if (reservation[i] == true && isReserve[i]==false)
                cost += Integer.parseInt(getPrevDataFoodTable()[i][3]);
            else if (reservation[i] == false && isReserve[i]==true)
                cost  -= Integer.parseInt(getPrevDataFoodTable()[i][3]);

        }
        if (cost > accountBalance)
            throw new Exception("The cost is mor than your balance account");
        else {
            setIsReserve(reservation);
            setAccountBalance(getAccountBalance() - cost);
            saveChanges();
            throw new Exception("The reservation is successfully done");
        }
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
     * This method get the data of classes
     * @return Data
     */
    public String[][] classData(){
        final File folder = new File("./Data/teachers");
        String[] teachersUser = listFilesForFolder(folder);
        ArrayList<String[]> datas = new ArrayList<>();
        int index = 1;
        for (int i = 0; i<teachersUser.length; i++)
        {
            String fileAddress = "./Data/teachers/" + teachersUser[i] + ".ser";
            ReadObjectFromFile read = null;
            try {
                read = new ReadObjectFromFile(fileAddress);
                while (true) {
                    Teacher teacher = (Teacher) read.readFromFile();
                    if (teacher.getClassRooms().size()!=0)
                    {
                        for (ClassRoom classRoom : teacher.getClassRooms()) {
                            String[] data = new String[6];
                            data[0] = "";
                            data[1] = "";
                            for (String day : classRoom.getTime().keySet()) {
                                data[0] += day;
                                data[0] += "\n";
                                for (String time:classRoom.getTime().get(day)){
                                    data[1] += time;
                                    data[1] += ",";
                                }
                            }
                            data[2] = "" + classRoom.getUnits();
                            data[3] = classRoom.getName();
                            data[4] = teacher.getUserName();
                            data[5] = ""+index;
                            index ++;
                            datas.add(data);
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
        String[][] dataTable = new String[datas.size()][6];
        for (int i = 0; i<datas.size();i++)
        {
            for (int j =0; j<6;j++)
            {
                dataTable[i][j] = datas.get(i)[j];
            }
        }
        size = datas.size();
        return dataTable;
    }

    /**
     * add a new class for student
     * @param number the number in table
     * @param data data of table
     * @throws Exception if the user don`t pay attention to the rules of registering we throw exception
     */
    public void addClass(String number,String[][] data) throws Exception {
        if (!isNumeric(number))
            throw new Exception("enter valid index");

        int index = Integer.parseInt(number) - 1;
        if (index > size)
            throw new Exception("enter valid index");

        String fileAddress = "./Data/teachers/" + data[index][4] + ".ser";
        Teacher teacher = null;
        ReadObjectFromFile read = null;
        System.out.println(fileAddress);
        try {
            read = new ReadObjectFromFile(fileAddress);
            while (true) {
                teacher = (Teacher) read.readFromFile();
                ClassRoom newClass = null;
                for (ClassRoom classRoom : teacher.getClassRooms())
                    if (classRoom.getName().equals(data[index][3]))
                        newClass = classRoom;
                if (getClasses().contains(newClass.getName()))
                    throw new Exception("you have this course from later.");
                if ((newClass.getUnits()+units >17 && getAverageGrade()<17)||(newClass.getUnits()+units >24 && getAverageGrade()>17))
                    throw new Exception("please attention to Ceil of units ");
                if (newClass.getStudents().size() >= newClass.getUnits())
                    throw new Exception("The class is complete and you cant choose it");
                setClass_grade(newClass, 0);
                newClass.addStudent(this);
                teacher.saveChanges();
                saveChanges();
                throw new Exception("register successfully");
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
        if(teacher != null) {
            try {

                File myStudent = new File(fileAddress);
                myStudent.delete();
                WriteObjectToFile write = new WriteObjectToFile(fileAddress);
                write.writeToFile(teacher);
                write.closeConnection();
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null, ioException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}
