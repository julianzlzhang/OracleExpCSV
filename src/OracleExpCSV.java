import java.io.*;
import java.sql.*;

public class OracleExpCSV {

	/*
	 * args[0] hostname:port/instance
	 * args[1] user
	 * args[2] password
	 * args[3] tablename
	 * args[4] filename with path
	 */
	public static void main(String[] args)  throws Exception{
		if(args.length != 5){
			throw new RuntimeException("The Input Parameters are wrong!\n"
					+ "The usage:\n "
					+ "java -jar OracleExpCSV.jar hostname:port/instance user password tablename filename");
		}
			
		String url = "jdbc:oracle:thin:@//" + args[0];
		String user = args[1];
		String password = args[2];
		String query = "select * from " + args[3];
		String fileName = args[4];
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url, user, password);
		conn.setReadOnly(true);
		Statement stmt = conn.createStatement();
		stmt.setFetchSize(10000);
		ResultSet rset = stmt.executeQuery(query);
		ResultSetMetaData metaData = rset.getMetaData();
		MetaDataManager manager = new MetaDataManager(metaData);
		int count=0;
		long beginTime = System.currentTimeMillis();
		
		StringBuilder buffer = new StringBuilder(1000000);
		while (rset.next()) {
			count++;
			manager.appendOneRow(buffer, rset);
			if(count%100 == 0)
			{
				writer.append(buffer);
				buffer.setLength(0);
			}
		}
		writer.append(buffer);
		buffer.setLength(0);
		
		rset.close();
		stmt.close();
		conn.close();
		
		writer.flush();
		writer.close();
		
		long endTime = System.currentTimeMillis();
		long elapsedTime = (endTime - beginTime)/1000;
		System.out.println("Table " + args[3] + " exported " + count + " rows! "
				+ "The elapsed time is " + elapsedTime + " seconds! "
						+ "In average " + count/elapsedTime + " rows/second!");
	}
}
