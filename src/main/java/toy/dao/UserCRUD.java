package toy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

import toy.dto.User;

public class UserCRUD {
	
	public Connection createTableUser() throws SQLException {
		DriverManager.registerDriver(new Driver());
		Connection conn = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/toymanagement?createDatabaseIfNotExist=true", "root", "root");
		Statement s = conn.createStatement();
		String query ="CREATE TABLE IF NOT EXISTS USER (ID INT PRIMARY KEY, NAME VARCHAR(40) NOT NULL, EMAIL VARCHAR(45) UNIQUE NOT NULL, PHONE BIGINT(10) UNIQUE NOT NULL, AGE TINYINT(2) NOT NULL, ADDRESS VARCHAR(140) NOT NULL, WALLET DECIMAL(6,2), PASSWORD VARCHAR(40) NOT NULL)";
		s.execute(query);
		s.close();
		return conn;
	}
	
	
	public User userLogin(String email) throws SQLException {
		User user = null;
		Connection conn = createTableUser();
		PreparedStatement s = conn.prepareStatement("SELECT * FROM USER WHERE EMAIL=? ");
		s.setString(1, email);
		ResultSet res = s.executeQuery();
		if (res.next()) {
			user = new User(res.getInt(1), res.getString(2), res.getString(3), res.getLong(4), res.getInt(5), res.getString(6), res.getDouble(7), res.getString(8));
		}
		s.close();
		conn.close();
		return user;
	}
	
	
	public int insertUserData(User user) throws SQLException {
		Connection conn=createTableUser();
		PreparedStatement ps=conn.prepareStatement("INSERT INTO USER VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
		ps.setInt(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getEmail());
		ps.setLong(4, user.getPhone());
		ps.setInt(5, user.getAge());
		ps.setString(6, user.getAddress());
		ps.setDouble(7, user.getWallet());
		ps.setString(8, user.getPassword());
		int data=ps.executeUpdate();
		ps.close();
		conn.close();
		return data;
	}
	
	public void fetchUserProfile(User user) {
		try {
			Connection conn = createTableUser();
			PreparedStatement s = conn.prepareStatement("SELECT * FROM USER WHERE ID=? ");
			s.setInt(1, user.getId());
			ResultSet res = s.executeQuery();
			System.out.println("ID\tNAME\t\tEMAIL\t\t    PHONE      AGE      ADDRESS\t\t  WALLET       PASSWORD");
			System.out.println("==\t====\t\t=====\t\t    =====      ===      =======\t\t  ======       ========");
			if (res.next()) {
				System.out.println(res.getString(1)+"   "+res.getString(2)+"   "+res.getString(3)+"   "+res.getString(4)+"   "+res.getLong(5)+"   "+res.getString(6)+"   "+res.getString(7)+"       "+res.getString(8));
			}
			s.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("\t\t\t\t\tRecord not Found...!");
		}
		System.out.println();
	}
	
	
	public int updateUserData(User user, int i, Object o) throws SQLException {
		Connection conn = createTableUser();
		PreparedStatement ps = null;
		int data = 0;
		if (i == 1) {
			String name = (String) o;
			ps = conn.prepareStatement("UPDATE USER SET NAME=? WHERE ID=?");
			ps.setString(1, name);
			ps.setInt(2, user.getId());
			data = ps.executeUpdate();
			ps.close();
			conn.close();
			return data;
		} else if (i == 2) {
			String e = (String) o;
			ps = conn.prepareStatement("UPDATE USER SET EMAIL=? WHERE ID=?");
			ps.setString(1, e);
			ps.setInt(2, user.getId());
			data = ps.executeUpdate();
			ps.close();
			conn.close();
			return data;
		}  
		else if (i == 3) {
			long ph = (long) o;
			ps = conn.prepareStatement("UPDATE USER SET PHONE=? WHERE ID=?");
			ps.setLong(1, ph);
			ps.setInt(2, user.getId());
			data = ps.executeUpdate();
			ps.close();
			conn.close();
			return data;
		}else if (i == 4) {
			int age = (int) o;
			ps = conn.prepareStatement("UPDATE USER SET PHONE=? WHERE ID=?");
			ps.setLong(1, age);
			ps.setInt(2, user.getId());
			data = ps.executeUpdate();
			ps.close();
			conn.close();
			return data;
		}else if (i == 5) {
			String ad = (String) o;
			ps = conn.prepareStatement("UPDATE USER SET ADDRESS=? WHERE ID=?");
			ps.setString(1, ad);
			ps.setInt(2, user.getId());
			data = ps.executeUpdate();
			ps.close();
			conn.close();
			return data;
		} else if (i == 6) {
			String pwd = (String) o;
			ps = conn.prepareStatement("UPDATE USER SET PASSWORD=? WHERE ID=?");
			ps.setString(1, pwd);
			ps.setInt(2, user.getId());
			data = ps.executeUpdate();
			ps.close();
			conn.close();
			return data;
		}
		return 0;
	}

	public int deleteUserData(int id) throws SQLException {
		Connection conn = createTableUser();
		PreparedStatement ps = conn.prepareStatement("DELETE FROM USER WHERE ID=?");
		ps.setInt(1, id);
		int data = ps.executeUpdate();		
		ps.close();
		conn.close();
		return data;
	}
	
	public User addWalletMoney(int id, double wallet) throws SQLException {
		User user=null;
		Connection conn = createTableUser();
		PreparedStatement s=conn.prepareStatement("UPDATE USER SET WALLET=? WHERE ID=?");
		s.setDouble(1, wallet);
		s.setInt(2, id);
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM USER WHERE ID=? ");
		ps.setInt(1, id);
		s.execute();
		ResultSet res = ps.executeQuery();
		if (res.next()) {
			user = new User(res.getInt(1), res.getString(2), res.getString(3), res.getLong(4), res.getInt(5), res.getString(6), res.getDouble(7), res.getString(8));
		}
		s.close();
		conn.close();
		return user;
	}
	
	public double updateWallet(int id, double wallet) throws SQLException {
		Connection conn = createTableUser();
		PreparedStatement s=conn.prepareStatement("UPDATE USER SET WALLET=? WHERE ID=?");
		s.setDouble(1, wallet);
		s.setInt(2, id);
		s.execute();
		PreparedStatement ps1=conn.prepareStatement("SELECT WALLET FROM USER WHERE ID=?");
		ps1.setInt(1, id);
		ResultSet r=ps1.executeQuery();
		double bal=0.0;
		if(r.next())
			bal=r.getDouble(1);
		ps1.execute();
		ps1.close();
		s.close();
		conn.close();
		return bal;
	}
}
