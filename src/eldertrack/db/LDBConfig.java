package eldertrack.db;

// THIS FILE IS UNDER .GITIGNORE AND WILL NOT CAUSE CONFLICTS.

public class LDBConfig {
	static final boolean useLocal = true;
	static final String hostname = "127.0.0.1";
	static final String port = "3306";
	static final String schema = "eldertrack";
	static final String dbuser = "eldertrackadmin";
	static final String dbpw = "eldertrack4321";
	static final String url = "jdbc:mysql://" + hostname + ":" + port + "/" + schema;
}
