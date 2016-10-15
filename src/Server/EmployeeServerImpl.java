package Server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import Dao_Interface.EmployeeDao;
import Entity.Employee;
import Server_Interface.EmployeeServer;

public class EmployeeServerImpl implements EmployeeServer {
	@Autowired
    private EmployeeDao ed;
    
    public List <Employee> queryForEmployee(Employee employee){
    	List <Employee> list=ed.getEmployeeByCondition(employee);
    	if(list!=null){
    		return list;
    	}
    	return null;
    }
    
}
