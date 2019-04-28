package DAO;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import entity.CustBill;

public class CustBillDAO {
	
	static{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public static Connection getconnection() throws SQLException{
		return DriverManager.getConnection("jdbc:oracle:thin:@188.131.140.28:1521:orcl","super", 
				            "594SHENG");
	}
	public static ArrayList<CustBill> custbill = new ArrayList<CustBill>();//数据表
	
	//插入操作
	public static void insert(String ID,String Name,String Phone,String Address,String Regdate) throws SQLException{
		String sql = "insert into custbill values(?,?,?,?,?)";
		Connection c1 = getconnection();
		PreparedStatement getinsql = c1.prepareStatement(sql);
		getinsql.setString(1,ID);
		getinsql.setString(2,Name);
		getinsql.setString(3,Phone);
		getinsql.setString(4,Address);
		getinsql.setString(5,Regdate);
		getinsql.execute();
	}
	
	//删除操作
	public static void delete(String ID) throws SQLException{
		String sql = "delete from custbill where ID = ?";
		Connection c1 = getconnection();
		PreparedStatement getinsql = c1.prepareStatement(sql);
		getinsql.setString(1, ID);
		getinsql.execute();
	}
	
	//更新操作
	public static void update(String ID,String Name,String Phone,String Address,String Regdate,String ID2) throws SQLException{
		String sql = "update custbill set ID = ?,Name = ?,Phone = ?,Address = ?,Regdate = ? where ID = ?";
		Connection c1 = getconnection();
		PreparedStatement getinsql = c1.prepareStatement(sql);
		getinsql.setString(1,ID);
		getinsql.setString(2,Name);	
		getinsql.setString(3,Phone);	
		getinsql.setString(4,Address);	
		getinsql.setString(5,Regdate);	
		getinsql.setString(6,ID2);	
		getinsql.execute();
	}
	
	//查询操作
	public static void select(String ID) throws SQLException{
		String sql = "select * from custbill where ID = "+"'"+ID+"'";
		Connection c1 = getconnection();
		PreparedStatement getinsql = c1.prepareStatement(sql);
		ResultSet rs = getinsql.executeQuery(sql);
		if(rs.next()){
			System.out.println(rs.getString("ID")+" "+rs.getString("Name")+" "+rs.getString("Phone")+" "+rs.getString("Address")+" "+rs.getString("Regdate"));
		}	
	}
	//查找全表
	public static void select_all() throws SQLException{
		String sql = "select * from custbill";
		Connection c1 = getconnection();
		PreparedStatement getinsql = c1.prepareStatement(sql);
		ResultSet rs = getinsql.executeQuery(sql);
		while(rs.next()){
			CustBill cust = new CustBill();
			cust.setID(rs.getString("ID"));
			cust.setName(rs.getString("Name"));
			cust.setPhone(rs.getString("Phone"));
			cust.setAddress(rs.getString("Address"));
			cust.setRegdate(rs.getString("Regdate"));
			custbill.add(cust);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		try {
//			insert("11","12","13","14","15");
//			insert("6","7","8","9","10");
//			update("11","12","13","14","15","1");
//			delete("11");
//			select("6");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

}
