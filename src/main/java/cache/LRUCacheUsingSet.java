package cache;

import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class LRUCacheUsingSet {
    Deque<Integer> que;
    HashSet<Integer> set;
    private final int CACHE_SIZE ;

    //constructor
    public LRUCacheUsingSet(int capacity){
        que = new LinkedList<>();
        set = new HashSet<>();
        CACHE_SIZE = capacity;
    }

    public void pageCache(int page){
        if(!set.contains(page)){
            //if page is no there. check for cache size if we have to remove
            if(que.size() == CACHE_SIZE){
                int temp = que.removeLast();
                set.remove(temp);
            }
        }
        else{
            //if page already there in the memory, we have to still rearrange and put it in the front
            que.remove(page);
        }
        que.push(page);
        set.add(page);
    }

    public void display(){
        Iterator<Integer> itr = que.iterator();
        while(itr.hasNext()){
            System.out.print(itr.next()+"  ");
        }
    }

    public static void main(String[] args) {
        LRUCacheUsingSet cache = new LRUCacheUsingSet(4);
        cache.pageCache(1);
        cache.pageCache(2);
        cache.pageCache(3);
        cache.pageCache(1);
        cache.pageCache(4);
        cache.pageCache(5);
        cache.pageCache(2);
        cache.pageCache(2);
        cache.pageCache(1);
        cache.display();
    }
}
