import java.util.ArrayList;
import java.util.List;

public class Floor {
    private final List<Corridor> corridors;
    private final List<SubCorridor> subCorridors;

    public Floor(int numberOfMainCorridors, int numberOfSubCorridors) {
        corridors = new ArrayList();
        for(int corridorIndex=0;corridorIndex<numberOfMainCorridors;corridorIndex++) {
            corridors.add(new Corridor(new Light(5, true), new Ac(0, true), null));
        }
        subCorridors = new ArrayList();
        for(int subCorridorIndex=0; subCorridorIndex<numberOfSubCorridors;subCorridorIndex++) {
            subCorridors.add(new SubCorridor(new Light(5, false), new Ac(10, true)));
        }
    }

    public List<Corridor> getCorridors() {
        return corridors;
    }

    public List<SubCorridor> getSubCorridors() {
        return subCorridors;
    }
}

