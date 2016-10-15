package Action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import Action_Interface.Check_ResultAction;
import Entity.Check_Result;
import Entity.Employee;
import Entity.Voucher;
import Server_Interface.Check_ResultServer;
import Server_Interface.VoucherServer;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Check_ResultActionImpl extends ActionSupport implements Check_ResultAction {
	
	private int voucher_id;
	private String position;
	private String result;
	private String comment;
	private int employee_id;
	@Autowired
	private Check_ResultServer crs;
	@Autowired
	private VoucherServer vs;
	private String message;
		
	public int getVoucher_id() {
		return voucher_id;
	}

	public void setVoucher_id(int voucher_id) {
		this.voucher_id = voucher_id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getVoucher(){
		if(voucher_id!=0){
		   ActionContext ac=ActionContext.getContext();
		   Map request=(Map)ac.get("request");
	       request.put("voucher_id", voucher_id);
	       return SUCCESS;
		}
		return ERROR;
	 }
	
	public String getCheckResult(){
		String string=null;
		//解决url传值乱码
		try {
			string=new String(position.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(string);
		Check_Result check_result=new Check_Result();
		Voucher voucher=new Voucher();
		Employee employee=new Employee();
		Date date=new Date();
		voucher.setId(voucher_id);
		voucher.setStatus(result);		
		employee.setId(employee_id);
		check_result.setVoucher(voucher);
		check_result.setResult(result);
		check_result.setEmployee(employee);
		check_result.setCheck_time(date);
		check_result.setComment(comment);
		int i=vs.changeVoucherStatus(voucher);
		int j=crs.addCheckResult(check_result);
		if(string.equals("部门经理")){
			if(i!=0&&j!=0){
				message="审核成功！";
				return "middlemaneger";
			}
			message="审核失败！";
			return "middlemaneger";
		}
		if(string.equals("总经理")){
			if(i!=0&&j!=0){
				message="审核成功！";
				return "highemaneger";
			}
			message="审核失败！";
			return "highemaneger";
		}
		return ERROR;
	}
}
