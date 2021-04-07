package BusinessLayer;

import BusinessLayer.BaseProduct;
import BusinessLayer.CompositeProduct;
import BusinessLayer.MenuItem;
import BusinessLayer.Restaurant;
import PresentationLayer.AdministratorGUI;
import PresentationLayer.ItemAddGui;
import PresentationLayer.ItemDeleteGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoneDeletingItemListener implements ActionListener {

    private Restaurant restaurant;
    private AdministratorGUI administrator;
    private ItemDeleteGui deleteItemView;

    public DoneDeletingItemListener(Restaurant restaurant, AdministratorGUI administrator, ItemDeleteGui addOnMueniu) {

        this.administrator = administrator;
        this.restaurant = restaurant;
        this.deleteItemView = addOnMueniu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("pressed");
        System.out.println(deleteItemView.getListSelected());
        String selected = (String) deleteItemView.getListSelected();
        for(MenuItem itm : restaurant.getMeniu()){
            if(itm.base){
                if(selected.equals(((BaseProduct)itm).getName())){

                    restaurant.deleteItem(itm);  //delete from meniu
                    deleteItemView.deleteBaseItemToTable((BaseProduct)itm); //delete table
                    Object obj = deleteItemView.getList().getSelectedItem();
                    System.out.println(obj);
                    deleteItemView.setComboBoxForDelete(0,obj);  //delete from comboBox
                    break;
                }

            }
            else if(itm.composite){
                if(selected.equals(((CompositeProduct)itm).getName())){

                    restaurant.getMeniu().remove((CompositeProduct)itm);  //delete from meniu
                    deleteItemView.deleteCompositeItemToTable((CompositeProduct)itm); //delete table
                    Object obj = deleteItemView.getList().getSelectedItem();
                    System.out.println(obj);
                    deleteItemView.setComboBoxForDelete(0,obj);  //delete from comboBox
                    break;
                }
            }
        }

    }
}
