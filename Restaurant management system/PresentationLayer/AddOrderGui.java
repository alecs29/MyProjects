package PresentationLayer;

import BusinessLayer.*;
import BusinessLayer.CompositeProduct;
import BusinessLayer.MenuItem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class AddOrderGui extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JButton btnGata;
    private JComboBox list;
    private JDesktopPane desktopPane;
    private JButton btnPlaseazaComanda;
    private Restaurant restaurant;
    private DefaultTableModel model;
    private JTextField textField;

    private ArrayList<MenuItem> elements = new ArrayList<MenuItem>();

    public void addTo(MenuItem item){
        this.elements.add(item);
    }


    /**
     * Launch the application.
     */
    public static void main(String[] args) {

         //AddOrderGui frame = new AddOrderGui();
         //frame.setVisible(true);

    }

    /**
     * Create the frame.
     */
    public AddOrderGui(Restaurant restaurant) {

        this.restaurant = restaurant;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 451, 428);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(255, 140, 0));
        contentPane.add(desktopPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        panel.setBounds(0, 0, 427, 36);
        desktopPane.add(panel);

        JLabel lblIntroducetiUnItem = new JLabel("ADAUGATI O COMANDA ");
        lblIntroducetiUnItem.setFont(new Font("Tahoma", Font.BOLD, 12));
        panel.add(lblIntroducetiUnItem);

        btnGata = new JButton("ADAUGA");
        btnGata.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnGata.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnGata.setBounds(268, 82, 129, 19);
        desktopPane.add(btnGata);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(29, 126, 368, 194);
        desktopPane.add(scrollPane);

        setComboBoxForDelete(-1);

        table = new JTable();
        table.setEnabled(false);

        table.setBackground(UIManager.getColor("Button.background"));
        table.setModel(new DefaultTableModel(new Object[]{"id", "denumire", "pret"}, 0));
        model = (DefaultTableModel) table.getModel();

//        table.setModel(new DefaultTableModel(
//                new Object[][] {
//                        {null, null, null, null},
//                        {null, null, null, null},
//                        {null, null, null, null},
//                        {null, null, null, null},
//                        {null, null, null, null},
//                        {null, null, null, null},
//                        {null, null, null, null},
//                        {null, null, null, null},
//                        {null, null, null, null},
//                        {null, null, null, null},
//                        {null, null, null, null},
//                        {null, null, null, null},
//                        {null, null, null, null},
//                        {null, null, null, null},
//                },
//                new String[] {
//                        "nr. produs", "denumire", "New column", "New column"
//                }
//        ));

        scrollPane.setViewportView(table);

        btnPlaseazaComanda = new JButton("PLASEAZA COMANDA");
        btnPlaseazaComanda.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnPlaseazaComanda.setBounds(234, 341, 163, 30);
        desktopPane.add(btnPlaseazaComanda);

        JLabel lblAdaugatiProduseIn = new JLabel("Adaugati produse, introduceti nr. mesei si apoi plasati comanda");
        lblAdaugatiProduseIn.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblAdaugatiProduseIn.setBounds(29, 46, 370, 13);
        desktopPane.add(lblAdaugatiProduseIn);

        JLabel lblMasa = new JLabel("Masa:");
        lblMasa.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblMasa.setBounds(29, 350, 45, 13);
        desktopPane.add(lblMasa);

        textField = new JTextField();
        textField.setBounds(69, 347, 96, 19);
        desktopPane.add(textField);
        textField.setColumns(10);
    }

    public void setComboBoxForDelete(int option) {

        String[] stuff;
        stuff = new String[restaurant.getMeniu().size()];

        int i = 0;


        for (MenuItem itm : restaurant.getMeniu()) {//parcurgem

            if (itm.base)//il adaugam
                stuff[i] = ((BaseProduct) itm).getName();
            else if (itm.composite)
                stuff[i] = ((CompositeProduct) itm).getName();

            i++;
        }


        if (option != -1)
            desktopPane.remove(list);

        list = new JComboBox(stuff);
        list.setVisible(false);
        list.setVisible(true);

        list.setBounds(29, 82, 136, 19);
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


    public void setBtnAddProductToCommandListener(ActionListener done){
        btnGata.addActionListener(done);
    }

    public void setPlaseazaComandaListener(ActionListener done){
        btnPlaseazaComanda.addActionListener(done);
    }

    public JTable getTable() {
        return table;
    }

    public JButton getBtnGata() {
        return btnGata;
    }

    public JComboBox getList() {
        return list;
    }

    public JDesktopPane getDesktopPane() {
        return desktopPane;
    }

    public JButton getBtnPlaseazaComanda() {
        return btnPlaseazaComanda;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public DefaultTableModel getModel() {
        return model;
    }


    public void setTable(JTable table) {
        this.table = table;
    }

    public void setBtnGata(JButton btnGata) {
        this.btnGata = btnGata;
    }

    public void setList(JComboBox list) {
        this.list = list;
    }

    public void setDesktopPane(JDesktopPane desktopPane) {
        this.desktopPane = desktopPane;
    }

    public void setBtnPlaseazaComanda(JButton btnPlaseazaComanda) {
        this.btnPlaseazaComanda = btnPlaseazaComanda;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

    public JTextField getTextField() {
        return textField;
    }

    public ArrayList<MenuItem> getElements() {
        return elements;
    }

    public void setElements(ArrayList<MenuItem> elements) {
        this.elements = elements;
    }

    public void addElements(MenuItem itm) {
        elements.add(itm);
    }
}
