import Simulation.SimulationNormalSchema;
import Simulation.SimulationReservedSchema;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MainAPP
{
    public static void main(String[] args) throws IOException {

        FileWriter csvWriter;
        csvWriter = new FileWriter("ReservedSimulationResult.csv");
        csvWriter.append("DroppedCount");
        csvWriter.append(",");
        csvWriter.append("BlockedCount");
        csvWriter.append("\n");

        int totalDroppedCalls = -1;
        int totalBlockedCalls = -1;

        String datafilename = null;
        for (int i = 1; i < 50; i++) {
            datafilename = "/Users/yangyubei/PycharmProjects/SimulationAndModeling/RandomNumberGenerate/SimulationData/data"+Integer.toString(i)+".csv";
//            SimulationNormalSchema simulationNormalSchema = new SimulationNormalSchema();
//            simulationNormalSchema.startSimulation(datafilename);
//
//            totalDroppedCalls = simulationNormalSchema.getDroppedCallCount();
//            totalBlockedCalls = simulationNormalSchema.getBlockedCallCount();

            SimulationReservedSchema simulationReservedSchema = new SimulationReservedSchema();
            simulationReservedSchema.startSimulation(datafilename);
            totalBlockedCalls = simulationReservedSchema.getBlockedCallCount();
            totalDroppedCalls = simulationReservedSchema.getDroppedCallCount();

            System.out.println("Total Dropped calls: "+totalDroppedCalls);
            System.out.println("Total Blocked calls: "+totalBlockedCalls);

            csvWriter.append(String.join(",", Integer.toString(totalDroppedCalls), Integer.toString(totalBlockedCalls)));
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();

        /**
         * Code for separation test
         */
       // Scanner in = new Scanner(System.in);
//        System.out.println("Choose Schema: ");
//        System.out.println("1. Normal 2.Reserved: ");
//        int selection = in.nextInt();
//        if(selection == 1){
//            SimulationNormalSchema simulationNormalSchema = new SimulationNormalSchema();
//            simulationNormalSchema.startSimulation(datafilename);
//
//            totalDroppedCalls = simulationNormalSchema.getDroppedCallCount();
//            totalBlockedCalls = simulationNormalSchema.getBlockedCallCount();
//        } else {
//            SimulationReservedSchema simulationReservedSchema = new SimulationReservedSchema();
//            simulationReservedSchema.startSimulation(datafilename);
//            totalBlockedCalls = simulationReservedSchema.getBlockedCallCount();
//            totalDroppedCalls = simulationReservedSchema.getDroppedCallCount();
//        }

        /**
         * Code for test warmup period
         */
//        for (int i = 1; i <=10; i++) {
//            String filename = "WarmUpAnalyze/Reserved/"+"SimulationAndModeling"+Integer.toString(i)+".csv";
//            SimulationReservedSchema simulationReservedSchema = new SimulationReservedSchema(filename);
//            simulationReservedSchema.startSimulation();
//        }

    }
}
