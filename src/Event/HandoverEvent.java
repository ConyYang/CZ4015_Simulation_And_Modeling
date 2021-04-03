package Event;

import Components.BaseStation;
import Components.Direction;

public class HandoverEvent extends ParentEvent{
    public HandoverEvent(Integer eventID, BaseStation baseStationNormal, Double eventTime, Direction direction, Double speed, Double callDuration, Double position) {
        super(eventID, baseStationNormal, eventTime, direction, speed, callDuration, position);
    }

    @Override
    public String toString(){
        return "HandoverEvent: \n"+super.toString();
    }

}
