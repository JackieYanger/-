package gui.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import DAO.UserBillDAO;
import gui.Panel.MainPanel;
import gui.Panel.UserPanel;

public class UserListener implements ActionListener{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try {
			char[] passwords = MainPanel.password.getPassword();
	        String p = String.valueOf(passwords);
	       
	        String lg =MainPanel.login.getText().trim();
	        System.out.println(lg+" "+p);
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
		UserPanel.showPanel();
	}

}
