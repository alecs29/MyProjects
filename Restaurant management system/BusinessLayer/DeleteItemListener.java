package BusinessLayer;

import PresentationLayer.AdministratorGUI;
import PresentationLayer.ItemDeleteGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteItemListener implements ActionListener {

    private Restaurant restaurant;
    private AdministratorGUI administrator;
    private ItemDeleteGui deleteItemView;

    public DeleteItemListener(Restaurant restaurant, AdministratorGUI administrator) {

        this.administrator = administrator;
        this.restaurant = restaurant;


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        deleteItemView = new ItemDeleteGui(administrator.getModel(), restaurant);

        deleteItemView.setVisible(true);
        //deleteItemView.setComboBoxForDelete();
        deleteItemView.setListenerStergeBtn(new DoneDeletingItemListener(restaurant, administrator, deleteItemView));



    }
}
