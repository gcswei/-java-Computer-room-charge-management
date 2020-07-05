package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.Dao;
import gongneng.ComputerManager;
import gongneng.ExpenseManage;
import xinxi.Computer;
import xinxi.Expense_info;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class Search extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	public int i=0;
	public int ssoul=0;
	//定义全局变量，存储查询到的结果
	Object[][] ss;

	/**
	 * Create the dialog.
	 */
	public Search() {
		setModal(true);
		setBounds(1200, 450, 570, 355);
		setTitle("信息搜索");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel label = new JLabel("\u8BA1\u7B97\u673A\u4FE1\u606F\u641C\u7D22\uFF1A");
		label.setFont(new Font("宋体", Font.BOLD, 17));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel label_1 = new JLabel("\u6536\u8D39\u4FE1\u606F\u641C\u7D22\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("宋体", Font.BOLD, 17));
		JLabel label_2 = new JLabel("\u6309");
		label_2.setFont(new Font("宋体", Font.PLAIN, 16));
		
		comboBox = new JComboBox(new String[] {"计算机编号", "操作系统","使用情况"});
		
		JLabel lblNewLabel = new JLabel("\u641C\u7D22\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		
		textField=new JTextField();
		textField.setColumns(10);
		
		JLabel label_2_1 = new JLabel("\u6309");
		label_2_1.setFont(new Font("宋体", Font.PLAIN, 16));
		
		comboBox_1 = new JComboBox(new String[] {"学号", "姓名","班级"});
		
		JLabel lblNewLabel_1 = new JLabel("\u641C\u7D22\uFF1A");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton button = new JButton("\u641C\u7D22");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sousuo1();
			}
		});
		
		JButton button_1 = new JButton("\u641C\u7D22");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sousuo2();
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(label)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(39)
									.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addGap(2)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
									.addGap(33)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
										.addComponent(button, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(53)
							.addComponent(label_2_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(83, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
	}
	public void sousuo1() {
		if(textField.getText().length()==0){
			JOptionPane.showMessageDialog(null, "输入框不能为空");
			return;
		}
		Dao dao = new Dao();
		int index=comboBox.getSelectedIndex();
		String s3=textField.getText();
		ComputerManager computerManager = new ComputerManager();
		List<Computer> list = dao.find(index,s3);
		ss=computerManager.list(list);				//各行的数据
		if(list.size()==0) {
			JOptionPane.showMessageDialog(null, "没找到结果！");
		}else {
			i=1;
			ssoul=1;
		}
	}
	public void sousuo2() {
		if(textField_1.getText().length()==0){
			JOptionPane.showMessageDialog(null, "输入框不能为空");
			return;
		}
		Dao dao = new Dao();
		int index=comboBox_1.getSelectedIndex();
		String s3=textField_1.getText();
		ExpenseManage expenseManage = new ExpenseManage();
		List<Expense_info> list = dao.findExpense(index,s3);
		ss=expenseManage.list(list);				//各行的数据
		if(list.size()==0) {
			JOptionPane.showMessageDialog(null, "没找到结果！");
		}else {
			i=1;
			ssoul=2;
		}
	}
}
class WindowEventHandler extends WindowAdapter {
	public int i=0;
	  public void windowClosing(WindowEvent evt) {
	    System.out.println("窗口关闭");
	    i=1;
	  }
	}
