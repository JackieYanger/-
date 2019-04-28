package gui.Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import DAO.UserBillDAO;

public class SystemPanel {
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
public static JButton init = new JButton("初始化");
public static JButton bcrs = new JButton("备份/恢复");
public static JButton set = new JButton("修改密码");
public static JButton ensure = new JButton("确认初始化");
private static JTextField textField = new JTextField();;
private static JTextField textField_1 = new JTextField();
public static JButton ensureset = new JButton("确认修改");
public static JFrame f = new JFrame("系统管理");
public static JFrame fset = new JFrame();
public static JFrame fb = new JFrame("初始化");
public static JLabel labelb = new JLabel("请输入bin目录：");
public static JTextField textFieldse = new JTextField();
public static JPanel panel= new JPanel(new BorderLayout());
public static JScrollPane pane1=new JScrollPane(panel);
public static String logins = null;

static {
	
	bcrs.addActionListener(new ActionListener() {
		public void showfbcrs() {
			fb.setSize(400, 200);
			fb.setLocation(300,300);
			fb.setVisible(true);
//			fse.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
			fb.setResizable(false);
			fb.setLayout(null);
			fb.setTitle("员工管理");
			
			
			labelb.setBounds(50,50, 103, 20);
			fb.add(labelb);
			
			textFieldse.setBounds(150 ,50 , 177, 30);
			fb.add(textFieldse);
			textFieldse.setColumns(10);
			fb.add(ensure);
			ensure.setBounds(230,100, 103, 25);
			ensure.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String ps = textFieldse.getText();
					try {
						Bcrs(ps);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "备份完成！");
				}
				
			});
		}
		public void actionPerformed(ActionEvent e) {
			showfbcrs();
			
		}
		
	});
	init.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				Init() ;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "初始化完成");
		}
	
	});
	
	set.addActionListener(new ActionListener() {
		public void showset() {
			
			fset.setSize(650, 200);
			fset.setLocation(300,300);
			fset.setVisible(true);
			fset.setResizable(false);
			fset.setLayout(null);
			fset.setTitle("修改密码");
			
			String ps = "image/密码.png";
			ImageIcon background = new ImageIcon(ps);
			JLabel label10 = new JLabel(background);
			label10.setBounds(0, 0, fset.getWidth(), fset.getHeight()); 
			JPanel imagePanel = (JPanel) fset.getContentPane();
			imagePanel.setOpaque(false);
			fset.getLayeredPane().add(label10, new Integer(Integer.MIN_VALUE)); 
			
			JLabel label = new JLabel("请输入密码：");
			label.setBounds(150, 32, 100, 16);
			fset.getContentPane().add(label);
			Font h = new Font("宋体",Font.PLAIN,16); 
			label.setFont(h);
			label.setForeground(Color.red);
			
			final JTextField textField = new JTextField();
//			textField.setBounds(252, 29, 159, 22);
			textField.setBounds(300, 29, 159, 22);
			fset.getContentPane().add(textField);
			textField.setColumns(10);
			
			
			final JTextField textField_1 = new JTextField();
			textField_1.setBounds(300, 78, 159, 22);
			fset.getContentPane().add(textField_1);
			textField_1.setColumns(10);
			
			
			JLabel label_1 = new JLabel("请确认密码：");
			label_1.setBounds(150, 81, 100, 16);
			fset.getContentPane().add(label_1);
			Font m = new Font("宋体",Font.PLAIN,16); 
			label_1.setFont(m);
			label_1.setForeground(Color.red);
			
			
			JButton ensureset = new JButton("确认修改");
			ensureset.setBounds(448, 120, 103, 30);
			fset.getContentPane().add(ensureset);
			ensureset.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					String m_1 = textField.getText();
					String m_2 = textField_1.getText();
					
					if (m_1.length()==0) {
						JOptionPane.showMessageDialog(null, "输入的新密码不能为空.", "错误",JOptionPane.ERROR_MESSAGE);
					}
					else {
					if (m_1.equals(m_2)) {
						try {
							change(m_1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "修改完成");
					}
					else {
						JOptionPane.showMessageDialog(null, "两次密码输入不一样.", "错误",JOptionPane.ERROR_MESSAGE);  
					}
				}
				}
				
			});
			
		}

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			showset();
		}
		
	});
 
	ensureset.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		
			String m_1 = textField.getText();
			String m_2 = textField_1.getText();
			
			//name=m_1;
		
			if (m_1.length()==0) {
				JOptionPane.showMessageDialog(null, "输入的新密码不能为空.", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			else {
			if (m_1.equals(m_2)) {
				try {
					change(m_1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "修改完成");
				return;
			}
			else {
				JOptionPane.showMessageDialog(null, "两次密码输入不一样.", "错误",JOptionPane.ERROR_MESSAGE);  
			}
		}
		}
		
	});
}


	public static void showpanel() {

		   
		   f.setSize(650, 600);
		   f.setLocation(300,300);
		   f.setVisible(true);
//		   f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		   f.setResizable(false);
		   f.setLayout(null);
		  
		   
		   String ps = "image/系统.png";
		   ImageIcon background = new ImageIcon(ps);
		   JLabel label10 = new JLabel(background);
		   label10.setBounds(0, 0, f.getWidth(), f.getHeight()); 
		   JPanel imagePanel = (JPanel) f.getContentPane();
		   imagePanel.setOpaque(false);
		   f.getLayeredPane().add(label10, new Integer(Integer.MIN_VALUE)); 
		   
		   
			init.setBounds(12, 24, 103, 92);
			f.getContentPane().add(init);
			
				
			bcrs.setBounds(12, 201, 103, 99);
			f.getContentPane().add(bcrs);
			
			
			set.setBounds(12, 380, 103, 92);
			f.getContentPane().add(set);
		   
			
			
			String text1 = "<html> 根据实际情况进行参数设置<br/> 将不可恢复删除的相应数据<br/> </html>";
			JLabel L1 = new JLabel(text1);
			Font m1 = new Font("黑体",Font.PLAIN,16);
			L1.setFont(m1);
			L1.setForeground(Color.yellow);
			L1.setBounds(242, 24, 290, 92);
			f.getContentPane().add(L1);
			
			
			String text2 = "<html> 每隔几小时自动备份一次<br/> 点击后手动备份<br/> </html>";
			JLabel L2 = new JLabel(text2);
			Font m2 = new Font("黑体",Font.PLAIN,16);
			L2.setFont(m2);
			L2.setForeground(Color.yellow);
			L2.setBounds(242, 202, 290, 92);
			f.getContentPane().add(L2);
			
			String text3 = "<html> 改变操作员的密码<br/> 登录哪个操作员就更改哪个操作员<br/> </html>";
			JLabel L3 = new JLabel(text3);
			Font m3 = new Font("黑体",Font.PLAIN,16);
			L3.setFont(m3);
			L3.setForeground(Color.yellow);
			L3.setBounds(242, 380, 290, 92);
			f.getContentPane().add(L3);
			
			
			
	       /*SetListener setlistener = new SetListener();
	       set.addActionListener(setlistener);
	       InitListener initlistener = new InitListener();
	       init.addActionListener(initlistener);
	       BcrsListener bcrslistener = new BcrsListener();
	       bcrs.addActionListener(bcrslistener);*/
		}
	public static void Bcrs(String ps) throws Exception{
		Runtime rt = Runtime.getRuntime();
 	
 	Process p = rt.exec("cmd /c "+ps+"\\mysqldump -hlocalhost -uroot -p123456 --opt staffid>d:\\bk.sql"); 
 	 System.out.println(p.toString());
	}
	private static void Init() throws SQLException{
		String sql = " drop database supermarket ";
		Connection c1 =  getconnection();
		PreparedStatement getinsql= c1.prepareStatement(sql);
		getinsql.execute();
	}
	private static void change(String password) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "update rootbill set Password = ? where account = ?";
		Connection c1 = getconnection();
		PreparedStatement getinsql =c1.prepareStatement(sql);
		getinsql.setString(1, password);
		getinsql.setString(2, logins);
		getinsql.execute();
		
	}
	
}
