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
	public static JFrame f = new JFrame("销售分析"); ;
	public static CardLayout card = new CardLayout(0, 0);
	public static JPanel mpanel= new JPanel(card);;
	
	public static  CardLayout card_low = new CardLayout(0, 0);
	public static  JPanel lowpanel = new JPanel(card_low);
public static void showpanel() throws SQLException {

	f.repaint();
	f.setSize(650, 600);
	f.setLocation(300, 100);
	f.setLayout(null);
	JLabel l_day = new JLabel("日销售记录");
	l_day.setBounds(0, 0, 100, 50);
	JButton dayb_pie = new JButton("饼状图");
	dayb_pie.setBounds(10, 50, 90, 50);
	JButton dayb_bar = new JButton("柱状图");
	dayb_bar.setBounds(10, 100, 90, 50);
	JLabel l_mon = new JLabel("月销售记录");
	l_mon.setBounds(0, 150, 100, 50);
	JButton monb_pie = new JButton("饼状图");
	monb_pie.setBounds(10, 200, 90, 50);
	JButton monb_bar = new JButton("柱状图");
	monb_bar.setBounds(10, 250, 90, 50);
	JLabel l_year = new JLabel("年销售记录");
	l_year.setBounds(0, 300, 100, 50);
	JButton yearb_pie = new JButton("饼状图");
	yearb_pie.setBounds(10, 350, 90, 50);
	JButton yearb_bar = new JButton("柱状图");
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
	JLabel day_sale = new JLabel("销售额"+":"+daypie.sale_all);
	JLabel day_date = new JLabel("销售日期"+":"+daypie.day_date);
	JPanel day_data = new JPanel();
	day_data.add(day_sale);
	day_data.add(day_date);
	JLabel mon_sale = new JLabel("销售额"+":"+monpie.mon_all);
	JLabel mon_date = new JLabel("销售日期"+":"+monpie.mon_date);
	JPanel mon_data = new JPanel();
	mon_data.add(mon_sale);
	mon_data.add(mon_date);
	JLabel year_sale = new JLabel("销售额"+":"+yearpie.year_all);
	JLabel year_date = new JLabel("销售日期"+":"+yearpie.year_date);
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
		JFreeChart chart = ChartFactory.createPieChart3D("日销售记录", data, true, false, false);
		// 设置百分比
		PiePlot pieplot = (PiePlot) chart.getPlot();
		DecimalFormat df = new DecimalFormat("0.00%");// 获得一个DecimalFormat对象，主要是设置小数问题
		NumberFormat nf = NumberFormat.getNumberInstance();// 获得一个NumberFormat对象
		StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);// 获得StandardPieSectionLabelGenerator对象
		pieplot.setLabelGenerator(sp1);// 设置饼图显示百分比
		// 没有数据的时候显示的内容
		pieplot.setNoDataMessage("无数据显示");
		pieplot.setCircular(false);
		pieplot.setLabelGap(0.02D);
		pieplot.setIgnoreNullValues(true);// 设置不显示空值
		pieplot.setIgnoreZeroValues(true);// 设置不显示负值
		frame1 = new ChartPanel(chart, true);
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体
		PiePlot piePlot = (PiePlot) chart.getPlot();// 获取图表区域对象
		piePlot.setLabelFont(new Font("宋体", Font.BOLD, 10));// 解决乱码
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 10));
	}

	private  DefaultPieDataset getDataSet() throws SQLException {
		Database d=new Database();
		DefaultPieDataset dataset = new DefaultPieDataset();
		String sql="select SaleDate,SaleMoney,类型 from DaySalebill,merch where DaySaleBill.MerchID=merch.商品编号";
		d.pstm = d.conn.prepareStatement(sql);
		ResultSet rs = d.pstm.executeQuery();
		//rs = d.pstm.executeQuery();
		
		while(rs.next()) {
			System.out.println("aaa");
			day_date=rs.getString("SaleDate");
			sale_all += rs.getInt("SaleMoney");
			if(rs.getString("类型").equals("食品"))
				sale_food+=rs.getInt("SaleMoney");
			else if(rs.getString("类型").equals("日用品"))
				sale_necessities+=rs.getInt("SaleMoney");
			else if(rs.getString("类型").equals("饮品"))
				sale_drink+=rs.getInt("SaleMoney");
			System.out.println(sale_food+sale_necessities+sale_drink);
			System.out.println(rs.getInt("SaleMoney"));
		}
		dataset.setValue("食品",sale_food);
		dataset.setValue("日用品", sale_necessities);
		dataset.setValue("饮品", sale_drink);
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
		JFreeChart chart = ChartFactory.createPieChart3D("月销售记录", data, true, false, false);
		// 设置百分比
		PiePlot pieplot = (PiePlot) chart.getPlot();
		DecimalFormat df = new DecimalFormat("0.00%");// 获得一个DecimalFormat对象，主要是设置小数问题
		NumberFormat nf = NumberFormat.getNumberInstance();// 获得一个NumberFormat对象
		StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);// 获得StandardPieSectionLabelGenerator对象
		pieplot.setLabelGenerator(sp1);// 设置饼图显示百分比

		// 没有数据的时候显示的内容
		pieplot.setNoDataMessage("无数据显示");
		pieplot.setCircular(false);
		pieplot.setLabelGap(0.02D);

		pieplot.setIgnoreNullValues(true);// 设置不显示空值
		pieplot.setIgnoreZeroValues(true);// 设置不显示负值
		frame1 = new ChartPanel(chart, true);
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体
		PiePlot piePlot = (PiePlot) chart.getPlot();// 获取图表区域对象
		piePlot.setLabelFont(new Font("宋体", Font.BOLD, 10));// 解决乱码
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 10));
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
		JFreeChart chart = ChartFactory.createPieChart3D("年销售记录", data, true, false, false);
		// 设置百分比
		PiePlot pieplot = (PiePlot) chart.getPlot();
		DecimalFormat df = new DecimalFormat("0.00%");// 获得一个DecimalFormat对象，主要是设置小数问题
		NumberFormat nf = NumberFormat.getNumberInstance();// 获得一个NumberFormat对象
		StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);// 获得StandardPieSectionLabelGenerator对象
		pieplot.setLabelGenerator(sp1);// 设置饼图显示百分比

		// 没有数据的时候显示的内容
		pieplot.setNoDataMessage("无数据显示");
		pieplot.setCircular(false);
		pieplot.setLabelGap(0.02D);

		pieplot.setIgnoreNullValues(true);// 设置不显示空值
		pieplot.setIgnoreZeroValues(true);// 设置不显示负值
		frame1 = new ChartPanel(chart, true);
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体
		PiePlot piePlot = (PiePlot) chart.getPlot();// 获取图表区域对象
		piePlot.setLabelFont(new Font("宋体", Font.BOLD, 10));// 解决乱码
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 10));
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
		JFreeChart chart = ChartFactory.createBarChart3D("日销售记录", // 图表标题
				"商品类型", // 目录轴的显示标签
				"销售额", // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				true, // 是否显示图例(对于简单的柱状图必须是false)
				false, // 是否生成工具
				false // 是否生成URL链接
		);

		// 从这里开始
		CategoryPlot plot = chart.getCategoryPlot();// 获取图表区域对象
		CategoryAxis domainAxis = plot.getDomainAxis(); // 水平底部列表
		domainAxis.setLabelFont(new Font("黑体", Font.BOLD, 14)); // 水平底部标题
		domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // 垂直标题
		ValueAxis rangeAxis = plot.getRangeAxis();// 获取柱状
		rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体

		// 到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题

		frame1 = new ChartPanel(chart, true); // 这里也可以用chartFrame,可以直接生成一个独立的Frame

	}

	private static CategoryDataset getDataSet() throws SQLException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Database d=new Database();
		String sql="select SaleDate,SaleMoney,类型 from daysalebill,merch where DaySaleBill.MerchID=merch.商品编号";
		d.pstm = d.conn.prepareStatement(sql);
		ResultSet rs = null;
		rs = d.pstm.executeQuery();
		int sale_food=0;
		int sale_necessities=0;
		int sale_drink=0;
		while(rs.next()) {
			if(rs.getString("类型").equals("食品"))
				sale_food+=rs.getInt("SaleMoney");
			else if(rs.getString("类型").equals("日用品"))
				sale_necessities+=rs.getInt("SaleMoney");
			else if(rs.getString("类型").equals("饮品"))
				sale_drink+=rs.getInt("SaleMoney");
			
		}
		dataset.setValue(sale_food, "商品类型", "食品");
		dataset.setValue(sale_necessities, "商品类型", "日用品");
		dataset.setValue(sale_drink, "商品类型", "饮品");
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
		JFreeChart chart = ChartFactory.createBarChart3D("月销售记录", // 图表标题
				"商品类型", // 目录轴的显示标签
				"销售额", // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				true, // 是否显示图例(对于简单的柱状图必须是false)
				false, // 是否生成工具
				false // 是否生成URL链接
		);

		// 从这里开始
		CategoryPlot plot = chart.getCategoryPlot();// 获取图表区域对象
		CategoryAxis domainAxis = plot.getDomainAxis(); // 水平底部列表
		domainAxis.setLabelFont(new Font("黑体", Font.BOLD, 14)); // 水平底部标题
		domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // 垂直标题
		ValueAxis rangeAxis = plot.getRangeAxis();// 获取柱状
		rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体

		// 到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题

		frame1 = new ChartPanel(chart, true); // 这里也可以用chartFrame,可以直接生成一个独立的Frame

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
			dataset.setValue(money, "日期", date);
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
		JFreeChart chart = ChartFactory.createBarChart3D("年销售记录", // 图表标题
				"商品类型", // 目录轴的显示标签
				"销售额", // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				true, // 是否显示图例(对于简单的柱状图必须是false)
				false, // 是否生成工具
				false // 是否生成URL链接
		);

		// 从这里开始
		CategoryPlot plot = chart.getCategoryPlot();// 获取图表区域对象
		CategoryAxis domainAxis = plot.getDomainAxis(); // 水平底部列表
		domainAxis.setLabelFont(new Font("黑体", Font.BOLD, 14)); // 水平底部标题
		domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // 垂直标题
		ValueAxis rangeAxis = plot.getRangeAxis();// 获取柱状
		rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体

		// 到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题

		frame1 = new ChartPanel(chart, true); // 这里也可以用chartFrame,可以直接生成一个独立的Frame

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
			dataset.setValue(money, "年", date);
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
		// 定义数据库的密码
		final String PASSWORD = "594SHENG";
		// 定义数据库的驱动信息
		final String DRIVER = "oracle.jdbc.driver.OracleDriver";
		final String URL = "jdbc:oracle:thin:@188.131.140.28:1521:orcl";
		try {
			Class.forName(DRIVER);
			System.out.println("注册驱动成功！！");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("注册驱动失败！！");
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
