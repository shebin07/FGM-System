/**
 * @author  Shebin Xu
 *	�ÿͷ���ӿ���	VisitorServiceDao
 *
 */
package team.FGMSystem.dao;

import team.FGMSystem.entity.Visitor;
import java.sql.*;
import team.FGMSystem.entity.*;

public interface VisitorServiceDao {
	/**
	 * �ÿ�ԤԼ
	 * @param vis
	 * @return count
	 */
	public int insertMakeAppoint(String sql,Object[] param);
	/**
	 * �ÿ�����
	 * @param vis
	 * @return count
	 */
	public Visitor alterVisit(String sql,Object[] param);
	
}
