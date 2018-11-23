/**
 * 
 */
package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Shalika Ashan
 *
 */
public class ProductModel {

	private int pid;
	private String pname;
	private String category;
	private String addeddate;
	private int bidPeriod_days;
	private String pcondition;
	private float lastBidPrice;
	private int sellerId;
	private String description;
	private String status;
	private String brand;
	private String model;
	private String addedTime;
	private String endDate;
	private  String[] image;
	private int winner;
	private long endDateLong;
	/**
	 * @return the addedTime
	 */
	public String getAddedTime() {
		return addedTime;
	}

	/**
	 * @param addedTime the addedTime to set
	 */
	public void setAddedTime(String addedTime) {
		this.addedTime = addedTime;
	}

	public ProductModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the pid
	 */
	public int getPid() {
		return pid;
	}

	/**
	 * @param pid the pid to set
	 */
	public void setPid(int pid) {
		this.pid = pid;
	}

	/**
	 * @return the pname
	 */
	public String getPname() {
		return pname;
	}

	/**
	 * @param pname the pname to set
	 */
	public void setPname(String pname) {
		this.pname = pname;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the addeddate
	 */
	public String getAddeddate() {
		return addeddate;
	}

	/**
	 * @param addeddate the addeddate to set
	 */
	public void setAddeddate(String addeddate) {
		this.addeddate = addeddate;
	}

	/**
	 * @return the bidPeriod_days
	 */
	public int getBidPeriod_days() {
		return bidPeriod_days;
	}

	/**
	 * @param bidPeriod_days the bidPeriod_days to set
	 */
	public void setBidPeriod_days(int bidPeriod_days) {
		this.bidPeriod_days = bidPeriod_days;
	}

	/**
	 * @return the pcondition
	 */
	public String getPcondition() {
		return pcondition;
	}

	/**
	 * @param pcondition the pcondition to set
	 */
	public void setPcondition(String pcondition) {
		this.pcondition = pcondition;
	}

	/**
	 * @return the lastBidPrice
	 */
	public float getLastBidPrice() {
		return lastBidPrice;
	}

	/**
	 * @param lastBidPrice the lastBidPrice to set
	 */
	public void setLastBidPrice(float lastBidPrice) {
		this.lastBidPrice = lastBidPrice;
	}

	/**
	 * @return the sellerId
	 */
	public int getSellerId() {
		return sellerId;
	}

	/**
	 * @param sellerId the sellerId to set
	 */
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	public void setEndDate() {
		SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = null;
		try {
			startDate = dtf.parse(this.addeddate+" " + this.addedTime);
			long end = startDate.getTime()+this.bidPeriod_days*(24 * 60 * 60 * 1000);
			this.endDateLong = end;
			this.endDate = dtf.format(end);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public String getEndDate() {
		return this.endDate;
	}

	/**
	 * @return the image
	 */
	public String getImage(int index) {
		return image[index];
	}
	/**
	 * @return the image
	 */
	public String[] getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String[] image) {
		this.image = image;
	}

	/**
	 * @return the winner
	 */
	public int getWinner() {
		return winner;
	}

	/**
	 * @param winner the winner to set
	 */
	public void setWinner(int winner) {
		this.winner = winner;
	}
	public long dateDifference() {
		return this.endDateLong - System.currentTimeMillis();
	}
}