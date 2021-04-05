package Utils;

import Components.Direction;

public class RNGTest {
    // InterArrivalTime, BaseStation, CallDuration, Velocity, Direction, Position



    public static Direction generateDirection(){
        if(Math.random()<0.5)
            return Direction.TO_BS_1;
        return Direction.TO_BS_20;
    }

    public static double generatePosition(){
        return Math.random()*2000.0;
    }

    /**
     * Note: in real generation for stats. Generate by python and just read the .csv
     */

}
