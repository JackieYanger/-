package gui.Panel;



import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class ProfferPanel {
	public static JPanel jp1 = new JPanel();
	public static JPanel jp2 = new JPanel();
	public static JFrame f = new JFrame("供应商信息管理");
	public static JTable jt;
	public static JButton select = new JButton("查询");
	public static JButton add = new JButton("添加");
	public static JButton modify = new JButton("修改");
	public static JButton delete = new JButton("删除");

	public static void showpanel() {
		Mysql1 mysql1 = new Mysql1();
		mysql1.selectall();

		DefaultTableModel dtm1 = new DefaultTableModel(mysql1.rows, mysql1.columnHeads);
		jt = new JTable(dtm1){
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
					int row = ProfferPanel.jt.rowAtPoint(p);
					int column = ProfferPanel.jt.columnAtPoint(p);
					int countr = jt.getSelectedRow();
					int countc = jt.getSelectedColumn();
					String getString = jt.getValueAt(countr, countc).toString();
					// System.out.println(getString);
					String getcolumnhead = mysql1.columnHeads.get(countc).toString();
					System.out.println(getcolumnhead);
					String getid = jt.getValueAt(countr, 0).toString();
					System.out.println(getid);
					JFrame jfm = new JFrame("修改页面");
					JLabel jl1 = new JLabel("供应商编号:" + getid);
					JLabel jl2 = new JLabel(getcolumnhead + ":" + getString);
					JLabel jl3 = new JLabel("修改为:");
					JTextField jtf = new JTextField(null);
					JButton jb1 = new JButton("确认");
					JButton jb2 = new JButton("取消");
					jfm.add(jb1);
					jfm.add(jb2);
					jfm.add(jtf);
					jfm.add(jl3);
					jfm.add(jl2);
					jfm.add(jl1);
					jl1.setBounds(70, 10, 150, 30);
					jl2.setBounds(70, 40, 150, 30);
					jl3.setBounds(70, 70, 50, 30);
					jtf.setBounds(120, 70, 100, 30);
					jb1.setBounds(70, 110, 60, 30);
					jb2.setBounds(160, 110, 60, 30);
					jb1.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							Mysql1 ms2 = new Mysql1();
							String s = jtf.getText();
							ms2.modify(getid, getcolumnhead, s);
							ms2.selectall();
							DefaultTableModel dtm2 = (DefaultTableModel) ProfferPanel.jt.getModel();// 获取表格模型
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

		modify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int countr = jt.getSelectedRow();
				int countc = jt.getSelectedColumn();
				String getString = jt.getValueAt(countr, countc).toString();
				System.out.println(getString);
			}
		});
		select.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SelectPanel1.selectpanel1();
			}
		});
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddPanel1.addpanel1();
			}
		});
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DeletePanel1.deletepanel1();
			}
		});
		jp1.add(select);
		jp1.add(add);

		jp1.add(delete);
		jp1.setBounds(0, 0, 105, 600);
		jp1.setLayout(null);
		jp1.setVisible(true);

		jp2.add(jsp);
		jp2.setBounds(105, 0, 530, 600);
		jp2.setVisible(true);

		f.add(jp1);
		f.add(jp2);
		f.setLayout(null);
		f.addWindowListener(new Mywindowlistener1());
		f.setSize(650, 600);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setResizable(false);

	}

	static {
		
	}
}

class Mysql1 {
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String DB_URL = "jdbc:oracle:thin:@188.131.140.28:1521:orcl";
	static final String USER = "super";
	static final String PASSWORD = "594SHENG";
	static Connection conn = null;
	static Statement stmt = null;
	static PreparedStatement pstm = null;
	Vector rows = new Vector();
	Vector columnHeads = new Vector();

	Mysql1() {
		try {
			Class.forName(JDBC_DRIVER);
			// System.out.println("连接数据库...");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			// System.out.println(" 实例化Statement对象...");
			stmt = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			pstm.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectall() {
		String sql = "select * from ProfferBill order by 供货商号";
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
		String sql = "select * from ProfferBill where 供货商号=?";
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
		String sql = "select * from ProfferBill where 名称=?";
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

	public void modify(String merch_id, String columnhead, String newx) {
		String sql = "update ProfferBill set " + columnhead + "=" + "'" + newx + "'" + "where 供货商号=" + "'" + merch_id
				+ "'";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.execute();
		} catch (SQLException sqle) {
		}
	}

	public int add(String pro_id, String name, String contacts, String number, String address, String bank) {
		int i = 0;
		String sql = "insert into ProfferBill (供货商号,名称,联系人,联系电话,联系地址,银行账号) values(?,?,?,?,?,?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, pro_id);
			pstm.setString(2, name);
			pstm.setString(3, contacts);
			pstm.setString(4, number);
			pstm.setString(5, address);
			pstm.setString(6, bank);
			i = pstm.executeUpdate();
		} catch (SQLException sqle) {
		}
		return i;
	}

	public int delete(String merch_id) {
		int j = 0;
		String sql = "delete from ProfferBill where 供货商号= " + "'" + merch_id + "'";
		try {
			pstm = conn.prepareStatement(sql);
			// pstm.setString(1, merch_id);
			j = pstm.executeUpdate();

		} catch (SQLException sqle) {
		}
		return j;
	}

	public static Vector getNextRow(ResultSet rs, ResultSetMetaData rsmd) throws SQLException {
		Vector currentRow = new Vector();
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			currentRow.addElement(rs.getString(i));
		}
		return currentRow;
	}
}

class Mywindowlistener1 implements WindowListener {

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		MainPanel.supply.setEnabled(true);
		ProfferPanel.jp2.removeAll();

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

class Addlistener1 implements WindowListener {

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		ProfferPanel.add.setEnabled(true);
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
class Deletelistener1 implements WindowListener {

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		ProfferPanel.delete.setEnabled(true);
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
class Selectlistener1 implements WindowListener {

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		ProfferPanel.select.setEnabled(true);
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
class SelectPanel1 {
	public static JPanel spl = new JPanel();
	public static JTextField jtf = new JTextField();
	public static JButton s2 = new JButton("供应商号查询");
	public static JButton s3 = new JButton("供应商名称查询");
	// JButton s7 = new JButton("即将过期查询");
	public static JButton quit = new JButton("返回");

	public static void selectpanel1() {
		jtf.setBounds(0, 0, 105, 50);
		s2.setBounds(0, 50, 105, 50);
		s3.setBounds(0, 100, 105, 50);

		// s7.setBounds(0, 350, 105, 50);
		quit.setBounds(0, 150, 105, 50);

		spl.setBounds(0, 0, 105, 600);
		spl.setLayout(null);
		spl.setVisible(true);

		spl.add(s2);
		spl.add(s3);

		// spl.add(s7);
		spl.add(quit);
		spl.add(jtf);
		ProfferPanel.f.remove(ProfferPanel.jp1);
		ProfferPanel.f.add(spl);
		ProfferPanel.f.repaint();
		
	}

	static {
		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ProfferPanel.f.remove(spl);
				ProfferPanel.f.add(ProfferPanel.jp1);
				ProfferPanel.f.repaint();
				Mysql1 ms2 = new Mysql1();
				ms2.selectall();
				DefaultTableModel dtm2 = (DefaultTableModel) ProfferPanel.jt.getModel();// 获取表格模型
				dtm2.setDataVector(ms2.rows, ms2.columnHeads);// 设置新内容
				// System.out.println(ms2.columnHeads.size());
				dtm2.fireTableStructureChanged();
				ProfferPanel.select.setEnabled(true);
				ms2.close();
			}

		});

		s2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Mysql1 ms2 = new Mysql1();
				ms2.selectproid(jtf.getText());
				DefaultTableModel dtm2 = (DefaultTableModel) ProfferPanel.jt.getModel();// 获取表格模型
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
				Mysql1 ms2 = new Mysql1();
				ms2.selectname(jtf.getText());
				DefaultTableModel dtm2 = (DefaultTableModel) ProfferPanel.jt.getModel();// 获取表格模型
				dtm2.setDataVector(ms2.rows, ms2.columnHeads);// 设置新内容
				dtm2.fireTableStructureChanged();
				ms2.close();
				jtf.setText(null);
			}
		});
	}
}

class AddPanel1 {
	public static JFrame jm = new JFrame("添加供货商信息");
	public static JButton yes = new JButton("确认");
	public static JButton no = new JButton("取消");
	public static JLabel proid = new JLabel("供货商号:");
	public static JLabel name = new JLabel("供货商名称:");
	public static JLabel contacts = new JLabel("联系人");
	public static JLabel number = new JLabel("联系电话");
	public static JLabel address = new JLabel("联系地址");
	public static JLabel bank = new JLabel("银行账号");
	public static JTextField proidjtf = new JTextField(null);
	public static JTextField namejtf = new JTextField(null);
	public static JTextField contactsjtf = new JTextField(null);
	public static JTextField numberjtf = new JTextField(null);
	public static JTextField addressjtf = new JTextField(null);
	public static JTextField bankjtf = new JTextField(null);

	public static void addpanel1() {
		jm.add(yes);
		jm.add(no);
		jm.add(proid);
		jm.add(name);
		jm.add(contacts);
		jm.add(number);
		jm.add(address);
		jm.add(bank);
		jm.add(proidjtf);
		jm.add(namejtf);
		jm.add(contactsjtf);
		jm.add(numberjtf);
		jm.add(addressjtf);
		jm.add(bankjtf);

		proid.setBounds(120, 50, 100, 35);
		proidjtf.setBounds(220, 50, 200, 30);
		name.setBounds(120, 85, 100, 35);
		namejtf.setBounds(220, 85, 200, 30);
		contacts.setBounds(120, 120, 100, 35);
		contactsjtf.setBounds(220, 120, 200, 30);
		number.setBounds(120, 155, 100, 35);
		numberjtf.setBounds(220, 155, 200, 30);
		address.setBounds(120, 190, 100, 35);
		addressjtf.setBounds(220, 190, 200, 30);
		bank.setBounds(120, 225, 100, 35);
		bankjtf.setBounds(220, 225, 200, 30);
		yes.setBounds(170, 260, 70, 35);
		no.setBounds(300, 260, 70, 35);
		
		jm.setLayout(null);
		jm.setSize(550, 600);
		jm.setLocationRelativeTo(null);
		jm.addWindowListener(new Addlistener1());
		jm.setVisible(true);
	}

	static {
		yes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Mysql1 ms2 = new Mysql1();
				int x = 0;
				String s = proidjtf.getText();
				if (s.length() == 0) {
					JOptionPane pane1 = new JOptionPane();
					pane1.showMessageDialog(jm, "供应商号不能为空");
					proidjtf.setText(null);
					namejtf.setText(null);
					contactsjtf.setText(null);
					numberjtf.setText(null);
					addressjtf.setText(null);
					bankjtf.setText(null);

					return;
				} else {
					x = ms2.add(proidjtf.getText(), namejtf.getText(), contactsjtf.getText(), numberjtf.getText(),
							addressjtf.getText(), bankjtf.getText());
				}
				ms2.selectall();
				DefaultTableModel dtm2 = (DefaultTableModel) ProfferPanel.jt.getModel();// 获取表格模型
				dtm2.setDataVector(ms2.rows, ms2.columnHeads);// 设置新内容
				dtm2.fireTableStructureChanged();
				ms2.close();
				proidjtf.setText(null);
				namejtf.setText(null);
				contactsjtf.setText(null);
				numberjtf.setText(null);
				addressjtf.setText(null);
				bankjtf.setText(null);
				JOptionPane pane = new JOptionPane();
				pane.showMessageDialog(null, "添加了" + x + "个数据");
				CommodityPanel.add.setEnabled(true);
				jm.dispose();
			}
		});
		no.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				proidjtf.setText(null);
				namejtf.setText(null);
				contactsjtf.setText(null);
				numberjtf.setText(null);
				addressjtf.setText(null);
				bankjtf.setText(null);
				ProfferPanel.add.setEnabled(true);
				jm.dispose();
			}
		});
	}
}

class DeletePanel1 {
	public static JFrame jm = new JFrame("删除供应商信息");
	public static JButton yes = new JButton("确认");
	public static JButton no = new JButton("取消");
	public static JTextField jtf = new JTextField(null);
	public static JLabel jbl = new JLabel("请输入删除的供应商号");

	public static void deletepanel1() {
		jm.add(yes);
		jm.add(jtf);
		jm.add(no);
		jm.add(jbl);
		yes.setBounds(75, 120, 70, 35);
		no.setBounds(160, 120, 70, 35);
		jtf.setBounds(50, 60, 200, 40);
		jbl.setBounds(90, 10, 200, 40);
	
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
				Mysql1 ms2 = new Mysql1();
				int x = 0;
				String s = jtf.getText();
				if (s.length() == 0) {
					JOptionPane pane1 = new JOptionPane();
					pane1.showMessageDialog(jm, "供应商号不能为空");
					jtf.setText(null);
					return;
				} else {
					x = ms2.delete(s);
				}
				ms2.selectall();
				DefaultTableModel dtm2 = (DefaultTableModel) ProfferPanel.jt.getModel();// 获取表格模型
				dtm2.setDataVector(ms2.rows, ms2.columnHeads);// 设置新内容
				dtm2.fireTableStructureChanged();
				ms2.close();
				jtf.setText(null);
				JOptionPane pane = new JOptionPane();
				pane.showMessageDialog(null, "删除了" + x + "个数据");
				ProfferPanel.delete.setEnabled(true);
				jm.dispose();
			}
		});
		no.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jtf.setText(null);
				ProfferPanel.delete.setEnabled(true);
				jm.dispose();
			}
		});
	}
	public static void main(String[] args) {
	
	}
}
