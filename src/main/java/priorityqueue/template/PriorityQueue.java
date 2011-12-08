package priorityqueue.template;

import java.util.ArrayList;
import java.util.List;

public class PriorityQueue {
    private List<Node> heap = new ArrayList<Node>();

    public class Node {
        public Object object;
        public int priority;

        public Node(Object object, int priority) {
            this.object = object;
            this.priority = priority;
        }
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public List<Node> heap() {
        return heap;
    }

    public Object poll() {
        if (heap.size() == 1) {
            return heap().remove(0).object;
        }
        Object result = heap.get(0).object;
        reheapify();
        return result;
    }

    private void reheapify() {
        moveLastToFirstSlot();
        int i = 0;
        while (priorityLessThanEitherChild(i)) {
            int child = highestPriorityChild(i);
            swap(i, child);
            i = child;
        }
    }

    private void moveLastToFirstSlot() {
        Node rightmost = heap.remove(heap.size() - 1);
        heap.set(0, rightmost);
    }

    private boolean priorityLessThanEitherChild(int index) {
        int priority = heap.get(index).priority;
        return priority < leftPriority(index) ||
               priority < rightPriority(index);
    }

    private int leftPriority(int index) {
        return priority(leftChild(index));
    }

    public static int leftChild(int index) {
        return index * 2 + 1;
    }

    private int priority(int index) {
        if (index > heap.size() - 1) {
            return Integer.MIN_VALUE;
        }
        return heap.get(index).priority;
    }

    private int rightPriority(int index) {
        return priority(rightChild(index));
    }

    public static int rightChild(int index) {
        return index * 2 + 2;
    }

    private int highestPriorityChild(int index) {
        return leftPriority(index) > rightPriority(index)
                ? leftChild(index)
                : rightChild(index);
    }

    public void add(Object item, int priority) {
        addNewNode(item, priority);
        siftUpFromLast(priority);
    }

    private boolean addNewNode(Object item, int priority) {
        return heap.add(new Node(item, priority));
    }

    private void siftUpFromLast(int priority) {
        int current = heap.size() - 1;
        while (priority > parentPriority(current)) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    private int parentPriority(int current) {
        return heap.get(parent(current)).priority;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private void swap(int x, int y) {
        Node temp = heap.get(x);
        heap.set(x, heap.get(y));
        heap.set(y, temp);
    }
}
