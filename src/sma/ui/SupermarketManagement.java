package sma.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sma.db.DBOperation;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SupermarketManagement extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SupermarketManagement frame = new SupermarketManagement();
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
	public SupermarketManagement() {
		setTitle("Supermarket Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 891, 199);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 857, 246);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Cusutomer Management");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CustomerManagement customerManagement = new CustomerManagement();
				customerManagement.show();
			}
		});
		btnNewButton.setBounds(34, 27, 213, 40);
		panel.add(btnNewButton);
		
		JButton btnInvoiceManagement = new JButton("Invoice Management");
		btnInvoiceManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				InvoiceManagement invoicemanagement = new InvoiceManagement();
				invoicemanagement.show();
			}
		});
		btnInvoiceManagement.setBounds(290, 27, 213, 40);
		panel.add(btnInvoiceManagement);
		
		JButton btnItemManagement = new JButton("Item Management");
		btnItemManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ItemManagement itemManagement = new ItemManagement();
				itemManagement.show();
			}
		});
		btnItemManagement.setBounds(551, 27, 213, 40);
		panel.add(btnItemManagement);
		
		JButton btnNewButton_1 = new JButton("Put Selected Item ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//PutSelectedItem putSelectedItem = new PutSelectedItem();
				
			}
		});
		btnNewButton_1.setBounds(34, 80, 213, 28);
		panel.add(btnNewButton_1);
	}
}
