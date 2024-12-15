package pointers;

import java.util.HashMap;

public class Pointers {
    public static void main(String[] args) {
        HashMap<Integer,String> map1 = new HashMap<>();
        HashMap<Integer,String> map2= new HashMap<>();

       map1.put(1,"A");
       map2=map1;
       map1.put(1,"B");

        System.out.printf(String.valueOf(map1));
        System.out.printf(String.valueOf(map2));
    }
}
