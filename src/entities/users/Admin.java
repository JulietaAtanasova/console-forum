package entities.users;

import contracts.Administrator;

public class Admin extends UserImpl implements Administrator {
	
	public Admin(int id, String name, String password, String email) {
		super(id, name, password, email);
	}

}
