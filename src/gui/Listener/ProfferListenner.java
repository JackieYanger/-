package gui.Listener;


import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DAO.UserBillDAO;
import gui.Panel.CommodityPanel;
import gui.Panel.MainPanel;
import gui.Panel.ProfferPanel;

public class ProfferListenner implements ActionListener {

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
		ProfferPanel pp = new ProfferPanel();
		pp.showpanel();
		MainPanel.supply.setEnabled(false);
	}
}
