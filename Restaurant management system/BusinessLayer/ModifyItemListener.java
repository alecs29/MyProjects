package BusinessLayer;

import PresentationLayer.AdministratorGUI;
import PresentationLayer.ItemModifyGui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyItemListener implements ActionListener {

    private Restaurant restaurant;
    private AdministratorGUI administrator;
    private ItemModifyGui modifyItemView;

    public ModifyItemListener(Restaurant restaurant, AdministratorGUI administrator) {
        this.administrator = administrator;
        this.restaurant = restaurant;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        modifyItemView = new ItemModifyGui(administrator.getModel(), restaurant);
        modifyItemView.setVisible(true);

        modifyItemView.setListenerSchimba(new ItemBtnChangeListener(restaurant, administrator, modifyItemView));
        //modifyItemView.setListenerSterge(new ItemBtnDeleteCompositeProduct(restaurant, administrator, modifyItemView));
        modifyItemView.setListenerSchimbaCompozitie(new ItemBtnAddSubProducts(restaurant, administrator, modifyItemView));
        modifyItemView.setListenerStergeSubProdus(new ItemBtnDeleteSubProducts(restaurant, administrator, modifyItemView));

        modifyItemView.setListenerRefreshSubProductComboBox(new RefreshSubProductComboBoxListener(restaurant, administrator, modifyItemView));

    }


}
