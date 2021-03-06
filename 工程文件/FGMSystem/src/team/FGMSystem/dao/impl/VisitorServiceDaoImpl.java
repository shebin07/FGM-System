/**
 * @author 
 *	访客服务接口实现类	 VisitorServiceDaoImpl
 *
 */
package team.FGMSystem.dao.impl;

import team.FGMSystem.dao.BaseDao;
import team.FGMSystem.dao.VisitorServiceDao;
import team.FGMSystem.entity.Visitor;
import java.sql.*;


public class VisitorServiceDaoImpl extends BaseDao implements VisitorServiceDao {

	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
    
    /**
	 * 访客预约
	 * @param vis
	 * @return count
	 */
	@Override
	public int insertMakeAppoint(String sql,Object[] param) {
		int count=super.executeSQL(sql, param);
		return count;
	}

	/**
	 * 访客来访
	 * @param vis
	 * @return count
	 */
	@Override
	public Visitor alterVisit(String sql,Object[] param){
		 try {
	            conn = getConn(); // 得到数据库连接
	            pstmt = conn.prepareStatement(sql); // 得到PreparedStatement对象
	            if (param != null) {
					for (int i = 0; i < param.length; i++) {
						pstmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
					}
				}
	            rs = pstmt.executeQuery(); // 执行SQL语句
	            if (rs.next()) {
	               Visitor vs=new Visitor();
	               vs.setName(rs.getString("NAME"));
	               vs.setPhoneNum(rs.getString("phoneNum"));
	               String retime=rs.getString("visitDate");
	               vs.setApitInf(rs.getString("apitImfo"));
	               vs.setApit(rs.getBoolean("isApit"));
	               return vs;
	            }
	            return null;

	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        } finally {
	            super.closeAll(conn, pstmt, rs);
	        }
	    }
	}


