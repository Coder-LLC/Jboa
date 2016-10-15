package Entity;

import java.util.Date;

public class Account implements java.io.Serializable {
    private int id;
    private Employee employee;
    private String accunt_type;
    private double account_money;
    private int sheet_id;
    private Date accunt_time;
    
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}   

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getAccunt_type() {
		return accunt_type;
	}
	
	public void setAccunt_type(String accunt_type) {
		this.accunt_type = accunt_type;
	}
	
	public double getAccount_money() {
		return account_money;
	}
	
	public void setAccount_money(double account_money) {
		this.account_money = account_money;
	}
	
	public int getSheet_id() {
		return sheet_id;
	}
	
	public void setSheet_id(int sheet_id) {
		this.sheet_id = sheet_id;
	}
	
	public Date getAccunt_time() {
		return accunt_time;
	}
	
	public void setAccunt_time(Date accunt_time) {
		this.accunt_time = accunt_time;
	}  
    
}
