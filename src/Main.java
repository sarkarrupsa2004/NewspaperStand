import java.sql.*;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = 0;

        while (choice != 5) {
            System.out.println("\n===== Newspaper Subscription System =====");
            System.out.println("1. Register Subscriber");
            System.out.println("2. Record Payment");
            System.out.println("3. Mark Delivery");
            System.out.println("4. View Unpaid Subscribers");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: registerSubscriber(); break;
                case 2: recordPayment();      break;
                case 3: markDelivery();       break;
                case 4: viewUnpaid();         break;
                case 5: System.out.println("Goodbye!"); break;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // FEATURE 1: Register a new subscriber
    static void registerSubscriber() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter address: ");
        String address = sc.nextLine();

        System.out.print("Enter newspaper name: ");
        String paperName = sc.nextLine();

        System.out.print("Enter monthly rate: ");
        double rate = sc.nextDouble();
        sc.nextLine();

        String sql = "INSERT INTO subscribers (name, address, paper_name, monthly_rate) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, paperName);
            ps.setDouble(4, rate);
            ps.executeUpdate();

            System.out.println("Subscriber registered successfully!");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // FEATURE 2: Record a payment
    static void recordPayment() {
        System.out.print("Enter subscriber ID: ");
        int subId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter payment month (e.g. 2026-04): ");
        String month = sc.nextLine();

        String sql = "INSERT INTO payments (sub_id, pay_month) VALUES (?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, subId);
            ps.setString(2, month);
            ps.executeUpdate();

            System.out.println("Payment recorded successfully!");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // FEATURE 3: Mark delivery for a subscriber
    static void markDelivery() {
        System.out.print("Enter subscriber ID: ");
        int subId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter delivery date (e.g. 2026-04-01): ");
        String date = sc.nextLine();

        String sql = "INSERT INTO deliveries (sub_id, del_date) VALUES (?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, subId);
            ps.setString(2, date);
            ps.executeUpdate();

            System.out.println("Delivery marked successfully!");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // FEATURE 4: View unpaid subscribers
    static void viewUnpaid() {
        System.out.print("Enter month to check (e.g. 2026-04): ");
        String month = sc.nextLine();

        String sql = "SELECT name, paper_name, monthly_rate FROM subscribers " +
                     "WHERE sub_id NOT IN " +
                     "(SELECT sub_id FROM payments WHERE pay_month = ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, month);
            ResultSet rs = ps.executeQuery();

            System.out.println("\nUnpaid Subscribers:");
            System.out.println("--------------------------------------------");

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("- %-20s | %-20s | Rs %.2f%n",
                    rs.getString("name"),
                    rs.getString("paper_name"),
                    rs.getDouble("monthly_rate"));
            }

            if (!found) {
                System.out.println("All subscribers have paid for this month!");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}