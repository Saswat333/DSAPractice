package cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheMain {
    LinkedNodeList list;
    Map<Integer, NodeDLL> map;
    Integer cap;

    LRUCacheMain(int capacity){
        map = new HashMap<>(capacity);
        list = new LinkedNodeList();
        cap = capacity;
    }

    public int get(int key){
        //takes key and returns the value, also moved it to head as its is recently visited
        NodeDLL node = map.get(key);
        if(node == null){
            return -1;
        }
        list.moveToHead(node);
        return node.val;
    }

    public void put(int key, int value){
        // takes key value pair and saves it in cache
        //first check if the map do not have the key then add it in head
        //if the map has the key:
        //      check if the capacity is full or not,
        NodeDLL node = map.get(key);
        if(node != null){
            list.moveToHead(node);
            node.val = value;
        }
        else{
            NodeDLL newNode = new NodeDLL(key, value);
            if(map.size() == cap){
                //remove the least recently used element from tail
                NodeDLL tail = list.getTail();
                map.remove(tail.key);
                list.removeTail();
            }
            map.put(key, newNode);
            list.addToHead(newNode);
        }
    }
}
