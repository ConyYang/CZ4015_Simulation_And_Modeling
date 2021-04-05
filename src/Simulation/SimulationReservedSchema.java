package Simulation;

import Components.BaseStation;
import Components.Direction;
import Event.HandoverEvent;
import Event.InitiationEvent;
import Event.ParentEvent;
import Event.TerminationEvent;

import java.io.FileWriter;
import java.io.IOException;

public class SimulationReservedSchema extends ParentSimulation{
    /**
     * Constructor
     */
    public SimulationReservedSchema() throws IOException {
        super();
        /**
         * Code for warmup analyze
         */
//        csvWriter = new FileWriter(fileName);
//        csvWriter.append("DroppedCount");
//        csvWriter.append(",");
//        csvWriter.append("BlockedCount");
//        csvWriter.append("\n");

        for (int i = 0; i < 20; i++) {
            BaseStation bs = new BaseStation(i+1,9);
            this.baseStationArrayList.add(bs);
        }
    }

    @Override
    public void handleEvent() throws IOException {
        ParentEvent currEvent= parentEventsQueue.peek();
        parentEventsQueue.remove(currEvent);

        assert currEvent != null;
        this.clock = currEvent.getEventTime();

        BaseStation currentBS = currEvent.getBaseStation();

        if (currEvent instanceof InitiationEvent) {
            // System.out.println("~~~~~~~~~~~~~Initiation Event~~~~~~~~~~~~~~");
            if (currentBS.getNumOfFreeChannel() > 0) {
                currentBS.useNormalChannel();
             //   System.out.println("Current BS " + currentBS.getId() + " use one channel with " + currentBS.getNumOfFreeChannel());
                ParentEvent nextEvent = GenerateNextEvent.generateNextEvent(currEvent, clock);
                parentEventsQueue.add(nextEvent);
            } else {
             //   System.err.println(("Current BS " + currentBS.getId() + "Has no channel: " + currentBS.getNumOfFreeChannel()));
                blockedCallCount += 1;
            }
            /**
             * Code for warm up period analyze
             */
//            csvWriter.append(String.join(",", Integer.toString(droppedCallCount), Integer.toString( blockedCallCount)));
//            csvWriter.append("\n");
//            j+=1;
            eventCount+=1;
            if (eventCount == warmUpEventCount){
                blockedCallCount = 0;
                droppedCallCount = 0;
            }
        }
        else if (currEvent instanceof HandoverEvent){
          //  System.out.println("~~~~~~~~~~~~~Handover Event~~~~~~~~~~~~~~");
            // Cal next base station
            BaseStation nextBS;
            int currBSID = currentBS.getId();
         //   System.out.println("Current BS: "+currBSID+" want to be hand over "+" to direction: "+currEvent.getDirection());
            if (currEvent.getDirection().equals(Direction.TO_BS_1)){
                // Check direction
                nextBS = baseStationArrayList.get(currBSID-1-1); // 1st -1 for indexing; 2nd -1 for direction
            } else {
                // Check direction
                nextBS = baseStationArrayList.get(currBSID); // 1st -1 for indexing; 2nd +1 for direction
            }

            // current station release one channel
            if (currentBS.getNumOfReservedChannel()==0){
                currentBS.releaseReservedChannel();
            }else{
                currentBS.releaseNormalChannel();
            }
           // System.out.println("Current BS: "+currBSID+" release one channel and has "+ currentBS.getNumOfFreeChannel() +"free channel.");
            currEvent.setBaseStation(nextBS);


            // check available channels
            if (nextBS.getNumOfFreeChannel()>0){
           //     System.out.println("BaseStation "+nextBS.getId()+" has free channel to be hand over");
                nextBS.useNormalChannel();
                ParentEvent nextEvent = GenerateNextEvent.generateNextEvent(currEvent, clock);
                parentEventsQueue.add(nextEvent);
            } else if(currentBS.getNumOfReservedChannel()==1){
                nextBS.useReservedChannel();
                ParentEvent nextEvent = GenerateNextEvent.generateNextEvent(currEvent, clock);
                parentEventsQueue.add(nextEvent);
            }
            else{
           //     System.out.println("BaseStation "+nextBS.getId()+" has no free channel to be hand over");
                droppedCallCount+=1;
            }
        }
        else if (currEvent instanceof TerminationEvent){
          //  System.out.println("~~~~~~~~~~~~~Termination Event~~~~~~~~~~~~~~");
            currentBS.releaseNormalChannel();
        }
    }

}
