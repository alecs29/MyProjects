package BusinessLayer;

public class Order {

    int orderId;
    int table;

    public Order(int orderId, int table) {

        this.orderId = orderId;
        this.table = table;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                '}';
    }

    public int getOrderId() {
        return orderId;
    }

    public int getTable() {
        return table;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setTable(int table) {
        this.table = table;
    }
}
