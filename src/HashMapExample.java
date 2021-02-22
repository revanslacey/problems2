import java.io.*;
import java.util.*;

public class HashMapExample {
    public static void main(String[] args) {

        //  https://stackoverflow.com/questions/15797446/how-do-i-add-values-to-a-set-inside-a-map/15797532

        HashMap<String, Set<Integer>> reqdMap = new HashMap<String, Set<Integer>>();

        //Form the set corresponding to apple.
        HashSet<Integer> appleSet = new HashSet<Integer>();
        appleSet.add(1);
        appleSet.add(2);
        appleSet.add(4);
        appleSet.add(2);

        reqdMap.put("apple", appleSet);

        //To Retrieve
        System.out.println( reqdMap.get("apple") );

//        // https://www.tutorialspoint.com/java/java_set_interface.htm
//        int count[] = {34, 22,10,60,30,22};
//        Set<Integer> set = new HashSet<Integer>();
//        try {
//            for(int i = 0; i < 5; i++) {
//                set.add(count[i]);
//            }
//            System.out.println(set);
//
//            TreeSet sortedSet = new TreeSet<Integer>(set);
//            System.out.println("The sorted list is:");
//            System.out.println(sortedSet);
//
//            System.out.println("The First element of the set is: "+ (Integer)sortedSet.first());
//            System.out.println("The last element of the set is: "+ (Integer)sortedSet.last());
//        }
//        catch(Exception e) {}


    }
}
