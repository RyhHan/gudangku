package gudangkuu;

import java.sql.*;
import java.time.LocalDate;

public class Makanan extends Product implements DatabaseOperations{

    private LocalDate expirationDate;
    private int idCategory;

    public Makanan(int idProduct, String nameProduct, int quantityProduct, LocalDate expirationDate, int idCategory, int userId) {
        super(idProduct, nameProduct, quantityProduct, userId);
        this.expirationDate = expirationDate;
        this.idCategory = idCategory;
    }

    @Override
    public void save() {
        String sql = "INSERT INTO products (nameProduct, quantityProduct, expirationDate, idCategory, user_id, typeProduct) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = new Koneksi().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nameProduct);
            stmt.setInt(2, quantityProduct);
            stmt.setDate(3, Date.valueOf(expirationDate));  // Convert LocalDate to SQL Date
            stmt.setInt(4, idCategory);
            stmt.setInt(5, userId);
            stmt.setString(6, "Makanan");  // Tipe produk Makanan
            stmt.executeUpdate();
            System.out.println("Produk Makanan berhasil ditambahkan.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit() {
        String sql = "UPDATE products SET nameProduct = ?, quantityProduct = ?, expirationDate = ?, idCategory = ? WHERE idProduct = ?";
        try (Connection conn = new Koneksi().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nameProduct);
            stmt.setInt(2, quantityProduct);
            stmt.setDate(3, Date.valueOf(expirationDate));  // Convert LocalDate to SQL Date
            stmt.setInt(4, idCategory);
            stmt.setInt(5, idProduct);
            stmt.executeUpdate();
            System.out.println("Produk Makanan berhasil diperbarui.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        String sql = "DELETE FROM products WHERE idProduct = ?";
        try (Connection conn = new Koneksi().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProduct);
            stmt.executeUpdate();
            System.out.println("Produk Makanan berhasil dihapus.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayProductInfo() {
        System.out.println("Produk Makanan: " + nameProduct + ", Jumlah: " + quantityProduct + ", Kadaluarsa: " + expirationDate);
    }
}
