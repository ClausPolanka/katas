package priorityqueue.template;

public class MyPriorityQueue {
    private boolean empty = true;
    private Object key;
    private int value;

    public boolean isEmpty() {
        return empty;
    }

    public void add(Object key, int value) {
        this.key = key;
        this.value = value;
        empty = false;
    }

    public Object poll() {
        empty = true;
        return key;
    }
}
