package PresentationLayer;
import BusinessLayer.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class AdministratorGUI extends JFrame {


	private static Restaurant restaurant;
	private JPanel contentPane;
	private JButton btnAddItem;
	private JButton btnDeleteItem;
	private JButton btnModifyItem;
	private JButton btnAfiseazaMeniu;
	private JTable table;
	private DefaultTableModel model;
	private int index;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		AdministratorGUI frame = new AdministratorGUI(restaurant);
		frame.setVisible(true);

	}

	/**
	 * Create the frame.
	 */
	public AdministratorGUI(Restaurant restaurant) {

		this.restaurant = restaurant;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(255, 140, 0));
		contentPane.add(desktopPane);

		btnAddItem = new JButton("ADD ITEM");//-------------------------add btn


		btnAddItem.setBounds(10, 80, 129, 26);
		desktopPane.add(btnAddItem);

		btnDeleteItem = new JButton("DELETE ITEM");//---------------------------delete btn

		btnDeleteItem.setBounds(10, 134, 129, 26);
		desktopPane.add(btnDeleteItem);

		btnModifyItem = new JButton("MODIFY ITEM");//---------------------------modify btn

		btnModifyItem.setBounds(10, 192, 129, 26);
		desktopPane.add(btnModifyItem);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 519, 61);
		desktopPane.add(panel);

		JLabel lblAdministrator = new JLabel("ADMINISTRATOR");
		panel.add(lblAdministrator);
		lblAdministrator.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblMeniu = new JLabel("MENIU:");//--------------------------meniu lbl
		lblMeniu.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMeniu.setBounds(161, 87, 84, 15);
		lblMeniu.setVisible(false);
		desktopPane.add(lblMeniu);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(161, 134, 327, 153);
		scrollPane.setVisible(false);
		desktopPane.add(scrollPane);


		table = new JTable();//-------------------------------------------table
		table.setBackground(UIManager.getColor("Button.background"));
		table.setModel(new DefaultTableModel(new Object[]{"id", "nume", "pret"}, 0));
		model = (DefaultTableModel) table.getModel();

		model.addRow(new Object[]{((BaseProduct)restaurant.getMeniu().get(0)).getId(),
				((BaseProduct)restaurant.getMeniu().get(0)).getName(),
				((BaseProduct)restaurant.getMeniu().get(0)).getPrice()});

		model.addRow(new Object[]{((BaseProduct)restaurant.getMeniu().get(1)).getId(),
				((BaseProduct)restaurant.getMeniu().get(1)).getName(),
				((BaseProduct)restaurant.getMeniu().get(1)).getPrice()});

		model.addRow(new Object[]{((BaseProduct)restaurant.getMeniu().get(2)).getId(),
				((BaseProduct)restaurant.getMeniu().get(2)).getName(),
				((BaseProduct)restaurant.getMeniu().get(2)).getPrice()});

		model.addRow(new Object[]{((BaseProduct)restaurant.getMeniu().get(3)).getId(),
				((BaseProduct)restaurant.getMeniu().get(3)).getName(),
				((BaseProduct)restaurant.getMeniu().get(3)).getPrice()});

		model.addRow(new Object[]{((BaseProduct)restaurant.getMeniu().get(4)).getId(),
				((BaseProduct)restaurant.getMeniu().get(4)).getName(),
				((BaseProduct)restaurant.getMeniu().get(4)).getPrice()});

		model.addRow(new Object[]{((BaseProduct)restaurant.getMeniu().get(5)).getId(),
				((BaseProduct)restaurant.getMeniu().get(5)).getName(),
				((BaseProduct)restaurant.getMeniu().get(5)).getPrice()});

		model.addRow(new Object[]{((BaseProduct)restaurant.getMeniu().get(6)).getId(),
				((BaseProduct)restaurant.getMeniu().get(6)).getName(),
				((BaseProduct)restaurant.getMeniu().get(6)).getPrice()});

		model.addRow(new Object[]{((BaseProduct)restaurant.getMeniu().get(7)).getId(),
				((BaseProduct)restaurant.getMeniu().get(7)).getName(),
				((BaseProduct)restaurant.getMeniu().get(7)).getPrice()});

		model.addRow(new Object[]{((BaseProduct)restaurant.getMeniu().get(8)).getId(),
				((BaseProduct)restaurant.getMeniu().get(8)).getName(),
				((BaseProduct)restaurant.getMeniu().get(8)).getPrice()});

		model.addRow(new Object[]{((BaseProduct)restaurant.getMeniu().get(9)).getId(),
				((BaseProduct)restaurant.getMeniu().get(9)).getName(),
				((BaseProduct)restaurant.getMeniu().get(9)).getPrice()});

		model.addRow(new Object[]{((CompositeProduct)restaurant.getMeniu().get(10)).getId(),
				((CompositeProduct)restaurant.getMeniu().get(10)).getName(),
				((CompositeProduct)restaurant.getMeniu().get(10)).getPrice()});

		model.addRow(new Object[]{((CompositeProduct)restaurant.getMeniu().get(11)).getId(),
				((CompositeProduct)restaurant.getMeniu().get(11)).getName(),
				((CompositeProduct)restaurant.getMeniu().get(11)).getPrice()});

		model.addRow(new Object[]{((CompositeProduct)restaurant.getMeniu().get(12)).getId(),
				((CompositeProduct)restaurant.getMeniu().get(12)).getName(),
				((CompositeProduct)restaurant.getMeniu().get(12)).getPrice()});



		scrollPane.setViewportView(table);

		btnAfiseazaMeniu = new JButton("AFISEAZA MENIU");//---------------------------afiseaza meniu btn
		btnAfiseazaMeniu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(index % 2 == 0) {
					lblMeniu.setVisible(true);
					scrollPane.setVisible(true);
				}
				else{
					lblMeniu.setVisible(false);
					scrollPane.setVisible(false);
				}

				index++;
			}
		});
		btnAfiseazaMeniu.setBounds(360, 305, 138, 26);
		desktopPane.add(btnAfiseazaMeniu);

	}


	public static Restaurant getRestaurant() {
		return restaurant;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setBtnAfiseazaMeniu(JButton btnAfiseazaMeniu) {
		this.btnAfiseazaMeniu = btnAfiseazaMeniu;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public JButton getBtnAddItem() {
		return btnAddItem;
	}

	public JButton getBtnDeleteItem() {
		return btnDeleteItem;
	}

	public JButton getBtnModifyItem() {
		return btnModifyItem;
	}

	public void setBtnAddItem(JButton btnAddItem) {
		this.btnAddItem = btnAddItem;
	}

	public void setBtnDeleteItem(JButton btnDeleteItem) {
		this.btnDeleteItem = btnDeleteItem;
	}

	public void setBtnModifyItem(JButton btnModifyItem) {
		this.btnModifyItem = btnModifyItem;
	}


	public void setAddItemListener(ActionListener addItm){
		btnAddItem.addActionListener(addItm);

	}
	public void  setDeleteItemListener(ActionListener delItm){
		btnDeleteItem.addActionListener(delItm);

	}
	public void setModifyItemListener(ActionListener modItm){
		btnModifyItem.addActionListener(modItm);
	}

}
