/**
 * Train Consist Management App
 * Version: 2.0
 * Demonstrates UC1 to UC20
 */

import java.util.*;
import java.util.regex.*;

// Custom Exception UC14
class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String msg) {
        super(msg);
    }
}

// Runtime Exception UC15
class CargoSafetyException extends RuntimeException {
    public CargoSafetyException(String msg) {
        super(msg);
    }
}

// Bogie Class
class Bogie {
    String name;
    int capacity;
    String cargo;

    // Passenger Constructor
    public Bogie(String name, int capacity) throws InvalidCapacityException {
        if (capacity <= 0) {
            throw new InvalidCapacityException("Capacity must be greater than zero");
        }
        this.name = name;
        this.capacity = capacity;
    }

    // Goods Constructor
    public Bogie(String name, String cargo) {
        this.name = name;
        this.cargo = cargo;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getName() {
        return name;
    }

    public String getCargo() {
        return cargo;
    }

    public String toString() {
        return name + " (" + (capacity > 0 ? capacity : cargo) + ")";
    }
}

public class TrainConsistManagementSystemApp {

    public static void main(String[] args) {

        // =========================
        // UC1
        // =========================
        System.out.println("=== Train Consist Management App ===");

        List<String> train = new ArrayList<>();
        System.out.println("Initial bogie count: " + train.size());

        // =========================
        // UC2
        // =========================
        System.out.println("\n--- UC2 ---");
        train.add("Sleeper");
        train.add("AC Chair");
        train.add("First Class");

        System.out.println(train);

        train.remove("AC Chair");
        System.out.println(train);

        // =========================
        // UC3
        // =========================
        System.out.println("\n--- UC3 ---");
        Set<String> bogieIds = new HashSet<>();
        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG101");

        System.out.println(bogieIds);

        // =========================
        // UC4
        // =========================
        System.out.println("\n--- UC4 ---");
        LinkedList<String> consist = new LinkedList<>();
        consist.add("Engine");
        consist.add("Sleeper");
        consist.add("AC");
        consist.add("Cargo");
        consist.add("Guard");

        consist.add(2, "Pantry");
        consist.removeFirst();
        consist.removeLast();

        System.out.println(consist);

        // =========================
        // UC5
        // =========================
        System.out.println("\n--- UC5 ---");
        Set<String> formation = new LinkedHashSet<>();
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");
        formation.add("Sleeper");

        System.out.println(formation);

        // =========================
        // UC6
        // =========================
        System.out.println("\n--- UC6 ---");
        Map<String, Integer> capacityMap = new HashMap<>();
        capacityMap.put("Sleeper", 72);
        capacityMap.put("AC Chair", 60);
        capacityMap.put("First Class", 40);

        capacityMap.forEach((k, v) -> System.out.println(k + " -> " + v));

        // =========================
        // UC7–UC14 (Core Advanced)
        // =========================
        System.out.println("\n--- UC7-UC14 ---");

        List<Bogie> bogies = new ArrayList<>();

        try {
            bogies.add(new Bogie("Sleeper", 72));
            bogies.add(new Bogie("AC Chair", 60));
            bogies.add(new Bogie("First Class", 40));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // UC7 Sort
        bogies.sort(Comparator.comparingInt(Bogie::getCapacity));
        System.out.println("Sorted Bogies: " + bogies);

        // UC8 Filter
        System.out.println("Filtered (>60): " +
                bogies.stream().filter(b -> b.getCapacity() > 60).toList());

        // UC9 Group
        System.out.println("Grouped: " +
                bogies.stream().collect(Collectors.groupingBy(Bogie::getName)));

        // UC10 Reduce
        int total = bogies.stream().map(Bogie::getCapacity).reduce(0, Integer::sum);
        System.out.println("Total Capacity: " + total);

        // UC11 Regex
        System.out.println("Train ID Valid: " +
                Pattern.matches("TRN-\\d{4}", "TRN-1234"));

        // UC12 Safety
        List<Bogie> goods = new ArrayList<>();
        goods.add(new Bogie("Cylindrical", "Petroleum"));

        boolean safe = goods.stream().allMatch(b ->
                !b.getName().equals("Cylindrical") ||
                        b.getCargo().equals("Petroleum"));
        System.out.println("Safety: " + safe);

        // UC13 Performance
        long start = System.nanoTime();
        bogies.stream().filter(b -> b.getCapacity() > 60).toList();
        long end = System.nanoTime();
        System.out.println("Time: " + (end - start));

        // UC14 Exception
        try {
            new Bogie("Invalid", 0);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // =========================
        // UC15
        // =========================
        System.out.println("\n--- UC15 ---");

        Bogie g = new Bogie("Rectangular", "None");

        try {
            String cargo = "Petroleum";

            if (g.getName().equals("Rectangular") && cargo.equals("Petroleum")) {
                throw new CargoSafetyException("Unsafe cargo assignment");
            }

            g.cargo = cargo;

        } catch (CargoSafetyException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Assignment checked");
        }

        // =========================
        // UC16 Bubble Sort
        // =========================
        System.out.println("\n--- UC16 ---");

        int[] arr = {72, 56, 24, 70, 60};

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(arr));

        // =========================
        // UC17 Arrays.sort
        // =========================
        System.out.println("\n--- UC17 ---");

        String[] names = {"Sleeper", "AC Chair", "First Class"};
        Arrays.sort(names);
        System.out.println(Arrays.toString(names));

        // =========================
        // UC18 Linear Search
        // =========================
        System.out.println("\n--- UC18 ---");

        String[] ids = {"BG101", "BG205", "BG309"};
        String key = "BG309";

        boolean found = false;
        for (String id : ids) {
            if (id.equals(key)) {
                found = true;
                break;
            }
        }

        System.out.println("Found: " + found);

        // =========================
        // UC19 Binary Search
        // =========================
        System.out.println("\n--- UC19 ---");

        Arrays.sort(ids);
        int low = 0, high = ids.length - 1;
        boolean found2 = false;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = ids[mid].compareTo(key);

            if (cmp == 0) {
                found2 = true;
                break;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Found(Binary): " + found2);

        // =========================
        // UC20 Fail Fast
        // =========================
        System.out.println("\n--- UC20 ---");

        List<String> emptyList = new ArrayList<>();

        try {
            if (emptyList.isEmpty()) {
                throw new IllegalStateException("No bogies to search");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n=== Program Completed ===");
    }
}