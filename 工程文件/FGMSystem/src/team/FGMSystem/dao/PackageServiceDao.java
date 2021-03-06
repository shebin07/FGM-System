/**
 * @author  Shebin Xu
 *	��������ӿ���	PackageServiceDao
 *
 */
package team.FGMSystem.dao;

import java.util.List;

import team.FGMSystem.entity.*;
import team.FGMSystem.entity.Package;

public interface PackageServiceDao {
	/**
	 * ǩ�տ��
	 * @param empNum
	 * @param packNum
	 * @return count
	 */
	public  List<Package> alterReceivePackage(String sql, Object[] param);
	/**
	 * �ĳ����
	 * @param empNum
	 * @return count
	 */
	public int insertSendPackage(String sql, Object[] param);
	/**
	 * ��ݹ�˾ȡ�߿��
	 * @return count
	 */
	public int alterExpressTakePack(String sql, Object[] param);
	
	/**
	 * ���㵱�¿�ݷ���
	 */
	public List<Package> queryPackCost(String sql,Object[]param);
}
