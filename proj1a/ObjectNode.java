public class ObjectNode<T> {
    private T item;
    private ObjectNode next;
    private ObjectNode previous;

    public ObjectNode(T i, ObjectNode previous, ObjectNode next) {
        item = i;
        this.next = next;
        this.previous = previous;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public ObjectNode getNext() {
        return next;
    }

    public void setNext(ObjectNode next) {
        this.next = next;
    }

    public ObjectNode getPrevious() {
        return previous;
    }

    public void setPrevious(ObjectNode previous) {
        this.previous = previous;
    }
}