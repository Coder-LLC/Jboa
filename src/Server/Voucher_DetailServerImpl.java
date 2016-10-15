package Server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import Dao_Interface.Voucher_DetailDao;
import Entity.Voucher_Detail;
import Server_Interface.Voucher_DetailServer;

public class Voucher_DetailServerImpl implements Voucher_DetailServer {
	@Autowired
	private Voucher_DetailDao vd;
	
    public List <Voucher_Detail> getVoucherByCondition(Voucher_Detail condition){
    	List <Voucher_Detail> list=null;
    	list=vd.getVoucherByCondition(condition);
    	if(list!=null){
			return list;
		}
		return null;
    }
	 
	public int addVoucher_Detail(List <Voucher_Detail> voucher_detail){
		int i=0;
		i=vd.addVoucher_Detail(voucher_detail);
		return i;
	}
}
