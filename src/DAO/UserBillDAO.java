package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.User;



public class UserBillDAO {
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
	public static ArrayList<User> userlist = new ArrayList<User>();//数据表
	
	//插入操作
	public static void insert(int id,String name,String password,String secure_code,String address,String phone) throws SQLException{
		String sql = "insert into user_web values(?,?,?,?,?,?)";
		Connection c1 = getconnection();
		PreparedStatement getinsql = c1.prepareStatement(sql);
		getinsql.setInt(1,id);
		getinsql.setString(2,name);
		getinsql.setString(3,password);
		getinsql.setString(4,secure_code);
		getinsql.setString(5,address);
		getinsql.setString(6,phone);
		getinsql.execute();
	}
	
	//删除操作
	public static void delete(int id) throws SQLException{
		String sql = "delete from user_web where id = ?";
		Connection c1 = getconnection();
		PreparedStatement getinsql = c1.prepareStatement(sql);
		getinsql.setInt(1, id);
		getinsql.execute();
	}
	
	//更新操作
	public static void update(int id,String name,String password,String secure_code,String address,String phone,int id2) throws SQLException{
		String sql = "update user_web set id = ?,name = ?,password = ?,code = ?,address = ?,phone = ? where id = ?";
		Connection c1 = getconnection();
		PreparedStatement getinsql = c1.prepareStatement(sql);
		getinsql.setInt(1,id);
		getinsql.setString(2,name);	
		getinsql.setString(3,password);	
		getinsql.setString(4,secure_code);	
		getinsql.setString(5,address);	
		getinsql.setString(6,phone);
		getinsql.setInt(7,id2);
		getinsql.execute();
	}
	
	//查询操作
	public static void select(int id) throws SQLException{
		String sql = "select * from user_web where ID = "+id;
		Connection c1 = getconnection();
		PreparedStatement getinsql = c1.prepareStatement(sql);
		ResultSet rs = getinsql.executeQuery(sql);
		if(rs.next()){
			System.out.println(rs.getInt("id")+" "+rs.getString("name")+" "+rs.getString("password")+" "+rs.getString("code")+" "+rs.getString("address")+" "+rs.getString("phone"));
		}	
	}
	public static String select_s(String username,String password) throws SQLException{
		String login = null ;
		String sql = "select account from rootbill where account ='"+username+"' and password = '"+password+"' ";
		Connection c1 = getconnection();
		 PreparedStatement ps = c1.prepareStatement(sql);
		 
//         ps.setString(1, username);
//         ps.setString(2, password);
		
		
		ResultSet rs = ps.executeQuery(sql);
		
		while(rs.next()){
			login=rs.getString(1);
			
		}	
		return login;
	}
	public static boolean select_s_(String username,String password) throws SQLException{
		boolean login = false ;
		String sql = "select * from rootbill where account ='"+username+"' and password = '"+password+"' ";
		Connection c1 = getconnection();
		 PreparedStatement ps = c1.prepareStatement(sql);
		 
//         ps.setString(1, username);
//         ps.setString(2, password);
		
		
		ResultSet rs = ps.executeQuery(sql);
		
		while(rs.next()){
			login=true;
			
		}	
		return login;
	}
	public static String select_system(String username) throws SQLException{
		String login = null ;
		String sql = "select account from rootbill where account ='"+username+"'";
		Connection c1 = getconnection();
		 PreparedStatement ps = c1.prepareStatement(sql);
		 
//         ps.setString(1, username);
//         ps.setString(2, password);
		
		
		ResultSet rs = ps.executeQuery(sql);
		
		while(rs.next()){
			login=rs.getString(1);
			
		}	
		return login;
	}
	//查找全表
	public static void select_all() throws SQLException{
		String sql = "select * from user_web";
		Connection c1 = getconnection();
		PreparedStatement getinsql = c1.prepareStatement(sql);
		ResultSet rs = getinsql.executeQuery(sql);
		while(rs.next()){
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			user.setSecure_code(rs.getString("code"));
			user.setAddress(rs.getString("address"));
			user.setPhone(rs.getString("phone"));
			userlist.add(user);
		}
	}
	
	public static void main(String[] args) {
		 //TODO Auto-generated method stub
		try {
//			insert(1,"2","3","4","5","6");
//			insert(7,"8","9","10","11","12");
//			insert(13,"14","15","16","17","18");
//			update(13,"14","15","16","17","18",1);
//			delete(13);
//			select(13);
//			select_all();
//			System.out.println(userlist.size());
			System.out.println(select_s("Tom","aaa"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
