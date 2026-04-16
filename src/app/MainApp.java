package app;

import model.*;
import service.*;

import java.util.*;

public class MainApp {

    private static Scanner sc = new Scanner(System.in);

    private static ProductService productService = new ProductService();
    private static SupplierService supplierService = new SupplierService();
    private static PurchaseService purchaseService = new PurchaseService();
    private static SaleService saleService = new SaleService();
    private static StockService stockService = new StockService();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== INVENTORY SYSTEM =====");
            System.out.println("1. Add Product");
            System.out.println("2. Add Supplier");
            System.out.println("3. Make Purchase");
            System.out.println("4. Make Sale");
            System.out.println("5. View Stock");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1: addProduct(); break;
                    case 2: addSupplier(); break;
                    case 3: makePurchase(); break;
                    case 4: makeSale(); break;
                    case 5: viewStock(); break;
                    case 6: System.exit(0);
                    default: System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addProduct() throws Exception {
        sc.nextLine(); // consume newline
        System.out.print("Enter product name: ");
        String name = sc.nextLine();

        System.out.print("Enter price: ");
        double price = sc.nextDouble();

        Product p = new Product(0, name, price);
        productService.addProduct(p);

        System.out.println("✅ Product added!");
    }

    private static void addSupplier() throws Exception {
        sc.nextLine();
        System.out.print("Enter supplier name: ");
        String name = sc.nextLine();

        System.out.print("Enter contact info: ");
        String contact = sc.nextLine();

        Supplier s = new Supplier(0, name, contact);
        supplierService.addSupplier(s);

        System.out.println("✅ Supplier added!");
    }

    private static void makePurchase() throws Exception {
        System.out.print("Enter supplier ID: ");
        int supplierId = sc.nextInt();

        System.out.print("Enter product ID: ");
        int productId = sc.nextInt();

        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();

        System.out.print("Enter price: ");
        double price = sc.nextDouble();

        Purchase p = new Purchase(0, supplierId, productId, qty, price, new Date());

        purchaseService.createPurchase(p);

        System.out.println("✅ Purchase completed!");
    }

    private static void makeSale() throws Exception {
        System.out.print("Enter product ID: ");
        int productId = sc.nextInt();

        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();

        System.out.print("Enter price: ");
        double price = sc.nextDouble();

        Sale s = new Sale(0, productId, qty, price, new Date());

        saleService.createSale(s);

        System.out.println("✅ Sale completed!");
    }

    private static void viewStock() throws Exception {
        System.out.print("Enter product ID: ");
        int productId = sc.nextInt();

        Stock stock = stockService.getStock(productId);

        if (stock == null) {
            System.out.println("No stock found!");
        } else {
            System.out.println("Stock for product " + productId + ": " + stock.getQuantity());
        }
    }
}
