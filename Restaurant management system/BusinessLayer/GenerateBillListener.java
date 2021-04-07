package BusinessLayer;

import BusinessLayer.BaseProduct;
import BusinessLayer.CompositeProduct;
import BusinessLayer.Order;
import BusinessLayer.Restaurant;
import DataLayer.MyFileWriter;
import PresentationLayer.AddOrderGui;
import PresentationLayer.AdministratorGUI;
import PresentationLayer.WaiterGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateBillListener implements ActionListener {

    private WaiterGUI waiter;
    private Restaurant restaurant;
    private AdministratorGUI administrator;
    private AddOrderGui addOrderView;


    public GenerateBillListener(Restaurant restaurant, AdministratorGUI administrator, WaiterGUI waiter) {
        this.restaurant = restaurant;
        this.administrator = administrator;
        this.waiter = waiter;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("pressed\n");

        MyFileWriter writer = new MyFileWriter(restaurant);




    }
}
