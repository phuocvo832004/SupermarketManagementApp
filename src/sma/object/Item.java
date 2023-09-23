package sma.object;

public class Item {

	private int itemId;
	private String itemName;
	private String category;
	private String measurement;
	private int quantity;
	private String total;
	private float unitPrice;
	private float price;
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getMeasurement() {
		return measurement;
	}
	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity2) {
		this.quantity = quantity2;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(float unitPrice2) {
		this.unitPrice = unitPrice2;
	}
	public float getPrice() {
		return quantity * unitPrice;
	}
	public void setPrice(float price) {
		this.price = price;
	}


	
	
	
}
