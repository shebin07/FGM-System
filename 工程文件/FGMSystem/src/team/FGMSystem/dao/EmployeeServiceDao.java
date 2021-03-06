/**
 * @author  Shebin Xu
 *	员工服务接口类 	EmployeeServiceDao 
 *
 */
package team.FGMSystem.dao;

import java.sql.ResultSet;
import team.FGMSystem.entity.*;
import java.util.*;

public interface EmployeeServiceDao {
	
	/**
	 * 员工登录
	 */
	public Object login(String sql,Object[] param);
	
	/**
	 * 员工签到信息插入
	 * @param empNum
	 * @return null
	 */
	public boolean insertSignIn(String sql,Object[] param);
	/**
	 * 员工签退信息插入
	 * @param empNum
	 * @return count
	 */
	public boolean insertSignOut(String sql,Object[] param);
	/**
	 * 员工查询出勤情况
	 * @param empNum
	 * @param date
	 * @return queryResultSet
	 */
	public List<Employee> queryAttendance(String sql,Object[] param,int index);
	/**
	 * 员工查询加班情况
	 * @param empNum
	 * @param date
	 * @return queryResultSet
	 */
	public ResultSet queryWorkOvertime(String empNum,String date);
	
}
