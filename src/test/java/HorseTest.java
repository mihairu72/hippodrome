import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {

    @Test
    public void nullNameThrowsIAException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.5, 1) );
    }
    @Test
    public void nullNameIAExceptionMessage() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.5, 1) );
        assertEquals("Name cannot be null.", e.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings={"", "   ", "\t", "\n"})
    public void blankNameThrowsIAException(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 2.5, 1));
    }
    @ParameterizedTest
    @ValueSource(strings={"", "   ", "\t", "\n"})
    public void blankNameIAExceptionMessage(String name) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 2.5, 1));
        assertEquals("Name cannot be blank.", e.getMessage());
    }
    @Test
    public void negativeSpeedThrowsIAException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Znoy", -2.5, 1));
    }
    @Test
    public void negativeSpeedIAExceptionMessage() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("Znoy", -2.5, 1));
        assertEquals("Speed cannot be negative.", e.getMessage());
    }
    @Test
    public void negativeDistanceThrowsIAException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Znoy", 2.5, -1));
    }
    @Test
    public void negativeDistanceIAExceptionMessage() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("Znoy", 2.5, -1));
        assertEquals("Distance cannot be negative.", e.getMessage());
    }
    @Test
    public void getNameMethodTest() {
        Horse horse = new Horse("Znoy", 2.5, 1);
//        Field name = Horse.class.getDeclaredField("name");
//        name.setAccessible(true);
//        String value = (String) name.get(horse);
        assertEquals("Znoy", horse.getName());
    }
    @Test
    public void getSpeedMethodTest() {
        Horse horse = new Horse("Znoy", 2.5, 1);
        assertEquals(2.5, horse.getSpeed());
    }
    @Test
    public void getDistanceMethodTest() {
        Horse horse = new Horse("Znoy", 2.5, 1.5);
        assertEquals(1.5, horse.getDistance());
    }
    @Test
    public void distanceSetToZeroIfNotSpecified() {
        Horse horse = new Horse("Znoy", 2.5);
        assertEquals(0, horse.getDistance());
    }
    @Test
    public void getRandomDoubleMethodTest() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Znoy", 2.5, 1.5);
            horse.move();
            mockedStatic.verify( () -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
    @ParameterizedTest
    @ValueSource(doubles = {0.3, 0.4, 0.8, 0.7})
    public void getRandomFormulaTest(double random) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            Horse horse = new Horse("Znoy", 2.5, 1.5);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);

            horse.move();

            assertEquals(1.5+2.5*random, horse.getDistance());
        }
    }
}
