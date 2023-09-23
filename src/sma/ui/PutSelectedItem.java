package sma.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sma.db.DBOperation;
import sma.object.Customer;
import sma.object.Invoice;
import sma.object.Item;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class PutSelectedItem extends JFrame {

	private JPanel contentPane;
	private JTextField txtCustomerId;
	private JTextField txtCustomerName;
	DefaultTableModel model = new DefaultTableModel();
	DefaultTableModel model2 = new DefaultTableModel();

	private JTable table;
	private JTable table2;
	static Connection conn = DBOperation.createConnection("jdbc:mysql://localhost:3306/supermarket", "phuocvo", "123456");
	private JTextField txtItemId;
	private JTextField txtInvoiceId;
	private JTextField txtTradingTime;
	private JTextField txtTotal;
	private JTextField txtQuantity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//					PutSelectedItem frame = new PutSelectedItem();
					//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PutSelectedItem(Customer customer) {
		setTitle("Put item to cart");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 961, 702);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("  Customer Information");
		lblNewLabel.setBounds(10, 11, 133, 20);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 538, 71);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Customer ID:");
		lblNewLabel_1.setBounds(10, 36, 78, 14);
		panel.add(lblNewLabel_1);

		txtCustomerId = new JTextField();
		txtCustomerId.setText((String) null);
		txtCustomerId.setEditable(false);
		txtCustomerId.setColumns(10);
		txtCustomerId.setBounds(98, 33, 100, 20);
		panel.add(txtCustomerId);
		txtCustomerId.setText(String.valueOf(customer.getCustomerId()));

		JLabel lblNewLabel_1_1 = new JLabel("Customer Name:");
		lblNewLabel_1_1.setBounds(282, 33, 100, 14);
		panel.add(lblNewLabel_1_1);

		txtCustomerName = new JTextField();
		txtCustomerName.setText((String) null);
		txtCustomerName.setEditable(false);
		txtCustomerName.setColumns(10);
		txtCustomerName.setBounds(392, 30, 100, 20);
		panel.add(txtCustomerName);
		txtCustomerName.setText(customer.getCustomerName());

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 165, 925, 457);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 462, 407);
		panel_1.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int row = table.getSelectedRow();
				String itemId = table.getValueAt(row, 0).toString();
				txtItemId.setText(itemId);
				//searchData1(itemId);
			}
		});
		String[] columnNames = {"Item ID", "Item Name", "Category", "Measurement", "Remaining", "Unit Price"};
		(model).setColumnIdentifiers(columnNames);

		scrollPane.setViewportView(table);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(482, 40, 433, 279);
		panel_1.add(scrollPane_1);
		String[] columnNames1 = {"Item ID", "Item Name", "Quantity", "Unit Price", "Price"};
		(model2).setColumnIdentifiers(columnNames1);
		table2 = new JTable();

		scrollPane_1.setViewportView(table2);

		JLabel lblNewLabel_2 = new JLabel("Items");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 11, 157, 14);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Selected Items");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(482, 13, 147, 14);
		panel_1.add(lblNewLabel_2_1);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(482, 329, 433, 118);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		JComboBox cbBoothId = new JComboBox();

		JButton btnNewButton_1 = new JButton("Add");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtQuantity.getText() == null || txtQuantity.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "Please fill in Quantity!");
					return;

				}else {
					int row = table.getSelectedRow();
					int itemId = Integer.parseInt(table.getValueAt(row, 0).toString());
					Item item = DBOperation.queryItem(itemId, conn);
					item.setQuantity(Integer.parseInt(txtQuantity.getText()));
					List<Item> items = new ArrayList<>();
					items.add(item);
					searchData(items);
					float total = 0;
					for(int i = 0; i<table2.getRowCount(); i++) {
						
						total += Float.parseFloat(table2.getValueAt(i, 4).toString());
					}
					
					txtTotal.setText(String.valueOf(total));
					
					txtQuantity.setText(null);
				}
			}
		});
		btnNewButton_1.setBounds(10, 10, 133, 40);
		panel_4.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("Update");
		btnNewButton_1_1.setBounds(153, 10, 133, 40);
		panel_4.add(btnNewButton_1_1);

		JButton btnNewButton_1_2 = new JButton("Delete");
		btnNewButton_1_2.setBounds(296, 10, 133, 40);
		panel_4.add(btnNewButton_1_2);

		JLabel lblNewLabel_3 = new JLabel("Total:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(20, 66, 48, 26);
		panel_4.add(lblNewLabel_3);

		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTotal.setBounds(78, 60, 163, 40);
		panel_4.add(txtTotal);
		txtTotal.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int customerId = Integer.parseInt(txtCustomerId.getText());
				int invoiceId = Integer.parseInt(txtInvoiceId.getText().toString());
				int boothId = Integer.parseInt(cbBoothId.getSelectedItem().toString());
				float total = Float.parseFloat(txtTotal.getText().toString());
				String result = DBOperation.insertCustomerInvoice(customerId, invoiceId, boothId, total, conn);
						
				while(table2.getRowCount() > 0) {
					
					int itemId = Integer.parseInt(table2.getValueAt(0, 0).toString());
					
					int quantity = Integer.parseInt(table2.getValueAt(0, 2).toString());
					
					Item item = DBOperation.queryItem(itemId, conn);
					
					item.setQuantity(quantity);
					
					String result1 = DBOperation.insertInvoiceDetail(invoiceId, item, conn);
					
					model2.removeRow(0);
				}
				
				dispose();
				
			}
		});
		btnSave.setBounds(266, 60, 133, 40);
		panel_4.add(btnSave);
		

		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(848, 632, 89, 23);
		contentPane.add(btnNewButton);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 84, 538, 71);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1_2 = new JLabel("Item ID:");
		lblNewLabel_1_2.setBounds(10, 15, 78, 14);
		panel_2.add(lblNewLabel_1_2);

		txtItemId = new JTextField();
		txtItemId.setText((String) null);
		txtItemId.setColumns(10);
		txtItemId.setBounds(98, 12, 100, 20);
		panel_2.add(txtItemId);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				searchData1(txtItemId.getText());
			}
		});
		btnSearch.setBounds(276, 11, 89, 23);
		panel_2.add(btnSearch);

		JButton btnResetTable = new JButton("Reset ");
		btnResetTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txtItemId.setText("");
				searchData1(txtItemId.getText());
			}
		});
		btnResetTable.setBounds(394, 12, 89, 23);
		panel_2.add(btnResetTable);

		JLabel lblNewLabel_1_2_3 = new JLabel("Quantity:");
		lblNewLabel_1_2_3.setBounds(10, 44, 78, 14);
		panel_2.add(lblNewLabel_1_2_3);

		txtQuantity = new JTextField();
		txtQuantity.setText((String) null);
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(98, 41, 100, 20);
		panel_2.add(txtQuantity);


		JPanel panel_3 = new JPanel();
		panel_3.setBounds(558, 11, 379, 142);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblInvoiceInformation = new JLabel("  Invoice Information");
		lblInvoiceInformation.setBounds(0, 0, 133, 20);
		panel_3.add(lblInvoiceInformation);

		JLabel lblNewLabel_1_2_2 = new JLabel("Booth ID:");
		lblNewLabel_1_2_2.setBounds(20, 75, 78, 14);
		panel_3.add(lblNewLabel_1_2_2);

		txtInvoiceId = new JTextField();
		txtInvoiceId.setEditable(false);
		txtInvoiceId.setHorizontalAlignment(SwingConstants.CENTER);
		txtInvoiceId.setText((String) null);
		txtInvoiceId.setColumns(10);
		txtInvoiceId.setBounds(108, 30, 125, 20);
		panel_3.add(txtInvoiceId);
		int m = DBOperation.getMaxInvoiceId(conn) + 1;
		txtInvoiceId.setText(String.valueOf(m));

		JLabel lblNewLabel_1_2_1 = new JLabel("Invoice ID:");
		lblNewLabel_1_2_1.setBounds(20, 33, 78, 14);
		panel_3.add(lblNewLabel_1_2_1);

		txtTradingTime = new JTextField();
		txtTradingTime.setHorizontalAlignment(SwingConstants.CENTER);
		txtTradingTime.setEditable(false);
		txtTradingTime.setText((String) null);
		txtTradingTime.setColumns(10);
		txtTradingTime.setBounds(108, 112, 125, 20);
		panel_3.add(txtTradingTime);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String currentTime = formatter.format(date);
		txtTradingTime.setText(currentTime);

		JLabel lblNewLabel_1_2_1_1 = new JLabel("Trading time:");
		lblNewLabel_1_2_1_1.setBounds(20, 115, 78, 14);
		panel_3.add(lblNewLabel_1_2_1_1);


		cbBoothId.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"}));
		cbBoothId.setBounds(108, 71, 125, 20);
		panel_3.add(cbBoothId);
	}

	public void searchData(List<Item> items) {

		
		table2.setModel(model2);
		
		for (Item s : items) {
			Object[] o = new Object[10];
			o[0] = s.getItemId();
			o[1] = s.getItemName();
			o[2] = s.getQuantity();
			o[3] = s.getUnitPrice();
			o[4] = s.getPrice();
			model2.addRow(o);
		}

		this.invalidate();
		this.repaint();
	}

	public void searchData1(String itemId) {

		Map <String, String> conditionMap = new HashMap <String, String>();

		if(itemId != null && !itemId.isEmpty()) {
			conditionMap.put(DBOperation.itemId, itemId);
		}
		List <Item> items = DBOperation.queryItem(conditionMap, conn);
		table.setModel(model);

		while( model.getRowCount() > 0) {
			model.removeRow(0);
		}		
		for (Item s : items) {
			Object[] o = new Object[10];
			o[0] = s.getItemId();
			o[1] = s.getItemName();
			o[2] = s.getCategory();
			o[3] = s.getMeasurement();
			o[4] = s.getQuantity();
			o[5] = s.getUnitPrice();
			model.addRow(o);
		}

		this.invalidate();
		this.repaint();
	}
}
