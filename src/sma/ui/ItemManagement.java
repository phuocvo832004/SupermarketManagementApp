package sma.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sma.db.DBOperation;
import sma.object.Customer;
import sma.object.Item;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

public class ItemManagement extends JFrame {

	private JPanel contentPane;
	private JTextField txtItemId;
	private JTextField txtItemName;
	private JTextField txtQuantity;
	private JTextField txtUnitPrice;
	private JTable table;
	DefaultTableModel model = new DefaultTableModel();
	static Connection conn = DBOperation.createConnection("jdbc:mysql://localhost:3306/supermarket", "phuocvo", "123456");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemManagement frame = new ItemManagement();
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
	public ItemManagement() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 889, 717);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 855, 65);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ITEM MANAGEMENT");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 835, 45);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 85, 855, 148);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("   ITEM INFORMATION");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(0, 0, 164, 26);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Item ID:");
		lblNewLabel_2.setBounds(10, 36, 86, 26);
		panel_1.add(lblNewLabel_2);
		
		txtItemId = new JTextField();
		txtItemId.setBounds(108, 40, 127, 22);
		panel_1.add(txtItemId);
		txtItemId.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Measurement:");
		lblNewLabel_2_1.setBounds(10, 88, 86, 26);
		panel_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Item Name:");
		lblNewLabel_2_2.setBounds(316, 36, 86, 26);
		panel_1.add(lblNewLabel_2_2);
		
		txtItemName = new JTextField();
		txtItemName.setColumns(10);
		txtItemName.setBounds(414, 40, 127, 22);
		panel_1.add(txtItemName);
		
		JLabel lblNewLabel_2_3 = new JLabel("Quantity:");
		lblNewLabel_2_3.setBounds(316, 88, 86, 26);
		panel_1.add(lblNewLabel_2_3);
		
		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(414, 92, 127, 22);
		panel_1.add(txtQuantity);
		
		JLabel lblNewLabel_2_4 = new JLabel("Category:");
		lblNewLabel_2_4.setBounds(620, 36, 86, 26);
		panel_1.add(lblNewLabel_2_4);
		
		JLabel lblNewLabel_2_5 = new JLabel("Unit Price:");
		lblNewLabel_2_5.setBounds(620, 88, 86, 26);
		panel_1.add(lblNewLabel_2_5);
		
		txtUnitPrice = new JTextField();
		txtUnitPrice.setColumns(10);
		txtUnitPrice.setBounds(718, 92, 127, 22);
		panel_1.add(txtUnitPrice);
		
		JComboBox cbCategory = new JComboBox();
		cbCategory.setBounds(716, 36, 129, 26);
		panel_1.add(cbCategory);
		
		JComboBox cbMeasurement = new JComboBox();
		cbMeasurement.setBounds(108, 91, 129, 26);
		panel_1.add(cbMeasurement);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 244, 855, 266);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
		public void mouseClicked(MouseEvent e) {
				
			int row = table.getSelectedRow();
			
			txtItemId.setText(String.valueOf(table.getValueAt(row, 0)));
			txtItemName.setText(String.valueOf(table.getValueAt(row, 1)));
			setSelectedValue(cbCategory, table.getValueAt(row, 2).toString());
			setSelectedValue(cbMeasurement, table.getValueAt(row, 3).toString());
			txtQuantity.setText(String.valueOf(table.getValueAt(row, 4)));
			txtUnitPrice.setText(String.valueOf(table.getValueAt(row, 5)));

		}
	});
		String[] columnNames = {"Item ID", "Item Name", "Category", "Measurement", "Quantity", "Unit Price"};
		(model).setColumnIdentifiers(columnNames);	
		
		scrollPane.setViewportView(table);
		
		table.setBounds(10, 458, 1, 1);
		contentPane.add(table);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 518, 855, 162);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 String itemId = txtItemId.getText();
				 String itemName = txtItemName.getText();
				 String category = cbCategory.getSelectedItem().toString();
				 String measurement = cbMeasurement.getSelectedItem().toString();
				 String quantity = txtQuantity.getText();
				 String unitPrice = txtUnitPrice.getText();
				 
				 Item item = new Item();
				 item.setItemId(itemId);
				 item.setItemName(itemName);
				 item.setCategory(category);
				 item.setMeasurement(measurement);
				 item.setQuantity(quantity);
				 item.setUnitPrice(unitPrice);
				 
				 String result = DBOperation.insertItem(item, conn);
				 
				 searchData();
			}
		});
		btnAdd.setBounds(10, 10, 143, 42);
		panel_2.add(btnAdd);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				searchData();
			}
		});
		btnSearch.setBounds(238, 10, 143, 42);
		panel_2.add(btnSearch);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(10, 81, 143, 42);
		panel_2.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDelete.setBounds(238, 81, 143, 42);
		panel_2.add(btnDelete);
		
		JButton btnResetTable = new JButton("Reset Table");
		btnResetTable.setBounds(469, 10, 143, 42);
		panel_2.add(btnResetTable);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(729, 124, 116, 28);
		panel_2.add(btnCancel);
	}
	
	public static void setSelectedValue(JComboBox comboBox, String value)
    {
        Item item;
        for (int i = 0; i < comboBox.getItemCount(); i++)
        {
            item = (Item)comboBox.getItemAt(i);
            if (item.getCategory().equalsIgnoreCase(value))
            {
                comboBox.setSelectedIndex(i);
                break;
            }
        }
    }
	
	public void searchData() {

		Map <String, String> conditionMap = new HashMap <String, String>();

		 String itemId = txtItemId.getText();
		 String itemName = txtItemName.getText();
		if(itemId != null && !itemId.isEmpty()) {
			conditionMap.put(DBOperation.itemId, itemId);
		}
		if(itemName != null && !itemName.isEmpty()) {
			conditionMap.put(DBOperation.itemName, itemName);
		}
		List<Item> items = DBOperation.queryItem(conditionMap, conn);
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
