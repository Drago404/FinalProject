package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.db.DBConnection;
import com.model.Order;
import com.model.User;

@Component
public class OrderDAO implements IOrderDAO{

	private static final String INSERT_ADDRESS = "insert into address(first_name, last_name, phone, city, post_code,"
			+ "street, street_number, block, enterance, floor, appartment, description) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
	private static final String INSERT_ORDER = "insert INTO `order`(users_id, address_id) values (?, ?)"; 
	private static final String INSERT_ITEMS_HAS_ORDER = "insert into items_has_order(items_id, order_id, quanitity) values (?, ?, ?)"; 
	private Connection conn;
	
	private OrderDAO() throws SQLException{
		this.conn = DBConnection.getInstance().getConnection();
	}
	
	@Override
	public void makeOrder(Order order) {
		try {
			PreparedStatement stmt = conn.prepareStatement(INSERT_ADDRESS,Statement.RETURN_GENERATED_KEYS);
			long addressId = 0;
			int orderId = 0;
			//check if adress exists
			
			stmt.setString(1, order.getOrdererFirstName());
			stmt.setString(2, order.getOrdererLastName());
			stmt.setString(3, order.getOrdererPhone());
			stmt.setString(4, order.getOrdererCity());
			stmt.setString(5, order.getOrdererPostCode());
			stmt.setString(6, order.getOrdererStreet());
			setInteger(7, order.getOrdererStreetNumber(), stmt);
		
			setString(8, order.getOrdererBlock(), stmt);
			setString(9, order.getOrdererEnterance(), stmt);
			
			setInteger(10, order.getOrdererFloor(), stmt);
			
			setString(11, order.getOrdererAppartment(), stmt);
			setString(12, order.getDescription(), stmt);

			
			synchronized (this) {
				stmt.executeUpdate();
			}
			ResultSet rs = stmt.getGeneratedKeys();
			
			/// try get address throw address exception
			if(rs.next()) {
				addressId= rs.getLong(1);
				order.setId(addressId);;
			}
			stmt.close();
			
			
			PreparedStatement stmt2 = conn.prepareStatement(INSERT_ORDER);
			stmt2.setInt(1, order.getUserId());
			stmt2.setLong(2, addressId);
			
			synchronized (this) {
				stmt2.executeUpdate();
			}
			ResultSet rs2 = stmt2.getGeneratedKeys();
			if(rs2.next()) {
				orderId= rs2.getInt(1);
			}
			stmt2.close();
			
			
			
			Map<Integer,Integer> items = order.getItems();
			for(Integer itemId : items.keySet()){
				int quantity = items.get(itemId).intValue();
				PreparedStatement stmt3 = conn.prepareStatement(INSERT_ITEMS_HAS_ORDER);
				stmt3.setInt(1, itemId.intValue());
				stmt3.setInt(2, orderId);
				stmt3.setInt(3, quantity);
				
				synchronized (this) {
					stmt3.executeUpdate();
				}
				stmt3.close();
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	private void setInteger(int stmtIndex,Integer integer, PreparedStatement stmt) throws SQLException {
		if(integer == null ){
			stmt.setNull(stmtIndex, java.sql.Types.INTEGER);
		}else{
			stmt.setInt(stmtIndex, integer);
		}
	}
	
	private void setString(int stmtIndex,String string, PreparedStatement stmt) throws SQLException {
		if(string.length() == 0){
			stmt.setString(stmtIndex, null);
		}else{
			stmt.setString(stmtIndex, string);
		}
	}
	

}
