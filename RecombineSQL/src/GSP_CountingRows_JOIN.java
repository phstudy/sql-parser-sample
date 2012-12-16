import gudusoft.gsqlparser.EDbVendor;
import gudusoft.gsqlparser.ESqlStatementType;
import gudusoft.gsqlparser.TCustomSqlStatement;
import gudusoft.gsqlparser.TGSqlParser;
import gudusoft.gsqlparser.nodes.TOrderBy;
import gudusoft.gsqlparser.nodes.TResultColumnList;
import gudusoft.gsqlparser.nodes.TTableList;
import gudusoft.gsqlparser.nodes.TWhereClause;
import gudusoft.gsqlparser.stmt.TSelectSqlStatement;

public class GSP_CountingRows_JOIN {

	public static void main(String[] args) {
		String queryString = "SELECT col FROM tbl,  WHERE col = 1";
		// target: SELECT count(*) FROM tbl WHERE col = 1
		
		TGSqlParser sqlparser = new TGSqlParser(EDbVendor.dbvmssql);
		sqlparser.sqltext = queryString;		
		int ret = sqlparser.parse();
		if (ret == 0) {
			for (int i = 0; i < sqlparser.sqlstatements.size(); i++) {
				TCustomSqlStatement st = sqlparser.sqlstatements.get(i);
				 
				if(st.sqlstatementtype == ESqlStatementType.sstselect) {
					TSelectSqlStatement sst = (TSelectSqlStatement)st;
					TResultColumnList select = sst.getResultColumnList();
					select.setString("count(*)");
					
					System.out.println(sst.toString());
				}
			}
		}
	}

}
