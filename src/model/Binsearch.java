package model;

import java.time.LocalTime;
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
            if (midValue.equalsIgnoreCase(goal)) {
                return mid;
            } else if (goal.compareTo(midValue) > 0) {
                begin = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }
    public int binsearchabbO(ArrayList<Order> arr, String goal) {
        int begin = 0;
        int end = arr.size() - 1;
        while (begin <= end) {
            int mid = (end + begin) / 2;
            String midValue = arr.get(mid).getNameBuyer();
            if (midValue.equalsIgnoreCase(goal)) {
                return mid;
            } else if (goal.compareTo(midValue) > 0) {
                begin = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }
    public  int binarySearchOrderPrice(ArrayList<Order> arr, double goal) {
        int left = 0;
        int right = arr.size() - 1;
    
        while (left <= right) {
            int mid = (left + right) / 2;
            double midPrice = arr.get(mid).getPrice();
    
            if (midPrice == goal) {
                return mid;
            } else if (midPrice < goal) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    
        return -1; 
    }
    public int binSearchOrderByTime(ArrayList<Order> orders, LocalTime targetTime) {
        int begin = 0;
        int end = orders.size() - 1;
    
        while (begin <= end) {
            int mid = (begin + end) / 2;
            LocalTime midTime = orders.get(mid).getDateBuy();
            if (midTime.equals(targetTime)) {
                return mid; 
            } else if (midTime.isBefore(targetTime)) {
                begin = mid + 1; 
            } else {
                end = mid - 1; 
            }
        }
    
        return -1; 
    }
    
    

}
