package utilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

public class DatabaseOperations {

	public static Connection conn = null;
	public Statement stmt = null;
	public ArrayList<Hashtable<String, String>> objResultSet = null;
	public Hashtable<String, String> rowdata = null;
	public int colCount;
	public int rowCount;

	public DatabaseOperations(Connection conn) {
		this.conn = conn;
	}

	public ResultSet performSelectOnDatabase(String selectStt) throws SQLException {
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(selectStt);
		return rs;
	}

	public ArrayList<Hashtable<String, String>> performSelectStatement(String selectStt) throws SQLException {
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery(selectStt);
		colCount = rs.getMetaData().getColumnCount();
//		rs.last();
//		rowCount = rs.getRow();
//		rs.beforeFirst();
		objResultSet = new ArrayList();

		ArrayList<String> headers = new ArrayList<String>();

		for (int i = 1; i <= colCount; i++) {
			headers.add(rs.getMetaData().getColumnName(i));
		}

//		int rowCounter = 0;

		while (rs.next()) {
			rowdata = new Hashtable<String, String>();
			for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {

				// Checking if the value is NULL in DB
				try {
					if (rs.getObject(i) == null) {
						rowdata.put(headers.get(i - 1), "");
						continue;
					}
				} catch (Exception e) {

				}

				try {
					rowdata.put(headers.get(i - 1), String.valueOf(rs.getInt(i)));
					continue;
				} catch (Exception e) {

				}

				try {
					rowdata.put(headers.get(i - 1), String.valueOf(rs.getLong(i)));
					continue;
				} catch (Exception e) {

				}

				try {
					rowdata.put(headers.get(i - 1), String.valueOf(rs.getString(i)));
					continue;
				} catch (Exception e) {

				}

				try {
					rowdata.put(headers.get(i - 1), String.valueOf(rs.getDate(i)));
					continue;
				} catch (Exception e) {

				}

				try {
					rowdata.put(headers.get(i - 1), String.valueOf(rs.getBoolean(i)));
					continue;
				} catch (Exception e) {

				}

				try {
					rowdata.put(headers.get(i - 1), String.valueOf(rs.getDouble(i)));
					continue;
				} catch (Exception e) {

				}

				try {
					rowdata.put(headers.get(i - 1), String.valueOf(rs.getFloat(i)));
					continue;
				} catch (Exception e) {

				}

				try {
					rowdata.put(headers.get(i - 1), String.valueOf(rs.getBigDecimal(i)));
					continue;
				} catch (Exception e) {

				}

				try {
					rowdata.put(headers.get(i - 1), String.valueOf(rs.getShort(i)));
					continue;
				} catch (Exception e) {

				}

				try {
					rowdata.put(headers.get(i - 1), String.valueOf(rs.getTime(i)));
					continue;
				} catch (Exception e) {

				}

				try {
					rowdata.put(headers.get(i - 1), String.valueOf(rs.getTimestamp(i)));
					continue;
				} catch (Exception e) {

				}

			}
			objResultSet.add(rowdata);
//			rowCounter++;
		}

		return objResultSet;
	}

	public ArrayList<String> getColSequence(String selectStt) throws SQLException {
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery(selectStt);
		colCount = rs.getMetaData().getColumnCount();

		ArrayList<String> headers = new ArrayList<String>();

		for (int i = 1; i <= colCount; i++) {
			headers.add(rs.getMetaData().getColumnName(i));
		}

		return headers;
	}

	public int performInsertUpdateDeleteStatement(String insertStt) throws SQLException {
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		int rowCount = stmt.executeUpdate(insertStt);
		return rowCount;
	}

}