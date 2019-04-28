package DAO;

import entity.StorageBill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StorageBillDAO {
 
	static {
		  try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public static  Connection getconnection() throws SQLException {
		return DriverManager.getConnection("jdbc:oracle:thin:@188.131.140.28:1521:orcl", "super",
                "594SHENG");
	}
	public static ArrayList<StorageBill> storagebill= new ArrayList<StorageBill>();  //左表
	public static ArrayList<StorageBill> storagebill_s = new ArrayList<StorageBill>();  //右表
	public static ArrayList<StorageBill> storagebill_m = new ArrayList<StorageBill>();  //右表
	//增加记录
	public static void insert(int StorageID,int StoreNum, String MerchID) throws SQLException {
		String sql = "insert into storagebill values(?,?,?)";
		Connection c1 = getconnection();
		PreparedStatement getinsql= c1.prepareStatement(sql);
		getinsql.setInt(1,StorageID);
		getinsql.setInt(2, StoreNum);
		getinsql.setString(3, MerchID);
		getinsql.execute();
		/*
		 * executeQuery只能用于查询，execute方法才可以执行insert，update，delete操作。
		 * */
		
	}
	//删除记录
	public static void delete(int StorageID) throws SQLException {
		String sql = "delete from storagebill where StorageID = ?";
		Connection c1 = getconnection();
		PreparedStatement getinsql= c1.prepareStatement(sql);
		getinsql.setInt(1, StorageID);
		getinsql.execute();
	}
	//更新记录
	public  static void update(int StorageID,int StoreNum,String MerchID,int StorageIDs) throws SQLException{
		String sql = "update StorageBill set StorageID = ? , StoreNum = ? , MerchID = ?  where StorageID = ?";
		 Connection c1 = getconnection();
		 PreparedStatement getinsql =c1.prepareStatement(sql);
		 getinsql.setInt(1,StorageID);
		 getinsql.setInt(2, StoreNum);
		 getinsql.setString(3, MerchID);
		 getinsql.setInt(4, StorageIDs);
		 getinsql.execute();
	}
	//查找记录(单行查找)
	public static void select(int StorageID) throws SQLException{
		String sql = "select * from StorageBill where StorageID = " + StorageID+"order by storageid";
		 Connection c1 = getconnection();
		 PreparedStatement getinsql =c1.prepareStatement(sql);
		 
		 ResultSet rs = getinsql.executeQuery(sql);		
		 if(rs.next())
		 System.out.println(rs.getInt("StorageID")+" "+rs.getInt("StoreNum")+" "+rs.getString("MerchID"));
	}
	//查找记录（查找全表）
	public static void select_all() throws SQLException{
		String sql = "select * from StorageBill ";
		
		 Connection c1 = getconnection();
		 PreparedStatement getinsql =c1.prepareStatement(sql);	 
		 ResultSet rs = getinsql.executeQuery(sql);		
		 while(rs.next()) {
			 StorageBill store = new StorageBill();
			 store.setStorageID(rs.getInt("StorageID"));
			 store.setStoreNum(rs.getInt("StoreNum"));
			 store.setMerchID(rs.getString("MerchID"));
			 storagebill.add(store);
		 }
	}
	public static void select_all_() throws SQLException{
		String sql = "select * from StorageBill ";
		
		 Connection c1 = getconnection();
		 PreparedStatement getinsql =c1.prepareStatement(sql);	 
		 ResultSet rs = getinsql.executeQuery(sql);		
		 while(rs.next()) {
			 StorageBill store = new StorageBill();
			 store.setStorageID(rs.getInt("StorageID"));
			 store.setStoreNum(rs.getInt("StoreNum"));
			 store.setMerchID(rs.getString("MerchID"));
			 storagebill_m.add(store);
		 }
	}
	public static void main(String[] args) throws SQLException {
		//insert(1012,12,"12");   测试通过
		//delete(1012);    测试通过
		//update(1012,12,"12",1011);  测试通过
		select(1);   //测试通过
//		select_all();
//		for(int i = 0;i<storagebill.size();i++) {
//			System.out.println(storagebill.get(i).getStorageID()+" "+
//					storagebill.get(i).getStoreNum()+" "+storagebill.get(i).getMerchID());
//		}      测试通过
	}
}
