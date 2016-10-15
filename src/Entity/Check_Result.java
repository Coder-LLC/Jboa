package Entity;

import java.util.Date;

public class Check_Result implements java.io.Serializable {
    private int id;
    private String sheet_type;
    private Voucher voucher;
    private Date check_time;
    private String type;
    private Employee employee;
    private String result;
    private String comment;
    
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getSheet_type() {
		return sheet_type;
	}
	
	public void setSheet_type(String sheet_type) {
		this.sheet_type = sheet_type;
	}
	
	public Voucher getVoucher() {
		return voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}

	public Date getCheck_time() {
		return check_time;
	}
	
	public void setCheck_time(Date check_time) {
		this.check_time = check_time;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}  

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
       
}
