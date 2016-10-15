package Action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import Server_Interface.VoucherServer;
import Server_Interface.Voucher_DetailServer;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import Action_Interface.VoucherAction;
import Entity.Employee;
import Entity.Voucher;
import Entity.Voucher_Detail;

public class VoucherActionImpl extends ActionSupport implements VoucherAction {
	private Date create_time;
	private double totalacount;
	private String status;
	private String event;
	private List <String> item = new ArrayList<String>();
	private List <String> account=new ArrayList<String>();
	private List <String> desc=new ArrayList<String>();
	@Autowired
	private VoucherServer vs;
	private String message;
	private int vid;

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public double getTotalacount() {
		return totalacount;
	}

	public void setTotalacount(double totalacount) {
		this.totalacount = totalacount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getItem() {
		return item;
	}

	public void setItem(List<String> item) {
		this.item = item;
	}

	public List<String> getAccount() {
		return account;
	}

	public void setAccount(List<String> account) {
		this.account = account;
	}

	public List<String> getDesc() {
		return desc;
	}

	public void setDesc(List<String> desc) {
		this.desc = desc;
	}
	

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}
	
	public Voucher getVoucherinfo(){
		int creator_id=0;//创建人id
		String items=null;
		double accounts=0.0;
		String str=null;
		String descs=null;
		//从session提取登陆id
		ActionContext ac=ActionContext.getContext();
    	Map session=(Map) ac.getSession();
    	Iterator it=session.entrySet().iterator();    	
    	while(it.hasNext()){   
    	    Map.Entry entry = (Entry) it.next();
    	    if(entry.getKey().equals("employee_id")){
    	    	creator_id=Integer.parseInt(entry.getValue().toString());	
    	    }    
        }
    	//将jsp页面中的属性放入Voucher的对象中
    	Employee employee1=new Employee();
		employee1.setId(creator_id);//待处理人
		Employee employee2=new Employee();
		employee2.setId(creator_id);//创建人
		Voucher voucher=new Voucher();	       
     	voucher.setEmployee1(employee1);
     	voucher.setEmployee2(employee2);
        voucher.setCreate_time(create_time);
        voucher.setTotal_account(totalacount);
        voucher.setEvent(event);
        voucher.setStatus(status);  	
		//将jsp页面中3个同名属性取出放入Voucher_Detail的对象中
		Iterator <String> iterator1=item.iterator(); 
		Iterator <String> iterator2=account.iterator();
		Iterator <String> iterator3=desc.iterator();		
		int maxid=0;
		maxid=vs.getMaxId()+1;	
        while(iterator1.hasNext()&&iterator2.hasNext()&&iterator3.hasNext()&&maxid!=0){	 
        	items=iterator1.next();
            str=iterator2.next();
        	descs=iterator3.next();
            if(items!=null&&!items.equals("")&&str!=null&&!str.equals("")&&descs!=null&&!descs.equals("")){
            	Voucher_Detail voucher_detail=new Voucher_Detail();	
            	accounts=Double.parseDouble(str); 
            	voucher.setId(maxid);
            	voucher_detail.setVoucher(voucher);
             	voucher_detail.setItem(items);
             	voucher_detail.setAccount(accounts);
             	voucher_detail.setDesc(descs);
             	voucher.getVoucher_detail().add(voucher_detail);                   	
            }              
        }  
        return voucher;
	}

	public String addVoucher(){
        Voucher voucher=getVoucherinfo();      
        boolean b=false;
        b=vs.addVoucher(voucher);
        if(b){
        	message="添加成功！";
        	return SUCCESS;
        }
        message="添加失败，请重新添加";
		return SUCCESS;
	}
	
	public String getVoucherDetail(){
		Voucher voucher=new Voucher();
		Voucher_Detail voucher_detail=new Voucher_Detail();
		Employee employee1=new Employee();
		Employee employee2=new Employee();
		voucher.setId(vid);
		voucher_detail.setVoucher(voucher);
		//防止空指针异常
		employee1.setId(0);
		employee2.setId(0);
		voucher.setEmployee1(employee1);
		voucher.setEmployee2(employee2);
		List <Object> all=vs.getVoucherDetail(voucher,voucher_detail);
		if(!all.isEmpty()){ 
			List <Voucher> list=(List)all.get(0);
		    List <Voucher_Detail> voucher_details=(List)all.get(1);
			ActionContext ac=ActionContext.getContext();
			Map<String, Object> request=(Map)ac.get("request");
	    	request.put("voucherdetail", list);
	        request.put("voucherdetails", voucher_details);
	        request.put("vid", vid);
	    	return SUCCESS;
		}
		return ERROR;
	}
	
	public String getVoucher(){
		 String vilidation=getVoucherDetail();		 
		 if(vilidation.equals("success")){
			 return SUCCESS;			
		 }
		 return ERROR;
	}
	
	 public String midifyVoucher(){
         Voucher voucher=getVoucherinfo();
         voucher.setId(vid);
         int i=vs.modifyVoucher(voucher);
         if(i!=0){
        	 message="修改成功！";
        	 return SUCCESS;
         }
         message="修改失败，请重试！";
		 return SUCCESS;
	 }
	 
	 public String deleteVoucher(){
		 Voucher voucher=new Voucher();
		 voucher.setId(vid);
		 int i=vs.deleteVoucher(voucher);
		 if(i!=0){
			 message="删除成功";
			 return SUCCESS;
		 }
		 message="删除失败";
		 return SUCCESS;
	 }
}
