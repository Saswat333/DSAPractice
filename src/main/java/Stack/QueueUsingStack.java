package Stack;

import java.util.Stack;

public class QueueUsingStack {
    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public QueueUsingStack(){
        stack1 = new Stack<>();
        stack2 = new Stack<>();


    }

    public void enQueue(int i) {

    }

    public int deQueue() {
        return 0;
    }
}
/*
* Enqueue:
*   - if there is element in stack1 then push all those elem to stack2
*   - add the new element to the stack1 and then push back all stack2 elem to stack1
*   - time = o(n)
*
* Dequeue:
*   - just return top of the element
*   - o(1)
* */