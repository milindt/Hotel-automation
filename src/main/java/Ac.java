public class Ac {
    private int units;
    private boolean on;

    public Ac(int units, boolean on) {
        this.units = units;
        this.on = on;
    }

    public int getUnits() {
        return on ? units : 0;
    }

    public boolean isOn() {
        return on;
    }
}
