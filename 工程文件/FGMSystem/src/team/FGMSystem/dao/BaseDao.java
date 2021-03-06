/**
 * @author 赖苗苗
 *	基本接口类	BaseDao
 *
 */
package team.FGMSystem.dao;

import java.sql.*;
import java.io.*;
import java.util.*;

public class BaseDao {
	public static String DRIVER; 	// 数据库驱动
	public static String URL; 		// url
	public static String DBNAME; 	// 数据库用户名
	public static String DBPASS; 	// 数据库密码
	Connection conn = null;			// 数据连接对象
	
	static{//静态代码块,在类加载的时候执行
		init();
	}
	
	/**
	 * 初始化连接参数,从配置文件里获得
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
	 * 得到数据库连接
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @return 数据库连接
	 */
	public Connection getConn() throws ClassNotFoundException, SQLException {
		Connection conn=null;
		try{
			Class.forName(DRIVER);  //加载数据库驱动
			conn=DriverManager.getConnection(URL,DBNAME,DBPASS);  //连接数据库
		}catch(SQLException e){
			e.printStackTrace();
		}
		return conn;
		
	}

	/**
	 * 释放资源
	 *
	 * @param conn	数据库连接
	 * @param pstmt	PreparedStatement对象
	 * @param rs	结果集
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
	 * 执行SQL语句，可以进行增、删、改的操作，不能执行查询
	 * 
	 * @param sql	预编译的 SQL 语句
	 * @param param	预编译的 SQL 语句中的‘？’参数的字符串数组
	 * @return 		影响的条数
	 */
	public int executeSQL(String preparedSql, Object[] param) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int num=0;
		try{
			conn=getConn();
			pstmt=conn.prepareStatement(preparedSql);  //创建prepareStatement对象
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
