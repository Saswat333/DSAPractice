package stack;

import java.util.Stack;

public class QueueUsingStack {
    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public QueueUsingStack(){
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void enQueue(int i) {
        stack1.push(i);
    }

    public int deQueue() {
        int result=0;
        //if both stack are empty

        //move element from stack1 to stack2 only if stack2 is empty
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                result = stack1.pop();
                stack2.push(result);
            }
        }
        if(!stack2.isEmpty())
            result = stack2.pop();
        return result;
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