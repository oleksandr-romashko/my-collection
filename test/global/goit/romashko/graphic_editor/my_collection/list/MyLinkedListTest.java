package global.goit.romashko.graphic_editor.my_collection.list;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class MyLinkedListTest {

    @Test
    void shouldCreateIntegerListWithZeroSize() {
        MyList<Integer> list = new MyLinkedList<>();
        assertEquals(0, list.size());
    }

    @Test
    void shouldCreateStringListWithZeroSize() {
        MyList<String> list = new MyLinkedList<>();
        assertEquals(0, list.size());
    }

    @Test
    void shouldCleanTheList() {
        MyList<String> list = new MyLinkedList<>();
        list.add("first");
        list.add("second");
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenAddNullElement() {
        MyList<String> list = new MyLinkedList<>();
        assertThrows(IllegalArgumentException.class, () -> list.add(null));
    }

    @Test
    void shouldAddOneElementListSizeOfOne() {
        MyList<String> list = new MyLinkedList<>();
        list.add("first");
        assertEquals(1, list.size());
    }

    @Test
    void shouldAddTwoElementsListSizeOfTwo() {
        MyList<String> list = new MyLinkedList<>();
        list.add("first");
        list.add("second");
        assertEquals(2, list.size());
    }

    @Test
    void shouldAddMoreThanTwoElements() {
        MyList<String> list = new MyLinkedList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        list.add("fourth");
        assertEquals(4, list.size());
    }

    @Test
    void shouldThrowOutOfBoundsExceptionWhenRemomeEmtyList() {
        MyList<String> list = new MyLinkedList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }

    @Test
    void shouldThrowOutOfBoundsExceptionWhenRemomeNegativeIndex() {
        MyList<String> list = new MyLinkedList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
    }

    @Test
    void shouldThrowOutOfBoundsExceptionWhenRemomeMoreThanSizeIndex() {
        MyList<String> list = new MyLinkedList<>();
        list.add("first");
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
    }

    @Test
    void emptyListWhenSizeIsOne() {
        MyList<String> list = new MyLinkedList<>();
        list.add("first");
        list.remove(0);
        assertEquals(0, list.size());
    }

    @Test
    void removeElementFromListWithMoreElements() {
        MyList<String> list = new MyLinkedList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        list.remove(1);
        assertEquals(2, list.size());
    }

    @Test
    void removeFirstElementFromListWithMoreElements() {
        MyList<String> list = new MyLinkedList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        list.remove(0);
        assertEquals(2, list.size());
    }

    @Test
    void returnEmptyToStringWhenSizeIsZero() {
        MyList<String> list = new MyLinkedList<>();
        assertEquals("[]", list.toString());
    }

    @Test
    void returnToStringWhenSizeIsNotZero() {
        MyList<String> list = new MyLinkedList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        assertEquals("[first, second, third]", list.toString());
    }

    @Test
    void returnIterator() {
        MyList<String> list = new MyLinkedList<>();
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

    @Test
    void shouldThrowNoSuchElementExceptionWhenListIsEmpty() {
        MyList<String> list = new MyLinkedList<>();
        Iterator<String> iterator = list.iterator();

        Throwable exception = assertThrows(
                NoSuchElementException.class, () -> iterator.hasNext()
        );
        String message = "List is empty";
        assertEquals(message, exception.getMessage());
    }
}
