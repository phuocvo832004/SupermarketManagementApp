package sma.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import sma.object.Booth;
import sma.object.Campaign;
import sma.object.Category;
import sma.object.Customer;
import sma.object.Invoice;
import sma.object.Item;
import sma.object.Level;
import sma.object.Measurement;
import sma.ui.CustomerLevel;

public class DBOperation {

	public static final String customerId = "customerId";
	public static final String customerName = "customerName";
	public static final String phoneNumbers = "phoneNumbers";
	public static final String address = "address";
	public static final String invoiceId = "invoiceId";
	public static final String tradingTime = "tradingTime";
	public static final String boothId = "boothId";
	public static final String boothName = "boothName";
	public static final String total = "total";
	public static final String itemId = "itemId"; 
	public static final String itemName = "itemName";
	public static final String category = "category";
	public static final String measurement = "measurement";
	public static final String quantity = "quantity";
	public static final String unitPrice = "unitPrice";
	public static final String price = "price";
	public static final String level = "level";

	/**
	 * create connection 
	 * @param dbUrl
	 * @param dbUserId
	 * @param dbPassword
	 * @return
	 */
	public static Connection createConnection (String dbUrl, String dbUserId, String dbPassword) {

		Connection conn = null;
		try {

			conn = DriverManager.getConnection(dbUrl, dbUserId, dbPassword);

			if (conn != null) {
				System.out.println("Connected");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return conn;
	}

	public static String insertCustomer (Customer customer, Connection conn) {

		String sql = "INSERT INTO CUSTOMERS (CUSTOMER_ID, CUSTOMER_NAME, PHONENUMBERS, ADDRESS, LEVEL) VALUES (?, ?, ?, ?, ?)";

		try {

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, customer.getCustomerId());
			statement.setString(2, customer.getCustomerName());
			statement.setString(3, customer.getPhoneNumbers());
			statement.setString(4, customer.getAddress());
			statement.setString(5, "NEW");


			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("A new user was inserted successfully!");
				return "Successful";
			}

		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		return "Failed";
	}

	public static String updateCustomer(Customer customer, Connection conn) {

		String sql = "UPDATE CUSTOMERS SET CUSTOMER_NAME=?, PHONENUMBERS=?, ADDRESS=? WHERE CUSTOMER_ID=?";

		try {

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, customer.getCustomerName());
			statement.setString(2, customer.getPhoneNumbers());
			statement.setString(3, customer.getAddress());
			statement.setInt(4, customer.getCustomerId());

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("A new user was updated successfully!");
				return "Successful";
			}

		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		return "Failed";
	}

	public static String deleteCustomer(int customerId, Connection conn) {

		String sql = "DELETE FROM CUSTOMERS WHERE CUSTOMER_ID = ?";

		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, customerId);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("A customer was deleted successfully!");
				return "Delete successfully";
			}

		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		return "Failed";

	}

	public static boolean checkExistPhonenumbers(String phoneNumber, Connection conn) {

		String sql = "SELECT PHONENUMBERS FROM CUSTOMERS WHERE PHONENUMBERS = ?";

		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, phoneNumber);
			ResultSet result = statement.executeQuery();
			while(result.next()) {

				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return false;
	}


	public static Customer queryCustomer(int customerId, Connection conn) {

		String sql = "SELECT * FROM CUSTOMERS WHERE CUSTOMER_ID = '" + customerId + "'" ;

		Customer customer = new Customer();
		try {

			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()){

				int customerId1 = result.getInt("CUSTOMER_ID");
				String customerName = result.getString("CUSTOMER_NAME");
				String phoneNumbers = result.getString("PHONENUMBERS");
				String address = result.getString("ADDRESS");
				String level = result.getString("LEVEL");

				customer.setCustomerId(customerId1);
				customer.setCustomerName(customerName);
				customer.setPhoneNumbers(phoneNumbers);
				customer.setAddress(address);
				customer.setLevel(level);


			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return customer;

	}

	public static List<Customer> queryCustomer(Map<String, String> conditionMap, Connection conn) {

		String sql = "SELECT * FROM CUSTOMERS WHERE ";

		if(conditionMap == null || conditionMap.isEmpty()) {
			sql = sql.replace("WHERE", "");
		}

		boolean needAnd = false;

		if(conditionMap.containsKey(customerName)) {

			sql = sql + "LOWER(CUSTOMER_NAME) LIKE LOWER('" + conditionMap.get(customerName) + "')"; 
			needAnd = true;
		}

		if(conditionMap.containsKey(phoneNumbers)) {

			if(needAnd) {
				sql = sql + " AND ";
			}
			sql = sql + "LOWER(PHONENUMBERS) LIKE LOWER('" + conditionMap.get(phoneNumbers) + "')"; 
			needAnd = true;
		}

		if(conditionMap.containsKey(address)) {

			if(needAnd) {
				sql = sql + " AND ";
			}
			sql = sql + "LOWER(ADDRESS) LIKE LOWER('" + conditionMap.get(address) + "')"; 

		}

		if(conditionMap.containsKey(level)) {

			if(needAnd) {
				sql = sql + " AND ";
			}
			sql = sql + "LOWER(LEVEL) LIKE LOWER('" + conditionMap.get(level) + "')"; 

		}

		List<Customer> customers = new ArrayList<Customer>();

		try {

			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()){

				int customerId = result.getInt("CUSTOMER_ID");
				String customerName = result.getString("CUSTOMER_NAME");
				String phoneNumbers = result.getString("PHONENUMBERS");
				String address = result.getString("ADDRESS");
				String level = result.getString("LEVEL");

				Customer customer = new Customer();
				customer.setCustomerId(customerId);
				customer.setCustomerName(customerName);
				customer.setPhoneNumbers(phoneNumbers);
				customer.setAddress(address);
				customer.setLevel(level);

				customers.add(customer);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}


	public static String insertItem(Item item, Connection conn) {

		String sql = "INSERT INTO ITEMS (ITEM_ID, ITEM_NAME, CATEGORY, MEASUREMENT, QUANTITY, UNIT_PRICE) VALUES (?, ?, ?, ?, ?, ?)";

		try {

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, item.getItemId());
			statement.setString(2, item.getItemName());
			statement.setString(3, item.getCategory());
			statement.setString(4, item.getMeasurement());
			statement.setInt(5, item.getQuantity());
			statement.setFloat(6, item.getUnitPrice());


			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("A new item was inserted successfully!");
				return "Insert successfully";
			}

		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		return "Failed";
	}

	public static List<Item> searchSelectedItem(int invoiceId, Connection conn){

		String sql = "SELECT * FROM INVOICE_DETAIL WHERE INVOICE_ID= '" + invoiceId + "'";

		List<Item> items = new ArrayList<>();

		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {

				int itemId = result.getInt("ITEM_ID");
				String itemName = result.getString("ITEM_NAME");
				int quantity = result.getInt("QUANTITY");
				float unitPrice = result.getFloat("UNIT_PRICE");

				Item item = new Item();
				item.setItemId(itemId);
				item.setItemName(itemName);
				item.setQuantity(quantity);
				item.setUnitPrice(unitPrice);

				items.add(item);
			}

		}catch (SQLException ex) {
			ex.printStackTrace();
		}

		return items;
	}

	public static String updateItem(Item item, Connection conn) {

		String sql = "UPDATE ITEMS SET ITEM_NAME=?, CATEGORY=?, MEASUREMENT=?, QUANTITY=?, UNIT_PRICE=? WHERE ITEM_ID=?";

		try {

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, item.getItemName());
			statement.setString(2, item.getCategory());
			statement.setString(3, item.getMeasurement());
			statement.setInt(4, item.getQuantity());
			statement.setFloat(5, item.getUnitPrice());
			statement.setInt(6, item.getItemId());

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("A new item was updated successfully!");
				return "Successful";
			}

		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		return "Failed";
	}

	public static String deleteItem(int itemId, Connection conn) {

		String sql = "DELETE FROM ITEMS WHERE ITEM_ID=?";

		try {

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, itemId);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("A new item was deleted successfully!");
				return "Delete successfully";
			}

		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		return "Failed";

	}

	public static Item queryItem(int itemId, Connection conn ) {

		String sql = "SELECT * FROM ITEMS WHERE ITEM_ID= '" + itemId + "'";

		Item item = new Item();

		try {

			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()){


				String itemName = result.getString("ITEM_NAME");
				String category = result.getString("CATEGORY");
				String measurement = result.getString("MEASUREMENT");
				int quantity = result.getInt("QUANTITY");
				float unitPrice = result.getFloat("UNIT_PRICE");

				item.setItemId(itemId);
				item.setItemName(itemName);
				item.setMeasurement(measurement);
				item.setCategory(category);
				item.setQuantity(quantity);
				item.setUnitPrice(unitPrice);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return item;
	}

	public static List<Item> queryItem(Map<String, String> conditionMap, Connection conn) {

		String sql = "SELECT * FROM ITEMS WHERE ";

		if(conditionMap == null || conditionMap.isEmpty()) {
			sql = sql.replace("WHERE", "");
		}

		boolean needAnd = false;

		if(conditionMap.containsKey(itemId)) {

			sql = sql + "ITEM_ID = '" + conditionMap.get(itemId) + "'"; 
			needAnd = true;
		}

		if(conditionMap.containsKey(itemName)) {

			if(needAnd) {
				sql = sql + " AND ";
			}
			sql = sql + "ITEM_NAME = '" + conditionMap.get(itemName) + "'"; 
			needAnd = true;
		}

		if(conditionMap.containsKey(category)) {

			if(needAnd) {
				sql = sql + " AND ";
			}
			sql = sql + "CATEGORY = '" + conditionMap.get(category) + "'"; 
			needAnd = true;
		}

		if(conditionMap.containsKey(measurement)) {

			if(needAnd) {
				sql = sql + " AND ";
			}
			sql = sql + "MEASUREMENT = '" + conditionMap.get(measurement) + "'"; 
			needAnd = true;
		}

		if(conditionMap.containsKey(quantity)) {

			if(needAnd) {
				sql = sql + " AND ";
			}
			sql = sql + "QUANTITY = '" + conditionMap.get(quantity) + "'"; 
			needAnd = true;
		}

		if(conditionMap.containsKey(unitPrice)) {

			if(needAnd) {
				sql = sql + " AND ";
			}
			sql = sql + "UNIT_PRICE = '" + conditionMap.get(unitPrice) + "'"; 
		}

		List<Item> items = new ArrayList<Item>();

		try {

			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()){

				int itemId = result.getInt("ITEM_ID");
				String itemName = result.getString("ITEM_NAME");
				String category = result.getString("CATEGORY");
				String measurement = result.getString("MEASUREMENT");
				int quantity = result.getInt("QUANTITY");
				float unitPrice = result.getFloat("UNIT_PRICE");

				Item item = new Item();
				item.setItemId(itemId);
				item.setItemName(itemName);
				item.setMeasurement(measurement);
				item.setCategory(category);
				item.setQuantity(quantity);
				item.setUnitPrice(unitPrice);

				items.add(item);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return items;

	}


	public static List<Item> querySelectedItem(Connection conn) {

		String sql = " SELECT invoice_detail.item_id,"
				+ "invoice_detail.item_name, items.category, items.measurement,"
				+ "invoice_detail.quantity, invoice_detail.unit_price "
				+ " FROM invoice_detail LEFT JOIN items ON invoice_detail.item_id = items.item_id WHERE INVOICE_DETAIL.INVOICE_ID = (SELECT MAX(INVOICE_ID) FROM INVOICE_DETAIL)";

		List<Item> items = new ArrayList<Item>();

		try {

			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				int itemId = result.getInt("item_id");
				String itemName = result.getString("item_name");
				String category = result.getString("category");
				String measurement = result.getString("measurement");
				int selectedQuantity = result.getInt("quantity");
				int unitPrice = result.getInt("unit_price");

				Item item = new Item();
				item.setItemId(itemId);
				item.setItemName(itemName);
				item.setMeasurement(measurement);
				item.setCategory(category);
				item.setQuantity(selectedQuantity);
				item.setUnitPrice(unitPrice);
				items.add(item);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return items;

	}

	public static Booth queryBoothInfo(String boothName, Connection conn) {

		String sql = "SELECT * FROM BOOTH_INFO WHERE BOOTH_NAME ='" + boothName + "'";

		Booth booth = new Booth();
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()){

				booth.setBoothId(result.getInt("booth_ID"));
				booth.setBoothName(result.getString("booth_NAME"));

			}
		}catch (SQLException e) {
			// TODO: handle exception
		}

		return booth;
	}
	
	public static List<CustomerLevel> queryCustomerLevel(Connection conn){
		
		String sql = "SELECT * FROM CUSTOMER_LEVEL";
		List<CustomerLevel> customerLevels = new ArrayList<CustomerLevel>();
		
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				
				CustomerLevel customerLevel = new CustomerLevel();
				customerLevel.setLevel(result.getString("LEVEL"));
				
				customerLevels.add(customerLevel);
			}
			
		}catch (SQLException e) {
			// TODO: handle exception
		}
		return customerLevels;
	}

	public static Booth queryBoothInfo(int boothId, Connection conn) {

		String sql = "SELECT * FROM BOOTH_INFO WHERE BOOTH_ID ='" + boothId + "'";

		Booth booth = new Booth();
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()){

				booth.setBoothId(result.getInt("booth_ID"));
				booth.setBoothName(result.getString("booth_NAME"));

			}
		}catch (SQLException e) {
			// TODO: handle exception
		}

		return booth;
	}

	public static List <Booth> queryBoothInfo (Connection conn){

		String sql = " SELECT B.BOOTH_ID, B.BOOTH_NAME FROM BOOTH_INFO B "
				+ "left JOIN CUSTOMER_INVOICE C "
				+ "on B.BOOTH_ID = C.BOOTH_ID "
				+ "group by B.BOOTH_ID order by B.BOOTH_NAME ASC ";

		List<Booth> booths = new ArrayList<Booth>();

		try {

			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()){


				Booth booth = new Booth();

				booth.setBoothId(result.getInt("booth_ID"));
				booth.setBoothName(result.getString("booth_NAME"));
				booths.add(booth);

			}

			statement.close();

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return booths;

	}

	public static List<Level> queryLevels ( Connection conn) {

		String sql = "SELECT * FROM LEVEL";

		List<Level> levels = new ArrayList<Level>();
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while(result.next()) {
				String level = result.getString("LEVEL");

				Level level1 = new Level();
				level1.setLevel(level);
				levels.add(level1);
			}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return levels;
	}

	public static List<Category> queryCategories (Connection conn){

		String sql = "SELECT * FROM CATEGORIES";

		List<Category> categories = new ArrayList<Category>();
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()){
				String category = result.getString("CATEGORY");

				Category category1 = new Category();
				category1.setCategory(category);
				categories.add(category1);
			}



		} catch (SQLException e) {

			e.printStackTrace();
		}
		return categories;
	}

	public static List<Measurement> queryMeasurement(Connection conn) {

		String sql = "SELECT * FROM MEASUREMENTS";

		List<Measurement> measurements = new ArrayList<Measurement>();

		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()){
				String measurement = result.getString("MEASUREMENT");

				Measurement measurement2 = new Measurement();
				measurement2.setMeasurement(measurement);
				measurements.add(measurement2);
			}



		} catch (SQLException e) {

			e.printStackTrace();
		}
		return measurements;
	}

	public static List<Invoice> queryInvoice(String customerId, Connection conn){

		String sql = "SELECT * FROM CUSTOMER_INVOICE WHERE CUSTOMER_ID = '" + customerId + "'" ;

		List<Invoice> invoices = new ArrayList<>();

		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet result = statement.executeQuery(sql);

			while (result.next()){
				int invoiceId = result.getInt("INVOICE_ID");
				String tradingTime = result.getString("TRADING_TIME");
				int boothId = result.getInt("BOOTH_ID");
				float total = result.getFloat("TOTAL");

				Invoice invoice = new Invoice();
				invoice.setInvoiceId(invoiceId);
				invoice.setTradingTime(tradingTime);
				invoice.setBoothId(boothId);
				invoice.setTotal(total);
				invoices.add(invoice);
			}

			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.print("Get an invoice successfully!");

			}
		}catch (SQLException e) {

			e.printStackTrace();
		}

		return invoices;
	}

	public static List<Invoice> queryInvoice(Map<String, String> conditionMap, Connection conn){

		String sql = "SELECT * FROM CUSTOMER_INVOICE WHERE ";

		if(conditionMap == null || conditionMap.isEmpty()) {
			sql = sql.replace("WHERE", "");
		}

		boolean needAnd = false;

		if(conditionMap.containsKey(customerId)) {

			sql = sql + "CUSTOMER_ID = '" + conditionMap.get(customerId) + "'"; 
			needAnd = true;
		}
		if(conditionMap.containsKey(invoiceId)) {

			if(needAnd) {
				sql = sql + " AND ";
			}
			sql = sql + "INVOICE_ID = '" + conditionMap.get(invoiceId) + "'"; 
			needAnd = true;
		}
		if(conditionMap.containsKey(tradingTime)) {

			if(needAnd) {
				sql = sql + " AND ";
			}
			sql = sql + "TRADING_TIME = '" + conditionMap.get(tradingTime) + "'"; 
			needAnd = true;
		}
		if(conditionMap.containsKey(boothId)) {

			if(needAnd) {
				sql = sql + " AND ";
			}
			sql = sql + "BOOTH_ID = '" + conditionMap.get(boothId) + "'"; 
			needAnd = true;
		}
		if(conditionMap.containsKey(total)) {

			if(needAnd) {
				sql = sql + " AND ";
			}
			sql = sql + "TOTAL = '" + conditionMap.get(total) + "'"; 
			needAnd = true;
		}

		List<Invoice> invoices = new ArrayList<>();

		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet result = statement.executeQuery(sql);

			while (result.next()){
				int invoiceId = result.getInt("INVOICE_ID");
				String tradingTime = result.getString("TRADING_TIME");
				int boothId = result.getInt("BOOTH_ID");
				float total = result.getFloat("TOTAL");

				Invoice invoice = new Invoice();
				invoice.setInvoiceId(invoiceId);
				invoice.setTradingTime(tradingTime);
				invoice.setBoothId(boothId);
				invoice.setTotal(total);
				invoices.add(invoice);
			}

		}catch (SQLException e) {

			e.printStackTrace();
		}		
		return invoices;
	}

	public static List<Item> queryInvoiceDetail(Map<String, String> conditionMap, Connection conn) {

		String sql = "SELECT * FROM INVOICE_DETAIL WHERE ";

		if(conditionMap == null || conditionMap.isEmpty()) {
			sql = sql.replace("WHERE", "");
		}

		boolean needAnd = false;

		if(conditionMap.containsKey(invoiceId)) {

			sql = sql + "INVOICE_ID = '" + conditionMap.get(invoiceId) + "'"; 
			needAnd = true;
		}

		if(conditionMap.containsKey(itemId)) {

			if(needAnd) {
				sql = sql + " AND ";
			}
			sql = sql + "ITEM_ID = '" + conditionMap.get(itemId) + "'"; 
			needAnd = true;
		}

		if(conditionMap.containsKey(itemName)) {

			if(needAnd) {
				sql = sql + " AND ";
			}
			sql = sql + "ITEM_NAME = '" + conditionMap.get(itemName) + "'"; 
			needAnd = true;
		}

		if(conditionMap.containsKey(quantity)) {

			if(needAnd) {
				sql = sql + " AND ";
			}
			sql = sql + "QUANTITY = '" + conditionMap.get(quantity) + "'"; 
			needAnd = true;
		}

		if(conditionMap.containsKey(unitPrice)) {

			if(needAnd) {
				sql = sql + " AND ";
			}
			sql = sql + "UNIT_PRICE = '" + conditionMap.get(unitPrice) + "'"; 
		}

		List<Item> items = new ArrayList<Item>();

		try {

			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()){

				int itemId = result.getInt("ITEM_ID");
				String itemName = result.getString("ITEM_NAME");
				int quantity = result.getInt("QUANTITY");
				float unitPrice = result.getFloat("UNIT_PRICE");

				Item item = new Item();
				item.setItemId(itemId);
				item.setItemName(itemName);
				item.setQuantity(quantity);
				item.setUnitPrice(unitPrice);

				items.add(item);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return items;

	}

	public static List<Item> queryItemInIvoice(String invoiceId, Connection conn) {

		String sql = "SELECT * FROM INVOICE_DETAIL WHERE INVOICE_ID = '" + invoiceId + "'" ;

		List<Item> items = new ArrayList<>();
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet result = statement.executeQuery(sql);

			while (result.next()){
				int itemId = result.getInt("ITEM_ID");
				String itemName = result.getString("ITEM_NAME");
				int quantity = result.getInt("QUANTITY");
				float unitPrice = result.getFloat("UNIT_PRICE");

				Item item = new Item();
				item.setItemId(itemId);
				item.setItemName(itemName);
				item.setQuantity(quantity);
				item.setUnitPrice(unitPrice);

				items.add(item);
			}

			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.print("Get an invoice successfully!");

			}
		}catch (SQLException e) {

			e.printStackTrace();
		}

		return items;
	}

	public static int getMaxInvoiceId(Connection conn) {
		String sql = "SELECT MAX(INVOICE_ID) AS MAX FROM INVOICE_DETAIL ";
		int max = 0;

		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet result = statement.executeQuery(sql);

			if (result.next()){
				max = result.getInt("MAX");
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Max invoice id = " + max );
		return max;
	}

	public static int getMaxCustomerId(Connection conn) {
		String sql = "SELECT MAX(CUSToMER_ID) AS MAX FROM CUSTOMERS ";
		int max = 0;

		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet result = statement.executeQuery(sql);

			if (result.next()){
				max = result.getInt("MAX");
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Max customer id = " + max );
		return max;
	}

	public static int getMaxItemId(Connection conn) {

		String sql = "SELECT MAX(ITEM_ID) AS MAX FROM ITEMS ";
		int max =0;
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet result = statement.executeQuery(sql);

			if (result.next()){
				max = result.getInt("MAX");
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Max item id = " + max );
		return max;

	}

	public static boolean existsPhone(String phoneNumbers, Connection conn) {
		String query = "SELECT * FROM customers WHERE PHONENUMBERS = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, phoneNumbers);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	public static Customer queryCustomerByPhone(String phoneNumbers, Connection conn) {

		String sql = "SELECT * FROM CUSTOMERS WHERE PHONENUMBERS = '" + phoneNumbers + "'";

		Customer customer = new Customer();
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet result = statement.executeQuery(sql);

			while(result.next()) {

				int customerId1 = result.getInt("CUSTOMER_ID");
				String customerName = result.getString("CUSTOMER_NAME");
				String phonenumbers = result.getString("PHONENUMBERS");
				String address = result.getString("ADDRESS");
				String level = result.getString("LEVEL");

				customer.setCustomerId(customerId1);
				customer.setCustomerName(customerName);
				customer.setPhoneNumbers(phonenumbers);
				customer.setAddress(address);
				customer.setLevel(level);
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}


		return customer;

	}

	public static String insertCustomerInvoice(int customerId, int invoiceId, int boothId,
			float total, Connection conn) {

		String sql = "INSERT INTO CUSTOMER_INVOICE (CUSTOMER_ID, INVOICE_ID, TRADING_TIME, BOOTH_ID, TOTAL, UPDATED_DATE) VALUES (?, ?, ?, ?, ?, ?)";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String currentTime = formatter.format(date);
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, customerId);
			statement.setInt(2, invoiceId);
			statement.setString(3, currentTime);
			statement.setInt(4, boothId);
			statement.setFloat(5, total);
			statement.setString(6, currentTime);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("A new customer_invoice was inserted successfully!");
				return "Successful";
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}

		return "Failed";
	}

	public static String insertInvoiceDetail(int invoiceId, Item item, Connection conn) {
		String sql = "INSERT INTO INVOICE_DETAIL (INVOICE_ID, ITEM_ID, ITEM_NAME, QUANTITY, UNIT_PRICE, UPDATED_DATE) VALUES (?, ?, ?, ?, ?, ?)";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String currentTime = formatter.format(date);
		try {

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, invoiceId);
			statement.setInt(2, item.getItemId());
			statement.setString(3, item.getItemName());
			statement.setInt(4, item.getQuantity());
			statement.setFloat(5, item.getUnitPrice());
			statement.setString(6, currentTime);


			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("A new invoice_detail was inserted successfully!");
				return "A new Invoice Detail was inserted successfully!";
			}

		}catch (SQLException ex) {
			ex.printStackTrace();
		}

		return "Failed";
	}

	public static int invoiceQuantity(int customerId, Connection conn) {
	    String sql = "SELECT COUNT(INVOICE_ID) AS MAX FROM CUSTOMER_INVOICE WHERE CUSTOMER_ID=?";
	    int max = 0;
	    try {
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setInt(1, customerId);
	        ResultSet result = statement.executeQuery(); //
	        if(result.next()) {
	            max = result.getInt("MAX");
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return max;
	}


	public static String updateLevel(int customerId, Connection conn) {
	    int level =  DBOperation.invoiceQuantity(customerId, conn);
	    String sql = "";
	    String doanhso = "SELECT SUM(TOTAL) AS total_sum FROM CUSTOMER_INVOICE WHERE YEAR(TRADING_TIME) = YEAR(CURDATE()) AND CUSTOMER_ID = ?";
	    int doanhso1 = 0;

	    try {
	        PreparedStatement statement = conn.prepareStatement(doanhso);
	        statement.setInt(1, customerId);
	        ResultSet result = statement.executeQuery();
	        if (result.next()) {
	            doanhso1 = result.getInt("total_sum");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    if(doanhso1 < 1000) {
	        sql = " UPDATE CUSTOMERS SET LEVEL= 'NEW' WHERE CUSTOMER_ID=?";
	    } else if(doanhso1 < 3000) {
	        sql = " UPDATE CUSTOMERS SET LEVEL= 'SILVER' WHERE CUSTOMER_ID=?";
	    } else if(doanhso1 < 5000) {
	        sql = " UPDATE CUSTOMERS SET LEVEL= 'GOLD' WHERE CUSTOMER_ID=?";
	    } else if(doanhso1 < 8000) {
	        sql = " UPDATE CUSTOMERS SET LEVEL= 'DIAMOND' WHERE CUSTOMER_ID=?";
	    } else {
	        sql = " UPDATE CUSTOMERS SET LEVEL= 'VIP' WHERE CUSTOMER_ID=?";
	    }

	    try {
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setInt(1, customerId);
	        statement.executeUpdate(); 
	        System.out.println("Update level customer successfully!");
	        return "SUCCESS!";
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return "FAILED!";
	}
	public static List<Campaign> queryCampaign(Connection conn) {
		
		String sql = "SELECT * FROM CAMPAIGN ";
		List<Campaign> campaigns = new ArrayList<Campaign>();
		
		try {
	        PreparedStatement statement = conn.prepareStatement(sql);
	        ResultSet result = statement.executeQuery(sql);
	        while(result.next()) {
	   		 int campaignId = result.getInt("CAMPAIGN_ID");
			 String campaignName = result.getString("CAMPAIGN_NAME");
			 String target_customer = result.getString("TARGET_CUSTOMER_LEVEL");
			 String campaignCode = result.getString("CAMPAIGN_CODE");
			 String status = result.getString("STATUS");
			 String msg_content = result.getString("MSG_CONTENT");
			 String campaign_timestamp = result.getString("CAMPAIGN_TIMESTAMP");
	        	
			 Campaign campaign = new Campaign();
			 campaign.setCampaignId(campaignId);
			 campaign.setCampaignName(campaignName);
			 campaign.setTarget_customer(target_customer);
			 campaign.setCampaignCode(campaignCode);
			 campaign.setStatus(status);
			 campaign.setMsg_content(msg_content);
			 campaign.setCampaign_timestamp(campaign_timestamp);
			 
			 campaigns.add(campaign);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return campaigns;
	}
}

