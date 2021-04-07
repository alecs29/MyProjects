package PresentationLayer;


import BusinessLayer.*;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class WaiterGUI extends JFrame implements IRestaurantProcessing, Subject {

	private static Restaurant restaurant;
	private JTable table;
	private JPanel contentPane;
	private JButton btnAddOrder;
	private JButton btnViewOrders;
	private JButton btnMakeBill;
	private int index = 0;
	private DefaultTableModel model;
	private Observer observer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		//WaiterGUI frame = new WaiterGUI(restaurant);
		//frame.setVisible(true);

	}

	/**
	 * Create the frame.
	 */
	public WaiterGUI(Restaurant restaurant, Observer observer) {

		this.restaurant = restaurant;
		this.observer = observer;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(255, 140, 0));
		contentPane.add(desktopPane);

		btnAddOrder= new JButton("ADD ORDER");
		btnAddOrder.setBounds(10, 80, 120, 26);
		desktopPane.add(btnAddOrder);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 519, 61);
		desktopPane.add(panel);

		JLabel lblWaiter = new JLabel("WAITER");
		panel.add(lblWaiter);
		lblWaiter.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnMakeBill = new JButton("MAKE BILL");
		btnMakeBill.setBounds(360, 305, 129, 26);
		desktopPane.add(btnMakeBill);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(141, 134, 348, 160);
		scrollPane.setHorizontalScrollBar(new JScrollBar());
		scrollPane.setVisible(false);


		table = new JTable();
		table.setBackground(UIManager.getColor("Button.background"));
		table.setModel(new DefaultTableModel(new Object[]{"Comanda", "masa", "produse"}, 0));
		model = (DefaultTableModel) table.getModel();


		//ArrayList<String> list = restaurant.showOnTable(0);
		//model.addRow(new Object[]{list.get(0),list.get(1),list.get(2)});

		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(1).setPreferredWidth(25);

		scrollPane.setViewportView(table);
		desktopPane.add(scrollPane);

		JLabel lblOrders = new JLabel("Orders:");
		lblOrders.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblOrders.setBounds(141, 87, 76, 19);
		lblOrders.setVisible(false);
		desktopPane.add(lblOrders);

		btnViewOrders = new JButton("VIEW ORDERS");
		btnViewOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(index % 2 == 0) {
					lblOrders.setVisible(true);
					scrollPane.setVisible(true);
				}
				else{
					lblOrders.setVisible(false);
					scrollPane.setVisible(false);
				}

				index++;
			}
		});
		btnViewOrders.setBounds(10, 134, 120, 26);
		desktopPane.add(btnViewOrders);


	}

	public void refreshTable(){

		model.setRowCount(0);

		int i=0;
		ArrayList<Data> data = new ArrayList<Data>();
		for(Order ord : restaurant.getOrders()){

			ArrayList<String> list = restaurant.showOnTable(i);
			model.addRow(new Object[]{list.get(0),list.get(1),list.get(2)});

			data.add(new Data(list.get(0),list.get(1),list.get(2)));

			i++;

		}
		notifyObsevers(data);

		table.setVisible(false);
		table.setVisible(true);
	}


	public void setAddOrderListener(ActionListener addOrd){
		btnAddOrder.addActionListener(addOrd);
	}

	public void setMakeBillListener(ActionListener bill){
		btnMakeBill.addActionListener(bill);
	}


	@Override
	public void addBaseItem(int id, String nume, double pret) {

	}

	@Override
	public void addCompositeItem(int id, String nume, double pret, ArrayList<MenuItem> list) {

	}

	@Override
	public void deleteItem(MenuItem item) {

	}

	@Override
	public void schimbaNume(MenuItem item, String name) {

	}

	@Override
	public void schimbaPret(MenuItem item, Double pret) {

	}

	@Override
	public void adaugaSubProdus(CompositeProduct item, MenuItem temp) {

	}

	@Override
	public void stergeSubProduse(CompositeProduct item, MenuItem temp) {

	}


	public void setGenerateBill(ActionListener done) {
		btnMakeBill.addActionListener(done);
	}


	@Override
	public void notifyObsevers(ArrayList<Data> list) {
		observer.update(list);
	}


}
