package builtinfunction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import collections.Array;

public class MapPractices {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Barry");
        names.add("Allen");
        names.add("UnKnow");
        List<Integer> ages = new ArrayList<>();
        ages.add(16);
        ages.add(17);
        ages.add(18);

        Map<String, Integer> newMap = new HashMap<>();
        for (int i = 0; i < names.size(); i++) {
            newMap.put(names.get(i), ages.get(i));
        }

        List<String> showList = new ArrayList<>();
        for (Map.Entry<String, Integer> stringIntegerEntry : newMap.entrySet()) {
            String result = String.format("name is %s age is %d", stringIntegerEntry.getKey(), stringIntegerEntry.getValue());
            showList.add(result);
        }

        for (int i = 0; i < showList.size(); i++) {
            System.out.println(showList.get(i));
        }
    }
}
