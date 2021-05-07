package org.juejin.examples.validation.service.impl;

import org.juejin.examples.validation.service.UserService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
*   
* @author : KangNing Hu
*/
@Service
public class UserServiceImpl implements UserService {


	@Override
	public @NotNull String getUser() {
		return "";
	}

	@Override
	public void add(@NotNull String userId){

	}
}
