import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {
    @Test
    public void nullHippodromeExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }
    @Test
    public void nullHippodromeExceptionMessageTest() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", e.getMessage());
    }
    @Test
    public void emptyListHippodromeExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }
    @Test
    public void emptyListHippodromeExceptionMessageTest() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", e.getMessage());
    }
    @Test
    public void getHorsesMethodTest() {
        List<Horse> horses = List.of(
                new Horse("Буцефал", 2.4),
                new Horse("Туз Пик", 2.5),
                new Horse("Зефир", 2.6),
                new Horse("Пожар", 2.7),
                new Horse("Лобстер", 2.8),
                new Horse("Пегас", 2.9),
                new Horse("Вишня", 3),
                new Horse("Муцефал", 2.4),
                new Horse("Куз Шик", 2.5),
                new Horse("Лефир", 2.6),
                new Horse("Цожар", 2.7),
                new Horse("Мобстер", 2.8),
                new Horse("Фугас", 2.9),
                new Horse("Черешня", 3),
                new Horse("Кофир", 2.6),
                new Horse("Куцефал", 2.4),
                new Horse("Валет Пик", 2.5),
                new Horse("Зефим", 2.6),
                new Horse("Пожак", 2.7),
                new Horse("Лобстем", 2.8),
                new Horse("Пегау", 2.9),
                new Horse("Вишнеу", 3),
                new Horse("Сацефал", 2.4),
                new Horse("Куз Рык", 2.5),
                new Horse("Луир", 2.6),
                new Horse("Цокар", 2.7),
                new Horse("Монстер", 2.8),
                new Horse("Фугап", 2.9),
                new Horse("Персик", 3),
                new Horse("Косир", 2.6)
        );
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }
    @Test
    public void moveMethodTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }
    @Test
    public void getWinnerMethodTest() {
        List<Horse> horses = List.of(
                new Horse("Буцефал", 2.4, 2.9),
                new Horse("Туз Пик", 2.5, 2.2),
                new Horse("Зефир", 2.6, 2.3),
                new Horse("Пожар", 2.7, 2.4),
                new Horse("Лобстер", 2.8, 2.5),
                new Horse("Пегас", 2.9, 2.6),
                new Horse("Вишня", 3, 2.7)
        );
        Hippodrome hippodrome = new Hippodrome(horses);

        Horse winner = hippodrome.getWinner();

        assertEquals(horses.get(0), winner);
    }
}
