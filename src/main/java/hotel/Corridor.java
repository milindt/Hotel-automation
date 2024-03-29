package hotel;

import controller.ElectronicEquipment;
import sensors.MotionSensible;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;


public class Corridor implements MotionSensible {

    private final Map<String, ElectronicEquipment> electronicEquipmentMap;

    public Corridor(Collection<ElectronicEquipment> equipments) {
        electronicEquipmentMap = equipments
                .stream()
                .collect(Collectors.toMap(
                        ElectronicEquipment::getType, v -> v));
    }

    public ElectronicEquipment getElectronicEquipment(String name) {
        return electronicEquipmentMap.get(name);
    }

    int getTotalPowerConsumption() {
        return electronicEquipmentMap
                .values()
                .stream()
                .map(ElectronicEquipment::getUnits)
                .reduce(Integer::sum)
                .orElse(0);
    }

}
