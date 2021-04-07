package BusinessLayer;

import BusinessLayer.BaseProduct;
import BusinessLayer.CompositeProduct;
import BusinessLayer.MenuItem;
import BusinessLayer.Restaurant;
import PresentationLayer.AdministratorGUI;
import PresentationLayer.ItemAddGui;
import PresentationLayer.ItemModifyGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemBtnDeleteSubProducts implements ActionListener {

    private Restaurant restaurant;
    private AdministratorGUI administrator;
    private ItemModifyGui modifyItemView;

    public ItemBtnDeleteSubProducts(Restaurant restaurant, AdministratorGUI administrator, ItemModifyGui modifyItemView) {

        this.administrator = administrator;
        this.restaurant = restaurant;
        this.modifyItemView = modifyItemView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        System.out.println("Pressed\n");
        if(modifyItemView.getRdbtnSchimbaCompozitiaUnui().isSelected()) {

            String productName = (String) modifyItemView.getComboBoxProduct().getSelectedItem();//get prod. name
            String subProductName = (String) modifyItemView.getComboBoxSubProduct().getSelectedItem();//get subprod. name

            CompositeProduct item = null;
            for (MenuItem itm : restaurant.getMeniu()) {//search it in meniu
                if (itm.composite) {//e composite sigur
                    if (((CompositeProduct) itm).getName().equals(productName)) {
                        item = (CompositeProduct) itm;//produsul in cauza

                        for (MenuItem subP : item.getSubproducts()) {
                            if (subP.base) {
                                if (((BaseProduct) subP).getName().equals(subProductName)) {
                                    //remove from subproducts
                                    //item.getSubproducts().remove(subP);
                                    restaurant.stergeSubProduse(item, subP);
                                    //remove from comboBox
                                    modifyItemView.setFourthComboBox(0, restaurant, (String) modifyItemView.getComboBoxProduct().getSelectedItem());
                                    break;
                                }

                            } else if (subP.composite) {
                                if (((CompositeProduct) subP).getName().equals(subProductName)) {
                                    //remove from subproducts
                                    //item.getSubproducts().remove(subP);
                                    restaurant.stergeSubProduse(item, subP);
                                    //remove from comboBox
                                    modifyItemView.setFourthComboBox(0, restaurant, (String) modifyItemView.getComboBoxProduct().getSelectedItem());
                                    break;
                                }
                            }

                        }
                    }
                }
            }

        }

    }
}
