/**
 * 
 */
package service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import model.AuctionModel;
import model.ProductModel;

/**
 * @author Shalika Ashan
 *
 */
public interface ProductService {
	/** Initialize logger */
	public static final Logger log = Logger.getLogger(PersonService.class.getName());
	public ProductModel selectByID(int id);
	public int[] placebid(int productId,float price,int cid);
	public int getMaxBidAmount(int pid);
	public ArrayList<ProductModel> showRelatedProduct(int pid,String category);
	public ArrayList<ProductModel> searchResult(String keyword);
	public ArrayList<AuctionModel> selectAllBidByCID(int cid);
	public String[] getPicturesByPid(int pid);
	public ArrayList<ProductModel> setImagetoResults(ArrayList<ProductModel> input);
	public Boolean getFavoriteProductByPidandCid(int cid,int pid);
	public int addtofavorite(int cid,int pid);
	public int deleteFromFavorite(int cid,int pid);
	public List<Integer> getAllFavoriteByCID(int cid);
	public List<Integer> getTopBiddingProduct();
	public ArrayList<ProductModel> showProducts(String keywork);
	public int updateProductByAdmin(int pid);
	public ArrayList<ProductModel> showProductBySellerId(int sid);
	public int updateWinner(int pid);
	public ArrayList<ProductModel> getAuctionEndProducts(int sid);
	public ArrayList<ProductModel> OngoingAuctionEndProducts(int sid);
	public int makePayment(int pid,int cid,double price);
	public List<Integer> myOrders(int cid);
	public int addProduct(ProductModel product);
	public int removeProductByAdmin(int pid);
	public ArrayList<ProductModel> notBoughtyetProductBySellerId(int sid);
	public ArrayList<ProductModel> soldProductBySellerID(int sid);
	public int showBidCount(int pid);
}