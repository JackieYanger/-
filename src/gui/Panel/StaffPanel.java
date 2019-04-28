package gui.Panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSet;


class BasicInfor{
	public String staffid;
	public String name;
	public String sex;
	public String post;
	public String phonenum;
	public String address;
	public String kind;
	public String idcard;
	public String salary;
	
}
public class StaffPanel extends AbstractTableModel{


	/**
	 * 
	 */
	static {
		MainPanel.GUINM();
		
	}
	public static void main(String[] args) throws SQLException {
		showpanel();
	}
	private static final long serialVersionUID = 1L;
	public static StaffPanel instances = new StaffPanel();
	static {
		  try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public static Connection getconnection() throws SQLException{
		return DriverManager.getConnection("jdbc:oracle:thin:@188.131.140.28:1521:orcl", "super",
                "594SHENG");
	}
	public static JButton free = new JButton("解雇员工");
	public static JButton add  = new JButton("聘用员工");
	public static JButton select = new JButton("查询员工");
	public static JButton update = new JButton("更新");
	public static JButton ensure3 = new JButton("删除");
	public static JButton ensure = new JButton("查找");
	public static JButton ensure4 = new JButton("更新");

	public static JFrame f = new JFrame("员工管理");
	public static JFrame fre = new JFrame();
	public static JFrame fse = new JFrame();
	public static JFrame fup = new JFrame();
	public static JFrame fadd = new JFrame("员工管理");
	public static JPanel tp = new JPanel(new BorderLayout());
	public static JPanel js = new JPanel(new GridLayout(4,1));
	public static JLabel labelse = new JLabel("请输入员工号：");
	public static JTextField textFieldse = new JTextField();

	
	public static ArrayList<BasicInfor> init = new ArrayList<BasicInfor>();
	
	public static String[] colunmnNames = {"职工编号", "姓名", "性别", "职务", "联系电话", "联系地址", "类别", "身份证号", "薪资"};
	public static Object[][] infor = new String[100][10]; 
	public static JTable table = new JTable(infor , colunmnNames); 
	public static  JScrollPane scrollPane = new JScrollPane(table);
	public static BasicInfor bi = new BasicInfor();	 
	public static JPanel table_panel = new JPanel();
	 public static int index=0;
	 public static int index_d=0;
	 public static DefaultTableModel model = new DefaultTableModel(infor,colunmnNames){
	 	 private static final long serialVersionUID=1L;
	 	 public boolean isCellEditable(int row,int column){
	 		 return false;
	 	 }
	  };
	
static {
	try {
		select();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
}
	
	// 获得列的数目
	 public int getRowCount() {
			// TODO Auto-generated method stub
			return 9;
		}
	// 获得行的数目
	 public int getColumnCount() {
			// TODO Auto-generated method stub
			return 100;
		}
	// 获得某列的名字，而目前各列的名字保存在字符串数组columnNames中
	 public String getColumnName(int col) {
         return colunmnNames[col];
	 }

	 public static void showpanel() throws SQLException {
	      //table= new JTable(infor , colunmnNames);
		   //js.removeAll(); 	
		    int index = 0;
			for(int i= 0 ;i<init.size();i++) {
			     infor[index][0]=init.get(i).staffid;
				      infor[index][1]=init.get(i).name;
				      infor[index][2]=init.get(i).sex;
				      infor[index][3]=init.get(i).post;
				      infor[index][4]=init.get(i).phonenum;
				      infor[index][5]=init.get(i).address;
				      infor[index][6]=init.get(i).kind;
				      infor[index][7]=init.get(i).idcard;
				      infor[index][8]=init.get(i).salary;
				      index++;
			}
		   table_panel.add(table);
		   table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		   table = new JTable(infor , colunmnNames); 
		   JScrollPane scrollPane = new JScrollPane(table);
		   // 产生一个带滚动条的面板		   	        
	       //添加一个布局	       
	       tp.add(add,BorderLayout.NORTH);
	       f.getContentPane().add(add,BorderLayout.NORTH);	       
	       //设置布局
	       f.getContentPane().setLayout(new BorderLayout());	 
	       // 将带滚动条的面板添加入窗口中
	       f.getContentPane().add(scrollPane, BorderLayout.CENTER);
	       //面板组件JPanel	       
	       js.add(add);
	       js.add(free);
	       js.add(select);
	       js.add(update);
	       f.add(js,BorderLayout.WEST);	      	      	  
	       f.setSize(650,600);
		   f.setVisible(true);
		   f.setResizable(false);
	          
		}
    public static void showadd() {
			
			
			fadd.setLayout(null);
			fadd.setTitle("员工管理");
			String ps = "image/添加员工.png";
			ImageIcon background = new ImageIcon(ps);
			JLabel label10 = new JLabel(background);
			label10.setBounds(0, 0, fadd.getWidth(), fadd.getHeight()); 
			JPanel imagePanel = (JPanel) fadd.getContentPane();
			imagePanel.setOpaque(false);
			fadd.getLayeredPane().add(label10, new Integer(Integer.MIN_VALUE)); 
			
			
			
			JLabel label = new JLabel("请输入员工号：");
			label.setBounds(104, 17, 112, 20);
			fadd.getContentPane().add(label);
			final JTextField textField = new JTextField();
			textField.setBounds(230, 15, 177, 22);
			fadd.getContentPane().add(textField);
			textField.setColumns(10);
			String staffid = textField.getText();
			
			JLabel label_1 = new JLabel("请输入姓名：");
			label_1.setBounds(104, 75, 112, 20);
			fadd.getContentPane().add(label_1);		
			final JTextField textField_1 = new JTextField();
			textField_1.setBounds(230, 72, 177, 22);
			fadd.getContentPane().add(textField_1);
			textField_1.setColumns(10);
			String name = textField_1.getText();
			
			JLabel label_2 = new JLabel("请输入性别：");
			label_2.setBounds(104, 120, 112, 20);
			fadd.getContentPane().add(label_2);
			final JTextField textField_2 = new JTextField();
			textField_2.setBounds(230, 117, 177, 22);
			fadd.getContentPane().add(textField_2);
			textField_2.setColumns(10);
			String sex = textField_2.getText();
			
			JLabel label_3 = new JLabel("请输入职务：");
			label_3.setBounds(104, 181, 112, 20);
			fadd.getContentPane().add(label_3);		
			final JTextField textField_3 = new JTextField();
			textField_3.setBounds(230, 178, 177, 22);
			fadd.getContentPane().add(textField_3);
			textField_3.setColumns(10);
			String post = textField_3.getText();
			
			JLabel label_4 = new JLabel("请输入电话：");
			label_4.setBounds(104, 243, 112, 20);
			fadd.getContentPane().add(label_4);
			final JTextField textField_4 = new JTextField();
			textField_4.setBounds(230, 240, 177, 22);
			fadd.getContentPane().add(textField_4);
			textField_4.setColumns(10);
			
			
			JLabel label_5 = new JLabel("请输入联系地址：");
			label_5.setBounds(104, 300, 112, 20);
			fadd.getContentPane().add(label_5);		
			final JTextField textField_5 = new JTextField();
			textField_5.setBounds(230, 297, 177, 22);
			fadd.getContentPane().add(textField_5);
			textField_5.setColumns(10);
			String address = textField_5.getText();
			
			JLabel label_6 = new JLabel("请输入职工类别：");
			label_6.setBounds(104, 360, 112, 20);
			fadd.getContentPane().add(label_6);		
			final JTextField textField_6 = new JTextField();
			textField_6.setBounds(230, 357, 177, 22);
			fadd.getContentPane().add(textField_6);
			textField_6.setColumns(10);
			String kind = textField_6.getText();
			
			JLabel label_7 = new JLabel("请输入身份证号：");
			label_7.setBounds(104, 412, 112, 20);
			fadd.getContentPane().add(label_7);		
			final JTextField textField_7 = new JTextField();
			textField_7.setBounds(230, 409, 177, 22);
			fadd.getContentPane().add(textField_7);
			textField_7.setColumns(10);
			JLabel lblNewLabel = new JLabel("请输入薪资：");
			lblNewLabel.setBounds(104, 465, 112, 20);
			fadd.getContentPane().add(lblNewLabel);		
			final JTextField textField_8 = new JTextField();
			textField_8.setBounds(230, 462, 177, 22);
			fadd.getContentPane().add(textField_8);
			textField_8.setColumns(10);
			String salary = textField_8.getText();
			
			JButton ensure2 = new JButton("添加员工");
			ensure2.setBounds(500, 465, 103, 25);
			fadd.getContentPane().add(ensure2);
			
			ensure2.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					//table.updateUI();
					String staffid = textField.getText();
					String name = textField_1.getText();
					String sex = textField_2.getText();
					String post = textField_3.getText();
					String phonenum = textField_4.getText();
					String address = textField_5.getText();
					String kind = textField_6.getText();
					String idcard = textField_7.getText();
					String salary = textField_8.getText();
					 BasicInfor instances1 = new BasicInfor();
					 instances1.staffid= staffid;
					 instances1.name= name;
					 instances1.sex= sex;
					 instances1.post= post;
					 instances1.phonenum= phonenum;
					 instances1.address= address;
					 instances1.kind= kind;
					 instances1.idcard= idcard;
					 instances1.salary= salary;
					 init.add(instances1);
					if(staffid.length() == 0 ) {
						JOptionPane.showMessageDialog(null, "输入有空！");
					}
					
					else{ 
						try {
						insert(staffid,name,sex,post,phonenum,address,kind,idcard,salary);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "添加成功！");
				}
																
		             for(int i = 0 ;i<init.size();i++) {
					  infor[i][0]=init.get(i).staffid;
				      infor[i][1]=init.get(i).name;
				      infor[i][2]=init.get(i).sex;
				      infor[i][3]=init.get(i).post;
				      infor[i][4]=init.get(i).phonenum;
				      infor[i][5]=init.get(i).address;
				      infor[i][6]=init.get(i).kind;
				      infor[i][7]=init.get(i).idcard;
				      infor[i][8]=init.get(i).salary;
				      table.updateUI();	    					
					}
		             
		             
		             fadd.setVisible(false);	
		          //   table_panel.removeAll();
				}
				
				});
			fadd.setSize(650, 560);
			fadd.setLocation(300,300);
			fadd.setVisible(true);
			
			fadd.setResizable(false);
		}
	 
	 
public static void showselect() {
			
  			fse.setSize(400, 200);
  			fse.setLocation(300,300);
  			fse.setVisible(true);
//  			fse.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
  			fse.setResizable(false);
  			fse.setLayout(null);
  			fse.setTitle("员工管理");
  			String ps = "image/添加员工.png";
  			ImageIcon background = new ImageIcon(ps);
  			JLabel label10 = new JLabel(background);
  			label10.setBounds(0, 0, fse.getWidth(), fse.getHeight()); 
  			JPanel imagePanel = (JPanel) fse.getContentPane();
  			imagePanel.setOpaque(false);
  			fse.getLayeredPane().add(label10, new Integer(Integer.MIN_VALUE)); 
  			
//  			JLabel labelse = new JLabel("请输入员工号：");
//  			JTextField textFieldse = new JTextField();
  			
  			labelse.setBounds(50,50, 103, 20);
  			fse.add(labelse);
  			
//  			final JTextField textFieldse = new JTextField();
  			textFieldse.setBounds(150 ,50 , 177, 30);
  			fse.add(textFieldse);
  			textFieldse.setColumns(10);
  			fse.add(ensure);
  			ensure.setBounds(230,100, 103, 25);
  			ensure.addActionListener(new ActionListener() {
  				
  				public void actionPerformed(ActionEvent arg0) {
  					
  					
  					String staffid = textFieldse.getText();
  					if(staffid.length() == 0){
  						JOptionPane.showMessageDialog(f, "StorageID不可为空","提示",JOptionPane.WARNING_MESSAGE);
  					}
  					else {try {
  						select(staffid);
  					} catch (SQLException e) {
  						// TODO Auto-generated catch block
  						e.printStackTrace();
  					}
  					}
  					fse.setVisible(false);
  				}
  				
  			});
  				
  			
  		}
  	   
		
public static void showfree() {
			
			fre.setSize(400, 200);
			fre.setLocation(300,300);
			fre.setVisible(true);
//			fre.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
			fre.setResizable(false);
			fre.setLayout(null);
			fre.setTitle("员工管理");
			JLabel labelfre = new JLabel("请输入员工号：");
			labelfre.setBounds(50,50, 103, 20);
			fre.getContentPane().add(labelfre);
			
			String ps = "D:/develop/材料/添加员工.png";
			ImageIcon backgroundfre = new ImageIcon(ps);
			JLabel label10 = new JLabel(backgroundfre);
			label10.setBounds(0, 0, fre.getWidth(), fre.getHeight()); 
			JPanel imagePanelfree = (JPanel) fre.getContentPane();
			imagePanelfree.setOpaque(false);
			fre.getLayeredPane().add(label10, new Integer(Integer.MIN_VALUE)); 
			
			
			final JTextField textFieldfree = new JTextField();
			textFieldfree.setBounds(150 ,50 , 177, 30);
			
			
//			delete(StaffID);
			
			fre.getContentPane().add(textFieldfree);
			textFieldfree.setColumns(10);
			fre.add(ensure3);
			ensure3.setBounds(230,100, 103, 25);
			//删除记录
			
			ensure3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String StaffID = textFieldfree.getText();
					fre.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
					if(StaffID.length()==0) {
						JOptionPane.showMessageDialog(null, "输入为空！");
					}
					else{
						try {
						delete(StaffID);
						for(int i =0;i<init.size();i++) {
							if(StaffID.equals(init.get(i).staffid)) {
								init.remove(i);
								break;
							}
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "删除成功！");
					
				}
					
					for(int i=0;i<init.size();i++) {											
						  infor[i][0]=init.get(i).staffid;
					      infor[i][1]=init.get(i).name;
					      infor[i][2]=init.get(i).sex;
					      infor[i][3]=init.get(i).post;
					      infor[i][4]=init.get(i).phonenum;
					      infor[i][5]=init.get(i).address;
					      infor[i][6]=init.get(i).kind;
					      infor[i][7]=init.get(i).idcard;
					      infor[i][8]=init.get(i).salary;
					      table.updateUI();				
					}
					infor[init.size()][0] =infor[init.size()][1]=infor[init.size()][2]=infor[init.size()][3]=infor[init.size()][4]=infor[init.size()][5]=infor[init.size()][6]=infor[init.size()][7]=infor[init.size()][8]= "";
					fre.setVisible(false);
				}							
			});
		}
		
public static void showfup() {
			fup.setSize(650, 560);
			fup.setLocation(300,300);
			fup.setVisible(true);
			fup.setResizable(false);
			fup.setLayout(null);
			fup.setTitle("员工管理");
			JLabel labelfre = new JLabel("请输入员工号：");
			labelfre.setBounds(104, 17, 103, 20);
			fup.getContentPane().add(labelfre);
			
			JLabel label = new JLabel("请输入员工号：");
			label.setBounds(104, 17, 112, 20);
			fup.getContentPane().add(label);
			final JTextField textField = new JTextField();
			textField.setBounds(230, 15, 177, 22);
			fup.getContentPane().add(textField);
			textField.setColumns(10);
			JLabel label_1 = new JLabel("请输入姓名：");
			label_1.setBounds(104, 75, 112, 20);
			fup.getContentPane().add(label_1);		
			final JTextField textField_1 = new JTextField();
			textField_1.setBounds(230, 72, 177, 22);
			fup.getContentPane().add(textField_1);
			textField_1.setColumns(10);
			JLabel label_2 = new JLabel("请输入性别：");
			label_2.setBounds(104, 120, 112, 20);
			fup.getContentPane().add(label_2);
			final JTextField textField_2 = new JTextField();
			textField_2.setBounds(230, 117, 177, 22);
			fup.getContentPane().add(textField_2);
			textField_2.setColumns(10);
			JLabel label_3 = new JLabel("请输入职务：");
			label_3.setBounds(104, 181, 112, 20);
			fup.getContentPane().add(label_3);		
			final JTextField textField_3 = new JTextField();
			textField_3.setBounds(230, 178, 177, 22);
			fup.getContentPane().add(textField_3);
			textField_3.setColumns(10);
			JLabel label_4 = new JLabel("请输入电话：");
			label_4.setBounds(104, 243, 112, 20);
			fup.getContentPane().add(label_4);
			final JTextField textField_4 = new JTextField();
			textField_4.setBounds(230, 240, 177, 22);
			fup.getContentPane().add(textField_4);
			textField_4.setColumns(10);
			JLabel label_5 = new JLabel("请输入联系地址：");
			label_5.setBounds(104, 300, 112, 20);
			fup.getContentPane().add(label_5);		
			final JTextField textField_5 = new JTextField();
			textField_5.setBounds(230, 297, 177, 22);
			fup.getContentPane().add(textField_5);
			textField_5.setColumns(10);
			JLabel label_6 = new JLabel("请输入职工类别：");
			label_6.setBounds(104, 360, 112, 20);
			fup.getContentPane().add(label_6);		
			final JTextField textField_6 = new JTextField();
			textField_6.setBounds(230, 357, 177, 22);
			fup.getContentPane().add(textField_6);
			textField_6.setColumns(10);
			JLabel label_7 = new JLabel("请输入身份证号：");
			label_7.setBounds(104, 412, 112, 20);
			fup.getContentPane().add(label_7);		
			final JTextField textField_7 = new JTextField();
			textField_7.setBounds(230, 409, 177, 22);
			fup.getContentPane().add(textField_7);
			textField_7.setColumns(10);
			JLabel lblNewLabel = new JLabel("请输入薪资：");
			lblNewLabel.setBounds(104, 465, 112, 20);
			fup.getContentPane().add(lblNewLabel);		
			final JTextField textField_8 = new JTextField();
			textField_8.setBounds(230, 462, 177, 22);
			fup.getContentPane().add(textField_8);
			textField_8.setColumns(10);
			JButton ensure4 = new JButton("更新");
			ensure4.setBounds(500, 465, 103, 25);
			fup.getContentPane().add(ensure4);
		  table_panel.removeAll();
			ensure4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int temp=0;
					String staffid = textField.getText();
					String name = textField_1.getText();
					String sex = textField_2.getText();
					String post = textField_3.getText();
					String phonenum = textField_4.getText();
					String address = textField_5.getText();
					String kind = textField_6.getText();
					String idcard = textField_7.getText();
					String salary = textField_8.getText();
					if(staffid.length() == 0 ) {
						JOptionPane.showMessageDialog(null, "输入有空！");
					}
					
					else{ 
						try {
						update(staffid,name,sex,post,phonenum,address,kind,idcard,salary);
						for(int i =0 ;i<init.size();i++) {
							if(staffid.equals(init.get(i).staffid)) {
								init.get(i).staffid=staffid;
								init.get(i).name=name;
								init.get(i).sex=sex;
								init.get(i).post=post;
								init.get(i).phonenum=phonenum;
								init.get(i).address=address;
								init.get(i).kind=kind;
								init.get(i).idcard=idcard;
								init.get(i).salary=salary;
						         break;
							}
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					JOptionPane.showMessageDialog(null, "更新成功！");
				}
					fup.setVisible(false);
					BasicInfor instances = new BasicInfor();								
					for(int i=0;i<init.size();i++) {						
						  infor[i][0]=init.get(i).staffid;
					      infor[i][1]=init.get(i).name;
					      infor[i][2]=init.get(i).sex;
					      infor[i][3]=init.get(i).post;
					      infor[i][4]=init.get(i).phonenum;
					      infor[i][5]=init.get(i).address;
					      infor[i][6]=init.get(i).kind;
					      infor[i][7]=init.get(i).idcard;
					      infor[i][8]=init.get(i).salary;
					  	 table.updateUI();
					}
				
					}
	
				});
		}
		
	  
	   static {		
		update.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				showfup();
			}
			
		});
		
		free.addActionListener(new ActionListener() {	    	  			    	 	    		    	  
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				showfree();
			}
	    	   
	       });
		   						
		select.addActionListener(new ActionListener() {				    	   	
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				showselect();
			}
	    	   
	       });
	       
		 add.addActionListener(new ActionListener() {
	    	  
	    	
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				showadd();
			}
	    	   
	       });
	}
	
	
	 public static void delete(String StaffID) throws SQLException {
		  String sql = "delete from Staffid where StaffID = ?";
//			Connection c1 = getconnection();
			Connection c1 =  getconnection();
			PreparedStatement getinsql= ((java.sql.Connection) c1).prepareStatement(sql);
			getinsql.setString(1, StaffID);
			getinsql.execute();
	  }
	public static void select(String Staffid) throws SQLException{
		String sql = "select * from Staffid where StaffID = "+ Staffid;
		Connection c1 =  getconnection();
		PreparedStatement getinsql= c1.prepareStatement(sql);
		 
		 java.sql.ResultSet rs = getinsql.executeQuery(sql);	
		 if(rs.next()) {
			 String url = rs.getString("StaffID")+"\n"+rs.getString("Name")+"\n"+rs.getString("Sex")+"\n"+
					 rs.getString("Post")+"\n"+ rs.getString("PhoneNum")+"\n" + rs.getString("Address") +"\n"+ rs.getString("Kind")+"\n"
					 + rs.getString("IDCard") +"\n"+ rs.getString("Salary");
			 JOptionPane.showMessageDialog(f, url,"提示",JOptionPane.WARNING_MESSAGE);
				
				
		 }
		 else{
			 JOptionPane.showMessageDialog(f, "没有找到该员工","提示",JOptionPane.WARNING_MESSAGE);
		 }
	}
	 public static void insert(String StaffID,String Name, String Sex,String Post,
				String Phonenum,String Address,String Kind,String IDCard,String Salary)throws SQLException {
				String sql = "insert into staffid values(?,?,?,?,?,?,?,?,?)";
				Connection c1 =  getconnection();
				PreparedStatement getinsql= c1.prepareStatement(sql);
				getinsql.setString(1, StaffID);
				getinsql.setString(2, Name);		
				getinsql.setString(3, Sex);
				getinsql.setString(4, Post);
				getinsql.setString(5, Phonenum);
				getinsql.setString(6, Address);
				getinsql.setString(7, Kind);
				getinsql.setString(8, IDCard);
				getinsql.setString(9, Salary);
				getinsql.execute();
			}
	 public static void update(String StaffID,String Name, String Sex,String Post,
				String Phonenum,String Address,String Kind,String IDCard,String Salary) throws SQLException{
			String sql = "update staffid set StaffID = ?, name = ?,Sex = ?,Post = ?,PhoneNum = ?,Address = ?,kind = ?,IDCard = ?,Salary = ? where StaffID = ?";
			Connection c1 = getconnection();
			PreparedStatement getinsql = c1.prepareStatement(sql);
			getinsql.setString(1, StaffID);
			getinsql.setString(2, Name);		
			getinsql.setString(3, Sex);
			getinsql.setString(4, Post);
			getinsql.setString(5, Phonenum);
			getinsql.setString(6, Address);
			getinsql.setString(7, Kind);
			getinsql.setString(8, IDCard);
			getinsql.setString(9, Salary);
			getinsql.setString(10, StaffID);
			getinsql.execute();
		}
	 public static void select() throws SQLException{
		    String sql = "select * from staffid";
		    Connection c1 = getconnection();
		    PreparedStatement s = c1.prepareStatement(sql);
		    java.sql.ResultSet rs = s.executeQuery(sql);
		    
		    while(rs.next()){
		      BasicInfor instances = new BasicInfor();
		      instances.staffid = rs.getString("StaffID");
		      instances.name = rs.getString("Name");
		      instances.sex = rs.getString("Sex");
		      instances.post = rs.getString("Post");
			  instances.phonenum = rs.getString("PhoneNum");
			  instances.address = rs.getString("Address");
		      instances.kind = rs.getString("Kind");
		      instances.idcard = rs.getString("IDCard");
		      instances.salary = rs.getString("Salary");
		      init.add(instances);
		    }
	 }
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
