/**
 * @author ������
 *	�����ӿ���	BaseDao
 *
 */
package team.FGMSystem.dao;

import java.sql.*;
import java.io.*;
import java.util.*;

public class BaseDao {
	public static String DRIVER; 	// ���ݿ�����
	public static String URL; 		// url
	public static String DBNAME; 	// ���ݿ��û���
	public static String DBPASS; 	// ���ݿ�����
	Connection conn = null;			// �������Ӷ���
	
	static{//��̬�����,������ص�ʱ��ִ��
		init();
	}
	
	/**
	 * ��ʼ�����Ӳ���,�������ļ�����
	 */
	public static void init(){
		Properties params=new Properties();
		String config="database.properties";
		InputStream is=BaseDao.class.getClassLoader().getResourceAsStream(config);
		try{
			params.load(is);
		}catch(IOException e){
			e.printStackTrace();
		}
		DRIVER=params.getProperty("driver");
		URL=params.getProperty("url");
		DBNAME=params.getProperty("user");
		DBPASS=params.getProperty("password");
	}   

	/**
	 * �õ����ݿ�����
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @return ���ݿ�����
	 */
	public Connection getConn() throws ClassNotFoundException, SQLException {
		Connection conn=null;
		try{
			Class.forName(DRIVER);  //�������ݿ�����
			conn=DriverManager.getConnection(URL,DBNAME,DBPASS);  //�������ݿ�
		}catch(SQLException e){
			e.printStackTrace();
		}
		return conn;
		
	}

	/**
	 * �ͷ���Դ
	 *
	 * @param conn	���ݿ�����
	 * @param pstmt	PreparedStatement����
	 * @param rs	�����
	 */
	public void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if(rs!=null){
			try{
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(pstmt!=null){
			try{
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * ִ��SQL��䣬���Խ�������ɾ���ĵĲ���������ִ�в�ѯ
	 * 
	 * @param sql	Ԥ����� SQL ���
	 * @param param	Ԥ����� SQL ����еġ������������ַ�������
	 * @return 		Ӱ�������
	 */
	public int executeSQL(String preparedSql, Object[] param) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int num=0;
		try{
			conn=getConn();
			pstmt=conn.prepareStatement(preparedSql);  //����prepareStatement����
			if(param!=null){
				for(int i=0;i<param.length;i++){
					pstmt.setObject(i+1, param[i]);
				}
			}
			num=pstmt.executeUpdate();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, null);
		}
		return num;
	}
}
