package Entity;

public class Voucher_Detail implements java.io.Serializable {
	private int id;
	private Voucher voucher;
    private String item;
    private Double account;
    private String desc;
    
	    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Voucher getVoucher() {
		return voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}

	public String getItem() {
		return item;
	}
	
	public void setItem(String item) {
		this.item = item;
	}
	
	public Double getAccount() {
		return account;
	}
	
	public void setAccount(Double account) {
		this.account = account;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
    
}

