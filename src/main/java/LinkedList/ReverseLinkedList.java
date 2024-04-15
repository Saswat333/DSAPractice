package LinkedList;

public class ReverseLinkedList {
    static Node head;

    static class Node{
        int data;
        Node next;

        Node(int d){
            this.data = d;
            this.next = null;
        }

    }

    static Node reverseLLI(Node node){
        Node prev = null;
        Node curr = node;
        Node next = null;


        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        node = prev;
        return node;

    }

    static Node reverseLLR(Node node){
        if(node == null)
            return null;
        if(node.next == null){
            head = node;
            return node;
        }
        Node temp = reverseLLR(node.next);
        temp.next = node;
        node.next = null;
        return node;
    }

    static void push(int d){
        Node temp = new Node(d);
        temp.next = head;
        head = temp;
    }
    static void printLL(Node node){
        Node temp = node;
        while(temp != null){
            System.out.print(temp.data+ " ->");
            temp = temp.next;
        }
        System.out.println("");
    }

    public static void main(String[] args){

        push(20);
        push(4);
        push(15);
        push(85);

        System.out.println("Given linked list");
        printLL(head);

//        head = reverseLLI(head);
        reverseLLR(head);
        System.out.println("");
        System.out.println("Reversed linked list");
        printLL(head);
    }
}
