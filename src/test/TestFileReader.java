package test;

import Utils.FileReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class TestFileReader {
    public static void main(String[] args) throws FileNotFoundException {
        FileReader reader = new FileReader("src/PCS_TEST_DETERMINSTIC.csv");
        String[] headerRow = reader.readLine();


        String[] row = null;
        int arrivalNo = -1;
        double arrivalTime = 0.000;
        int baseStationNo = -1;
        double callDurationTime = 0.0;
        double velocity = 0.0;

        for (int i = 0; i < 10; i++) {
            row = reader.readLine();

            arrivalNo = Integer.parseInt(row[0]);
            arrivalTime = Double.parseDouble(row[1]);
            baseStationNo = Integer.parseInt(row[2]);
            callDurationTime = Double.parseDouble(row[3]);
            velocity = Double.parseDouble(row[4]);

            System.out.println("Arrival Number: " + arrivalNo);
            System.out.println("Arrival Time: "+arrivalTime);
            System.out.println("Base Station: "+baseStationNo);
            System.out.println("Call Duration: "+callDurationTime);
            System.out.println("Velocity: "+velocity);
        }

    }
}

