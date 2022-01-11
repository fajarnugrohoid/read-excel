package com.poi;

import com.poi.models.AtmCard;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadCsv {

    public static void main(String[] args)
    {
        List<AtmCard> atmCards = readAtmCardsFromCSV("ks_atm_card.csv");

        // let's print all the person read from CSV file
        for (AtmCard b : atmCards) {
            System.out.println(b.getCreatedBy());
        }
        System.out.println("atmCards:" + atmCards.size());
    }

    private static List<AtmCard> readAtmCardsFromCSV(String fileName) {
        List<AtmCard> atmCards = new ArrayList<AtmCard>();
        /*Path pathToFile = Paths.get(fileName);

        // create an instance of BufferedReader
        // using try with resource, Java 7 feature to close resources
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {

            // read the first line from the text file
            String line = br.readLine();

            // loop until all lines are read
            while (line != null) {

                // use string.split to load a string array with the values from
                // each line of
                // the file, using a comma as the delimiter
                String[] attributes = line.split(",");

                AtmCard atmCard = addNew(attributes);

                // adding book into ArrayList
                atmCards.add(atmCard);

                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } */

        return atmCards;
    }

    private static AtmCard addNew(String[] metadata) {
        String a = metadata[0];
        String b = metadata[1];
        String c = metadata[2];
        String d = metadata[3];

        // create and return book of this metadata
        return new AtmCard(a, b, c, d);
    }



}
