package sma.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import sma.object.Category;
import sma.object.Customer;
import sma.object.Item;
import sma.object.Measurement;

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

		String sql = "INSERT INTO CUSTOMERS (CUSTOMER_ID, CUSTOMER_NAME, PHONENUMBERS, ADDRESS) VALUES (?, ?, ?, ?)";

		try {

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, customer.getCustomerId());
			statement.setString(2, customer.getCustomerName());
			statement.setString(3, customer.getPhoneNumbers());
			statement.setString(4, customer.getAddress());

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

	public static List<Customer> queryCustomer(Map<String, String> conditionMap, Connection conn) {

		String sql = "SELECT * FROM CUSTOMERS WHERE ";

		if(conditionMap == null || conditionMap.isEmpty()) {
			sql = sql.replace("WHERE", "");
		}

		boolean needAnd = false;

		if(conditionMap.containsKey(customerId)) {

			sql = sql + "CUSTOMER_ID = '" + conditionMap.get(customerId) + "'"; 
			needAnd = true;
		}

		if(conditionMap.containsKey(customerName)) {

			if(needAnd) {
				sql = sql + " AND ";
			}
			sql = sql + "CUSTOMER_NAME = '" + conditionMap.get(customerName) + "'"; 
			needAnd = true;
		}

		if(conditionMap.containsKey(phoneNumbers)) {

			if(needAnd) {
				sql = sql + " AND ";
			}
			sql = sql + "PHONENUMBERS = '" + conditionMap.get(phoneNumbers) + "'"; 
			needAnd = true;
		}

		if(conditionMap.containsKey(address)) {

			if(needAnd) {
				sql = sql + " AND ";
			}
			sql = sql + "ADDRESS = '" + conditionMap.get(address) + "'"; 
			
		}

		List<Customer> customers = new ArrayList<Customer>();

		try {

			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()){

				 String customerId = result.getString("CUSTOMER_ID");
				 String customerName = result.getString("CUSTOMER_NAME");
				 String phoneNumbers = result.getString("PHONENUMBERS");
				 String address = result.getString("ADDRESS");

				Customer customer = new Customer();
				customer.setCustomerId(customerId);
				customer.setCustomerName(customerName);
				customer.setPhoneNumbers(phoneNumbers);
				customer.setAddress(address);

				customers.add(customer);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}

	public static String updateCustomer(Customer customer, Connection conn) {
		
		String sql = "UPDATE CUSTOMERS SET CUSTOMER_NAME=?, PHONENUMBERS=?, ADDRESS=? WHERE CUSTOMER_ID=?";

		try {

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, customer.getCustomerName());
			statement.setString(2, customer.getPhoneNumbers());
			statement.setString(3, customer.getAddress());
			statement.setString(4, customer.getCustomerId());

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
	
	public static String deleteCustomer(String customerId, Connection conn) {
		
		String sql = "DELETE FROM CUSTOMERS WHERE cUSTOMER_ID=?";
		
		try {

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, customerId);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("A new user was deleted successfully!");
				return "Delete successfully";
			}

		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		return "Failed";
		
	}
	
	public static String insertItem(Item item, Connection conn) {
		
		String sql = "INSERT INTO ITEMS (ITEM_ID, NAME, CATEGORY, MEASUREMENT, QUANTITY, UNIT_PRICE) VALUES (?, ?, ?, ?, ?, ?)";
		
		try {

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, itemId);
			statement.setString(2, itemName);
			statement.setString(3, category);
			statement.setString(4, measurement);
			statement.setString(5, quantity);
			statement.setString(6, unitPrice);
			

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("A new user was deleted successfully!");
				return "Delete successfully";
			}

		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		return "Failed";
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

				 String itemId = result.getString("ITEM_ID");
				 String itemName = result.getString("ITEM_NAME");
				 String category = result.getString("CATEGORY");
				 String measurement = result.getString("MEASUREMENT");
				 String quantity = result.getString("QUANTITY");
				 String unitPrice = result.getString("UNIT_PRICE");
				 
				Item item = new Item();
				item.setItemId(itemId);
				item.setItemName(itemName);
				item.setMeasurement(measurement);
				item.setCategory(category);
				item.setUnitPrice(unitPrice);
				
				items.add(item);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return items;
		
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return measurements;
		
		
		
	}
	
}

