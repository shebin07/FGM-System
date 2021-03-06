/**
 * @author 
 *	�ÿͷ���ӿ�ʵ����	 VisitorServiceDaoImpl
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
	 * �ÿ�ԤԼ
	 * @param vis
	 * @return count
	 */
	@Override
	public int insertMakeAppoint(String sql,Object[] param) {
		int count=super.executeSQL(sql, param);
		return count;
	}

	/**
	 * �ÿ�����
	 * @param vis
	 * @return count
	 */
	@Override
	public Visitor alterVisit(String sql,Object[] param){
		 try {
	            conn = getConn(); // �õ����ݿ�����
	            pstmt = conn.prepareStatement(sql); // �õ�PreparedStatement����
	            if (param != null) {
					for (int i = 0; i < param.length; i++) {
						pstmt.setObject(i + 1, param[i]); // ΪԤ����sql���ò���
					}
				}
	            rs = pstmt.executeQuery(); // ִ��SQL���
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


