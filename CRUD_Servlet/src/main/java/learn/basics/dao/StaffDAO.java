package learn.basics.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import learn.basics.beans.Staff;
import learn.basics.utils.DBConnection;

public class StaffDAO {
	//DAO - Data access object
	//It does business logic operations as Create - Insert - Update - Delete data
	//These operation has named CRUD operation
	public int creatStaff(Staff staff) {
		
		Connection conn = DBConnection.getH2Connection();
		String insSql = "insert into staff (id, name, language) values (?,?,?)";
		int insResult = -1;
		try {
			PreparedStatement preStatement = conn.prepareStatement(insSql);
			preStatement.setInt(1, staff.getId());
			preStatement.setString(2, staff.getName());
			preStatement.setString(3, staff.getLanguage());
			insResult = preStatement.executeUpdate();
			preStatement.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return insResult;
	}
	
	public Staff getStaffById(int staffId) {
		Connection conn = DBConnection.getH2Connection();
		Staff staff = null;
		String selSql = "select * from staff where id=?";
		try {
			PreparedStatement preStatment = conn.prepareStatement(selSql);
			preStatment.setInt(1, staffId);
			ResultSet resSet = preStatment.executeQuery();
			while(resSet.next()) {
				staff = new Staff(staffId, resSet.getString("name"), resSet.getString("language"));
				break;
			}
			preStatment.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return staff;
	}
	
	public List<Staff> getAllStaff() {
		Connection conn = DBConnection.getH2Connection();
		List<Staff> staffs = new ArrayList<>();
		String selSql = "select * from staff";
		try {
			Statement statement = conn.createStatement(); //Read only statement result
			ResultSet resSet = statement.executeQuery(selSql);
			while(resSet.next()) {
				staffs.add(new Staff(resSet.getInt("id"), resSet.getString("name"), resSet.getString("language")));
			}
			statement.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return staffs;
	}
	
	public boolean updateStaff(Staff staff) {
		Connection conn = DBConnection.getH2Connection();
		String updSql = "update staff set name=?, language=? where id=?";
		int updResult = -1;
		try {
			PreparedStatement preStatement = conn.prepareStatement(updSql);
			preStatement.setString(1, staff.getName());
			preStatement.setString(2, staff.getLanguage());
			preStatement.setInt(3, staff.getId());
			updResult = preStatement.executeUpdate();
			preStatement.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updResult >= 0;
	}
	
	public int deleteStaff(int staffId) {
		int delResult = -1;
		Connection conn = DBConnection.getH2Connection();
		String delSql = "delete from staff where id=?";
		try {
			PreparedStatement preStatement = conn.prepareStatement(delSql);
			preStatement.setInt(1, staffId);
			delResult = preStatement.executeUpdate();
			preStatement.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return delResult;
	}
}
