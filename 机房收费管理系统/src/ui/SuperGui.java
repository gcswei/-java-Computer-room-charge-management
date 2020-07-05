package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import xinxi.Computer;
import xinxi.Expense_info;
import dao.Dao;
import gongneng.ComputerManager;
import gongneng.ExpenseManage;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class SuperGui extends JFrame {

	private JPanel contentPane;
	private JPanel contentpanel;
	private JTable table;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JPanel ModifyDelectpanel;
	private JButton LookComputerbutton;
	private JButton LookExpensebutton;
	private JLabel label;
	private JButton Selectbutton;
	private JButton Xiajibutton;
	private JPanel FeiYongpanel;
	private JLabel lblh;
	private JComboBox FeiyongcomboBox;
		//新建表格标题
	String [] computersearch = {"计算机编号", "系统", "使用情况"};		//计算机信息
	String [] expensesearch = {"计算机编号", "学号", "姓名","班级","上机起始时间","下机结束时间","费用(元)"};		//消费信息

	SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	//获取当前时间

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SuperGui frame = new SuperGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SuperGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(620, 300, 1400, 654);
		setTitle("管理员操作界面 作者：邓定伟-0305");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel buttonpanel = new JPanel();
		buttonpanel.setBackground(Color.GRAY);
		
		contentpanel = new JPanel();
		contentpanel.setBackground(Color.LIGHT_GRAY);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(buttonpanel, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(contentpanel, GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(buttonpanel, GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
				.addComponent(contentpanel, GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
		);
		
		panel = new JPanel();
		GroupLayout gl_contentpanel = new GroupLayout(contentpanel);
		gl_contentpanel.setHorizontalGroup(
			gl_contentpanel.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1158, Short.MAX_VALUE)
		);
		gl_contentpanel.setVerticalGroup(
			gl_contentpanel.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
		);
		
		label = new JLabel("\u8BA1\u7B97\u673A\u4FE1\u606F\u5217\u8868");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("宋体", Font.BOLD, 20));
		
		scrollPane = new JScrollPane();
		
		ModifyDelectpanel = new JPanel();
		
		LookComputerbutton = new JButton("\u8BA1\u7B97\u673A\u4FE1\u606F\u6D4F\u89C8");
		LookComputerbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		//计算机信息浏览按钮
				showComputer();
				LookComputerbutton.setVisible(true);
				LookExpensebutton.setVisible(true);
			}
		});
		
		LookExpensebutton = new JButton("\u6536\u8D39\u4FE1\u606F\u6D4F\u89C8");
		LookExpensebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		//收费信息浏览按钮
				showExpense();
				LookComputerbutton.setVisible(true);
				LookExpensebutton.setVisible(true);
			}
		});
		
		Selectbutton = new JButton("\u67E5\u8BE2");			//查询按钮
		Selectbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search s = new Search();		//创建模态对话窗口
				WindowEventHandler ww;
				s.addWindowListener(ww=new WindowEventHandler());
				Runnable r =new Runnable() {							
									@Override
									public void run() {
										while (true) {
											SwingUtilities.invokeLater(new Runnable() {
												@Override
												public void run() {
													// TODO 自动生成的方法存根													
													s.setVisible(true);
												}
											});
											if (s.i==1) {		//如果是找到了结果，就弹出提示，然后退出循环
												JOptionPane.showMessageDialog(null, "已找到结果！");
												s.setVisible(false);
												break;
											}
											if(ww.i==1) {		//如果是手动关闭了模态对话窗口，退出循环								
												break;
											}
											System.out.println("线程正在运行");
											try {
												Thread.sleep(500);
											} catch (InterruptedException e) {
												// TODO 自动生成的 catch 块
												e.printStackTrace();
											}
									}
										SwingUtilities.invokeLater(new Runnable() {											
											@Override
											public void run() {
												if(s.ssoul==1) {
													Object[][] ss2=s.ss;
													label.setText("计算机信息查询列表");
													table.setModel(new DefaultTableModel(ss2,computersearch));
													s.setVisible(false);
												}else if(s.ssoul==2) {
													Object[][] ss2=s.ss;
													label.setText("消费信息查询列表");
													table.setModel(new DefaultTableModel(ss2,expensesearch));
													s.setVisible(false);
												}
												s.setVisible(false);
											}
										});
									}
								};
									Thread th = new Thread(r);
									th.start();
			}
		});
		Selectbutton.setFont(new Font("宋体", Font.BOLD, 17));
		
		Xiajibutton = new JButton("\u4E00\u952E\u4E0B\u673A");
		Xiajibutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		//一键下机按钮
				Dao dao = new Dao();
				int id=(int) table.getValueAt(table.getSelectedRow(),1);
				int bianhao=(int) table.getValueAt(table.getSelectedRow(),0);
				String starttime=(String) table.getValueAt(table.getSelectedRow(),4);
				String nowtime=String.valueOf(myfmt.format(new java.util.Date()));
				long between=(java.sql.Timestamp.valueOf(nowtime).getTime()-java.sql.Timestamp.valueOf(starttime).getTime())/1000;
				long hour2=between/3600;	//总相差小时
				long minute1=between%3600/60;
				long second=between%60/60;
				if(minute1>0) {		//没满一小时也算一小时的时间来计费
					hour2+=1;
				}
				float feiyong=Float.parseFloat((String) FeiyongcomboBox.getSelectedItem());
				float expense=hour2*feiyong;
				System.out.println(expense);
				int a=dao.findE_id(id);
				int i=dao.modifyExpense(expense,nowtime,a);
				int i2=dao.modifyComputeStater(bianhao,1);
				if(i==1&&i2==1){
					JOptionPane.showMessageDialog(null, "下机成功!!\n"+"  本次消费:"+expense+"元");
					}else {
						JOptionPane.showMessageDialog(null, "出现错误！");
					}
			}
		});
		Xiajibutton.setFont(new Font("宋体", Font.BOLD, 17));
		
		FeiYongpanel = new JPanel();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(145)
					.addComponent(LookComputerbutton, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
					.addGap(57)
					.addComponent(label, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
					.addGap(50)
					.addComponent(LookExpensebutton, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
					.addGap(169))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(FeiYongpanel, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(ModifyDelectpanel, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
					.addGap(35)
					.addComponent(Xiajibutton, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(Selectbutton, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addGap(69))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1130, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(label)
						.addComponent(LookComputerbutton, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(LookExpensebutton, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(FeiYongpanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(Xiajibutton, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
								.addComponent(Selectbutton, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
							.addComponent(ModifyDelectpanel, GroupLayout.PREFERRED_SIZE, 69, Short.MAX_VALUE)))
					.addContainerGap())
		);
		
		lblh = new JLabel("\u6BCF\u5C0F\u65F6\u8D39\u7528(\u5143/h)\uFF1A");
		lblh.setFont(new Font("宋体", Font.BOLD, 18));
		
		JButton FeiYongModifybutton = new JButton("\u4FEE\u6539");
		FeiYongModifybutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//每小时费用修改按钮
				FeiyongcomboBox.setEditable(true);
			}
		});
		FeiYongModifybutton.setFont(new Font("宋体", Font.BOLD, 16));
		
		FeiyongcomboBox = new JComboBox();
		FeiyongcomboBox.setEditable(false);
		FeiyongcomboBox.setFont(new Font("宋体", Font.ITALIC, 20));
		FeiyongcomboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		FeiyongcomboBox.setSelectedIndex(1);
		GroupLayout gl_FeiYongpanel = new GroupLayout(FeiYongpanel);
		gl_FeiYongpanel.setHorizontalGroup(
			gl_FeiYongpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_FeiYongpanel.createSequentialGroup()
					.addComponent(lblh)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(FeiyongcomboBox, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(FeiYongModifybutton, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		gl_FeiYongpanel.setVerticalGroup(
			gl_FeiYongpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_FeiYongpanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_FeiYongpanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblh, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(FeiYongModifybutton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(FeiyongcomboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		FeiYongpanel.setLayout(gl_FeiYongpanel);
		
		JButton Modifybutton = new JButton("\u4FEE\u6539");		//修改按钮
		Modifybutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dao dao = new Dao();
				
				int id=(int) table.getValueAt(table.getSelectedRow(),0);
				String name=(String) table.getValueAt(table.getSelectedRow(),1);
				String vs=(String) table.getValueAt(table.getSelectedRow(),2);
				int i=dao.modifyComputer(id, new Computer(id,name,vs));
				System.out.println(id+name+vs);
				if(i==1){
					JOptionPane.showMessageDialog(null, "修改成功");
					showComputer();
					ModifyDelectpanel.setVisible(true);
					table.setEnabled(true);
					}else {
						JOptionPane.showMessageDialog(null, "修改失败！");
					}
			}
		});
		Modifybutton.setFont(new Font("宋体", Font.BOLD, 17));
		
		JButton button_1 = new JButton("\u5220\u9664");		//删除按钮
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dao dao = new Dao();
				int id=(int) table.getValueAt(table.getSelectedRow(),0);
				int i=dao.deleteComputer(id);
				if(i==1){
					JOptionPane.showMessageDialog(null, "删除成功");
					showComputer();
					ModifyDelectpanel.setVisible(true);
					table.setEnabled(true);
					}else {
						JOptionPane.showMessageDialog(null, "删除失败！");
					}
			}
		});
		button_1.setFont(new Font("宋体", Font.BOLD, 17));
		GroupLayout gl_ModifyDelectpanel = new GroupLayout(ModifyDelectpanel);
		gl_ModifyDelectpanel.setHorizontalGroup(
			gl_ModifyDelectpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ModifyDelectpanel.createSequentialGroup()
					.addContainerGap(242, Short.MAX_VALUE)
					.addComponent(Modifybutton, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addGap(60)
					.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addGap(70))
		);
		gl_ModifyDelectpanel.setVerticalGroup(
			gl_ModifyDelectpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ModifyDelectpanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ModifyDelectpanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(Modifybutton, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		ModifyDelectpanel.setLayout(gl_ModifyDelectpanel);
		

		panel.setLayout(gl_panel);
		panel.setVisible(false);			//默认设置为不可见
		
		contentpanel.setLayout(gl_contentpanel);
		
		JButton btnNewButton = new JButton("\u8BA1\u7B97\u673A\u4FE1\u606F\u5F55\u5165");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//录入功能
				InsertComputer insertComputer = new InsertComputer();
				insertComputer.Insert();
			}
		});
		
		JButton btnNewButton_1 = new JButton("\u4FE1\u606F\u6D4F\u89C8");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//浏览功能
				showComputer();
				LookComputerbutton.setVisible(true);
				LookExpensebutton.setVisible(true);
			}
		});
		
		JButton btnNewButton_2 = new JButton("\u8BA1\u7B97\u673A\u4FE1\u606F\u4FEE\u6539");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//修改功能
				showComputer();
				table.setEnabled(true);
				ModifyDelectpanel.setVisible(true);
			}
		});
		
		JButton btnNewButton_4 = new JButton("\u5B66\u751F\u4E0A\u673A");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//上机功能
				ShangJi shang=new ShangJi();
				WindowEventHandler ww;
				shang.addWindowListener(ww=new WindowEventHandler());
				Runnable r =new Runnable() {
									
									@Override
									public void run() {
										// TODO 自动生成的方法存根
										while (true) {
											//更新标签
											SwingUtilities.invokeLater(new Runnable() {		//先执行这个窗体线程，如果满足条件，就退出循环
												
												@Override
												public void run() {
													// TODO 自动生成的方法存根
													shang.textField_3.setText(String.valueOf(myfmt.format(new java.util.Date())));
													shang.setVisible(true);
												}
											});
											if (shang.ii==1) {		//上机成功后退出循环
												
												break;
											}
											if(ww.i==1) {		//如果是手动关闭了模态对话窗口，退出循环							
												break;
											}
											System.out.println("线程正在运行");
											try {
												Thread.sleep(500);
											} catch (InterruptedException e) {
												// TODO 自动生成的 catch 块
												e.printStackTrace();
											}
									}
										SwingUtilities.invokeLater(new Runnable() {			//这个窗体线程用来把弹出的模态对话窗口关闭										
											@Override
											public void run() {
												shang.setVisible(false);
											}
										});
									}
								};
									Thread th = new Thread(r);
									th.start();
			}
		});
		
		JButton btnNewButton_5 = new JButton("\u5B66\u751F\u4E0B\u673A");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//下机功能
				showOnLine();
			}
		});
		
		JButton btnNewButton_3 = new JButton("\u4FE1\u606F\u67E5\u627E");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//搜索功能
				showSearch();		//初始化表格
			}
		});
		
		JButton btnNewButton_6 = new JButton("\u6536\u8D39\u4FE1\u606F\u7EDF\u8BA1");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//统计功能
				showTongJi();
			}
		});
		
		JButton btnNewButton_7 = new JButton("\u9000\u51FA\u7CFB\u7EDF");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//退出
				System.exit(0);
			}
		});
		GroupLayout gl_buttonpanel = new GroupLayout(buttonpanel);
		gl_buttonpanel.setHorizontalGroup( 
			gl_buttonpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonpanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_buttonpanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
						.addComponent(btnNewButton_4, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_5, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_6, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_7, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_2, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_buttonpanel.setVerticalGroup(
			gl_buttonpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonpanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton_5, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_6, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton_7, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		buttonpanel.setLayout(gl_buttonpanel);
		contentPane.setLayout(gl_contentPane);
	}
	private void showComputer() {
		label.setText("计算机信息列表");
		Dao dao = new Dao();
		ComputerManager computerManager = new ComputerManager();
		List<Computer> list = dao.getAllComputers(1);		//传入参数1，获取数据库中所有的计算机列表
		Object[][] results=computerManager.list(list);				//各行的数据	
		table = new JTable(results,computersearch);			//创建表格，并赋值
		tableStyle();
		table.setEnabled(false);
		buttonShow("1");
	}
	private void showExpense() {
		label.setText("消费信息列表");
		Dao dao = new Dao();
		ExpenseManage expenseManage = new ExpenseManage();
		List<Expense_info> list = dao.getAllExpenses(1);
		Object[][] results=expenseManage.list(list);
		table = new JTable(results,expensesearch);
		table.getColumnModel().getColumn(4).setMinWidth(180);	//第五第六列的宽度设置为180
		table.getColumnModel().getColumn(5).setMinWidth(180);
		tableStyle();			//表格样式
		table.setEnabled(false);
		buttonShow("1");		//内容面板相关按钮的显示，默认都不显示
	}
	private void showSearch() {
	label.setText("信息查询");
	table = new JTable();
	tableStyle();
	buttonShow("Select");		//查询按钮显示
}
	private void showOnLine() {		//显示学生在线列表
	label.setText("当前在线学生");
	Dao dao = new Dao();
	ExpenseManage expenseManage = new ExpenseManage();
	List<Expense_info> list = dao.getAllOnline();
	Object[][] results=expenseManage.online_list(list);				//各行的数据
	String [] onlnesearch = {"计算机编号", "学号", "姓名","班级","上机起始时间","上机时长"};		//消费信息
	table = new JTable(results,onlnesearch);
	table.getColumnModel().getColumn(4).setMinWidth(200);
	tableStyle();
	buttonShow("xiaji");
}
	private void showTongJi() {
		String year=(String.valueOf(myfmt.format(new java.util.Date()))).substring(0, 4);
		label.setText(year+"年消费统计");
		Dao dao = new Dao();
		ExpenseManage expenseManage = new ExpenseManage();
		List<Expense_info> list = dao.getAllExpenses(2);
		Object[][] results=expenseManage.countExpense(list);
		String [] booksearch = {"月份", "总计"};		//标题名称
		table = new JTable(results,booksearch);
		tableStyle();
		table.setColumnSelectionAllowed(true);
		buttonShow("1");
}
	private void buttonShow(String n) {					//控制显示按钮和panel组件
		LookComputerbutton.setVisible(false);
		LookExpensebutton.setVisible(false);
		ModifyDelectpanel.setVisible(false);
		FeiYongpanel.setVisible(false);
		Selectbutton.setVisible(false);
		Xiajibutton.setVisible(false);
		if(n=="Select") {
			Selectbutton.setVisible(true);
		}
		if(n=="xiaji") {
			FeiYongpanel.setVisible(true);
			Xiajibutton.setVisible(true);
		}
}
	private void tableStyle() {		//设置表格的统一样式，将重复代码进行封装，提高程序可读性
		table.setRowHeight(30);
		table.setFont(new Font("宋体", Font.PLAIN, 20));
		scrollPane.setViewportView(table);
		panel.setVisible(true);
	}
}