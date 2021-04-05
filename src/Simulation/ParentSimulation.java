package Simulation;

import Components.BaseStation;
import Components.Direction;
import Event.InitiationEvent;
import Event.ParentEvent;
import Utils.FileReader;
import Utils.RNGTest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ParentSimulation {
    public final int totalEventCount = 10000;
    public final int warmUpEventCount = 1700;
    protected int eventCount;

    public double clock;
    public ArrayList<BaseStation> baseStationArrayList;

    protected FileWriter csvWriter;

    protected int blockedCallCount;
    protected int droppedCallCount;

    protected PriorityQueue<ParentEvent> parentEventsQueue;
    protected int j = 1;


    public ParentSimulation(){
        this.clock = 0;
        this.blockedCallCount = 0;
        this.droppedCallCount = 0;

        this.parentEventsQueue = new PriorityQueue<>(1, eventSchedule);
        this.baseStationArrayList = new ArrayList<>();
    }

    public void startSimulation(String dataFileName) throws IOException {
        FileReader reader = new FileReader(dataFileName);
        reader.readLine();

        String[] row = null;
        int arrivalNo = -1;
        double arrivalTime = 0.000;
        int baseStationNo = -1;
        double callDurationTime = 0.0;
        double velocity = 0.0;
        Direction direction;
        double position;

        for (int i = 0; i < totalEventCount; i++) {
            row = reader.readLine();
            arrivalNo = (int)Double.parseDouble(row[0]);
            baseStationNo = (int)Double.parseDouble(row[1]);
            arrivalTime = Double.parseDouble(row[2]);
            callDurationTime = Double.parseDouble(row[3]);
            velocity = Double.parseDouble(row[4]);
            if ((int)Double.parseDouble(row[5])==1){
                direction = Direction.TO_BS_1;
            }else{
                direction = Direction.TO_BS_20;
            }
            position = Double.parseDouble(row[6]);

            InitiationEvent initEvent = new InitiationEvent(
                    arrivalNo,
                    baseStationArrayList.get(baseStationNo-1),
                    arrivalTime,
                    direction,
                    velocity,
                    callDurationTime,
                    position
            );
           // System.out.println("Create Event: "+ initEvent);
            parentEventsQueue.add(initEvent);

            // Handel first Event
            handleEvent();
        }
        // While loop
        while (!parentEventsQueue.isEmpty()){
            handleEvent();
        }
        /**
         * Code for WarmUp Period Analyze
         */
//        csvWriter.flush();
//        csvWriter.close();
    }

    public void handleEvent() throws IOException {}

    /**
     * Static Method Comparator for event queue
     */
    public static Comparator<ParentEvent> eventSchedule = new Comparator<ParentEvent>() {
        @Override
        public int compare(ParentEvent o1, ParentEvent o2) {
            if (o1.getEventTime() < o2.getEventTime())
                return -1;
            if (o1.getEventTime() > o2.getEventTime())
                return 1;
            return 0;
        }
    };

    public int getBlockedCallCount() {
        return blockedCallCount;
    }

    public int getDroppedCallCount() {
        return droppedCallCount;
    }

    public double getBlockedRate() {return (double)droppedCallCount / totalEventCount;}

    public double getDroppedRate() {return (double)blockedCallCount / totalEventCount;}
}
