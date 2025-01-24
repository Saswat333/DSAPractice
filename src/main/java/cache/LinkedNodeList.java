package cache;

public class LinkedNodeList {
    NodeDLL dummyHead;
    NodeDLL dummyTail;

    LinkedNodeList(){
        dummyHead = new NodeDLL(0,0);
        dummyTail = new NodeDLL(0,0);
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    void addToHead(NodeDLL node){
        NodeDLL temp = dummyHead.next; //temp holds tail section
        dummyHead.next = node;
        node.next = temp; //attach the tail to node
        node.prev = dummyHead;
        temp.prev = node;
    }

    void moveToHead(NodeDLL node){
        node.prev.next = node.next;
        node.next.prev = node.prev;//top2 line to remove node from its place
        addToHead(node);
    }

    void removeTail(){
        NodeDLL newTail = dummyTail.prev.prev;
        newTail.next = dummyTail;
        dummyTail.prev = newTail;
    }

    NodeDLL getTail(){
        return dummyTail.prev;
    }
}
