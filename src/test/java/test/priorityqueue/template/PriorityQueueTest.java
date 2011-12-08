package test.priorityqueue.template;

import org.junit.Before;
import org.junit.Test;
import priorityqueue.template.PriorityQueue;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static priorityqueue.template.PriorityQueue.leftChild;
import static priorityqueue.template.PriorityQueue.rightChild;

public class PriorityQueueTest {

    private PriorityQueue queue;

    @Before
    public void create() throws Exception {
        queue = new PriorityQueue();
    }

    @Test
    public void emptyWhenCreated() throws Exception {
        assertThat(queue.isEmpty(), is(true));
    }

    @Test
    public void noLongerEmptyAfterAdd() throws Exception {
        queue.add("", 50);
        assertThat("Queue is empty", queue.isEmpty(), is(false));
    }

    @Test
    public void singletonQueueReturnsSoleItemOnPoll() throws Exception {
        queue.add("abc", 50);
        assertThat("Sole item", queue.poll(), is((Object) "abc"));
    }

    @Test
    public void isEmptyAfterSoleElementRemoved() throws Exception {
        queue.add("", 50);
        queue.poll();
        assertThat("Queue is empty", queue.isEmpty(), is(true));
    }

    @Test
    public void returnsFIFOWithTwoAlreadyOrderedItems() throws Exception {
        add(50, 100);
        assertQueuePollingOrder(100, 50);
        assertValid(queue.heap());
    }

    @Test
    public void returnsTwoUnorderedItemsInPriorityOrder() throws Exception {
        add(50, 100);
        assertQueuePollingOrder(100, 50);
        assertValid(queue.heap());
    }

    @Test
    public void heapifiesThreeElements() throws Exception {
        add(50, 75, 100);
        assertValid(queue.heap());
    }

    @Test
    public void heapifiesMultipleLevels() throws Exception {
        add(50, 75, 100, 125);
        assertValid(queue.heap());
    }

    @Test
    public void removeAnswerHighest() throws Exception {
        add(20, 30, 40, 100, 50, 60);
        assertThat("Root Node", queue.poll(), is((Object) "100"));
    }

    @Test
    public void removeReheapifiesTwoItems() throws Exception {
        add(20, 30, 40);
        queue.poll();
        assertValid(queue.heap());
    }

    @Test
    public void removeReheapifiyMustConsiderBothChildren() throws Exception {
        add(40, 20, 30, 10);
        queue.poll();
        assertValid(queue.heap());
    }

    @Test
    public void removeMustReheapifyMultipleLevels() throws Exception {
        add(50, 40, 20, 30, 10);
        queue.poll();
        assertValid(queue.heap());
    }

    @Test
    public void pollExhaustsHeapInPriorityOrder() throws Exception {
        add(40, 20, 60, 80, 10, 30, 90, 70, 50);
        assertQueuePollingOrder(90, 80, 70, 60, 50, 40, 30, 20, 10);
    }

    @Test
    public void wikipediaIntegrationTest() throws Exception {
        add(1, 2, 3, 7, 17, 19, 25, 36, 100);
        assertValid(queue.heap());
    }

    private void assertQueuePollingOrder(Integer... expectedItems) {
        List<Integer> items = new ArrayList<Integer>();
        while (!queue.isEmpty()) {
            items.add(parseInt((String) queue.poll()));
        }
        assertThat("Items", items, is(asList(expectedItems)));
    }

    private void add(int... priorities) {
        for (int p : priorities) {
            queue.add(valueOf(p), p);
        }
    }

    private void assertValid(List<PriorityQueue.Node> heap) {
        assertSubheap(heap, MAX_VALUE, 0, "Root Priority higher than node priority");
    }

    private void assertSubheap(List<PriorityQueue.Node> heap, int priority, int root, String reason) {
        if (root > heap.size() - 1) {
            return;
        }
        PriorityQueue.Node node = heap.get(root);
        assertThat(reason, priority > node.priority, is(true));

        assertSubheap(heap, node.priority, leftChild(root),
                "Left Subtree Root Priority higher than node priority");
        assertSubheap(heap, node.priority, rightChild(root),
                "Right Subtree Root Priority higher than node priority");
    }

}
