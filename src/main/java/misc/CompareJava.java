package misc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompareJava {

    public static void main(String[] args) {
        // creating a Stream of strings
        Stream<String> s = Stream.of("apple",
                "grapes",
                "banana",
                "geeky banana",
                "puffy potato",
                "garlic",
                "ginger",
        "& honey");

        // using Collectors toList() function
        List<String> myList = s.collect(Collectors.toList());

        // printing the elements
        System.out.println(myList);


        List<String> ls = Arrays.asList("apple",
                "geeky banana",
                "garlic",
                "& horrrible honey");

        System.out.println(ls);

        System.out.println(myList.containsAll(ls));
    }
}
