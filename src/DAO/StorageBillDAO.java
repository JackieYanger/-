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
	public static ArrayList<StorageBill> storagebill= new ArrayList<StorageBill>();  //���
	public static ArrayList<StorageBill> storagebill_s = new ArrayList<StorageBill>();  //�ұ�
	public static ArrayList<StorageBill> storagebill_m = new ArrayList<StorageBill>();  //�ұ�
	//���Ӽ�¼
	public static void insert(int StorageID,int StoreNum, String MerchID) throws SQLException {
		String sql = "insert into storagebill values(?,?,?)";
		Connection c1 = getconnection();
		PreparedStatement getinsql= c1.prepareStatement(sql);
		getinsql.setInt(1,StorageID);
		getinsql.setInt(2, StoreNum);
		getinsql.setString(3, MerchID);
		getinsql.execute();
		/*
		 * executeQueryֻ�����ڲ�ѯ��execute�����ſ���ִ��insert��update��delete������
		 * */
		
	}
	//ɾ����¼
	public static void delete(int StorageID) throws SQLException {
		String sql = "delete from storagebill where StorageID = ?";
		Connection c1 = getconnection();
		PreparedStatement getinsql= c1.prepareStatement(sql);
		getinsql.setInt(1, StorageID);
		getinsql.execute();
	}
	//���¼�¼
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
	//���Ҽ�¼(���в���)
	public static void select(int StorageID) throws SQLException{
		String sql = "select * from StorageBill where StorageID = " + StorageID+"order by storageid";
		 Connection c1 = getconnection();
		 PreparedStatement getinsql =c1.prepareStatement(sql);
		 
		 ResultSet rs = getinsql.executeQuery(sql);		
		 if(rs.next())
		 System.out.println(rs.getInt("StorageID")+" "+rs.getInt("StoreNum")+" "+rs.getString("MerchID"));
	}
	//���Ҽ�¼������ȫ��
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
		//insert(1012,12,"12");   ����ͨ��
		//delete(1012);    ����ͨ��
		//update(1012,12,"12",1011);  ����ͨ��
		select(1);   //����ͨ��
//		select_all();
//		for(int i = 0;i<storagebill.size();i++) {
//			System.out.println(storagebill.get(i).getStorageID()+" "+
//					storagebill.get(i).getStoreNum()+" "+storagebill.get(i).getMerchID());
//		}      ����ͨ��
	}
}
