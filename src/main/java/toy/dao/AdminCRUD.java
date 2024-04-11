package toy.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

import toy.dto.Admin;

public class AdminCRUD {
	
	public Connection createTableAdmin() throws SQLException {
		DriverManager.registerDriver(new Driver());
		Connection conn = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/toymanagement?createDatabaseIfNotExist=true", "root", "root");
		Statement s = conn.createStatement();
		String query ="CREATE TABLE IF NOT EXISTS ADMIN (ID VARCHAR(15) PRIMARY KEY, NAME VARCHAR(40) NOT NULL, SHOPNAME VARCHAR(30) NOT NULL, EMAIL VARCHAR(45) UNIQUE NOT NULL, PHONE BIGINT(10) UNIQUE NOT NULL, ADDRESS VARCHAR(140) NOT NULL, PASSWORD VARCHAR(40) NOT NULL)";
		s.execute(query);
		s.close();
		return conn;
	}
	
	
	public int insertAdminData(Admin admin) throws SQLException {
		Connection conn=createTableAdmin();
		PreparedStatement ps=conn.prepareStatement("INSERT INTO ADMIN VALUES(?, ?, ?, ?, ?, ?, ?)");
		ps.setString(1, admin.getId());
		ps.setString(2, admin.getName());
		ps.setString(3, admin.getShopName());
		ps.setString(4, admin.getEmail());
		ps.setLong(5, admin.getPhone());
		ps.setString(6, admin.getAddress());
		ps.setString(7, admin.getPassword());
		int data=ps.executeUpdate();
		ps.close();
		conn.close();
		return data;
	}
	
	
	public Admin adminLogin(String email) throws SQLException {
		Admin admin = null;
		Connection conn = createTableAdmin();
		PreparedStatement s = conn.prepareStatement("SELECT * FROM ADMIN WHERE EMAIL=? ");
		s.setString(1, email);
		ResultSet res = s.executeQuery();
		if (res.next()) {
			admin = new Admin(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getLong(5), res.getString(6), res.getString(7));
		}
		s.close();
		conn.close();
		return admin;
	}
	
	
	public void fetchAdminProfile(Admin admin) {
		try {
			Connection conn = createTableAdmin();
			PreparedStatement s = conn.prepareStatement("SELECT * FROM ADMIN WHERE ID=? ");
			s.setString(1, admin.getId());
			ResultSet res = s.executeQuery();
			System.out.println("ID\t    NAME\tSHOPNAME\t    EMAIL\t      PHONE\t    ADDRESS\t\t PASSWORD");
			System.out.println("==\t    ====\t========\t    =====\t      =====\t    =======\t\t ========");
			if (res.next()) {
				System.out.println(res.getString(1)+"   "+res.getString(2)+"   "+res.getString(3)+"   "+res.getString(4)+"   "+res.getLong(5)+"   "+res.getString(6)+"   "+res.getString(7));
			}
			s.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("\t\t\t\t\tRecord not Found...!");
		}
		System.out.println();
	}
	
	
	public int updateAdminData(Admin admin, int i, Object o) throws SQLException {
		Connection conn = createTableAdmin();
		PreparedStatement ps = null;
		int data = 0;
		if (i == 1) {
			String name = (String) o;
			ps = conn.prepareStatement("UPDATE ADMIN SET NAME=? WHERE ID=?");
			ps.setString(1, name);
			ps.setString(2, admin.getId());
			data = ps.executeUpdate();
			ps.close();
			conn.close();
			return data;
		} else if (i == 2) {
			String  sn= (String) o;
			ps = conn.prepareStatement("UPDATE ADMIN SET SHOPNAME=? WHERE ID=?");
			ps.setString(1, sn);
			ps.setString(2, admin.getId());
			data = ps.executeUpdate();
			ps.close();
			conn.close();
			return data;
		}else if (i == 3) {
			String e = (String) o;
			ps = conn.prepareStatement("UPDATE ADMIN SET EMAIL=? WHERE ID=?");
			ps.setString(1, e);
			ps.setString(2, admin.getId());
			data = ps.executeUpdate();
			ps.close();
			conn.close();
			return data;
		}  
		else if (i == 4) {
			long ph = (long) o;
			ps = conn.prepareStatement("UPDATE ADMIN SET PHONE=? WHERE ID=?");
			ps.setLong(1, ph);
			ps.setString(2, admin.getId());
			data = ps.executeUpdate();
			ps.close();
			conn.close();
			return data;
		} else if (i == 5) {
			String ad = (String) o;
			ps = conn.prepareStatement("UPDATE ADMIN SET ADDRESS=? WHERE ID=?");
			ps.setString(1, ad);
			ps.setString(2, admin.getId());
			data = ps.executeUpdate();
			ps.close();
			conn.close();
			return data;
		} else if (i == 6) {
			String pwd = (String) o;
			ps = conn.prepareStatement("UPDATE ADMIN SET PASSWORD=? WHERE ID=?");
			ps.setString(1, pwd);
			ps.setString(2, admin.getId());
			data = ps.executeUpdate();
			ps.close();
			conn.close();
			return data;
		}
		return 0;
	}

	public int deleteAdminData(String id) throws SQLException {
		Connection conn = createTableAdmin();
		PreparedStatement ps = conn.prepareStatement("DELETE FROM ADMIN WHERE ID=?");
		ps.setString(1, id);
		int data = ps.executeUpdate();		
		ps.close();
		conn.close();
		return data;
	}
}
