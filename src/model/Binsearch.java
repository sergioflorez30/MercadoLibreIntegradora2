package model;

import java.util.ArrayList;

public class Binsearch {

    public Binsearch() {

    }

    public int binsearchabb(ArrayList<Product> arr, String goal) {
        int begin = 0;
        int end = arr.size() - 1;
        while (begin <= end) {
            int mid = (end + begin) / 2;
            String midValue = arr.get(mid).getName();
            if (midValue.equals(goal)) {
                return mid;
            } else if (goal.compareTo(midValue) > 0) {
                begin = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

}
