package track.lessons.lesson3;

import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 * Односвязный список
 */
public class MyLinkedList extends List implements Queue, Stack {

    @Override
    public void push(int value) {
        this.add(value);
    }

    @Override
    public int pop() {
        return this.remove(0);
    }

    @Override
    public void enqueue(int value) {
        this.add(value);
    }

    @Override
    public int dequeu() {
        return this.remove(length - 1);
    }

    /**
     * private - используется для сокрытия этого класса от других.
     * Класс доступен только изнутри того, где он объявлен
     * <p>
     * static - позволяет использовать Node без создания экземпляра внешнего класса
     */
    private Node head = null;
    private Node tail = null;

    private static class Node {
        Node prev;
        Node next;
        int val;

        Node(Node prev, Node next, int val) {
            this.prev = prev;
            this.next = next;
            this.val = val;
        }
    }



    @Override
    void add(int item) {
        if (length != 0) {
            tail = new Node(tail, null, item);
            tail.prev.next = tail;

        } else {
            head = new Node(null, null, item);
            tail = head;
        }
        length++;
    }

    @Override
    int remove(int idx) throws NoSuchElementException {
        checkIndex(idx);
        Node current = head;
        while (idx > 0) {
            current = current.next;
            idx--;
        }
        if ((current.prev == null) && (current.next != null)) {
            //Это первый элемент и остльные не ноль
            head = head.next;
            head.prev = null;
            length--;
            return current.val;
        } else if ((current.next == null) && (current.prev != null)) {
            //Это значит что это последний элемент и предыдущие не ноль
            tail = tail.prev;
            tail.next = null;
            length--;
            return current.val;
        } else if ((current.next == null) && (current.prev == null)) {
            //Единственный элемент в списке
            head = tail = null;
            length--;
            return current.val;
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
            length--;
            return current.val;

        }


    }




    @Override
    int get(int idx) throws NoSuchElementException {
        Node current = head;
        checkIndex(idx);
        while (idx > 0) {
            current = current.next;
            idx--;
        }
        return current.val;

    }
}
