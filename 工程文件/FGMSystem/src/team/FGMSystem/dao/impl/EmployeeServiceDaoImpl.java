/**
 * @author 1.谢琼颉
 *	员工服务实现类	EmployeeServiceDaoImpl
 *
 */
package team.FGMSystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import team.FGMSystem.dao.BaseDao;
import team.FGMSystem.dao.EmployeeServiceDao;
import team.FGMSystem.entity.Employee;

public class EmployeeServiceDaoImpl extends BaseDao implements EmployeeServiceDao {

	private Connection conn=null;//保存数据库连接
	private PreparedStatement pstmt=null;//用于执行SQL语句
	private ResultSet rs=null;//用户保存查询结果集
	
	/**
	 * 员工登录
	 */
	@Override
	public Object login(String sql, Object[] param) {
			try {
				conn = getConn(); // 得到数据库连接
				pstmt = conn.prepareStatement(sql); // 得到PreparedStatement对象
				if (param != null) {
					for (int i = 0; i < param.length; i++) {
						pstmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
					}
				}
				rs = pstmt.executeQuery(); // 执行SQL语句	
				
				if(rs.next()){
					return rs.getString(3);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				super.closeAll(conn, pstmt, rs);
			}
			return null;

	}

	/**
	 * 员工签到信息插入
	 */
	@Override
	public boolean insertSignIn(String sql,Object[] param) {
		try {
			conn = getConn(); // 得到数据库连接
			//先检查是否已经签到
			String presql="select* from clock where employee_id=? and date=?";
			pstmt = conn.prepareStatement(presql); // 得到PreparedStatement对象
			if (param != null) {
				for (int i = 0; i < param.length-1; i++) {
					pstmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
				}
			}
			rs = pstmt.executeQuery(); // 执行SQL语句	
			if(rs.next()){
				if(rs.getString("clock_in_time")!=null){
					System.out.print("您已签到!");
					return false;
				}else{
					executeSQL(sql,param);
					return true;
				}
			}else{
				executeSQL(sql,param);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		
		return false;
	}

	/**
	 * 员工签退
	 */
	@Override
	public boolean insertSignOut(String sql,Object[] param) {
		try {
			conn = getConn(); // 得到数据库连接
			//先检查是否已经签退
			String presql="select* from clock where employee_id=? and date=?";
			pstmt = conn.prepareStatement(presql); // 得到PreparedStatement对象
			if (param != null) {
				for (int i = 1; i < param.length; i++) {
					pstmt.setObject(i, param[i]); // 为预编译sql设置参数
				}
			}
			rs = pstmt.executeQuery(); // 执行SQL语句	
			if(rs.next()){
				if(rs.getString("clock_out_time")!=null){
					System.out.print("您已签退!");
					return false;
				}
				if(rs.getString("clock_in_time")==null){
					System.out.print("您还未签到!");
					return false;
				}
				else{
					executeSQL(sql,param);
					return true;
				}
			}else{
				System.out.print("您还未签到!");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return false;
	}
	
	/**
	 * 查询出勤情况
	 */
	@Override
	public List<Employee> queryAttendance(String sql,Object[] param,int index) {
		try {
			conn = getConn(); // 得到数据库连接
			pstmt = conn.prepareStatement(sql); // 得到PreparedStatement对象
			List<Employee> staffList = new ArrayList<Employee>();
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
				}
			}
			rs = pstmt.executeQuery(); // 执行SQL语句	
			
			while(rs.next()){
				Employee staff=new Employee();
				if(index==1){   //查询clock表
					staff.setEmpNum(rs.getString("employee_id"));
					staff.setNowDate(rs.getString("date"));
					staff.setIn_time(rs.getString("clock_in_time"));
					staff.setOut_time(rs.getString("clock_out_time"));
					staffList.add(staff);
				}else if(index==2){  //查询employee表
					staff.setName(rs.getString("name"));
					staff.setEmpNum(rs.getString("employee_id"));
					staff.setDepartment(rs.getString("department"));
					staff.setJob(rs.getString("position"));
					staff.setPhoneNum(rs.getString("phone"));
					staff.setEntryDate(rs.getString("entry_date"));
					staff.setSign_in_sum(Integer.valueOf(rs.getString("sign_in_sum")).intValue());
					staff.setOvertime_sum(Integer.valueOf(rs.getString("overtime_sum")).intValue());
					staffList.add(staff);
					}
			}
			return staffList;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return null;
	}

	/**
	 * 查询加班情况
	 */
	@Override
	public ResultSet queryWorkOvertime(String empNum, String date) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
