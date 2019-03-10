import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Floor {
    private final List<Corridor> corridors;
    private final List<SubCorridor> subCorridors;

    public Floor(int numberOfMainCorridors, int numberOfSubCorridors) {
        corridors = new ArrayList();
        for(int corridorIndex=0;corridorIndex<numberOfMainCorridors;corridorIndex++) {
            corridors.add(new Corridor(
                    Arrays.asList(new ElectronicEquipment(ElectronicEquipment.LIGHT, 5, true),
                            new ElectronicEquipment(ElectronicEquipment.AC, 10, true))));
        }
        subCorridors = new ArrayList();
        for(int subCorridorIndex=0; subCorridorIndex<numberOfSubCorridors;subCorridorIndex++) {
            subCorridors.add(new SubCorridor(
                    Arrays.asList(new ElectronicEquipment(ElectronicEquipment.LIGHT, 5, false),
                    new ElectronicEquipment(ElectronicEquipment.AC, 10, true))));
        }
    }

    public List<Corridor> getCorridors() {
        return corridors;
    }

    public List<SubCorridor> getSubCorridors() {
        return subCorridors;
    }

    public int totalPowerConsumption() {
        return corridors.stream()
                .map(Corridor::getTotalPowerConsumption)
                .reduce(Integer::sum)
                .orElse(0) +
                subCorridors.stream()
                .map(SubCorridor::getTotalPowerConsumption)
                .reduce(Integer::sum)
                .orElse(0);
    }

}

