/**
 * @author  Shebin Xu
 *	Ա��������	EmployeeService
 *
 */
package team.FGMSystem.service;

import team.FGMSystem.entity.Employee;

public interface EmployeeService {
	/**
	 * Ա����¼
	 * @param account
	 * @param password
	 * @return
	 */
	Object login(String account,String password);
	/**
	 * Ա��ǩ��
	 * @param emp
	 */
	void signIn(Employee emp);
	/**
	 * Ա��ǩ��
	 * @param emp
	 */
	void signOut(Employee emp);
	/**
	 * Ա����ѯ���³������
	 * @param emp
	 */
	void queryAttendance(Employee emp);
	/**
	 * Ա����ѯ��ʷ�������
	 * @param emp
	 * @param date
	 */
	void queryAttendance(Employee emp,String date);
	/**
	 * Ա����ѯ���¼Ӱ����
	 * @param emp
	 */
	void queryWorkOverTime(Employee emp);
	/**
	 * Ա����ѯ��ʷ�Ӱ����
	 * @param emp
	 * @param date
	 */
	void queryWorkOverTime(Employee emp,String date);
//	/**
//	 * Ա��ǩ�տ��
//	 * @param emp
//	 * @param packNum
//	 */
//	void empReceivePackage(Employee emp,String packNum);
//	/**
//	 * Ա���Ŀ��
//	 * �Զ����ɿ�ݵ���yyyymmddhhmmss
//	 * @param emp
//	 */
//	void empSendPackage(Employee emp);
}
