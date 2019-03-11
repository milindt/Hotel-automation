package sensors;

public class MotionSensorControl {

    //Suppressing warning knowing this is an external module
    // which would use the parameter
    @SuppressWarnings("unused")
    public boolean isMovement(MotionSensible sensible) {
        //To be treated as external module,
        // This won't have any implementation
        return false;
    }

}
