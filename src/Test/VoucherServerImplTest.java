package Test;

import org.junit.Before;
import org.junit.Test;

import Entity.Voucher;

public class VoucherServerImplTest {
	Voucher voucher;
	@Before
	public void setUp() throws Exception {
		voucher=new Voucher();
	}

	@Test
	public void testDeleteVoucher() {
		voucher.setId(4);
	}

}
