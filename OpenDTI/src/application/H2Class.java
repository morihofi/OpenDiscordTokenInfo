package application;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.util.StringUtils;

public class H2Class {
	
	public static String db_database = "jdbc:h2:~/tokendb";
	public static final String db_user = "dti";
	public static final String db_pass = "discordtokeninfo";
    
	public static String table = "TOKENS";
    
    
	

} 