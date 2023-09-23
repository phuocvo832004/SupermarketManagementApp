package sma.object;

public class Invoice {
	
	private int invoiceId;
	private String tradingTime;
	private int boothId;
	private String boothName;
	private float total;
	
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getTradingTime() {
		return tradingTime;
	}
	public void setTradingTime(String tradingTime) {
		this.tradingTime = tradingTime;
	}
	public int getBoothId() {
		return boothId;
	}
	public void setBoothId(int boothId) {
		this.boothId = boothId;
	}
	public String getBoothName() {
		return boothName;
	}
	public void setBoothName(String boothName) {
		this.boothName = boothName;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	
	
	

}
