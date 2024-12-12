package gudangkuu;

public abstract class Product{

    protected int idProduct;
    protected String nameProduct;
    protected int quantityProduct;
    protected int userId;  // Relasi dengan user

    public Product(int idProduct, String nameProduct, int quantityProduct, int userId) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.quantityProduct = quantityProduct;
        this.userId = userId;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public int getQuantityProduct() {
        return quantityProduct;
    }

    public abstract void displayProductInfo();
}
