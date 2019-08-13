package com.xiaobi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JdbcTemplate {
	//数据库连接
	private static Connection connection = null;
	//执行sql使用到的预编译类，可以直接使用Statement类，PreparedStatement是预编译的Statement，使用预编译的Statement提高数据库性能
	private static PreparedStatement preparedStatement = null;
	//接收数据查询数据的结果集
	private static ResultSet resultSet = null;
	//
	private static String driverClassName = null;
	private static String url = null;
	private static String username = null;
	private static String password = null;
	
	static{
		ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
		driverClassName = bundle.getString("driverClassName");
		url = bundle.getString("url");
		username = bundle.getString("username");
		password = bundle.getString("password");
	}
	
	public JdbcTemplate(){
		try {
			//加载驱动
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			connection = DriverManager.getConnection(url, username, password);
			String sql = "select user_id,user_name from user where user_id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "1");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				String userId = resultSet.getString("user_id");
				String userName = resultSet.getString("user_name");
				System.out.println(userId+"----"+userName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
		}
	}
}
