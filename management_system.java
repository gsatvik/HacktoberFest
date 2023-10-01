import java.util.*;

class Book {
    private String title;
    private String author;
    private double price;
    private int stock;

    public Book(String title, String author, double price, int stock) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.stock = stock;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

class Bookstore {
    private List<Book> inventory = new ArrayList<>();

    public void addBook(Book book) {
        inventory.add(book);
    }

    public void processOrder(Map<Book, Integer> order) {
        for (Map.Entry<Book, Integer> entry : order.entrySet()) {
            Book book = entry.getKey();
            int quantity = entry.getValue();
            int currentStock = book.getStock();
            if (currentStock >= quantity) {
                book.setStock(currentStock - quantity);
                System.out.println("Order processed: " + book.getTitle() + " x" + quantity);
            } else {
                System.out.println("Not enough stock for: " + book.getTitle());
            }
        }
    }

    public void generateInvoice(Map<Book, Integer> order) {
        double totalAmount = 0;
        System.out.println("Invoice:");
        for (Map.Entry<Book, Integer> entry : order.entrySet()) {
            Book book = entry.getKey();
            int quantity = entry.getValue();
            double totalPrice = book.getPrice() * quantity;
            System.out.println(book.getTitle() + " x" + quantity + ": $" + totalPrice);
            totalAmount += totalPrice;
        }
        System.out.println("Total Amount: $" + totalAmount);
    }
}

public class BookstoreManagementSystem {
    public static void main(String[] args) {
        Bookstore bookstore = new Bookstore();

        // Adding books to inventory
        bookstore.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", 10.99, 50));
        bookstore.addBook(new Book("To Kill a Mockingbird", "Harper Lee", 12.99, 30));
        bookstore.addBook(new Book("1984", "George Orwell", 9.99, 40));

        // Creating a customer order
        Map<Book, Integer> order = new HashMap<>();
        order.put(bookstore.inventory.get(0), 2);
        order.put(bookstore.inventory.get(1), 3);

        // Processing the order
        bookstore.processOrder(order);

        // Generating an invoice
        bookstore.generateInvoice(order);
    }
}
