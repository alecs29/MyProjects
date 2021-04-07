package PresentationLayer;

import BusinessLayer.BaseProduct;
import BusinessLayer.CompositeProduct;
import BusinessLayer.DoneAddingItemListener;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class ItemAddGui extends JFrame {

    private JPanel contentPane;
    private JTextField textField_id;
    private JTextField textField_nume;
    private JTextField textField_pret;
    private JButton btnGata;
    private JTable table;
    private DefaultTableModel model;
    private JRadioButton chckbxBaseProduct;
    private JRadioButton chckbxCompositeProduct;
    private JTextPane textPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

         //ItemAddGui frame = new ItemAddGui();
         //frame.setVisible(true);
    }

    /**
     * Create the frame.
     */
    public ItemAddGui(DefaultTableModel model) {
        this.model = model;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 723, 431);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(255, 140, 0));
        contentPane.add(desktopPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        panel.setBounds(0, 0, 699, 42);
        desktopPane.add(panel);

        JLabel lblIntroducetiUnItem = new JLabel("INTRODUCE-TI UN ITEM");
        lblIntroducetiUnItem.setFont(new Font("Tahoma", Font.BOLD, 12));
        panel.add(lblIntroducetiUnItem);

        JLabel lblId = new JLabel("ID:");
        lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblId.setBounds(10, 46, 26, 15);
        desktopPane.add(lblId);

        textField_id = new JTextField();
        textField_id.setBounds(43, 45, 96, 19);
        desktopPane.add(textField_id);
        textField_id.setColumns(10);

        JLabel lblNume = new JLabel("NUME:");
        lblNume.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNume.setBounds(151, 46, 45, 13);
        desktopPane.add(lblNume);

        textField_nume = new JTextField();
        textField_nume.setBounds(207, 46, 96, 19);
        desktopPane.add(textField_nume);
        textField_nume.setColumns(10);

        JLabel lblPret = new JLabel("PRET:");
        lblPret.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblPret.setBounds(313, 46, 45, 13);
        desktopPane.add(lblPret);

        textField_pret = new JTextField();
        textField_pret.setBounds(368, 45, 96, 19);
        desktopPane.add(textField_pret);
        textField_pret.setColumns(10);

        chckbxBaseProduct = new JRadioButton("BASE PRODUCT");
        chckbxBaseProduct.setBounds(10, 83, 144, 19);
        desktopPane.add(chckbxBaseProduct);

        chckbxCompositeProduct = new JRadioButton("COMPOSITE PRODUCT");
        chckbxCompositeProduct.setBounds(10, 117, 144, 21);
        desktopPane.add(chckbxCompositeProduct);

        ButtonGroup grup = new ButtonGroup();
        chckbxBaseProduct.setSelected(true);
        grup.add(chckbxBaseProduct);
        grup.add(chckbxCompositeProduct);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(235, 83, 459, 203);
        desktopPane.add(scrollPane);

        table = new JTable();
        table.setEnabled(false);

        table.setModel(model);


        scrollPane.setViewportView(table);

        JLabel lblInCazulAl = new JLabel("In cazul al doilea va rugam sa introduceti");
        lblInCazulAl.setBounds(10, 156, 197, 19);
        desktopPane.add(lblInCazulAl);

        JLabel lblSaIntroducetiIdurile = new JLabel("id-urile produselor din care va fii");
        lblSaIntroducetiIdurile.setBounds(10, 185, 192, 15);
        desktopPane.add(lblSaIntroducetiIdurile);

        JLabel lblCompusProdusul = new JLabel("compus produsul separate prin virgula");
        lblCompusProdusul.setBounds(10, 210, 220, 13);
        desktopPane.add(lblCompusProdusul);

        textPane = new JTextPane();
        textPane.setBounds(10, 233, 186, 42);
        desktopPane.add(textPane);

        btnGata = new JButton("GATA");

        btnGata.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnGata.setBounds(458, 319, 129, 30);
        desktopPane.add(btnGata);
    }

    public void addBaseItemToTable(BaseProduct product){
        Vector row = new Vector();
        row.add(product.getId());
        row.add(product.getName());
        row.add(product.getPrice());

        model.addRow(row);
    }
    public void addCompositeItemToTable(CompositeProduct product){
        Vector row = new Vector();
        row.add(product.getId());
        row.add(product.getName());
        row.add(product.getPrice());

        model.addRow(row);
    }



    public String getTextField_id() {
        return textField_id.getText();
    }

    public String getTextField_nume() {
        return textField_nume.getText();
    }

    public String getTextField_pret() {
        return textField_pret.getText();
    }

    public void setListenerGataBtn(DoneAddingItemListener gata){
        btnGata.addActionListener(gata);
    }

    public boolean isBaseProductSelected() {
        return chckbxBaseProduct.isSelected();
    }
    public boolean isCompositeProductSelected() {
        return chckbxCompositeProduct.isSelected();
    }

    public String getTextPaneText() {
        return textPane.getText();
    }


}
