/**
 * @author Shebin Xu
 *	���ݷ����ӿ���	DataArrangeServiceDao 
 *
 */
package team.FGMSystem.dao;

import java.util.List;

import team.FGMSystem.entity.Employee;
import team.FGMSystem.entity.Visitor;

public interface DataArrangeServiceDao {
	
//	/**
//	 * ����ĳ�·ÿ�����
//	 * @return sum
//	 */
//	public int queryCountVisitor(String date);
	/**
	 * ��ѯȫ��Ա��
	 * @return List<Employee>
	 */
	
	/**
     * ��ѯԱ���������
     *
     * @param emp
     * @param date
     * @return null
     * @author Tebby
     * @date 2020-08-27 15:46
     */
	List<Employee> queryEmpAttendance(String sql,Object[]param);
    
    /**
     * ��ѯԱ���Ӱ����
     *
     * @param emp
     * @param date
     * @return null
     * @author Tebby
     * @date 2020-08-27 15:48
     */
	List<Employee> queryEmpWorkOverTime(String sql,Object[]param);

    /**
     * ��ѯȫ�����
     *
     * @param date
     * @return null
     * @author Tebby
     * @date 2020-08-27 15:48
     */
	List<Employee> queryAttendance(String sql,Object[]param);
	
	/**
	 * ��ѯ�Ӱ����
	 * @param sql
	 * @param param
	 * @return
	 * @author shebin
	 */
	List<Employee> queryWorkOverTime(String sql,Object[]param);

    /**
	 * ��ѯ�ÿ���Ϣ
     * @param
     * @return
     * @author Tebby
     * @date 2020-08-27 15:49
     */
    List<Visitor> queryVisitInf(String sql,Object[] param);

}
