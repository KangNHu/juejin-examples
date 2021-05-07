package org.juejin.examples.validation.service;


import javax.validation.constraints.NotNull;

/**
*   
* @author : KangNing Hu
*/
public interface UserService {


	String getUser();

	void add(@NotNull String userId);

}
