package Dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import Dao_Interface.VoucherDao;
import Entity.Department;
import Entity.Employee;
import Entity.Voucher;

public class VoucherDaoImpl extends BaseDao implements VoucherDao {
	
	 //为普通员工查询生成HQL语句
	 public String createHqlforQuery(Voucher condition){
	     String hql=null;
		 StringBuffer sb=new StringBuffer();
		 sb.append("from Voucher as v where 1=1");
		 if(condition!=null){
			if(condition.getId()!=0&&!"".equals(condition.getId())){
				sb.append(" and v.id=:id");				
			}
			if(condition.getEmployee1().getId()!=0&&!"".equals(condition.getEmployee1().getId())){
				sb.append(" and v.employee1.id=:nextdealby_id");
			}
			if(condition.getEmployee2().getId()!=0&&!"".equals(condition.getEmployee2().getId())){
				sb.append(" and v.employee2.id=:creator_id");
			}
			if(condition.getCreate_time()!=null){
				sb.append(" and v.create_time=:create_time");
			}
			if(condition.getEvent()!=null&&!"".equals(condition.getEvent())){
				sb.append(" and v.event=:event");
			}
			if(condition.getTotal_account()!=null){
				sb.append(" and v.total_account =:total_account");
			}
			if(condition.getStatus()!=null&&!"".equals(condition.getStatus())){
				sb.append(" and v.status=:status");
			}
			hql=sb.toString();
		  }
		 return hql;
	 }
	 
	 //为查询总记录数生成HQL语句
	 public String createHqlForgetVoucherCount(Voucher voucher,Employee employee){	
		 String hql=null;
		 if(employee!=null){
		   //为员工生成HQL语句	 
		   if(employee.getDepartment().getId()==0){
			   hql="select count(v) from Voucher as v where v.employee2.id=:creator_id";
		   }	 
		   if(voucher!=null&&employee.getDepartment().getId()==0&&!"".equals(voucher.getStatus())){
			   if(voucher.getStatus().equals("未审核")){
				   hql="select count(v) from Voucher as v where v.status='未审核' and v.employee2.id=:creator_id";
			  	   System.out.println(1);
			    }
			   if(voucher.getStatus().equals("未通过")){
				   hql="select count(v) from Voucher as v where v.status='未通过' and v.employee2.id=:creator_id";
			   }
			   if(voucher.getStatus().equals("已通过")){
				   hql="select count(v) from Voucher as v where v.status='已通过' and v.employee2.id=:creator_id";
			   }	 
		   }
		   //为部门经理生成HQL
		   if(employee.getDepartment().getId()!=0){
			   hql="select count(v) from Voucher as v where v.total_account < 5000 and"
					   + " v.employee2.id in (from Employee as e where e.department.id=:department_id)";
		   }
		   if(voucher!=null&&employee.getDepartment().getId()!=0&&!"".equals(voucher.getStatus())){
		     if(voucher.getStatus().equals("未审核")){
			     hql="select count(v) from Voucher as v where v.status='未审核' and v.total_account < 5000 and"
					     + " v.employee2.id in (from Employee as e where e.department.id=:department_id)";
		     }
		     else if(voucher.getStatus().equals("未通过")){
			     hql="select count(v) from Voucher as v where v.status='未通过' and v.total_account < 5000 and"
					     + " v.employee2.id in (from Employee as e where e.department.id=:department_id)";
		     }
		     else if(voucher.getStatus().equals("已通过")){
			     hql="select count(v) from Voucher as v where v.status='已通过' and v.total_account < 5000 and"
					     + " v.employee2.id in (from Employee as e where e.department.id=:department_id)";
		     }
		   }   
		 }
		 //为总经理生成HQL语句
		 if(employee==null&&"".equals(voucher.getStatus())){
			 hql="select count(v) from Voucher as v where v.total_account >= 5000";
		 }
		 if(employee==null&&voucher.getStatus().equals("未审核")){
			 hql="select count(v) from Voucher as v where v.total_account >= 5000 and v.status='未审核'";
		 }
		 if(employee==null&&voucher.getStatus().equals("未通过")){
			 hql="select count(v) from Voucher as v where v.total_account >= 5000 and v.status='未通过'";
		 }
		 if(employee==null&&voucher.getStatus().equals("已通过")){
			 hql="select count(v) from Voucher as v where v.total_account >= 5000 and v.status='已通过'";
		 }
		 return hql;
	 }
	    
	 //为部门经理查询生成HQL语句
	 public String createHqlforgetVoucherForMiddle(Voucher voucher){
	     String hql=null;
		 StringBuffer sb=new StringBuffer();
		 sb.append("from Voucher as v where v.total_account < 5000");
		 if(voucher!=null){
		    if(voucher.getStatus().equals("未审核")){
			   sb.append(" and v.status='未审核'");
		    }
		    if(voucher.getStatus().equals("已通过")){
			   sb.append(" and v.status='已通过'");
		    }
		    if(voucher.getStatus().equals("未通过")){
			   sb.append(" and v.status='未通过'");
		    }
		 }
		 sb.append(" and v.employee2.id in (from Employee as e where e.department.id=:department_id)");
		 hql=sb.toString();
		 return hql;
	 }
	 
	 public List <Voucher> getVoucherByCondition(Voucher condition,int start,int size){
		 List <Voucher> list=null;
		 String hql=createHqlforQuery(condition);
		 try{
			 Session session=getSession();
			 Query query=session.createQuery(hql);
			 if(condition!=null){
				if(condition.getId()!=0&&!"".equals(condition.getId())){
					query.setInteger("id", condition.getId());				
				}
				if(condition.getEmployee1().getId()!=0&&!"".equals(condition.getEmployee1().getId())){
					query.setInteger("nextdealby_id", condition.getEmployee1().getId());
				}
				if(condition.getEmployee2().getId()!=0&&!"".equals(condition.getEmployee2().getId())){
					query.setInteger("creator_id", condition.getEmployee2().getId());
				}
				if(condition.getCreate_time()!=null){
					query.setDate("create_time", condition.getCreate_time());
				}
				if(condition.getEvent()!=null&&!"".equals(condition.getEvent())){
					query.setString("event", condition.getEvent());
				}
				if(condition.getTotal_account()!=null){
					query.setDouble("total_account", condition.getTotal_account());
				}
				if(condition.getStatus()!=null&&!"".equals(condition.getStatus())){
					query.setString("status" , condition.getStatus());
				}
			  }
			 //分页
	    	 query.setFirstResult(start);
	    	 query.setMaxResults(size);
			 list=query.list();
		}
		catch(HibernateException e){
			e.printStackTrace();
		}		 
		return list;
	 }
	 
	 public int getVoucherCount(Voucher voucher,Employee employee){ 
		 int count=0;	
		 if(employee!=null){    
		   try {
			   String hql=createHqlForgetVoucherCount(voucher,employee);
			   Session session=getSession();
			   Query query=session.createQuery(hql);
			   if(employee.getDepartment().getId()!=0){			
				   query.setInteger("department_id", employee.getDepartment().getId());
			   }	 
			   if(employee.getDepartment().getId()==0){
                   if(voucher.getEmployee2().getId()!=0){
				      query.setInteger("creator_id", voucher.getEmployee2().getId());
                   }
			   }
			   long l=(long)query.uniqueResult();//总条数    
			   count=new Long(l).intValue();//long转换为int型
			   return count;
		   } 
		   catch (HibernateException e) {	 
			  e.printStackTrace();
			  return 0;
		   }	
		 }
		 else{	 
			   try {
				   String hql=createHqlForgetVoucherCount(voucher,employee);
				   Session session=getSession();
				   Query query=session.createQuery(hql);
				   long l=(long)query.uniqueResult();//总条数    
				   count=new Long(l).intValue();//long转换为int型
				   return count;
			   }
			   catch(HibernateException e){
				   e.printStackTrace();
				   return 0;
			   }
		 }	
	 }
	 
	 public int addVoucher(Voucher voucher){		
		 int i=0;		
		 try{
			 Session session=getSession();
			 session.save(voucher);
			 i=1;
		 } 
		 catch (HibernateException e) {
			e.printStackTrace();
		}
		 return i;
	 }
	 
	 public int getMaxId(){
		 int i=0;
		 try {
			String hql="select max (v.id) from Voucher as v";
			Session session=getSession();
			Query query=session.createQuery(hql);
			i=(int)query.uniqueResult();
		} 
		 catch (HibernateException e) {
			e.printStackTrace();
		}
		 return i;
	 }
	 
	 public int modifyVoucher(Voucher voucher){
		 int i=0;
		 try {
			Session session=getSession();
			session.update(voucher);
			i=1;
		} 
		 catch (HibernateException e) {
			e.printStackTrace();
		}
		 return i;
	 }
	 
	 public int deleteVoucher(Voucher voucher){
		 int i=0;
		 String hql="delete Voucher as v where v.id=:id";
		 try{			
			Session session=getSession();
			Query query=session.createQuery(hql);
			query.setInteger("id", voucher.getId());
			i=query.executeUpdate();
		 } 
		 catch (HibernateException e) {
			e.printStackTrace();
		 }
		 return i;
	 }
	 
	 public List <Voucher> getVoucherForMiddle(Voucher voucher,Department department,int start,int size){
		 List <Voucher> list=null;
		 String hql=createHqlforgetVoucherForMiddle(voucher);
		 try {
			Session session=getSession();
			Query query=session.createQuery(hql);
			query.setInteger("department_id", department.getId());
			query.setFirstResult(start);
	    	query.setMaxResults(size);
			list=query.list();
		 } 
		 catch (HibernateException e) {
			e.printStackTrace();
		}
		 return list;
	 }
	 
	 public String createHqlForGetVoucherForHigh(Voucher voucher){
	     String hql=null;
		 StringBuffer sb=new StringBuffer();
		 sb.append("from Voucher as v where v.total_account >= 5000");
		 if(!"".equals(voucher.getStatus())){
		    if(voucher.getStatus().equals("未审核")){
			   sb.append(" and v.status='未审核'");
		    }
		    if(voucher.getStatus().equals("已通过")){
			   sb.append(" and v.status='已通过'");
		    }
		    if(voucher.getStatus().equals("未通过")){
			   sb.append(" and v.status='未通过'");
		    }
		 }
		 hql=sb.toString();
		 return hql;
	 }
	 
	 public List <Voucher> getVoucherForHigh(Voucher voucher ,int start,int size){
		 List <Voucher> list=null;
		 String hql=createHqlForGetVoucherForHigh(voucher);
		 try {
			Session session=getSession();
			Query query=session.createQuery(hql);
			query.setFirstResult(start);
	    	query.setMaxResults(size);
			list=query.list();
		 } 
		 catch (HibernateException e) {
			e.printStackTrace();
		 }
		 return list;
	 }
	 
	 public int changeVoucherStatus(Voucher voucher){
		 int i=0;
		 String hql="update Voucher as v set v.status=:status where v.id=:id";
		 try{
			 Session session=getSession();
			 Query query = session.createQuery(hql);
			 query.setInteger("id", voucher.getId());
			 query.setString("status", voucher.getStatus());
			 i=query.executeUpdate();
		 }
		 catch(HibernateException e){
			 e.printStackTrace();
		 }
		 return i;
	 }
}
