package Simulation;

import Components.BaseStation;
import Components.Direction;
import Event.InitiationEvent;
import Event.ParentEvent;
import Utils.FileReader;
import Utils.RandomNumberGenerator;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ParentSimulation {
    public final int totalEventCount = 10000;
    public double clock;
    public ArrayList<BaseStation> baseStationArrayList;
    protected int blockedCallCount;
    protected int droppedCallCount;

    protected PriorityQueue<ParentEvent> parentEventsQueue;

    public ParentSimulation(){
        this.clock = 0;
        this.blockedCallCount = 0;
        this.droppedCallCount = 0;

        this.parentEventsQueue = new PriorityQueue<>(1, eventSchedule);
        this.baseStationArrayList = new ArrayList<>();
    }

    public void startSimulation() throws FileNotFoundException {
        FileReader reader = new FileReader("Data/PCS_TEST_DETERMINSTIC.csv");
        reader.readLine();

        String[] row = null;
        int arrivalNo = -1;
        double arrivalTime = 0.000;
        int baseStationNo = -1;
        double callDurationTime = 0.0;
        double velocity = 0.0;

        for (int i = 0; i < totalEventCount; i++) {
            row = reader.readLine();
            arrivalNo = Integer.parseInt(row[0]);
            arrivalTime = Double.parseDouble(row[1]);
            baseStationNo = Integer.parseInt(row[2]);
            callDurationTime = Double.parseDouble(row[3]);
            velocity = Double.parseDouble(row[4]);
            Direction direction = RandomNumberGenerator.generateDirection();
            double position = RandomNumberGenerator.generatePosition();

            InitiationEvent initEvent = new InitiationEvent(
                    arrivalNo,
                    baseStationArrayList.get(baseStationNo-1),
                    arrivalTime,
                    direction,
                    velocity,
                    callDurationTime,
                    position
            );
            System.out.println("Create Event: "+ initEvent);
            parentEventsQueue.add(initEvent);

            // Handel first Event
            handleEvent();
        }
        // While loop
        while (!parentEventsQueue.isEmpty()){
            handleEvent();
        }
    }

    public void handleEvent(){}

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
}
