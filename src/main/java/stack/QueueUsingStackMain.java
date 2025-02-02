package stack;

public class QueueUsingStackMain {
    public static void main(String[] args) {
        QueueUsingStack que = new QueueUsingStack();
        que.enQueue(5);
        que.enQueue(2);
        que.enQueue(4);
        System.out.println(que.deQueue());
        que.enQueue(7);
        que.enQueue(6);
        System.out.println(que.deQueue());
    }

}
