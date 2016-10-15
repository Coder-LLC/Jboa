package Server_Interface;

import java.util.List;

import Entity.Voucher_Detail;

public interface Voucher_DetailServer {
	public List <Voucher_Detail> getVoucherByCondition(Voucher_Detail condition);
	 
	public int addVoucher_Detail(List <Voucher_Detail> voucher_detail);
}
