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

public class SaleManagement extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaleManagement frame = new SaleManagement();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SaleManagement() {
		setTitle("Supermarket Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 493, 139);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 459, 82);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Cusutomer Management");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CustomerManagement customerManagement = new CustomerManagement();
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
				PutSelectedItem3 putSelectedItem = new PutSelectedItem3();
				putSelectedItem.setVisible(true);
				putSelectedItem.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnInvoiceManagement.setBounds(236, 27, 213, 40);
		panel.add(btnInvoiceManagement);
	}
}
