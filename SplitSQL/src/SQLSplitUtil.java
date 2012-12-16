
public class SQLSplitUtil {

	private static final String SELECT = "select";
	private static final String FROM = "from";

	public String getSelectFields(String sql) {
		int selectStartIndex = sql.indexOf(SELECT);
		int selectEndIndex = sql.indexOf(FROM);

		String select = sql.substring(selectStartIndex + SELECT.length(),
				selectEndIndex);

		return select;
	}
}
