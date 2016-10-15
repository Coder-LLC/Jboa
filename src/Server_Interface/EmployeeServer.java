package Server_Interface;

import java.util.List;

import Entity.Employee;

public interface EmployeeServer {
	public List <Employee> queryForEmployee(Employee employee);

}
