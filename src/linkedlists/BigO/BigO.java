package linkedlists.BigO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class BigO {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();


        for (int i = 0; i < 1000000 ; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }
        long start = System.nanoTime();
        arrayList.remove(arrayList.size()-1);
        long end = System.nanoTime();
        long duration = end - start;
        System.out.println(duration + " Time to delete an element in arraylist");

        start = System.nanoTime();
        linkedList.removeLast();
        end = System.nanoTime();
        duration = end - start;
        System.out.println(duration + " Time to remove an element in linkedlist");



    }

}
