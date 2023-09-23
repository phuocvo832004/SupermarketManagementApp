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

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerInvoice extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtCustomerId;
	private JTextField txtCustomerName;
	private JTable table2;
	DefaultTableModel model = new DefaultTableModel();
	DefaultTableModel model2 = new DefaultTableModel();

	static Connection conn = DBOperation.createConnection("jdbc:mysql://localhost:3306/supermarket", "phuocvo", "123456");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					CustomerInvoice frame = new CustomerInvoice();
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
	public CustomerInvoice(Customer customer) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 893, 657);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 127, 857, 143);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int row = table.getSelectedRow();
				String invoiceId = table.getValueAt(row, 0).toString();
				searchData1(invoiceId);
			}
		});
		String[] columnNames = {"INVOICE_ID", "TRADING_TIME", "BOOTH_ID", "TOTAL"};
		(model).setColumnIdentifiers(columnNames);
		
		searchData(customer);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Customer Invoice");
		lblNewLabel.setBounds(20, 93, 133, 23);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 857, 71);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblCustomerInformation = new JLabel("Customer Information");
		lblCustomerInformation.setBounds(10, 11, 145, 23);
		panel.add(lblCustomerInformation);
		
		JLabel lblNewLabel_1 = new JLabel("Customer ID:");
		lblNewLabel_1.setBounds(20, 45, 78, 14);
		panel.add(lblNewLabel_1);
		
		txtCustomerId = new JTextField();
		txtCustomerId.setEditable(false);
		txtCustomerId.setBounds(108, 42, 100, 20);
		panel.add(txtCustomerId);
		txtCustomerId.setColumns(10);
		txtCustomerId.setText(String.valueOf(customer.getCustomerId()));
		
		txtCustomerName = new JTextField();
		txtCustomerName.setEditable(false);
		txtCustomerName.setColumns(10);
		txtCustomerName.setBounds(436, 42, 100, 20);
		panel.add(txtCustomerName);
		txtCustomerName.setText(customer.getCustomerName());
		
		JLabel lblNewLabel_1_1 = new JLabel("Customer Name:");
		lblNewLabel_1_1.setBounds(326, 45, 100, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblInvoiceDetail = new JLabel("Invoice Detail");
		lblInvoiceDetail.setBounds(20, 281, 133, 23);
		contentPane.add(lblInvoiceDetail);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 315, 857, 255);
		contentPane.add(scrollPane_1);
		
		table2 = new JTable();
		
		String[] columnNames1 = {"ITEM_ID", "ITEM_NAME", "QUANTITY", "UNIT_PRICE"};
		(model2).setColumnIdentifiers(columnNames1);
		scrollPane_1.setViewportView(table2);
	}
	
	
	public void searchData(Customer customer) {

		Map <String, String> conditionMap = new HashMap <String, String>();

		conditionMap.put(DBOperation.customerId, String.valueOf(customer.getCustomerId()));
		List <Invoice> invoices = DBOperation.queryInvoice(conditionMap, conn);
		table.setModel(model);

		while( model.getRowCount() > 0) {
			model.removeRow(0);
		}		
		for(Invoice s : invoices) {
			Object[] o = new Object[6];
			o[0] = s.getInvoiceId();
			o[1] = s.getTradingTime();
			o[2] = s.getBoothId();
			o[3] = s.getTotal();
			model.addRow(o);
		}

		this.invalidate();
		this.repaint();

	}
	
	public void searchData1(String invoiceId) {

		Map <String, String> conditionMap = new HashMap <String, String>();

		conditionMap.put(DBOperation.invoiceId, invoiceId);
		List <Item> items = DBOperation.queryInvoiceDetail(conditionMap, conn);
		table2.setModel(model2);

		while( model2.getRowCount() > 0) {
			model2.removeRow(0);
		}		
		for(Item s : items) {
			Object[] o = new Object[7];
			o[0] = s.getItemId();
			o[1] = s.getItemName();
			o[2] = s.getQuantity();
			o[3] = s.getUnitPrice();
			//o[6] = s.get
			model2.addRow(o);
		}

		this.invalidate();
		this.repaint();

	}
	
}
