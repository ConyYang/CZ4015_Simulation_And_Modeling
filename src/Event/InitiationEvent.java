package Event;

import Components.BaseStation;
import Components.Direction;


public class InitiationEvent extends ParentEvent {

    public InitiationEvent(Integer eventID, BaseStation baseStationNormal, Double eventTime,
                           Direction direction, Double speed,
                           Double callDuration, Double position) {
        super(eventID, baseStationNormal, eventTime, direction, speed, callDuration, position);
    }

    @Override
    public String toString(){
        return "InitiationEvent: \n"+super.toString();
    }
}
