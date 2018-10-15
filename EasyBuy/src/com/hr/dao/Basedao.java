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
			Class.forName("com.mysql.jdbc.Driver");//��ʼ����������.�����Ǹ�����MySQL��Driver����,���ھ�̬�������ͨ��JDBC��DriverManagerע����һ������.����Ҳ����ֱ��ʹ��JDBC������������ע��mysql����
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();//e�Ǵ˶�����������,�������д�ӡ�쳣��Ϣ�ڳ����г����λ�ü�ԭ��
		}
	}
	
	public static Connection getconn(){
		Connection conn=null;//�������ݿ����Ӷ���
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
				if(rs!=null)//ResultSet���ݿ����������ݱ�ͨ��ͨ��ִ�в�ѯ���ݿ��������ɡ�
				   rs.close();
				if(ps!=null)//Statement �� Java ִ�����ݿ������һ����Ҫ�ӿڣ��������Ѿ��������ݿ����ӵĻ����ϣ������ݿⷢ��Ҫִ�е�SQL��䡣Statement��������ִ�в��������ļ�SQL��䡣
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
