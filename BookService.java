import java.sql.*;
import java.util.Scanner;

public class BookService {
    private Connection conn;
    private Scanner sc = new Scanner(System.in);

    public BookService(Connection conn) {
        this.conn = conn;
    }

    public void addBook() throws SQLException {
        System.out.print("Enter title: ");
        String title = sc.nextLine();
        System.out.print("Enter author: ");
        String author = sc.nextLine();

        String sql = "INSERT INTO books (title, author) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, title);
        stmt.setString(2, author);
        stmt.executeUpdate();
        System.out.println("✅ Book added successfully!");
    }

    public void issueBook() throws SQLException {
        System.out.print("Enter book ID to issue: ");
        int id = sc.nextInt(); sc.nextLine();

        String checkSql = "SELECT * FROM books WHERE id=? AND isIssued=false";
        PreparedStatement checkStmt = conn.prepareStatement(checkSql);
        checkStmt.setInt(1, id);
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            String updateSql = "UPDATE books SET isIssued=true WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(updateSql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("📘 Book issued!");
        } else {
            System.out.println("❌ Book not available!");
        }
    }

    public void returnBook() throws SQLException {
        System.out.print("Enter book ID to return: ");
        int id = sc.nextInt(); sc.nextLine();

        String sql = "UPDATE books SET isIssued=false WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        int rows = stmt.executeUpdate();

        if (rows > 0)
            System.out.println("🔄 Book returned!");
        else
            System.out.println("❌ Invalid book ID!");
    }

    public void viewBooks() throws SQLException {
        String sql = "SELECT * FROM books";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("\n=== Library Books ===");
        while (rs.next()) {
            System.out.printf("%d | %s | %s | %s\n",
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getBoolean("isIssued") ? "Issued" : "Available");
        }
        System.out.println("=====================\n");
    }
}
