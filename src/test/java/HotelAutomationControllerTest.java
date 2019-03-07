import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class HotelAutomationControllerTest {

    @BeforeEach
    void setUp() {
       Hotel hotel = new Hotel(2, 2, 2);
    }

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
        assertThat(new Light(5, true).getUnits()).isEqualTo(5);
    }

    @Test
    void lightConsumes0UnitsWhenOff() {
        assertThat(new Light(5, false).getUnits()).isEqualTo(0);
    }

    @Test
    void eachCorridorsHasOneLight() {
        assertThat(new Corridor(new Light(0, false), null)
                .getLight())
                .isInstanceOf(Light.class);
    }

    @Test
    void eachSubCorridorsHasOneLight() {
        assertThat(new SubCorridor(new Light(0, false), new Ac(0, false))
                .getLight())
                .isInstanceOf(Light.class);
    }

    @Test
    void acConsumes10Units() {
        assertThat(new Ac(10, true).getUnits()).isEqualTo(10);
    }

    @Test
    void acConsumes0UnitsWhenOff() {
        assertThat(new Ac(10, false).getUnits()).isEqualTo(0);
    }

    @Test
    void eachCorridorsHasOneAc() {
        assertThat(new Corridor(null, new Ac(0, false))
                .getAc())
                .isInstanceOf(Ac.class);
    }

    @Test
    void eachSubCorridorsHasOneAc() {
        assertThat(new SubCorridor(null, new Ac(0, false))
                .getAc())
                .isInstanceOf(Ac.class);
    }

    @Test
    void mainCorridorHasAlltheListsOnByDefault() {
        //Assuming only Night time
        new Hotel(2, 2,0)
                .getFloors()
                .stream()
                .flatMap(f -> f.getCorridors().stream())
                .map(Corridor::getLight)
                .forEach(l -> assertThat(l.isOn()).isTrue());
    }

    @Test
    void subCorridorHasAlltheListsOffByDefault() {
        new Hotel(2, 2,0)
                .getFloors()
                .stream()
                .flatMap(f -> f.getSubCorridors().stream())
                .map(SubCorridor::getLight)
                .forEach(l -> assertThat(l.isOn()).isFalse());
    }

    @Test
    void mainCorridorHasAlltheAcsOnByDefault() {
        //Assuming only Night time
        new Hotel(2, 2,0)
                .getFloors()
                .stream()
                .flatMap(f -> f.getCorridors().stream())
                .map(Corridor::getAc)
                .forEach(l -> assertThat(l.isOn()).isTrue());
    }

    @Test
    void subCorridorHasAlltheAcsOnByDefault() {
        new Hotel(2, 2,0)
                .getFloors()
                .stream()
                .flatMap(f -> f.getSubCorridors().stream())
                .map(SubCorridor::getLight)
                .forEach(l -> assertThat(l.isOn()).isTrue());
    }


}
