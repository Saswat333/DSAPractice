package Strings;

import java.util.*;

public class GroupAnagramQuery {
    public static List<List<String>> findAnagrams(List<String> strs, List<String> queries) {
        // Step 1: Create a map to group anagrams
        Map<String, List<String>> anagramMap = new HashMap<>();

        // Step 2: Populate the map with sorted string keys
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sortedStr = new String(charArray);
            anagramMap.putIfAbsent(sortedStr, new ArrayList<>());
            anagramMap.get(sortedStr).add(str);
        }

        // Step 3: Prepare the result for the queries
        List<List<String>> result = new ArrayList<>();
        for (String query : queries) {
            char[] charArray = query.toCharArray();
            Arrays.sort(charArray);
            String sortedQuery = new String(charArray);
            // Get the list of anagrams for the query
            List<String> anagrams = anagramMap.getOrDefault(sortedQuery, new ArrayList<>());
            result.add(anagrams);
        }

        return result;
    }

    public static void main(String[] args) {
        List<String> strs = Arrays.asList("eat", "tea", "tan", "ate", "nat", "bat");
        List<String> queries = Arrays.asList("ate", "nat", "bat", "cat");

        List<List<String>> anagramResults = findAnagrams(strs, queries);

        // Print results
        for (int i = 0; i < queries.size(); i++) {
            System.out.println("Anagrams of " + queries.get(i) + ": " + anagramResults.get(i));
        }
    }
}

