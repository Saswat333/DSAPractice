//package LinkedList;
//
//public class FindMiddle {
//
//    static Node head;
//
//    static class Node{
//        int data;
//        Node next;
//
//        Node(int d){
//            this.data = d;
//            this.next = null;
//        }
//
//    }
//
//    static Node findMiddleElem(Node head){
//        Node slow = head; Node fast = head;
//
//        while(fast != null && fast.next != null){
//            slow = slow.next;
//            fast = fast.next.next;
//        }
//
//        return slow;
//    }
//
//    static void push(int d){
//        Node temp = new Node(d);
//        temp.next = head;
//        head = temp;
//    }
//    static void printLL(Node node){
//        Node temp = node;
//        while(temp != null){
//            System.out.print(temp.data+ " ->");
//            temp = temp.next;
//        }
//        System.out.println("");
//    }
//
//    public static void main(String[] args){
//
//        push(20);
//        push(4);
//        push(15);
//        push(85);
//
//        System.out.println("Given linked list");
//        printLL(head);
//
//        findMiddleElem(head);
//        System.out.println("");
//        System.out.println("Reversed linked list");
//        printLL(head);
//    }
//}
