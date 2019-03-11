package hotel;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private List<Floor> floors;

    public Hotel(int numberOfFloors, int numberOfMainCorridors, int numberOfSubCorridors) {
        floors = new ArrayList<>();
        for(int floorIndex=0;floorIndex<numberOfFloors;floorIndex++) {
            floors.add(new Floor(numberOfMainCorridors, numberOfSubCorridors));
        }
    }

    public List<Floor> getFloors() {
        return floors;
    }
}
