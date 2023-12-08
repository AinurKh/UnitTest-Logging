import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {


    private  List<Horse> horses;
    Hippodrome hippodrome;

    @Test //Проверка на выброс исключения если horses null
    void whenConstructorNullThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()->{
            Hippodrome hippodrome = new Hippodrome(null);
        });
    }


    @Test //Проверка сообщения выброшенного исключения если null
    void sendMessageIfConstructorNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Hippodrome hippodrome = new Hippodrome(null);
        });
        assertEquals("Horses cannot be null.",exception.getMessage());
    }


    @Test //Проверка на выброс исключения если horses empty
    void whenConstructorIsEmptyThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()->{
           Hippodrome hippodrome=new Hippodrome(Collections.emptyList());
        });
    }

    @Test //Проверка сообщения выброшенного исключения если null
    void  sendMessageIfConstructorIsEmpty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Hippodrome hippodrome = new Hippodrome(Collections.emptyList());
        });
        assertEquals("Horses cannot be empty.",exception.getMessage());
    }

    @Test
    void getHorses() {
         horses = new ArrayList<>();
        for (int i = 0; i<30; i++) {
            horses.add(new Horse("Mike"+i,10+i,15+i));
        }
        hippodrome = new Hippodrome(horses);
        assertEquals(horses,hippodrome.getHorses());
    }

    @Test
    void move() {
        horses = new ArrayList<>();
        for (int i = 0; i <10; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        new Hippodrome(horses).move();
        for (Horse s:horses){
            Mockito.verify(s).move();
        }
    }

    @Test
    void getWinner() {
        Horse horse = new Horse("Bucephalus", 2.4,30);
        Horse horse1 = new Horse("Ace of Spades", 2.5,20);
        Horse horse2 = new Horse("Zephyr", 2.6,10);
        Horse horse3 = new Horse("Blaze", 2.7,45);
        Horse horse4 = new Horse("Lobster", 2.8);
        Horse horse5 = new Horse("Pegasus", 2.9);
        Horse horse6 = new Horse("Cherry", 3);
        horses = List.of(horse1,horse2,horse3,horse4,horse5,horse6);
        hippodrome = new Hippodrome(horses);
        assertSame(horse3,hippodrome.getWinner());
    }
}