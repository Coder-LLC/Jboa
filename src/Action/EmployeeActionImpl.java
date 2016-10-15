package Action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import Action_Interface.EmployeeAction;
import Entity.Department;
import Entity.Employee;
import Entity.Page;
import Entity.Voucher;
import Server_Interface.EmployeeServer;
import Server_Interface.VoucherServer;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class EmployeeActionImpl extends ActionSupport implements EmployeeAction {
    private String username;
    private String password;
    @Autowired
    private EmployeeServer es;
    private String message;
    private int employee_id;
    @Autowired
    private VoucherServer vs;
    private int pageNumber;
    private int department_id;
    private String status;
    
    public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	//用户登录
	public String employeeLogin(){
    	Employee employee=new Employee();
    	employee.setName(username);
    	employee.setPassword(password);
    	try{
        	List <Employee> list=es.queryForEmployee(employee);
        	//解耦合访问Servlet API
        	Iterator <Employee> iterator=list.iterator();
        	if(iterator.hasNext()){
        		//普通员工视图
        		employee=iterator.next();
            	ActionContext ac=ActionContext.getContext();
            	Map session=ac.getSession();
            	session.put("employee", list);
            	session.put("employee_id",employee.getId());
            	//部门经理视图
            	if(employee.getPosition().getName().equals("部门经理")){
              	   String middle="middlemaneger";
                     return middle;
              	}
            	//总经理视图
            	if(employee.getPosition().getName().equals("总经理")){
               	   String high="highemaneger";
                      return high;
               	}
            	return SUCCESS;		           	
        	}
        	message="用户名或密码错误，请检查后重试";
            return INPUT;	
    	}
    	catch(Exception e){
    		e.printStackTrace();
    		return ERROR;
    	}
    }
	
	//查询当前员工下的所有报销单
    public String employeeQueryVoucher(){	
    	Voucher voucher=new Voucher(); 
    	Page page=null;
    	List <Voucher> list=null;
    	int i=0;
    	if(status!=null&&!"".equals(status)){
      	   if(status.equals("1")){
      		  voucher.setStatus("未审核");
      		  i=1;
      	   }
      	   if(status.equals("2")){
      		  voucher.setStatus("已通过");
      		  i=2;
      	   }
      	   if(status.equals("3")){
      		  voucher.setStatus("未通过");
      		  i=3;
      	   }  
      	}	
    	else{
          	//防止空指针异常
            voucher.setStatus("");	  
    	}
    	Employee employee1=new Employee();
    	Employee employee2=new Employee();
    	Department department=new Department();
    	employee1.setId(0);
    	employee2.setId(employee_id);
    	voucher.setEmployee1(employee1);
    	//设置voucher表中create_id的值
    	voucher.setEmployee2(employee2);
    	//普通员工不需要department_id进行查询，因此设为0
    	department.setId(0);
    	employee1.setDepartment(department);		
	    List <Object> all=vs.queryForVoucher(pageNumber,employee1,voucher);
	    page=(Page)all.get(0);
	    list=(List)all.get(1);
	    if(!list.isEmpty()){
	       ActionContext ac=ActionContext.getContext();
	       Map request=(Map)ac.get("request");
	       request.put("voucher", list);
	       request.put("totalpage",page.getTotalpage());
	       request.put("status", i);
	       return SUCCESS;
	    }
    	else if(list.size()==0){
    		message="无查询结果";
    		return SUCCESS;
    	}
    	else{
    		return ERROR;
    	}				
	}
    
    //用户注销
    public String employeeloginOut(){
    	ActionContext ac=ActionContext.getContext();
        Map session=ac.getSession();
		session.clear();
		return INPUT;
    }
    
    public String middleManegerQueryVoucher(){ 
    	List <Voucher> list=null;
    	Voucher voucher=null;
    	Page page=null;
    	int i=0;
    	if(status!=null&&!"".equals(status)){
    	   if(status.equals("1")){
    		  voucher=new Voucher();
    		  voucher.setStatus("未审核");
    		  i=1;
    	   }
    	   if(status.equals("2")){
    		  voucher=new Voucher();
    		  voucher.setStatus("已通过");
    		  i=2;
    	   }
    	   if(status.equals("3")){
    		  voucher=new Voucher();
    		  voucher.setStatus("未通过");
    		  i=3;
    	   }
    	}
    	else{
          	//防止空指针异常
    		voucher=new Voucher();
            voucher.setStatus("");	  
    	}
    	Department department=new Department();
    	Employee employee=new Employee();
    	department.setId(department_id);	
    	employee.setDepartment(department);	
    	List <Object> all=vs.getVoucherForMiddle(pageNumber,employee,voucher);
 	    page=(Page)all.get(0);
 	    list=(List)all.get(1);
    	if(!list.isEmpty()){
    	   ActionContext ac=ActionContext.getContext();
  	       Map request=(Map)ac.get("request");
  	       request.put("voucher", list);
  	       request.put("totalpage",page.getTotalpage());
  	       request.put("status", i);
    	  return SUCCESS;
    	}
    	else if(list.size()==0){
    		message="无查询结果";
    		return SUCCESS;
    	}
    	else{
    		return ERROR;
    	}
    }
    
    public String highManegerQueryVoucher(){
    	Voucher voucher=null;
    	List <Voucher> list=null;
    	Employee employee=null;
    	Page page=null;
    	int i=0;
    	if(status!=null&&!"".equals(status)){
    	   if(status.equals("1")){
    		  voucher=new Voucher();
    		  voucher.setStatus("未审核");
    		  i=1;
    	   }
    	   if(status.equals("2")){
    		  voucher=new Voucher();
    		  voucher.setStatus("已通过");
    		  i=2;
    	   }
    	   if(status.equals("3")){
    		  voucher=new Voucher();
    		  voucher.setStatus("未通过");
    		  i=3;
    	   }
    	}
    	else{
    		voucher=new Voucher();
    		voucher.setStatus("");
    	}
    	List <Object> all=vs.getVoucherForHigh(pageNumber,employee,voucher);
 	    page=(Page)all.get(0);
 	    list=(List)all.get(1);
    	if(!list.isEmpty()){
     	   ActionContext ac=ActionContext.getContext();
   	       Map request=(Map)ac.get("request");
   	       request.put("voucher", list);
   	       request.put("totalpage",page.getTotalpage());
   	       request.put("status",i);
     	  return SUCCESS;
     	}
     	else if(list.size()==0){
     		message="无查询结果";
     		return SUCCESS;
     	}
     	else{
     		return ERROR;
     	}
    }
}
