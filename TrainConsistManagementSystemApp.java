/**
 * Train Consist Management App
 * Version: 1.0
 * Demonstrates UC1 to UC6 using Core Java & Data Structures
 */

import java.util.*;

public class TrainConsistManagementSystemApp {

    public static void main(String[] args) {

        // =========================
        // UC1: Initialize Train
        // =========================
        System.out.println("=== Train Consist Management App ===");

        List<String> train = new ArrayList<>();
        System.out.println("Initial bogie count: " + train.size());

        // =========================
        // UC2: ArrayList Operations
        // =========================
        System.out.println("\n--- UC2: Passenger Bogies ---");

        train.add("Sleeper");
        train.add("AC Chair");
        train.add("First Class");

        System.out.println("After adding bogies: " + train);

        train.remove("AC Chair");
        System.out.println("After removing AC Chair: " + train);

        System.out.println("Contains Sleeper? " + train.contains("Sleeper"));

        // =========================
        // UC3: Unique Bogie IDs (HashSet)
        // =========================
        System.out.println("\n--- UC3: Unique Bogie IDs ---");

        Set<String> bogieIds = new HashSet<>();
        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG101"); // duplicate

        System.out.println("Unique Bogie IDs: " + bogieIds);

        // =========================
        // UC4: Ordered Train (LinkedList)
        // =========================
        System.out.println("\n--- UC4: LinkedList Train Consist ---");

        LinkedList<String> consist = new LinkedList<>();

        consist.add("Engine");
        consist.add("Sleeper");
        consist.add("AC");
        consist.add("Cargo");
        consist.add("Guard");

        consist.add(2, "Pantry"); // insert at position

        consist.removeFirst();
        consist.removeLast();

        System.out.println("Final Train Consist: " + consist);

        // =========================
        // UC5: LinkedHashSet (Order + Unique)
        // =========================
        System.out.println("\n--- UC5: Ordered Unique Formation ---");

        Set<String> formation = new LinkedHashSet<>();

        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");
        formation.add("Sleeper"); // duplicate

        System.out.println("Train Formation: " + formation);

        // =========================
        // UC6: HashMap (Bogie Capacity)
        // =========================
        System.out.println("\n--- UC6: Bogie Capacity Mapping ---");

        Map<String, Integer> capacityMap = new HashMap<>();

        capacityMap.put("Sleeper", 72);
        capacityMap.put("AC Chair", 60);
        capacityMap.put("First Class", 40);

        for (Map.Entry<String, Integer> entry : capacityMap.entrySet()) {
            System.out.println(entry.getKey() + " -> Capacity: " + entry.getValue());
        }

        System.out.println("\n=== Program Running Successfully ===");
    }
}