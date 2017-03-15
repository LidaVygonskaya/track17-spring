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
    private static final int defaultSize = 10;
    private int[] array;

    public MyArrayList() {
        this(defaultSize);
    }

    public MyArrayList(int capacity) {
        array = new int[capacity];
    }

    @Override
    void add(int item) {
        if (length >= array.length) {
            int[] newArray = new int[array.length + defaultSize];
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
        for (int k = idx; k < length - 1; k++) {
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
