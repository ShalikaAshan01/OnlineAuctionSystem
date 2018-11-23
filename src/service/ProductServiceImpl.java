
package service;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.spi.DirStateFactory.Result;
import javax.sound.midi.Soundbank;

import model.AuctionModel;
import model.ProductModel;
import util.DBConnectionUtil;
import util.dbCheck;

public class ProductServiceImpl implements ProductService {

	private static Connection conn;
	private static PreparedStatement preparedstatement;
	/** initialize the logger **/
	public static final Logger log = Logger.getLogger(PersonService.class.getName());

	public ProductServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * search item by ites product id
	 * 
	 * @return productDetails or null
	 */
	@Override
	public ProductModel selectByID(int id) {
		try {
			conn = DBConnectionUtil.getDBConnection();
			String query = "SELECT * FROM product WHERE pid=? AND status='auction' ";
			PreparedStatement preparedstatement = conn.prepareStatement(query);
			preparedstatement.setInt(1, id);
			ResultSet result = preparedstatement.executeQuery();
			if (result.next()) {
				ProductModel details = new ProductModel();
				details.setPid(result.getInt(1));
				details.setPname(result.getString(2));
				details.setCategory(result.getString(3));
				details.setAddeddate(result.getString(4));
				details.setBidPeriod_days(result.getInt(5));
				details.setPcondition(result.getString(6));
				details.setLastBidPrice(result.getFloat(7));
				details.setSellerId(result.getInt(8));
				details.setDescription(result.getString(9));
				details.setBrand(result.getString(10));
				details.setModel(result.getString(11));
				details.setStatus(result.getString(12));
				details.setAddedTime(result.getString(13));
				details.setEndDate();
				details.setWinner(result.getInt("wonBy"));
				details.setImage(getPicturesByPid(id));
				return details;
			}
		} catch (SQLException | ClassNotFoundException | NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		/**
		 * close the prepared statement and database connection at the end
		 */

		finally {
			try {
				if (preparedstatement != null) {
					preparedstatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

		return null;
	}

	@Override
	public int[] placebid(int productId, float price, int cid) {
		String query1 = "INSERT INTO biddetails(bidPrice, cid, pid, bidDate) VALUES (?,?,?,?)";
		String query2 = "UPDATE product SET `lastBidPrice`=? WHERE pid=?";
		int result[] = { 0, 0 };

		/* get local date and time */
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String dateandtime = dtf.format(now);
		try {
			conn = DBConnectionUtil.getDBConnection();
			PreparedStatement preparedStatement1 = conn.prepareStatement(query1);
			PreparedStatement preparedStatement2 = conn.prepareStatement(query2);

			preparedStatement1.setFloat(1, price);
			preparedStatement1.setInt(2, cid);
			preparedStatement1.setInt(3, productId);
			preparedStatement1.setString(4, dateandtime);

			preparedStatement2.setFloat(1, price);
			preparedStatement2.setInt(2, productId);

			result[0] = preparedStatement1.executeUpdate();
			result[1] = preparedStatement2.executeUpdate();

		} catch (SQLException | ClassNotFoundException | NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		/**
		 * close the prepared statement and database connection at the end
		 */

		finally {
			try {
				if (preparedstatement != null) {
					preparedstatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return result;
	}

	/**
	 * find maximum bid amount and who is placed the bid
	 * 
	 * @return
	 */
	@Override
	public int getMaxBidAmount(int pid) {
		String query = "SELECT cid FROM biddetails WHERE pid=? AND bidPrice=(SELECT MAX(bidPrice)"
				+ " FROM biddetails WHERE pid=? HAVING MAX(bidPrice))";
		int cid = 0;
		try {
			conn = DBConnectionUtil.getDBConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, pid);
			preparedStatement.setInt(2, pid);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				cid = result.getInt("cid");
			}

		} catch (SQLException | ClassNotFoundException | NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		/**
		 * close the prepared statement and database connection at the end
		 */

		finally {
			try {
				if (preparedstatement != null) {
					preparedstatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return cid;
	}

	@Override
	public ArrayList<ProductModel> showRelatedProduct(int pid, String category) {
		String query = "SELECT pid, pname,addeddate, bidPeriod_days, pcondition, lastBidPrice,addedTime,wonBy "
				+ "FROM product WHERE category=? AND status LIKE 'auction'";
		ArrayList<ProductModel> relatedProduct = new ArrayList<ProductModel>();
		try {
			conn = DBConnectionUtil.getDBConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			// preparedStatement.setInt(1, pid);
			preparedStatement.setString(1, category);
			ResultSet result = preparedStatement.executeQuery();
			int count =1;
			while (result.next()) {
				ProductModel product = new ProductModel();
				product.setPid(result.getInt("pid"));
				if (product.getPid() == pid) {
					continue;
				}
				if(count==5) {
					break;
				}
				product.setWinner(result.getInt("wonBy"));
				/*
				 * winner id = 0 mean this product hasnt winner.
				 * So if product has winner that prouduct will skip
				 */
				if(product.getWinner() != 0) {
					continue;
				}
				
				
				product.setPname(result.getString("pname"));
				product.setAddeddate(result.getString("addeddate"));
				product.setBidPeriod_days(result.getInt("bidPeriod_days"));
				product.setPcondition(result.getString("pcondition"));
				product.setLastBidPrice(result.getFloat("lastBidPrice"));
				product.setAddedTime(result.getString("addedTime"));
				product.setEndDate();
				// product.setImage(getPicturesByPid(result.getInt("pid")));
				relatedProduct.add(product);
				count++;
			}
		} catch (SQLException | ClassNotFoundException | NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		/**
		 * close the prepared statement and database connection at the end
		 */

		finally {
			try {
				if (preparedstatement != null) {
					preparedstatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		ArrayList<ProductModel> relatedProductList = setImagetoResults(relatedProduct);
		return relatedProductList;
	}

	/**
	 * select product from database where user's keyword
	 */

	@Override
	public ArrayList<ProductModel> searchResult(String keyword) {
		ArrayList<ProductModel> productResult = new ArrayList<ProductModel>();
		try {
			conn = DBConnectionUtil.getDBConnection();
			String query = "SELECT * FROM product WHERE pname Like ? OR category LIKE ? OR description LIKE ? OR"
					+ " brand LIKE ? OR model LIKE ? HAVING status='auction'  ORDER BY addeddate DESC";
			PreparedStatement preparedstatement = conn.prepareStatement(query);
			keyword = "%" + keyword + "%";
			preparedstatement.setString(1, keyword);
			preparedstatement.setString(2, keyword);
			preparedstatement.setString(3, keyword);
			preparedstatement.setString(4, keyword);
			preparedstatement.setString(5, keyword);
			ResultSet result = preparedstatement.executeQuery();
			while (result.next()) {
				ProductModel product = new ProductModel();
				product.setWinner(result.getInt("wonBy"));
				if(product.getWinner()!=0) {
					continue;
				}
				
				product.setPid(result.getInt(1));
				product.setPname(result.getString(2));
				product.setCategory(result.getString(3));
				product.setAddeddate(result.getString(4));
				product.setBidPeriod_days(result.getInt(5));
				product.setPcondition(result.getString(6));
				product.setLastBidPrice(result.getFloat(7));
				product.setSellerId(result.getInt(8));
				product.setDescription(result.getString(9));
				product.setBrand(result.getString(10));
				product.setModel(result.getString(11));
				product.setStatus(result.getString(12));
				product.setAddedTime(result.getString(13));
				product.setEndDate();
				// product.setImage(getPicturesByPid(result.getInt("pid")));
				productResult.add(product);
			}

		} catch (SQLException | ClassNotFoundException | NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		/**
		 * close the prepared statement and database connection at the end
		 */

		finally {
			try {
				if (preparedstatement != null) {
					preparedstatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		ArrayList<ProductModel> productList = setImagetoResults(productResult);
		return productList;
	}

	/**
	 * get all bid results form specific user
	 */
	@Override
	public ArrayList<AuctionModel> selectAllBidByCID(int cid) {
		ArrayList<AuctionModel> bidDetails = new ArrayList<>();
		try {
			String query = "SELECT * FROM biddetails WHERE cid=? ORDER BY(bidDate) DESC";
			conn = DBConnectionUtil.getDBConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, cid);
			ResultSet result = preparedStatement.executeQuery();
			/*
			 * get all matchiing results
			 */
			while (result.next()) {
				AuctionModel auctiondetails = new AuctionModel();
				auctiondetails.setBidId(result.getInt(1));
				auctiondetails.setBidPrice(result.getFloat(2));
				auctiondetails.setCid(result.getInt(3));
				auctiondetails.setPid(result.getInt(4));
				auctiondetails.setBidDate(result.getString(5));
				bidDetails.add(auctiondetails);
			}

		} catch (SQLException | ClassNotFoundException | NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		/**
		 * close the prepared statement and database connection at the end
		 */

		finally {
			try {
				if (preparedstatement != null) {
					preparedstatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return bidDetails;
	}

	/**
	 * Retrive image path from database
	 */
	@Override
	public String[] getPicturesByPid(int pid) {
		String image[] = new String[5];

		try {
			String query = "SELECT imgPath FROM product_picture WHERE pid=?";
			conn = DBConnectionUtil.getDBConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, pid);
			ResultSet result = preparedStatement.executeQuery();
			int count = 0;
			while (result.next()) {
				File path = new File(result.getString("imgPath"));
				image[count] = path.getPath();
				count++;
			}
			for(int i=0; i<5; i++) {
				if(image[i] == null || image[i].isEmpty()) {
					image[i]= "style/images/No-Image-Available.jpg";
				}
			}

		} catch (SQLException | ClassNotFoundException | NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		/**
		 * close the prepared statement and database connection at the end
		 */

		finally {
			try {
				if (preparedstatement != null) {
					preparedstatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return image;
	}

	/**
	 * set product image to all arraylist product obects and return again arralist
	 */
	@Override
	public ArrayList<ProductModel> setImagetoResults(ArrayList<ProductModel> input) {
		ArrayList<ProductModel> result = new ArrayList<>();
		for (ProductModel productSet : input) {
			ProductModel product = new ProductModel();
			product.setPid(productSet.getPid());
			product.setPname(productSet.getPname());
			product.setCategory(productSet.getCategory());
			product.setAddeddate(productSet.getAddeddate());
			product.setBidPeriod_days(productSet.getBidPeriod_days());
			product.setPcondition(productSet.getPcondition());
			product.setLastBidPrice(productSet.getLastBidPrice());
			product.setSellerId(productSet.getSellerId());
			product.setDescription(productSet.getDescription());
			product.setBrand(productSet.getBrand());
			product.setModel(productSet.getModel());
			product.setStatus(productSet.getStatus());
			product.setAddedTime(productSet.getAddedTime());
			product.setEndDate();
			product.setImage(getPicturesByPid(productSet.getPid()));
			result.add(product);
		}
		return result;
	}

	/**
	 * get selected product is in a customer's favorites or not
	 * 
	 * @return true or false
	 */
	@Override
	public Boolean getFavoriteProductByPidandCid(int cid, int pid) {
		boolean result = false;
		try {
			String query = "SELECT cid, pid FROM myfavorite WHERE cid=? AND pid=?";
			conn = DBConnectionUtil.getDBConnection();

			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, cid);
			preparedStatement.setInt(2, pid);
			ResultSet value = preparedStatement.executeQuery();

			if (value.next()) {
				result = true;
			} else {
				result = false;
			}

		} catch (SQLException | ClassNotFoundException | NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		/**
		 * close the prepared statement and database connection at the end
		 */

		finally {
			try {
				if (preparedstatement != null) {
					preparedstatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return result;
	}

	public int addtofavorite(int cid, int pid) {
		int result = 0;
		try {
			String query = "INSERT INTO myfavorite VALUES (?,?)";
			conn = DBConnectionUtil.getDBConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, cid);
			preparedStatement.setInt(2, pid);
			result = preparedStatement.executeUpdate();
		} catch (SQLException | ClassNotFoundException | NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		/**
		 * close the prepared statement and database connection at the end
		 */

		finally {
			try {
				if (preparedstatement != null) {
					preparedstatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return result;
	}

	/**
	 * Delete the product from my favorite
	 */
	@Override
	public int deleteFromFavorite(int cid, int pid) {

		int result = 0;
		try {
			String query = "DELETE FROM myfavorite WHERE cid=? AND pid=?";
			conn = DBConnectionUtil.getDBConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, cid);
			preparedStatement.setInt(2, pid);
			result = preparedStatement.executeUpdate();

		} catch (SQLException | ClassNotFoundException | NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		/**
		 * close the prepared statement and database connection at the end
		 */

		finally {
			try {
				if (preparedstatement != null) {
					preparedstatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

		return result;
	}

	/**
	 * Display all Favorites by Customer ID
	 */
	@Override
	public List<Integer> getAllFavoriteByCID(int cid) {
		List<Integer> favProduct = new ArrayList<Integer>();
		try {
			String query = "SELECT pid FROM myfavorite WHERE cid=?";
			conn = DBConnectionUtil.getDBConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, cid);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				favProduct.add(result.getInt("pid"));
			}
		} catch (SQLException | ClassNotFoundException | NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		/**
		 * close the prepared statement and database connection at the end
		 */

		finally {
			try {
				if (preparedstatement != null) {
					preparedstatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

		return favProduct;
	}

	/**
	 * Get top bidding products
	 */
	@Override
	public List<Integer> getTopBiddingProduct() {
		List<Integer> pid = new ArrayList<>();
		try {
			String query = "SELECT pid,COUNT(*) FROM biddetails GROUP BY pid  ORDER BY COUNT(*) DESC";
			conn = DBConnectionUtil.getDBConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			ResultSet result = preparedStatement.executeQuery();
			int count = 1;
			while (result.next()) {
				if(count ==5) {
					break;
				}
				pid.add(result.getInt("pid"));
				count ++;
			}
		} catch (SQLException | ClassNotFoundException | NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		/**
		 * close the prepared statement and database connection at the end
		 */

		finally {
			try {
				if (preparedstatement != null) {
					preparedstatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return pid;
	}

	/**
	 * show all products to admin
	 * 
	 * @return
	 */
	@Override
	public ArrayList<ProductModel> showProducts(String keyword) {

		String query = "SELECT * FROM product WHERE status LIKE ?  ORDER BY pid DESC";

		ArrayList<ProductModel> showProducts = new ArrayList<ProductModel>();

		try {
			conn = DBConnectionUtil.getDBConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, "%" + keyword + "%");
			ResultSet res = preparedStatement.executeQuery();

			while (res.next()) {

				ProductModel pro = new ProductModel();
				pro.setPid(res.getInt("pid"));
				pro.setPname(res.getString("pname"));
				pro.setCategory(res.getString("category"));
				pro.setAddeddate(res.getString("addeddate"));
				pro.setBidPeriod_days(res.getInt("bidPeriod_days"));
				pro.setPcondition(res.getString("pcondition"));
				pro.setLastBidPrice(res.getFloat("lastBidPrice"));
				pro.setSellerId(res.getInt("sellerId"));
				pro.setDescription(res.getString("description"));
				pro.setBrand(res.getString("brand"));
				pro.setModel(res.getString("model"));
				pro.setStatus(res.getString("status"));
				pro.setAddedTime(res.getString("addedTime"));
				showProducts.add(pro);

			}
		} catch (SQLException | ClassNotFoundException | NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		/**
		 * close the prepared statement and database connection at the end
		 */

		finally {
			try {
				if (preparedstatement != null) {
					preparedstatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return showProducts;
	}

	public int updateProductByAdmin(int pid) {
		int result = 0;
		try {
			String query = "UPDATE product SET status='auction' WHERE pid=?";
			conn = DBConnectionUtil.getDBConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, pid);
			result = preparedStatement.executeUpdate();
		} catch (SQLException | ClassNotFoundException | NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		/**
		 * close the prepared statement and database connection at the end
		 */

		finally {
			try {
				if (preparedstatement != null) {
					preparedstatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return result;
	}

	/**
	 * Show all products if he added
	 */
	@Override
	public ArrayList<ProductModel> showProductBySellerId(int sid) {
		String query = "SELECT * FROM product WHERE sellerID=? AND status LIKE 'auction' ORDER BY addeddate DESC";

		ArrayList<ProductModel> showProduct = new ArrayList<ProductModel>();

		try {
			conn = DBConnectionUtil.getDBConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, sid);
			ResultSet res = preparedStatement.executeQuery();

			while (res.next()) {

				ProductModel pro = new ProductModel();
				pro.setPid(res.getInt("pid"));
				pro.setPname(res.getString("pname"));
				pro.setCategory(res.getString("category"));
				pro.setAddeddate(res.getString("addeddate"));
				pro.setBidPeriod_days(res.getInt("bidPeriod_days"));
				pro.setPcondition(res.getString("pcondition"));
				pro.setLastBidPrice(res.getFloat("lastBidPrice"));
				pro.setSellerId(res.getInt("sellerId"));
				pro.setDescription(res.getString("description"));
				pro.setBrand(res.getString("brand"));
				pro.setModel(res.getString("model"));
				pro.setStatus(res.getString("status"));
				pro.setAddedTime(res.getString("addedTime"));
				pro.setWinner(res.getInt("wonBy"));
				pro.setEndDate();
				showProduct.add(pro);

			}
		} catch (SQLException | ClassNotFoundException | NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		/**
		 * close the prepared statement and database connection at the end
		 */

		finally {
			try {
				if (preparedstatement != null) {
					preparedstatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return showProduct;
	}

	/**
	 * update product winner
	 */
	@Override
	public int updateWinner(int pid) {
		int result = 0;
		try {
			String query = "UPDATE product SET wonBy=? WHERE pid=?";
			int wonBy = getMaxBidAmount(pid);
			conn = DBConnectionUtil.getDBConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, wonBy);
			preparedStatement.setInt(2, pid);
			result = preparedStatement.executeUpdate();
		} catch (SQLException | ClassNotFoundException | NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		/**
		 * close the prepared statement and database connection at the end
		 */

		finally {
			try {
				if (preparedstatement != null) {
					preparedstatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return result;
	}
	/**
	 * In this method we will return products which is end
	 */
	@Override
	public ArrayList<ProductModel> getAuctionEndProducts(int sid) {
		String query = "SELECT * FROM product WHERE sellerID=? AND status LIKE 'auction' ORDER BY addeddate ASC";

		ArrayList<ProductModel> product = new ArrayList<ProductModel>();
		try {
			conn = DBConnectionUtil.getDBConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, sid);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				ProductModel pro = new ProductModel();
				pro.setPid(resultSet.getInt("pid"));
				pro.setPname(resultSet.getString("pname"));
				pro.setCategory(resultSet.getString("category"));
				pro.setAddeddate(resultSet.getString("addeddate"));
				pro.setBidPeriod_days(resultSet.getInt("bidPeriod_days"));
				pro.setPcondition(resultSet.getString("pcondition"));
				pro.setLastBidPrice(resultSet.getFloat("lastBidPrice"));
				pro.setSellerId(resultSet.getInt("sellerId"));
				pro.setDescription(resultSet.getString("description"));
				pro.setBrand(resultSet.getString("brand"));
				pro.setModel(resultSet.getString("model"));
				pro.setStatus(resultSet.getString("status"));
				pro.setAddedTime(resultSet.getString("addedTime"));
				pro.setWinner(resultSet.getInt("wonBy"));
				pro.setEndDate();
				if(pro.dateDifference()<=0) {
					product.add(pro);
				}
				
				
			}
		}catch (SQLException | ClassNotFoundException | NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		/**
		 * close the prepared statement and database connection at the end
		 */

		finally {
			try {
				if (preparedstatement != null) {
					preparedstatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return product;
	}
	/**
	 * int this method return all ongoing auctions
	 */
	@Override
	public ArrayList<ProductModel> OngoingAuctionEndProducts(int sid) {
		String query = "SELECT * FROM product WHERE sellerID=? AND status LIKE 'auction' ORDER BY addeddate DESC";

		ArrayList<ProductModel> product = new ArrayList<ProductModel>();
		try {
			conn = DBConnectionUtil.getDBConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, sid);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				ProductModel pro = new ProductModel();
				pro.setPid(resultSet.getInt("pid"));
				pro.setPname(resultSet.getString("pname"));
				pro.setCategory(resultSet.getString("category"));
				pro.setAddeddate(resultSet.getString("addeddate"));
				pro.setBidPeriod_days(resultSet.getInt("bidPeriod_days"));
				pro.setPcondition(resultSet.getString("pcondition"));
				pro.setLastBidPrice(resultSet.getFloat("lastBidPrice"));
				pro.setSellerId(resultSet.getInt("sellerId"));
				pro.setDescription(resultSet.getString("description"));
				pro.setBrand(resultSet.getString("brand"));
				pro.setModel(resultSet.getString("model"));
				pro.setStatus(resultSet.getString("status"));
				pro.setAddedTime(resultSet.getString("addedTime"));
				pro.setWinner(resultSet.getInt("wonBy"));
				pro.setEndDate();
				if(pro.dateDifference()>0) {
					product.add(pro);
				}
			}
		}catch (SQLException | ClassNotFoundException | NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		/**
		 * close the prepared statement and database connection at the end
		 */

		finally {
			try {
				if (preparedstatement != null) {
					preparedstatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return product;
	}
	
	/**
	 *Make payment  
	 **/
	@Override
	public int makePayment(int pid,int cid,double price) {
		int result =0;
		try {
			String query = "INSERT INTO myorders(pid, cid, price) VALUES (?,?,?)";
			conn = DBConnectionUtil.getDBConnection();
			PreparedStatement preparedStatement=conn.prepareStatement(query);
			preparedStatement.setInt(1, pid);
			preparedStatement.setInt(2, cid);
			preparedStatement.setDouble(3, price);
			result = preparedStatement.executeUpdate();
		}catch (SQLException | ClassNotFoundException | NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		/**
		 * close the prepared statement and database connection at the end
		 */

		finally {
			try {
				if (preparedstatement != null) {
					preparedstatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return result;
	}
	/**
	 * show customer's orders
	 */
	@Override
	public List<Integer> myOrders(int cid) {
		List<Integer> myOrders = new ArrayList<>();
		try {
			String query = "SELECT pid FROM myorders WHERE cid=? ORDER by orderId DESC";
			conn = DBConnectionUtil.getDBConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, cid);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				myOrders.add(resultSet.getInt("pid"));
			}
		}catch (SQLException | ClassNotFoundException | NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		/**
		 * close the prepared statement and database connection at the end
		 */

		finally {
			try {
				if (preparedstatement != null) {
					preparedstatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return myOrders;
	}
	/**
	 * product add by seller
	 */
	@Override
	public int addProduct(ProductModel product) {
		int result = 0;
		String query="INSERT INTO product(pname, category, addeddate, bidPeriod_days, pcondition, lastBidPrice, sellerId, description, brand, model,addedTime,wonBy) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,0)";
		try {
			conn = DBConnectionUtil.getDBConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, product.getPname());
			preparedStatement.setString(2, product.getCategory());
			preparedStatement.setString(3, product.getAddeddate());
			preparedStatement.setInt (4, product.getBidPeriod_days());
			preparedStatement.setString(5, product.getPcondition());
			preparedStatement.setFloat(6, product.getLastBidPrice());
			preparedStatement.setInt(7, product.getSellerId());
			preparedStatement.setString(8, product.getDescription());
			preparedStatement.setString(9, product.getBrand());
			preparedStatement.setString(10, product.getModel());
			preparedStatement.setString(11, product.getAddedTime());
			result = preparedStatement.executeUpdate();
		}catch (SQLException | ClassNotFoundException | NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		/**
		 * close the prepared statement and database connection at the end
		 */

		finally {
			try {
				if (preparedstatement != null) {
					preparedstatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		
		return result;
	}
	
	/**
	 * delete product by admin in this  system no delete only it mark as suspend
	 */
	@Override
	public int removeProductByAdmin(int pid) {
		int result = 0;
		try {
			String query = "UPDATE product SET status='suspend' WHERE pid=?";
			conn = DBConnectionUtil.getDBConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, pid);
			result = preparedStatement.executeUpdate();
		} catch (SQLException | ClassNotFoundException | NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		/**
		 * close the prepared statement and database connection at the end
		 */

		finally {
			try {
				if (preparedstatement != null) {
					preparedstatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return result;
	}
	/**
	 * show product which is not bought by customer and already he is won
	 */
	@Override
	public ArrayList<ProductModel> notBoughtyetProductBySellerId(int sid){
		String query = "SELECT p.pid,p.pname,p.category,p.addeddate,p.addedTime,p.bidPeriod_days,.p.lastBidPrice,p.wonBy,p.sellerId,m.orderId FROM product p LEFT OUTER JOIN myorders m ON p.pid=m.pid WHERE p.sellerId=? AND p.wonBy!=0 AND m.orderId IS NULL ORDER BY p.pid DESC";
		ArrayList<ProductModel> product = new ArrayList<ProductModel>();
		try {
			conn = DBConnectionUtil.getDBConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, sid);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				ProductModel pro = new ProductModel();
				pro.setPid(resultSet.getInt("pid"));
				pro.setPname(resultSet.getString("pname"));
				pro.setCategory(resultSet.getString("category"));
				pro.setAddeddate(resultSet.getString("addeddate"));
				pro.setBidPeriod_days(resultSet.getInt("bidPeriod_days"));
				pro.setLastBidPrice(resultSet.getFloat("lastBidPrice"));
				pro.setSellerId(resultSet.getInt("sellerId"));
				pro.setAddedTime(resultSet.getString("addedTime"));
				pro.setWinner(resultSet.getInt("wonBy"));
				pro.setEndDate();
				product.add(pro);
			}
		}catch (SQLException | ClassNotFoundException | NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		/**
		 * close the prepared statement and database connection at the end
		 */

		finally {
			try {
				if (preparedstatement != null) {
					preparedstatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return product;
	}
	/**
	 * return all products sold by seller
	 */
	@Override
	public ArrayList<ProductModel> soldProductBySellerID(int sid){
		String query = "SELECT p.pid,p.pname,p.category,p.addeddate,p.addedTime,p.bidPeriod_days,.p.lastBidPrice,p.wonBy,p.sellerId,m.orderId FROM product p LEFT OUTER JOIN myorders m ON p.pid=m.pid WHERE p.sellerId=? AND p.wonBy!=0 AND m.orderId IS NOT NULL ORDER BY p.pid DESC";
		ArrayList<ProductModel> product = new ArrayList<ProductModel>();
		try {
			conn = DBConnectionUtil.getDBConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, sid);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				ProductModel pro = new ProductModel();
				pro.setPid(resultSet.getInt("pid"));
				pro.setPname(resultSet.getString("pname"));
				pro.setCategory(resultSet.getString("category"));
				pro.setAddeddate(resultSet.getString("addeddate"));
				pro.setBidPeriod_days(resultSet.getInt("bidPeriod_days"));
				pro.setLastBidPrice(resultSet.getFloat("lastBidPrice"));
				pro.setSellerId(resultSet.getInt("sellerId"));
				pro.setAddedTime(resultSet.getString("addedTime"));
				pro.setWinner(resultSet.getInt("wonBy"));
				pro.setEndDate();
				product.add(pro);
			}
		}catch (SQLException | ClassNotFoundException | NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		/**
		 * close the prepared statement and database connection at the end
		 */

		finally {
			try {
				if (preparedstatement != null) {
					preparedstatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return product;
	}
	/**
	 * show bid count by product id 
	 */
	@Override
	public int showBidCount(int pid) {
		int no = 0;
		try {
			String query="SELECT COUNT(pid) AS bids FROM biddetails WHERE pid=?";
			conn = DBConnectionUtil.getDBConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, pid);
			ResultSet result = preparedStatement.executeQuery();
			if(result.next()) {
				no = result.getInt("bids");
			}
		}catch (SQLException | ClassNotFoundException | NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		/**
		 * close the prepared statement and database connection at the end
		 */

		finally {
			try {
				if (preparedstatement != null) {
					preparedstatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return no;
	}
}