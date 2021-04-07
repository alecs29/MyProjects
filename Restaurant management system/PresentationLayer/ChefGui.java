package PresentationLayer;

import BusinessLayer.Observer;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ChefGui extends JFrame implements Observer {

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

//        interfaceEditor.Chef frame = new interfaceEditor.Chef();
//        frame.setVisible(true);

    }

    /**
     * Create the frame.
     */
    public ChefGui() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 547, 356);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(255, 140, 0));
        contentPane.add(desktopPane);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 523, 61);
        desktopPane.add(panel);

        JLabel lblAdministrator = new JLabel("CHEF");
        panel.add(lblAdministrator);
        lblAdministrator.setFont(new Font("Tahoma", Font.BOLD, 14));

        JLabel lblMeniu = new JLabel("COMENZI NOI:");
        lblMeniu.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblMeniu.setBounds(10, 71, 123, 15);
        desktopPane.add(lblMeniu);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 96, 478, 191);
        desktopPane.add(scrollPane);

        table = new JTable();
        table.setBackground(UIManager.getColor("Button.background"));
        table.setModel(new DefaultTableModel(new Object[]{"Comanda", "masa", "produse"}, 0));
        model = (DefaultTableModel) table.getModel();

        scrollPane.setViewportView(table);
    }


    @Override
    public void update(ArrayList<Data> data) {
        int i=0;
        model.setRowCount(0);
        for(Data d : data) {
            model.addRow(new Object[]{data.get(i).getFirst(), data.get(i).getSecond(), data.get(i).getThird()});
            i++;
        }
    }
}
