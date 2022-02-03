package com.company;

import java.io.Serializable;

/**
 * This is a class for holding the data of food Table
 * it is Serializable and we can save it as a file
 *
 * @author Mahdi Rahmani
 * @version 1.0
 */
public class FoodData implements Serializable {
    // the data of table
    private String[][] data;

    /**
     * This is the constructor of this class
     * creat new FoodData
     */
    public FoodData()
    {
        data = new String[7][4];
    }

    /**
     * get the data
     * @return data table
     */
    public String[][] getData() {
        return data;
    }

    /**
     * set the data
     * @param data data
     */
    public void setData(String[][] data) {
        this.data = data;
    }
}
