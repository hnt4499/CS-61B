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
        System.out.println();
    }

    private void printDeque(ObjectNode<T> objectNode) {
        if (objectNode.item == null) {
            return;
        }
        System.out.print(objectNode.item + " ");
        printDeque(objectNode.next);
    }

    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        ObjectNode<T> itemToReturn = sentinel.next;
        sentinel.next.next.previous = sentinel;
        sentinel.next = sentinel.next.next;
        size --;
        return itemToReturn.item;

    }

    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        ObjectNode<T> itemToReturn = sentinel.previous;
        sentinel.previous.previous.next = sentinel;
        sentinel.previous = sentinel.previous.previous;
        size --;
        return itemToReturn.item;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        ObjectNode<T> pointer = sentinel.next;

        for (int i = 0; i < index; i++) {
            pointer = pointer.next;
        }
        return pointer.item;
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursive(index, sentinel.next);
    }

    private T getRecursive(int index, ObjectNode pointer) {
        if (index == 0) {
            return (T) pointer.item;
        }
        return getRecursive(index - 1, pointer.next);
    }
}
