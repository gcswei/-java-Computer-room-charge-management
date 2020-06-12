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
		//�½�������
	String [] computersearch = {"��������", "ϵͳ", "ʹ�����"};		//�������Ϣ
	String [] expensesearch = {"��������", "ѧ��", "����","�༶","�ϻ���ʼʱ��","�»�����ʱ��","����(Ԫ)"};		//������Ϣ

	SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	//��ȡ��ǰʱ��
	private JButton button_1_1_1;
	private JPanel panel_2;
	private JLabel lblh;
	private JComboBox comboBox;
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
		setTitle("����Ա��������");
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
		label.setFont(new Font("����", Font.BOLD, 20));
		
		scrollPane = new JScrollPane();
		
		panel_1 = new JPanel();
		
		btnNewButton_8 = new JButton("\u8BA1\u7B97\u673A\u4FE1\u606F\u6D4F\u89C8");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		//�������Ϣ���
				showComputer();
			}
		});
		
		btnNewButton_8_1 = new JButton("\u6536\u8D39\u4FE1\u606F\u6D4F\u89C8");
		btnNewButton_8_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		//�շ���Ϣ���
				showExpense();
			}
		});
		
		button_1_1 = new JButton("\u67E5\u8BE2");		//��ѯ��ť
		button_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search s = new Search();		//����ģ̬�Ի�����
				WindowEventHandler ww;
				s.addWindowListener(ww=new WindowEventHandler());
				Runnable r =new Runnable() {							
									@Override
									public void run() {
										while (true) {
											SwingUtilities.invokeLater(new Runnable() {
												@Override
												public void run() {
													// TODO �Զ����ɵķ������													
													s.setVisible(true);
												}
											});
											if (s.i==1) {		//������ҵ��˽�����͵�����ʾ��Ȼ���˳�ѭ��
												JOptionPane.showMessageDialog(null, "���ҵ������");
												s.setVisible(false);
												break;
											}
											if(ww.i==1) {		//������ֶ��ر���ģ̬�Ի����ڣ��˳�ѭ��								
												break;
											}
											System.out.println("�߳���������");
											try {
												Thread.sleep(500);
											} catch (InterruptedException e) {
												// TODO �Զ����ɵ� catch ��
												e.printStackTrace();
											}
									}
										SwingUtilities.invokeLater(new Runnable() {											
											@Override
											public void run() {
												if(s.ssoul==1) {
													Object[][] ss2=s.ss;
													label.setText("�������Ϣ��ѯ�б�");
													table.setModel(new DefaultTableModel(ss2,computersearch));
													s.setVisible(false);
												}else if(s.ssoul==2) {
													Object[][] ss2=s.ss;
													label.setText("������Ϣ��ѯ�б�");
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
		button_1_1.setFont(new Font("����", Font.BOLD, 17));
		
		button_1_1_1 = new JButton("\u4E00\u952E\u4E0B\u673A");
		button_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dao dao = new Dao();
				int id=(int) table.getValueAt(table.getSelectedRow(),1);
				int bianhao=(int) table.getValueAt(table.getSelectedRow(),0);
				String starttime=(String) table.getValueAt(table.getSelectedRow(),4);
				String nowtime=String.valueOf(myfmt.format(new java.util.Date()));
				long between=(java.sql.Timestamp.valueOf(nowtime).getTime()-java.sql.Timestamp.valueOf(starttime).getTime())/1000;
				long hour2=between/3600;	//�����Сʱ
				long minute1=between%3600/60;
				long second=between%60/60;
				if(minute1>0) {		//û��һСʱҲ��һСʱ��ʱ�����Ʒ�
					hour2+=1;
				}
				float feiyong=Float.parseFloat((String) comboBox.getSelectedItem());
				float expense=hour2*feiyong;
				System.out.println(expense);
				int a=dao.findE_id(id);
				int i=dao.modifyExpense(expense,nowtime,a);
				int i2=dao.modifyComputeStater(bianhao,1);
				if(i==1&&i2==1){
					JOptionPane.showMessageDialog(null, "�»��ɹ�!!\n"+"  ��������:"+expense+"Ԫ");
					}else {
						JOptionPane.showMessageDialog(null, "���ִ���");
					}
			}
		});
		button_1_1_1.setFont(new Font("����", Font.BOLD, 17));
		
		panel_2 = new JPanel();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(145)
					.addComponent(btnNewButton_8, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
					.addGap(57)
					.addComponent(label, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
					.addGap(50)
					.addComponent(btnNewButton_8_1, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
					.addGap(169))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
					.addGap(35)
					.addComponent(button_1_1_1, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(button_1_1, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
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
						.addComponent(btnNewButton_8, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_8_1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(button_1_1_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_1_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 69, Short.MAX_VALUE)))
					.addContainerGap())
		);
		
		lblh = new JLabel("\u6BCF\u5C0F\u65F6\u8D39\u7528(\u5143/h)\uFF1A");
		lblh.setFont(new Font("����", Font.BOLD, 18));
		
		JButton btnNewButton_9 = new JButton("\u4FEE\u6539");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setEditable(true);
			}
		});
		btnNewButton_9.setFont(new Font("����", Font.BOLD, 16));
		
		comboBox = new JComboBox();
		comboBox.setEditable(false);
		comboBox.setFont(new Font("����", Font.ITALIC, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		comboBox.setSelectedIndex(1);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(lblh)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton_9, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblh, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(btnNewButton_9, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		table_1 = new JTable();
		table_1.setCellSelectionEnabled(true);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		table_1.getColumnModel().getColumn(0).setMinWidth(230);
		table_1.setRowHeight(30);// ����ÿ�еĸ߶�Ϊ20  
		scrollPane.setViewportView(table_1);
		
		JButton button = new JButton("\u4FEE\u6539");		//�޸İ�ť
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dao dao = new Dao();
				System.out.println(table.getSelectedRow());
				System.out.println(table.getSelectedColumn());
				
				int id=(int) table.getValueAt(table.getSelectedRow(),0);
				String name=(String) table.getValueAt(table.getSelectedRow(),1);
				String vs=(String) table.getValueAt(table.getSelectedRow(),2);
				int i=dao.modifyComputer(id, new Computer(id,name,vs));
				System.out.println(id+name+vs);
				if(i==1){
					JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
					}else {
						JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");
					}
			}
		});
		button.setFont(new Font("����", Font.BOLD, 17));
		
		JButton button_1 = new JButton("\u5220\u9664");		//ɾ����ť
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dao dao = new Dao();
				int id=(int) table.getValueAt(table.getSelectedRow(),0);
				int i=dao.deleteComputer(id);
				if(i==1){
					JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
					}else {
						JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�");
					}
			}
		});
		button_1.setFont(new Font("����", Font.BOLD, 17));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(242, Short.MAX_VALUE)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addGap(60)
					.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addGap(70))
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
		panel.setVisible(false);			//Ĭ������Ϊ���ɼ�
		panel_1.setVisible(false);
		
		contentpanel.setLayout(gl_contentpanel);
		
		JButton btnNewButton = new JButton("\u8BA1\u7B97\u673A\u4FE1\u606F\u5F55\u5165");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//¼��
				InsertComputer insertComputer = new InsertComputer();
				insertComputer.Insert();
			}
		});
		
		JButton btnNewButton_1 = new JButton("\u4FE1\u606F\u6D4F\u89C8");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//���
				showComputer();
				btnNewButton_8.setVisible(true);
				btnNewButton_8_1.setVisible(true);
			}
		});
		
		JButton btnNewButton_2 = new JButton("\u8BA1\u7B97\u673A\u4FE1\u606F\u4FEE\u6539");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//�޸�
				btnNewButton_8.setVisible(false);
				btnNewButton_8_1.setVisible(false);
				showComputer();
				table.setEnabled(true);
				panel_1.setVisible(true);
			}
		});
		
		JButton btnNewButton_4 = new JButton("\u5B66\u751F\u4E0A\u673A");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//�ϻ�
				ShangJi shang=new ShangJi();
				WindowEventHandler ww;
				shang.addWindowListener(ww=new WindowEventHandler());
				Runnable r =new Runnable() {
									
									@Override
									public void run() {
										// TODO �Զ����ɵķ������
										while (true) {
											//���±�ǩ
											SwingUtilities.invokeLater(new Runnable() {		//��ִ����������̣߳�����������������˳�ѭ��
												
												@Override
												public void run() {
													// TODO �Զ����ɵķ������
													shang.textField_3.setText(String.valueOf(myfmt.format(new java.util.Date())));
													shang.setVisible(true);
												}
											});
											if (shang.ii==1) {		//�ϻ��ɹ����˳�ѭ��
												
												break;
											}
											if(ww.i==1) {		//������ֶ��ر���ģ̬�Ի����ڣ��˳�ѭ��							
												break;
											}
											System.out.println("�߳���������");
											try {
												Thread.sleep(500);
											} catch (InterruptedException e) {
												// TODO �Զ����ɵ� catch ��
												e.printStackTrace();
											}
									}
										SwingUtilities.invokeLater(new Runnable() {			//��������߳������ѵ�����ģ̬�Ի����ڹر�										
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
			public void actionPerformed(ActionEvent e) {	//�»�
				onLine();
//				XiaJi xia=new XiaJi();
//				xia.setVisible(true);
			}
		});
		
		JButton btnNewButton_3 = new JButton("\u4FE1\u606F\u67E5\u627E");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//����
				showSearch();		//��ʼ�����
			}
		});
		
		JButton btnNewButton_6 = new JButton("\u6536\u8D39\u4FE1\u606F\u7EDF\u8BA1");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//ͳ��
				showTongJi();
			}
		});
		
		JButton btnNewButton_7 = new JButton("\u9000\u51FA\u7CFB\u7EDF");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//�˳�
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
		label.setText("�������Ϣ�б�");
		Dao dao = new Dao();
		ComputerManager computerManager = new ComputerManager();
		List<Computer> list = dao.getAllComputers(1);		//�������1����ȡ���ݿ������еļ�����б�
		Object[][] results=computerManager.list(list);				//���е�����	
		table = new JTable(results,computersearch);			//������񣬲���ֵ
		table.setRowHeight(30);// ����ÿ�еĸ߶�Ϊ30
		table.setEnabled(false);
		table.setFont(new Font("����", Font.PLAIN, 20));
//		table.setColumnSelectionAllowed(true);
		scrollPane.setViewportView(table);
		panel.setVisible(true);
		panel_1.setVisible(false);
		panel_2.setVisible(false);
		button_1_1.setVisible(false);
		button_1_1_1.setVisible(false);
	}
	private void showExpense() {
		label.setText("������Ϣ�б�");
		Dao dao = new Dao();
		ExpenseManage expenseManage = new ExpenseManage();
		List<Expense_info> list = dao.getAllExpenses(1);
		Object[][] results=expenseManage.list(list);				//���е�����
		table = new JTable(results,expensesearch);			//������񣬲���ֵ
		table.getColumnModel().getColumn(4).setMinWidth(180);
		table.getColumnModel().getColumn(5).setMinWidth(180);
		table.setRowHeight(30);// ����ÿ�еĸ߶�Ϊ30
		table.setEnabled(false);
		table.setFont(new Font("����", Font.PLAIN, 20));
		table.setColumnSelectionAllowed(true);
		scrollPane.setViewportView(table);
		panel.setVisible(true);
		panel_1.setVisible(false);
		panel_2.setVisible(false);
		button_1_1.setVisible(false);
		button_1_1_1.setVisible(false);
	}
	private void showSearch() {
	label.setText("��Ϣ��ѯ");
	table = new JTable();			//������񣬲���ֵ
	table.setRowHeight(30);
	table.setFont(new Font("����", Font.PLAIN, 20));
	scrollPane.setViewportView(table);
	panel.setVisible(true);
	panel_1.setVisible(false);
	panel_2.setVisible(false);
	button_1_1.setVisible(true);
	button_1_1_1.setVisible(false);
	btnNewButton_8.setVisible(false);
	btnNewButton_8_1.setVisible(false);
}
	private void onLine() {		//��ʾѧ�������б�
	label.setText("��ǰ����ѧ��");
	Dao dao = new Dao();
	ExpenseManage expenseManage = new ExpenseManage();
	List<Expense_info> list = dao.getAllOnline();
	Object[][] results=expenseManage.online_list(list);				//���е�����
	String [] onlnesearch = {"��������", "ѧ��", "����","�༶","�ϻ���ʼʱ��","�ϻ�ʱ��"};		//������Ϣ
	table = new JTable(results,onlnesearch);			//������񣬲���ֵ
	table.getColumnModel().getColumn(4).setMinWidth(200);
	table.setRowHeight(30);
	table.setFont(new Font("����", Font.PLAIN, 20));
	scrollPane.setViewportView(table);
	panel.setVisible(true);
	panel_1.setVisible(false);
	panel_2.setVisible(true);
	button_1_1.setVisible(false);
	button_1_1_1.setVisible(true);
	btnNewButton_8.setVisible(false);
	btnNewButton_8_1.setVisible(false);
}
	private void showTongJi() {
		String year=(String.valueOf(myfmt.format(new java.util.Date()))).substring(0, 4);
		label.setText(year+"������ͳ��");
		Dao dao = new Dao();
		ExpenseManage expenseManage = new ExpenseManage();
		List<Expense_info> list = dao.getAllExpenses(2);
		Object[][] results=expenseManage.countExpense(list);				//���е�����
		String [] booksearch = {"�·�", "�ܼ�"};		//��������
		
		table = new JTable(results,booksearch);			//������񣬲���ֵ
		table.setRowHeight(30);
		table.setFont(new Font("����", Font.PLAIN, 20));
		table.setColumnSelectionAllowed(true);
		scrollPane.setViewportView(table);
		panel.setVisible(true);
		panel_1.setVisible(false);
		panel_2.setVisible(false);
		button_1_1.setVisible(false);
		button_1_1_1.setVisible(false);
		btnNewButton_8.setVisible(false);
		btnNewButton_8_1.setVisible(false);
	}
}