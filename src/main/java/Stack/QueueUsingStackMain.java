package Stack;

public class QueueUsingStackMain {
    public static void main(String[] args) {
        QueueUsingStack que = new QueueUsingStack();
        que.enQueue(5);
        que.enQueue(2);
        que.enQueue(4);
        System.out.println(que.deQueue());
    }

}