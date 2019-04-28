package gui.Panel;

import DAO.StorageBillDAO;
import entity.StorageBill;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class RepertoryPanel {
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
	static {
		GUINM();
		try{
			StorageBillDAO.select_all_();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	//���
	public static RepertoryPanel instance = new RepertoryPanel(); 
	private RepertoryPanel(){}
	
	public static JButton add_ = new JButton("����");
	public static JButton modify_ = new JButton("�޸�");
	public static JButton delete_ = new JButton("ɾ��");
	public static JButton search_ = new JButton("��ѯ");
	
	public static JFrame frame = new JFrame("������");
	
	public static JLabel img = new JLabel();
	
	public static Object[][] obj = new Object[100][3];
	public static String[] head = {"�����","�������","�����̺�"};
	public static JTable table = new JTable(obj,head);
	public static JScrollPane scroll = new JScrollPane(table);
	
	public static JPanel pbut = new JPanel();
	public static JPanel pright = new JPanel();
	
	//�����м�����
	public static int index = 0;
	static {
	
	}
	public static void showButton(){
		ImageIcon iadd = new ImageIcon("");
		add_.setIcon(iadd);
		add_.setVerticalTextPosition(JButton.BOTTOM);
		add_.setHorizontalTextPosition(JButton.CENTER);
		
		ImageIcon isearch = new ImageIcon("");
		search_.setIcon(isearch);
		search_.setVerticalTextPosition(JButton.BOTTOM);
		search_.setHorizontalTextPosition(JButton.CENTER);
		
//		ImageIcon imodify = new ImageIcon("");
//		modify_.setIcon(imodify);
//		modify_.setVerticalTextPosition(JButton.BOTTOM);
//		modify_.setHorizontalTextPosition(JButton.CENTER);
		
		ImageIcon istorage = new ImageIcon("image/storage.png");	
		img.setIcon(istorage);
	}
	public static void showpanel(){
		pright.removeAll();
		
		
		
		//table.updateUI();
		index = 0;
		for(int i = 0;i<StorageBillDAO.storagebill_m.size();i++){			
			obj[i][0] =Integer.toString(StorageBillDAO.storagebill_m.get(i).getStorageID());		   
			obj[i][1] =Integer.toString(StorageBillDAO.storagebill_m.get(i).getStoreNum());
			obj[i][2] =StorageBillDAO.storagebill_m.get(i).getMerchID();		
			index++;
		}
		
		showButton();
		//��ť���
		pbut.setSize(400,200);
		pbut.setLayout(new GridLayout(2,1,3,3));
		pbut.add(add_);
		pbut.add(search_);
//		pbut.add(modify_);
		//�Ҳ����
		pright.setLayout(new GridLayout(2,1,3,3));
		pright.add(img);
		scroll.setSize(300,200);
		pright.add(scroll);
		//frame
		frame.setLayout(new BorderLayout());
		frame.add(pbut,BorderLayout.WEST);
		frame.add(pright);
		frame.setSize(650,600);
		frame.setLocation(300,100);
		frame.setVisible(true);
		//frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}
	static{
		add();
		search();
		table_op();
	}
	
	//���Ӳ���
	public static void add(){
		add_.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				final JDialog dia = new JDialog(frame);
				dia.setSize(220,240);
				dia.setLocation(320,120);
				dia.setTitle("���ӿ��");
				//
				JLabel label = new JLabel();
				ImageIcon label_img = new ImageIcon();
				label.setIcon(label_img);
				//
				JButton submit = new JButton("�ύ");
				//
				JLabel ls_id = new JLabel("StorageID:");
				JLabel ls_num = new JLabel("StoreNum:");
				JLabel lm_id = new JLabel("MerchID:");
				final JTextField ts_id = new JTextField();
				final JTextField ts_num = new JTextField();
				final JTextField tm_id = new JTextField();
				//
				JPanel p = new JPanel(new BorderLayout());
				JPanel pdown = new JPanel(new GridLayout(3,2,2,2));
				pdown.add(ls_id);
				pdown.add(ts_id);
				pdown.add(ls_num);
				pdown.add(ts_num);
				pdown.add(lm_id);
				pdown.add(tm_id);
				p.add(label,BorderLayout.NORTH);
				p.add(pdown);
				p.add(submit,BorderLayout.SOUTH);
				dia.add(p);
				dia.setVisible(true);
				final StorageBill store = new StorageBill();
				
				submit.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						if(ts_id.getText().length() == 0){
//							JDialog jd = new JDialog();
//							jd.setTitle("Error");
//							JLabel l = new JLabel("StorageID ���Բ�����Ϊ��");
//							jd.add(l);
//							jd.pack();
//							jd.setLocation(360,140);
//							jd.setVisible(true);
							JOptionPane.showMessageDialog(frame,"StorageID������Ϊ��","��ʾ",JOptionPane.WARNING_MESSAGE);
						}
						else{
							store.setStorageID(Integer.parseInt(ts_id.getText()));
							store.setStoreNum(Integer.parseInt(ts_num.getText()));
							store.setMerchID(tm_id.getText());
							StorageBillDAO.storagebill.add(store);
							obj[StorageBillDAO.storagebill.size()-1][0] = store.getStorageID();
							obj[StorageBillDAO.storagebill.size()-1][1] = store.getStoreNum();
							obj[StorageBillDAO.storagebill.size()-1][2] = store.getMerchID();
							table.updateUI();
							try {
								StorageBillDAO.insert(store.getStorageID(), store.getStoreNum(), store.getMerchID());
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
	
	//��ѯ����
	public static void search(){
		search_.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String str = JOptionPane.showInputDialog(frame,"������Ҫ���ҵ�StorageID\n","����",JOptionPane.PLAIN_MESSAGE);
//				JDialog dia = new JDialog(frame);
//				dia.setSize(220,240);
//				dia.setLocation(320,120);
//				dia.setTitle("��ѯ��Ϣ");
//				JPanel p = new JPanel(new BorderLayout());
//				JPanel pdown = new JPanel(new GridLayout(3,2));
//				JLabel label = new JLabel();
//				ImageIcon ii = new ImageIcon();
//				label.setIcon(ii);
				if(str.length() == 0){
					JOptionPane.showMessageDialog(frame, "StorageID����Ϊ��","��ʾ",JOptionPane.WARNING_MESSAGE);
				}
				else{
					int i = 0;
					int temp=Integer.parseInt(str);
				for(i = 0;i<StorageBillDAO.storagebill.size();i++){
					if(StorageBillDAO.storagebill.get(i).getStorageID()==temp){
						final JDialog dia = new JDialog(frame);
						dia.setSize(220,240);
						dia.setLocation(320,120);
						dia.setTitle("��ѯ��Ϣ");
						//
						JPanel p = new JPanel(new BorderLayout());
						JPanel pdown = new JPanel(new GridLayout(3,2));
						//
						JLabel label = new JLabel();
						ImageIcon ii = new ImageIcon();
						label.setIcon(ii);
						//
						JButton back = new JButton("����");
						//
				    	JLabel s_id = new JLabel("StorageID");
				    	JLabel s_num = new JLabel("StoreNum");
				    	JLabel m_id = new JLabel("MerchID");
				    	
				    	JLabel ts_id = new  JLabel(Integer.toString(StorageBillDAO.storagebill.get(i).getStorageID()));
				    	JLabel ts_num = new  JLabel(Integer.toString(StorageBillDAO.storagebill.get(i).getStoreNum()));
				    	JLabel tm_id = new  JLabel(StorageBillDAO.storagebill.get(i).getMerchID());
				    	//
				    	pdown.add(s_id);            	              	 
		           	    pdown.add(ts_id);
		           	    pdown.add(s_num);
		            	pdown.add(ts_num);
		            	pdown.add(m_id);
		            	pdown.add(tm_id);           	  
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
				if(i == StorageBillDAO.storagebill.size()){
					JOptionPane.showMessageDialog(frame, "�޴���Ʒ!","��ʾ",JOptionPane.WARNING_MESSAGE);
				}
					
				}
			}
			
		});
	}
	
	//�޸Ĳ���
	public static void table_op(){
		table.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				final int row = table.rowAtPoint(e.getPoint());
				int col = table.columnAtPoint(e.getPoint());
				Object value= table.getValueAt(row, col);
//				System.out.println(value);
//				System.out.println(row);
//				System.out.println(StorageBillDAO.storagebill.size());
				if(row > StorageBillDAO.storagebill.size()-1){//table��0��ʼ����
					JOptionPane.showMessageDialog(frame,"ѡ����û�����ݵ���","��ʾ",JOptionPane.WARNING_MESSAGE);
					return;
				}
				Object[] options = {"ɾ��","�޸�"};
				int rt = JOptionPane.showOptionDialog(frame, "������һ������", "��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				//System.out.println(rt);
				if(rt == 0){
					if(StorageBillDAO.storagebill.size() == 0){
						JOptionPane.showMessageDialog(frame, "��Ϊ���޷�ɾ��","��ʾ",JOptionPane.WARNING_MESSAGE);
						return;
					}
//					if(StorageBillDAO.storagebill.size() == 1){
//						for(int j =0;j<StorageBillDAO.storagebill.size();j++){
//							obj[j][0]=obj[j][1]=obj[j][2] = "";
//						}
//					}
					try {
					StorageBillDAO.delete(StorageBillDAO.storagebill.get(row).getStorageID());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					StorageBillDAO.storagebill.remove(row);
					//System.out.println(StorageBillDAO.storagebill.size());
					int i;
					for(i=0;i<StorageBillDAO.storagebill.size();i++){
						obj[i][0] = StorageBillDAO.storagebill.get(i).getStorageID();
						obj[i][1] = StorageBillDAO.storagebill.get(i).getStoreNum();
						obj[i][2] = StorageBillDAO.storagebill.get(i).getMerchID();
					}
					obj[i][0]=obj[i][1]=obj[i][2] = "";
					table.updateUI();
				}
				if(rt == 1){
					final JDialog dia = new JDialog(frame);
					dia.setSize(220,240);
					dia.setLocation(320,120);
					dia.setTitle("�޸���Ϣ");
					//ͼƬ
					JLabel label = new JLabel();
					ImageIcon ii = new ImageIcon("");
					label.setIcon(ii);
					//��ť
					JButton submit = new JButton("�ύ�޸�");
					//Label and text
					JLabel s_id =new JLabel("StorageID:");
					JLabel s_num =new JLabel("StorageNum:");
					JLabel m_id =new JLabel("MerchID:");
					final JTextField ts_id = new JTextField();
					final JTextField ts_num = new JTextField();
					final JTextField tm_id = new JTextField();
					//Panel
					JPanel p = new JPanel(new BorderLayout());
					JPanel pdown = new JPanel(new GridLayout(3,2));
					pdown.add(s_id);
					pdown.add(ts_id);
					pdown.add(s_num);
					pdown.add(ts_num);
					pdown.add(m_id);
					pdown.add(tm_id);
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
							if(ts_id.getText().length() == 0){
								JOptionPane.showMessageDialog(frame, "StorageID������Ϊ��","��ʾ",JOptionPane.WARNING_MESSAGE);
							}
							else{
								try {
									StorageBillDAO.update(Integer.parseInt(ts_id.getText()),Integer.parseInt(ts_num.getText()),tm_id.getText(), StorageBillDAO.storagebill.get(row).getStorageID());
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								StorageBillDAO.storagebill.get(row).setStorageID(Integer.parseInt(ts_id.getText()));
								StorageBillDAO.storagebill.get(row).setStoreNum(Integer.parseInt(ts_num.getText()));
								StorageBillDAO.storagebill.get(row).setMerchID(tm_id.getText());
								obj[row][0] = StorageBillDAO.storagebill.get(row).getStorageID();
								obj[row][1] = StorageBillDAO.storagebill.get(row).getStoreNum();
								obj[row][2] = StorageBillDAO.storagebill.get(row).getMerchID();
								table.updateUI();
								dia.setVisible(false);
							}
						}
	            		
	            	});
				}
				
			}
		});
	}
	public static void main(String[] args) {
		GUINM();
		showpanel();
	}

}
