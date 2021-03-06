/**
 * @author  Shebin Xu
 *	Ա������ӿ��� 	EmployeeServiceDao 
 *
 */
package team.FGMSystem.dao;

import java.sql.ResultSet;
import team.FGMSystem.entity.*;
import java.util.*;

public interface EmployeeServiceDao {
	
	/**
	 * Ա����¼
	 */
	public Object login(String sql,Object[] param);
	
	/**
	 * Ա��ǩ����Ϣ����
	 * @param empNum
	 * @return null
	 */
	public boolean insertSignIn(String sql,Object[] param);
	/**
	 * Ա��ǩ����Ϣ����
	 * @param empNum
	 * @return count
	 */
	public boolean insertSignOut(String sql,Object[] param);
	/**
	 * Ա����ѯ�������
	 * @param empNum
	 * @param date
	 * @return queryResultSet
	 */
	public List<Employee> queryAttendance(String sql,Object[] param,int index);
	/**
	 * Ա����ѯ�Ӱ����
	 * @param empNum
	 * @param date
	 * @return queryResultSet
	 */
	public ResultSet queryWorkOvertime(String empNum,String date);
	
}
