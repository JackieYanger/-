package gui.Panel;

import DAO.CustBillDAO;
import DAO.UserBillDAO;
import javax.swing.*;
import entity.CustBill;
import entity.User;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class UserPanel {
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
	public static void GUINF(){
		try{
			javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static{
		GUINM();
		try {
			CustBillDAO.select_all();
			UserBillDAO.select_all();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static UserPanel instance = new UserPanel();
	private UserPanel(){}
	
	//table计数器
	public static int index_l = 0;
	public static int index_r = 0;
	//Frame
	public static JFrame frame = new JFrame("用户管理");
	//四个Button
	public static JButton add_l = new JButton("增加（左表）");
	public static JButton add_r = new JButton("增加（右表）");
	public static JButton search_l = new JButton("查询（左表）");
	public static JButton search_r = new JButton("查询（右表）");
	//图片
	public static JLabel img = new JLabel();
	//左表 用户表
	public static String[] head_l = {"id","name","password","secure_code","address","phone"};
	public static Object[][] infor_l = new Object[100][6];
	public static JTable tab_l = new JTable(infor_l,head_l);
	public static JScrollPane scroll_l = new JScrollPane(tab_l);
	//右表 会员表
	public static String[] head_r = {"id","name","phone","address","regdate"};
	public static Object[][] infor_r = new Object[100][5];
	public static JTable tab_r = new JTable(infor_r,head_r);
	public static JScrollPane scroll_r = new JScrollPane(tab_r);
	//按钮和表容器
	public static JPanel panel_but = new JPanel(new GridLayout(4,4));
	public static JPanel panel_tab = new JPanel(new GridLayout(1,2));
	public static JPanel panel_img = new JPanel(new FlowLayout());
	public static JPanel panel_right = new JPanel(new GridLayout(2,1));
	public static JPanel panel_tot = new JPanel(new FlowLayout());
	
	
	public static void showButton(){
		ImageIcon iadd_l = new ImageIcon("");
		add_l.setIcon(iadd_l);
		add_l.setVerticalTextPosition(JButton.BOTTOM);
		add_l.setHorizontalTextPosition(JButton.CENTER);
		
		ImageIcon iadd_r = new ImageIcon("");
		add_r.setIcon(iadd_r);
		add_r.setVerticalTextPosition(JButton.BOTTOM);
		add_r.setHorizontalTextPosition(JButton.CENTER);
		
		ImageIcon isearch_l = new ImageIcon("");
		search_l.setIcon(isearch_l);
		search_l.setVerticalTextPosition(JButton.BOTTOM);
		search_l.setHorizontalTextPosition(JButton.CENTER);
		
		ImageIcon isearch_r = new ImageIcon("");
		search_r.setIcon(isearch_r);
		search_r.setVerticalTextPosition(JButton.BOTTOM);
		search_r.setHorizontalTextPosition(JButton.CENTER);
		
		ImageIcon iuser = new ImageIcon("image/storage.png");	
		img.setIcon(iuser);
	}
	public static void showPanel(){
		panel_tab.removeAll();
		
		index_r = 0;
		for(int r = 0;r<CustBillDAO.custbill.size();r++){
			infor_r[index_r][0] = CustBillDAO.custbill.get(r).getID();
			infor_r[index_r][1] = CustBillDAO.custbill.get(r).getName();
			infor_r[index_r][2] = CustBillDAO.custbill.get(r).getPhone();
			infor_r[index_r][3] = CustBillDAO.custbill.get(r).getAddress();
			infor_r[index_r][4] = CustBillDAO.custbill.get(r).getRegdate();
			index_r++;
		}
		tab_r.updateUI();
		
		index_l = 0;
		//System.out.println(UserBillDAO.userlist.size());
		for(int l = 0;l<UserBillDAO.userlist.size();l++){
			infor_l[index_l][0] = UserBillDAO.userlist.get(l).getId();
			infor_l[index_l][1] = UserBillDAO.userlist.get(l).getName();
			infor_l[index_l][2] = UserBillDAO.userlist.get(l).getPassword();
			infor_l[index_l][3] = UserBillDAO.userlist.get(l).getSecure_code();
			infor_l[index_l][4] = UserBillDAO.userlist.get(l).getAddress();
			infor_l[index_l][5] = UserBillDAO.userlist.get(l).getPhone();
			index_l++;
		}
		tab_l.updateUI();
		showButton();
		//按钮面板
		panel_but.add(add_l);
		panel_but.add(add_r);
		panel_but.add(search_l);
		panel_but.add(search_r);
		//表格面板
//		scroll_l.setSize(1,200);
//		scroll_r.setSize(300,200);
		panel_tab.add(scroll_l);
		panel_tab.add(scroll_r);
		//图片面板
		panel_img.add(img);
		panel_right.add(panel_img);
		panel_right.add(panel_tab);
		panel_tot.setLayout(new BorderLayout());
		panel_tot.add(panel_but,BorderLayout.WEST);
		panel_tot.add(panel_right);
		//frame
		frame.add(panel_tot);
		frame.setSize(850,600);
		frame.setLocation(300,100);
		frame.setVisible(true);
		//frame.pack();
		//frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setResizable(true);
	}
	static{
		add_r_op();
		search_r_op();
		op_r();
		add_l_op();
		search_l_op();
		op_l();
	}
	//增加右表
	public static void add_r_op(){
		add_r.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				final JDialog dia = new JDialog(frame);
				dia.setSize(220,240);
				dia.setLocation(320,120);
				dia.setTitle("添加信息（右表）");
				//图片
				JLabel label = new JLabel();
				ImageIcon ii = new ImageIcon("");
				label.setIcon(ii);
				//Button
				JButton submit = new JButton("提交");
				//Label
				JLabel l_id = new JLabel("ID:");
				JLabel l_name = new JLabel("Name:");
				JLabel l_phone = new JLabel("Phone:");
				JLabel l_add = new JLabel("Address:");
				JLabel l_date = new JLabel("Regdate:");
				//TextField
				final JTextField t_id = new JTextField();
				final JTextField t_name = new JTextField();
				final JTextField t_phone = new JTextField();
				final JTextField t_add = new JTextField();
				final JTextField t_date = new JTextField();
				//JPanel
				JPanel p = new JPanel(new BorderLayout());
				JPanel pdown = new JPanel(new GridLayout(5,2));
				
				pdown.add(l_id);
				pdown.add(t_id);
				pdown.add(l_name);
				pdown.add(t_name);
				pdown.add(l_phone);
				pdown.add(t_phone);
				pdown.add(l_add);
				pdown.add(t_add);
				pdown.add(l_date);
				pdown.add(t_date);
				
				p.add(label,BorderLayout.NORTH);
				p.add(pdown);
				p.add(submit,BorderLayout.SOUTH);
				
				dia.add(p);
				dia.setVisible(true);
				
				final CustBill cust = new CustBill();
				submit.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						if(t_id.getText().length() == 0){
							JOptionPane.showMessageDialog(frame, "ID不可为空","提示",JOptionPane.WARNING_MESSAGE);
						}
						else{
						cust.setID(t_id.getText());
						cust.setName(t_name.getText());
						cust.setPhone(t_phone.getText());
						cust.setAddress(t_add.getText());
						cust.setRegdate(t_date.getText());
						CustBillDAO.custbill.add(cust);
						infor_r[CustBillDAO.custbill.size()-1][0] = cust.getID();
						infor_r[CustBillDAO.custbill.size()-1][1] = cust.getName();
						infor_r[CustBillDAO.custbill.size()-1][2] = cust.getPhone();
						infor_r[CustBillDAO.custbill.size()-1][3] = cust.getAddress();
						infor_r[CustBillDAO.custbill.size()-1][4] = cust.getRegdate();
						tab_r.updateUI();
						try {
							CustBillDAO.insert(cust.getID(),cust.getName(),cust.getPhone(),cust.getAddress(), cust.getRegdate());
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						dia.setVisible(false);
						}
					}
					
				});
			}
			
		});
	}
	//查询右表
	public static void search_r_op(){
		search_r.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String str = JOptionPane.showInputDialog(frame,"请输入要查找的ID\n","查找",JOptionPane.PLAIN_MESSAGE);
				if(str.length() == 0){
					JOptionPane.showMessageDialog(frame, "ID不可为空","提示",JOptionPane.WARNING_MESSAGE);
				}else{
					int i = 0;
					for(i = 0;i<CustBillDAO.custbill.size();i++){
						if(CustBillDAO.custbill.get(i).getID().equals(str)){
							final JDialog dia = new JDialog(frame);
							dia.setSize(220,240);
							dia.setLocation(320,120);
							dia.setTitle("查询信息");
							//
							JPanel p = new JPanel(new BorderLayout());
							JPanel pdown = new JPanel(new GridLayout(5,2));
							//
							JLabel label = new JLabel();
							ImageIcon ii = new ImageIcon();
							label.setIcon(ii);
							//
							JButton back = new JButton("返回");
							//
					    	JLabel l_id = new JLabel("ID:");
					    	JLabel l_name = new JLabel("Name:");
					    	JLabel l_phone = new JLabel("Phone:");
					    	JLabel l_add = new JLabel("Address:");
					    	JLabel l_date = new JLabel("Regdate:");
					    	JLabel t_id = new  JLabel(CustBillDAO.custbill.get(i).getID());
					    	JLabel t_name = new  JLabel(CustBillDAO.custbill.get(i).getName());
					    	JLabel t_phone = new  JLabel(CustBillDAO.custbill.get(i).getPhone());
					    	JLabel t_add = new  JLabel(CustBillDAO.custbill.get(i).getAddress());
					    	JLabel t_date = new  JLabel(CustBillDAO.custbill.get(i).getRegdate());
					    	//
					    	pdown.add(l_id);            	              	 
			           	    pdown.add(t_id);
			           	    pdown.add(l_name);
			            	pdown.add(t_name);
			            	pdown.add(l_phone);
			            	pdown.add(t_phone); 
			            	pdown.add(l_add);
			            	pdown.add(t_add);   
			            	pdown.add(l_date);
			            	pdown.add(t_date);   
			            	p.add(pdown);
			            	p.add(label,BorderLayout.NORTH);
			            	p.add(back, BorderLayout.SOUTH);
			            	dia.add(p);
			            	dia.setVisible(true);
			            	dia.setResizable(false);
			            	back.addActionListener(new ActionListener() {

								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									dia.setVisible(false);
								}
			            		  
			            	  });
			            	break;
						}
					}
					//System.out.println(i);
					if(i == CustBillDAO.custbill.size()){
						JOptionPane.showMessageDialog(frame, "无此用户!","提示",JOptionPane.WARNING_MESSAGE);
					
				}
				}
			}
		});
	}
	//对右表的各种操作
	public static void op_r(){
		tab_r.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				final int row = tab_r.rowAtPoint(e.getPoint());
				final int col = tab_r.columnAtPoint(e.getPoint());
//				Object value= tab_r.getValueAt(row, col);
//				System.out.println(value);
//				System.out.println(row);
//				System.out.println(StorageBillDAO.storagebill.size());
				if(row > CustBillDAO.custbill.size()-1){//table从0开始计数
					JOptionPane.showMessageDialog(frame,"选择了没有内容的行","提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
				Object[] options = {"删除","修改"};
				int rt = JOptionPane.showOptionDialog(frame, "进行哪一步操作", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				//System.out.println(rt);
				if(rt == 0){
					if(CustBillDAO.custbill.size() == 0){
						JOptionPane.showMessageDialog(frame, "表为空无法删除","提示",JOptionPane.WARNING_MESSAGE);
						return;
					}
					try {
						CustBillDAO.delete(CustBillDAO.custbill.get(row).getID());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//					if(StorageBillDAO.storagebill.size() == 1){
//						for(int j =0;j<StorageBillDAO.storagebill.size();j++){
//							obj[j][0]=obj[j][1]=obj[j][2] = "";
//						}
//					}
					CustBillDAO.custbill.remove(row);
//					System.out.println(StorageBillDAO.storagebill.size());
					int i;
					for(i=0;i<CustBillDAO.custbill.size();i++){
						infor_r[i][0] = CustBillDAO.custbill.get(i).getID();
						infor_r[i][1] = CustBillDAO.custbill.get(i).getName();
						infor_r[i][2] = CustBillDAO.custbill.get(i).getPhone();
						infor_r[i][3] = CustBillDAO.custbill.get(i).getAddress();
						infor_r[i][4] = CustBillDAO.custbill.get(i).getRegdate();	
					}
					infor_r[i][0]=infor_r[i][1]=infor_r[i][2] =infor_r[i][3]=infor_r[i][4]= "";
					tab_r.updateUI();
				}
				if(rt == 1){
					final JDialog dia = new JDialog(frame);
					dia.setSize(220,240);
					dia.setLocation(320,120);
					dia.setTitle("修改信息");
					//图片
					JLabel label = new JLabel();
					ImageIcon ii = new ImageIcon("");
					label.setIcon(ii);
					//按钮
					JButton submit = new JButton("提交修改");
					//Label and text
					JLabel l_id = new JLabel("ID:");
			    	JLabel l_name = new JLabel("Name:");
			    	JLabel l_phone = new JLabel("Phone:");
			    	JLabel l_add = new JLabel("Address:");
			    	JLabel l_date = new JLabel("Regdate:");
			    	final JTextField t_id = new JTextField();
					final JTextField t_name = new JTextField();
					final JTextField t_phone = new JTextField();
					final JTextField t_add = new JTextField();
					final JTextField t_date = new JTextField();
					//Panel
					JPanel p = new JPanel(new BorderLayout());
					JPanel pdown = new JPanel(new GridLayout(5,2));
					
					pdown.add(l_id);
					pdown.add(t_id);
					pdown.add(l_name);
					pdown.add(t_name);
					pdown.add(l_phone);
					pdown.add(t_phone);
					pdown.add(l_add);
					pdown.add(t_add);
					pdown.add(l_date);
					pdown.add(t_date);
					
					p.add(label, BorderLayout.NORTH);
					p.add(submit,BorderLayout.SOUTH);
					p.add(pdown);
					dia.add(p);
					dia.setVisible(true);
	            	dia.setResizable(false);
	            	submit.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							if(t_id.getText().length() == 0){
								JOptionPane.showMessageDialog(frame, "ID不可以为空","提示",JOptionPane.WARNING_MESSAGE);
							}
							else{
								try {
									CustBillDAO.update(t_id.getText(), t_name.getText(), t_phone.getText(), t_add.getText(), t_date.getText(), CustBillDAO.custbill.get(row).getID());
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								CustBillDAO.custbill.get(row).setID(t_id.getText());
								CustBillDAO.custbill.get(row).setName(t_name.getText());
								CustBillDAO.custbill.get(row).setPhone(t_phone.getText());
								CustBillDAO.custbill.get(row).setAddress(t_add.getText());
								CustBillDAO.custbill.get(row).setRegdate(t_date.getText());
								
								infor_r[row][0] = CustBillDAO.custbill.get(row).getID();
								infor_r[row][1] = CustBillDAO.custbill.get(row).getName();
								infor_r[row][2] = CustBillDAO.custbill.get(row).getPhone();
								infor_r[row][3] = CustBillDAO.custbill.get(row).getAddress();
								infor_r[row][4] = CustBillDAO.custbill.get(row).getRegdate();
								
								tab_r.updateUI();
								dia.setVisible(false);
							}
						}
	            		
	            	});
				}
				
			}
		});
	}
	//添加信息（左表）
	public static void add_l_op(){
		add_l.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				final JDialog dia = new JDialog(frame);
				dia.setSize(220,240);
				dia.setLocation(320,120);
				dia.setTitle("添加信息（左表）");
				//图片
				JLabel label = new JLabel();
				ImageIcon ii = new ImageIcon("");
				label.setIcon(ii);
				//Button
				JButton submit = new JButton("提交");
				//Label
				JLabel l_id = new JLabel("id:");
				JLabel l_name = new JLabel("name:");
				JLabel l_password = new JLabel("password:");
				JLabel l_code = new JLabel("code:");
				JLabel l_add = new JLabel("address:");
				JLabel l_phone = new JLabel("phone:");
				//TextField
				final JTextField t_id = new JTextField();
				final JTextField t_name = new JTextField();
				final JTextField t_password = new JTextField();
				final JTextField t_code = new JTextField();
				final JTextField t_add = new JTextField();
				final JTextField t_phone = new JTextField();
				//JPanel
				JPanel p = new JPanel(new BorderLayout());
				JPanel pdown = new JPanel(new GridLayout(6,2));
				
				pdown.add(l_id);
				pdown.add(t_id);
				pdown.add(l_name);
				pdown.add(t_name);
				pdown.add(l_password);
				pdown.add(t_password);
				pdown.add(l_code);
				pdown.add(t_code);
				pdown.add(l_add);
				pdown.add(t_add);
				pdown.add(l_phone);
				pdown.add(t_phone);
				
				p.add(label,BorderLayout.NORTH);
				p.add(pdown);
				p.add(submit,BorderLayout.SOUTH);
				
				dia.add(p);
				dia.setVisible(true);
				
				final User user = new User();
				submit.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						if(t_id.getText().length() == 0){
							JOptionPane.showMessageDialog(frame, "ID不可为空","提示",JOptionPane.WARNING_MESSAGE);
						}
						else{
						user.setId(Integer.parseInt(t_id.getText()));
						user.setName(t_name.getText());
						user.setPassword(t_password.getText());
						user.setSecure_code(t_code.getText());
						user.setAddress(t_add.getText());
						user.setPhone(t_phone.getText());
						UserBillDAO.userlist.add(user);
						infor_l[UserBillDAO.userlist.size()-1][0] = user.getId();
						infor_l[UserBillDAO.userlist.size()-1][1] = user.getName();
						infor_l[UserBillDAO.userlist.size()-1][2] = user.getPassword();
						infor_l[UserBillDAO.userlist.size()-1][3] = user.getSecure_code();
						infor_l[UserBillDAO.userlist.size()-1][4] = user.getAddress();
						infor_l[UserBillDAO.userlist.size()-1][5] = user.getPhone();
						tab_l.updateUI();
						try {
							UserBillDAO.insert(user.getId(), user.getName(), user.getPassword(), user.getSecure_code(), user.getAddress(),user.getPhone());
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						dia.setVisible(false);
						}
					}
					
				});
			}
			
		});
	}
	//查询左表
	public static void search_l_op(){
		search_l.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String str = JOptionPane.showInputDialog(frame,"请输入要查找的ID\n","查找",JOptionPane.PLAIN_MESSAGE);
				if(str.length() == 0){
					JOptionPane.showMessageDialog(frame, "ID不可为空","提示",JOptionPane.WARNING_MESSAGE);
				}else{
					int i = 0;
					for(i = 0;i<UserBillDAO.userlist.size();i++){
						if(UserBillDAO.userlist.get(i).getId() == Integer.parseInt(str)){
							final JDialog dia = new JDialog(frame);
							dia.setSize(220,240);
							dia.setLocation(320,120);
							dia.setTitle("查询信息");
							//
							JPanel p = new JPanel(new BorderLayout());
							JPanel pdown = new JPanel(new GridLayout(6,2));
							//
							JLabel label = new JLabel();
							ImageIcon ii = new ImageIcon();
							label.setIcon(ii);
							//
							JButton back = new JButton("返回");
							//
							JLabel l_id = new JLabel("id:");
							JLabel l_name = new JLabel("name:");
							JLabel l_password = new JLabel("password:");
							JLabel l_code = new JLabel("code:");
							JLabel l_add = new JLabel("address:");
							JLabel l_phone = new JLabel("phone:");
							
							JLabel t_id = new JLabel(Integer.toString(UserBillDAO.userlist.get(i).getId()));
							JLabel t_name = new JLabel(UserBillDAO.userlist.get(i).getName());
							JLabel t_password = new JLabel(UserBillDAO.userlist.get(i).getPassword());
							JLabel t_code = new JLabel(UserBillDAO.userlist.get(i).getSecure_code());
							JLabel t_add = new JLabel(UserBillDAO.userlist.get(i).getAddress());
							JLabel t_phone = new JLabel(UserBillDAO.userlist.get(i).getPhone());
					    	//
							pdown.add(l_id);
							pdown.add(t_id);
							pdown.add(l_name);
							pdown.add(t_name);
							pdown.add(l_password);
							pdown.add(t_password);
							pdown.add(l_code);
							pdown.add(t_code);
							pdown.add(l_add);
							pdown.add(t_add);
							pdown.add(l_phone);
							pdown.add(t_phone);  
			            	p.add(pdown);
			            	p.add(label,BorderLayout.NORTH);
			            	p.add(back, BorderLayout.SOUTH);
			            	dia.add(p);
			            	dia.setVisible(true);
			            	dia.setResizable(false);
			            	back.addActionListener(new ActionListener() {

								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									dia.setVisible(false);
								}
			            		  
			            	  });
			            	break;
						}
					}
					//System.out.println(i);
					if(i ==UserBillDAO.userlist.size()){
						JOptionPane.showMessageDialog(frame, "无此用户!","提示",JOptionPane.WARNING_MESSAGE);
					
				}
				}
			}
		});
	}
	//对左表的各种操作
	public static void op_l(){
		tab_l.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				final int row = tab_l.rowAtPoint(e.getPoint());
				final int col = tab_l.columnAtPoint(e.getPoint());
//				Object value= tab_r.getValueAt(row, col);
//				System.out.println(value);
//				System.out.println(row);
//				System.out.println(StorageBillDAO.storagebill.size());
				if(row > UserBillDAO.userlist.size()-1){//table从0开始计数
					JOptionPane.showMessageDialog(frame,"选择了没有内容的行","提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
				Object[] options = {"删除","修改"};
				int rt = JOptionPane.showOptionDialog(frame, "进行哪一步操作", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				//System.out.println(rt);
				if(rt == 0){
					if(UserBillDAO.userlist.size() == 0){
						JOptionPane.showMessageDialog(frame, "表为空无法删除","提示",JOptionPane.WARNING_MESSAGE);
						return;
					}
//					if(StorageBillDAO.storagebill.size() == 1){
//						for(int j =0;j<StorageBillDAO.storagebill.size();j++){
//							obj[j][0]=obj[j][1]=obj[j][2] = "";
//						}
//					}
					try {
						UserBillDAO.delete(UserBillDAO.userlist.get(row).getId());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					UserBillDAO.userlist.remove(row);
//					System.out.println(StorageBillDAO.storagebill.size());
					int i;
					for(i=0;i<UserBillDAO.userlist.size();i++){
						infor_l[i][0] = UserBillDAO.userlist.get(i).getId();
						infor_l[i][1] = UserBillDAO.userlist.get(i).getName();
						infor_l[i][2] = UserBillDAO.userlist.get(i).getPassword();
						infor_l[i][3] = UserBillDAO.userlist.get(i).getSecure_code();
						infor_l[i][4] = UserBillDAO.userlist.get(i).getAddress();	
						infor_l[i][5] = UserBillDAO.userlist.get(i).getPhone();	
					}
					infor_l[i][0]=infor_l[i][1]=infor_l[i][2] =infor_l[i][3]=infor_l[i][4]= infor_l[i][5] = "";
					tab_l.updateUI();
				}
				if(rt == 1){
					final JDialog dia = new JDialog(frame);
					dia.setSize(220,240);
					dia.setLocation(320,120);
					dia.setTitle("修改信息");
					//图片
					JLabel label = new JLabel();
					ImageIcon ii = new ImageIcon("");
					label.setIcon(ii);
					//按钮
					JButton submit = new JButton("提交修改");
					//Label and text
					JLabel l_id = new JLabel("id:");
					JLabel l_name = new JLabel("name:");
					JLabel l_password = new JLabel("password:");
					JLabel l_code = new JLabel("code:");
					JLabel l_add = new JLabel("address:");
					JLabel l_phone = new JLabel("phone:");
					final JTextField t_id = new JTextField();
					final JTextField t_name = new JTextField();
					final JTextField t_password = new JTextField();
					final JTextField t_code = new JTextField();
					final JTextField t_add = new JTextField();
					final JTextField t_phone = new JTextField();
					//Panel
					JPanel p = new JPanel(new BorderLayout());
					JPanel pdown = new JPanel(new GridLayout(6,2));
					
					pdown.add(l_id);
					pdown.add(t_id);
					pdown.add(l_name);
					pdown.add(t_name);
					pdown.add(l_password);
					pdown.add(t_password);
					pdown.add(l_code);
					pdown.add(t_code);
					pdown.add(l_add);
					pdown.add(t_add);
					pdown.add(l_phone);
					pdown.add(t_phone);  
					
					p.add(label, BorderLayout.NORTH);
					p.add(submit,BorderLayout.SOUTH);
					p.add(pdown);
					dia.add(p);
					dia.setVisible(true);
	            	dia.setResizable(false);
	            	submit.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							if(t_id.getText().length() == 0){
								JOptionPane.showMessageDialog(frame, "ID不可以为空","提示",JOptionPane.WARNING_MESSAGE);
							}
							else{
								try {
									UserBillDAO.update(Integer.parseInt(t_id.getText()), t_name.getText(), t_password.getText(), t_code.getText(),t_add.getText(), t_phone.getText(), UserBillDAO.userlist.get(row).getId());
								} catch (NumberFormatException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								UserBillDAO.userlist.get(row).setId(Integer.parseInt(t_id.getText()));
								UserBillDAO.userlist.get(row).setName(t_name.getText());
								UserBillDAO.userlist.get(row).setPassword(t_password.getText());
								UserBillDAO.userlist.get(row).setSecure_code(t_code.getText());
								UserBillDAO.userlist.get(row).setAddress(t_add.getText());
								UserBillDAO.userlist.get(row).setPhone(t_phone.getText());
								
								infor_l[row][0] = UserBillDAO.userlist.get(row).getId();
								infor_l[row][1] = UserBillDAO.userlist.get(row).getName();
								infor_l[row][2] = UserBillDAO.userlist.get(row).getPassword();
								infor_l[row][3] = UserBillDAO.userlist.get(row).getSecure_code();
								infor_l[row][4] = UserBillDAO.userlist.get(row).getAddress();
								infor_l[row][5] = UserBillDAO.userlist.get(row).getPhone();
								
								tab_l.updateUI();
								dia.setVisible(false);
							}
						}
	            		
	            	});
				}
				
			}
		});
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		showPanel();
   
	}

}
