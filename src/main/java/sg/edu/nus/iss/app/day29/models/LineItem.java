package sg.edu.nus.iss.app.day29.models;

public class LineItem {
    
    private String item;
    private Integer quantity;

    
    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "LineItem [item=" + item + ", quantity=" + quantity + "]";
    } 
    
}
