package PresentationLayer;
import BusinessLayer.*;
import BusinessLayer.CompositeProduct;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ItemModifyGui extends JFrame  {

    private Restaurant restaurant;
    private DefaultTableModel model;
    private JPanel contentPane;
    private JTable table;
    private JTextField textFieldEnterChange;
    private JTextField textFieldId;
    private JRadioButton rdbtnSchimbaNumeleProdusului;
    private JRadioButton rdbtnSchimbaCompozitiaUnui;
    private  JComboBox comboBoxProduct;//3 rd
    private  JComboBox comboBoxSubProduct;//4 th
    private  JComboBox chooseNameOrPrice;//2 nd
    private JComboBox comboBoxSelect;//1 st
    private  JButton btnSterge;
    private JButton btnStergeSubProduct;
    private JButton btnSchimbaSubProdus;
    private JButton btnSchimba;
    private JDesktopPane desktopPane;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        //ItemModifyGui frame = new ItemModifyGui(new DefaultTableModel());
        //frame.setVisible(true);

    }

    /**
     * Create the frame.
     */
    public ItemModifyGui(DefaultTableModel model, Restaurant restaurant) {
        this.model = model;
        this.restaurant = restaurant;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 717, 463);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(255, 140, 0));
        contentPane.add(desktopPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        panel.setBounds(0, 0, 693, 42);
        desktopPane.add(panel);

        JLabel lblIntroducetiUnItem = new JLabel("MODIFICATI UN ITEM");
        lblIntroducetiUnItem.setFont(new Font("Tahoma", Font.BOLD, 12));
        panel.add(lblIntroducetiUnItem);

        textFieldEnterChange = new JTextField();
        textFieldEnterChange.setBounds(110, 129, 96, 19);
        desktopPane.add(textFieldEnterChange);
        textFieldEnterChange.setColumns(10);

        btnSchimba = new JButton("SCHIMBA");
        btnSchimba.setBounds(226, 128, 90, 21);
        desktopPane.add(btnSchimba);

        rdbtnSchimbaNumeleProdusului = new JRadioButton("Schimba numele/pretul produsului:");
        rdbtnSchimbaNumeleProdusului.setBounds(10, 48, 230, 21);
        desktopPane.add(rdbtnSchimbaNumeleProdusului);

        rdbtnSchimbaCompozitiaUnui = new JRadioButton("Schimba compozitia unui produs compus:");
        rdbtnSchimbaCompozitiaUnui.setBounds(10, 277, 270, 21);
        desktopPane.add(rdbtnSchimbaCompozitiaUnui);

        ButtonGroup group = new ButtonGroup();
        rdbtnSchimbaNumeleProdusului.setSelected(true);
        group.add(rdbtnSchimbaCompozitiaUnui);
        group.add(rdbtnSchimbaNumeleProdusului);


        btnSterge = new JButton("STERGE");
        btnSterge.setBounds(248, 324, 85, 21);
        //desktopPane.add(btnSterge);

        JLabel label = new JLabel("Produs:");
        label.setBounds(10, 328, 45, 21);
        desktopPane.add(label);

        JLabel lblAdaugaAltul = new JLabel("Adauga alt subprodus, ID:");
        lblAdaugaAltul.setBounds(299, 376, 148, 13);
        desktopPane.add(lblAdaugaAltul);

        textFieldId = new JTextField();
        textFieldId.setBounds(468, 373, 96, 19);
        desktopPane.add(textFieldId);
        textFieldId.setColumns(10);

        btnSchimbaSubProdus = new JButton("ADAUGA");
        btnSchimbaSubProdus.setBounds(585, 372, 95, 21);
        desktopPane.add(btnSchimbaSubProdus);

        JLabel lblProdus = new JLabel("Produs:");
        lblProdus.setBounds(10, 90, 45, 21);
        desktopPane.add(lblProdus);

        setFirstComboBox(-1, restaurant);
        setSecondComboBox();
        setThirdComboBox(-1, restaurant);
        setFourthComboBox(-1, restaurant, null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(328, 51, 355, 224);
        desktopPane.add(scrollPane);

        table = new JTable();
        table.setModel(model);
        table.setEnabled(false);
        //table.setBounds(328, 51, 355, 224);
        scrollPane.setViewportView(table);


        btnStergeSubProduct = new JButton("STERGE");
        btnStergeSubProduct.setBounds(181, 372, 85, 21);
        desktopPane.add(btnStergeSubProduct);


    }

    public void refreshTable(){
        model.setRowCount(0);

        for(MenuItem itm : restaurant.getMeniu()){
            if(itm.base) {
                Object[] obj = new Object[]{((BaseProduct)itm).getId(), ((BaseProduct)itm).getName(),
                        ((BaseProduct)itm).getPrice()};
                model.addRow(obj);
            }
            else if(itm.composite){
                Object[] obj = new Object[]{((CompositeProduct)itm).getId(), ((CompositeProduct)itm).getName(),
                        ((CompositeProduct)itm).getPrice()};
                model.addRow(obj);
            }
        }
    }

    public void setFirstComboBox(int option, Restaurant restaurant){////--------------------------comboBox1

        String[] stuff;
        stuff = new String[restaurant.getMeniu().size()];

        int i=0;

        for (MenuItem itm : restaurant.getMeniu()) {
            if (itm.base) {
                stuff[i] = ((BaseProduct) itm).getName();
            }
            else if (itm.composite) {
                stuff[i] = ((CompositeProduct) itm).getName();
            }

            i++;
        }


        if(option != -1)
            desktopPane.remove(comboBoxSelect);


        comboBoxSelect = new JComboBox(stuff);
        comboBoxSelect.setBounds(58, 90, 161, 21);
        comboBoxSelect.setVisible(false);
        comboBoxSelect.setVisible(true);
        desktopPane.add(comboBoxSelect);
    }

    public void setSecondComboBox() {////--------------------------comboBox2

        String[] stuff;
        stuff = new String[]{"numele", "pretul"};

        chooseNameOrPrice = new JComboBox(stuff);
        chooseNameOrPrice.setBounds(10, 128, 76, 21);
        desktopPane.add(chooseNameOrPrice);
    }

    public void setThirdComboBox(int option, Restaurant restaurant){////--------------------------comboBox3

        String[] stuff;
        int size = 0;
        for(MenuItem itm : restaurant.getMeniu()){
            if (itm.composite) {
                size++;
            }
        }
        stuff = new String[size];

        int i=0;
        for (MenuItem itm : restaurant.getMeniu()) {

            if (itm.composite) {
                stuff[i] = ((CompositeProduct) itm).getName();
                i++;
            }


        }

        if(option != -1)
            desktopPane.remove(comboBoxProduct);

        comboBoxProduct = new JComboBox(stuff);
        comboBoxProduct.setBounds(65, 324, 161, 21);
        comboBoxProduct.setVisible(false);
        comboBoxProduct.setVisible(true);
//        comboBoxProduct.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                //cand vom schimba produsul vom seta si subprodusele in comboBox
//                setFourthComboBox(0, restaurant, (String) comboBoxProduct.getSelectedItem());
//            }
//        });
        desktopPane.add(comboBoxProduct);
    }


    public void setFourthComboBox(int option, Restaurant restaurant, String itemName){////--------------------------comboBox4

        String str;
        CompositeProduct item = null;
        


        if(option != -1) {//When product is selected

            for(MenuItem i : restaurant.getMeniu()){//cautam stringul 

                if (i.composite) {
                    str = ((CompositeProduct) i).getName();
                    if(str.equals(itemName)){
                        item = (CompositeProduct) i;
                        break;
                    }
                }
            }
            

            String[] stuff;

            stuff = new String[item.getSubproducts().size()];

            int i = 0;

            for (MenuItem itm : item.getSubproducts()) {//add subproducts
                if (itm.base) {
                    stuff[i] = ((BaseProduct) itm).getName();
                } else if (itm.composite) {
                    stuff[i] = ((CompositeProduct) itm).getName();
                }

                i++;
            }

            desktopPane.remove(comboBoxSubProduct);


            comboBoxSubProduct = new JComboBox(stuff);
        }
        else {      //when initialising (-1)
            comboBoxSubProduct = new JComboBox();
        }
        comboBoxSubProduct.setBounds(10, 372, 161, 21);
        comboBoxSubProduct.setVisible(false);
        comboBoxSubProduct.setVisible(true);
        desktopPane.add(comboBoxSubProduct);
    }

    public void setListenerSchimba(ActionListener done){//1
        btnSchimba.addActionListener(done);
    }
    public void setListenerSterge(ActionListener done){//2
        btnSterge.addActionListener(done);
    }
    public void setListenerStergeSubProdus(ActionListener done){//3
        btnStergeSubProduct.addActionListener(done);
    }
    public void setListenerSchimbaCompozitie(ActionListener done){//4
        btnSchimbaSubProdus.addActionListener(done);
    }
    public void setListenerRefreshSubProductComboBox(ActionListener done){
        comboBoxProduct.addActionListener(done);
    }

    @Override
    public JPanel getContentPane() {
        return contentPane;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public JTable getTable() {
        return table;
    }

    public JTextField getTextFieldEnterChange() {
        return textFieldEnterChange;
    }

    public JTextField getTextFieldId() {
        return textFieldId;
    }

    public JComboBox getComboBoxSelect() {
        return comboBoxSelect;
    }

    public JButton getBtnSchimba() {
        return btnSchimba;
    }

    public JRadioButton getRdbtnSchimbaNumeleProdusului() {
        return rdbtnSchimbaNumeleProdusului;
    }

    public JRadioButton getRdbtnSchimbaCompozitiaUnui() {
        return rdbtnSchimbaCompozitiaUnui;
    }

    public JComboBox getComboBoxProduct() {
        return comboBoxProduct;
    }

    public JComboBox getComboBoxSubProduct() {
        return comboBoxSubProduct;
    }

    public JComboBox getChooseNameOrPrice() {
        return chooseNameOrPrice;
    }

    public JButton getBtnSterge() {
        return btnSterge;
    }

    public JButton getBtnSchimbaSubProdus() {
        return btnSchimbaSubProdus;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

    public void setContentOfContentPane(JPanel contentPane) {
        this.contentPane = contentPane;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public void setTextFieldEnterChange(JTextField textFieldEnterChange) {
        this.textFieldEnterChange = textFieldEnterChange;
    }

    public void setTextFieldId(JTextField textFieldId) {
        this.textFieldId = textFieldId;
    }

    public void setComboBoxSelect(JComboBox comboBoxSelect) {
        this.comboBoxSelect = comboBoxSelect;
    }

    public void setBtnSchimba(JButton btnSchimba) {
        this.btnSchimba = btnSchimba;
    }

    public void setRdbtnSchimbaNumeleProdusului(JRadioButton rdbtnSchimbaNumeleProdusului) {
        this.rdbtnSchimbaNumeleProdusului = rdbtnSchimbaNumeleProdusului;
    }

    public void setRdbtnSchimbaCompozitiaUnui(JRadioButton rdbtnSchimbaCompozitiaUnui) {
        this.rdbtnSchimbaCompozitiaUnui = rdbtnSchimbaCompozitiaUnui;
    }

    public void setComboBoxProduct(JComboBox comboBoxProduct) {
        this.comboBoxProduct = comboBoxProduct;
    }

    public void setComboBoxSubProduct(JComboBox comboBoxSubProduct) {
        this.comboBoxSubProduct = comboBoxSubProduct;
    }

    public void setChooseNameOrPrice(JComboBox chooseNameOrPrice) {
        this.chooseNameOrPrice = chooseNameOrPrice;
    }

    public void setBtnSterge(JButton btnSterge) {
        this.btnSterge = btnSterge;
    }

    public void setBtnSchimbaSubProdus(JButton btnSchimbaSubProdus) {
        this.btnSchimbaSubProdus = btnSchimbaSubProdus;
    }

}
