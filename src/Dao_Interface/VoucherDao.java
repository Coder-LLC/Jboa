package Dao_Interface;

import java.util.List;

import Entity.Department;
import Entity.Employee;
import Entity.Voucher;

public interface VoucherDao {
	    
	 public List <Voucher> getVoucherByCondition(Voucher condition,int start,int size);
	  
	 public int getVoucherCount(Voucher voucher,Employee employee);
	 
	 public int addVoucher(Voucher voucher);
	 
	 public int getMaxId();
	 
	 public int modifyVoucher(Voucher voucher);
	 
	 public int deleteVoucher(Voucher voucher);
	 
	 public List <Voucher> getVoucherForMiddle(Voucher voucher,Department department,int start,int size);
	 
	 public List <Voucher> getVoucherForHigh(Voucher voucher ,int start,int size);
	 
	 public int changeVoucherStatus(Voucher voucher);
}
