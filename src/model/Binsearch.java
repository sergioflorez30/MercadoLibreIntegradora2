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
    public int binsearchabbRangePrice(ArrayList<Order> arr, double goal) {
        int begin = 0;
        int end = arr.size() - 1;
        while (begin <= end) {
            int mid = (end + begin) / 2;
            double midValue = arr.get(mid).getPrice();
            if (midValue >= goal) {
                if (mid == 0 || arr.get(mid - 1).getPrice() < goal) {
                    return mid;
                } else {
                    end = mid - 1;
                }
            } else {
                begin = mid + 1;
            }
        }
        return -(begin + 1); // Retorna el complemento a 2 de la posición donde debería estar el elemento
    }
    public int binsearchabbPref(ArrayList<Order> arr, String goal, boolean isStart) {
        int begin = 0;
        int end = arr.size() - 1;
        int result = -1;
        while (begin <= end) {
            int mid = (end + begin) / 2;
            String midValue = arr.get(mid).getNameBuyer();
            if (midValue.toLowerCase().startsWith(goal.toLowerCase())) {
                result = mid;
                if (isStart) {
                    end = mid - 1;
                } else {
                    begin = mid + 1;
                }
            } else if (goal.compareTo(midValue) > 0) {
                begin = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        if (result < 0) {
            result = -(begin + 1);
        }
        return result;
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
