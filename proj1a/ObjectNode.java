public class ObjectNode<T> {
    public T item;
    public ObjectNode next;
    public ObjectNode previous;

    public ObjectNode(T i, ObjectNode previous, ObjectNode next) {
        item = i;
        this.next = next;
        this.previous = previous;
    }
}