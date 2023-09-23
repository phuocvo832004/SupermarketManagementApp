package sma.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
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

public class InvoiceManagement extends JFrame {

	private JPanel contentPane;
	private JTextField txtCustomerId;
	private JTextField txtCustomerName;
	private JTextField txtAddress;
	private JTextField txtPhonenumbers;
	private JTextField txtBoothId;
	private JTextField txtBoothName;
	private JTable table;
	private JTextField txtInvoiceId;
	private JTextField txtDate;
	private JTextField txtTime;
	private JTextField txtTotal;
	DefaultTableModel model = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InvoiceManagement frame = new InvoiceManagement();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InvoiceManagement() {
		setForeground(new Color(128, 128, 255));
		setBackground(new Color(0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 756, 667);
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
		
		JLabel lblNewLabel_3_2 = new JLabel("Time:");
		lblNewLabel_3_2.setBounds(555, 73, 59, 14);
		panel.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_1 = new JLabel("Date:");
		lblNewLabel_3_1.setBounds(555, 45, 59, 14);
		panel.add(lblNewLabel_3_1);
		
		txtTime = new JTextField();
		txtTime.setEditable(false);
		txtTime.setColumns(10);
		txtTime.setBounds(624, 70, 86, 20);
		panel.add(txtTime);
		
		txtDate = new JTextField();
		txtDate.setEditable(false);
		txtDate.setColumns(10);
		txtDate.setBounds(624, 42, 86, 20);
		panel.add(txtDate);
		
		txtInvoiceId = new JTextField();
		txtInvoiceId.setEditable(false);
		txtInvoiceId.setBounds(624, 11, 86, 20);
		panel.add(txtInvoiceId);
		txtInvoiceId.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Invoice ID:");
		lblNewLabel_3.setBounds(555, 14, 59, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("Sales Invoice");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 700, 85);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 129, 720, 107);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Customer Information");
		lblNewLabel_1.setBounds(0, 0, 172, 20);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Customer ID:");
		lblNewLabel_2.setBounds(10, 31, 87, 20);
		panel_1.add(lblNewLabel_2);
		
		txtCustomerId = new JTextField();
		txtCustomerId.setBounds(107, 31, 111, 20);
		panel_1.add(txtCustomerId);
		txtCustomerId.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Customer name:");
		lblNewLabel_2_1.setBounds(10, 62, 97, 20);
		panel_1.add(lblNewLabel_2_1);
		
		txtCustomerName = new JTextField();
		txtCustomerName.setColumns(10);
		txtCustomerName.setBounds(107, 62, 111, 20);
		panel_1.add(txtCustomerName);
		
		JLabel lblNewLabel_2_2 = new JLabel("Address:");
		lblNewLabel_2_2.setBounds(337, 31, 87, 20);
		panel_1.add(lblNewLabel_2_2);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(434, 31, 111, 20);
		panel_1.add(txtAddress);
		
		JLabel lblNewLabel_2_3 = new JLabel("Phone numbers:");
		lblNewLabel_2_3.setBounds(337, 62, 97, 20);
		panel_1.add(lblNewLabel_2_3);
		
		txtPhonenumbers = new JTextField();
		txtPhonenumbers.setColumns(10);
		txtPhonenumbers.setBounds(434, 62, 111, 20);
		panel_1.add(txtPhonenumbers);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(10, 247, 720, 65);
		contentPane.add(panel_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Booth Information");
		lblNewLabel_1_1.setBounds(0, 0, 172, 20);
		panel_1_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_4 = new JLabel("Booth ID:");
		lblNewLabel_2_4.setBounds(10, 31, 87, 20);
		panel_1_1.add(lblNewLabel_2_4);
		
		txtBoothId = new JTextField();
		txtBoothId.setColumns(10);
		txtBoothId.setBounds(107, 31, 111, 20);
		panel_1_1.add(txtBoothId);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Booth name:");
		lblNewLabel_2_2_1.setBounds(337, 31, 87, 20);
		panel_1_1.add(lblNewLabel_2_2_1);
		
		txtBoothName = new JTextField();
		txtBoothName.setColumns(10);
		txtBoothName.setBounds(434, 31, 111, 20);
		panel_1_1.add(txtBoothName);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 350, 720, 152);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
			}
		});
		
		String[] columnNames = {"STT", "Item ID", "Item Name", "Category", "Measurement", "Quantity", "Unit Price", "Price"};
		(model).setColumnIdentifiers(columnNames);
		scrollPane.setViewportView(table);
		
		txtTotal = new JTextField();
		txtTotal.setBounds(625, 513, 105, 20);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Total");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(568, 513, 47, 20);
		contentPane.add(lblNewLabel_4);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.activeCaption);
		panel_2.setBounds(10, 543, 720, 74);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(26, 22, 89, 23);
		panel_2.add(btnAdd);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(158, 22, 89, 23);
		panel_2.add(btnSearch);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setBounds(295, 22, 89, 23);
		panel_2.add(btnPrint);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(427, 22, 89, 23);
		panel_2.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
				
			}
		});
		btnCancel.setBounds(631, 51, 89, 23);
		panel_2.add(btnCancel);
		
		JLabel lblNewLabel_5 = new JLabel("Selected Item List");
		lblNewLabel_5.setBounds(10, 333, 118, 14);
		contentPane.add(lblNewLabel_5);
	}
}
