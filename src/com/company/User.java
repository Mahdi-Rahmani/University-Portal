package com.company;

import java.io.Serializable;

/**
 * This is user class
 * In this class we holds the information that similar between different type of users
 *
 * @author Mahdi Rahmani
 * @version 1.0
 */
public class User implements Serializable {
    //The user name of user
    private String userName;
    //The passWord of user
    private String passWord;

    /**
     * This is the constructor of this class
     * We creat new user with a given information
     * @param userName the username
     * @param passWord the password
     */
    public User(String userName, String passWord)  {
        this.userName = userName;
        this.passWord = passWord;

    }

    /**
     * set the username of user
     * @param userName the username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * get the username of user
     * @return username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * get the password of user
     * @return password
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * set the password of user
     * @param passWord the userPassword
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
