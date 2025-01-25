import java.io.*;
import java.util.*;

public class InventoryManager {
    private static final String INVENTORY_FILE = "src/inventory.txt";
    public static void main(String[] args) {
        // Entry point for the program
        // TODO: Implement menu-driven program for inventory management
        InventoryManager manager = new InventoryManager();
        manager.run();
    }
    private void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Inventory Management System");
            System.out.println("1. Read Inventory");
            System.out.println("2. Add New Item");
            System.out.println("3. Update Existing Item");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    readInventory();
                    break;
                case 2:
                    addItem(scanner);
                    break;
                case 3:
                    updateItem(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public static void readInventory(String fileName) {
        // TODO: Read and display inventory from file
        File file = new File(INVENTORY_FILE);
        if (!file.exists()) {
            System.out.println("Inventory file does not exist.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addItem(String fileName, String itemName, int itemCount) {
        // TODO: Add a new item to the inventory
        
        System.out.print("Enter item name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter item count: ");
        int itemCount = scanner.nextInt();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INVENTORY_FILE, true))) {
            writer.write(itemName + " " + itemCount);
            writer.newLine();
            System.out.println("Item added to inventory.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateItem(String fileName, String itemName, int itemCount) {
        // TODO: Update the count of an existing item
        {
            System.out.print("Enter item name: ");
            String itemName = scanner.nextLine();
            System.out.print("Enter new item count: ");
            int itemCount = scanner.nextInt();
    
            File file = new File(INVENTORY_FILE);
            if (!file.exists()) {
                System.out.println("Inventory file does not exist.");
                return;
            }
    
            List<String> lines = new ArrayList<>();
            boolean itemFound = false;
    
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith(itemName + " ")) {
                        lines.add(itemName + " " + itemCount);
                        itemFound = true;
                    } else {
                        lines.add(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!itemFound) {
                System.out.println("Item not found in inventory.");
                return;
            }
    
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
                System.out.println("Item count updated.");
            } catch (IOException e) {
                e.printStackTrace();
            }
            
    }
}
