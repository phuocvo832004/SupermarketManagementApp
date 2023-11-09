package sma.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sma.db.DBOperation;
import sma.object.Category;
import sma.object.Customer;
import sma.object.Item;
import sma.object.Measurement;

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
import javax.swing.JComboBox;

public class AddItemDialog extends JFrame {

	private JPanel contentPane;
	DefaultTableModel model = new DefaultTableModel();
	static Connection conn = DBOperation.createConnection("jdbc:mysql://localhost:3306/supermarket", "phuocvo", "123456");
	private JTextField txtItemId;
	private JTextField txtItemName;
	private JTextField txtQuantity;
	private JTextField txtUnitPrice;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddItemDialog frame = new AddItemDialog();
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
	public AddItemDialog() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 891, 281);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 170, 855, 61);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(746, 27, 99, 23);
		panel_1.add(btnCancel);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(10, 11, 855, 148);
		contentPane.add(panel_1_1);

		txtUnitPrice = new JTextField();
		txtUnitPrice.setColumns(10);
		txtUnitPrice.setBounds(718, 92, 127, 22);
		panel_1_1.add(txtUnitPrice);

		JComboBox cbMeasurement = new JComboBox();
		cbMeasurement.setBounds(108, 91, 129, 26);
		panel_1_1.add(cbMeasurement);
		List<Measurement> measurements = DBOperation.queryMeasurement(conn);
		for(Measurement m : measurements) {

			cbMeasurement.addItem(m.getMeasurement());

		}

		txtItemName = new JTextField();
		txtItemName.setColumns(10);
		txtItemName.setBounds(414, 40, 127, 22);
		panel_1_1.add(txtItemName);

		txtItemId = new JTextField();
		txtItemId.setEnabled(false);
		txtItemId.setEditable(false);
		txtItemId.setColumns(10);
		txtItemId.setBounds(108, 40, 127, 22);
		panel_1_1.add(txtItemId);
		txtItemId.setText(String.valueOf(DBOperation.getMaxItemId(conn)+1));

		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(414, 92, 127, 22);
		panel_1_1.add(txtQuantity);

		JComboBox cbCategory = new JComboBox();
		cbCategory.setBounds(716, 36, 129, 26);
		panel_1_1.add(cbCategory);
		List<Category> categories = DBOperation.queryCategories(conn);
		for(Category c : categories) {

			cbCategory.addItem(c.getCategory());

		}

		JLabel lblNewLabel_1 = new JLabel("   ITEM INFORMATION");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(0, 0, 164, 26);
		panel_1_1.add(lblNewLabel_1);

		JLabel txtItemId2 = new JLabel("Item ID:");
		txtItemId2.setBounds(10, 36, 86, 26);
		panel_1_1.add(txtItemId2);

		JLabel lblNewLabel_2_1 = new JLabel("Measurement:");
		lblNewLabel_2_1.setBounds(10, 88, 86, 26);
		panel_1_1.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("Item Name:");
		lblNewLabel_2_2.setBounds(316, 36, 86, 26);
		panel_1_1.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_3 = new JLabel("Quantity:");
		lblNewLabel_2_3.setBounds(316, 88, 86, 26);
		panel_1_1.add(lblNewLabel_2_3);

		JLabel lblNewLabel_2_4 = new JLabel("Category:");
		lblNewLabel_2_4.setBounds(620, 36, 86, 26);
		panel_1_1.add(lblNewLabel_2_4);

		JLabel lblNewLabel_2_5 = new JLabel("Unit Price:");
		lblNewLabel_2_5.setBounds(620, 88, 86, 26);
		panel_1_1.add(lblNewLabel_2_5);

		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        int itemId = DBOperation.getMaxItemId(conn) + 1;
		        String itemName = txtItemName.getText();
		        String category = cbCategory.getSelectedItem().toString();
		        String measurement = cbMeasurement.getSelectedItem().toString();

		        if( itemName.equals("") || txtQuantity.getText().isEmpty() || txtUnitPrice.getText().isEmpty()) {

		            JOptionPane.showMessageDialog(null,"Please fill in all the required information.");
		            return;
		        }else {
			        int quantity = Integer.parseInt(txtQuantity.getText());
			        float unitPrice = Float.parseFloat(txtUnitPrice.getText());
		            Item item = new Item();
		            item.setItemId(itemId);
		            item.setItemName(itemName);
		            item.setCategory(category);
		            item.setMeasurement(measurement);
		            item.setQuantity(quantity);
		            item.setUnitPrice(unitPrice);
		            String result = DBOperation.insertItem(item, conn);
		            JOptionPane.showMessageDialog(null, "Add successfully!");
		            dispose();
		        }
		    }
		});

		btnApply.setBounds(637, 27, 99, 23);
		panel_1.add(btnApply);


	}
}
