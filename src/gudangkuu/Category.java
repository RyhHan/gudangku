package gudangkuu;

import java.sql.*;
import java.time.LocalDate;

public class Category implements DatabaseOperations {

    private int idCategory;
    private String nameCategory;
    private int userId;  

    public Category(int idCategory, String nameCategory, int userId) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
        this.userId = userId;
    }

    // Getter dan Setter
    public int getIdCategory() {
        return idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public void save() {
        String sql = "INSERT INTO categories (nameCategory, user_id) VALUES (?, ?)";
        try (Connection conn = new Koneksi().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nameCategory);
            stmt.setInt(2, userId);  // Mengaitkan kategori dengan user_id
            stmt.executeUpdate();
            System.out.println("Kategori berhasil disimpan.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit() {
        String sql = "UPDATE categories SET nameCategory = ? WHERE idCategory = ?";
        try (Connection conn = new Koneksi().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nameCategory);
            stmt.setInt(2, idCategory);
            stmt.executeUpdate();
            System.out.println("Kategori berhasil diperbarui.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        String sql = "DELETE FROM categories WHERE idCategory = ?";
        try (Connection conn = new Koneksi().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCategory);
            stmt.executeUpdate();
            System.out.println("Kategori berhasil dihapus.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Menampilkan kategori milik pengguna tertentu
    public static void listCategories(int userId) {
        String sql = "SELECT * FROM categories WHERE user_id = ?";
        try (Connection conn = new Koneksi().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);  // ambil user_id
            ResultSet rs = stmt.executeQuery();
            System.out.println("Daftar Kategori Anda:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("idCategory") + ", Nama: " + rs.getString("nameCategory"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayProducts() {
        String sql = "SELECT * FROM products WHERE idCategory = ? AND user_id = ?";
        try (Connection conn = new Koneksi().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCategory); 
            stmt.setInt(2, userId);     
            ResultSet rs = stmt.executeQuery();
            System.out.println("Produk dalam kategori " + nameCategory + ":");

            while (rs.next()) {
                int idProduct = rs.getInt("idProduct");
                String nameProduct = rs.getString("nameProduct");
                int quantityProduct = rs.getInt("quantityProduct");
                String productType = rs.getString("typeProduct");  

                
                Product product = null;
                if ("Makanan".equalsIgnoreCase(productType)) {
                    LocalDate expirationDate = rs.getDate("expirationDate").toLocalDate();
                    product = new Makanan(idProduct, nameProduct, quantityProduct, expirationDate, idCategory, userId);
                } else if ("Barang".equalsIgnoreCase(productType)) {
                    product = new Barang(idProduct, nameProduct, quantityProduct, idCategory, userId);
                }
                if (product != null) {
                    product.displayProductInfo();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
