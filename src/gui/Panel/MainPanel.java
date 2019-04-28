package gui.Panel;

import gui.Listener.BuyListener;
import gui.Listener.CommodityListener;
import gui.Listener.ProfferListenner;
import gui.Listener.RepertoryListener;
import gui.Listener.SaleLisener;
import gui.Listener.StaffListener;
import gui.Listener.SystemListener;
import gui.Listener.UserListener;
import gui.Panel.BuyPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import DAO.StorageBillDAO;
import DAO.UserBillDAO;



public class MainPanel {
	private static final long serialVersionUID = 1L;
	public static void GUINM() {
	    	 try {
	    		 UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
	    		// UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceFieldOfWheatLookAndFeel");
				//UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
static {
	GUINM();
	
}
private MainPanel() {
	
}
public static MainPanel instance = new MainPanel();
public static JButton buy = new JButton("购货管理");
public static JButton user = new JButton("用户管理");
public static JButton supply = new JButton("供应商管理");
public static JButton commodity = new JButton("商品信息管理");
public static JButton repertory = new JButton("库存管理");
public static JButton sale = new JButton("销售分析");
public static JButton config = new JButton("系统管理");
public static JButton employee = new JButton("员工管理");
public static JFrame f = new JFrame("M超市管理系统");
public static JButton shop = new JButton("登录");
public static JPasswordField password = new JPasswordField();
public static JTextField login = new JTextField();
public static JLabel img = new JLabel();
public static int login_chect = 0;
public static void showbutton() {
	 ImageIcon pbuy = new ImageIcon("image/购货管理.png");	
	 buy.setIcon(pbuy);
	 buy.setVerticalTextPosition(JButton.BOTTOM);
	 buy.setHorizontalTextPosition(JButton.CENTER);
	 buy.setBounds(100,100,100,100);
	 ImageIcon puser = new ImageIcon("image/用户管理.png");	
	 user.setIcon(puser);
	 user.setVerticalTextPosition(JButton.BOTTOM);
	 user.setHorizontalTextPosition(JButton.CENTER);
	 ImageIcon psupply = new ImageIcon("image/供应商管理.png");	
	 supply.setIcon(psupply);
	 supply.setVerticalTextPosition(JButton.BOTTOM);
	 supply.setHorizontalTextPosition(JButton.CENTER);
	 ImageIcon pcommodity = new ImageIcon("image/商品信息管理.png");	
	 commodity.setIcon(pcommodity);
	 commodity.setVerticalTextPosition(JButton.BOTTOM);
	 commodity.setHorizontalTextPosition(JButton.CENTER);
	 ImageIcon prepertory = new ImageIcon("image/库存管理.png");	
	 repertory.setIcon(prepertory);
	 repertory.setVerticalTextPosition(JButton.BOTTOM);
	 repertory.setHorizontalTextPosition(JButton.CENTER);
	 ImageIcon psale = new ImageIcon("image/销售分析.png");	
	 sale.setIcon(psale);
	 sale.setVerticalTextPosition(JButton.BOTTOM);
	 sale.setHorizontalTextPosition(JButton.CENTER);
	 ImageIcon pconfig = new ImageIcon("image/系统管理.png");	
	 config.setIcon(pconfig);
	 config.setVerticalTextPosition(JButton.BOTTOM);
	 config.setHorizontalTextPosition(JButton.CENTER);
	 ImageIcon pemployee = new ImageIcon("image/员工管理.png");	
	 employee.setIcon(pemployee);
	 employee.setVerticalTextPosition(JButton.BOTTOM);
	 employee.setHorizontalTextPosition(JButton.CENTER);
	 ImageIcon pshop = new ImageIcon("image/商店2.jpg");	
	 img.setIcon(pshop);
	
}
public static void showpanel() {
	
	 JPanel  p = new JPanel();
	 JPanel  p_s = new JPanel();
	 p_s.setLayout(new FlowLayout());
	 p_s.setSize(200,200);
	 f.setLayout(new BorderLayout());
	
	 login.setPreferredSize(new Dimension(150,30));
	 JLabel lg = new JLabel("账号:");
	
	 password.setPreferredSize(new Dimension(150,30));
	 JLabel pw = new JLabel("密码:");
	 JPanel login_s = new JPanel();
	 login_s.setLayout(new FlowLayout());
	 login_s.add(lg);
	 login_s.add(login);
	 JPanel pw_s = new JPanel();
	 pw_s.setLayout(new FlowLayout());
	 pw_s.add(pw);
	 pw_s.add(password);
     p.setLayout(new GridLayout(8,1));
	 showbutton();
	 f.setSize(650, 600);
	 f.setLocation(100,100);
	 p.add(buy);
	 p.add(user);
	 p.add(supply);
	 p.add(commodity);
	 p.add(repertory);
	 p.add(sale);
	 p.add(config);
	 p.add(employee);
	 p_s.add(login_s);
	 p_s.add(pw_s);
	 p_s.add(shop);
	 JPanel right = new JPanel();
	 right.setLayout(new BorderLayout());
	 right.add(p_s,BorderLayout.SOUTH);
	 right.add(img, BorderLayout.CENTER);
	 f.add(right, BorderLayout.CENTER);
	 f.add(p,BorderLayout.WEST);
	 //各类事件监听
	 //登录检测事件监听
	 shop.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			try {
				char[] passwords = password.getPassword();
		        String p = String.valueOf(passwords);
		       
		        String lg =login.getText().trim();		
		        if(UserBillDAO.select_s_(lg,p)==false){
					JOptionPane.showMessageDialog(null, "密码输入错误！", "提示",JOptionPane.WARNING_MESSAGE);  
				    return ;
				}
		        else if(UserBillDAO.select_s(lg,p).equals("Tom")) {
					JOptionPane.showMessageDialog(f, "店长，欢迎登陆M超市管理系统！", "提示",JOptionPane.WARNING_MESSAGE);  
				    login_s.removeAll();
				    pw_s.removeAll();
				    login_s.updateUI();
				    pw_s.updateUI();
				    p_s.remove(shop);
				    p_s.updateUI();
				    SystemPanel.logins="Tom";
				}
				else if(UserBillDAO.select_s(lg,p).equals("Gina")) {
					JOptionPane.showMessageDialog(f, "欢迎登陆M超市管理系统", "提示",JOptionPane.WARNING_MESSAGE);  
					user.setEnabled(false);
				    sale.setEnabled(false);
				    SystemPanel.bcrs.setEnabled(false);
				    SystemPanel.init.setEnabled(false);
				    employee.setEnabled(false);
					   login_s.removeAll();
					    pw_s.removeAll();
					    login_s.updateUI();
					    pw_s.updateUI();
					    p_s.remove(shop);
					    p_s.updateUI();
					    SystemPanel.logins="Gina";
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		 
	 });
	 //购货管理事件监听
	 BuyListener buylistener = new BuyListener();
	 buy.addActionListener(buylistener);
	 UserListener userlistener = new UserListener();
	 user.addActionListener(userlistener);
	 RepertoryListener repertorylistener = new RepertoryListener();
	 repertory.addActionListener(repertorylistener);
	 StaffListener stafflistener = new StaffListener();
	 employee.addActionListener(stafflistener);
	 ProfferListenner profferlistener = new ProfferListenner();
	 supply.addActionListener(profferlistener);
	 SaleLisener salelistener = new SaleLisener();
	 sale.addActionListener(salelistener);
	 CommodityListener commoditylistener = new CommodityListener();
	 commodity.addActionListener(commoditylistener);
	 SystemListener systemlistener = new SystemListener ();
	 config.addActionListener(systemlistener);
	 //commodity.enable(false);
	 f.setVisible(true);
	 f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
	 f.setResizable(false);
}
	 public static void main(String[] args) {
		 GUINM();
		 
		instance.showpanel();

	}
}

