package DB;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * @author GUOFENG  --登录连接数据库
 * 
 */
public class UserDB {
	// 驱动程序名
	String driver = "";
	// URL指向要访问的数据库名hello
	String url = "";
	// MySQL配置
	String sqluser = "";
	String sqlpassword = "";

	String userpwd_;
	String username_;
	boolean n = false;

	public UserDB(String name, String pwd) {
		username_ = name;
		userpwd_ = pwd;
	}

	public Boolean selectsql() {
		n = false;
		try {
			// 加载驱动
			Class.forName(driver);
			// 连接数据库
			Connection conn = DriverManager.getConnection(url, sqluser,
					sqlpassword);
			if (!conn.isClosed())
				System.out.println("连接数据库成功!");
			// statement用来执行SQL语句
			Statement statement = conn.createStatement();
			// 要执行的SQL语句
			String sql = "select userpwd from info where username=" + "'" + username_ + "';";
			// 结果集
			ResultSet rs = statement.executeQuery(sql);
			String readpwd = null;
			while (rs.next()) {
				// 选择passworld这列数据
				readpwd = rs.getString("userpwd");
				// 首先使用ISO-8859-1字符集将其解码为字节序列并将结果存储新的字节数组中。
				// 然后使用GB2312字符集解码指定的字节数组
				readpwd = new String(readpwd.getBytes("ISO-8859-1"), "GB2312");
				// 输出结果
				System.out.println(readpwd);
				if (readpwd.equals(userpwd_)) {
					n = true;
				}
			}
			rs.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			System.out.println("加载MySQL驱动失败!");
		} catch (SQLException e1) {
			System.out.println("1.hellosql:" + e1.getMessage());
		} catch (Exception e2) {
			System.out.println("2.hellosql:" + e2.getMessage());
		}
		return n;
	}

	public boolean addsql() {
		int count = 0;
		n = false;
		try {
			// 加载驱动
			Class.forName(driver);
			// 连接数据库
			Connection conn = DriverManager.getConnection(url, sqluser,
					sqlpassword);
			if (!conn.isClosed())
				System.out.println("连接数据库成功!");

			String sql = "insert into info (username, userpwd) values (?,?);";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username_);
			ps.setString(2, userpwd_);
			count = ps.executeUpdate();
			if (this.selectsql() == true)
				{	n = true;
					System.out.println("***");
				}
			else {
				JOptionPane.showMessageDialog(new JFrame(), "注册失败！", "错误",
						JOptionPane.ERROR_MESSAGE);
			}
			ps.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			System.out.println("加载MySQL驱动失败!");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

}