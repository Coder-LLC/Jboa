package Server;

import org.springframework.beans.factory.annotation.Autowired;

import Dao_Interface.Check_ResultDao;
import Entity.Check_Result;
import Server_Interface.Check_ResultServer;

public class Check_ResultServerImpl implements Check_ResultServer {
	
	@Autowired
	private Check_ResultDao crd;
	
	public int addCheckResult(Check_Result check_result){
		int i=0;
		i=crd.addCheckResult(check_result);
		return i;
	}
}
