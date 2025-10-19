import java.sql.Connection;
import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.out.println("❌ Failed to connect to database.");
                return;
            }

            BookService service = new BookService(conn);
            Scanner sc = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\n=== Library Menu ===");
                System.out.println("1. Add Book");
                System.out.println("2. Issue Book");
                System.out.println("3. Return Book");
                System.out.println("4. View All Books");
                System.out.println("0. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt(); sc.nextLine();

                switch (choice) {
                    case 1 -> service.addBook();
                    case 2 -> service.issueBook();
                    case 3 -> service.returnBook();
                    case 4 -> service.viewBooks();
                    case 0 -> System.out.println("👋 Exiting...");
                    default -> System.out.println("❌ Invalid choice!");
                }
            } while (choice != 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
