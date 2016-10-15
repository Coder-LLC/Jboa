package Dao_Interface;

import java.util.List;

import Entity.Employee;

public interface EmployeeDao {
    
    public List <Employee> getEmployeeByCondition(Employee condition);
    
}
