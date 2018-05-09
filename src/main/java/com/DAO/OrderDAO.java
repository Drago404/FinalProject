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
public class OrderDAO implements IOrderDAO {

	private static final String INSERT_ADDRESS = "insert into address(first_name, last_name, phone, city, post_code,"
			+ "street, street_number, block, enterance, floor, appartment, description) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_ORDER = "insert INTO `order`(users_id, address_id) values (?, ?)";
	private static final String INSERT_ITEMS_HAS_ORDER = "insert into items_has_order(items_id, order_id, quanitity) values (?, ?, ?)";
	private static final String FIND_ADDRESS = "SELECT * from address where first_name = ? AND last_name = ? AND phone = ? AND city =? AND post_code = ? AND street= ?";
	private Connection conn;

	private OrderDAO() throws SQLException {
		this.conn = DBConnection.getInstance().getConnection();
	}

	@Override
	public void makeOrder(Order order) {
		try {

			long addressId = 0;
			int orderId = 0;
			// check if adress exists

			PreparedStatement stmt = conn.prepareStatement(FIND_ADDRESS);

			stmt.setString(1, order.getOrdererFirstName());
			stmt.setString(2, order.getOrdererLastName());
			stmt.setString(3, order.getOrdererPhone());
			stmt.setString(4, order.getOrdererCity());
			stmt.setString(5, order.getOrdererPostCode());
			stmt.setString(6, order.getOrdererStreet());

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				addressId = rs.getInt("id");

			} else {
				PreparedStatement stmt2 = conn.prepareStatement(INSERT_ADDRESS, Statement.RETURN_GENERATED_KEYS);
				stmt2.setString(1, order.getOrdererFirstName());
				stmt2.setString(2, order.getOrdererLastName());
				stmt2.setString(3, order.getOrdererPhone());
				stmt2.setString(4, order.getOrdererCity());
				stmt2.setString(5, order.getOrdererPostCode());
				stmt2.setString(6, order.getOrdererStreet());
				setInteger(7, order.getOrdererStreetNumber(), stmt2);
				setString(8, order.getOrdererBlock(), stmt2);
				setString(9, order.getOrdererEnterance(), stmt2);
				setInteger(10, order.getOrdererFloor(), stmt2);
				setString(11, order.getOrdererAppartment(), stmt2);
				setString(12, order.getDescription(), stmt2);

				synchronized (this) {
					stmt2.executeUpdate();
				}
				ResultSet rs2 = stmt2.getGeneratedKeys();

				/// try get address throw address exception
				if (rs2.next()) {
					addressId = rs2.getLong(1);
				}
				stmt2.close();
			}
			PreparedStatement stmt3 = conn.prepareStatement(INSERT_ORDER);
			stmt3.setInt(1, order.getUserId());
			stmt3.setLong(2, addressId);

			synchronized (this) {
				stmt3.executeUpdate();
			}
			ResultSet rs3 = stmt3.getGeneratedKeys();
			if (rs3.next()) {
				orderId = rs3.getInt(1);
			}
			stmt3.close();

			Map<Integer, Integer> items = order.getItems();
			for (Integer itemId : items.keySet()) {
				int quantity = items.get(itemId).intValue();
				PreparedStatement stmt4 = conn.prepareStatement(INSERT_ITEMS_HAS_ORDER);
				stmt4.setInt(1, itemId.intValue());
				stmt4.setInt(2, orderId);
				stmt4.setInt(3, quantity);

				synchronized (this) {
					stmt4.executeUpdate();
				}
				stmt4.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void setInteger(int stmtIndex, Integer integer, PreparedStatement stmt) throws SQLException {
		if (integer == null) {
			stmt.setNull(stmtIndex, java.sql.Types.INTEGER);
		} else {
			stmt.setInt(stmtIndex, integer);
		}
	}

	private void setString(int stmtIndex, String string, PreparedStatement stmt) throws SQLException {
		if (string.length() == 0) {
			stmt.setString(stmtIndex, null);
		} else {
			stmt.setString(stmtIndex, string);
		}
	}

}
