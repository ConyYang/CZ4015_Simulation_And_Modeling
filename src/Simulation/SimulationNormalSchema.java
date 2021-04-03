package Simulation;

import Components.BaseStation;
import Components.Direction;
import Event.HandoverEvent;
import Event.InitiationEvent;
import Event.ParentEvent;
import Event.TerminationEvent;


public class SimulationNormalSchema extends ParentSimulation{
    /**
     * Constructor
     */
    public SimulationNormalSchema() {
        super();
        for (int i = 0; i < 20; i++) {
            BaseStation bs = new BaseStation(i+1,10);
            this.baseStationArrayList.add(bs);
        }
    }

    @Override
    public void handleEvent(){
        ParentEvent currEvent= parentEventsQueue.peek();
        parentEventsQueue.remove(currEvent);

        assert currEvent != null;
        this.clock = currEvent.getEventTime();

        BaseStation currentBS = currEvent.getBaseStation();

        if (currEvent instanceof InitiationEvent) {
            System.out.println("~~~~~~~~~~~~~Initiation Event~~~~~~~~~~~~~~");
            if (currentBS.getNumOfFreeChannel() > 0) {
                currentBS.useNormalChannel();
                System.out.println("Current BS " + currentBS.getId() + " use one channel with " + currentBS.getNumOfFreeChannel());
                ParentEvent nextEvent = GenerateNextEvent.generateNextEvent(currEvent, clock);
                parentEventsQueue.add(nextEvent);
            } else {
                System.err.println(("Current BS " + currentBS.getId() + "Has no channel: " + currentBS.getNumOfFreeChannel()));
                blockedCallCount += 1;
            }
        }
        else if (currEvent instanceof HandoverEvent){
            System.out.println("~~~~~~~~~~~~~Handover Event~~~~~~~~~~~~~~");
            // Cal next base station
            BaseStation nextBS;
            int currBSID = currentBS.getId();
            System.out.println("Current BS: "+currBSID+" want to be hand over "+" to direction: "+currEvent.getDirection());
            if (currEvent.getDirection().equals(Direction.TO_BS_1)){
                // Check direction
                nextBS = baseStationArrayList.get(currBSID-1-1); // 1st -1 for indexing; 2nd -1 for direction
            } else {
                // Check direction
                nextBS = baseStationArrayList.get(currBSID); // 1st -1 for indexing; 2nd +1 for direction
            }

            // current station release on channel
            currentBS.releaseNormalChannel();
            System.out.println("Current BS: "+currBSID+" release one channel and has "+ currentBS.getNumOfFreeChannel() +"free channel.");
            currEvent.setBaseStation(nextBS);


            // check available channels
            if (nextBS.getNumOfFreeChannel()>0){
                System.out.println("BaseStation "+nextBS.getId()+" has free channel to be hand over");
                nextBS.useNormalChannel();
                ParentEvent nextEvent = GenerateNextEvent.generateNextEvent(currEvent, clock);
                parentEventsQueue.add(nextEvent);
            } else {
                System.out.println("BaseStation "+nextBS.getId()+" has no free channel to be hand over");
                droppedCallCount+=1;
            }
        }
        else if (currEvent instanceof TerminationEvent){
            System.out.println("~~~~~~~~~~~~~Termination Event~~~~~~~~~~~~~~");
            currentBS.releaseNormalChannel();
        }
    }

}

