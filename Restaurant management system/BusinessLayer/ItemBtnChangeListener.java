package BusinessLayer;

import PresentationLayer.AdministratorGUI;
import PresentationLayer.ItemModifyGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemBtnChangeListener implements ActionListener {

    private Restaurant restaurant;
    private AdministratorGUI administrator;
    private ItemModifyGui modifyItemView;

    public ItemBtnChangeListener(Restaurant restaurant, AdministratorGUI administrator, ItemModifyGui modifyItemView) {

        this.administrator = administrator;
        this.restaurant = restaurant;
        this.modifyItemView = modifyItemView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("Pressed\n");
        if(modifyItemView.getRdbtnSchimbaNumeleProdusului().isSelected()) {
            if (modifyItemView.getChooseNameOrPrice().getSelectedItem().equals("numele")) { //--------set name

                String text = modifyItemView.getTextFieldEnterChange().getText();  //numele nou
                Object obj = modifyItemView.getComboBoxSelect().getSelectedItem(); //produsul selectat
                for (MenuItem itm : restaurant.getMeniu()) {//cautare + schimbare

                    if (itm.base) {
                        if (((BaseProduct) itm).getName().equals(obj)) {
                            //((BaseProduct) itm).setName(text);//shimbam nume
                            restaurant.schimbaNume(itm, text);
                        }
                    } else if (itm.composite) {
                        if (((CompositeProduct) itm).getName().equals(obj)) {
                            //((CompositeProduct) itm).setName(text);//shimbam nume
                            restaurant.schimbaNume(itm, text);
                        }
                    }
                }
            } else if (modifyItemView.getChooseNameOrPrice().getSelectedItem().equals("pretul")) {//----------set price

                Double price = Double.parseDouble(modifyItemView.getTextFieldEnterChange().getText());  //pretul nou
                Object obj = modifyItemView.getComboBoxSelect().getSelectedItem(); //produsul selectat
                for (MenuItem itm : restaurant.getMeniu()) {//cautare + schimbare

                    if (itm.base) {
                        if (((BaseProduct) itm).getName().equals(obj)) {
                            //((BaseProduct) itm).setPrice(price);
                            restaurant.schimbaPret(itm, price);
                        }
                    } else if (itm.composite) {
                        if (((CompositeProduct) itm).getName().equals(obj)) {
                            //((CompositeProduct) itm).setPrice(price);
                            restaurant.schimbaPret(itm, price);
                        }
                    }
                }
            }

            modifyItemView.refreshTable(); //set table
            modifyItemView.setFirstComboBox(0, restaurant);//set combobox
            modifyItemView.setThirdComboBox(0, restaurant);//set the other combobox
            modifyItemView.setFourthComboBox(0,restaurant,(String)modifyItemView.getComboBoxProduct().getSelectedItem());
            modifyItemView.setListenerRefreshSubProductComboBox(new RefreshSubProductComboBoxListener(restaurant, administrator, modifyItemView));

        }



    }
}
