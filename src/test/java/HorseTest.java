import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class HorseTest {
    private Horse horse;

    //Проверка на выброс исключения если строка null
    @Test
    void when1ArgNullThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            horse = new Horse(null, 3);
        });
    }

    //Проверка сообщения выброшенного исключения если null
    @Test
    void sendMessageIfFirstArgNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            horse = new Horse(null, 30);
        });
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    //Проверка на выброс исключения если пустая строка
    @ParameterizedTest
    @EmptySource
    void when1ArgBlankThrowException(String blank) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            horse = new Horse(blank, 30);
        });
    }

    //Проверка  сообщения если пустая строка
    @ParameterizedTest
    @EmptySource
    void sendMessageIf1ArgBlank(String blank) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            horse = new Horse(blank, 30);
        });
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    //Если 2й аргумент отрицательный тогда выбросить исключение
    @Test
    void when2ArgIsNegativeThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            horse = new Horse("Mike", -4);
        });
    }

    //Если 2й аргумент отрицательный тогда отправить сообщение
    @Test
    void sendMessageIf2ArgIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            horse = new Horse("Mike", -4);
        });
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    //Если 3й аргумент отрицательный тогда выбросить исключение
    @Test
    void when3ArgIsNegativeThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            horse = new Horse("Mike", 30, -10);
        });
    }

    //Если 3й аргумент отрицательный тогда отправить сообщение
    @Test
    void sendMessageIf3ArgIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            horse = new Horse("Mike", 30, -10);
        });
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void shouldGetName() {
        horse = new Horse("Mike", 30);
        assertEquals("Mike", horse.getName());
    }

    @Test
    void shouldGetSpeed() {
        horse = new Horse("Mike", 30);
        assertEquals(30, horse.getSpeed());
    }

    @Test
    void shouldGetDistance() {
        horse = new Horse("Mike", 30, 10);
        assertEquals(10, horse.getDistance());
    }

    @Test
    void shouldGetDistanceIfOnly2Args() {
        horse = new Horse("Mike", 30);
        assertEquals(0, horse.getDistance());
    }

    //Проверить, что метод вызывает внутри метод getRandomDouble с параметрами 0.2 и 0.9.
    @Test
    void checkWorkRandomForMove() {
        try (MockedStatic<Horse> getRand = Mockito.mockStatic(Horse.class)) {
            getRand.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.4);
            assertEquals(0.4, Horse.getRandomDouble(0.2, 0.9));
            getRand.verify(() -> Horse.getRandomDouble(0.2, 0.9));
            // Узнать как будет работать verify именно с MockStatic
        }
    }

//    Проверить, что метод присваивает дистанции значение
//    по формуле: distance + speed * getRandomDouble(0.2, 0.9).
    @Test
    void shouldMoveBeRight(){
        horse = new Horse("Mike",30,20);
        // 30 + 20 * 0.4 = 32
        try (MockedStatic<Horse> getRand = Mockito.mockStatic(Horse.class)) {
            getRand.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.4);
            assertEquals(0.4, Horse.getRandomDouble(0.2, 0.9));
            getRand.verify(() -> Horse.getRandomDouble(0.2, 0.9));
            assertEquals(32,horse.getDistance()+(horse.getSpeed()*Horse.getRandomDouble(0.2, 0.9)));
        }
    }
}