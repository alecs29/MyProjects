package BusinessLayer;
import BusinessLayer.Restaurant;
import PresentationLayer.AdministratorGUI;
import PresentationLayer.ItemModifyGui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefreshSubProductComboBoxListener implements ActionListener {

    private Restaurant restaurant;
    private AdministratorGUI administrator;
    private ItemModifyGui modifyItemView;

    public RefreshSubProductComboBoxListener(Restaurant restaurant, AdministratorGUI administrator, ItemModifyGui modifyItemView) {

        this.administrator = administrator;
        this.restaurant = restaurant;
        this.modifyItemView = modifyItemView;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("Change made!\n");
        modifyItemView.setFourthComboBox(0, restaurant, (String) modifyItemView.getComboBoxProduct().getSelectedItem());
        System.out.println("wtf?!\n");

    }
}
