package system;

import org.junit.Test;

import junit.framework.Assert;
import operation.Constraints;

@SuppressWarnings("deprecation")
public class ConstraintsTest {
	Constraints access = new Constraints();

	@SuppressWarnings("deprecation")
	@Test
	public void testWhiteSpace() {
		String text = "This Is Text.#";
		
		boolean actual = access.isThereWhiteSpace(text);
		Assert.assertEquals(true, actual);
		
	}
	
	@Test
	public void testNowhiteSpace() {
		String text = "thereisnospace@#$";
		boolean actual = access.isThereWhiteSpace(text);
		Assert.assertEquals(false, actual);
	}

}
