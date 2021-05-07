package org.juejin.examples.validation;

import org.juejin.examples.BaseTest;
import org.juejin.examples.validation.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
*   
* @author : KangNing Hu
*/
public class MethodValidationTest  extends BaseTest {


	@Autowired
	private UserService userService;

	/**
	 * 校验返回值
	 */
	@Test
	public  void checkReturn() {
		userService.getUser();
	}


	@Test
	public void checkParam(){
		userService.add("111");
	}
}
