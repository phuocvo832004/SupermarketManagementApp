package sma.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sma.db.DBOperation;
import sma.object.Customer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

public class AddCustomerDialog extends JFrame {

	private JPanel contentPane;
	private JTextField txtCustomerId;
	private JTextField txtCustomerName;
	private JTextField txtAddress;
	private JTextField txtPhonenumbers;
	DefaultTableModel model = new DefaultTableModel();
	static Connection conn = DBOperation.createConnection("jdbc:mysql://localhost:3306/supermarket", "phuocvo", "123456");


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCustomerDialog frame = new AddCustomerDialog();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddCustomerDialog() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 288);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 685, 156);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Customer Information");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 11, 171, 38);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("Customer ID:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(20, 60, 89, 19);
		panel.add(lblNewLabel_2);

		txtCustomerId = new JTextField();
		txtCustomerId.setEditable(false);
		txtCustomerId.setColumns(10);
		txtCustomerId.setBounds(156, 60, 162, 20);
		panel.add(txtCustomerId);
		txtCustomerId.setText(String.valueOf(DBOperation.getMaxCustomerId(conn) + 1));

		JLabel lblNewLabel_2_2 = new JLabel("Customer Name:");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_2.setBounds(354, 60, 112, 19);
		panel.add(lblNewLabel_2_2);

		txtCustomerName = new JTextField();
		txtCustomerName.setColumns(10);
		txtCustomerName.setBounds(490, 60, 157, 20);
		panel.add(txtCustomerName);
		
		JLabel lblNewLabel_2_1 = new JLabel("Address:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(20, 121, 57, 19);
		panel.add(lblNewLabel_2_1);

		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(156, 121, 162, 20);
		panel.add(txtAddress);

		JLabel lblNewLabel_2_3 = new JLabel("Phone Numbers:");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_3.setBounds(354, 121, 109, 19);
		panel.add(lblNewLabel_2_3);

		txtPhonenumbers = new JTextField();
		txtPhonenumbers.setColumns(10);
		txtPhonenumbers.setBounds(490, 121, 157, 20);
		panel.add(txtPhonenumbers);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 178, 685, 60);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		txtPhonenumbers.setText("");
		txtCustomerName.requestFocus();

		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        int customerId = Integer.parseInt(txtCustomerId.getText());
		        String customerName = txtCustomerName.getText();
		        String phoneNumbers = txtPhonenumbers.getText();
		        String address = txtAddress.getText();

		        if( txtCustomerName.getText().isEmpty() || txtPhonenumbers.getText().isEmpty() || txtAddress.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null,"Please fill in all the required information.");
		            return;
		        } else if(DBOperation.existsPhone(phoneNumbers, conn)) {
		            JOptionPane.showMessageDialog(null, "Phonenumbers existed!");
		            txtPhonenumbers.setText("");
		            txtPhonenumbers.requestFocus();
		            return;
		        } else {
		            Customer customer = new Customer();
		            customer.setCustomerId(customerId);
		            customer.setCustomerName(customerName);
		            customer.setPhoneNumbers(phoneNumbers);
		            customer.setAddress(address);

		            String result = DBOperation.insertCustomer(customer, conn);
		            JOptionPane.showMessageDialog(null, "Insert new customer successfully!");
		        }
		        
		        dispose();
		    }
		});

		btnApply.setBounds(469, 26, 99, 23);
		panel_1.add(btnApply);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(578, 26, 99, 23);
		panel_1.add(btnCancel);
	}
}
