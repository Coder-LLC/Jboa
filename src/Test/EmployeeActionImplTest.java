package Test;

import org.junit.Before;
import org.junit.Test;

import Entity.Department;

public class EmployeeActionImplTest {
    Department department;
	@Before
	public void setUp() throws Exception {
		department=new Department();
	}

	@Test
	public void testMiddleManegerQueryVoucher() {
		department.setId(1);
	}

}
