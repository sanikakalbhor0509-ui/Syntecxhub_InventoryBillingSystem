package pro;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Item class
class Item {
    int id;
    String name;
    double price;
    int quantity;

    Item(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}

public class InventoryBillingSystem {

    static HashMap<Integer, Item> inventory = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== INVENTORY BILLING SYSTEM =====");
            System.out.println("1. Add Item");
            System.out.println("2. Update Stock");
            System.out.println("3. View Inventory");
            System.out.println("4. Generate Bill");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    updateStock();
                    break;
                case 3:
                    viewInventory();
                    break;
                case 4:
                    generateBill();
                    break;
                case 5:
                    System.out.println("Thank you for using the system!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);
    }

    // Add new item
    static void addItem() {
        System.out.print("Enter Item ID: ");
        int id = sc.nextInt();

        if (inventory.containsKey(id)) {
            System.out.println("Item already exists!");
            return;
        }

        sc.nextLine(); // consume newline
        System.out.print("Enter Item Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        inventory.put(id, new Item(id, name, price, qty));
        System.out.println("Item added successfully!");
    }

    // Update stock
    static void updateStock() {
        System.out.print("Enter Item ID to update: ");
        int id = sc.nextInt();

        if (!inventory.containsKey(id)) {
            System.out.println("Item not found!");
            return;
        }

        System.out.print("Enter new quantity: ");
        int qty = sc.nextInt();

        inventory.get(id).quantity = qty;
        System.out.println("Stock updated successfully!");
    }

    // View inventory
    static void viewInventory() {
        System.out.println("\n----- INVENTORY LIST -----");

        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty!");
            return;
        }

        System.out.printf("%-10s %-15s %-10s %-10s\n", "ID", "Name", "Price", "Qty");

        for (Item item : inventory.values()) {
            System.out.printf("%-10d %-15s %-10.2f %-10d\n",
                    item.id, item.name, item.price, item.quantity);
        }
    }

    // Generate bill
    static void generateBill() {
        double total = 0;

        System.out.println("\n----- BILL GENERATION -----");

        while (true) {
            System.out.print("Enter Item ID (0 to finish): ");
            int id = sc.nextInt();

            if (id == 0)
                break;

            if (!inventory.containsKey(id)) {
                System.out.println("Item not found!");
                continue;
            }

            System.out.print("Enter quantity: ");
            int qty = sc.nextInt();

            Item item = inventory.get(id);

            if (qty > item.quantity) {
                System.out.println("Insufficient stock!");
                continue;
            }

            double amount = qty * item.price;
            total += amount;
            item.quantity -= qty;

            System.out.println(item.name + " - ₹" + amount);
        }

        System.out.println("---------------------------");
        System.out.println("Total Bill Amount: ₹" + total);
        System.out.println("---------------------------");
    }
}
