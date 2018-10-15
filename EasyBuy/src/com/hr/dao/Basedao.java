package com.hr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Basedao {
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");//初始化给定的类.而我们给定的MySQL的Driver类中,它在静态代码块中通过JDBC的DriverManager注册了一下驱动.我们也可以直接使用JDBC的驱动管理器注册mysql驱动
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();//e是此对象引用名称,在命令行打印异常信息在程序中出错的位置及原因
		}
	}
	
	public static Connection getconn(){
		Connection conn=null;//声明数据库连接对象
		 try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_easy_buy?useUnicode=true&characterEncoding=UTF-8","root","root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void closeall(ResultSet rs,Statement ps,Connection conn){
			try {
				if(rs!=null)//ResultSet数据库结果集的数据表，通常通过执行查询数据库的语句生成。
				   rs.close();
				if(ps!=null)//Statement 是 Java 执行数据库操作的一个重要接口，用于在已经建立数据库连接的基础上，向数据库发送要执行的SQL语句。Statement对象，用于执行不带参数的简单SQL语句。
					ps.close();
				if(conn!=null)//
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}
	
	public static int exectuIUD(String sql,Object[] params){
		int count=0;
		  Connection conn=Basedao.getconn();
		  PreparedStatement ps=null;
		  try {
			ps=conn.prepareStatement(sql);
			if(params!=null){
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i+1, params[i]);
				}
			}
			count=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			Basedao.closeall(null, ps, conn);
		}
		return count;
	}
}
