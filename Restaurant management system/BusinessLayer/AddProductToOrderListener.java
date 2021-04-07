package BusinessLayer;
import BusinessLayer.BaseProduct;
import BusinessLayer.CompositeProduct;
import BusinessLayer.MenuItem;
import BusinessLayer.Restaurant;
import PresentationLayer.AddOrderGui;
import PresentationLayer.AdministratorGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductToOrderListener implements ActionListener {

    private Restaurant restaurant;
    private AdministratorGUI administrator;
    private AddOrderGui addOrderView;

    public AddProductToOrderListener(Restaurant restaurant, AdministratorGUI administrator, AddOrderGui addOrderView) {
        this.restaurant = restaurant;
        this.administrator = administrator;
        this.addOrderView = addOrderView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {//Adding a product to an order

        System.out.println("pressed");

        String itemName = (String)addOrderView.getList().getSelectedItem();//luam prod


        for(MenuItem itm : restaurant.getMeniu()) {
            if(itm.base) {
                if(((BaseProduct)itm).getName().equals(itemName)) {

                    addOrderView.getModel().addRow(new Object[]{((BaseProduct)itm).getId(),//actualizam tabel
                            ((BaseProduct)itm).getName(),
                            ((BaseProduct)itm).getPrice()});

                    addOrderView.getElements().add(itm);//introducem in arraylist
                    break;
                }
            }

            else if(itm.composite){
                if(((CompositeProduct)itm).getName().equals(itemName)) {

                    addOrderView.getModel().addRow(new Object[]{((CompositeProduct)itm).getId(),//actualizam tabel
                            ((CompositeProduct)itm).getName(),
                            ((CompositeProduct)itm).getPrice()});

                    addOrderView.getElements().add(itm);//introducem in arraylist
                    break;
                }
            }
        }



    }
}
