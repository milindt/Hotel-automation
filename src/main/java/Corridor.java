import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

class Corridor implements MotionSensible {

    private final Ac ac;
    private final Map<String, ElectronicEquipment> electronicEquipmentMap;

    Corridor(Ac ac, Collection<ElectronicEquipment> equipments) {
        this.ac = ac;
        electronicEquipmentMap = equipments.stream().collect(Collectors.toMap(ElectronicEquipment::getType, v -> v));
    }

    ElectronicEquipment getElectronicEquipment(String name) {
        return electronicEquipmentMap.get(name);
    }

    Ac getAc() {
        return ac;
    }

    int getTotalPowerConsumption() {
        return getElectronicEquipment(ElectronicEquipment.LIGHT).getUnits() + getAc().getUnits();
    }

}
