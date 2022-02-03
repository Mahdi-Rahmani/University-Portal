package com.company;

import java.io.*;

/**
 * This class is created for reading from an object file
 * also has a method for closing file after reading
 *
 * @author Mahdi Rahmani
 * @version 1.0
 */
public class ReadObjectFromFile {
    private ObjectInputStream in;

    /**
     * This is the constructor of this class and we call ObjectInputStream
     *
     * @param fileAddress the address of file
     * @throws FileNotFoundException if the file isn`t find we throw an exception
     * @throws IOException if the file isn`t closed we throw exception
     */
    public ReadObjectFromFile(String fileAddress) throws FileNotFoundException, IOException{
        in = new ObjectInputStream(new FileInputStream(new File(fileAddress)));
    }

    /**
     * This method is using for read from a file
     * @return the object for reading
     * @throws ClassNotFoundException if the file isn`t find we throw an exception
     * @throws IOException if there is a problem in reading from a file it throws an exception
     */
    public Object readFromFile() throws ClassNotFoundException,IOException{
        return in.readObject();
    }

    /**
     * This method is using for closing file
     * @throws IOException if there is a problem in reading from a file it throws an exception
     */
    public void closeConnection() throws IOException{
        in.close();
    }
}