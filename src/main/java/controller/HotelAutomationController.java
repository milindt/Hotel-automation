package controller;

import hotel.Floor;
import hotel.Hotel;
import hotel.SubCorridor;
import sensors.MotionSensorControl;

import java.util.Collection;
import java.util.function.Predicate;

public class HotelAutomationController {

    private Collection<Hotel>   hotels;
    private MotionSensorControl motionSensorControl;

    public HotelAutomationController(Collection<Hotel> hotels, MotionSensorControl motionSensorControl) {
        this.hotels = hotels;
        this.motionSensorControl = motionSensorControl;
    }

    /**
     * Call when motion is detected
     */
    public Collection<Hotel> operate() {
        switchEquipmentStatusInSubCorridorsMatching(ElectronicEquipmentFactory.LIGHT, true,
                s -> motionSensorControl.isMovement(s), f -> true);

        switchEquipmentStatusInSubCorridorsMatching(ElectronicEquipmentFactory.LIGHT, false,
                s -> !motionSensorControl.isMovement(s), f -> true);

        switchEquipmentStatusInSubCorridorsMatching(ElectronicEquipmentFactory.AC, true,
                s -> !motionSensorControl.isMovement(s), f -> true);

        switchEquipmentStatusInSubCorridorsMatching(ElectronicEquipmentFactory.AC, false,
                s -> true, f ->f.totalPowerConsumption() > 15 * f.getCorridors().size() + 10 * f.getSubCorridors().size());
        return hotels;
    }

    private void switchEquipmentStatusInSubCorridorsMatching(
            String electronicEquipment, boolean on, Predicate<SubCorridor> subCorridorPredicate, Predicate<Floor> getFloorCriteria) {
        hotels.stream()
                .flatMap(h -> h.getFloors().stream())
                .filter(getFloorCriteria)
                .flatMap(f -> f.getSubCorridors().stream())
                .filter(subCorridorPredicate)
                .forEach(s -> s.getElectronicEquipment(electronicEquipment).switchIt(on));
    }

    public Collection<Hotel> hotels() {
        return hotels;
    }

}
