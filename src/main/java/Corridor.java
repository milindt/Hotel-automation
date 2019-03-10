import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

class Corridor implements MotionSensible {

    private final Light light;
    private final Ac ac;
    private final Map<String, ElectronicEquipment> electronicEquipmentMap;

    Corridor(Light light, Ac ac, Collection<ElectronicEquipment> equipments) {
        this.light = light;
        this.ac = ac;
        electronicEquipmentMap = equipments.stream().collect(Collectors.toMap(ElectronicEquipment::getType, v -> v));
    }

    Light getLight() {
        return light;
    }

    ElectronicEquipment getElectronicEquipment(String name) {
        return electronicEquipmentMap.get(name);
    }

    Ac getAc() {
        return ac;
    }

    int getTotalPowerConsumption() {
        return getElectronicEquipment("Light").getUnits() + getAc().getUnits();
    }

}
