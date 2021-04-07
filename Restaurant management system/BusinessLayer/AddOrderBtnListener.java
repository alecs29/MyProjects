package BusinessLayer;

import PresentationLayer.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddOrderBtnListener implements ActionListener {

    private WaiterGUI waiter;
    private Restaurant restaurant;
    private AdministratorGUI administrator;
    private AddOrderGui addOrderView;

    public AddOrderBtnListener(Restaurant restaurant, AdministratorGUI administrator, WaiterGUI waiter) {
        this.restaurant = restaurant;
        this.administrator = administrator;
        this.waiter = waiter;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        addOrderView = new AddOrderGui(restaurant);
        addOrderView.setVisible(true);

        addOrderView.setBtnAddProductToCommandListener(new AddProductToOrderListener(restaurant, administrator, addOrderView));
        addOrderView.setPlaseazaComandaListener(new PlaceOrderListener(restaurant, administrator, addOrderView, waiter));

    }
}
