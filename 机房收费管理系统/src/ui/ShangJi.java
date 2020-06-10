package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.Dao;
import gongneng.ComputerManager;
import xinxi.Computer;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.awt.event.ActionEvent;

public class ShangJi extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public JTextField textField_3;
	
	public int ii=0;
	public int ssoul=0;
			//获取数据库中已有的计算机编号
	Dao dao = new Dao();
	ComputerManager computerManager = new ComputerManager();
	List<Computer> list = dao.getAllComputers();
	String[] ss=computerManager.id_list(list);				//编号的String数组
//	SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	//获取当前时间
	SimpleDateFormat myfmt=new SimpleDateFormat("hh:mm:ss");	//获取当前时间
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ShangJi dialog = new ShangJi();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ShangJi() {
		setModal(true);
		setBounds(1200, 450, 570, 355);
		setTitle("学生上机");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel label = new JLabel("\u8BA1\u7B97\u673A\u7F16\u53F7\uFF1A");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("宋体", Font.BOLD, 20));
		
		JLabel label_1 = new JLabel("\u5B66\u53F7\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("宋体", Font.BOLD, 20));
		
		JLabel label_2 = new JLabel("\u59D3\u540D\uFF1A");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("宋体", Font.BOLD, 20));
		
		JLabel label_3 = new JLabel("\u73ED\u7EA7\uFF1A");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("宋体", Font.BOLD, 20));
		
		JLabel label_4 = new JLabel("\u4E0A\u673A\u8D77\u59CB\u65F6\u95F4\uFF1A");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("宋体", Font.BOLD, 20));
		
		JComboBox comboBox = new JComboBox(ss);				//初始化下拉框
		comboBox.setFont(new Font("宋体", Font.BOLD, 17));
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.BOLD, 17));
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.BOLD, 17));
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("宋体", Font.BOLD, 17));
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("宋体", Font.BOLD, 20));
		textField_3.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(175, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if(textField.getText().length()==0){
							JOptionPane.showMessageDialog(null, "学号不能为空");
							return;
						}
						if(textField.getText().length()!=10){
							JOptionPane.showMessageDialog(null, "学号需要是10位数\n   当前位数："+(textField.getText().length())+"位");
							return;
						}
						if(textField_1.getText().length()==0){
							JOptionPane.showMessageDialog(null, "姓名不能为空");
							return;
						}
						if(textField_2.getText().length()==0){
							JOptionPane.showMessageDialog(null, "班级不能为空");
							return;
						}
						Object bianhao = comboBox.getSelectedItem();		//编号
						String xuehao=textField.getText();		//学号
						String name=textField_1.getText();		//姓名
						String sclass=textField_2.getText();		//班级
						String starttime=String.valueOf(myfmt.format(new java.util.Date()));		//开始时间
						int i=dao.addExpense(bianhao, xuehao, name, sclass, starttime);
						if(i==1){
							JOptionPane.showMessageDialog(null, "添加成功");
							ii=1;
						}else {
							JOptionPane.showMessageDialog(null, "添加失败");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("\u91CD\u7F6E");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						comboBox.setSelectedIndex(0);
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void getTime() {
		Dao dao = new Dao();
//		int index=comboBox.getSelectedIndex();
//		String s3=textField.getText();
		ComputerManager computerManager = new ComputerManager();
		List<Computer> list = dao.getAllComputers();
		String[] ss=computerManager.id_list(list);				//各行的数据
		System.out.println("hh"+ss);
	}
}
