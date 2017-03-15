package track.lessons.lesson3;

/**
 * Created by PC on 15.03.2017.
 */
public interface Stack {
    // Стек - структура данных, удовлетворяющая правилу Last IN First OUT
    void push(int value); // положить значение наверх стека

    int pop(); // вытащить верхнее значение со стека
}
