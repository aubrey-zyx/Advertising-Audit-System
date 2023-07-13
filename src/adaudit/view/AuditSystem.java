package adaudit.view;
import java.awt.EventQueue;

import java.awt.*;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;

import adaudit.dao.Database;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.event.ListSelectionEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;

public class AuditSystem {

	private JFrame mainFrm;
	
	private JPanel logInPanel;
	private JTextField tfUser;
	private JPasswordField passwordField;
	
	private CardLayout cardlayout1;
	private CardLayout cardlayout2;
	
	private JPanel auditPanel;
	private JPanel chooseAdPanel;
	private JList listQueue;
	private JLabel lblQueueName;
	private JLabel lblTaskNum;
	private JTable tableAdsOfQueue;
	private JScrollPane scpAdsOfQueue;
	
	private JPanel adPanel;
	private JPanel basicInfopanel;
	private JLabel lblAdId2;
	private JLabel lblLanguage2;
	private JLabel lbCreateTime2;
	private JLabel lblDeliveryCountry2;
	private JLabel lblAdvId2;
	private JLabel lblAdvName2;
	private JLabel lblAdvType2;
	private JPanel materialInfoPanel;
	private JLabel lblTitle2;
	private JLabel lblPicture2;
	private JLabel lblVideo2;
	private JLabel lblLP2;
	private ButtonGroup btnGroupAudit;
	private JRadioButton rdbtnApprove;
	private JRadioButton rdbtnDisapprove;
	private JComboBox cboxRiskLabel;
	private JComboBox cboxPunishment;
	private JTextArea tareaReason;
	
	
	private JScrollPane scpAdvInfo;
	private JScrollPane scpAdvInfo_1;
	private JTextField tfAdvId;
	private JTextField tfAdvName;
	private JTextField textField;
	private JTextField tfTimeAfter;
	private JTextField tfTimeBefore;
	private JTable tableAdvInfo;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_6;
	private JTable tableAuditor;
	
	private String startTime;
	private String finishTime;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuditSystem window = new AuditSystem();
					window.mainFrm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AuditSystem() {
		new Database();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrm = new JFrame();
		mainFrm.setTitle("\u5E7F\u544A\u5BA1\u6838\u7BA1\u7406\u7CFB\u7EDF");
		mainFrm.setBounds(100, 50, 1000, 600);
		mainFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		cardlayout1 = new CardLayout();
		mainFrm.getContentPane().setLayout(cardlayout1);
		
		//-----------------------------------------------------------logInPanel-----------------------------------------------------------
		logInPanel = new JPanel();
		logInPanel.setLayout(null);
		mainFrm.getContentPane().add(logInPanel, "logInCard");
		
		JLabel lblAccount = new JLabel("\u8D26        \u53F7\uFF1A");
		lblAccount.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblAccount.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblAccount.setBounds(360, 199, 90, 40);
		logInPanel.add(lblAccount);
		
		JLabel lblPassword = new JLabel("\u5BC6        \u7801\uFF1A");
		lblPassword.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblPassword.setAlignmentX(0.5f);
		lblPassword.setBounds(360, 264, 90, 40);
		logInPanel.add(lblPassword);
		
		tfUser = new JTextField();
		tfUser.setBounds(471, 203, 160, 30);
		logInPanel.add(tfUser);
		tfUser.setColumns(10);
		
		JLabel lblRole = new JLabel("\u89D2        \u8272\uFF1A");
		lblRole.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblRole.setAlignmentX(0.5f);
		lblRole.setBounds(319, 322, 90, 40);
		logInPanel.add(lblRole);
		
		passwordField = new JPasswordField(20);
		passwordField.setBounds(471, 270, 160, 30);
		logInPanel.add(passwordField);
		
		JRadioButton rdbtnAuditor = new JRadioButton("\u5BA1 \u6838 \u5458 / \u5176 \u4ED6");
		rdbtnAuditor.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		rdbtnAuditor.setBounds(560, 328, 130, 29);
		logInPanel.add(rdbtnAuditor);
		
		JRadioButton rdbtnAdministrator = new JRadioButton("\u7CFB \u7EDF \u7BA1 \u7406 \u5458");
		rdbtnAdministrator.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		rdbtnAdministrator.setBounds(425, 328, 118, 29);
		logInPanel.add(rdbtnAdministrator);
		
		ButtonGroup btnGroupRole = new ButtonGroup();
		btnGroupRole.add(rdbtnAuditor);
		btnGroupRole.add(rdbtnAdministrator);
		
		JButton btnLogIn = new JButton("\u767B       \u5F55");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login(e);
			}
		});
		btnLogIn.setBackground(SystemColor.activeCaption);
		btnLogIn.setFont(new Font("微软雅黑", Font.BOLD, 16));
		btnLogIn.setBounds(454, 397, 100, 40);
		logInPanel.add(btnLogIn);
		
		//-----------------------------------------------------------tabbedPane_1-----------------------------------------------------------
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setFont(new Font("宋体", Font.PLAIN, 16));
		mainFrm.getContentPane().add(tabbedPane_1, "systemCard");
		
		auditPanel = new JPanel();
		tabbedPane_1.addTab(" \u5E7F\u544A\u5BA1\u6838 ", null, auditPanel, null);
		cardlayout2 = new CardLayout();
		auditPanel.setLayout(cardlayout2);
		
		//------------------------------------chooseAdPanel------------------------------------
		chooseAdPanel = new JPanel();
		auditPanel.add(chooseAdPanel, "chooseAdCard");

		String[] queueListData = Database.getQueueList();
		listQueue = new JList(queueListData);
		listQueue.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				try {
					showAds(e);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		chooseAdPanel.setLayout(null);
		listQueue.setBounds(0, 0, 225, 530);
		listQueue.setBorder(BorderFactory.createTitledBorder("审核队列"));
		listQueue.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		chooseAdPanel.add(listQueue);

		lblQueueName = new JLabel();
		lblQueueName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblQueueName.setBounds(235, 10, 348, 30);
		chooseAdPanel.add(lblQueueName);
		
		lblTaskNum = new JLabel();
		lblTaskNum.setFont(new Font("宋体", Font.PLAIN, 15));
		lblTaskNum.setBounds(821, 10, 150, 30);
		chooseAdPanel.add(lblTaskNum);

		tableAdsOfQueue = new JTable();
		tableAdsOfQueue.setVisible(false);
		tableAdsOfQueue.setBounds(1, 1, 450, 0);
		chooseAdPanel.add(tableAdsOfQueue);
		
		scpAdsOfQueue = new JScrollPane(tableAdsOfQueue);
		scpAdsOfQueue.setBounds(235, 52, 736, 420);
		scpAdsOfQueue.setVisible(false);
		scpAdsOfQueue.setViewportView(tableAdsOfQueue);
		chooseAdPanel.add(scpAdsOfQueue);
		
		//"审核”按钮
		JButton btnAudit = new JButton("\u5BA1 \u6838");
		btnAudit.setActionCommand("\u5BA1 \u6838");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		btnAudit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout2.show(auditPanel, "auditCard");
				try {
					showAdInfo(e);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				startTime = simpleDateFormat.format(new Date());
			}
		});
		btnAudit.setFont(new Font("宋体", Font.PLAIN, 14));
		btnAudit.setBounds(570, 482, 83, 33);
		chooseAdPanel.add(btnAudit);
		
		//------------------------------------adPanel------------------------------------
		adPanel = new JPanel();
		adPanel.setBounds(0, 0, 981, 530);
		auditPanel.add(adPanel,"auditCard");
		adPanel.setLayout(null);
		
		basicInfopanel = new JPanel();
		basicInfopanel.setBounds(10, 10, 255, 510);
		adPanel.add(basicInfopanel);
		basicInfopanel.setBorder(new TitledBorder(null, "\u5E7F\u544A\u57FA\u672C\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		basicInfopanel.setLayout(null);
		
		JLabel lblAdId = new JLabel("\u5E7F\u544AID");
		lblAdId.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblAdId.setFont(new Font("宋体", Font.PLAIN, 13));
		lblAdId.setBounds(10, 25, 75, 45);
		basicInfopanel.add(lblAdId);
		
		JLabel lblLanguage = new JLabel("\u8BED\u8A00");
		lblLanguage.setFont(new Font("宋体", Font.PLAIN, 13));
		lblLanguage.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblLanguage.setBounds(10, 69, 75, 45);
		basicInfopanel.add(lblLanguage);
		
		JLabel lbCreateTime = new JLabel("\u521B\u5EFA\u65F6\u95F4");
		lbCreateTime.setFont(new Font("宋体", Font.PLAIN, 13));
		lbCreateTime.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lbCreateTime.setBounds(10, 113, 75, 45);
		basicInfopanel.add(lbCreateTime);
		
		JLabel lblDeliveryCountry = new JLabel("\u6295\u653E\u5730");
		lblDeliveryCountry.setFont(new Font("宋体", Font.PLAIN, 13));
		lblDeliveryCountry.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblDeliveryCountry.setBounds(10, 157, 75, 45);
		basicInfopanel.add(lblDeliveryCountry);
		
		JLabel lblAdvId = new JLabel("\u5E7F\u544A\u4E3BID");
		lblAdvId.setFont(new Font("宋体", Font.PLAIN, 13));
		lblAdvId.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblAdvId.setBounds(10, 201, 75, 45);
		basicInfopanel.add(lblAdvId);
		
		JLabel lblAdvName = new JLabel("\u5E7F\u544A\u4E3B\u540D\u79F0");
		lblAdvName.setFont(new Font("宋体", Font.PLAIN, 13));
		lblAdvName.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblAdvName.setBounds(10, 245, 75, 45);
		basicInfopanel.add(lblAdvName);
		
		JLabel lblAdvType = new JLabel("\u5E7F\u544A\u4E3B\u7C7B\u578B");
		lblAdvType.setFont(new Font("宋体", Font.PLAIN, 13));
		lblAdvType.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblAdvType.setBounds(10, 289, 75, 45);
		basicInfopanel.add(lblAdvType);
		
		lblAdId2 = new JLabel("");
		lblAdId2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		lblAdId2.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblAdId2.setBounds(84, 25, 161, 45);
		basicInfopanel.add(lblAdId2);
		
		lblLanguage2 = new JLabel("");
		lblLanguage2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		lblLanguage2.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblLanguage2.setBounds(84, 69, 161, 45);
		basicInfopanel.add(lblLanguage2);
		
		lbCreateTime2 = new JLabel("");
		lbCreateTime2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		lbCreateTime2.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lbCreateTime2.setBounds(84, 113, 161, 45);
		basicInfopanel.add(lbCreateTime2);
		
		lblDeliveryCountry2 = new JLabel("");
		lblDeliveryCountry2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		lblDeliveryCountry2.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblDeliveryCountry2.setBounds(84, 157, 161, 45);
		basicInfopanel.add(lblDeliveryCountry2);
		
		lblAdvId2 = new JLabel("");
		lblAdvId2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		lblAdvId2.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblAdvId2.setBounds(84, 201, 161, 45);
		basicInfopanel.add(lblAdvId2);
		
		lblAdvName2 = new JLabel("");
		lblAdvName2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		lblAdvName2.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblAdvName2.setBounds(84, 245, 161, 45);
		basicInfopanel.add(lblAdvName2);
		
		lblAdvType2 = new JLabel("");
		lblAdvType2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		lblAdvType2.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblAdvType2.setBounds(84, 289, 161, 45);
		basicInfopanel.add(lblAdvType2);
		
		materialInfoPanel = new JPanel();
		materialInfoPanel.setLayout(null);
		materialInfoPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u5E7F\u544A\u7D20\u6750\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		materialInfoPanel.setBounds(285, 10, 686, 256);
		adPanel.add(materialInfoPanel);
		
		JLabel lblTitle = new JLabel("\u6807\u9898\uFF1A");
		lblTitle.setFont(new Font("宋体", Font.PLAIN, 13));
		lblTitle.setBorder(null);
		lblTitle.setBounds(10, 25, 78, 55);
		materialInfoPanel.add(lblTitle);
		
		JLabel lblPicture = new JLabel("\u56FE\u7247\uFF1A");
		lblPicture.setFont(new Font("宋体", Font.PLAIN, 13));
		lblPicture.setBorder(null);
		lblPicture.setBounds(10, 80, 78, 55);
		materialInfoPanel.add(lblPicture);
		
		JLabel lblVideo = new JLabel("\u89C6\u9891\uFF1A");
		lblVideo.setFont(new Font("宋体", Font.PLAIN, 13));
		lblVideo.setBorder(null);
		lblVideo.setBounds(10, 135, 78, 55);
		materialInfoPanel.add(lblVideo);
		
		JLabel lblLP = new JLabel("\u843D\u5730\u9875\uFF1A");
		lblLP.setFont(new Font("宋体", Font.PLAIN, 13));
		lblLP.setBorder(null);
		lblLP.setBounds(10, 190, 78, 55);
		materialInfoPanel.add(lblLP);
		
		lblTitle2 = new JLabel("");
		lblTitle2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		lblTitle2.setBorder(null);
		lblTitle2.setBounds(87, 25, 589, 55);
		materialInfoPanel.add(lblTitle2);
		
		lblPicture2 = new JLabel("");
		lblPicture2.setForeground(SystemColor.textHighlight);
		lblPicture2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		lblPicture2.setBorder(null);
		lblPicture2.setBounds(87, 80, 589, 55);
		materialInfoPanel.add(lblPicture2);
		
		lblVideo2 = new JLabel("");
		lblVideo2.setForeground(SystemColor.textHighlight);
		lblVideo2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		lblVideo2.setBorder(null);
		lblVideo2.setBounds(87, 135, 589, 55);
		materialInfoPanel.add(lblVideo2);
		
		lblLP2 = new JLabel("");
		lblLP2.setForeground(SystemColor.textHighlight);
		lblLP2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		lblLP2.setBorder(null);
		lblLP2.setBounds(87, 190, 589, 55);
		materialInfoPanel.add(lblLP2);
		
		rdbtnApprove = new JRadioButton("\u901A\u8FC7");
		rdbtnApprove.setFont(new Font("宋体", Font.PLAIN, 13));
		rdbtnApprove.setBounds(285, 294, 74, 23);
		adPanel.add(rdbtnApprove);
		
		rdbtnDisapprove = new JRadioButton("\u62D2\u7EDD");
		rdbtnDisapprove.setFont(new Font("宋体", Font.PLAIN, 13));
		rdbtnDisapprove.setBounds(386, 294, 74, 23);
		adPanel.add(rdbtnDisapprove);
		
		btnGroupAudit = new ButtonGroup();
		btnGroupAudit.add(rdbtnApprove);
		btnGroupAudit.add(rdbtnDisapprove);
		
		JLabel lblRiskLabel = new JLabel("\u98CE\u9669\u6807\u7B7E\uFF1A");
		lblRiskLabel.setFont(new Font("宋体", Font.PLAIN, 13));
		lblRiskLabel.setBounds(513, 298, 74, 15);
		adPanel.add(lblRiskLabel);
		
		cboxRiskLabel = new JComboBox();
		cboxRiskLabel.setForeground(UIManager.getColor("ComboBox.foreground"));
		cboxRiskLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		cboxRiskLabel.setBounds(593, 294, 261, 23);
		adPanel.add(cboxRiskLabel);
		cboxRiskLabel.addItem("None");
		try {
			String[] labels = Database.getLabels();
			for (int i=0; i<labels.length; i++) {
				cboxRiskLabel.addItem(labels[i]);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		JLabel lblReason = new JLabel("\u62D2\u7EDD\u7406\u7531\uFF1A");
		lblReason.setFont(new Font("宋体", Font.PLAIN, 13));
		lblReason.setBounds(285, 339, 74, 15);
		adPanel.add(lblReason);
		
		tareaReason = new JTextArea();
		tareaReason.setBounds(354, 335, 617, 80);
		adPanel.add(tareaReason);
		
		JLabel lblPunish = new JLabel("\u5BF9\u5E7F\u544A\u4E3B\u91C7\u53D6\u7684\u63AA\u65BD\uFF1A");
		lblPunish.setFont(new Font("宋体", Font.PLAIN, 13));
		lblPunish.setBounds(285, 438, 142, 15);
		adPanel.add(lblPunish);
		
		cboxPunishment = new JComboBox();
		cboxPunishment.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		cboxPunishment.setBackground(UIManager.getColor("ComboBox.background"));
		cboxPunishment.setBounds(433, 434, 300, 23);
		adPanel.add(cboxPunishment);
		cboxPunishment.addItem("None");
		cboxPunishment.addItem("Send warning");
		cboxPunishment.addItem("Suspend for one week");
		cboxPunishment.addItem("Suspend for one month");
		cboxPunishment.addItem("Suspend forever");
		
		//“确定”按钮
		JButton btnConfirm = new JButton("\u786E \u5B9A");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishTime = simpleDateFormat.format(new Date());
				audit(e);
				cardlayout2.show(auditPanel, "chooseAdCard");
			}
		});
		btnConfirm.setFont(new Font("宋体", Font.PLAIN, 14));
		btnConfirm.setActionCommand("\u5BA1 \u6838");
		btnConfirm.setBounds(540, 480, 83, 33);
		adPanel.add(btnConfirm);
		
		//“返回”按钮
		JButton btnReturn = new JButton("\u8FD4 \u56DE");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout2.show(auditPanel, "chooseAdCard");
			}
		});
		btnReturn.setFont(new Font("宋体", Font.PLAIN, 14));
		btnReturn.setActionCommand("\u5BA1 \u6838");
		btnReturn.setBounds(650, 480, 83, 33);
		adPanel.add(btnReturn);
		
		JPanel queryPanel = new JPanel();
		tabbedPane_1.addTab(" \u4FE1\u606F\u67E5\u8BE2 ", null, queryPanel, null);
		queryPanel.setLayout(null);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane_2.setFont(new Font("宋体", Font.PLAIN, 15));
		tabbedPane_2.setBounds(0, 0, 981, 530);
		queryPanel.add(tabbedPane_2);
		
		JPanel advInfoPanel = new JPanel();
		advInfoPanel.setFont(new Font("宋体", Font.PLAIN, 14));
		tabbedPane_2.addTab("\r\n\u5E7F\u544A\u4E3B\u4FE1\u606F\u67E5\u8BE2\r\n", null, advInfoPanel, null);
		advInfoPanel.setLayout(null);
		
		JLabel lblInfoAdvId = new JLabel("\u5E7F\u544A\u4E3BID\uFF1A");
		lblInfoAdvId.setBounds(10, 10, 67, 16);
		lblInfoAdvId.setFont(new Font("宋体", Font.PLAIN, 13));
		advInfoPanel.add(lblInfoAdvId);
		
		tfAdvId = new JTextField();
		tfAdvId.setBounds(87, 8, 176, 21);
		advInfoPanel.add(tfAdvId);
		tfAdvId.setColumns(10);
		
		JLabel lblInfoAdvName = new JLabel("\u540D\u79F0\uFF1A");
		lblInfoAdvName.setFont(new Font("宋体", Font.PLAIN, 13));
		lblInfoAdvName.setBounds(293, 11, 54, 15);
		advInfoPanel.add(lblInfoAdvName);
		
		tfAdvName = new JTextField();
		tfAdvName.setColumns(10);
		tfAdvName.setBounds(347, 8, 255, 21);
		advInfoPanel.add(tfAdvName);
		
		JLabel lblInfoAdvType = new JLabel("\u7C7B\u578B\uFF1A");
		lblInfoAdvType.setFont(new Font("宋体", Font.PLAIN, 13));
		lblInfoAdvType.setBounds(628, 11, 40, 15);
		advInfoPanel.add(lblInfoAdvType);
		
		JComboBox cboxAdvType = new JComboBox();
		cboxAdvType.setBounds(678, 7, 165, 23);
		advInfoPanel.add(cboxAdvType);
		
		JLabel lblInfoAgency = new JLabel("\u4EE3\u7406\u5546\uFF1A");
		lblInfoAgency.setFont(new Font("宋体", Font.PLAIN, 13));
		lblInfoAgency.setBounds(10, 46, 67, 16);
		advInfoPanel.add(lblInfoAgency);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(75, 44, 255, 21);
		advInfoPanel.add(textField);
		
		JLabel lblInfoReg = new JLabel("\u6CE8\u518C\u5730\uFF1A");
		lblInfoReg.setFont(new Font("宋体", Font.PLAIN, 13));
		lblInfoReg.setBounds(369, 47, 54, 15);
		advInfoPanel.add(lblInfoReg);
		
		JComboBox cboxReg = new JComboBox();
		cboxReg.setBounds(433, 43, 88, 23);
		advInfoPanel.add(cboxReg);
		
		JLabel lblInfoAdvStatus = new JLabel("\u72B6\u6001\uFF1A");
		lblInfoAdvStatus.setFont(new Font("宋体", Font.PLAIN, 13));
		lblInfoAdvStatus.setBounds(562, 47, 40, 15);
		advInfoPanel.add(lblInfoAdvStatus);
		
		JComboBox cboxAdvStatus = new JComboBox();
		cboxAdvStatus.setBounds(612, 43, 231, 23);
		advInfoPanel.add(cboxAdvStatus);
		
		JLabel lblInfoRegTime = new JLabel("\u6CE8\u518C\u65F6\u95F4\u6BB5\uFF1A");
		lblInfoRegTime.setFont(new Font("宋体", Font.PLAIN, 13));
		lblInfoRegTime.setBounds(10, 86, 94, 16);
		advInfoPanel.add(lblInfoRegTime);
		
		tfTimeAfter = new JTextField();
		tfTimeAfter.setColumns(10);
		tfTimeAfter.setBounds(101, 82, 122, 21);
		advInfoPanel.add(tfTimeAfter);
		
		JLabel lblInfoBetween = new JLabel("\u81F3");
		lblInfoBetween.setFont(new Font("宋体", Font.PLAIN, 13));
		lblInfoBetween.setBounds(233, 86, 21, 15);
		advInfoPanel.add(lblInfoBetween);
		
		tfTimeBefore = new JTextField();
		tfTimeBefore.setColumns(10);
		tfTimeBefore.setBounds(255, 82, 122, 21);
		advInfoPanel.add(tfTimeBefore);
		
		JButton btnConfirm_1 = new JButton("\u67E5 \u8BE2");
		btnConfirm_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					showAdv(e);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnConfirm_1.setFont(new Font("宋体", Font.PLAIN, 14));
		btnConfirm_1.setActionCommand("\u5BA1 \u6838");
		btnConfirm_1.setBounds(753, 117, 75, 33);
		advInfoPanel.add(btnConfirm_1);
		
		tableAdvInfo = new JTable();
		tableAdvInfo.setBounds(10, 170, 833, 332);
		//advInfoPanel.add(tableAdvInfo);
		
		scpAdvInfo = new JScrollPane();
		scpAdvInfo.setBounds(10, 170, 833, 332);
		scpAdvInfo.setViewportView(tableAdvInfo);
		advInfoPanel.add(scpAdvInfo);
		
		//---------------------------------------------------------------------------------------------------
		JPanel adInfoPanel = new JPanel();
		tabbedPane_2.addTab("\u5E7F\u544A\u4FE1\u606F\u67E5\u8BE2", null, adInfoPanel, null);
		
		JPanel auditInfoPanel = new JPanel();
		tabbedPane_2.addTab("\u5BA1\u6838\u65E5\u5FD7\u67E5\u8BE2", null, auditInfoPanel, null);
		
		JPanel managePanel = new JPanel();
		tabbedPane_1.addTab(" \u7CFB\u7EDF\u7BA1\u7406 ", null, managePanel, null);
		managePanel.setLayout(null);
		
		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane_3.setFont(new Font("宋体", Font.PLAIN, 15));
		tabbedPane_3.setBounds(0, 0, 981, 530);
		managePanel.add(tabbedPane_3);
		
		JPanel auditorManagePanel = new JPanel();
		auditorManagePanel.setLayout(null);
		auditorManagePanel.setFont(new Font("宋体", Font.PLAIN, 14));
		tabbedPane_3.addTab("\u5BA1\u6838\u5458\u7BA1\u7406", null, auditorManagePanel, null);
		
		JLabel lblInfoAdvId_1 = new JLabel("\u5BA1\u6838\u5458ID\uFF1A");
		lblInfoAdvId_1.setFont(new Font("宋体", Font.PLAIN, 13));
		lblInfoAdvId_1.setBounds(10, 10, 67, 16);
		auditorManagePanel.add(lblInfoAdvId_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(87, 8, 127, 21);
		auditorManagePanel.add(textField_1);
		
		JLabel lblInfoAdvName_1 = new JLabel("\u59D3\u540D\uFF1A");
		lblInfoAdvName_1.setFont(new Font("宋体", Font.PLAIN, 13));
		lblInfoAdvName_1.setBounds(243, 11, 54, 15);
		auditorManagePanel.add(lblInfoAdvName_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(297, 8, 182, 21);
		auditorManagePanel.add(textField_2);
		
		JLabel lblInfoAdvType_1 = new JLabel("\u5E74\u9F84\uFF1A");
		lblInfoAdvType_1.setFont(new Font("宋体", Font.PLAIN, 13));
		lblInfoAdvType_1.setBounds(507, 11, 40, 15);
		auditorManagePanel.add(lblInfoAdvType_1);
		
		JComboBox cboxAdvType_1 = new JComboBox();
		cboxAdvType_1.setBounds(711, 7, 88, 23);
		auditorManagePanel.add(cboxAdvType_1);
		
		JLabel lblInfoAgency_1 = new JLabel("\u5165\u804C\u65E5\u671F\uFF1A");
		lblInfoAgency_1.setFont(new Font("宋体", Font.PLAIN, 13));
		lblInfoAgency_1.setBounds(10, 46, 67, 16);
		auditorManagePanel.add(lblInfoAgency_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(87, 44, 127, 21);
		auditorManagePanel.add(textField_3);
		
		JLabel lblInfoReg_1 = new JLabel("\u5DE5\u4F5C\u57CE\u5E02\uFF1A");
		lblInfoReg_1.setFont(new Font("宋体", Font.PLAIN, 13));
		lblInfoReg_1.setBounds(243, 47, 236, 15);
		auditorManagePanel.add(lblInfoReg_1);
		
		JComboBox cboxReg_1 = new JComboBox();
		cboxReg_1.setBounds(320, 43, 159, 23);
		auditorManagePanel.add(cboxReg_1);
		
		JLabel lblInfoAdvStatus_1 = new JLabel("\u8BED\u8A00\uFF1A");
		lblInfoAdvStatus_1.setFont(new Font("宋体", Font.PLAIN, 13));
		lblInfoAdvStatus_1.setBounds(507, 47, 40, 15);
		auditorManagePanel.add(lblInfoAdvStatus_1);
		
		JComboBox cboxAdvStatus_1 = new JComboBox();
		cboxAdvStatus_1.setBounds(557, 43, 144, 23);
		auditorManagePanel.add(cboxAdvStatus_1);
		
		JButton btnConfirm_1_1 = new JButton("\u67E5 \u8BE2");
		btnConfirm_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					showAuditor(e);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnConfirm_1_1.setFont(new Font("宋体", Font.PLAIN, 14));
		btnConfirm_1_1.setActionCommand("\u5BA1 \u6838");
		btnConfirm_1_1.setBounds(681, 92, 75, 33);
		auditorManagePanel.add(btnConfirm_1_1);
		
		tableAuditor = new JTable();
		tableAuditor.setBounds(10, 145, 833, 311);
		auditorManagePanel.add(tableAuditor);
		
		scpAdvInfo_1 = new JScrollPane(tableAuditor);
		scpAdvInfo_1.setBounds(10, 145, 833, 311);
		scpAdvInfo_1.setViewportView(tableAuditor);
		auditorManagePanel.add(scpAdvInfo_1);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(558, 8, 75, 21);
		auditorManagePanel.add(textField_6);
		
		JLabel lblInfoAdvType_1_1 = new JLabel("\u6027\u522B\uFF1A");
		lblInfoAdvType_1_1.setFont(new Font("宋体", Font.PLAIN, 13));
		lblInfoAdvType_1_1.setBounds(661, 11, 40, 15);
		auditorManagePanel.add(lblInfoAdvType_1_1);
		
		JButton btnConfirm_1_1_1 = new JButton("\u6DFB \u52A0");
		btnConfirm_1_1_1.setFont(new Font("宋体", Font.PLAIN, 14));
		btnConfirm_1_1_1.setActionCommand("\u5BA1 \u6838");
		btnConfirm_1_1_1.setBounds(768, 92, 75, 33);
		auditorManagePanel.add(btnConfirm_1_1_1);
		
		JButton btnConfirm_1_1_2 = new JButton("\u5220 \u9664");
		btnConfirm_1_1_2.setFont(new Font("宋体", Font.PLAIN, 14));
		btnConfirm_1_1_2.setActionCommand("\u5BA1 \u6838");
		btnConfirm_1_1_2.setBounds(320, 476, 75, 33);
		auditorManagePanel.add(btnConfirm_1_1_2);
		
		JButton btnConfirm_1_1_2_1 = new JButton("\u4FEE \u6539");
		btnConfirm_1_1_2_1.setFont(new Font("宋体", Font.PLAIN, 14));
		btnConfirm_1_1_2_1.setActionCommand("\u5BA1 \u6838");
		btnConfirm_1_1_2_1.setBounds(429, 476, 75, 33);
		auditorManagePanel.add(btnConfirm_1_1_2_1);
		
		JPanel queueManagePanel = new JPanel();
		tabbedPane_3.addTab("\u5BA1\u6838\u961F\u5217\u7BA1\u7406", null, queueManagePanel, null);
		
		JPanel labelManagePanel = new JPanel();
		tabbedPane_3.addTab("\u98CE\u9669\u6807\u7B7E\u7BA1\u7406", null, labelManagePanel, null);
	}

	protected void audit(ActionEvent e) {
		boolean approveOrNot = true;
		if(rdbtnDisapprove.isSelected()) {
			approveOrNot = false;
		}
		String labelName = (String) cboxRiskLabel.getSelectedItem();
		if(labelName == "None") {
			labelName = null;
		}
		Database.updateAdStatus(lblAdId2.getText(), approveOrNot, labelName);
		String advId = lblAdvId2.getText();
		String punishment = (String) cboxPunishment.getSelectedItem();
		Database.updateAdvStatus(advId, punishment);
		String auditorId = "20190203";
		Database.updateAudit(lblAdId2.getText(), auditorId, startTime, finishTime, approveOrNot, labelName, tareaReason.getText());
	}

	protected void showAdInfo(ActionEvent e) throws SQLException {
		String currentAdId = (String) tableAdsOfQueue.getValueAt(tableAdsOfQueue.getSelectedRow(), 0);
		String[] adInfo = Database.getCurrentAdInfo(currentAdId);
		lblAdId2.setText(currentAdId);
		lblLanguage2.setText(adInfo[0]);
		lbCreateTime2.setText(adInfo[1]);
		lblDeliveryCountry2.setText(adInfo[2]);
		lblAdvId2.setText(adInfo[3]);
		lblAdvName2.setText(adInfo[4]);
		lblAdvType2.setText(adInfo[5]);
		lblTitle2.setText(adInfo[6]);
		lblPicture2.setText(adInfo[7]);
		lblVideo2.setText(adInfo[8]);
		lblLP2.setText(adInfo[9]);
	}

	protected void showAuditor(ActionEvent e) throws SQLException {
		// TODO Auto-generated method stub
		String manageAuditorId = "20190203";
		String[] manageAuditorTitle = {"审核员ID","姓名","年龄","性别","入职日期","工作城市","语言"};
		Object[][] info = {{"20190203", "Alice Wang", "27", "F", "2019-08-02", "Shanghai", "Chinese"}};
		DefaultTableModel tabModel = new DefaultTableModel(info,manageAuditorTitle);
		tableAuditor.setModel(tabModel);
	}

	protected void showAdv(ActionEvent e) throws SQLException {
		// TODO Auto-generated method stub
		String queryAdvId = "20210500400011";
		String[] advInfoTitle = {"广告主ID","名称","类型","代理商","注册地","注册日期","状态"};
		DefaultTableModel tabModel = new DefaultTableModel(Database.getAdvInfo(queryAdvId),advInfoTitle);
		tableAdvInfo.setModel(tabModel);
	}

	protected void showAds(ListSelectionEvent e) throws SQLException {
		String currentQueueName = new String(listQueue.getSelectedValue().toString()); 
		lblQueueName.setText(currentQueueName); //更新当前队列名称显示
		lblTaskNum.setText("待审核任务量：" + String.valueOf(Database.getTaskNum(currentQueueName))); //更新当前队列待审核任务量显示
		//更新表格内容 
		scpAdsOfQueue.setVisible(true);
		tableAdsOfQueue.setVisible(true);
		String currentqueueId = Database.getQueueId(currentQueueName); //获得当前队列ID
		String[] adsOfQueueTitle = {"广告ID","创建时间","广告标题","广告主"};
		DefaultTableModel tabModel = new DefaultTableModel(Database.getAdsOfQueueTableInfo(currentqueueId),adsOfQueueTitle);
		tableAdsOfQueue.setModel(tabModel);
	}

	protected void Login(ActionEvent e) {
		String user = tfUser.getText().trim();
		String password = new String(passwordField.getPassword());
		if(!user.isEmpty() && !password.isEmpty()) {
			Database.getCon();
			boolean result = Database.loginConfirm(user, password);
			if(result) {		
				//cardlayout.show(mainFrm.getContentPane(), "systemCard");
				cardlayout1.next(mainFrm.getContentPane());
				System.out.println("登录成功!");
			}else {
				JOptionPane.showMessageDialog(null, "账号或密码错误，请重新登录!", "提示", JOptionPane.ERROR_MESSAGE); 
			}
		}
	}
}
