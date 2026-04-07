/**
 * Train Consist Management App
 * Version: 2.0
 * Covers UC1 to UC14
 */

import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

class Bogie {
    String name;
    int capacity;
    String type;   // Passenger / Goods
    String cargo;  // For goods

    // Constructor for passenger bogie
    public Bogie(String name, int capacity) throws InvalidCapacityException {
        if (capacity <= 0) {
            throw new InvalidCapacityException("Capacity must be greater than zero");
        }
        this.name = name;
        this.capacity = capacity;
        this.type = "Passenger";
    }

    // Constructor for goods bogie
    public Bogie(String name, String cargo) {
        this.name = name;
        this.cargo = cargo;
        this.type = "Goods";
    }

    public int getCapacity() {
        return capacity;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getCargo() {
        return cargo;
    }

    @Override
    public String toString() {
        if (type.equals("Passenger"))
            return name + "(" + capacity + ")";
        else
            return name + "(" + cargo + ")";
    }
}

// UC14 Custom Exception
class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String msg) {
        super(msg);
    }
}

public class TrainConsistManagementSystemApp {

    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App ===");

        // =========================
        // UC1: Initialize Train
        // =========================
        List<String> train = new ArrayList<>();
        System.out.println("Initial bogie count: " + train.size());

        // =========================
        // UC2: ArrayList Operations
        // =========================
        System.out.println("\n--- UC2 ---");
        train.add("Sleeper");
        train.add("AC Chair");
        train.add("First Class");

        System.out.println(train);

        train.remove("AC Chair");
        System.out.println(train);

        // =========================
        // UC3: HashSet
        // =========================
        System.out.println("\n--- UC3 ---");
        Set<String> ids = new HashSet<>();
        ids.add("BG101");
        ids.add("BG102");
        ids.add("BG101");
        System.out.println(ids);

        // =========================
        // UC4: LinkedList
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
        // UC5: LinkedHashSet
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
        // UC6: HashMap
        // =========================
        System.out.println("\n--- UC6 ---");
        Map<String, Integer> capacityMap = new HashMap<>();
        capacityMap.put("Sleeper", 72);
        capacityMap.put("AC Chair", 60);
        capacityMap.put("First Class", 40);

        capacityMap.forEach((k, v) ->
                System.out.println(k + " -> " + v));

        // =========================
        // UC7: Sort Bogies (Comparator)
        // =========================
        System.out.println("\n--- UC7 ---");

        List<Bogie> bogies = new ArrayList<>();

        try {
            bogies.add(new Bogie("Sleeper", 72));
            bogies.add(new Bogie("AC Chair", 60));
            bogies.add(new Bogie("First Class", 40));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        bogies.sort(Comparator.comparingInt(Bogie::getCapacity));

        bogies.forEach(System.out::println);

        // =========================
        // UC8: Filter using Streams
        // =========================
        System.out.println("\n--- UC8 ---");

        List<Bogie> filtered = bogies.stream()
                .filter(b -> b.getCapacity() > 60)
                .toList();

        filtered.forEach(System.out::println);

        // =========================
        // UC9: Grouping
        // =========================
        System.out.println("\n--- UC9 ---");

        Map<String, List<Bogie>> grouped =
                bogies.stream()
                        .collect(Collectors.groupingBy(Bogie::getName));

        grouped.forEach((k, v) -> System.out.println(k + " -> " + v));

        // =========================
        // UC10: Total Capacity
        // =========================
        System.out.println("\n--- UC10 ---");

        int total = bogies.stream()
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);

        System.out.println("Total Capacity: " + total);

        // =========================
        // UC11: Regex Validation
        // =========================
        System.out.println("\n--- UC11 ---");

        String trainId = "TRN-1234";
        String cargoCode = "PET-AB";

        Pattern p1 = Pattern.compile("TRN-\\d{4}");
        Pattern p2 = Pattern.compile("PET-[A-Z]{2}");

        System.out.println("Train ID valid: " + p1.matcher(trainId).matches());
        System.out.println("Cargo Code valid: " + p2.matcher(cargoCode).matches());

        // =========================
        // UC12: Safety Check
        // =========================
        System.out.println("\n--- UC12 ---");

        List<Bogie> goods = new ArrayList<>();
        goods.add(new Bogie("Cylindrical", "Petroleum"));
        goods.add(new Bogie("Rectangular", "Coal"));

        boolean safe = goods.stream().allMatch(b ->
                !b.getName().equals("Cylindrical") ||
                        b.getCargo().equals("Petroleum")
        );

        System.out.println("Safety Compliance: " + safe);

        // =========================
        // UC13: Performance Comparison
        // =========================
        System.out.println("\n--- UC13 ---");

        long start = System.nanoTime();

        List<Bogie> loopResult = new ArrayList<>();
        for (Bogie b : bogies) {
            if (b.getCapacity() > 60) {
                loopResult.add(b);
            }
        }

        long end = System.nanoTime();
        System.out.println("Loop Time: " + (end - start));

        start = System.nanoTime();

        List<Bogie> streamResult = bogies.stream()
                .filter(b -> b.getCapacity() > 60)
                .toList();

        end = System.nanoTime();
        System.out.println("Stream Time: " + (end - start));

        // =========================
        // UC14: Custom Exception
        // =========================
        System.out.println("\n--- UC14 ---");

        try {
            Bogie invalid = new Bogie("Invalid", 0);
        } catch (InvalidCapacityException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        System.out.println("\n=== Program Completed ===");
    }
}