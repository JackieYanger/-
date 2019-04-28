package gui.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import gui.Panel.BuyPanel;
import gui.Panel.MainPanel;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import DAO.StorageBillDAO;
import DAO.UserBillDAO;

public class BuyListener implements ActionListener{
   
	 
	@Override
	public void actionPerformed(ActionEvent e) {
		 
		// TODO Auto-generated method stub
		try {
			char[] passwords = MainPanel.password.getPassword();
	        String p = String.valueOf(passwords);
	       
	        String lg =MainPanel.login.getText().trim();
	     
			if(UserBillDAO.select_s(lg,p)!=null) {
				
			   
			}
			else {
				JOptionPane.showMessageDialog(null, "ÇëµÇÂ¼£¡", "ÌáÊ¾",JOptionPane.WARNING_MESSAGE);  
			    return ;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		BuyPanel.showpanel();
	
		
	}
	
     
}
