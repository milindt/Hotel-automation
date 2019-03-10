import java.util.Collection;

class HotelAutomationController {

    private Collection<Hotel> hotels;
    private MotionSensorControl motionSensorControl;

    HotelAutomationController(Collection<Hotel> hotels, MotionSensorControl motionSensorControl) {
        this.hotels = hotels;
        this.motionSensorControl = motionSensorControl;
    }

    /**
     * Call when motion is detected
     */
    Collection<Hotel> operate() {
        switchOnSubCorridorLightsNearMovement();
        switchOffSubCorridorLightsWithoutMovement();
        switchOnSubCorridorAcsWithoutMovement();
        switchOffAllTheSubCorridorAcsAsPerCriteria();
        return hotels;
    }

    private void switchOnSubCorridorAcsWithoutMovement() {
        hotels.stream()
                .flatMap(h -> h.getFloors().stream())
                .flatMap(f -> f.getSubCorridors().stream())
                .filter(s -> !motionSensorControl.isMovement(s))
                .forEach(s -> s.getAc().switchOn());
    }

    private void switchOffSubCorridorLightsWithoutMovement() {
        hotels.stream()
                .flatMap(h -> h.getFloors().stream())
                .flatMap(f -> f.getSubCorridors().stream())
                .filter(s -> !motionSensorControl.isMovement(s))
                .forEach(s -> s.getLight().switchOff());
    }

    private void switchOffAllTheSubCorridorAcsAsPerCriteria() {
        //Having flipped some switches, make sure the consumption criteria is still met
        hotels.stream()
                .flatMap(h -> h.getFloors().stream())
                .filter(this::getFloorCriteria)
                .flatMap(f -> f.getSubCorridors().stream())
                .forEach(s -> s.getAc().switchOff());
    }

    private void switchOnSubCorridorLightsNearMovement() {
        hotels.stream()
                .flatMap(h -> h.getFloors().stream())
                .flatMap(f -> f.getSubCorridors().stream())
                .filter(s -> motionSensorControl.isMovement(s))
                .forEach(s -> s.getLight().switchOn());
    }

    private boolean getFloorCriteria(Floor floor) {
        return floor.totalPowerConsumption() >
                15 * floor.getCorridors().size() +
                10 * floor.getSubCorridors().size();
    }

    Collection<Hotel> hotels() {
        return hotels;
    }

    boolean floorHasMovement(Floor floor) {
        //Not including main floors as of now..
        return hotels.stream()
                .flatMap(h -> h.getFloors().stream())
                .filter(f -> f.equals(floor))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getSubCorridors()
                .stream()
                .anyMatch(s -> motionSensorControl.isMovement(s));
    }
}
