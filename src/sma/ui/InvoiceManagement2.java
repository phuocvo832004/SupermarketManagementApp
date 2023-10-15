package sma.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sma.db.DBOperation;
import sma.object.Booth;
import sma.object.Customer;
import sma.object.Item;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvoiceManagement2 extends JFrame {

	private JPanel contentPane;
	private JTextField txtBoothId;
	private JTextField txtBoothName;
	private JTable table;
	private JTextField txtInvoiceId;
	private JTextField txtDate;
	private JTextField txtTotal;
	DefaultTableModel model = new DefaultTableModel();
	static Connection conn = DBOperation.createConnection("jdbc:mysql://localhost:3306/supermarket", "phuocvo", "123456");


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					InvoiceManagement frame = new InvoiceManagement();
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
	public InvoiceManagement2(int customerId , int invoiceId, int boothId, String tradingTime ) {
		setForeground(new Color(128, 128, 255));
		setBackground(new Color(0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 756, 462);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setForeground(new Color(128, 128, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(10, 11, 720, 107);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sales Invoice");
		lblNewLabel.setBackground(new Color(192, 192, 192));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 475, 85);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_3_1 = new JLabel("Date:");
		lblNewLabel_3_1.setBounds(495, 45, 59, 14);
		panel.add(lblNewLabel_3_1);
		
		txtDate = new JTextField();
		txtDate.setEditable(false);
		txtDate.setColumns(10);
		txtDate.setBounds(564, 42, 146, 20);
		panel.add(txtDate);
		txtDate.setText(tradingTime);
		
		txtInvoiceId = new JTextField();
		txtInvoiceId.setEditable(false);
		txtInvoiceId.setBounds(564, 11, 146, 20);
		panel.add(txtInvoiceId);
		txtInvoiceId.setColumns(10);
		txtInvoiceId.setText(String.valueOf(invoiceId));
		
		JLabel lblNewLabel_3 = new JLabel("Invoice ID:");
		lblNewLabel_3.setBounds(495, 14, 59, 14);
		panel.add(lblNewLabel_3);
		
		Customer customer = DBOperation.queryCustomer(customerId, conn);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(10, 129, 720, 65);
		contentPane.add(panel_1_1);
		
		txtBoothId = new JTextField();
		txtBoothId.setEditable(false);
		txtBoothId.setEnabled(false);
		txtBoothId.setColumns(10);
		txtBoothId.setBounds(107, 31, 111, 20);
		panel_1_1.add(txtBoothId);
		txtBoothId.setText(String.valueOf(boothId));
		
		JLabel lblNewLabel_2_4 = new JLabel("Booth ID:");
		lblNewLabel_2_4.setBounds(10, 31, 87, 20);
		panel_1_1.add(lblNewLabel_2_4);
		
		Booth booth = DBOperation.queryBoothInfo(boothId, conn);
		
		txtBoothName = new JTextField();
		txtBoothName.setEditable(false);
		txtBoothName.setEnabled(false);
		txtBoothName.setColumns(10);
		txtBoothName.setBounds(434, 31, 111, 20);
		panel_1_1.add(txtBoothName);
		txtBoothName.setText(booth.getBoothName());
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Booth name:");
		lblNewLabel_2_2_1.setBounds(337, 31, 87, 20);
		panel_1_1.add(lblNewLabel_2_2_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Booth Information");
		lblNewLabel_1_1.setBounds(10, 0, 162, 20);
		panel_1_1.add(lblNewLabel_1_1);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 230, 720, 152);
		contentPane.add(scrollPane);
		
		txtTotal = new JTextField();
		txtTotal.setEnabled(false);
		txtTotal.setEditable(false);
		txtTotal.setBounds(625, 393, 105, 20);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		
		table = new JTable();
		table.setEnabled(false);
		
		String[] columnNames = {"STT", "Item ID", "Item Name", "Category", "Measurement", "Quantity", "Unit Price", "Price"};
		(model).setColumnIdentifiers(columnNames);
		
		searchData();
		float total = 0;
		for(int k=0;k<table.getRowCount();k++) {
			
			total += Float.parseFloat(table.getValueAt(k, 7).toString());
		}
		txtTotal.setText(String.valueOf(total));
		scrollPane.setViewportView(table);
		

		
		JLabel lblNewLabel_4 = new JLabel("Total");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(568, 393, 47, 20);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Selected Item List");
		lblNewLabel_5.setBounds(10, 205, 108, 14);
		contentPane.add(lblNewLabel_5);
	}
	public void searchData() {
		

		List <Item> items = DBOperation.querySelectedItem(conn);
		table.setModel(model);
		int i =1;
		for (Item s : items) {
			Object[] o = new Object[10];
			o[0] = i;
			o[1] = s.getItemId();
			o[2] = s.getItemName();
			o[3] = s.getCategory();
			o[4] = s.getMeasurement();
			o[5] = s.getQuantity();
			o[6] = s.getUnitPrice();
			o[7] = s.getPrice();
			//o[8] = s.getPrice();
			i++;
			model.addRow(o);
		}

	}
}
