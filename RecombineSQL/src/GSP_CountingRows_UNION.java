import gudusoft.gsqlparser.EDbVendor;
import gudusoft.gsqlparser.ESqlStatementType;
import gudusoft.gsqlparser.TCustomSqlStatement;
import gudusoft.gsqlparser.TGSqlParser;
import gudusoft.gsqlparser.nodes.TOrderBy;
import gudusoft.gsqlparser.nodes.TResultColumnList;
import gudusoft.gsqlparser.nodes.TTableList;
import gudusoft.gsqlparser.nodes.TWhereClause;
import gudusoft.gsqlparser.stmt.TSelectSqlStatement;

public class GSP_CountingRows_UNION {

	public static void main(String[] args) {
		String queryString = "SELECT col FROM tbl UNION SELECT col FROM tbl";
		// target: SELECT count(*) FROM (SELECT col FROM tbl UNION SELECT col FROM tbl2) as tmp;
		
		TGSqlParser sqlparser = new TGSqlParser(EDbVendor.dbvmssql);
		sqlparser.sqltext = queryString;		
		int ret = sqlparser.parse();
		if (ret == 0) {
			for (int i = 0; i < sqlparser.sqlstatements.size(); i++) {
				TCustomSqlStatement st = sqlparser.sqlstatements.get(i);
				 
				if(st.sqlstatementtype == ESqlStatementType.sstselect) {
					TSelectSqlStatement sst = (TSelectSqlStatement)st;
					StringBuilder sb = new StringBuilder();
					sb.append("SELECT count(*) FROM (");
					sb.append(sst.toString());
					sb.append(") as tmp");
					
					System.out.println(sb.toString());
				}
			}
		}
	}

}
