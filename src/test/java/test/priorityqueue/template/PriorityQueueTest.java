package test.priorityqueue.template;

import org.junit.Ignore;
import org.junit.Test;
import priorityqueue.template.MyPriorityQueue;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PriorityQueueTest {

    private static final Object ITEM_50 = "50";
    private static final Object ITEM_100 = "100";

    @Test
    public void emptyWhenCreated() throws Exception {
        MyPriorityQueue queue = new MyPriorityQueue();
        assertThat(queue.isEmpty(), is(true));
    }

    @Test
    public void noLongerEmptyAfterAdd() throws Exception {
        MyPriorityQueue queue = new MyPriorityQueue();
        queue.add("50", 50);
        assertThat("Queue is empty", queue.isEmpty(), is(false));
    }

    @Test
    public void singletonQueueReturnsSoleItemOnPoll() throws Exception {
        MyPriorityQueue queue = new MyPriorityQueue();
        queue.add(ITEM_50, 50);
        assertThat("Sole item", queue.poll(), is(ITEM_50));
    }

    @Test
    public void isEmptyAfterSoleElementRemoved() throws Exception {
        MyPriorityQueue queue = new MyPriorityQueue();
        queue.add(ITEM_50, 50);
        queue.poll();
        assertThat("Queue is empty", queue.isEmpty(), is(true));
    }

    @Test
    @Ignore
    public void returnsFIFOWithTwoAlreadyOrderedItems() throws Exception {
        MyPriorityQueue queue = new MyPriorityQueue();
        queue.add(ITEM_100, 100);
        queue.add(ITEM_50, 50);
        assertThat("Item", queue.poll(), is(ITEM_100));
        assertThat("Item", queue.poll(), is(ITEM_50));
        assertThat("Queue is empty", queue.isEmpty(), is(true));
    }
}
