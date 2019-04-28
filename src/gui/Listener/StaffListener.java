package gui.Listener;

import gui.Panel.MainPanel;
import gui.Panel.StaffPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import DAO.UserBillDAO;


public class StaffListener  implements ActionListener {	
	public void actionPerformed(ActionEvent e) {
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
		// TODO Auto-generated method stub
		try {
			StaffPanel.showpanel();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
	
}
