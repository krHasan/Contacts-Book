package databaseAccess;

import org.junit.Test;

public class SystemAccessTest {

	@Test
	public void dbConnectionTest() {
		SystemAccess access = new SystemAccess();
		System.out.println(access.hasdb());
	}

}
