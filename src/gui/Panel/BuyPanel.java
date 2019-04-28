package gui.Panel;

import DAO.StorageBillDAO;
import entity.StorageBill;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class BuyPanel extends AbstractTableModel{
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
	 public static void GUINF() {
	    	try {
	    		javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
	    		//javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
	    		 
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
	  try {
		StorageBillDAO.select_all();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	 
  
  }
  public static BuyPanel instance = new BuyPanel();
  private BuyPanel() {
	  
  }
  public static String []columnNames={"库存编号","库存数量","供货商号"};
  public static Object[][] infor_1=new String[100][14];
  public static JTable table_1=new JTable(infor_1,columnNames);
  public static Object[][] infor_2=new String[100][14];
  public static JTable table_2=new JTable(infor_2,columnNames);
  
  public static int index_1 =  0;
  public static int index_2 = 0 ;
  public static DefaultTableModel model = new DefaultTableModel(infor_1,columnNames){
 	 private static final long serialVersionUID=1L;
 	 public boolean isCellEditable(int row,int column){
 		 return false;
 	 }
  };
  public static JPanel panel_1= new JPanel(new BorderLayout());
  public static JScrollPane jsp_1=new JScrollPane(panel_1);
  public static JPanel panel_2= new JPanel(new BorderLayout());
  public static JScrollPane jsp_2=new JScrollPane(panel_2);
  public static JButton adds = new JButton("增加订单");
  public static JButton delete = new JButton("入库");
  public static JButton modify = new JButton("查询(左表)");
  public static JButton search = new JButton("查询(右表)");
  public static JLabel pos = new JLabel();
  public static JLabel title = new JLabel();
  public static JLabel title_1 = new JLabel("进货员上报单");
  public static JLabel title_2 =new JLabel("实际拟入库单");
  public static JFrame f = new JFrame("购货管理");	  
  public static JPanel   form = new JPanel(new GridLayout(1,2));
  public static JPanel   form_s = new JPanel(new BorderLayout());
  public static JPanel   bt = new JPanel(new BorderLayout());
  public static JPanel   bt_m = new JPanel(new BorderLayout());
  public static JPanel   bt_s = new JPanel(new GridLayout(4,1));
  public static JPanel   lb = new JPanel(new GridLayout(1,2));
  JButton bk = new JButton("返回");
public static void showbutton() {
		 ImageIcon padds = new ImageIcon("image/add.png");	
		 adds.setIcon(padds);
		 adds.setVerticalTextPosition(JButton.BOTTOM);
		 adds.setHorizontalTextPosition(JButton.CENTER);
		 //add.setBounds(10,10,10,10);
		 ImageIcon pdelete = new ImageIcon("image/delete_1.png");	
		 delete.setIcon(pdelete);
		 delete.setVerticalTextPosition(JButton.BOTTOM);
		 delete.setHorizontalTextPosition(JButton.CENTER);
		 //delete.setBounds(10,10,10,10);
		 ImageIcon pmodify = new ImageIcon("image/search.png");	
		 modify.setIcon(pmodify);
		 modify.setVerticalTextPosition(JButton.BOTTOM);
		 modify.setHorizontalTextPosition(JButton.CENTER);
		 ImageIcon psearch = new ImageIcon("image/search.png");	
		 search.setIcon(psearch);
		 search.setVerticalTextPosition(JButton.BOTTOM);
		 search.setHorizontalTextPosition(JButton.CENTER);
		 ImageIcon ppos = new ImageIcon("image/pos3.jpg");	
		 pos.setIcon(ppos);
		 ImageIcon ptitle = new ImageIcon("image/标题.jpg");	
		 title.setIcon(ptitle);
	}
static {
	
}
  public static void showpanel() {
	  form.removeAll();
	  
	  int index_2=0;
	  for(int i = 0 ; i< StorageBillDAO.storagebill.size(); i ++) {
		  infor_2[i][0] = Integer.toString(StorageBillDAO.storagebill.get(i).getStorageID());		   
		  infor_2[i][1]  = Integer.toString(StorageBillDAO.storagebill.get(i).getStoreNum());
		  infor_2[i][2] =  StorageBillDAO.storagebill.get(i).getMerchID();
		  index_2++;
	  }

	  showbutton();
	 	
	  bt.updateUI();
	  f.setSize(700, 600);
	  f.setLocation(100,100);
	  jsp_1=new JScrollPane(table_1);
	  jsp_2=new JScrollPane(table_2);
	  lb.add(title_1);
	  lb.add(title_2);
      form.add(jsp_1);
      form.add(jsp_2);
      form_s.add(form);
      form_s.add(title, BorderLayout.NORTH);
     // form_s.add(lb, BorderLayout.CENTER);
      //bt_s.add(pos);
      bt_s.add(adds);
      bt_s.add(delete);
      bt_s.add(modify);
      bt_s.add(search);      
      bt_m.add(pos,BorderLayout.NORTH);
      bt_m.add(bt_s);
      bt.add(form_s);
      bt.add(bt_m, BorderLayout.WEST);
      f.add(bt);
//      add_l();
//      add_r();
//      modify();
//      search();
//	  table_op_l();
//	  table_op_r();	  
	  f.setVisible(true);		 
	  //f.setResizable(false);
	  //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  JOptionPane.showMessageDialog(f, "左表为进货员上报需要进的货物\n右表为已经入库的货物","提示",JOptionPane.PLAIN_MESSAGE);
     
  }
 static {
	  add_l();
	    add_r();
	    modify();
	    search();
		 table_op_l();
		 table_op_r();	  
 }
/*四个按钮的监听操作*/
 //增加订单（右表）
 public static void add_r() {
	
	  delete.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
						
	    	  String str=JOptionPane.showInputDialog(f,"请输入要入库的订单号\n","添加",JOptionPane.PLAIN_MESSAGE); 
	    	  if(str.length()==0) {
	    		 return;
	    	  }
	    	  int st = Integer.parseInt(str);	 
			            int temp=0;
	            		for(int i=0 ; i<StorageBillDAO.storagebill_s.size();i++) {
	            			if(StorageBillDAO.storagebill_s.get(i).getStorageID()== st) {
	            				  StorageBillDAO.storagebill.add(StorageBillDAO.storagebill_s.get(i));
	            				  infor_2[StorageBillDAO.storagebill.size()-1][0]=Integer.toString(StorageBillDAO.storagebill_s.get(i).getStorageID());	
	            				  infor_2[StorageBillDAO.storagebill.size()-1][1]=Integer.toString(StorageBillDAO.storagebill_s.get(i).getStoreNum());
	            				  infor_2[StorageBillDAO.storagebill.size()-1][2]=StorageBillDAO.storagebill_s.get(i).getMerchID();
	            			      table_2.updateUI();
	            			      temp=i;
	            			      break;
	            			}
	            		}
	            		
	            		  try {
	  						StorageBillDAO.insert(StorageBillDAO.storagebill_s.get(temp).getStorageID(), StorageBillDAO.storagebill_s.get(temp).getStoreNum(), StorageBillDAO.storagebill_s.get(temp).getMerchID());
	  					} catch (SQLException e1) {
	  						// TODO Auto-generated catch block
	  						e1.printStackTrace();
	  					}
	            		  StorageBillDAO.storagebill_s.remove(temp);
	            		if( StorageBillDAO.storagebill_s.size()==1) {
	            			for(int j=0;j<StorageBillDAO.storagebill_s.size();j++) {
	            			 infor_1[j][0]="";
	            			 infor_1[j][1]="";
	            			 infor_1[j][2]="";
	            			 table_1.updateUI();
	            			}
	            		}
	            	    for(int i = 0 ; i < StorageBillDAO.storagebill_s.size();i++) {
	                   	 
	                  	  infor_1[i][0] = Integer.toString(StorageBillDAO.storagebill_s.get(i).getStorageID());		   
	              		  infor_1[i][1]  = Integer.toString(StorageBillDAO.storagebill_s.get(i).getStoreNum());
	              		  infor_1[i][2] = StorageBillDAO.storagebill_s.get(i).getMerchID();
	              		  infor_1[StorageBillDAO.storagebill_s.size()][0]= infor_1[StorageBillDAO.storagebill_s.size()][1]= infor_1[StorageBillDAO.storagebill_s.size()][2]="";
	              		  table_1.updateUI();
	                    }
	            	 
				}
			
		
	  
	  });
  }
  //增加订单（左表）
  public static void add_l() {
	  adds.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
         	  JDialog dia = new JDialog(f);
        	  dia.setSize(220, 240);
        	  dia.setLocation(200, 200);
        	  dia.setTitle("添加订单");
        	  JLabel supers = new JLabel();
        	  ImageIcon psupers = new ImageIcon("D:/super.jpg");
        	  supers.setIcon(psupers);
        	  JButton submit = new JButton("提交");
        	  JLabel s_id = new JLabel("StorageID");
        	  JLabel s_num = new JLabel("StoreNum");
        	  JLabel m_id = new JLabel("MerchID");
        	  JTextField ts_id = new JTextField();
        	  JTextField ts_num = new JTextField();
        	  JTextField tm_id = new JTextField();
        	  JPanel panel = new JPanel(new GridLayout(3,2));
        	  JPanel panel_s = new JPanel(new BorderLayout());
        	  panel.add(s_id);            	              	 
       	      panel.add(ts_id);
       	      panel.add(s_num);
        	  panel.add(ts_num);
        	  panel.add(m_id);
        	  panel.add(tm_id);           	  
        	  panel_s.add(panel);
        	  panel_s.add(supers,BorderLayout.NORTH);
        	  panel_s.add(submit, BorderLayout.SOUTH);
        	  dia.add(panel_s);
        	  dia.setVisible(true);
        	  //dia.setResizable(false);
        	  StorageBill store = new StorageBill();
        	  
        	  submit.addActionListener(new ActionListener() {
                
        		 
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					  store.setStorageID(Integer.parseInt(ts_id.getText()));
		        	  store.setStoreNum(Integer.parseInt(ts_num.getText()));
		        	  store.setMerchID(tm_id.getText());
					
					 StorageBillDAO.storagebill_s.add(store);
					
					infor_1[StorageBillDAO.storagebill_s.size()-1][0] = Integer.toString(StorageBillDAO.storagebill_s.get(StorageBillDAO.storagebill_s.size()-1).getStorageID());		   
	        		infor_1[StorageBillDAO.storagebill_s.size()-1][1]  = Integer.toString(StorageBillDAO.storagebill_s.get(StorageBillDAO.storagebill_s.size()-1).getStoreNum());
	        		infor_1[StorageBillDAO.storagebill_s.size()-1][2] = StorageBillDAO.storagebill_s.get(StorageBillDAO.storagebill_s.size()-1).getMerchID();
	              
	        		table_1.updateUI();
	        		dia.setVisible(false);
				}
        		  
        	  });
		}
	  
	  });
  }
  //查询订单（右表）
  public static void search() {
	  search.addActionListener(new ActionListener() {
			 
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				  String str=JOptionPane.showInputDialog(f,"请输入要查找的订单号\n","查找",JOptionPane.PLAIN_MESSAGE); 
				  JDialog dia = new JDialog(f);
		    	  dia.setSize(220, 240);
		    	  dia.setLocation(200, 200);
		    	  dia.setTitle("查询信息");
		    	  JPanel panel_s = new JPanel(new BorderLayout());
		    	  JPanel panel = new JPanel(new GridLayout(3,2));
		    	  JLabel supers = new JLabel();
	        	  ImageIcon psupers = new ImageIcon("D:/super.jpg");
	        	  supers.setIcon(psupers);
				int st = Integer.parseInt(str);
				int i = 0;
				for( i=0 ; i<StorageBillDAO.storagebill_s.size();i++) {
					if(StorageBillDAO.storagebill_s.get(i).getStorageID()== st) {
						 JButton back = new JButton("返回");
				    	  JLabel s_id = new JLabel("StorageID");
				    	  JLabel s_num = new JLabel("StoreNum");
				    	  JLabel m_id = new JLabel("MerchID");
				    	  JLabel ts_id = new  JLabel(Integer.toString(StorageBillDAO.storagebill_s.get(i).getStorageID()));
				    	  JLabel ts_num = new  JLabel(Integer.toString(StorageBillDAO.storagebill_s.get(i).getStoreNum()));
				    	  JLabel tm_id = new  JLabel(StorageBillDAO.storagebill_s.get(i).getMerchID());
				    	  panel.add(s_id);            	              	 
		           	      panel.add(ts_id);
		           	      panel.add(s_num);
		            	  panel.add(ts_num);
		            	  panel.add(m_id);
		            	  panel.add(tm_id);           	  
		            	  panel_s.add(panel);
		            	  panel_s.add(supers,BorderLayout.NORTH);
		            	  panel_s.add(back, BorderLayout.SOUTH);
		            	  dia.add(panel_s);
		            	  dia.setVisible(true);
		            	  dia.setResizable(false);
		            	  back.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								dia.setVisible(false);
							}
		            		  
		            	  });
		            	  break;
					}
				}
				if(i==StorageBillDAO.storagebill_s.size()) {
					JOptionPane.showMessageDialog(f, "无此订单！", "提示",JOptionPane.WARNING_MESSAGE);  
				}
				
			}
			  
		  });
  }
  //查询订单（左表）
  public  static void modify() {
	  modify.addActionListener(new ActionListener() {
		 
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			  String str=JOptionPane.showInputDialog(f,"请输入要查找的订单号\n","查找",JOptionPane.PLAIN_MESSAGE); 
			  JDialog dia = new JDialog(f);
	    	  dia.setSize(220, 240);
	    	  dia.setLocation(200, 200);
	    	  dia.setTitle("查询信息");
	    	  JPanel panel_s = new JPanel(new BorderLayout());
	    	  JPanel panel = new JPanel(new GridLayout(3,2));
	    	  JLabel supers = new JLabel();
        	  ImageIcon psupers = new ImageIcon("D:/super.jpg");
        	  supers.setIcon(psupers);
			int st = Integer.parseInt(str);
			int i = 0;
			for( i=0 ; i<StorageBillDAO.storagebill.size();i++) {
				if(StorageBillDAO.storagebill.get(i).getStorageID()== st) {
					 JButton back = new JButton("返回");
			    	  JLabel s_id = new JLabel("StorageID");
			    	  JLabel s_num = new JLabel("StoreNum");
			    	  JLabel m_id = new JLabel("MerchID");
			    	  JLabel ts_id = new  JLabel(Integer.toString(StorageBillDAO.storagebill.get(i).getStorageID()));
			    	  JLabel ts_num = new  JLabel(Integer.toString(StorageBillDAO.storagebill.get(i).getStoreNum()));
			    	  JLabel tm_id = new  JLabel(StorageBillDAO.storagebill.get(i).getMerchID());
			    	  panel.add(s_id);            	              	 
	           	      panel.add(ts_id);
	           	      panel.add(s_num);
	            	  panel.add(ts_num);
	            	  panel.add(m_id);
	            	  panel.add(tm_id);           	  
	            	  panel_s.add(panel);
	            	  panel_s.add(supers,BorderLayout.NORTH);
	            	  panel_s.add(back, BorderLayout.SOUTH);
	            	  dia.add(panel_s);
	            	  dia.setVisible(true);
	            	  dia.setResizable(false);
	            	  back.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							dia.setVisible(false);
						}
	            		  
	            	  });
	            	  break;
				}
				//
			}
			if(i==StorageBillDAO.storagebill.size()) {
				JOptionPane.showMessageDialog(f, "无此订单！", "提示",JOptionPane.WARNING_MESSAGE);  
			}
			
		}
		  
	  });
			  
  }
  //对左表的各种操作
  public static void table_op_l() {
	  //GUINF();
	  table_1.addMouseListener(new java.awt.event.MouseAdapter() {
		  public void mouseClicked(MouseEvent e) {
			  
			  int row = table_1.rowAtPoint(e.getPoint());  
              int col = table_1.columnAtPoint(e.getPoint());
              Object value=table_1.getValueAt(row, col); 
             // System.out.println(value);
              if(row>StorageBillDAO.storagebill_s.size()-1) {
        		  JOptionPane.showMessageDialog(f, "选择了没有内容的行!", "提示",JOptionPane.WARNING_MESSAGE); 
            	  return;
        	  }
              Object[] options ={ "删除", "修改"};  //自定义按钮上的文字
              int rt= JOptionPane.showOptionDialog(f, "进行下边哪一步操作？", "提示",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
         
              //返回值为0或1
              if(rt==0) {
            	
              if(StorageBillDAO.storagebill_s.size()==0) {
            	  JOptionPane.showMessageDialog(f, "表空!", "提示",JOptionPane.WARNING_MESSAGE); 
            	  return;
              }
              if(StorageBillDAO.storagebill_s.size()==1) {
            	  for( int j = 0 ; j < StorageBillDAO.storagebill_s.size();j++) 
        		  infor_1[j][0]=infor_1[j][1]=infor_1[j][2]="";
        		 
        	  }
                  
            	  StorageBillDAO.storagebill_s.remove(row);
                  int i;
              for( i = 0 ; i < StorageBillDAO.storagebill_s.size();i++) {
            	 
            	  infor_1[i][0] = Integer.toString(StorageBillDAO.storagebill_s.get(i).getStorageID());		   
        		  infor_1[i][1]  = Integer.toString(StorageBillDAO.storagebill_s.get(i).getStoreNum());
        		  infor_1[i][2] = StorageBillDAO.storagebill_s.get(i).getMerchID();
        		  infor_1[StorageBillDAO.storagebill_s.size()][0]= infor_1[StorageBillDAO.storagebill_s.size()][1]= infor_1[StorageBillDAO.storagebill_s.size()][2]="";
        		  table_1.updateUI();
              }
                  
              table_1.updateUI();
              
		  }
              JLabel supers = new JLabel();
        	  ImageIcon psupers = new ImageIcon("D:/super.jpg");
        	  supers.setIcon(psupers);
              if(rt==1) {
            	  JDialog dia = new JDialog(f);
            	  dia.setSize(220, 240);
            	  dia.setLocation(200, 200);
            	  dia.setTitle("修改");
            	  JButton submit = new JButton("提交修改");
            	  JLabel s_id = new JLabel("StorageID");
            	  JLabel s_num = new JLabel("StoreNum");
            	  JLabel m_id = new JLabel("MerchID");
            	  JTextField ts_id = new JTextField();
            	  JTextField ts_num = new JTextField();
            	  JTextField tm_id = new JTextField();
            	  JPanel panel = new JPanel(new GridLayout(3,2));
            	  JPanel panel_s = new JPanel(new BorderLayout());
            	  panel.add(s_id);            	              	 
           	      panel.add(ts_id);
           	      panel.add(s_num);
            	  panel.add(ts_num);
            	  panel.add(m_id);
            	  panel.add(tm_id);           	  
            	  panel_s.add(panel);
            	  panel_s.add(supers,BorderLayout.NORTH);
            	  panel_s.add(submit, BorderLayout.SOUTH);
            	  dia.add(panel_s);
            	  dia.setVisible(true);
            	  dia.setResizable(false);
            	  submit.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						StorageBillDAO.storagebill_s.get(row).setStorageID(Integer.parseInt(ts_id.getText()));
						StorageBillDAO.storagebill_s.get(row).setStoreNum(Integer.parseInt(ts_num.getText()));
						StorageBillDAO.storagebill_s.get(row).setMerchID(ts_id.getText());
						infor_1[row][0] = Integer.toString(StorageBillDAO.storagebill_s.get(row).getStorageID());		   
		        		infor_1[row][1]  = Integer.toString(StorageBillDAO.storagebill_s.get(row).getStoreNum());
		        		infor_1[row][2] = StorageBillDAO.storagebill_s.get(row).getMerchID();
		        		table_1.updateUI();
		        		dia.setVisible(false);
					}
            		  
            	  });
              }
		  }
	  });
  }
  //对右表的各种操作
  public static void table_op_r() {
	  //GUINF();
	  table_2.addMouseListener(new java.awt.event.MouseAdapter() {
		  public void mouseClicked(MouseEvent e) {
			  
			  int row = table_2.rowAtPoint(e.getPoint());  
              int col = table_2.columnAtPoint(e.getPoint());
              Object value=table_2.getValueAt(row, col); 
             // System.out.println(value);
              if(row>StorageBillDAO.storagebill.size()) {
        		  JOptionPane.showMessageDialog(f, "选择了没有内容的行!", "提示",JOptionPane.WARNING_MESSAGE); 
            	  return;
        	  }
              Object[] options ={ "删除", "修改"};  //自定义按钮上的文字
              int rt= JOptionPane.showOptionDialog(f, "进行下边哪一步操作？", "提示",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
         
              //返回值为0或1
              if(rt==0) {
            	
              if(StorageBillDAO.storagebill.size()==0) {
            	  JOptionPane.showMessageDialog(f, "表空!", "提示",JOptionPane.WARNING_MESSAGE); 
            	  return;
              }
              if(StorageBillDAO.storagebill.size()==1) {
            	  for( int j = 0 ; j < StorageBillDAO.storagebill.size();j++) 
        		  infor_2[j][0]=infor_2[j][1]=infor_2[j][2]="";
        		 
        	  }
              try {
				StorageBillDAO.delete(StorageBillDAO.storagebill.get(row).getStorageID());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            	  StorageBillDAO.storagebill.remove(row);
            	  //
                  int i;
              for( i = 0 ; i < StorageBillDAO.storagebill.size();i++) {
            	 
            	  infor_2[i][0] = Integer.toString(StorageBillDAO.storagebill.get(i).getStorageID());		   
        		  infor_2[i][1]  = Integer.toString(StorageBillDAO.storagebill.get(i).getStoreNum());
        		  infor_2[i][2] = StorageBillDAO.storagebill.get(i).getMerchID();
        		 
        		  infor_2[StorageBillDAO.storagebill.size()][0]= infor_2[StorageBillDAO.storagebill.size()][1]= infor_2[StorageBillDAO.storagebill.size()][2]="";
        		  table_2.updateUI();
              }
                  
              table_2.updateUI();
              
		  }
              JLabel supers = new JLabel();
        	  ImageIcon psupers = new ImageIcon("D:/super.jpg");
        	  supers.setIcon(psupers);
              if(rt==1) {
            	  JDialog dia = new JDialog(f);
            	  dia.setSize(200, 240);
            	  dia.setLocation(200, 200);
            	  dia.setTitle("修改");
            	  JButton submit = new JButton("提交修改");
            	  JLabel s_id = new JLabel("StorageID");
            	  JLabel s_num = new JLabel("StoreNum");
            	  JLabel m_id = new JLabel("MerchID");
            	  JTextField ts_id = new JTextField();
            	  JTextField ts_num = new JTextField();
            	  JTextField tm_id = new JTextField();
            	  JPanel panel = new JPanel(new GridLayout(3,2));
            	  JPanel panel_s = new JPanel(new BorderLayout());
            	  panel.add(s_id);            	              	 
           	      panel.add(ts_id);
           	      panel.add(s_num);
            	  panel.add(ts_num);
            	  panel.add(m_id);
            	  panel.add(tm_id);           	  
            	  panel_s.add(panel);
            	  panel_s.add(supers,BorderLayout.NORTH);
            	  panel_s.add(submit, BorderLayout.SOUTH);
            	  dia.add(panel_s);
            	  dia.setVisible(true);
            	  dia.setResizable(false);
            	  submit.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						StorageBillDAO.storagebill.get(row).setStorageID(Integer.parseInt(ts_id.getText()));
						StorageBillDAO.storagebill.get(row).setStoreNum(Integer.parseInt(ts_num.getText()));
						StorageBillDAO.storagebill.get(row).setMerchID(ts_id.getText());
						infor_2[row][0] = Integer.toString(StorageBillDAO.storagebill.get(row).getStorageID());		   
		        		infor_2[row][1]  = Integer.toString(StorageBillDAO.storagebill.get(row).getStoreNum());
		        		infor_2[row][2] = StorageBillDAO.storagebill.get(row).getMerchID();
		        		table_2.updateUI();
		        		dia.setVisible(false);
					}
            		  
            	  });
              }
		  }
	  });
  }
@Override
public int getRowCount() {
	// TODO Auto-generated method stub
	return 100;
}

@Override
public int getColumnCount() {
	// TODO Auto-generated method stub
	return 3;
}

@Override
public Object getValueAt(int rowIndex, int columnIndex) {
	// TODO Auto-generated method stub
	return null;
}
 public static void main(String[] args) {
	 
	instance.showpanel();
//	 int month = (new Date()).getHours();
//	 System.out.println(new Date()+" "+month);
	
}
}
