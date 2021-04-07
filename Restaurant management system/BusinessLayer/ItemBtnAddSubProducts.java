package BusinessLayer;

import BusinessLayer.BaseProduct;
import BusinessLayer.CompositeProduct;
import BusinessLayer.MenuItem;
import BusinessLayer.Restaurant;
import PresentationLayer.AdministratorGUI;
import PresentationLayer.ItemAddGui;
import PresentationLayer.ItemModifyGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemBtnAddSubProducts implements ActionListener {

    private Restaurant restaurant;
    private AdministratorGUI administrator;
    private ItemModifyGui modifyItemView;

    public ItemBtnAddSubProducts(Restaurant restaurant, AdministratorGUI administrator, ItemModifyGui modifyItemView) {

        this.administrator = administrator;
        this.restaurant = restaurant;
        this.modifyItemView = modifyItemView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        System.out.println("Pressed\n");
        if(modifyItemView.getRdbtnSchimbaCompozitiaUnui().isSelected()) {

            String productName = (String) modifyItemView.getComboBoxProduct().getSelectedItem();//get prod. name
            double subProductId = Double.parseDouble((String) modifyItemView.getTextFieldId().getText());//get subprod. name

            CompositeProduct item = null;

            for (MenuItem itm : restaurant.getMeniu()) {//search it in meniu

                if (itm.composite) {//e composite sigur

                    if (((CompositeProduct) itm).getName().equals(productName)) {

                        item = (CompositeProduct) itm;//produsul in cauza


                        modifyItemView.setFourthComboBox(0, restaurant, (String) modifyItemView.getComboBoxProduct().getSelectedItem());
                    }
                }
            }
            String subProductName = null;

            for (MenuItem itm : restaurant.getMeniu()) {//search it in meniu
                if (itm.base) {
                    if (((BaseProduct) itm).getId() == subProductId)
                        subProductName = ((BaseProduct) itm).getName();
                } else if (itm.composite) {
                    if (((CompositeProduct) itm).getId() == subProductId)
                        subProductName = ((CompositeProduct) itm).getName();
                }
            }

            for (MenuItem temp : restaurant.getMeniu()) {
                if (temp.base) {
                    if (((BaseProduct) temp).getName().equals(subProductName)) {
                        //item.getSubproducts().add(temp);//adauga in compozitie
                        restaurant.adaugaSubProdus(item, temp);
                        break;
                    }
                } else if (temp.composite) {
                    if (((CompositeProduct) temp).getName().equals(subProductName)) {
                        //item.getSubproducts().add(temp);//adauga in compozitie
                        restaurant.adaugaSubProdus(item, temp);
                        break;
                    }
                }
            }

            modifyItemView.setFourthComboBox(0, restaurant, (String) modifyItemView.getComboBoxProduct().getSelectedItem());
        }

    }
}
