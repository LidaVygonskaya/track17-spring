package track.lessons.lesson3;

/**
 * Created by PC on 15.03.2017.
 */
public interface Queue {
    // Очередь - структура данных, удовлетворяющая правилу First IN First OUT
    void enqueue(int value); // поместить элемент в очередь

    int dequeu(); // вытащить первый элемент из очереди
}
