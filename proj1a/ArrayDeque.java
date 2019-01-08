public class ArrayDeque<T> {

    private int size;
    private int nextFirst;
    private int nextLast;
    public T[] items;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 3;
        nextLast = 4;
        size = 0;
    }

    public ArrayDeque(T firstItem) {
        items = (T[]) new Object[8];
        items[3] = firstItem;
        nextFirst = 2;
        nextLast = 4;
        size = 1;
    }

    public T[] getAll(T[] newArray) {
        int count = 0;
        if (nextFirst < nextLast) {
            for (int i = nextFirst + 1; i < nextLast; i++) {
                newArray[count] = items[i];
                count ++;
            }
        } else {
            for (int i = nextFirst + 1; i < items.length; i++) {
                newArray[count] = items[i];
                count ++;
            }
            for (int i = 0; i < nextLast; i++) {
                newArray[count] = items[i];
                count ++;
            }
        }
        return newArray;
    }

    public void expand() {
        int lastIndex = items.length * 2 - 1;
        T[] newArray = (T[]) new Object[lastIndex + 1];
        items = this.getAll(newArray);
        nextFirst = lastIndex;
        nextLast = size;
    }

    public void shorten() {
        T[] newArray = (T[]) new Object[items.length / 2];
        items = this.getAll(newArray);
        nextFirst = items.length - 1;
        nextLast = size;
    }

    public void checkSize() {
        if (size >= items.length - 1) {
            this.expand();
        } else if (size <= items.length / 4 && items.length > 8) {
            this.shorten();
        }
    }

    public void addFirst(T item) {
        items[nextFirst] = item;
        size++;

        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst--;
        }
        checkSize();
    }

    public void addLast(T item) {
        items[nextLast] = item;
        size++;

        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast++;
        }
        checkSize();
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (nextFirst < nextLast) {
            for (int i = nextFirst + 1; i < nextLast; i++) {
                System.out.print(items[i] + " ");
            }
        } else {
            for (int i = nextFirst + 1; i < items.length; i++) {
                System.out.print(items[i] + " ");
            }
            for (int i = 0; i < nextLast; i++) {
                System.out.print(items[i] + " ");
            }
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (nextFirst == items.length - 1) {
            nextFirst = 0;
        } else {
            nextFirst++;
        }
        size--;
        checkSize();
        return items[nextFirst];
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (nextLast == 0) {
            nextLast = items.length - 1;
        } else {
            nextLast--;
        }
        size--;
        checkSize();
        return items[nextLast];
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int newIndex = index + nextFirst + 1;
        if (newIndex < items.length) {
            return items[newIndex];
        } else {
            return items[newIndex - items.length];
        }
    }


}
