package Event;

import Components.*;

public class ParentEvent {
    private Integer eventID;
    private BaseStation baseStationNormal;
    private Double eventTime;
    private Direction direction;
    private Double speed;
    private Double callDuration;
    private Double position;

    public ParentEvent(Integer eventID, BaseStation baseStationNormal,
                       Double eventTime, Direction direction, Double speed, Double callDuration, Double position) {
        this.eventID = eventID;
        this.baseStationNormal = baseStationNormal;
        this.eventTime = eventTime;
        this.direction = direction;
        this.speed = speed;
        this.callDuration = callDuration;
        this.position = position;
    }

    public ParentEvent(Integer eventID, BaseStation baseStationNormal,
                       Double eventTime) {
        this.eventID = eventID;
        this.baseStationNormal = baseStationNormal;
        this.eventTime = eventTime;
    }

    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public BaseStation getBaseStation() {
        return baseStationNormal;
    }

    public void setBaseStation(BaseStation baseStationNormal) {
        this.baseStationNormal = baseStationNormal;
    }

    public Double getEventTime() {
        return eventTime;
    }

    public void setEventTime(Double eventTime) {
        this.eventTime = eventTime;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(Double callDuration) {
        this.callDuration = callDuration;
    }

    public Double getPosition() {
        return position;
    }

    public void setPosition(Double position) {
        this.position = position;
    }

    @Override
    public String toString(){
        return "EventID: "+eventID+"\nUtils.BaseStation ID: "+ baseStationNormal.getId()+"\nEvent Time: "+eventTime+
                "\nDirection: "+direction+"\nSpeed: "+speed+"\nCall Duration: "+callDuration+"\nPosition: "+position;
    }
}
