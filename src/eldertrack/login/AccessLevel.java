package eldertrack.login;

public enum AccessLevel {
		NOACCESS(0), 	// Not allowed to login DB Level: 0
		STAFF	(1), 		// Use features only for checking DB Level: 1
		SRSTAFF	(2), 	// Allowed to edit Diet and Med DB Level: 2
		ADMIN	(3), 		// Allowed to edit elderly profiles on Management DB Level: 3
		MANAGER	(4); 	// Allowed to edit staff profiles on Management DB Level: 4

	private final int level;
	
	AccessLevel(int level) {
		this.level = level;
	}
	
	int getValue() {
		return level;
	}

}

