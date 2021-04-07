package BusinessLayer;

import BusinessLayer.CompositeProduct;
import BusinessLayer.Restaurant;
import PresentationLayer.AdministratorGUI;
import PresentationLayer.ItemAddGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import BusinessLayer.*;

public class DoneAddingItemListener implements ActionListener {

    private Restaurant restaurant;
    private AdministratorGUI administrator;
    private ItemAddGui addItemView;
    private int index = 0;

    public DoneAddingItemListener(Restaurant restaurant, AdministratorGUI administrator, ItemAddGui addOnMueniu) {

        this.administrator = administrator;
        this.restaurant = restaurant;
        this.addItemView = addOnMueniu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int id = Integer.parseInt(addItemView.getTextField_id());
        String nume = addItemView.getTextField_nume();
        double pret = Double.parseDouble(addItemView.getTextField_pret());

        if(addItemView.isBaseProductSelected()){
            restaurant.addBaseItem(id, nume, pret);
            addItemView.addBaseItemToTable(new BaseProduct(id, nume, pret));
        }

        else if(addItemView.isCompositeProductSelected()){

            String idsString = addItemView.getTextPaneText();//get ids of subproducts
            idsString.trim();
            String[] parts = idsString.split("\\s+");
            ArrayList<MenuItem> list = new ArrayList<MenuItem>();

            for(String nr : parts){
                if(nr.matches("\\d+")) {//daca e nr. valid
                    System.out.println(nr);
                    for(int i=0 ; i < restaurant.getMeniu().size(); i++){//il cautam
                        if(restaurant.getMeniu().get(i).base)
                        {
                            if(((BaseProduct)restaurant.getMeniu().get(i)).getId() == Integer.parseInt(nr))
                                list.add((BaseProduct)restaurant.getMeniu().get(i));


                        }
                        else if(restaurant.getMeniu().get(i).composite)
                        {
                            if(((CompositeProduct)restaurant.getMeniu().get(i)).getId() == Integer.parseInt(nr))
                                list.add((CompositeProduct)restaurant.getMeniu().get(i));


                        }

                    }

                }

            }

            restaurant.addCompositeItem(id, nume, pret, list);
            addItemView.addCompositeItemToTable(new CompositeProduct(id, nume, pret, list));
        }


    }
}
