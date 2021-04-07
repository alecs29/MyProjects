package BusinessLayer;

import BusinessLayer.Restaurant;
import PresentationLayer.AdministratorGUI;
import PresentationLayer.ItemAddGui;
import PresentationLayer.ItemModifyGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemBtnDeleteCompositionListener implements ActionListener {

    private Restaurant restaurant;
    private AdministratorGUI administrator;
    private ItemModifyGui modifyItemView;

    public ItemBtnDeleteCompositionListener(Restaurant restaurant, AdministratorGUI administrator, ItemModifyGui modifyItemView) {

        this.administrator = administrator;
        this.restaurant = restaurant;
        this.modifyItemView = modifyItemView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("Pressed\n");

    }
}
