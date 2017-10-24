package cl.intelliware.smartlab;

import cl.intelliware.smartlab.models.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmartlabApplicationTests {

	@Test
	public void userCheckMailReturnTrue() {
		User user = new User();
		user.setMail("juan.jote@usach.cl");
		Assert.assertTrue("User mail  check OK!", user.mailCheck());
	}

	@Test
	public void userCheckRutReturnTrue() {
		User user = new User();
		user.setRut("19.522.760-8");
		Assert.assertTrue(  "User rut check", user.rutCheck());
	}


}


