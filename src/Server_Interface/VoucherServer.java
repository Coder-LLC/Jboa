package Server_Interface;

import java.util.List;

import Entity.Employee;
import Entity.Page;
import Entity.Voucher;
import Entity.Voucher_Detail;

public interface VoucherServer {
	public int getVoucherCount(Voucher voucher,Employee employee);
	
	public List <Object> queryForVoucher(int pageNumber,Employee employee1,Voucher voucher);//以分页的形式查询
    
    public boolean addVoucher(Voucher voucher);
    
    public int getMaxId();
    
    public List <Object> getVoucherDetail(Voucher voucher,Voucher_Detail voucher_detail);
    
    public int modifyVoucher(Voucher voucher);
    
    public int deleteVoucher(Voucher voucher);
    
    public List <Object> getVoucherForMiddle(int pageNumber,Employee employee,Voucher voucher);
    
    public List <Object> getVoucherForHigh(int pageNumber,Employee employee,Voucher voucher);
    
    public int changeVoucherStatus(Voucher voucher);
    
    public Page devidePage(int pageNumber,Voucher voucher,Employee employee);
}
