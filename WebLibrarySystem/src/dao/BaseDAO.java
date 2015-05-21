package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class BaseDAO<T> {
	private Connection connection = null; 
	public BaseDAO(Connection conn) {
		this.connection = conn;
	}

	protected Connection getConnection() {
		return connection;
	}

	public void save(String query, Object[] vals) throws Exception {
		Connection con = getConnection();

		PreparedStatement stmt = con.prepareStatement(query);
		int count = 1;
		for (Object o : vals) {
			stmt.setObject(count, o);
			count++;
		}

		stmt.executeUpdate();
	}

	public int saveWithId(String query, Object[] vals) throws Exception {
		Connection con = getConnection();

		PreparedStatement stmt = con.prepareStatement(query,
				Statement.RETURN_GENERATED_KEYS);
		int count = 1;
		for (Object o : vals) {
			stmt.setObject(count, o);
			count++;
		}

		stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.next())
			return rs.getInt(1);
		else
			return -1;

	}

	public List<?> read(String query, Object[] vals) throws Exception {
		Connection conn = getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);

		if (vals != null) {
			int count = 1;
			for (Object o : vals) {
				stmt.setObject(count, o);
				count++;
			}
		}

		ResultSet rs = stmt.executeQuery();
		return extractData(rs);
	}

	protected abstract List<?> extractData(ResultSet rs) throws SQLException, ClassNotFoundException, Exception;

}
