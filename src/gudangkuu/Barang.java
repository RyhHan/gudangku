package gudangkuu;

import java.sql.*;

public class Barang extends Product implements DatabaseOperations{

    private int idCategory;

    public Barang(int idProduct, String nameProduct, int quantityProduct, int idCategory, int userId) {
        super(idProduct, nameProduct, quantityProduct, userId);
        this.idCategory = idCategory;
    }

    @Override
    public void save() {
        String sql = "INSERT INTO products (nameProduct, quantityProduct, idCategory, user_id, typeProduct) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = new Koneksi().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nameProduct);
            stmt.setInt(2, quantityProduct);
            stmt.setInt(3, idCategory);
            stmt.setInt(4, userId);
            stmt.setString(5, "Barang");  // Tipe produk Barang
            stmt.executeUpdate();
            System.out.println("Produk Barang berhasil ditambahkan.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit() {
        String sql = "UPDATE products SET nameProduct = ?, quantityProduct = ? WHERE idProduct = ?";
        try (Connection conn = new Koneksi().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nameProduct);
            stmt.setInt(2, quantityProduct);
            stmt.setInt(3, idProduct);
            stmt.executeUpdate();
            System.out.println("Produk Barang berhasil diperbarui.");
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
            System.out.println("Produk Barang berhasil dihapus.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayProductInfo() {
        System.out.println("Produk Barang: " + nameProduct + ", Jumlah: " + quantityProduct);
    }
}
