package gui.Panel;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;



public class SalePanel {
	public static JFrame f = new JFrame("���۷���"); ;
	public static CardLayout card = new CardLayout(0, 0);
	public static JPanel mpanel= new JPanel(card);;
	
	public static  CardLayout card_low = new CardLayout(0, 0);
	public static  JPanel lowpanel = new JPanel(card_low);
public static void showpanel() throws SQLException {

	f.repaint();
	f.setSize(650, 600);
	f.setLocation(300, 100);
	f.setLayout(null);
	JLabel l_day = new JLabel("�����ۼ�¼");
	l_day.setBounds(0, 0, 100, 50);
	JButton dayb_pie = new JButton("��״ͼ");
	dayb_pie.setBounds(10, 50, 90, 50);
	JButton dayb_bar = new JButton("��״ͼ");
	dayb_bar.setBounds(10, 100, 90, 50);
	JLabel l_mon = new JLabel("�����ۼ�¼");
	l_mon.setBounds(0, 150, 100, 50);
	JButton monb_pie = new JButton("��״ͼ");
	monb_pie.setBounds(10, 200, 90, 50);
	JButton monb_bar = new JButton("��״ͼ");
	monb_bar.setBounds(10, 250, 90, 50);
	JLabel l_year = new JLabel("�����ۼ�¼");
	l_year.setBounds(0, 300, 100, 50);
	JButton yearb_pie = new JButton("��״ͼ");
	yearb_pie.setBounds(10, 350, 90, 50);
	JButton yearb_bar = new JButton("��״ͼ");
	yearb_bar.setBounds(10, 400, 90, 50);

	mpanel.setBounds(100, 0, 550, 500);
	// mpanel.setLayout(new CardLayout());
	// JPanel day_pie=new JPanel();
		PieChart_day daypie = new PieChart_day();
		JScrollPane day_pie = new JScrollPane(daypie.getChartPanel());
		// day_pie.add(day_s_pie);
		// day_pie.add(pie.getChartPanel());
		// JPanel day_bar=new JPanel();
		BarChart_day bar = new BarChart_day();
		JScrollPane day_bar = new JScrollPane(bar.getChartPanel());
		// day_bar.add(day_s_bar);
		// day_bar.add(bar.getChartPanel());
		PieChart_mon monpie = new PieChart_mon();
		JScrollPane mon_pie = new JScrollPane(monpie.getChartPanel());
		BarChart_mon monbar = new BarChart_mon();
		JScrollPane mon_bar = new JScrollPane(monbar.getChartPanel());
		PieChart_year yearpie = new PieChart_year();
		JScrollPane year_pie = new JScrollPane(yearpie.getChartPanel());
		BarChart_year yearbar = new BarChart_year();
		JScrollPane year_bar = new JScrollPane(yearbar.getChartPanel());
	
	mpanel.add(day_pie, "daypie");
	mpanel.add(day_bar, "daybar");
	mpanel.add(mon_pie, "monpie");
	mpanel.add(mon_bar, "monbar");
	mpanel.add(year_pie, "yearpie");
	mpanel.add(year_bar, "yearbar");
	
	
	lowpanel.setBounds(100, 510, 550, 90);
	JLabel day_sale = new JLabel("���۶�"+":"+daypie.sale_all);
	JLabel day_date = new JLabel("��������"+":"+daypie.day_date);
	JPanel day_data = new JPanel();
	day_data.add(day_sale);
	day_data.add(day_date);
	JLabel mon_sale = new JLabel("���۶�"+":"+monpie.mon_all);
	JLabel mon_date = new JLabel("��������"+":"+monpie.mon_date);
	JPanel mon_data = new JPanel();
	mon_data.add(mon_sale);
	mon_data.add(mon_date);
	JLabel year_sale = new JLabel("���۶�"+":"+yearpie.year_all);
	JLabel year_date = new JLabel("��������"+":"+yearpie.year_date);
	JPanel year_data = new JPanel();
	year_data.add(year_sale);
	year_data.add(year_date);
	lowpanel.removeAll();
	lowpanel.add(day_data, "daydata");
	lowpanel.add(mon_data, "mondata");
	lowpanel.add(year_data, "yeardata");
	f.add(mpanel);
	f.add(l_day);
	f.add(dayb_pie);
	f.add(dayb_bar);
	f.add(l_mon);
	f.add(monb_pie);
	f.add(monb_bar);
	f.add(l_year);
	f.add(yearb_pie);
	f.add(yearb_bar);
	f.add(lowpanel);
	f.setVisible(true);
	f.setLocationRelativeTo(null);
	// PieChart pie=new PieChart();
	// mpanel.add(pie.getChartPanel());
	// BarChart bar=new BarChart();
	// f.add(bar.getChartPanel());
	// f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
	f.setResizable(false);
	dayb_pie.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			card.show(mpanel, "daypie");
			card_low.show(lowpanel, "daydata");
		}
	});
	dayb_bar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			card.show(mpanel, "daybar");
			card_low.show(lowpanel, "daydata");
		}
	});
	monb_pie.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			card.show(mpanel, "monpie");
			card_low.show(lowpanel, "mondata");
		}
	});
	monb_bar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			card.show(mpanel, "monbar");
			card_low.show(lowpanel, "mondata");
		}
	});
	yearb_pie.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			card.show(mpanel, "yearpie");
			card_low.show(lowpanel, "yeardata");
		}
	});
	yearb_bar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			card.show(mpanel, "yearbar");
			card_low.show(lowpanel, "yeardata");
		}
	});
}

}

class PieChart_day {
	public  int sale_all=0;
	public static String day_date;
	int sale_food=0;
	int sale_necessities=0;
	int sale_drink=0;
	ChartPanel frame1;
	public PieChart_day() throws SQLException {
		DefaultPieDataset data = getDataSet();
		JFreeChart chart = ChartFactory.createPieChart3D("�����ۼ�¼", data, true, false, false);
		// ���ðٷֱ�
		PiePlot pieplot = (PiePlot) chart.getPlot();
		DecimalFormat df = new DecimalFormat("0.00%");// ���һ��DecimalFormat������Ҫ������С������
		NumberFormat nf = NumberFormat.getNumberInstance();// ���һ��NumberFormat����
		StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);// ���StandardPieSectionLabelGenerator����
		pieplot.setLabelGenerator(sp1);// ���ñ�ͼ��ʾ�ٷֱ�
		// û�����ݵ�ʱ����ʾ������
		pieplot.setNoDataMessage("��������ʾ");
		pieplot.setCircular(false);
		pieplot.setLabelGap(0.02D);
		pieplot.setIgnoreNullValues(true);// ���ò���ʾ��ֵ
		pieplot.setIgnoreZeroValues(true);// ���ò���ʾ��ֵ
		frame1 = new ChartPanel(chart, true);
		chart.getTitle().setFont(new Font("����", Font.BOLD, 20));// ���ñ�������
		PiePlot piePlot = (PiePlot) chart.getPlot();// ��ȡͼ���������
		piePlot.setLabelFont(new Font("����", Font.BOLD, 10));// �������
		chart.getLegend().setItemFont(new Font("����", Font.BOLD, 10));
	}

	private  DefaultPieDataset getDataSet() throws SQLException {
		Database d=new Database();
		DefaultPieDataset dataset = new DefaultPieDataset();
		String sql="select SaleDate,SaleMoney,���� from DaySalebill,merch where DaySaleBill.MerchID=merch.��Ʒ���";
		d.pstm = d.conn.prepareStatement(sql);
		ResultSet rs = d.pstm.executeQuery();
		//rs = d.pstm.executeQuery();
		
		while(rs.next()) {
			System.out.println("aaa");
			day_date=rs.getString("SaleDate");
			sale_all += rs.getInt("SaleMoney");
			if(rs.getString("����").equals("ʳƷ"))
				sale_food+=rs.getInt("SaleMoney");
			else if(rs.getString("����").equals("����Ʒ"))
				sale_necessities+=rs.getInt("SaleMoney");
			else if(rs.getString("����").equals("��Ʒ"))
				sale_drink+=rs.getInt("SaleMoney");
			System.out.println(sale_food+sale_necessities+sale_drink);
			System.out.println(rs.getInt("SaleMoney"));
		}
		dataset.setValue("ʳƷ",sale_food);
		dataset.setValue("����Ʒ", sale_necessities);
		dataset.setValue("��Ʒ", sale_drink);
		return dataset;
	}
	public ChartPanel getChartPanel() {
		return frame1;
	}
} 
class PieChart_mon {
	ChartPanel frame1;
	public  int mon_all=0;
	public static String mon_date;
	public PieChart_mon() throws SQLException {
		DefaultPieDataset data = getDataSet();
		JFreeChart chart = ChartFactory.createPieChart3D("�����ۼ�¼", data, true, false, false);
		// ���ðٷֱ�
		PiePlot pieplot = (PiePlot) chart.getPlot();
		DecimalFormat df = new DecimalFormat("0.00%");// ���һ��DecimalFormat������Ҫ������С������
		NumberFormat nf = NumberFormat.getNumberInstance();// ���һ��NumberFormat����
		StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);// ���StandardPieSectionLabelGenerator����
		pieplot.setLabelGenerator(sp1);// ���ñ�ͼ��ʾ�ٷֱ�

		// û�����ݵ�ʱ����ʾ������
		pieplot.setNoDataMessage("��������ʾ");
		pieplot.setCircular(false);
		pieplot.setLabelGap(0.02D);

		pieplot.setIgnoreNullValues(true);// ���ò���ʾ��ֵ
		pieplot.setIgnoreZeroValues(true);// ���ò���ʾ��ֵ
		frame1 = new ChartPanel(chart, true);
		chart.getTitle().setFont(new Font("����", Font.BOLD, 20));// ���ñ�������
		PiePlot piePlot = (PiePlot) chart.getPlot();// ��ȡͼ���������
		piePlot.setLabelFont(new Font("����", Font.BOLD, 10));// �������
		chart.getLegend().setItemFont(new Font("����", Font.BOLD, 10));
	}

	private  DefaultPieDataset getDataSet() throws SQLException {
		//Database d=new Database();
		Database d=new Database();
		DefaultPieDataset dataset = new DefaultPieDataset();
		String sql="select SaleDate,SaleMoney from MonthSaleBill";
		d.pstm = d.conn.prepareStatement(sql);
		ResultSet rs = null;
		rs = d.pstm.executeQuery();
		while(rs.next()){
			int money=rs.getInt("SaleMoney");
			String date=(rs.getString("SaleDate")).substring((rs.getString("SaleDate")).length()-3, (rs.getString("SaleDate")).length());
			dataset.setValue(date, money);
			mon_all+=rs.getInt("SaleMoney");
			mon_date=(rs.getString("SaleDate")).substring(0, 8);
		}
		return dataset;
	}


	public ChartPanel getChartPanel() {
		return frame1;

	}
}
class PieChart_year {
	ChartPanel frame1;
	public  int year_all=0;
	public static String year_date;
	public PieChart_year() throws SQLException {
		DefaultPieDataset data = getDataSet();
		JFreeChart chart = ChartFactory.createPieChart3D("�����ۼ�¼", data, true, false, false);
		// ���ðٷֱ�
		PiePlot pieplot = (PiePlot) chart.getPlot();
		DecimalFormat df = new DecimalFormat("0.00%");// ���һ��DecimalFormat������Ҫ������С������
		NumberFormat nf = NumberFormat.getNumberInstance();// ���һ��NumberFormat����
		StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);// ���StandardPieSectionLabelGenerator����
		pieplot.setLabelGenerator(sp1);// ���ñ�ͼ��ʾ�ٷֱ�

		// û�����ݵ�ʱ����ʾ������
		pieplot.setNoDataMessage("��������ʾ");
		pieplot.setCircular(false);
		pieplot.setLabelGap(0.02D);

		pieplot.setIgnoreNullValues(true);// ���ò���ʾ��ֵ
		pieplot.setIgnoreZeroValues(true);// ���ò���ʾ��ֵ
		frame1 = new ChartPanel(chart, true);
		chart.getTitle().setFont(new Font("����", Font.BOLD, 20));// ���ñ�������
		PiePlot piePlot = (PiePlot) chart.getPlot();// ��ȡͼ���������
		piePlot.setLabelFont(new Font("����", Font.BOLD, 10));// �������
		chart.getLegend().setItemFont(new Font("����", Font.BOLD, 10));
	}

	private  DefaultPieDataset getDataSet() throws SQLException {
		//Database d=new Database();
		Database d=new Database();
		DefaultPieDataset dataset = new DefaultPieDataset();
		String sql="select SaleDate,SaleMoney from YearSaleBill";
		d.pstm = d.conn.prepareStatement(sql);
		ResultSet rs = null;
		rs = d.pstm.executeQuery();
		while(rs.next()){
			int money=rs.getInt("SaleMoney");
			String date=(rs.getString("SaleDate")).substring((rs.getString("SaleDate")).length()-6, (rs.getString("SaleDate")).length()-3);
			dataset.setValue(date, money);
			year_all+=rs.getInt("SaleMoney");
			year_date=(rs.getString("SaleDate")).substring(0, 5);
		}
		return dataset;
	}


	public ChartPanel getChartPanel() {
		return frame1;

	}
}


class BarChart_day {
	ChartPanel frame1;
	public BarChart_day() throws SQLException {
		CategoryDataset dataset = getDataSet();
		JFreeChart chart = ChartFactory.createBarChart3D("�����ۼ�¼", // ͼ�����
				"��Ʒ����", // Ŀ¼�����ʾ��ǩ
				"���۶�", // ��ֵ�����ʾ��ǩ
				dataset, // ���ݼ�
				PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
				true, // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
				false, // �Ƿ����ɹ���
				false // �Ƿ�����URL����
		);

		// �����￪ʼ
		CategoryPlot plot = chart.getCategoryPlot();// ��ȡͼ���������
		CategoryAxis domainAxis = plot.getDomainAxis(); // ˮƽ�ײ��б�
		domainAxis.setLabelFont(new Font("����", Font.BOLD, 14)); // ˮƽ�ײ�����
		domainAxis.setTickLabelFont(new Font("����", Font.BOLD, 12)); // ��ֱ����
		ValueAxis rangeAxis = plot.getRangeAxis();// ��ȡ��״
		rangeAxis.setLabelFont(new Font("����", Font.BOLD, 15));
		chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));
		chart.getTitle().setFont(new Font("����", Font.BOLD, 20));// ���ñ�������

		// �������������Ȼ�����е�࣬��ֻΪһ��Ŀ�ģ����������������

		frame1 = new ChartPanel(chart, true); // ����Ҳ������chartFrame,����ֱ������һ��������Frame

	}

	private static CategoryDataset getDataSet() throws SQLException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Database d=new Database();
		String sql="select SaleDate,SaleMoney,���� from daysalebill,merch where DaySaleBill.MerchID=merch.��Ʒ���";
		d.pstm = d.conn.prepareStatement(sql);
		ResultSet rs = null;
		rs = d.pstm.executeQuery();
		int sale_food=0;
		int sale_necessities=0;
		int sale_drink=0;
		while(rs.next()) {
			if(rs.getString("����").equals("ʳƷ"))
				sale_food+=rs.getInt("SaleMoney");
			else if(rs.getString("����").equals("����Ʒ"))
				sale_necessities+=rs.getInt("SaleMoney");
			else if(rs.getString("����").equals("��Ʒ"))
				sale_drink+=rs.getInt("SaleMoney");
			
		}
		dataset.setValue(sale_food, "��Ʒ����", "ʳƷ");
		dataset.setValue(sale_necessities, "��Ʒ����", "����Ʒ");
		dataset.setValue(sale_drink, "��Ʒ����", "��Ʒ");
		return dataset;
	}

	public ChartPanel getChartPanel() {
		return frame1;

	}
}

class BarChart_mon {
	ChartPanel frame1;
	public BarChart_mon() throws SQLException {
		CategoryDataset dataset = getDataSet();
		JFreeChart chart = ChartFactory.createBarChart3D("�����ۼ�¼", // ͼ�����
				"��Ʒ����", // Ŀ¼�����ʾ��ǩ
				"���۶�", // ��ֵ�����ʾ��ǩ
				dataset, // ���ݼ�
				PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
				true, // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
				false, // �Ƿ����ɹ���
				false // �Ƿ�����URL����
		);

		// �����￪ʼ
		CategoryPlot plot = chart.getCategoryPlot();// ��ȡͼ���������
		CategoryAxis domainAxis = plot.getDomainAxis(); // ˮƽ�ײ��б�
		domainAxis.setLabelFont(new Font("����", Font.BOLD, 14)); // ˮƽ�ײ�����
		domainAxis.setTickLabelFont(new Font("����", Font.BOLD, 12)); // ��ֱ����
		ValueAxis rangeAxis = plot.getRangeAxis();// ��ȡ��״
		rangeAxis.setLabelFont(new Font("����", Font.BOLD, 15));
		chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));
		chart.getTitle().setFont(new Font("����", Font.BOLD, 20));// ���ñ�������

		// �������������Ȼ�����е�࣬��ֻΪһ��Ŀ�ģ����������������

		frame1 = new ChartPanel(chart, true); // ����Ҳ������chartFrame,����ֱ������һ��������Frame

	}

	private static CategoryDataset getDataSet() throws SQLException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Database d=new Database();
		String sql="select SaleDate,SaleMoney from MonthSaleBill";
		d.pstm = d.conn.prepareStatement(sql);
		ResultSet rs = null;
		rs = d.pstm.executeQuery();
		while(rs.next()){
			int money=rs.getInt("SaleMoney");
			String date=(rs.getString("SaleDate")).substring((rs.getString("SaleDate")).length()-3, (rs.getString("SaleDate")).length());
			dataset.setValue(money, "����", date);
		}
		return dataset;
	}

	public ChartPanel getChartPanel() {
		return frame1;

	}
}

class BarChart_year {
	ChartPanel frame1;
	public BarChart_year() throws SQLException {
		CategoryDataset dataset = getDataSet();
		JFreeChart chart = ChartFactory.createBarChart3D("�����ۼ�¼", // ͼ�����
				"��Ʒ����", // Ŀ¼�����ʾ��ǩ
				"���۶�", // ��ֵ�����ʾ��ǩ
				dataset, // ���ݼ�
				PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
				true, // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
				false, // �Ƿ����ɹ���
				false // �Ƿ�����URL����
		);

		// �����￪ʼ
		CategoryPlot plot = chart.getCategoryPlot();// ��ȡͼ���������
		CategoryAxis domainAxis = plot.getDomainAxis(); // ˮƽ�ײ��б�
		domainAxis.setLabelFont(new Font("����", Font.BOLD, 14)); // ˮƽ�ײ�����
		domainAxis.setTickLabelFont(new Font("����", Font.BOLD, 12)); // ��ֱ����
		ValueAxis rangeAxis = plot.getRangeAxis();// ��ȡ��״
		rangeAxis.setLabelFont(new Font("����", Font.BOLD, 15));
		chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));
		chart.getTitle().setFont(new Font("����", Font.BOLD, 20));// ���ñ�������

		// �������������Ȼ�����е�࣬��ֻΪһ��Ŀ�ģ����������������

		frame1 = new ChartPanel(chart, true); // ����Ҳ������chartFrame,����ֱ������һ��������Frame

	}

	private static CategoryDataset getDataSet() throws SQLException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Database d=new Database();
		String sql="select SaleDate,SaleMoney from YearSaleBill";
		d.pstm = d.conn.prepareStatement(sql);
		ResultSet rs = null;
		rs = d.pstm.executeQuery();
		while(rs.next()){
			int money=rs.getInt("SaleMoney");
			String date=(rs.getString("SaleDate")).substring((rs.getString("SaleDate")).length()-6, (rs.getString("SaleDate")).length()-3);
			dataset.setValue(money, "��", date);
		}
		return dataset;
	}

	public ChartPanel getChartPanel() {
		return frame1;

	}
}

class Database {
	static Connection conn=null;
	static PreparedStatement pstm = null;
	public Database() {
		final String USERNAME = "super";
		// �������ݿ������
		final String PASSWORD = "594SHENG";
		// �������ݿ��������Ϣ
		final String DRIVER = "oracle.jdbc.driver.OracleDriver";
		final String URL = "jdbc:oracle:thin:@188.131.140.28:1521:orcl";
		try {
			Class.forName(DRIVER);
			System.out.println("ע�������ɹ�����");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("ע������ʧ�ܣ���");
		}
		try {
			conn = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
			conn.setAutoCommit(false);
			//pstm = sample.prepareStatement(sql);
			//ResultSet rs = null;
			//rs = pstm.executeQuery();
			//ResultSetMetaData rsmd = null;
		} catch (SQLException sqle) {
		}
	}
}
