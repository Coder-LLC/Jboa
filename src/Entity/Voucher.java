package Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Voucher implements java.io.Serializable {
    private int id;
    private Employee employee1;//待处理人
    private Employee employee2;//填报人
    private Date create_time;
    private String event;
    private Double total_account;
    private String status;
    private List <Voucher_Detail> voucher_detail=new ArrayList();
    
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}     

	public Employee getEmployee1() {
		return employee1;
	}

	public void setEmployee1(Employee employee1) {
		this.employee1 = employee1;
	}

	public Employee getEmployee2() {
		return employee2;
	}

	public void setEmployee2(Employee employee2) {
		this.employee2 = employee2;
	}

	public Date getCreate_time() {
		return create_time;
	}
	
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public String getEvent() {
		return event;
	}
	
	public void setEvent(String event) {
		this.event = event;
	}
	
	public Double getTotal_account() {
		return total_account;
	}
	
	public void setTotal_account(Double total_account) {
		this.total_account = total_account;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
    
	public List <Voucher_Detail> getVoucher_detail() {
		return voucher_detail;
	}
	
	public void setVoucher_detail(List <Voucher_Detail> voucher_detail) {
		this.voucher_detail = voucher_detail;
	}
	
}
