package Event;

import Components.BaseStation;


public class TerminationEvent extends ParentEvent {

    public TerminationEvent(Integer eventID, BaseStation baseStationNormal, Double eventTime) {
        super(eventID, baseStationNormal, eventTime);
    }

    @Override
    public String toString(){
        return  "TerminationEvent: \n"+"EventID: "+this.getEventID()+"\nUtils.BaseStation ID: "+
                this.getBaseStation().getId()+"\nEvent Time: "+this.getEventTime();
    }

}
