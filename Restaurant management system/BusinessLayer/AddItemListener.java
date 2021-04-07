package BusinessLayer;

import PresentationLayer.AdministratorGUI;
import PresentationLayer.ItemAddGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItemListener implements ActionListener {

    private Restaurant restaurant;
    private AdministratorGUI administrator;
    private ItemAddGui addItemView;

    public AddItemListener(Restaurant restaurant, AdministratorGUI administrator) {

        this.administrator = administrator;
        this.restaurant = restaurant;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        addItemView = new ItemAddGui(administrator.getModel());
        addItemView.setVisible(true);

        addItemView.setListenerGataBtn(new DoneAddingItemListener(restaurant, administrator, addItemView));



    }
}
