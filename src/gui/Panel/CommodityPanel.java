package gui.Panel;



import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CommodityPanel {

	public static JPanel jp1 = new JPanel();
	public static JPanel jp2 = new JPanel();
	public static JFrame f = new JFrame("商品信息管理");
	public static JTable jt = new JTable();
	public static JButton select = new JButton("查询");
	public static JButton add = new JButton("添加");
	public static JButton delete = new JButton("删除");
	public static JButton refresh = new JButton("刷新");
	public void showpanel() {
		f.remove(jt);
		f.repaint();
		Mysql mysql = new Mysql();
		mysql.selectall();

		
		DefaultTableModel dtm1 = new DefaultTableModel(mysql.rows, mysql.columnHeads);
		jt = new JTable(dtm1) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JScrollPane jsp = new JScrollPane(jt);

		jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jt.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getClickCount() == 2) {
					Point p = e.getPoint();
					int row = CommodityPanel.jt.rowAtPoint(p);
					int column = CommodityPanel.jt.columnAtPoint(p);
					int countr = jt.getSelectedRow();
					int countc = jt.getSelectedColumn();
					String getString = jt.getValueAt(countr, countc).toString();
					//System.out.println(getString);
					String getcolumnhead = mysql.columnHeads.get(countc).toString();
					//System.out.println(getcolumnhead);
					String getid = jt.getValueAt(countr, 0).toString();
					//System.out.println(getid);
					JFrame jfm = new JFrame("修改页面");
					JLabel jl1 = new JLabel("商品编号:"+getid);
					JLabel jl2 = new JLabel(getcolumnhead+":"+getString);
					JLabel jl3 = new JLabel("修改为:");
					JTextField jtf = new JTextField(null);
					JButton jb1 = new JButton("确认");
					JButton jb2 = new JButton("取消");
					jfm.add(jb1);jfm.add(jb2);jfm.add(jtf);jfm.add(jl3);jfm.add(jl2);jfm.add(jl1);
					jl1.setBounds(70,10,150,30);
					jl2.setBounds(70,40,150,30);
					jl3.setBounds(70, 70, 50, 30);
					jtf.setBounds(120, 70, 100, 30);
					jb1.setBounds(70, 110, 60, 30);
					jb2.setBounds(160, 110, 60, 30);
					jb1.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							Mysql ms2 = new Mysql();
							String s =jtf.getText();
							ms2.modify(getid, getcolumnhead, s);
							ms2.selectall();
							DefaultTableModel dtm2 = (DefaultTableModel) CommodityPanel.jt.getModel();// 获取表格模型
							dtm2.setDataVector(ms2.rows, ms2.columnHeads);// 设置新内容
							dtm2.fireTableStructureChanged();
							ms2.close();
							jtf.setText(null);
							jfm.dispose();
						}
					});
					jb2.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							jtf.setText(null);
							jfm.dispose();
						}
					});
					
					jfm.setLayout(null);
					jfm.setSize(300, 200);
					jfm.setLocationRelativeTo(null);
					jfm.setVisible(true);
				}

			}
		});

		jsp.setPreferredSize(new Dimension(495, 550));
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		select.setBounds(0, 0, 100, 50);
		add.setBounds(0, 50, 100, 50);
		delete.setBounds(0, 100, 100, 50);
		refresh.setBounds(0, 150, 100, 50);

		refresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Mysql ms2 = new Mysql();
				ms2.selectall();
				DefaultTableModel dtm2 = (DefaultTableModel) jt.getModel();// 获取表格模型
				dtm2.setDataVector(ms2.rows, ms2.columnHeads);// 设置新内容
				dtm2.fireTableStructureChanged();
				ms2.close();
				// jt.updateUI();
			}
		});
		select.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SelectPanel sp = new SelectPanel();
				select.setEnabled(false);
			}
		});
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddPanel ap = new AddPanel();
				JOptionPane p1 = new JOptionPane();
				p1.showMessageDialog(null, "类型请从食品，商品，日用品中选择");
				add.setEnabled(false);
			}
		});
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DeletePanel dp = new DeletePanel();
				delete.setEnabled(false);
			}
		});
		jp1.add(select);
		jp1.add(add);
		jp1.add(delete);
		//jp1.add(refresh);
		jp1.setBounds(0, 0, 105, 600);
		jp1.setLayout(null);
		jp1.setVisible(true);

		jp2.add(jsp);
		jp2.setBounds(105, 0, 530, 600);
		jp2.setVisible(true);

		f.add(jp1);
		f.add(jp2);
		f.setLayout(null);
		f.addWindowListener(new Mywindowlistener());
		f.setSize(650, 600);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setResizable(false);
		mysql.close();
	}
}

class Mysql {
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String DB_URL = "jdbc:oracle:thin:@188.131.140.28:1521:orcl";
	static final String USER = "super";
	static final String PASSWORD = "594SHENG";
	static Connection conn = null;
	static Statement stmt = null;
	static PreparedStatement pstm = null;
	Vector rows = new Vector();
	Vector columnHeads = new Vector();

	Mysql() {
		try {
			Class.forName(JDBC_DRIVER);
			//System.out.println("连接数据库...");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			//System.out.println(" 实例化Statement对象...");
			stmt = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close() {
		try {
			pstm.close();
			stmt.close();
			conn.close();
			//System.out.println("Bye!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectall() {
		String sql = "select * from merch order by 商品编号";
		try {
			pstm = conn.prepareStatement(sql);
			ResultSet rs = null;
			rs = pstm.executeQuery();
			ResultSetMetaData rsmd = null;
			rsmd = rs.getMetaData();
			while (rs.next()) {
				rows.addElement(getNextRow(rs, rsmd));
			}
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				columnHeads.addElement(rsmd.getColumnName(i));
			}
		} catch (SQLException sqle) {
		}
	}

	public void selectmerchid(String merch_id) {
		String sql = "select * from merch where 商品编号=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, merch_id);
			ResultSet rs = null;
			rs = pstm.executeQuery();
			ResultSetMetaData rsmd = null;
			rsmd = rs.getMetaData();
			while (rs.next()) {
				rows.addElement(getNextRow(rs, rsmd));
			}
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				columnHeads.addElement(rsmd.getColumnName(i));
			}
		} catch (SQLException sqle) {
		}
	}

	public void selectproid(String pro_id) {
		String sql = "select * from merch where 供货商号=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, pro_id);
			ResultSet rs = null;
			rs = pstm.executeQuery();
			ResultSetMetaData rsmd = null;
			rsmd = rs.getMetaData();
			while (rs.next()) {
				rows.addElement(getNextRow(rs, rsmd));
			}
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				columnHeads.addElement(rsmd.getColumnName(i));
			}
		} catch (SQLException sqle) {
		}
	}

	public void selectname(String name) {
		String sql = "select * from merch where 名称=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			ResultSet rs = null;
			rs = pstm.executeQuery();
			ResultSetMetaData rsmd = null;
			rsmd = rs.getMetaData();
			while (rs.next()) {
				rows.addElement(getNextRow(rs, rsmd));
			}
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				columnHeads.addElement(rsmd.getColumnName(i));
			}
		} catch (SQLException sqle) {
		}
	}

	public void selectkind(String kind) {
		String sql = "select * from merch where 类型=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, kind);
			ResultSet rs = null;
			rs = pstm.executeQuery();
			ResultSetMetaData rsmd = null;
			rsmd = rs.getMetaData();
			while (rs.next()) {
				rows.addElement(getNextRow(rs, rsmd));
			}
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				columnHeads.addElement(rsmd.getColumnName(i));
			}
		} catch (SQLException sqle) {
		}
	}

	public void selectbar(String bar) {
		String sql = "select * from merch where 商品条码=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, bar);
			ResultSet rs = null;
			rs = pstm.executeQuery();
			ResultSetMetaData rsmd = null;
			rsmd = rs.getMetaData();
			while (rs.next()) {
				rows.addElement(getNextRow(rs, rsmd));
			}
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				columnHeads.addElement(rsmd.getColumnName(i));
			}
		} catch (SQLException sqle) {
		}
	}

	public void selectprovider(String name) {
		String sql = "select * from merch where 生产商=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			ResultSet rs = null;
			rs = pstm.executeQuery();
			ResultSetMetaData rsmd = null;
			rsmd = rs.getMetaData();
			while (rs.next()) {
				rows.addElement(getNextRow(rs, rsmd));
			}
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				columnHeads.addElement(rsmd.getColumnName(i));
			}
		} catch (SQLException sqle) {
		}
	}

	public int add(String merch_id, String pro_id, String name, String kind, String bar, String purprice,
			String selprice, String manu, String measure, String shel, String pro_day) {
		int i = 0;
		String sql = "insert into merch (商品编号,供货商号,名称,类型,商品条码,进价,售价,生产商,计量单位,保质期,生产日期) values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, merch_id);
			pstm.setString(2, pro_id);
			pstm.setString(3, name);
			pstm.setString(4, kind);
			pstm.setString(5, bar);
			pstm.setString(6, purprice);
			pstm.setString(7, selprice);
			pstm.setString(8, manu);
			pstm.setString(9, measure);
			pstm.setString(10, shel);
			pstm.setString(11, pro_day);
			i = pstm.executeUpdate();
		} catch (SQLException sqle) {
		}
		return i;
	}

	public int delete(String merch_id) {
		int j = 0;
		String sql = "delete from merch where 商品编号=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, merch_id);
			j = pstm.executeUpdate();

		} catch (SQLException sqle) {
		}
		return j;
	}

	public void modify(String merch_id,String columnhead,String newx)
	{
		String sql = "update merch set "+columnhead+"="+"'"+newx+"'"+"where 商品编号="+"'"+merch_id+"'";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.execute();			
		} catch (SQLException sqle) {
		}
	}
	public static Vector getNextRow(ResultSet rs, ResultSetMetaData rsmd) throws SQLException {
		Vector currentRow = new Vector();
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			currentRow.addElement(rs.getString(i));
		}
		return currentRow;
	}
}

class Mywindowlistener implements WindowListener {

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		MainPanel.commodity.setEnabled(true);
		CommodityPanel.jp2.removeAll();
	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
class Addlistener implements WindowListener {

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		CommodityPanel.add.setEnabled(true);
	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
class Deletelistener implements WindowListener {

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		CommodityPanel.delete.setEnabled(true);
	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
class Selectlistener implements WindowListener {

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		CommodityPanel.select.setEnabled(true);
	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}

class SelectPanel {
	public static JPanel spl = new JPanel();
	public static JTextField jtf = new JTextField();
	public static JButton s1 = new JButton("商品编号查询");
	public static JButton s2 = new JButton("供货商号查询");
	public static JButton s3 = new JButton("名称查询");
	public static JButton s4 = new JButton("类型查询");
	public static JButton s5 = new JButton("商品条码查询");
	public static JButton s6 = new JButton("生产商查询");
	// JButton s7 = new JButton("即将过期查询");
	public static JButton quit = new JButton("返回");

	SelectPanel() {
		jtf.setBounds(0, 0, 105, 50);
		s1.setBounds(0, 50, 105, 50);
		s2.setBounds(0, 100, 105, 50);
		s3.setBounds(0, 150, 105, 50);
		s4.setBounds(0, 200, 105, 50);
		s5.setBounds(0, 250, 105, 50);
		s6.setBounds(0, 300, 105, 50);
		// s7.setBounds(0, 350, 105, 50);
		quit.setBounds(0, 350, 105, 50);

		
		

		spl.setBounds(0, 0, 105, 600);
		spl.setLayout(null);
		spl.setVisible(true);
		spl.add(s1);
		spl.add(s2);
		spl.add(s3);
		spl.add(s4);
		spl.add(s5);
		spl.add(s6);
		// spl.add(s7);
		spl.add(quit);
		spl.add(jtf);
		CommodityPanel.f.remove(CommodityPanel.jp1);
		CommodityPanel.f.add(spl);
		CommodityPanel.f.repaint();
	}
	static {
		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CommodityPanel.f.remove(spl);
				CommodityPanel.f.add(CommodityPanel.jp1);
				CommodityPanel.f.repaint();
				CommodityPanel.select.setEnabled(true);
				Mysql ms2 = new Mysql();
				ms2.selectall();
				DefaultTableModel dtm2 = (DefaultTableModel) CommodityPanel.jt.getModel();// 获取表格模型
				dtm2.setDataVector(ms2.rows, ms2.columnHeads);// 设置新内容
				//System.out.println(ms2.columnHeads.size());
				dtm2.fireTableStructureChanged();
				ms2.close();
			}

		});
		s1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Mysql ms2 = new Mysql();
				ms2.selectmerchid(jtf.getText());
				DefaultTableModel dtm2 = (DefaultTableModel) CommodityPanel.jt.getModel();// 获取表格模型
				dtm2.setDataVector(ms2.rows, ms2.columnHeads);// 设置新内容
				dtm2.fireTableStructureChanged();
				ms2.close();
				jtf.setText(null);
			}
		});
		s2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Mysql ms2 = new Mysql();
				ms2.selectproid(jtf.getText());
				DefaultTableModel dtm2 = (DefaultTableModel) CommodityPanel.jt.getModel();// 获取表格模型
				dtm2.setDataVector(ms2.rows, ms2.columnHeads);// 设置新内容
				dtm2.fireTableStructureChanged();
				ms2.close();
				jtf.setText(null);
			}
		});
		s3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Mysql ms2 = new Mysql();
				ms2.selectname(jtf.getText());
				DefaultTableModel dtm2 = (DefaultTableModel) CommodityPanel.jt.getModel();// 获取表格模型
				dtm2.setDataVector(ms2.rows, ms2.columnHeads);// 设置新内容
				dtm2.fireTableStructureChanged();
				ms2.close();
				jtf.setText(null);
			}
		});
		s4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Mysql ms2 = new Mysql();
				ms2.selectkind(jtf.getText());
				DefaultTableModel dtm2 = (DefaultTableModel) CommodityPanel.jt.getModel();// 获取表格模型
				dtm2.setDataVector(ms2.rows, ms2.columnHeads);// 设置新内容
				dtm2.fireTableStructureChanged();
				ms2.close();
				jtf.setText(null);
			}
		});
		s5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Mysql ms2 = new Mysql();
				ms2.selectbar(jtf.getText());
				DefaultTableModel dtm2 = (DefaultTableModel) CommodityPanel.jt.getModel();// 获取表格模型
				dtm2.setDataVector(ms2.rows, ms2.columnHeads);// 设置新内容
				dtm2.fireTableStructureChanged();
				ms2.close();
				jtf.setText(null);
			}
		});
		s6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Mysql ms2 = new Mysql();
				ms2.selectprovider(jtf.getText());
				DefaultTableModel dtm2 = (DefaultTableModel) CommodityPanel.jt.getModel();// 获取表格模型
				dtm2.setDataVector(ms2.rows, ms2.columnHeads);// 设置新内容
				dtm2.fireTableStructureChanged();
				ms2.close();
				jtf.setText(null);
			}
		});
	}
}

class AddPanel {
	public static JFrame jm = new JFrame("添加商品信息");
	public static JButton yes = new JButton("确认");
	public static JButton no = new JButton("取消");
	public static JLabel merid = new JLabel("商品编号:");
	public static JLabel proid = new JLabel("供货商号:");
	public static JLabel name = new JLabel("名称:");
	public static JLabel kind = new JLabel("类型:");
	public static JLabel barcode = new JLabel("商品条码:");
	public static JLabel purprice = new JLabel("进价:");
	public static JLabel selprice = new JLabel("售价:");
	public static JLabel manu = new JLabel("生产商:");
	public static JLabel measure = new JLabel("计量单位:");
	public static JLabel shel = new JLabel("保质期:");
	public static JLabel pd = new JLabel("生产日期:");
	public static JTextField meridjtf = new JTextField(null);
	public static JTextField proidjtf = new JTextField(null);
	public static JTextField namejtf = new JTextField(null);
	public static JTextField kindjtf = new JTextField(null);
	public static JTextField barcodejtf = new JTextField(null);
	public static JTextField purpricejtf = new JTextField(null);
	public static JTextField selpricejtf = new JTextField(null);
	public static JTextField manujtf = new JTextField(null);
	public static JTextField measurejtf = new JTextField(null);
	public static JTextField sheljtf = new JTextField(null);
	public static JTextField pdjtf = new JTextField(null);

	AddPanel() {
		jm.add(yes);
		jm.add(no);
		jm.add(merid);
		jm.add(proid);
		jm.add(name);
		jm.add(kind);
		jm.add(barcode);
		jm.add(purprice);
		jm.add(selprice);
		jm.add(manu);
		jm.add(measure);
		jm.add(shel);
		jm.add(pd);
		jm.add(meridjtf);
		jm.add(proidjtf);
		jm.add(namejtf);
		jm.add(kindjtf);
		jm.add(barcodejtf);
		jm.add(purpricejtf);
		jm.add(selpricejtf);
		jm.add(manujtf);
		jm.add(sheljtf);
		jm.add(pdjtf);
		jm.add(measurejtf);

		merid.setBounds(120, 50, 100, 35);
		meridjtf.setBounds(220, 50, 200, 30);
		proid.setBounds(120, 85, 100, 35);
		proidjtf.setBounds(220, 85, 200, 30);
		name.setBounds(120, 120, 100, 35);
		namejtf.setBounds(220, 120, 200, 30);
		kind.setBounds(120, 155, 100, 35);
		kindjtf.setBounds(220, 155, 200, 30);
		barcode.setBounds(120, 190, 100, 35);
		barcodejtf.setBounds(220, 190, 200, 30);
		purprice.setBounds(120, 225, 100, 35);
		purpricejtf.setBounds(220, 225, 200, 30);
		selprice.setBounds(120, 260, 100, 35);
		selpricejtf.setBounds(220, 260, 200, 30);
		manu.setBounds(120, 295, 100, 35);
		manujtf.setBounds(220, 295, 200, 30);
		measure.setBounds(120, 335, 100, 35);
		measurejtf.setBounds(220, 335, 200, 30);
		shel.setBounds(120, 370, 100, 35);
		sheljtf.setBounds(220, 370, 200, 30);
		pd.setBounds(120, 405, 100, 35);
		pdjtf.setBounds(220, 405, 200, 30);
		yes.setBounds(170, 465, 70, 35);
		no.setBounds(300, 465, 70, 35);


		jm.addWindowListener(new Addlistener());
		jm.setLayout(null);
		jm.setSize(550, 600);
		jm.setLocationRelativeTo(null);
		jm.setVisible(true);
	}
	static {
		yes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Mysql ms2 = new Mysql();
				int x = 0;
				String s = meridjtf.getText();
				if (s.length() == 0) {
					JOptionPane pane1 = new JOptionPane();
					pane1.showMessageDialog(jm, "商品编号不能为空");
					meridjtf.setText(null);
					proidjtf.setText(null);
					namejtf.setText(null);
					kindjtf.setText(null);
					barcodejtf.setText(null);
					purpricejtf.setText(null);
					selpricejtf.setText(null);
					manujtf.setText(null);
					measurejtf.setText(null);
					sheljtf.setText(null);
					pdjtf.setText(null);
					return;
				} else if(kindjtf.getText().equals("食品")||kindjtf.getText().equals("饮品")||kindjtf.getText().equals("日用品")){
					x = ms2.add(meridjtf.getText(), proidjtf.getText(), namejtf.getText(), kindjtf.getText(),
							barcodejtf.getText(), purpricejtf.getText(), selpricejtf.getText(), manujtf.getText(),
							measurejtf.getText(), sheljtf.getText(), pdjtf.getText());
					ms2.selectall();
					DefaultTableModel dtm2 = (DefaultTableModel) CommodityPanel.jt.getModel();// 获取表格模型
					dtm2.setDataVector(ms2.rows, ms2.columnHeads);// 设置新内容
					dtm2.fireTableStructureChanged();
					ms2.close();
					meridjtf.setText(null);
					proidjtf.setText(null);
					namejtf.setText(null);
					kindjtf.setText(null);
					barcodejtf.setText(null);
					purpricejtf.setText(null);
					selpricejtf.setText(null);
					manujtf.setText(null);
					measurejtf.setText(null);
					sheljtf.setText(null);
					pdjtf.setText(null);
					JOptionPane pane = new JOptionPane();
					pane.showMessageDialog(null, "添加了" + x + "个数据");
					CommodityPanel.add.setEnabled(true);
					jm.dispose();
				}
				else {
					JOptionPane p2 = new JOptionPane();
					p2.showMessageDialog(null, "类型请从食品，商品，日用品中选择");
					kindjtf.setText(null);
				}
				
			}
		});
		no.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				meridjtf.setText(null);
				proidjtf.setText(null);
				namejtf.setText(null);
				kindjtf.setText(null);
				barcodejtf.setText(null);
				purpricejtf.setText(null);
				selpricejtf.setText(null);
				manujtf.setText(null);
				measurejtf.setText(null);
				sheljtf.setText(null);
				pdjtf.setText(null);
				CommodityPanel.add.setEnabled(true);
				jm.dispose();
			}
		});
		
	}
}

class DeletePanel {
	public static JFrame jm = new JFrame("删除商品信息");
	public static JButton yes = new JButton("确认");
	public static JButton no = new JButton("取消");
	public static JTextField jtf = new JTextField(null);
	public static JLabel jbl = new JLabel("请输入删除的商品编号");

	DeletePanel() {
		jm.add(yes);
		jm.add(jtf);
		jm.add(no);
		jm.add(jbl);
		yes.setBounds(75, 120, 70, 35);
		no.setBounds(160, 120, 70, 35);
		jtf.setBounds(50, 60, 200, 40);
		jbl.setBounds(90, 10, 200, 40);

		
		jm.addWindowListener(new Deletelistener());
		jm.setLayout(null);
		jm.setSize(300, 200);
		jm.setLocationRelativeTo(null);
		jm.setVisible(true);
		
	}
	static {
		yes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Mysql ms2 = new Mysql();
				int x = 0;
				String s = jtf.getText();
				if (s.length() == 0) {
					JOptionPane pane1 = new JOptionPane();
					pane1.showMessageDialog(jm, "商品编号不能为空");
					jtf.setText(null);
					return;
				} else {
					x = ms2.delete(s);
				}
				ms2.selectall();
				DefaultTableModel dtm2 = (DefaultTableModel) CommodityPanel.jt.getModel();// 获取表格模型
				dtm2.setDataVector(ms2.rows, ms2.columnHeads);// 设置新内容
				dtm2.fireTableStructureChanged();
				ms2.close();
				jtf.setText(null);
				JOptionPane pane = new JOptionPane();
				pane.showMessageDialog(null, "删除了" + x + "个数据");
				CommodityPanel.delete.setEnabled(true);
				jm.dispose();
			}
		});
		no.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jtf.setText(null);
				CommodityPanel.delete.setEnabled(true);
				jm.dispose();
			}
		});
	}
}
