package BusinessLayer;
import BusinessLayer.*;
import PresentationLayer.AddOrderGui;
import PresentationLayer.AdministratorGUI;
import PresentationLayer.WaiterGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlaceOrderListener implements ActionListener {
    private Restaurant restaurant;
    private AdministratorGUI administrator;
    private AddOrderGui addOrderView;
    public WaiterGUI waiter;

    public PlaceOrderListener(Restaurant restaurant, AdministratorGUI administrator, AddOrderGui addOrderView, WaiterGUI waiter) {
        this.restaurant = restaurant;
        this.administrator = administrator;
        this.addOrderView = addOrderView;
        this.waiter = waiter;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("pressed");

        //cream un order nou
        if(addOrderView.getElements().size() > 0) {

            String tab = addOrderView.getTextField().getText();
            int table = Integer.parseInt(tab);

            restaurant.setOrdersIndex(restaurant.getOrdersIndex() + 1);//++ order idx
            restaurant.getOrders().add(new Order(restaurant.getOrdersIndex(),
                    table));// (++ order idx, table)

            restaurant.getCommands().put(restaurant.getOrders().get(restaurant.getOrdersIndex() - 1), addOrderView.getElements());
            waiter.refreshTable();

            addOrderView.getModel().setRowCount(0);
            addOrderView.setElements(new ArrayList<MenuItem>());

        }

    }
}
