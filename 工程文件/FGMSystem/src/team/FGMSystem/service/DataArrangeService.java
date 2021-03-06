/**
 * @author  Shebin Xu
 *	���ݷ��������� 	DataArrangeService
 *
 */
package team.FGMSystem.service;

import team.FGMSystem.entity.Employee;

public interface DataArrangeService {
	/**
	 * ��ѯԱ���������
	 * @param emp
	 * @param date
	 */
	void queryEmpAttendance(String date);
	/**
	 * ��ѯԱ���Ӱ����
	 * @param emp
	 * @param date
	 */
	void queryEmpWorkOverTime(String date);
	/**
	 * ��ѯȫ�����
	 * @param date
	 */
	void queryAttendance(String date);
	/**
	 * ��ѯ�Ӱ����
	 * @param date
	 */
	void queryWorkOverTime(String date);
	/**
	 * ��ѯ�ÿ���Ϣ
	 * @param vis
	 */
	void queryVisitInf(String date);
	
	
}
