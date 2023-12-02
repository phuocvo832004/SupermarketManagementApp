package sma.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sma.db.DBOperation;
import sma.object.Booth;
import sma.object.Customer;
import sma.object.Invoice;
import sma.object.Item;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PutSelectedItem2 extends JFrame {

	private JPanel contentPane;
	private JTextField txtCustomerId;
	private JTextField txtCustomerName;
	DefaultTableModel model = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			//all cells false
			return false;
		}

	};
	DefaultTableModel model2 = new DefaultTableModel() {
		@Override
		public Class<?> getColumnClass(int column) {
			switch (column) {
			case 0:
				return Boolean.class;
			default:
				return String.class;
			}
		}
	};

	private JTable table;
	private JTable table2;
	static Connection conn = DBOperation.createConnection("jdbc:mysql://localhost:3306/supermarket", "phuocvo", "123456");
	private JTextField txtItemId;
	private JTextField txtInvoiceId;
	private JTextField txtTradingTime;
	private JTextField txtTotal;
	private JTextField txtQuantity;
	private JTextField txtPhonenumbers;
	private InvoiceManagement invoiceManagement;
	private InvoiceManagement2 invoiceManagement2;

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
	public PutSelectedItem2(int customerId, String customerName, String phonenumber,  int i) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Put item to cart");
		setBounds(100, 100, 961, 715);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("  Customer Information");
		lblNewLabel.setBounds(10, 11, 133, 20);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 538, 96);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Customer ID:");
		lblNewLabel_1.setBounds(10, 36, 78, 14);
		panel.add(lblNewLabel_1);

		txtCustomerId = new JTextField();
		txtCustomerId.setText((String) null);
		txtCustomerId.setEditable(false);
		txtCustomerId.setColumns(10);
		txtCustomerId.setBounds(106, 32, 100, 20);
		panel.add(txtCustomerId);
		txtCustomerId.setText(String.valueOf(customerId));

		JLabel lblNewLabel_1_1 = new JLabel("Customer Name:");
		lblNewLabel_1_1.setBounds(282, 33, 100, 14);
		panel.add(lblNewLabel_1_1);

		txtCustomerName = new JTextField();
		txtCustomerName.setText((String) null);
		txtCustomerName.setEditable(false);
		txtCustomerName.setColumns(10);
		txtCustomerName.setBounds(392, 30, 100, 20);
		panel.add(txtCustomerName);
		txtCustomerName.setText(customerName);

		JLabel lblNewLabel_4 = new JLabel("Phone numbers:");
		lblNewLabel_4.setBounds(10, 72, 105, 20);
		panel.add(lblNewLabel_4);

		txtPhonenumbers = new JTextField();
		txtPhonenumbers.setText(phonenumber);
		txtPhonenumbers.setColumns(10);
		txtPhonenumbers.setBounds(106, 72, 100, 20);
		panel.add(txtPhonenumbers);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 192, 925, 481);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 462, 430);
		panel_1.add(scrollPane);

		table = new JTable();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				int row = table.getSelectedRow();
				String itemId = table.getValueAt(row, 0).toString();
				txtItemId.setText(itemId);

				txtQuantity.setText("");
				txtQuantity.requestFocus();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = table.getSelectedRow();
					int itemId = Integer.parseInt(table.getValueAt(row, 0).toString());
					Item item = DBOperation.queryItem(itemId, conn);
					item.setQuantity(1);
					List<Item> items = new ArrayList<>();
					items.add(item);
					searchData(items);
					float total = 0;
					for(int i = 0; i<table2.getRowCount(); i++) {

						total += Float.parseFloat(table2.getValueAt(i, 4).toString());
					}

					txtTotal.setText(String.valueOf(total));

					txtQuantity.setText(null);


					txtItemId.setText("");
					searchData1(txtItemId.getText());
				}
			}
		});
		String[] columnNames = {"Item ID", "Item Name", "Category", "Measurement", "Remaining", "Unit Price"};
		(model).setColumnIdentifiers(columnNames);

		scrollPane.setViewportView(table);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(482, 40, 433, 279);
		panel_1.add(scrollPane_1);

		table2 = new JTable();
		table2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {


			}
		});
		String[] columnNames1 = {" ", "Item ID", "Item Name", "Quantity", "Unit Price", "Price"};
		(model2).setColumnIdentifiers(columnNames1);

		scrollPane_1.setViewportView(table2);

		JLabel lblNewLabel_2 = new JLabel("Items");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 11, 157, 14);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Selected Items");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(482, 13, 147, 14);
		panel_1.add(lblNewLabel_2_1);

		List<Booth> booths = DBOperation.queryBoothInfo(conn);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(482, 329, 433, 106);
		panel_1.add(panel_4);
		JComboBox cbBoothId = new JComboBox();

		for(Booth b : booths) {

			cbBoothId.addItem(new BoothItem(b.getBoothId(), b.getBoothName()));
		}
		if(i == 14 || i ==15) {
			for(int d =0;d<cbBoothId.getItemCount();d++) {
				cbBoothId.setSelectedIndex(1);
			}
		}else {
			for(int d =0;d<cbBoothId.getItemCount();d++) {
				cbBoothId.setSelectedIndex(0);
			}
		}

		JButton btnNewButton_1 = new JButton("Add");
		btnNewButton_1.setBounds(10, 11, 116, 26);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addItem();
			}
		});

		JButton btnNewButton_1_2 = new JButton("Delete");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int rowCount = model2.getRowCount();
				for (int i = rowCount - 1; i >= 0; i--) {
					if(Boolean.TRUE.equals(model2.getValueAt(i, 0))) {
						model2.removeRow(i);
					}
				}	
			}
		});
		btnNewButton_1_2.setBounds(161, 11, 116, 26);

		JLabel lblNewLabel_3 = new JLabel("Total:");
		lblNewLabel_3.setBounds(10, 62, 48, 26);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));

		txtTotal = new JTextField();
		txtTotal.setBounds(68, 60, 163, 32);
		txtTotal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTotal.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 117, 538, 65);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1_2 = new JLabel("Item ID:");
		lblNewLabel_1_2.setBounds(10, 15, 78, 14);
		panel_2.add(lblNewLabel_1_2);

		txtItemId = new JTextField();
		txtItemId.setText((String) null);
		txtItemId.setColumns(10);
		txtItemId.setBounds(106, 15, 100, 20);
		panel_2.add(txtItemId);

		searchData1(txtItemId.getText());

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
		txtQuantity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				addItem();
			}
		});
		txtQuantity.setText((String) null);
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(106, 44, 100, 20);
		panel_2.add(txtQuantity);

		JCheckBox checkBoxMember = new JCheckBox("non-member");
		checkBoxMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txtCustomerId.setText(String.valueOf(DBOperation.getMaxCustomerId(conn) + 1));
				txtCustomerName.setText(" ");
				txtPhonenumbers.setText(" ");

			}
		});
		checkBoxMember.setFont(new Font("Tahoma", Font.PLAIN, 12));
		checkBoxMember.setBounds(276, 41, 109, 21);
		panel_2.add(checkBoxMember);


		JPanel panel_3 = new JPanel();
		panel_3.setBounds(558, 11, 379, 171);
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
		cbBoothId.setBounds(108, 71, 125, 20);
		panel_3.add(cbBoothId);
		JButton btnSave = new JButton("Print");
		btnSave.setBounds(283, 62, 95, 26);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Booth booth = DBOperation.queryBoothInfo(cbBoothId.getSelectedItem().toString(), conn);

				int customerId = Integer.parseInt(txtCustomerId.getText());
				int invoiceId = Integer.parseInt(txtInvoiceId.getText().toString());
				int boothId = booth.getBoothId();
				String k = txtTotal.getText().toString(); 
				NumberFormat format = NumberFormat.getInstance();
				Number number;
				try {
					number = format.parse(k);
					float total = number.floatValue();
				if(!checkBoxMember.isSelected()) {
					if(txtPhonenumbers.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please fill customer information or tick checkbox!");
						return;
					}
				}else {
					Customer customer = new Customer();
					customer.setCustomerId(customerId);
					customer.setCustomerName(txtCustomerName.getText());
					customer.setPhoneNumbers(txtPhonenumbers.getText());
					customer.setAddress(" ");
					DBOperation.insertCustomer(customer, conn);
				}
				DBOperation.insertCustomerInvoice(customerId, invoiceId, boothId, total, conn);

				while(table2.getRowCount() > 0) {

					int itemId = Integer.parseInt(table2.getValueAt(0, 1).toString());

					int quantity = Integer.parseInt(table2.getValueAt(0, 3).toString());

					Item item = DBOperation.queryItem(itemId, conn);

					item.setQuantity(quantity);

					DBOperation.insertInvoiceDetail(invoiceId, item, conn);

					model2.removeRow(0);
				}
		        if(!checkBoxMember.isSelected()) {
		    		invoiceManagement = new InvoiceManagement(Integer.parseInt(txtCustomerId.getText()), Integer.parseInt(txtInvoiceId.getText()), boothId, txtTradingTime.getText());
		            //invoiceManagement.setVisible(true);
		        } else {
		            invoiceManagement2 = new InvoiceManagement2(Integer.parseInt(txtCustomerId.getText()), Integer.parseInt(txtInvoiceId.getText()), boothId, txtTradingTime.getText());
		            //invoiceManagement2.setVisible(true);
		        }
				PrinterJob job = PrinterJob.getPrinterJob();
				job.setPrintable(new Printable() {
				    public int print(Graphics pg, PageFormat pf, int pageNum) {
				        if (pageNum > 0) {
				            return Printable.NO_SUCH_PAGE;
				        }
				        Graphics2D g2 = (Graphics2D) pg;
				        g2.translate(pf.getImageableX(), pf.getImageableY());
				        double scaleX = pf.getImageableWidth() / invoiceManagement.getWidth();
				        double scaleY = pf.getImageableHeight() / invoiceManagement.getHeight();
				        g2.scale(scaleX, scaleY);
						if(!checkBoxMember.isSelected()) {
							invoiceManagement.paint(g2);
						}else {
							invoiceManagement2.paint(g2);
						}
				        return Printable.PAGE_EXISTS;
				    }
				});
				boolean ok = job.printDialog();
				if (ok) {
				    try {
				        job.print();
				    } catch (PrinterException ex) {
				        /* The job did not successfully complete */
				    }
				}

				DBOperation.updateLevel(customerId, conn);
				dispose();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton btnNewButton_1_2_1 = new JButton("Save");
		btnNewButton_1_2_1.setBounds(307, 11, 116, 26);
		btnNewButton_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int customerId = Integer.parseInt(txtCustomerId.getText());
				int invoiceId = Integer.parseInt(txtInvoiceId.getText().toString());
				String boothName = cbBoothId.getSelectedItem().toString();
				Booth booth = DBOperation.queryBoothInfo(boothName, conn);
				int boothId = booth.getBoothId();
				float total = Float.parseFloat(txtTotal.getText().toString());
				DecimalFormat formatter = new DecimalFormat("#,###.00");
				formatter.format(total);
				if(!checkBoxMember.isSelected()) {
					if(txtPhonenumbers.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please fill customer information or tick checkbox!");
						return;
					}
				}else {
					Customer customer = new Customer();
					customer.setCustomerId(customerId);
					customer.setCustomerName(txtCustomerName.getText());
					customer.setPhoneNumbers(txtPhonenumbers.getText());
					customer.setAddress(" ");

					DBOperation.insertCustomer(customer, conn);

				}
				DBOperation.insertCustomerInvoice(customerId, invoiceId, boothId, total, conn);

				while(table2.getRowCount() > 0) {

					int itemId = Integer.parseInt(table2.getValueAt(0, 1).toString());

					int quantity = Integer.parseInt(table2.getValueAt(0, 3).toString());

					Item item = DBOperation.queryItem(itemId, conn);

					item.setQuantity(quantity);

					DBOperation.insertInvoiceDetail(invoiceId, item, conn);

					model2.removeRow(0);
				}
				DBOperation.updateLevel(customerId, conn);
				dispose();
			}
		});
		panel_4.setLayout(null);
		panel_4.add(lblNewLabel_3);
		panel_4.add(txtTotal);
		panel_4.add(btnSave);
		panel_4.add(btnNewButton_1);
		panel_4.add(btnNewButton_1_2);
		panel_4.add(btnNewButton_1_2_1);




		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setBounds(836, 447, 89, 23);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	public void searchData(List<Item> items) {


		table2.setModel(model2);

		for (Item s : items) {
			Object[] o = new Object[10];
			o[0] = false;
			o[1] = s.getItemId();
			o[2] = s.getItemName();
			o[3] = s.getQuantity();
			o[4] = s.getUnitPrice();
			o[5] = s.getPrice();
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
	public boolean existedItem(String n) {

		for(int g=0;g<table2.getRowCount();g++) {
			if(n.equals(table2.getValueAt(g, 1).toString())) {
				return true;
			}
		}
		return false;
	}
	public void addItem() {
		if(existedItem(txtItemId.getText())) {
			JOptionPane.showMessageDialog(null, "Existed item in cart!");
			return;
		}else {
			int row = table.getSelectedRow();
			int itemId = Integer.parseInt(table.getValueAt(row, 0).toString());
			Item item = DBOperation.queryItem(itemId, conn);
			if(txtQuantity.getText() == null || txtQuantity.getText().isEmpty()) {
				item.setQuantity(1);

			}else {
				if(!txtQuantity.getText().matches("\\d+")) {
					JOptionPane.showMessageDialog(null, "Please fill correct datatypes in Quantity!");
					txtQuantity.setText(null);
					txtQuantity.requestFocus();
					return;
				}else {
					item.setQuantity(Integer.parseInt(txtQuantity.getText()));
				}
			}
			List<Item> items = new ArrayList<>();
			items.add(item);
			searchData(items);
			float total = 0;
			for(int i = 0; i<table2.getRowCount(); i++) {

				total += Float.parseFloat(table2.getValueAt(i, 5).toString());
				DecimalFormat formatter = new DecimalFormat("#,###.00");
				String formatted = formatter.format(total);
				txtTotal.setText(formatted);
			}
			txtQuantity.setText(null);
			txtItemId.setText("");
			searchData1(txtItemId.getText());
	        
		}
	}

}
