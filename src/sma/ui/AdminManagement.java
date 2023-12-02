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

public class AdminManagement extends JFrame {

	private JPanel contentPane;
	static Connection conn = DBOperation.createConnection("jdbc:mysql://localhost:3306/supermarket", "phuocvo", "123456");
	private int k = LogInDialog.getBoothId();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					AdminManagement frame = new AdminManagement(i);
//					frame.setVisible(true);
//					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param i 
	 */
	public AdminManagement(int i) {
		setTitle("Admin Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 712, 139);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 677, 82);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Cusutomer Management");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CustomerManagement customerManagement = new CustomerManagement(k);
				customerManagement.setLocationRelativeTo(null);
				customerManagement.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(10, 27, 213, 40);
		panel.add(btnNewButton);
		
		JButton btnInvoiceManagement = new JButton("Invoice Management");
		btnInvoiceManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PutSelectedItem putSelectedItem = new PutSelectedItem(i);
				putSelectedItem.setVisible(true);
				putSelectedItem.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnInvoiceManagement.setBounds(233, 27, 213, 40);
		panel.add(btnInvoiceManagement);
		
		JButton btnInvoiceManagement_1 = new JButton("Item Management");
		btnInvoiceManagement_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ItemManagement itemManagement = new ItemManagement(i);
				itemManagement.setLocationRelativeTo(null);
				itemManagement.setVisible(true);
				dispose();
			}
		});
		btnInvoiceManagement_1.setBounds(454, 27, 213, 40);
		panel.add(btnInvoiceManagement_1);
	}
}
