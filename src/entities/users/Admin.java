package entities.users;

import contracts.Administrator;

public class Admin extends UserImpl implements Administrator {
	
	public Admin(String name, String password, String email) {
		super(name, password, email);
	}

}
