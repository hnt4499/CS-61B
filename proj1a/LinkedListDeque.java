public class LinkedListDeque<T> {

    private ObjectNode<T> sentinel;
    private int size;

    public LinkedListDeque(T x) {
        size = 1;
        ObjectNode<T> newNode = new ObjectNode<>(x, null, null);
        sentinel = new ObjectNode<>(null, newNode, newNode);
        newNode.previous = sentinel;
        newNode.next = sentinel;
    }

    public LinkedListDeque() {
        size = 0;
        sentinel = new ObjectNode<>(null, null, null);
        sentinel.next = sentinel;
        sentinel.previous = sentinel;
    }

    public void addFirst(T item) {
        ObjectNode<T> newNode = new ObjectNode<>(item, sentinel, sentinel.next);
        sentinel.next.previous = newNode;
        sentinel.next = newNode;
        size ++;
    }
    
    public void addLast(T item) {
        ObjectNode<T> newNode = new ObjectNode<>(item, sentinel.previous, sentinel);
        sentinel.previous.next = newNode;
        sentinel.previous = newNode;
        size ++;
    }

    public boolean isEmpty() {
        if (sentinel.next.item == null) {
            return true;
        } return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        printDeque(sentinel.next);
    }

    private void printDeque(ObjectNode objectNode) {
        if (objectNode.item == null) {
            return;
        }
        System.out.print(objectNode.item + " ");
        printDeque(objectNode.next);
    }
}
