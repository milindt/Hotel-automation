import controller.ElectronicEquipment;
import controller.ElectronicEquipmentFactory;
import controller.HotelAutomationController;
import hotel.Corridor;
import hotel.Floor;
import hotel.Hotel;
import hotel.SubCorridor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sensors.MotionSensible;
import sensors.MotionSensorControl;

import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class HotelAutomationControllerTest {

    @Test
    void hotelCanHaveMultipleFloors() {
        Hotel hotel = new Hotel(2, 0, 0);
        assertThat(hotel.getFloors().size()).isEqualTo(2);
    }

    @Test
    void eachFloorCanHaveMultipleMainCorridors() {
        Hotel hotel = new Hotel(2, 3, 0);
        assertThat(hotel.getFloors().get(0).getCorridors().size()).isEqualTo(3);

        hotel.getFloors()
                .stream()
                .flatMap(f -> f.getCorridors().stream())
                .forEach(c -> assertThat(c).isInstanceOf(Corridor.class));
    }

    @Test
    void eachFloorCanHaveMultipleSubCorridors() {
        Hotel hotel = new Hotel(2, 3, 4);
        assertThat(hotel.getFloors().get(0).getSubCorridors().size()).isEqualTo(4);

        hotel.getFloors()
                .stream()
                .flatMap(f -> f.getSubCorridors().stream())
                .forEach(c -> assertThat(c).isInstanceOf(SubCorridor.class));
    }

    @Test
    void lightConsumes5Units() {
        assertThat(new ElectronicEquipment(ElectronicEquipmentFactory.LIGHT, 5, true).getUnits()).isEqualTo(5);
    }

    @Test
    void lightConsumes0UnitsWhenOff() {
        assertThat(new ElectronicEquipment(ElectronicEquipmentFactory.LIGHT, 5, false).getUnits()).isEqualTo(0);
    }

    @Test
    void eachCorridorsHasOneLight() {
        assertThat(new Corridor(
                Collections.singletonList(new ElectronicEquipment(ElectronicEquipmentFactory.LIGHT, 5, true)))
                .getElectronicEquipment(ElectronicEquipmentFactory.LIGHT).getType())
                .isEqualTo(ElectronicEquipmentFactory.LIGHT);
    }

    @Test
    void eachSubCorridorsHasOneLight() {
        assertThat(new SubCorridor(
                Collections.singletonList(new ElectronicEquipment(ElectronicEquipmentFactory.LIGHT, 5, false)))
                .getElectronicEquipment(ElectronicEquipmentFactory.LIGHT).getType())
                .isEqualTo(ElectronicEquipmentFactory.LIGHT);
    }

    @Test
    void acConsumes10Units() {
        assertThat(new ElectronicEquipment(ElectronicEquipmentFactory.AC,10, true).getUnits()).isEqualTo(10);
    }

    @Test
    void acConsumes0UnitsWhenOff() {
        assertThat(new ElectronicEquipment(ElectronicEquipmentFactory.AC,10, false).getUnits()).isEqualTo(0);
    }

    @Test
    void eachCorridorsHasOneAc() {
        assertThat(new Corridor(
                Collections.singletonList(new ElectronicEquipment(ElectronicEquipmentFactory.AC, 10, true)))
                .getElectronicEquipment(ElectronicEquipmentFactory.AC).getType())
                .isEqualTo(ElectronicEquipmentFactory.AC);
    }

    @Test
    void eachSubCorridorsHasOneAc() {
        assertThat(new SubCorridor(
                Collections.singletonList(new ElectronicEquipment(ElectronicEquipmentFactory.AC, 10, false)))
                .getElectronicEquipment(ElectronicEquipmentFactory.AC).getType())
                .isEqualTo(ElectronicEquipmentFactory.AC);
    }

    @Test
    void mainCorridorHasAlltheLightsOnByDefault() {
        //Assuming only Night time
        new Hotel(2, 2, 0)
                .getFloors()
                .stream()
                .flatMap(f -> f.getCorridors().stream())
                .map(c -> c.getElectronicEquipment(ElectronicEquipmentFactory.LIGHT))
                .forEach(l -> assertThat(l.isOn()).isTrue());
    }

    @Test
    void subCorridorHasAlltheListsOffByDefault() {
        new Hotel(2, 2, 0)
                .getFloors()
                .stream()
                .flatMap(f -> f.getSubCorridors().stream())
                .map(s -> s.getElectronicEquipment(ElectronicEquipmentFactory.LIGHT))
                .forEach(l -> assertThat(l.isOn()).isFalse());
    }

    @Test
    void mainCorridorHasAlltheAcsOnByDefault() {
        //Assuming only Night time
        new Hotel(2, 2, 0)
                .getFloors()
                .stream()
                .flatMap(f -> f.getCorridors().stream())
                .map(c -> c.getElectronicEquipment(ElectronicEquipmentFactory.AC))
                .forEach(l -> assertThat(l.isOn()).isTrue());
    }

    @Test
    void subCorridorHasAlltheAcsOnByDefault() {
        new Hotel(2, 2, 0)
                .getFloors()
                .stream()
                .flatMap(f -> f.getSubCorridors().stream())
                .map(s -> s.getElectronicEquipment(ElectronicEquipmentFactory.LIGHT))
                .forEach(l -> assertThat(l.isOn()).isTrue());
    }

    @Test
    void canDetectMotionInCorridors() {
        /*Movement detection could be a push mechanism,
         but for sake of simplicity we will poll the sensor
         for movement detection for now*/
        assertThat(new Corridor(Collections.singletonList(
                new ElectronicEquipment(ElectronicEquipmentFactory.LIGHT, 5, true))))
                .isInstanceOf(MotionSensible.class);
    }

    @Test
    void eachSubCorridorCanDetectMotion() {
        /*Movement detection could be a push mechanism,
         but for sake of simplicity we will poll the sensor
         for movement detection for now*/
        assertThat(new SubCorridor(
                Collections.singletonList(new ElectronicEquipment(ElectronicEquipmentFactory.LIGHT, 5, false))))
                .isInstanceOf(MotionSensible.class);
    }

    @Test
    void controllerCanOperateOnHotels() {
        //Operate could be called periodically and on any sensor trigger(if/when push is implemented)
        new HotelAutomationController(Collections.singletonList(
                new Hotel(1, 2, 4)), new MotionSensorControl())
                .operate();
    }

    @Test
    void whenMotionIsDetectedInSubCorridorsControllerShouldTurnTheLightOn() {
        //Operate could be called periodically and on any sensor trigger(if/when push is implemented)
        final MotionSensorControl motionSensorControl = Mockito.mock(MotionSensorControl.class);

        final HotelAutomationController hotelAutomationController = new HotelAutomationController(Collections.singletonList(
                new Hotel(1, 2, 4)), motionSensorControl);

        final SubCorridor firstSubCorridorOnFirstFloorInFirstHotel =
                getFirstSubCorridorOnFirstFloorInFirstHotel(hotelAutomationController.hotels());

        Mockito.when(motionSensorControl.isMovement(firstSubCorridorOnFirstFloorInFirstHotel))
                .thenReturn(true);

        assertThat(getFirstSubCorridorOnFirstFloorInFirstHotel(hotelAutomationController
                .operate())
                .getElectronicEquipment(ElectronicEquipmentFactory.LIGHT)
                .isOn())
                .isTrue();
    }

    @Test
    void eachFloorShouldBeAbleTocalculateItsPowerConsumption() {
        /*Considering the by default Sub corridor has lights off,
        * the total power consumption should be 50*/
        assertThat(new Floor(2, 2)
                .totalPowerConsumption())
                .isEqualTo(50);
    }

    @Test
    void toKeepPowerConsumtionAsPerTheCriteriaControllerShouldTurnOffAcsInSubCorridorsWithoutMovement() {
        final MotionSensorControl motionSensorControl = Mockito.mock(MotionSensorControl.class);

        final int numberOfMainCorridors = 2;
        final int numberOfSubCorridors = 4;
        final HotelAutomationController hotelAutomationController = new HotelAutomationController(Collections.singletonList(
                new Hotel(2, numberOfMainCorridors, numberOfSubCorridors)), motionSensorControl);

        final SubCorridor subCorridorWithMovement =
                getFirstSubCorridorOnFirstFloorInFirstHotel(hotelAutomationController.hotels());


        Mockito.when(motionSensorControl.isMovement(
                subCorridorWithMovement))
                .thenReturn(true);

        assertThat(hotelAutomationController
                .operate()
                .stream()
                .flatMap(h -> h.getFloors().stream())
                .filter(floor -> floorHasMovement(floor, hotelAutomationController.hotels(), motionSensorControl))
                .flatMap(f -> f.getSubCorridors().stream())
                .allMatch(s -> s.getElectronicEquipment(ElectronicEquipmentFactory.AC).isOff()))
                .isTrue();

        assertThat(hotelAutomationController.hotels()
                .stream()
                .flatMap(h -> h.getFloors().stream())
                .filter(f -> f.totalPowerConsumption() >= 15*numberOfMainCorridors* +  10*numberOfSubCorridors)
                .findAny())
                .isNotPresent();
    }

    @Test
    void whenThereIsNoMovementForOneMinSubCorridorLightsShouldBeOff() {
        final MotionSensorControl motionSensorControl = Mockito.mock(MotionSensorControl.class);

        final int numberOfMainCorridors = 2;
        final int numberOfSubCorridors = 4;
        final HotelAutomationController hotelAutomationController = new HotelAutomationController(Collections.singletonList(
                new Hotel(2, numberOfMainCorridors, numberOfSubCorridors)), motionSensorControl);

        Mockito.when(motionSensorControl.isMovement(
                getFirstSubCorridorOnFirstFloorInFirstHotel(hotelAutomationController.hotels())))
                .thenReturn(true);

        hotelAutomationController.operate();

        Mockito.when(motionSensorControl.isMovement(
                getFirstSubCorridorOnFirstFloorInFirstHotel(hotelAutomationController.hotels())))
                .thenReturn(false);

        /*Assuming operate is called after 1 min*/
        assertThat(hotelAutomationController
                .operate()
                .stream()
                .flatMap(h -> h.getFloors().stream())
                .flatMap(f -> f.getSubCorridors().stream())
                .allMatch(s -> s.getElectronicEquipment(ElectronicEquipmentFactory.LIGHT).isOff()))
                .isTrue();
    }

    @Test
    void whenThereIsNoMovementForOneMinSubCorridorAcsShouldBeOn() {
        final MotionSensorControl motionSensorControl = Mockito.mock(MotionSensorControl.class);

        final int numberOfMainCorridors = 2;
        final int numberOfSubCorridors = 4;
        final HotelAutomationController hotelAutomationController = new HotelAutomationController(Collections.singletonList(
                new Hotel(2, numberOfMainCorridors, numberOfSubCorridors)), motionSensorControl);

        Mockito.when(motionSensorControl.isMovement(
                getFirstSubCorridorOnFirstFloorInFirstHotel(hotelAutomationController.hotels())))
                .thenReturn(true);

        hotelAutomationController.operate();

        Mockito.when(motionSensorControl.isMovement(
                getFirstSubCorridorOnFirstFloorInFirstHotel(hotelAutomationController.hotels())))
                .thenReturn(false);

        /*Assuming operate is called after 1 min*/
        assertThat(hotelAutomationController
                .operate()
                .stream()
                .flatMap(h -> h.getFloors().stream())
                .flatMap(f -> f.getSubCorridors().stream())
                .allMatch(s -> s.getElectronicEquipment(ElectronicEquipmentFactory.AC).isOn()))
                .isTrue();
    }

    @Test
    void aFactoryShouldCreateSubCorridorDevices() {
        assertThat(ElectronicEquipmentFactory.getSubCorridorDevices())
                .hasSize(2)
                .containsOnlyOnce(new ElectronicEquipment(ElectronicEquipmentFactory.AC, 10, true))
                .containsOnlyOnce(new ElectronicEquipment(ElectronicEquipmentFactory.LIGHT, 5, false));
    }

    @Test
    void aFactoryShouldCreateCorridorDevices() {
        assertThat(ElectronicEquipmentFactory.getCorridorDevices())
                .hasSize(2)
                .containsOnlyOnce(new ElectronicEquipment(ElectronicEquipmentFactory.AC, 10, true))
                .containsOnlyOnce(new ElectronicEquipment(ElectronicEquipmentFactory.LIGHT, 5, true));
    }

    private SubCorridor getFirstSubCorridorOnFirstFloorInFirstHotel(Collection<Hotel> hotels) {
        return hotels.stream()
                .flatMap(h -> h.getFloors().stream())
                .flatMap(f -> f.getSubCorridors().stream())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No sub corridor found"));
    }

    boolean floorHasMovement(Floor floor, Collection<Hotel> hotels, MotionSensorControl motionSensorControl) {
        //Not including main floors as of now..
        return hotels.stream()
                .flatMap(h -> h.getFloors().stream())
                .filter(f -> f.equals(floor))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getSubCorridors()
                .stream()
                .anyMatch(motionSensorControl::isMovement);
    }

}
