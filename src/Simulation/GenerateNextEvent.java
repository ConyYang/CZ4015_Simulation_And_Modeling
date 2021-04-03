package Simulation;

import Components.Direction;
import Event.HandoverEvent;
import Event.InitiationEvent;
import Event.ParentEvent;
import Event.TerminationEvent;


public class GenerateNextEvent {
    public static ParentEvent generateNextEvent(ParentEvent currEvent, double clock){
        ParentEvent nextEvent= null;
        double remainDistTONextBS;
        double realRemainingTimeToNextBS;
        double remainingTimeToNextBS_CalByDist;
        double nextEventDuration;

        if (currEvent instanceof InitiationEvent){
            double currPosition = currEvent.getPosition();
            System.out.println("[Simulation.GenerateNextEvent] The current car is at position: "+currPosition+" to baseStation "+currEvent.getDirection());

            if (currEvent.getDirection().equals(Direction.TO_BS_1)){
                remainDistTONextBS = currPosition;
            } else{
                remainDistTONextBS = 2000.00 - currEvent.getPosition();
            }
        }
        else {
            // it is handover or termination event
            remainDistTONextBS = 2000.00 - currEvent.getPosition();
        }

        // Get duration of next event time
        double currEventCallDuration = currEvent.getCallDuration();
        remainingTimeToNextBS_CalByDist = remainDistTONextBS / (currEvent.getSpeed()*1000.00/3600.00);
        System.out.println("Simulation.GenerateNextEvent remainingTimeToNextBS_CalByDist: "+remainingTimeToNextBS_CalByDist+
                "Call Duration"+ currEventCallDuration);
        if (remainingTimeToNextBS_CalByDist< currEvent.getCallDuration()){
            realRemainingTimeToNextBS = remainingTimeToNextBS_CalByDist;
        }else{
            realRemainingTimeToNextBS = currEventCallDuration;
        }
        nextEventDuration = currEventCallDuration - realRemainingTimeToNextBS;

        // Create next Event
        if (currEventCallDuration > realRemainingTimeToNextBS && currEvent.getDirection() == Direction.TO_BS_1 && currEvent.getBaseStation().getId()!=1){
            nextEvent = new HandoverEvent(
                    currEvent.getEventID(), //event ID
                    currEvent.getBaseStation(), // BaseStation
                    clock+realRemainingTimeToNextBS, // event time
                    currEvent.getDirection(), // direction
                    currEvent.getSpeed(), // speed
                    nextEventDuration, // call duration
                    0.0); // positions
            System.out.println("Create next event: "+nextEvent.toString());
        } else if (currEventCallDuration > realRemainingTimeToNextBS && currEvent.getDirection() == Direction.TO_BS_20 && currEvent.getBaseStation().getId()!=20){
            nextEvent = new HandoverEvent(
                    currEvent.getEventID(), //event ID
                    currEvent.getBaseStation(), // BaseStation
                    clock+realRemainingTimeToNextBS, // event time
                    currEvent.getDirection(), // direction
                    currEvent.getSpeed(), // speed
                    nextEventDuration, // call duration
                    0.0); // positions
            System.out.println("Create next event: "+nextEvent.toString());
        } else {
            // call end before reach nextBS
            nextEvent = new TerminationEvent(
                    currEvent.getEventID(),
                    currEvent.getBaseStation(),
                    clock+realRemainingTimeToNextBS
            );
            System.out.println("Creat next event: "+nextEvent.toString());
        }
        return nextEvent;
    }
}
