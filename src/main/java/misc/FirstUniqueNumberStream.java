package misc;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class FirstUniqueNumberStream {
    HashSet<Integer> hashSet = new HashSet<>();
    LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();

    public FirstUniqueNumberStream() {
    }

    public FirstUniqueNumberStream(int[] nums) {
        for(int num: nums){
            add(num);
        }
    }

    private void add(int value) {
        if(hashSet.add(value)){
            //if true: means the there is only one unique value now, add to unique list
            linkedHashSet.add(value);
        }
        else{
            //if false: means there is duplicate elem in set already
            linkedHashSet.remove(value);
        }
    }

    private int getFirstUnique() {
        if(linkedHashSet.isEmpty()){
            return -1;
        }
        return linkedHashSet.iterator().next();
    }

    public static void main(String[] args) {
        FirstUniqueNumberStream stream = new FirstUniqueNumberStream();
        stream.add(2);
        System.out.println(stream.getFirstUnique()); //2
        stream.add(2);
        System.out.println(stream.getFirstUnique()); // null
        stream.add(3);
        System.out.println(stream.getFirstUnique()); // 3
        stream.add(4);
        System.out.println(stream.getFirstUnique()); // 3
        stream.add(3);
        System.out.println(stream.getFirstUnique()); // 4

//        Time complexity:
//        add()
//        showfirstUnique()
//        both operation are O(1)
    }
}

/*
Leetcode-1429 (Premium)
You have a queue of integers, you need to retrieve the first unique integer in the queue.

Implement the FirstUnique class:

FirstUnique(int[] nums) Initializes the object with the numbers in the queue.
int showFirstUnique() returns the value of the first unique integer of the queue, and returns -1 if there is no such integer.
void add(int value) insert value to the queue.

Input:
["FirstUnique","showFirstUnique","add","showFirstUnique","add","showFirstUnique","add","showFirstUnique"]
[[[2,3,5]],[],[5],[],[2],[],[3],[]]
Output:
[null,2,null,2,null,3,null,-1]
Explanation:
FirstUnique firstUnique = new FirstUnique([2,3,5]);
firstUnique.showFirstUnique(); // return 2
firstUnique.add(5);            // the queue is now [2,3,5,5]
firstUnique.showFirstUnique(); // return 2
firstUnique.add(2);            // the queue is now [2,3,5,5,2]
firstUnique.showFirstUnique(); // return 3
firstUnique.add(3);            // the queue is now [2,3,5,5,2,3]
firstUnique.showFirstUnique(); // return -1


TIME COMPLEXITY:
add()
showfirstUnique()
both operation are O(1)


Can be solved only using linkedHashSet

public FirstUnique(int[] nums) {
    set = new LinkedHashSet<>();
    for (int num : nums) {
        add(num);
    }
}

public int showFirstUnique() {
    if (set.isEmpty()) {
        return -1;
    }
    return set.iterator().next();
}

public void add(int value) {
    if (set.contains(value)) {
        set.remove(value);
    } else {
        set.add(value);
    }
}
* */