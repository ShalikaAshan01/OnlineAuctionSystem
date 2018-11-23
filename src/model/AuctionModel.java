/**
 * 
 */
package model;

/**
 * @author Shalika Ashan
 *
 */
public class AuctionModel {

	private int bidId;
	private int pid;
	private int cid;
	private String bidDate;
	private float bidPrice;
	public AuctionModel() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the bidId
	 */
	public int getBidId() {
		return bidId;
	}
	/**
	 * @param bidId the bidId to set
	 */
	public void setBidId(int bidId) {
		this.bidId = bidId;
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
	 * @return the cid
	 */
	public int getCid() {
		return cid;
	}
	/**
	 * @param cid the cid to set
	 */
	public void setCid(int cid) {
		this.cid = cid;
	}
	/**
	 * @return the bidDate
	 */
	public String getBidDate() {
		return bidDate;
	}
	/**
	 * @param bidDate the bidDate to set
	 */
	public void setBidDate(String bidDate) {
		this.bidDate = bidDate;
	}
	/**
	 * @return the bidPrice
	 */
	public float getBidPrice() {
		return bidPrice;
	}
	/**
	 * @param bidPrice the bidPrice to set
	 */
	public void setBidPrice(float bidPrice) {
		this.bidPrice = bidPrice;
	}

}
