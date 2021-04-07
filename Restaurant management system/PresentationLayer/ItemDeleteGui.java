package PresentationLayer;

import BusinessLayer.BaseProduct;
import BusinessLayer.CompositeProduct;
import BusinessLayer.MenuItem;
import BusinessLayer.Restaurant;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ItemDeleteGui extends JFrame {

    private  DefaultTableModel model;
    private JPanel contentPane;
    private JTable table;
    private JButton btnSterge;
    private JComboBox list;
    private JDesktopPane desktopPane;

    private Restaurant restaurant;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        //ItemDeleteGui frame = new ItemDeleteGui();
        //frame.setVisible(true);

    }

    /**
     * Create the frame.
     */
    public ItemDeleteGui(DefaultTableModel model, Restaurant restaurant) {

        this.model = model;
        this.restaurant = restaurant;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 416, 390);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(255, 140, 0));
        contentPane.add(desktopPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        panel.setBounds(0, 0, 392, 36);
        desktopPane.add(panel);

        JLabel lblIntroducetiUnItem = new JLabel("STERGETI UN ITEM");
        lblIntroducetiUnItem.setFont(new Font("Tahoma", Font.BOLD, 12));
        panel.add(lblIntroducetiUnItem);

        btnSterge = new JButton("STERGE");

        btnSterge.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSterge.setBounds(234, 46, 129, 30);
        desktopPane.add(btnSterge);

        setComboBoxForDelete(-1, null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(29, 103, 334, 194);
        desktopPane.add(scrollPane);

        table = new JTable();
        table.setEnabled(false);
        table.setModel(model);
        //table.setBounds(29, 103, 334, 194);
        scrollPane.setViewportView(table);



    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setComboBoxForDelete(int option, Object obj){

        String[] stuff;
        stuff = new String[restaurant.getMeniu().size()];

        int i=0;

            if(option != -1){//actualizare
                for (MenuItem itm : restaurant.getMeniu()) {//parcurgem
                    if (!(itm.equals(obj))) { //daca obj(deleted) e diferit de itemul din meniu
                        if (itm.base)//il adaugam
                            stuff[i] = ((BaseProduct) itm).getName();
                        else if (itm.composite)
                            stuff[i] = ((CompositeProduct) itm).getName();
                    }
                    i++;
                }
            }
            else{//initializare
                for (MenuItem itm : restaurant.getMeniu()) {
                    if (itm.base)
                        stuff[i] = ((BaseProduct) itm).getName();
                    else if (itm.composite)
                        stuff[i] = ((CompositeProduct) itm).getName();
                    i++;
                }
            }

        if(option != -1)
            desktopPane.remove(list);
        list = new JComboBox(stuff);
        list.setVisible(false);
        list.setVisible(true);

        list.setBounds(29, 53, 136, 19);
        desktopPane.add(list);
    }

    public void deleteBaseItemToTable(BaseProduct product){

        Vector<Vector> vec = model.getDataVector();

        for(int i=0;i<model.getRowCount();i++){
            if(vec.elementAt(i).get(1).equals(product.getName())){
                model.removeRow(i);
            }
        }

    }
    public void deleteCompositeItemToTable(CompositeProduct product){
        Vector<Vector> vec = model.getDataVector();

        for(int i=0;i<model.getRowCount();i++){
            if(vec.elementAt(i).get(1).equals(product.getName())){
                model.removeRow(i);
            }
        }
    }


    public void setListenerStergeBtn(ActionListener gata){

        btnSterge.addActionListener(gata);
    }

    public Object getListSelected() {

        return list.getSelectedItem();

    }
    public JComboBox getList() {

        return list;

    }

    public DefaultTableModel getModel() {
        return model;
    }

    public JTable getTable() {
        return table;
    }

    public JButton getBtnSterge() {
        return btnSterge;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public void setBtnSterge(JButton btnSterge) {
        this.btnSterge = btnSterge;
    }
    
}
