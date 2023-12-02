package sma.ui;

import java.awt.EventQueue;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import sma.db.DBOperation;
import sma.object.Campaign;

import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddCampaignDialog extends JFrame {

	private JPanel contentPane;
	private JTextField txtCampaignId;
	private JTextField txtCampaignName;
	private JTextField txtCampaignCode;
	private JTextField txtStatus;
	private JTextField txtContent;
	private JTextField txtTime;
	JDatePanelImpl datePanel;
	JDatePickerImpl datePicker;
	static Connection conn = DBOperation.createConnection("jdbc:mysql://localhost:3306/supermarket", "phuocvo", "123456");

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCampaignDialog frame = new AddCampaignDialog();
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
	public AddCampaignDialog() {
		setTitle("Add campaign dialog");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 652, 271);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 616, 159);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtCampaignId = new JTextField();
		txtCampaignId.setEditable(false);
		txtCampaignId.setHorizontalAlignment(SwingConstants.CENTER);
		txtCampaignId.setColumns(10);
		txtCampaignId.setBounds(147, 12, 145, 26);
		panel.add(txtCampaignId);
		int maxCampaign = DBOperation.getCountCampaign(conn);
		txtCampaignId.setText(String.valueOf(maxCampaign + 1));
		
		txtCampaignName = new JTextField();
		txtCampaignName.setHorizontalAlignment(SwingConstants.CENTER);
		txtCampaignName.setColumns(10);
		txtCampaignName.setBounds(147, 48, 145, 26);
		panel.add(txtCampaignName);
		
		JComboBox cbTargetCustomer = new JComboBox();
		cbTargetCustomer.setBounds(147, 83, 145, 26);
		panel.add(cbTargetCustomer);
		
		txtCampaignCode = new JTextField();
		txtCampaignCode.setEditable(false);
		txtCampaignCode.setHorizontalAlignment(SwingConstants.CENTER);
		txtCampaignCode.setColumns(10);
		txtCampaignCode.setBounds(147, 120, 145, 26);
		panel.add(txtCampaignCode);
		Random rand = new Random();
        int rand_int = 10000 + rand.nextInt(90000);
        txtCampaignCode.setText(String.valueOf(rand_int));
		
		txtStatus = new JTextField();
		txtStatus.setEditable(false);
		txtStatus.setColumns(10);
		txtStatus.setBounds(461, 13, 145, 26);
		panel.add(txtStatus);
		txtStatus.setText("New");
		txtStatus.setHorizontalAlignment(SwingConstants.CENTER);

		
		txtContent = new JTextField();
		txtContent.setColumns(10);
		txtContent.setBounds(461, 45, 145, 26);
		panel.add(txtContent);
		
		SpinnerDateModel model = new SpinnerDateModel();
		model.setCalendarField(Calendar.SECOND); 
		JSpinner spinner = new JSpinner(model);
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm:ss");
		spinner.setEditor(editor);
		spinner.setBounds(461, 120, 145, 26);
		JSpinner.DefaultEditor editor1 = (JSpinner.DefaultEditor)spinner.getEditor();
		editor1.getTextField().setHorizontalAlignment(JTextField.CENTER);
		panel.add(spinner);
		
		JLabel lblTimestamp_1_1 = new JLabel("hh : mm : ss");
		lblTimestamp_1_1.setBounds(324, 116, 127, 26);
		panel.add(lblTimestamp_1_1);
		
		UtilDateModel model1 = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(model1, p);
		JLabel lblTimestamp_1 = new JLabel("Timestamp:");
		lblTimestamp_1.setBounds(324, 83, 127, 26);
		panel.add(lblTimestamp_1);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(461, 79, 145, 26);
		panel.add(datePicker);
		
		JLabel lblTimestamp = new JLabel("Message content:");
		lblTimestamp.setBounds(324, 47, 127, 26);
		panel.add(lblTimestamp);
		
		JLabel lblTimestamp_1_1_1 = new JLabel("Status:");
		lblTimestamp_1_1_1.setBounds(324, 12, 127, 26);
		panel.add(lblTimestamp_1_1_1);
		
		JLabel lblCampaignCode = new JLabel("Campaign code:");
		lblCampaignCode.setBounds(10, 119, 127, 26);
		panel.add(lblCampaignCode);
		
		List<CustomerLevel> customerLevels = DBOperation.queryCustomerLevel(conn);
		for(CustomerLevel c : customerLevels) {
			cbTargetCustomer.addItem(c.getLevel());
		}
		JLabel lblTargetCampaign = new JLabel("Target Customer:");
		lblTargetCampaign.setBounds(10, 83, 127, 26);
		panel.add(lblTargetCampaign);
		
		JLabel lblCampaignName = new JLabel("Campaign Name:");
		lblCampaignName.setBounds(10, 47, 127, 26);
		panel.add(lblCampaignName);
		
		JLabel lblNewLabel = new JLabel("Campaign ID:");
		lblNewLabel.setBounds(10, 11, 127, 26);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 183, 616, 38);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(517, 11, 89, 23);
		panel_1.add(btnCancel);
		
		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String campaignId = txtCampaignId.getText();
				String campaignName = txtCampaignName.getText();
				String campaignCode = txtCampaignCode.getText();
				String status = txtStatus.getText();
				String content = txtContent.getText();
				String targetCustomer = cbTargetCustomer.getSelectedItem().toString();
				String selectedDate = datePicker.getJFormattedTextField().getText();
				Date date = (Date) model.getValue();
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				String selectedTime = sdf.format(date);
				String taskRunningTime = selectedDate + " " + selectedTime;

				Campaign campaign = new Campaign();
				campaign.setCampaignId(Integer.parseInt(campaignId));
				campaign.setCampaignName(campaignName);
				campaign.setCampaignCode(campaignCode);
				campaign.setStatus(status);
				campaign.setMsg_content(content);
				campaign.setTarget_customer(targetCustomer);
				campaign.setCampaign_timestamp(taskRunningTime);
				
				String result = DBOperation.addCampaign(campaign, conn);
				if ("Successful".equals(result)) {
					JOptionPane.showMessageDialog(null, "Campaign added successfully!");
				} else {
					JOptionPane.showMessageDialog(null, "Failed to add campaign. Please try again.");
				}
				dispose();
			}
		});
		btnApply.setBounds(418, 11, 89, 23);
		panel_1.add(btnApply);
	}
}
