import java.util.Collection;

public class HotelAutomationController {

    private Collection<Hotel> hotels;
    private MotionSensorControl motionSensorControl;

    public HotelAutomationController(Collection<Hotel> hotels, MotionSensorControl motionSensorControl) {
        this.hotels = hotels;
        this.motionSensorControl = motionSensorControl;
    }

    public Collection<Hotel> operate() {
        switchOnSubCorridorLightsOnMovement();
        return hotels;
    }

    private void switchOnSubCorridorLightsOnMovement() {
        hotels.stream()
                .flatMap(h -> h.getFloors().stream())
                .flatMap(f -> f.getSubCorridors().stream())
                .filter(s -> motionSensorControl.isMovement(s))
                .forEach(s -> s.getLight().switchOn());
    }

    public Collection<Hotel> hotels() {
        return hotels;
    }

    public MotionSensorControl sensorControl() {
        return motionSensorControl;
    }

}
