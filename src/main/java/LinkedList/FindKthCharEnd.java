package LinkedList;

public class FindKthCharEnd {

    //N = length of LL, K= kth node from end
    public void findKthChar(Node head, int N, int K){
        Node fast = head, slow = head;
        //because we have to reach slow pointer before the last kth node we can use
        // fast.next==null instead of fast==null as exit condition
        int count = 0;
        if(head==null)
            System.out.println("No elem");
        while(count<K){
            fast=fast.next;
            count++;
        }
        //Edge case: when kth node is the size of LL then the first node of the LL is the result
        // we can't return fast.next here as we will be at the enf of LL
        if(fast==null)
            System.out.println(head.val);
        // as both the pointer are in correct position here we can move forward together
        while(fast!=null){
            fast = fast.next;
            slow = slow.next;
        }
        //remove kth node from end
//        slow.next = slow.next.next;
        System.out.println(slow.next.val);
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(5);
        head.next.next = new Node(2);
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(8);
        int N = 5;
        System.out.print("Original Linked List: ");
        head.printLinkedList(head);

        FindKthCharEnd obj = new FindKthCharEnd();
        int K = 5;
        obj.findKthChar(head, N, K);
    }
}
