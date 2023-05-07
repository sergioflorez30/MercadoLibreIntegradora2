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

    public int binarySearchOrderPrice(ArrayList<Order> arr, double goal) {
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

    public int binarySearchProductPrice(ArrayList<Product> arr, double goal) {
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

    public int binarySearchProductCategory(ArrayList<Product> arr, Category goal) {
        int left = 0;
        int right = arr.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            Category midCategory = arr.get(mid).getCategory();

            if (midCategory.equals(goal)) {
                return mid;
            } else if (midCategory.compareTo(goal) > 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public int binarySearchProductNumberB(ArrayList<Product> arr, int goal) {
        int left = 0;
        int right = arr.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            double midPrice = arr.get(mid).getNumber_bought();

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

    public int binsearchabbRangePriceIz(ArrayList<Order> arr, double goal) {
        int left = 0;
        int right = arr.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr.get(mid).getPrice() == goal) {
                while (mid > 0 && arr.get(mid-1).getPrice() == goal) {
                    mid--;
                }
                return mid;
            } else if (arr.get(mid).getPrice() < goal) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // Si no encontramos el valor, retornamos el complemento
        return -(left + 1);
    }

    public int binsearchabbRangePriceDe(ArrayList<Order> arr, double goal) {
        int left = 0;
        int right = arr.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr.get(mid).getPrice() == goal) {
                while (mid < arr.size()-1 && arr.get(mid+1).getPrice() == goal) {
                    mid++;
                }
                return mid;
            } else if (arr.get(mid).getPrice() < goal) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // Si no encontramos el valor, retornamos el complemento
        return -(left + 1);
    }

    public int binsearchabbPrefIz(ArrayList<Order> arr, String goal) {
        int begin = 0;
        int end = arr.size() - 1;
        int result = -1;
        while (begin <= end) {
            int mid = (end + begin) / 2;
            String midValue = arr.get(mid).getNameBuyer();
            if (midValue.toLowerCase().startsWith(goal.toLowerCase())) {
                while (mid > 0 && arr.get(mid-1).getNameBuyer().toLowerCase().startsWith(goal.toLowerCase())) {
                    mid--;
                }
                return mid;

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
    public int binsearchabbPrefDe(ArrayList<Order> arr, String goal1) {
        int begin = 0;
        int end = arr.size() - 1;
        int result = -1;
        while (begin <= end) {
            int mid = (end + begin) / 2;
            String midValue = arr.get(mid).getNameBuyer();
            if (midValue.toLowerCase().startsWith(goal1.toLowerCase())) {
                while (mid > 0 && arr.get(mid+1).getNameBuyer().toLowerCase().startsWith(goal1.toLowerCase())) {
                    mid--;
                }
                return mid;

            } else if (goal1.compareTo(midValue) > 0) {
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

}
