package Dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import Entity.Employee;


public class EmployeeDaoImpl extends BaseDao implements Dao_Interface.EmployeeDao {
	public String createHqlforQuery(Employee condition){
		String hql=null;
		StringBuffer sb=new StringBuffer();
		sb.append("from Employee as e where 1=1");
		if(condition!=null){
			if(condition.getId()!=0&&!"".equals(condition.getId())){
				sb.append(" and e.id=:id");				
			}
			if(condition.getName()!=null&&!"".equals(condition.getName())){
				sb.append(" and e.name=:name");
			}
			if(condition.getPassword()!=null&&!"".equals(condition.getPassword())){
				sb.append(" and e.password=:password");
			}
			if(condition.getPosition()!=null){
				sb.append(" and e.position.id=:position_id");
			}
			if(condition.getDepartment()!=null){
				sb.append(" and e.department.id=:department_id");
			}
			if(condition.getStatus()!=null&&!"".equals(condition.getStatus())){
				sb.append(" and e.status like ?");
			}
		}
		hql=sb.toString();
		return hql;
	}
	
	public List <Employee> getEmployeeByCondition(Employee condition){	
		Session session=getSession();
		List <Employee> list=null;
	    String hql=createHqlforQuery(condition);
		try{
		    Query query=session.createQuery(hql);
		    if(condition!=null){
			  if(condition.getId()!=0&&!"".equals(condition.getId())){
				  query.setInteger("id", condition.getId());				
			  }
			  if(condition.getName()!=null&&!"".equals(condition.getName())){
				  query.setString("name", condition.getName());
			  }
			  if(condition.getPassword()!=null&&!"".equals(condition.getPassword())){
				  query.setString("password", condition.getPassword());
			  }
			  if(condition.getPosition()!=null){
				  query.setInteger("position_id", condition.getPosition().getId());
			  }
			  if(condition.getDepartment()!=null){
				  query.setInteger("department_id", condition.getDepartment().getId());
			  }
			  if(condition.getStatus()!=null&&!"".equals(condition.getStatus())){
				  query.setString(0 , "%"+condition.getStatus()+"%");
			  }
		    }
			list=query.list();
		}
		catch(HibernateException e){
			e.printStackTrace();
		}	
		return list;
    }
}