/**
 * @author  Shebin Xu
 *	����������	PackageService
 *
 */
package team.FGMSystem.service;
import team.FGMSystem.entity.Package;
import team.FGMSystem.entity.Employee;

public interface PackageService {
	/**
	 * ��ݱ�ǩ��
	 * @param emp
	 * @param packNum
	 */
	void ReceivePackage(Employee emp);
	/**
	 * ��ݱ��ĳ�
	 * @param emp
	 */
	void SendPackage(Employee emp);
	/**
	 * ��ݽ�������������ݹ�˾���ߣ�
	 * @param emp
	 */
	void expressTakePack(Employee emp);
	/**
	 * ǰ̨¼���µ������Ϣ
	 */
	void insertNewPackage(Package pk);
	/**
	 * ���㵱�¿�ݷ���
	 * @param date
	 * @return cost
	 */
	public double queryPackCost(String date);
}
