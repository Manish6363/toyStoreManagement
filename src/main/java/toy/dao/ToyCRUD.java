package toy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Driver;

import toy.dto.Toy;

public class ToyCRUD {

	public Connection createTableToy() throws SQLException {
		DriverManager.registerDriver(new Driver());
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/toymanagement?createDatabaseIfNotExist=true", "root", "root");
		Statement s = conn.createStatement();
		String query = "CREATE TABLE IF NOT EXISTS TOY ( ID INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(20) NOT NULL, COLOR VARCHAR(20) NOT NULL, PRICE DECIMAL(6, 2) NOT NULL, QUANTITY TINYINT, SHOPID VARCHAR(20) NOT NULL, FOREIGN KEY (SHOPID) REFERENCES ADMIN(ID) ON DELETE CASCADE)";
		s.execute(query);
		s.close();
		return conn;
	}

	public int insertToyData(ArrayList<Toy> al, String id) throws SQLException {
		Connection conn = createTableToy();
		PreparedStatement ps = conn
				.prepareStatement("INSERT INTO TOY (NAME, COLOR, PRICE, QUANTITY, SHOPID) VALUES (?, ?, ?, ?, ?)");
		for(Toy toy: al) {
			ps.setString(1, toy.gettName());
			ps.setString(2, toy.getColor());
			ps.setDouble(3, toy.getPrice());
			ps.setInt(4, toy.getQuantity());
			ps.setString(5, id);
			ps.addBatch();
		}
		int[] len=ps.executeBatch();
		int data = len.length;
		ps.close();
		conn.close();
		return data;
	}

	public void fetchToy(String id) {
		try {
			Connection conn = createTableToy();
			PreparedStatement s = conn.prepareStatement("SELECT * FROM TOY WHERE SHOPID=? ");
			s.setString(1, id);
			ResultSet res = s.executeQuery();
			System.out.println("ID\tNAME\t COLOUR\tPRICE\tQUANTITY\tSHOPID");
			System.out.println("==\t====\t ======\t=====\t========\t======");
			while (res.next()) {
				System.out.println(res.getInt(1) + "\t " + res.getString(2) + "\t " + res.getString(3) + "\t"
						+ res.getDouble(4) + "\t" + res.getInt(5) + "\t\t" + res.getString(6));
			}
			s.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("\t\t\t\tRecord not Found...!");
		}
		System.out.println();
	}

	public void fetchAllToy() {
		try {
			Connection conn = createTableToy();
			PreparedStatement s = conn.prepareStatement("SELECT ID, NAME, COLOR, PRICE, SHOPID FROM TOY ORDER BY NAME");
			ResultSet res = s.executeQuery();
			System.out.println("ID\tNAME\t COLOR\tPRICE\tSHOPID");
			System.out.println("==\t====\t =====\t=====\t======");
			while (res.next()) {
				System.out.println(res.getInt(1) + "\t " + res.getString(2) + "\t " + res.getString(3) + "\t"
						+ res.getDouble(4) + "\t" + res.getString(5));
			}
			s.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("\t\t\t\tRecord not Found...!");
		}
		System.out.println();
	}

	public int updateToyData(int id, int i, Object o) throws SQLException {
		Connection conn = createTableToy();
		PreparedStatement ps = null;
		int data = 0;
		if (i == 1) {
			String name = (String) o;
			ps = conn.prepareStatement("UPDATE TOY SET NAME=? WHERE ID=?");
			ps.setString(1, name);
			ps.setInt(2, id);
			data = ps.executeUpdate();
			ps.close();
			conn.close();
			return data;
		} else if (i == 2) {
			String col = (String) o;
			ps = conn.prepareStatement("UPDATE TOY SET COLOR=? WHERE ID=?");
			ps.setString(1, col);
			ps.setInt(2, id);
			data = ps.executeUpdate();
			ps.close();
			conn.close();
			return data;
		} else if (i == 3) {
			double pr = (double) o;
			ps = conn.prepareStatement("UPDATE TOY SET PRICE=? WHERE ID=?");
			ps.setDouble(1, pr);
			ps.setInt(2, id);
			data = ps.executeUpdate();
			ps.close();
			conn.close();
			return data;
		} else if (i == 4) {
			int quan = (int) o;
			PreparedStatement s = conn.prepareStatement("SELECT * FROM TOY WHERE ID=?");
			s.setInt(1, id);
			ResultSet res=s.executeQuery();
			int q=0;
			if(res.next()) {
				q=res.getInt(5);
			}
			quan+=q;
			ps = conn.prepareStatement("UPDATE TOY SET QUANTITY=? WHERE ID=?");
			ps.setInt(1, quan);
			ps.setInt(2, id);
			data = ps.executeUpdate();
			ps.close();
			conn.close();
			return data;
		}
		return 0;
	}

	public int deleteToyData(int id) throws SQLException {
		Connection conn = createTableToy();
		PreparedStatement ps = conn.prepareStatement("DELETE FROM TOY WHERE ID=?");
		ps.setInt(1, id);
		int data = ps.executeUpdate();
		ps.close();
		conn.close();
		return data;
	}

	public Toy purchageToys(int id, int quantity) throws SQLException {
		Toy toy = null;
		Connection conn = createTableToy();
		PreparedStatement ps = conn.prepareStatement("SELECT ID, NAME, COLOR, PRICE, QUANTITY FROM TOY WHERE ID=?");
		ps.setInt(1, id);
		ResultSet res = ps.executeQuery();
		if (res.next()) {
			if (res.getInt(5) >= quantity) {
				toy = new Toy(res.getInt(1), res.getString(2), res.getString(3), res.getDouble(4), quantity);
			} else {
				System.out.println(
						"Sorry! You have Given the Quantity is " + quantity + " but availabe is " + res.getInt(5));
				System.out.println();
			}
		}
		ps.close();
		conn.close();
		return toy;
	}

	public int updateToyQuantity(int id, int quantity) throws SQLException {
		int data=0;
		Connection conn = createTableToy();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM TOY WHERE ID=?");
		ps.setInt(1, id);
		ResultSet res = ps.executeQuery();
		if (res.next()) {
			int q = res.getInt(5) - quantity;
			PreparedStatement s = conn.prepareStatement("UPDATE TOY SET QUANTITY=? WHERE ID=?");
			s.setInt(1, q);
			s.setInt(2, id);
			data=s.executeUpdate();
			s.close();
		} 
		ps.close();
		conn.close();
		return data;
	}

}
