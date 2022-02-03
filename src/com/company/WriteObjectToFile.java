package com.company;
import java.io.*;
/**
 * This class is created for writing from an object file
 * also has a method for closing file after writing
 *
 * @author Mahdi Rahmani
 * @version 1.0
 */
public class WriteObjectToFile {
    private ObjectOutputStream out;

    /**
     * This is the constructor of this class and we call ObjectInputStream
     *
     * @param fileAddress the address of file
     * @throws FileNotFoundException if the file isn`t find we throw an exception
     * @throws IOException if the file isn`t closed we throw exception
     */
    public WriteObjectToFile(String fileAddress) throws FileNotFoundException, IOException {
        out = new ObjectOutputStream(new FileOutputStream(new File(fileAddress)));
    }
    /**
     * This method is using for write from to a file
     * @return the object for writing on it
     * @throws ClassNotFoundException if the file isn`t find we throw an exception
     * @throws IOException if there is a problem in writing to a file it throws an exception
     */
    public void writeToFile(Object o) throws IOException{
        out.writeObject(o);
    }
    /**
     * This method is using for closing file
     * @throws IOException if there is a problem in writing to a file it throws an exception
     */
    public void closeConnection() throws IOException{
        out.close();
    }
}