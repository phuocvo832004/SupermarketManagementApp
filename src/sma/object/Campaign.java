package sma.object;

public class Campaign {
	private int campaignId;
	private String campaignName;
	private String target_customer;
	private String campaignCode;
	private String status;
	private String msg_content;
	private String campaign_timestamp;
	public int getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}
	public String getCampaignName() {
		return campaignName;
	}
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}
	public String getTarget_customer() {
		return target_customer;
	}
	public void setTarget_customer(String target_customer) {
		this.target_customer = target_customer;
	}
	public String getCampaignCode() {
		return campaignCode;
	}
	public void setCampaignCode(String campaignCode) {
		this.campaignCode = campaignCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg_content() {
		return msg_content;
	}
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	public String getCampaign_timestamp() {
		return campaign_timestamp;
	}
	public void setCampaign_timestamp(String campaign_timestamp) {
		this.campaign_timestamp = campaign_timestamp;
	}
}
