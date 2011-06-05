package test.stack.template;

import org.junit.Before;
import org.junit.Test;
import stack.template.BoundedStack;
import stack.template.Stack;

import static org.junit.Assert.*;

public class StackTest {
    private Stack stack;

    @Before
    public void setUp() throws Exception {
        stack = BoundedStack.Make(2);
    }

    @Test
    public void NewlyCreateStack_ShouldBeEmpty() throws Exception {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.getSize());
    }

    @Test
    public void AfterOnePush_StackSizeShouldBeOne() throws Exception {
        stack.push(1);
        assertEquals(1, stack.getSize());
        assertFalse(stack.isEmpty());
    }

    @Test
    public void AfterOnePushAndOnePop_ShouldBeEmpty() throws Exception {
        stack.push(1);
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test(expected = BoundedStack.Overflow.class)
    public void WhenPushedPastLimit_StackOverflows() throws Exception {
        stack.push(1);
        stack.push(1);
        stack.push(1);
    }

    @Test(expected = BoundedStack.Underflow.class)
    public void WhenEmptyStackIsPopped_ShouldThrowUnderflow() throws Exception {
        stack.pop();
    }

    @Test
    public void WhenOneIsPushed_OneIsPopped() throws Exception {
        stack.push(1);
        assertEquals(1, stack.pop());
    }

    @Test
    public void WhenOneAndTwoArePushed_TwoAndOneArePopped() throws Exception {
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test(expected = BoundedStack.IllegalCapacity.class)
    public void WhenCreatingStackWithNegativeSize_ShouldThrowIllegalCapacity() throws Exception {
        BoundedStack.Make(-1);
    }

    @Test(expected = BoundedStack.Overflow.class)
    public void WhenCreatingStackWithZeroCapacity_AnyPushShouldOverflow() throws Exception {
        Stack stack = BoundedStack.Make(0);
        stack.push(1);
    }

    @Test
    public void WhenOneIsPushed_OneShouldBeOnTop() throws Exception {
        stack.push(1);
        assertEquals(1, stack.top());
    }

    @Test(expected = Stack.Empty.class)
    public void WhenStackIsEmpty_TopThrowsEmpty() throws Exception {
        stack.top();
    }

    @Test(expected = Stack.Empty.class)
    public void WithZeroCapacityStack_TopThrowsEmpty() throws Exception {
        Stack stack = BoundedStack.Make(0);
        stack.top();
    }

    @Test
    public void GivenStackWithOneAndTwoPushed_FindOneAndTwo() throws Exception {
        stack.push(1);
        stack.push(2);
        int indexOfOne = stack.find(1);
        int indexOfTwo = stack.find(2);
        assertEquals(1, indexOfOne);
        assertEquals(0, indexOfTwo);
    }

    @Test
    public void GivenAStickWithNoTwo_FindTwoShouldReturnNull() throws Exception {
        assertNull(stack.find(2));
    }
    @Test
    public void GivenAStickWithZeroCapacity_FindTwoShouldReturnNull() throws Exception {
        Stack stack = BoundedStack.Make(0);
        assertNull(stack.find(2));
    }
}
