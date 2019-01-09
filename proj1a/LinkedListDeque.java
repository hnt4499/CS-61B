public class LinkedListDeque<T> {

    private ObjectNode<T> sentinel;
    private int size;


    public LinkedListDeque() {
        size = 0;
        sentinel = new ObjectNode<>(null, null, null);
        sentinel.setNext(sentinel);
        sentinel.setPrevious(sentinel);
    }

    public void addFirst(T item) {
        ObjectNode<T> newNode = new ObjectNode<>(item, sentinel, sentinel.getNext());
        sentinel.getNext().setPrevious(newNode);
        sentinel.setNext(newNode);
        size++;
    }

    public void addLast(T item) {
        ObjectNode<T> newNode = new ObjectNode<>(item, sentinel.getPrevious(), sentinel);
        sentinel.getPrevious().setNext(newNode);
        sentinel.setPrevious(newNode);
        size++;
    }

    public boolean isEmpty() {
        if (sentinel.getNext().getItem() == null) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        printDeque(sentinel.getNext());
        System.out.println();
    }

    private void printDeque(ObjectNode<T> objectNode) {
        if (objectNode.getItem() == null) {
            return;
        }
        System.out.print(objectNode.getItem() + " ");
        printDeque(objectNode.getNext());
    }

    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        ObjectNode<T> itemToReturn = sentinel.getNext();
        sentinel.getNext().getNext().setPrevious(sentinel);
        sentinel.setNext(sentinel.getNext().getNext());
        size--;
        return itemToReturn.getItem();

    }

    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        ObjectNode<T> itemToReturn = sentinel.getPrevious();
        sentinel.getPrevious().getPrevious().setNext(sentinel);
        sentinel.setPrevious(sentinel.getPrevious().getPrevious());
        size--;
        return itemToReturn.getItem();
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        ObjectNode<T> pointer = sentinel.getNext();

        for (int i = 0; i < index; i++) {
            pointer = pointer.getNext();
        }
        return pointer.getItem();
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursive(index, sentinel.getNext());
    }

    private T getRecursive(int index, ObjectNode pointer) {
        if (index == 0) {
            return (T) pointer.getItem();
        }
        return getRecursive(index - 1, pointer.getNext());
    }
}
