package entity;

public class Product {
    private int id;
    private String productName;

    public Product(int id, String productName) {
        this.id = id;
        this.productName = productName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(id);
        sb.append(", productName='").append(productName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
