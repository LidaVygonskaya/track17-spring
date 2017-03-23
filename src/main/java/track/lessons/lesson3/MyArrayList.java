package track.lessons.lesson3;

import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 *
 * Должен иметь 2 конструктора
 * - без аргументов - создает внутренний массив дефолтного размера на ваш выбор
 * - с аргументом - начальный размер массива
 */
public class MyArrayList extends List {
    private static final int DEFAULT_SIZE = 10;
    private static final int INCREMENT_SIZE = 5;
    private int[] array;

    public MyArrayList() {
        this(DEFAULT_SIZE);
    }

    public MyArrayList(int capacity) {
        array = new int[capacity];
    }

    @Override
    void add(int item) {
        if (length >= array.length) {
            int[] newArray = new int[array.length + INCREMENT_SIZE];
            System.arraycopy(array,0, newArray, 0, array.length);
            array = newArray;
        }
        array[length] = item;
        length++;

    }

    @Override
    int remove(int idx) throws NoSuchElementException {
        checkIndex(idx);
        int delElement = array[idx];
        for (int i = idx; i < length - 1; i++) {
            array[idx] = array[idx + 1];
            array[length - 1] = 0;
        }
        length--;
        return delElement;

    }

    @Override
    int get(int idx) throws NoSuchElementException {
        checkIndex(idx);
        return array[idx];

    }
}
