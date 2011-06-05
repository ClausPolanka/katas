package stack.template;

public class BoundedStack implements Stack {

    private int size;
    private int capacity;
    private int[] elements;

    public static Stack Make(int capacity) {
        if (capacity < 0) {
            throw new IllegalCapacity();
        }
        if (capacity == 0) {
            return new ZeroCapacityStack();
        }
        return new BoundedStack(capacity);
    }

    private BoundedStack(int capacity) {
        this.capacity = capacity;
        elements = new int[this.capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void push(int element) {
        if (size == capacity) {
            throw new Overflow();
        }
        this.elements[size++] = element;
    }

    public int pop() {
        if (isEmpty()) {
            throw new Underflow();
        }
        return elements[--size];
    }

    public int top() {
        if (isEmpty()) {
            throw new Empty();
        }
        return elements[size - 1];
    }

    public Integer find(int element) {
        int indexOfLastElement = size - 1;
        for (int i = indexOfLastElement; i >= 0; i--) {
            if (elements[i] == element) {
                return indexOfLastElement - i;
            }
        }
        return null;
    }

    private static class ZeroCapacityStack implements Stack {
        public boolean isEmpty() {
            return true;
        }

        public int getSize() {
            return 0;
        }

        public void push(int element) {
            throw new Overflow();
        }

        public int pop() {
            throw new Underflow();
        }

        public int top() {
            throw new Empty();
        }

        public Integer find(int element) {
            return null;
        }
    }

    public static class Overflow extends RuntimeException {
    }

    public static class Underflow extends RuntimeException {
    }

}
