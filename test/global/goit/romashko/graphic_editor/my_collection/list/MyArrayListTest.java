package global.goit.romashko.graphic_editor.my_collection.list;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.Iterator;


public class MyArrayListTest {
    final static int DEFAULT_SIZE = 10;

    @Test
    void shouldAddTwoStringsToListWhenAddString() {
        MyList<String> list = new MyArrayList<>();

        String element1 = "Hello";
        String element2 = "Java";
        list.add(element1);
        list.add(element2);

        assertEquals(2, list.size());
    }

    @Test
    void shouldAddTwoIntegersToListWhenAddString() {
        MyList<Integer> list = new MyArrayList<>();

        int element1 = 0;
        int element2 = 1;
        list.add(element1);
        list.add(element2);

        assertEquals(2, list.size());
    }

    @Test
    void shouldThrowExpectionWhenAddNullElement() {
        MyList<Integer> list = new MyArrayList<>();
        assertThrows(IllegalArgumentException.class,() -> list.add(null));
    }

    @Test
    void shouldHaveSameAddedOrderWhenAddingElements() {
        MyList<Integer> list = new MyArrayList<>();

        int element1 = 0;
        int element2 = 1;
        list.add(element1);
        list.add(element2);

        assertEquals(0, list.get(0));
        assertEquals(1, list.get(1));
    }

    @Test
    void shouldHaveSameTypeWhenRetreiveElement() {
        MyList<Integer> list = new MyArrayList<>();

        list.add(0);

        assertInstanceOf(Integer.class, list.get(0));

    }

    @Test
    void shouldSizeZeroWhenCreatedAndNoElements() {
        MyList<Integer> list = new MyArrayList<>();
        assertEquals(0, list.size());
    }

    @Test
    void shouldResizeListWhenMoreThanDefaultSizeNumberOfElements() {
        MyList<Integer> list = new MyArrayList<>();

        for(int i = 0; i < DEFAULT_SIZE; i++) {
            list.add(i);
        }

        assertEquals(10, list.size());

        list.add(10);

        assertEquals(11, list.size());
    }

    @Test
    void shouldReturnFormatedStringWhenToString() {
        MyList<Integer> list = new MyArrayList<>();
        for(int i = 0; i < 10; i++) {
            list.add(i);
        }
        assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", list.toString());
    }

    @Test
    void shouldThrowExceptionWhenIndexOurOfArrayBounds() {
        MyList<Integer> list = new MyArrayList<>();
        assertThrows(IndexOutOfBoundsException.class,() -> list.get(-1));

        assertThrows(IndexOutOfBoundsException.class,() -> list.get(DEFAULT_SIZE));

        assertThrows(IndexOutOfBoundsException.class,() -> list.get(DEFAULT_SIZE - 1));

        Throwable exception = assertThrows(
                IndexOutOfBoundsException.class, () -> list.get(DEFAULT_SIZE - 1)
        );
        String exceptionMessage = "Requested index 9 is out of bounds for size 0";
        assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    void shouldClearListWhenUseClearMethod() {
        MyList<Integer> list = new MyArrayList<>();
        list.add(0);
        assertEquals(1, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    void shouldThrowOutOfBoundsExceptionWhenRemomeEmtyList() {
        MyList<String> list = new MyArrayList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }

    @Test
    void shouldThrowOutOfBoundsExceptionWhenRemomeNegativeIndex() {
        MyList<String> list = new MyArrayList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
    }

    @Test
    void shouldThrowOutOfBoundsExceptionWhenRemomeMoreThanSizeIndex() {
        MyList<String> list = new MyArrayList<>();
        list.add("first");
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
    }

    @Test
    void emptyListWhenSizeIsOne() {
        MyList<String> list = new MyArrayList<>();
        list.add("first");
        list.remove(0);
        assertEquals(0, list.size());
    }

    @Test
    void removeElementFromListWithMoreElements() {
        MyList<String> list = new MyArrayList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        list.remove(1);
        assertEquals(2, list.size());
    }

    @Test
    void removeFirstElementFromListWithMoreElements() {
        MyList<String> list = new MyArrayList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        list.remove(0);
        assertEquals(2, list.size());
    }

    @Test
    void shouldEqualsWithOrder() {
        MyList<String> list1 = new MyArrayList<>();
        list1.add("first");
        list1.add("second");
        list1.add("third");

        MyList<String> list2 = new MyArrayList<>();
        list2.add("first");
        list2.add("second");
        list2.add("third");

        MyList<String> list3 = new MyArrayList<>();
        list3.add("third");
        list3.add("second");
        list3.add("first");

        assertEquals(list1, list2);
        assertNotEquals(list1, list3);
    }

    @Test
    void returnIterator() {
        MyList<String> list = new MyArrayList<>();
        list.add("first");
        list.add("second");
        list.add("third");

        Iterator<String> iterator = list.iterator();

        assertTrue(iterator.hasNext());
        assertEquals("first", iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals("second", iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals("third", iterator.next());

        assertFalse(iterator.hasNext());
    }
}
