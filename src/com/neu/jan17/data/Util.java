package com.neu.jan17.data;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

public class Util {


    public static String readFromFile(String filePath) throws Exception{
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        Integer available = fileInputStream.available();
        byte b[] = new byte[available];
        fileInputStream.read(b);
        fileInputStream.close();
        return new String(b);
    }

    public static void writeToFile(String filepath,String contentToWrite, boolean append) throws Exception{

        File file = new File(filepath);
        FileWriter fileWriter = new FileWriter(file, append);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write(contentToWrite);
        bufferedWriter.close();
    }
}
