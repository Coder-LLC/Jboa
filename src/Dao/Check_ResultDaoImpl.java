package Dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import Dao_Interface.Check_ResultDao;
import Entity.Check_Result;

public class Check_ResultDaoImpl extends BaseDao implements Check_ResultDao {
    public int addCheckResult(Check_Result check_result){
    	int i=0;
    	try {
			Session session=getSession();
			session.save(check_result);
			i=1;
		} 
    	catch (HibernateException e) {
			e.printStackTrace();
		}
    	return i;
    }
}
