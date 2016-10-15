package Server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import Dao_Interface.VoucherDao;
import Dao_Interface.Voucher_DetailDao;
import Entity.Employee;
import Entity.Page;
import Entity.Voucher;
import Entity.Voucher_Detail;
import Server_Interface.VoucherServer;
import Server_Interface.Voucher_DetailServer;

public class VoucherServerImpl implements VoucherServer {
	@Autowired
	private VoucherDao vd;
	@Autowired
	private Voucher_DetailDao vdd;	
	@Autowired
	private Voucher_DetailServer vds;
	
	
	public int getVoucherCount(Voucher voucher,Employee employee){
		int i=0;
		i=vd.getVoucherCount(voucher,employee);
		return i;
	}
	
	public List <Object> queryForVoucher(int pageNumber,Employee employee,Voucher voucher){
		List <Object> all=new ArrayList <Object>();	
		Page page=devidePage(pageNumber,voucher,employee);
		all.add(page);
		List <Voucher> list=null;
		list=vd.getVoucherByCondition(voucher,page.getStart(),page.getSize());
		if(list!=null){
			all.add(list);
		}
		return all;
	}
	
	public boolean addVoucher(Voucher voucher){
		int i=0;
		boolean b=false;
		i=vd.addVoucher(voucher);
		if(i!=0){
			b=true;
		}
		return b;
	}
	
	public int getMaxId(){
		int i=0;
		i=vd.getMaxId();
		return i;
	}
	
	 public List <Object> getVoucherDetail(Voucher voucher,Voucher_Detail voucher_detail){
		 List <Object> all=new ArrayList <Object> ();
		 List <Voucher> list=vd.getVoucherByCondition(voucher, 0, 1);
		 all.add(list);
		 List <Voucher_Detail> voucher_details=vds.getVoucherByCondition(voucher_detail);
		 all.add(voucher_details);	 
		 return all;
	 }
	 
	 public int modifyVoucher(Voucher voucher){
		 int i=0;
		 i=vd.modifyVoucher(voucher);
		 return i;
	 }
	 
	 public int deleteVoucher(Voucher voucher){
		 int i=0;
		 i=vd.deleteVoucher(voucher);
		 return i;
	 }
	 
	 public List <Object> getVoucherForMiddle(int pageNumber,Employee employee,Voucher voucher){
		 List <Object> all=new ArrayList <Object>();	
		 Page page=devidePage(pageNumber,voucher,employee);
		 all.add(page);
		 List <Voucher> list=null;
		 list=vd.getVoucherForMiddle(voucher, employee.getDepartment(), page.getStart(), page.getSize());
		 if(list!=null){
			all.add(list);
		 }
		 return all;
	 }
	 
	 public List <Object> getVoucherForHigh(int pageNumber,Employee employee,Voucher voucher){
		 List <Object> all=new ArrayList <Object>();	
		 Page page=devidePage(pageNumber,voucher,employee);
		 all.add(page);
		 List <Voucher> list=null;
		 list=vd.getVoucherForHigh(voucher, page.getStart(), page.getSize());
		 if(list!=null){
			all.add(list);
		 }
		 return all;
	 }
	 
	 public int changeVoucherStatus(Voucher voucher){
		 int i=0;
		 i=vd.changeVoucherStatus(voucher);
		 return i;
	 }
	 
	 public Page devidePage(int pageNumber,Voucher voucher,Employee employee){
	     Page page=new Page();
	     int start=0;
	     int totalpage=0;
		 if(pageNumber==0){
		    pageNumber=1;
		 } 
		int count=getVoucherCount(voucher,employee);
		int size=5;//每页显示条数
		if(count!=0){ 	
		    totalpage=count/size;
		    if(count%size!=0){
			   totalpage++;
			}
		    start=(pageNumber-1)*size;
	        page.setSize(size);
	        page.setStart(start);
	        page.setTotalpage(totalpage);
	    }
		return page;
	  }
}
