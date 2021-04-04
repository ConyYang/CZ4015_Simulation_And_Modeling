import Simulation.SimulationNormalSchema;
import Simulation.SimulationReservedSchema;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MainAPP
{
    public static void main(String[] args) throws IOException {
//        int totalDroppedCalls = -1;
//        int totalBlockedCalls = -1;
//        Scanner in = new Scanner(System.in);
//
//        System.out.println("Choose Schema: ");
//        System.out.println("1. Normal 2.Reserved: ");
//        int selection = in.nextInt();
//
//        if(selection == 1){
//            SimulationNormalSchema simulationNormalSchema = new SimulationNormalSchema();
//            simulationNormalSchema.startSimulation();
//
//            totalDroppedCalls = simulationNormalSchema.getDroppedCallCount();
//            totalBlockedCalls = simulationNormalSchema.getBlockedCallCount();
//        } else {
//            SimulationReservedSchema simulationReservedSchema = new SimulationReservedSchema();
//            simulationReservedSchema.startSimulation();
//            totalBlockedCalls = simulationReservedSchema.getBlockedCallCount();
//            totalDroppedCalls = simulationReservedSchema.getDroppedCallCount();
//        }

        for (int i = 1; i <=10; i++) {
            String filename = "WarmUpAnalyze/Reserved/"+"SimulationAndModeling"+Integer.toString(i)+".csv";
            SimulationReservedSchema simulationReservedSchema = new SimulationReservedSchema(filename);
            simulationReservedSchema.startSimulation();
        }

//        System.out.println("Total Dropped calls: "+totalDroppedCalls);
//        System.out.println("Total Blocked calls: "+totalBlockedCalls);
    }
}
