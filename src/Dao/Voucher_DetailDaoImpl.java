package Dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import Dao_Interface.Voucher_DetailDao;
import Entity.Voucher_Detail;

public class Voucher_DetailDaoImpl extends BaseDao implements Voucher_DetailDao {
	public String createHqlforQuery(Voucher_Detail condition){
	     String hql=null;
		 StringBuffer sb=new StringBuffer();
		 sb.append("from Voucher_Detail as v where 1=1");
		 if(condition!=null){
			if(condition.getId()!=0&&!"".equals(condition.getId())){
				sb.append(" and v.id=:id");				
			}
			if(condition.getVoucher().getId()!=0&&!"".equals(condition.getVoucher().getId())){
				sb.append(" and v.voucher.id=:voucher_id");
			}
			if(condition.getItem()!=null&&!"".equals(condition.getItem())){
				sb.append(" and v.item=:item");
			}
			if(condition.getAccount()!=null&&!condition.getAccount().equals(0.0)){
				sb.append(" and v.account =:account");
			}
			if(condition.getDesc()!=null&&!"".equals(condition.getDesc())){
				sb.append(" and v.desc like ?");
			}
		  }
		 hql=sb.toString();
		 return hql;
	 }
    
	 public List <Voucher_Detail> getVoucherByCondition(Voucher_Detail condition){
		 Session session=getSession();
		 List <Voucher_Detail> list=null;
		 String hql=createHqlforQuery(condition);		 
		 try{
			Query query=session.createQuery(hql);
			 if(condition!=null){
				if(condition.getId()!=0&&!"".equals(condition.getId())){
				   query.setInteger("id", condition.getId());				
			    }
			    if(condition.getVoucher().getId()!=0&&!"".equals(condition.getVoucher().getId())){
				   query.setInteger("voucher_id", condition.getVoucher().getId());
			    }
			    if(condition.getItem()!=null&&!"".equals(condition.getItem())){
				   query.setString("item", condition.getItem());
			    }
			    if(condition.getAccount()!=null&&!condition.getAccount().equals(0.0)){
				   query.setDouble("account", condition.getAccount());
			    }
			    if(condition.getDesc()!=null&&!"".equals(condition.getDesc())){
				   query.setString(0 , "%"+condition.getDesc()+"%");
			    }
			    list=query.list();
		     }
		 } 
		 catch (HibernateException e) {
			e.printStackTrace();
		 }
		 return list;
    }
	 
	 public int addVoucher_Detail(List<Voucher_Detail> voucher_details){
		 int i=0;
	     Voucher_Detail voucher_detail=new Voucher_Detail();
		 Iterator<Voucher_Detail>it=voucher_details.iterator();		 
		 while(it.hasNext()){
			 voucher_detail=it.next();
			 try{	
				 Session session=getSession();
				 session.save(voucher_detail);
				 i=1;
				 System.out.println(i);
			 } 
			 catch (HibernateException e) {
				e.printStackTrace();
			}
		 }
		 return i;
	 }
}