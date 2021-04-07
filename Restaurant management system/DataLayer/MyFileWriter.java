package DataLayer;

import BusinessLayer.BaseProduct;
import BusinessLayer.CompositeProduct;
import BusinessLayer.Order;
import BusinessLayer.Restaurant;

import java.io.IOException;

public class MyFileWriter {

    private Restaurant restaurant;

    public MyFileWriter(Restaurant restaurant) {

        System.out.println("pressed\n");

        this.restaurant = restaurant;

        java.io.FileWriter wr = null;
        try {
            wr = new java.io.FileWriter("D:\\faculta\\ANUL 2\\SEMESTRUL2\\TP\\Assignment4\\Bill.txt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            wr.write("****************************BILL****************************\n\n\n");
            int i=0;
            for(Order ord : restaurant.getOrders()){

                wr.write("====Chitanta masa "+ i +"====\n");
                double total = 0;

                for(int j=0;  j < restaurant.getCommands().get(restaurant.getOrders().get(i)).size();  j++){
                    if(restaurant.getCommands().get(restaurant.getOrders().get(i)).get(j).base) {

                        wr.write((((BaseProduct) restaurant.getCommands().get(restaurant.getOrders().get(i)).get(j)).getName()));
                        wr.write("------------------------");
                        total = total + ((BaseProduct) restaurant.getCommands().get(restaurant.getOrders().get(i)).get(j)).getPrice();
                        wr.write(Double.toString(((BaseProduct) restaurant.getCommands().get(restaurant.getOrders().get(i)).get(j)).getPrice()));
                        wr.write("\n");
                    }

                    else if(restaurant.getCommands().get(restaurant.getOrders().get(i)).get(j).composite){
                        wr.write((((CompositeProduct) restaurant.getCommands().get(restaurant.getOrders().get(i)).get(j)).getName()));
                        wr.write("------------------------");
                        total = total + ((BaseProduct) restaurant.getCommands().get(restaurant.getOrders().get(i)).get(j)).getPrice();
                        wr.write(Double.toString(((CompositeProduct) restaurant.getCommands().get(restaurant.getOrders().get(i)).get(j)).getPrice()));
                        wr.write("\n");
                    }

                }
                wr.write("===================================== total " + Double.toString(total) + " ron\n\n");

                i++;

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            wr.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
