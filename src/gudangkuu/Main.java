package gudangkuu;

import java.util.Scanner;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Sistem Manajemen Gudang");
        System.out.println("1. Registrasi");
        System.out.println("2. Login");
        System.out.print("Pilih opsi: ");
        int loginChoice = scanner.nextInt();
        scanner.nextLine(); 

        User loggedInUser = null;

        if (loginChoice == 1) {
            System.out.print("Masukkan username: ");
            String username = scanner.nextLine();
            System.out.print("Masukkan password: ");
            String password = scanner.nextLine();
            boolean isRegistered = User.register(username, password);
            if (isRegistered) {
                System.out.println("Registrasi berhasil. Silakan login.");
            }
        }

        if (loginChoice == 2 || (loginChoice == 1 && loggedInUser == null)) {
            System.out.print("Masukkan username: ");
            String username = scanner.nextLine();
            System.out.print("Masukkan password: ");
            String password = scanner.nextLine();
            loggedInUser = User.login(username, password);
            if (loggedInUser != null) {
                System.out.println("Login berhasil. Selamat datang, " + loggedInUser.getUsername());
            } else {
                System.out.println("Login gagal. Coba lagi.");
                return;
            }
        }
        
        while (true) {
            System.out.println("\nMenu Utama");
            System.out.println("1. Buat Kategori");
            System.out.println("2. Daftar Kategori");
            System.out.println("3. Tambahkan Produk ke Kategori");
            System.out.println("4. Tampilkan Produk dalam Kategori");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Masukkan nama kategori: ");
                    String categoryName = scanner.nextLine();
                    Category category = new Category(0, categoryName, loggedInUser.getUserId());  
                    category.save();
                    break;

                case 2:
                    Category.listCategories(loggedInUser.getUserId());  
                    break;

                case 3:
                    
                    System.out.print("Masukkan ID kategori untuk menambahkan produk: ");
                    int catId = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Masukkan tipe produk (Makanan/Barang): ");
                    String productType = scanner.nextLine();
                    System.out.print("Masukkan nama produk: ");
                    String productName = scanner.nextLine();
                    System.out.print("Masukkan jumlah produk: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); 

                    if (productType.equalsIgnoreCase("Makanan")) {
                        System.out.print("Masukkan tanggal kadaluarsa (yyyy-mm-dd): ");
                        String expirationDateStr = scanner.nextLine();
                        LocalDate expirationDate = LocalDate.parse(expirationDateStr);
                        Makanan product = new Makanan(0, productName, quantity, expirationDate, catId, loggedInUser.getUserId());  
                        product.save();
                        System.out.println("Produk Makanan berhasil ditambahkan.");
                    } else if (productType.equalsIgnoreCase("Barang")) {
                        Barang product = new Barang(0, productName, quantity, catId, loggedInUser.getUserId());  
                        product.save();
                        System.out.println("Produk Barang berhasil ditambahkan.");
                    } else {
                        System.out.println("Tipe produk tidak dikenali.");
                    }
                    break;

                case 4:
                    System.out.print("Masukkan ID kategori untuk melihat produk: ");
                    int catIdForProducts = scanner.nextInt();
                    scanner.nextLine(); 
                    Category categoryForProducts = new Category(catIdForProducts, "", loggedInUser.getUserId());  
                    categoryForProducts.displayProducts();
                    break;

                case 5:
                    System.out.println("Keluar dari aplikasi...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opsi tidak valid. Silakan coba lagi.");
            }
        }
    }
}
