import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HotelAutomationControllerTestTest {

    @BeforeEach
    void setUp() {
       Hotel hotel = new Hotel(2, 2, 2);
    }

    @Test
    void hotelCanHaveMultipleFloors() {
        Hotel hotel = new Hotel(2, 0, 0);
        Assertions.assertThat(hotel.getFloors().size()).isEqualTo(2);
    }

    @Test
    void eachFloorCanHaveMultipleMainCorridors() {
        Hotel hotel = new Hotel(2, 3, 0);
        Assertions.assertThat(hotel.getFloors().get(0).getCorridors().size()).isEqualTo(3);
    }

}