package BusinessLayer;

import PresentationLayer.AdministratorGUI;
import PresentationLayer.WaiterGUI;


public class Controller {

    private Restaurant restaurant;
    private AdministratorGUI administrator;
    private WaiterGUI waiter;

    public Controller(Restaurant restaurant, AdministratorGUI administrator, WaiterGUI waiter){

            this.restaurant = restaurant;
            this.administrator = administrator;
            this.waiter = waiter;

            administrator.setAddItemListener(new AddItemListener(restaurant, administrator));
            administrator.setDeleteItemListener(new DeleteItemListener(restaurant, administrator));
            administrator.setModifyItemListener(new ModifyItemListener(restaurant, administrator));

            waiter.setAddOrderListener(new AddOrderBtnListener(restaurant, administrator, waiter));
            waiter.setGenerateBill(new GenerateBillListener(restaurant, administrator, waiter));

    }
}
