public class ElectronicEquipment {
    public static final String LIGHT = "Light";
    public static final String AC = "Ac";
    private String type;
    private int units;
    private boolean on;

    public ElectronicEquipment(String type, int units, boolean on) {
        this.type = type;
        this.units = units;
        this.on = on;
    }

    public int getUnits() {
        return on ? units : 0;
    }

    public void switchOn() {
        this.on = true;
    }

    public boolean isOn() {
        return on;
    }

    public boolean isOff() {
        return !on;
    }

    public void switchOff() {
        this.on = false;
    }

    public String getType() {
        return type;
    }
}
