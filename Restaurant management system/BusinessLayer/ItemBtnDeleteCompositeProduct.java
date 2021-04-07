package BusinessLayer;

import BusinessLayer.CompositeProduct;
import BusinessLayer.MenuItem;
import BusinessLayer.Restaurant;
import PresentationLayer.AdministratorGUI;
import PresentationLayer.ItemAddGui;
import PresentationLayer.ItemModifyGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemBtnDeleteCompositeProduct implements ActionListener {

    private Restaurant restaurant;
    private AdministratorGUI administrator;
    private ItemModifyGui modifyItemView;

    public ItemBtnDeleteCompositeProduct(Restaurant restaurant, AdministratorGUI administrator, ItemModifyGui modifyItemView) {

        this.administrator = administrator;
        this.restaurant = restaurant;
        this.modifyItemView = modifyItemView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

////////////////////////////////////////////////////////////////////////////////////////NOT USED!!!!!!!!!!!!!!
        System.out.println("Pressed\n");
        
        String productName = (String)modifyItemView.getComboBoxProduct().getSelectedItem();//get prod. name
        CompositeProduct item = null;
        for(MenuItem itm : restaurant.getMeniu()) {//search it in meniu
            if(itm.composite){//e composite sigur
                if(((CompositeProduct)itm).getName().equals(productName)) {
                    item = (CompositeProduct) itm;

                    //delete from meniu --chk
                    restaurant.getMeniu().remove(item);
                    //delete from table --chk
                    modifyItemView.refreshTable();
                    //delete from comboBoxes --chk
                    modifyItemView.setThirdComboBox(0,restaurant);
                    modifyItemView.setFirstComboBox(0, restaurant);//set other combobox
                     break;

                }
            }
        }



    }
}
