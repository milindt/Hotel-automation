import java.util.Arrays;
import java.util.Collection;

public class ElectronicEquipmentFactory {

    public static Collection<ElectronicEquipment> getSubCorridorDevices() {
        return Arrays.asList(new ElectronicEquipment(ElectronicEquipment.LIGHT, 5, false),
                new ElectronicEquipment(ElectronicEquipment.AC, 10, true));
    }

    public static Collection<ElectronicEquipment> getCorridorDevices() {
        return Arrays.asList(new ElectronicEquipment(ElectronicEquipment.LIGHT, 5, true),
                new ElectronicEquipment(ElectronicEquipment.AC, 10, true));
    }
}
