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

import xinxi.Computer;
import xinxi.Expense_info;
import dao.Dao;
import gongneng.ComputerManager;
import gongneng.ExpenseManage;

public class SuperGui extends JFrame {

	private JPanel contentPane;
	private JPanel contentpanel;
	private JTable table;
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	private JPanel panel;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	private JButton btnNewButton_8;
	private JButton btnNewButton_8_1;
	private JLabel label;
	private JTable table_1;
	private JButton button_1_1;
		//新建表格标题
	String [] computersearch = {"计算机编号", "系统", "使用情况"};		//计算机信息
	String [] expensesearch = {"计算机编号", "学号", "姓名","班级","上机起始时间","下机结束时间","费用"};		//消费信息

	SimpleDateFormat myfmt=new SimpleDateFormat("hh:mm:ss");	//获取当前时间
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
		setBounds(770, 300, 1192, 654);
		setTitle("管理员操作界面");
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
				.addGroup(gl_contentpanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 922, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentpanel.setVerticalGroup(
			gl_contentpanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentpanel.createSequentialGroup()
					.addGap(27)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 547, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		
		label = new JLabel("\u8BA1\u7B97\u673A\u4FE1\u606F\u5217\u8868");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("宋体", Font.BOLD, 20));
		
		scrollPane = new JScrollPane();
		
		panel_1 = new JPanel();
		
		btnNewButton_8 = new JButton("\u8BA1\u7B97\u673A\u4FE1\u606F\u6D4F\u89C8");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		//计算机信息浏览
				showComputer();
			}
		});
		
		btnNewButton_8_1 = new JButton("\u6536\u8D39\u4FE1\u606F\u6D4F\u89C8");
		btnNewButton_8_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		//收费信息浏览
				showExpense();
			}
		});
		
		button_1_1 = new JButton("\u67E5\u8BE2");		//查询按钮
		button_1_1.addActionListener(new ActionListener() {
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
		button_1_1.setFont(new Font("宋体", Font.BOLD, 17));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(145)
							.addComponent(btnNewButton_8, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
							.addGap(57)
							.addComponent(label, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
							.addGap(50)
							.addComponent(btnNewButton_8_1, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
							.addGap(155))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)))
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(101)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 643, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(button_1_1, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addGap(68))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(label)
						.addComponent(btnNewButton_8, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_8_1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 386, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(24)
							.addComponent(button_1_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		table_1.setRowHeight(30);// 设置每行的高度为20  
		scrollPane.setViewportView(table_1);
		
		JButton button = new JButton("\u4FEE\u6539");		//修改按钮
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dao dao = new Dao();
				System.out.println(table.getSelectedRow());
				System.out.println(table.getSelectedColumn());
				
				int id=(int) table.getValueAt(table.getSelectedRow(),0);
				String name=(String) table.getValueAt(table.getSelectedRow(),1);
				String vs=(String) table.getValueAt(table.getSelectedRow(),2);
				dao.modifyComputer(id, new Computer(id,name,vs));
				System.out.println(id+name+vs);
				JOptionPane.showMessageDialog(null, "修改成功");
				
			}
		});
		button.setFont(new Font("宋体", Font.BOLD, 17));
		
		JButton button_1 = new JButton("\u5220\u9664");		//删除按钮
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dao dao = new Dao();
				int id=(int) table.getValueAt(table.getSelectedRow(),0);
				dao.deleteComputer(id);
				JOptionPane.showMessageDialog(null, "删除成功");
			}
		});
		button_1.setFont(new Font("宋体", Font.BOLD, 17));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(242)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addGap(60)
					.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(231, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		

		panel.setLayout(gl_panel);
		panel.setVisible(false);			//默认设置为不可见
		panel_1.setVisible(false);
		
		contentpanel.setLayout(gl_contentpanel);
		
		JButton btnNewButton = new JButton("\u8BA1\u7B97\u673A\u4FE1\u606F\u5F55\u5165");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//录入
				InsertComputer insertComputer = new InsertComputer();
				insertComputer.Insert();
			}
		});
		
		JButton btnNewButton_1 = new JButton("\u4FE1\u606F\u6D4F\u89C8");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//浏览
				showComputer();
				btnNewButton_8.setVisible(true);
				btnNewButton_8_1.setVisible(true);
			}
		});
		
		JButton btnNewButton_2 = new JButton("\u8BA1\u7B97\u673A\u4FE1\u606F\u4FEE\u6539");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//修改
				btnNewButton_8.setVisible(false);
				btnNewButton_8_1.setVisible(false);
				showComputer();

				table.setEnabled(true);
				panel_1.setVisible(true);
			}
		});
		
		JButton btnNewButton_4 = new JButton("\u5B66\u751F\u4E0A\u673A");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//上机
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
			public void actionPerformed(ActionEvent e) {	//下机
				XiaJi xia=new XiaJi();
				xia.setVisible(true);
			}
		});
		
		JButton btnNewButton_3 = new JButton("\u4FE1\u606F\u67E5\u627E");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//搜索
				btnNewButton_8.setVisible(false);
				btnNewButton_8_1.setVisible(false);
				showSearch();		//初始化表格
			}
		});
		
		JButton btnNewButton_6 = new JButton("\u6536\u8D39\u4FE1\u606F\u7EDF\u8BA1");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//统计
				btnNewButton_8.setVisible(false);
				btnNewButton_8_1.setVisible(false);
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
		List<Computer> list = dao.getAllComputers();
		Object[][] results=computerManager.list(list);				//各行的数据	
		table = new JTable(results,computersearch);			//创建表格，并赋值

		table.setRowHeight(30);// 设置每行的高度为30
		table.setEnabled(false);
		table.setFont(new Font("宋体", Font.PLAIN, 20));
//		table.setColumnSelectionAllowed(true);
		scrollPane.setViewportView(table);
		panel.setVisible(true);
		panel_1.setVisible(false);
		button_1_1.setVisible(false);
	}
	private void showExpense() {
		label.setText("消费信息列表");
		Dao dao = new Dao();
		ExpenseManage expenseManage = new ExpenseManage();
		List<Expense_info> list = dao.getAllExpenses();
		Object[][] results=expenseManage.list(list);				//各行的数据
		table = new JTable(results,expensesearch);			//创建表格，并赋值
		table.setRowHeight(30);// 设置每行的高度为30
		table.setEnabled(false);
		table.setFont(new Font("宋体", Font.PLAIN, 17));
		table.setColumnSelectionAllowed(true);
		scrollPane.setViewportView(table);
		panel.setVisible(true);
		panel_1.setVisible(false);
		button_1_1.setVisible(false);
	}
	private void showSearch() {
	label.setText("信息查询");
	table = new JTable();			//创建表格，并赋值
	table.setFont(new Font("宋体", Font.PLAIN, 20));
	scrollPane.setViewportView(table);
	panel.setVisible(true);
	panel_1.setVisible(false);
	button_1_1.setVisible(true);
}
	private void showTongJi() {
		label.setText("消费统计");
		Dao dao = new Dao();
		ComputerManager computerManager = new ComputerManager();
		List<Computer> list = dao.getAllComputers();
		Object[][] results=computerManager.list(list);				//各行的数据
		Object[][] ss=new Object[1][2];
		ss[0][0]=1;
		ss[0][1]=2;
		String [] booksearch = {"月份", "总计"};		//标题名称
		
		table = new JTable(ss,booksearch);			//创建表格，并赋值

		table.setFont(new Font("宋体", Font.PLAIN, 20));
		table.setColumnSelectionAllowed(true);
		scrollPane.setViewportView(table);
		panel.setVisible(true);
		panel_1.setVisible(false);
		button_1_1.setVisible(false);
	}
}