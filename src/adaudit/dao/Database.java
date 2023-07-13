package adaudit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Database {
	private String dbDriver = "com.mysql.jdbc.Driver";
	private String dbUrl  = "jdbc:mysql://localhost:3306/audit_db?characterEncoding=utf8"; 
	private String dbName = "root";
	private String dbPassword = "20000930";
	
	static Connection con = null;
	
	public Database() {
		try {
			Class.forName(dbDriver);
			con = DriverManager.getConnection(dbUrl,dbName,dbPassword);
			System.out.println("数据库连接成功!");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("数据库连接失败!");
		}
	}
	
	public static Connection getCon() {
		return con;
	}
	
	public void closeCon(Connection con) throws Exception{
    	if(con!=null){ 		
    		con.close();
    	}
    }
	
	public static boolean loginConfirm(String user, String password) {
		boolean result = false;
		String sql = "select * from user where user=? and password=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = true;
			}
			rs.close();
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String[] getQueueList() {
		String sql = "select * from queue";
		List<String> list = new ArrayList<String>();
		String[] arr = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("queue_name"));
			}
			if(list != null && list.size() > 0) {
				arr = new String[list.size()];
				for(int i=0; i<list.size(); i++) {
					arr[i] = list.get(i);
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	public static int getTaskNum(String queueName) {
		String sql = "select * from queue where queue_name = ?";
		int taskNum = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, queueName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				taskNum = rs.getInt("task_num");
			}
			rs.close();
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return taskNum;
	}
	
	public static String getQueueId(String queueName) {
		String sql = "select * from queue where queue_name = ?";
		String queueId = new String();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, queueName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				queueId = rs.getString("queue_id");
			}
			rs.close();
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return queueId;
	}
	
	public static Object[][] getAdsOfQueueTableInfo(String queueId) throws SQLException{
		String sql = "select * from ad where queue_id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, queueId);
		ResultSet rs = ps.executeQuery();
		int count = 0;	//该队列中广告数量		
		while (rs.next()) {
			count++;
		}
		rs = ps.executeQuery();
		Object[][] info = new Object[count][4];
		count = 0;
		while (rs.next()) {
			info[count][0] = rs.getString("ad_id");
			info[count][1] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("create_time"));
			info[count][2] = rs.getString("title");
			info[count][3] = rs.getString("adv_id");
			count++;
		}
		rs.close();
		ps.close();
		return info;
	}
	
	public static String[] getCurrentAdInfo(String adId) throws SQLException{
		String sql = "select * from ad where ad_id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, adId);
		ResultSet rs = ps.executeQuery();
		String[] info = new String[10];
		if (rs.next()) {
			info[0] = rs.getString("language");
			info[1] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("create_time"));
			info[2] = rs.getString("delivery_country");
			info[3] = rs.getString("adv_id");
			info[4] = (String) getAdvInfo(info[3])[0][1];
			info[5] = (String) getAdvInfo(info[3])[0][2];
			info[6] = rs.getString("title");
			info[7] = rs.getString("picture");
			info[8] = rs.getString("video");
			info[9] = rs.getString("landing_page");
		}
		rs.close();
		ps.close();		
		return info;
	}
	
	public static String[] getLabels() throws SQLException{
		String sql = "select * from label";
		List<String> list = new ArrayList<String>();
		String[] label = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("label_name"));
			}
			if (list != null && list.size() > 0) {
				label = new String[list.size()];
				for(int i=0; i<list.size(); i++) {
					label[i] = list.get(i);
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return label;
	}
	
	public static void updateAdStatus(String adId, boolean approveOrNot, String labelName){
		String sql = "update ad set ad_status = ?, queue_id = null, label_id = ? where ad_id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			if (approveOrNot) {
				ps.setInt(1, 2); //“待投放”状态
			} else {
				ps.setInt(1, 1); //“审核拒绝”状态
			}	
			if(labelName == null) {
				ps.setNull(2, Types.CHAR);
			} else {
				ps.setString(2, getLabelId(labelName));
			}		
			ps.setString(3, adId);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String getLabelId(String labelName) {
		String sql = "select * from label where label_name = ?";
		String labelId = new String();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, labelName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				labelId = rs.getString("label_id");
			}
			rs.close();
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return labelId;
	}
	
	public static void updateAdvStatus(String advId, String punishment){
		String sql = "update advertiser set adv_status = ? where adv_id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			switch (punishment) {
			case "None":
				ps.setString(1, "4");
				break;
			case "Send warning":
				ps.setString(1, "3");
				break;
			case "Suspend for one week":
				ps.setString(1, "2");
				break;
			case "Suspend for one month":
				ps.setString(1, "1");
				break;
			case "Suspend forever":
				ps.setString(1, "0");
				break;
			}	
			ps.setString(2, advId);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateAudit(String adId, String auditorId, String startTime, String finishTime, 
									boolean approveOrNot, String labelName, String reason) {
		String sql = "insert into audit values(?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, adId);
			ps.setString(2, auditorId);
			ps.setString(3, startTime);
			ps.setString(4, finishTime);
			if (!approveOrNot) {
				ps.setInt(5, 1); //审核拒绝
			} else {
				ps.setInt(5, 2); //审核通过
			}
			if (labelName == null) {
				ps.setNull(6, Types.CHAR);
			} else {
				ps.setString(6, getLabelId(labelName));
			}
			if (reason == null) {
				ps.setNull(7, Types.VARCHAR);
			} else {
				ps.setString(7, reason);
			}
			ps.executeUpdate();
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Object[][] getAdvInfo(String advId) throws SQLException{
		String sql = "select * from advertiser where adv_id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, advId);
		ResultSet rs = ps.executeQuery();
		int count = 0;			
		while (rs.next()) {
			count++;
		}
		rs = ps.executeQuery();
		Object[][] info = new Object[count][7];
		count = 0;
		while (rs.next()) {
			info[count][0] = rs.getString("adv_id");
			info[count][1] = rs.getString("adv_name");
			if (rs.getInt("adv_type") == 0) {
				info[count][2] = "non-self-serve";
			}else {
				info[count][2] = "self-serve";
			}
			info[count][3] = rs.getString("agency");
			info[count][4] = rs.getString("registration_country");
			info[count][5] = rs.getString("registration_date");
			switch (rs.getInt("adv_status")) {
			case 0:
				info[count][6] = "suspended forever";
				break;
			case 1:
				info[count][6] = "suspended for one month";
				break;
			case 2:
				info[count][6] = "suspended for one week";
				break;
			case 3:
				info[count][6] = "warned";
				break;
			case 4:
				info[count][6] = "good to go";
				break;
			}
			count++;
		}
		rs.close();
		ps.close();
		return info;
	}
	
	public static Object[][] getAuditorInfo(String auditorId) throws SQLException{
		String sql = "select * from auditor where auditor_id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, auditorId);
		ResultSet rs = ps.executeQuery();
		int count = 0;			
		while (rs.next()) {
			count++;
		}
		rs = ps.executeQuery();
		Object[][] info = new Object[count][7];
		count = 0;
		while (rs.next()) {
			info[count][0] = rs.getString("auditor_id");
			info[count][1] = rs.getString("name");
			info[count][2] = rs.getInt("age");
			info[count][3] = rs.getString("gender");
			info[count][3] = rs.getString("employment_date");
			info[count][4] = rs.getString("city");
			info[count][5] = rs.getString("language");
			count++;
		}
		rs.close();
		ps.close();
		return info;
	}
}
