package Components;

public class BaseStation {
    private Integer id; // 1 - 20
    private Integer numOfFreeChannel;
    private Integer numOfReservedChannel;


    public BaseStation(Integer id, Integer noFreeChannel) {
        this.id = id;
        this.numOfFreeChannel = noFreeChannel;
        this.numOfReservedChannel = 1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumOfFreeChannel() {
        return numOfFreeChannel;
    }

    public void setNumOfFreeChannel(Integer numOfFreeChannel) {
        this.numOfFreeChannel = numOfFreeChannel;
    }

    public void useNormalChannel(){
        numOfFreeChannel--;
    }

    public void releaseNormalChannel() {
        numOfFreeChannel++;
    }

    public Integer getNumOfReservedChannel() {
        return numOfReservedChannel;
    }

    public void setNumOfReservedChannel(Integer numOfReservedChannel) {
        this.numOfReservedChannel = numOfReservedChannel;
    }

    public void useReservedChannel() {
        numOfReservedChannel--;
    }

    public void releaseReservedChannel(){
        numOfReservedChannel++;
    }

}

