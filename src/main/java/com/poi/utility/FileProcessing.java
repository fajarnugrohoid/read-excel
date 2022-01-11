package com.poi.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileProcessing {

    public FileProcessing() {
    }

    public void createFile(String fileName){
        try {
            File myObj = new File( fileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeToFile(List<String> listData , String fileAMachine){

        File myObj = new File(fileAMachine);
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
        createFile(fileAMachine);
        try {
            FileWriter myWriter = new FileWriter(fileAMachine);
            for (int i = 0; i < listData.size() ; i++) {
                myWriter.write( listData.get(i) + "\n" );
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeToFile(String contentData , String fileAMachine){

        File myObj = new File(fileAMachine);
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
        createFile(fileAMachine);
        try {
            FileWriter myWriter = new FileWriter(fileAMachine);

                myWriter.write( contentData + "\n" );

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
