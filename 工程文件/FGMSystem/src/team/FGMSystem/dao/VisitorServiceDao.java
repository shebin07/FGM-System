/**
 * @author  Shebin Xu
 *	访客服务接口类	VisitorServiceDao
 *
 */
package team.FGMSystem.dao;

import team.FGMSystem.entity.Visitor;
import java.sql.*;
import team.FGMSystem.entity.*;

public interface VisitorServiceDao {
	/**
	 * 访客预约
	 * @param vis
	 * @return count
	 */
	public int insertMakeAppoint(String sql,Object[] param);
	/**
	 * 访客来访
	 * @param vis
	 * @return count
	 */
	public Visitor alterVisit(String sql,Object[] param);
	
}
