package gui.Listener;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import DAO.UserBillDAO;
import gui.Panel.MainPanel;

public class EmployeeLisener implements ActionListener {

	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			char[] passwords = MainPanel.password.getPassword();
	        String p = String.valueOf(passwords);
	       
	        String lg =MainPanel.login.getText().trim();
	      
			if(UserBillDAO.select_s(lg,p)!=null) {
				
			   
			}
			else {
				JOptionPane.showMessageDialog(null, "���¼��", "��ʾ",JOptionPane.WARNING_MESSAGE);  
			    return ;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	// TODO Auto-generated method stub
	Connection conn = null;
	PreparedStatement pstm = null;
	final String USERNAME = "root";
	// �������ݿ������
	final String PASSWORD = "yourmoon98";
	// �������ݿ��������Ϣ
	final String DRIVER = "com.mysql.jdbc.Driver";
	final String URL = "jdbc:mysql://localhost:3306/shop";
	try
	{
		Class.forName(DRIVER);
		System.out.println("ע�������ɹ�����");
	}catch(
	ClassNotFoundException e1)
	{
		// TODO Auto-generated catch block
		System.out.println("ע������ʧ�ܣ���");
	}try
	{
		conn = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
		conn.setAutoCommit(false);
		String sql="insert into DaySaleBill (SaleDate,SaleMoney,MerchID) values(?,?,?)";
		String sql2="insert into MerchBill MerchID,kind values(?,?)";
		pstm = conn.prepareStatement(sql);
		PreparedStatement pstm2=conn.prepareStatement(sql2);
		for(int i=0;i<10;i++) {
			pstm.setString(1, "2018��10��01��");
			pstm.setInt(2, i*10);
			pstm.setString(3, ""+i+"");
			ResultSet rs = null;
			pstm.executeUpdate();
		}
		for(int i=0;i<4;i++) {
			pstm2.setString(1, ""+i+"");
			pstm2.setString(2,"����Ʒ");
			pstm2.executeUpdate();
		}
		for(int i=4;i<8;i++) {
			pstm2.setString(1, ""+i+"");
			pstm2.setString(2,"��Ʒ");
			pstm2.executeUpdate();
		}
		for(int i=8;i<10;i++) {
			pstm2.setString(1, ""+i+"");
			pstm2.setString(2,"ʳƷ");
			pstm2.executeUpdate();
		}
		String sql3="insert into MonthSaleBill (SaleDate,SaleMoney) values(?,?)";
		PreparedStatement pstm3=conn.prepareStatement(sql3);
		for(int i=0;i<10;i++) {
			pstm.setString(1, "2018��10��0"+i+"��");
			pstm.setInt(2, i*10);
			ResultSet rs = null;
			pstm3.executeUpdate();
		}
		String sql4="insert into YearSaleBill (SaleDate,SaleMoney) values(?,?)";
		PreparedStatement pstm4=conn.prepareStatement(sql4);
		for(int i=0;i<10;i++) {
			pstm.setString(1, "2018��0"+i+"��00��");
			pstm.setInt(2, i*10);
			ResultSet rs = null;
			pstm4.executeUpdate();
		}
		// ResultSet rs = null;
		// rs = pstm.executeQuery();
		// ResultSetMetaData rsmd = null;
	}catch(
	SQLException sqle)
	{
	}

}}

