import gudusoft.gsqlparser.EDbVendor;
import gudusoft.gsqlparser.ESqlStatementType;
import gudusoft.gsqlparser.TCustomSqlStatement;
import gudusoft.gsqlparser.TGSqlParser;
import gudusoft.gsqlparser.nodes.TOrderBy;
import gudusoft.gsqlparser.nodes.TResultColumnList;
import gudusoft.gsqlparser.stmt.TSelectSqlStatement;

public class GSP_CountingRows {

	public static void main(String[] args) {
		String queryString = "SELECT col, col2 FROM tbl ORDER BY col, col2";
		// target: SELECT count(*) FROM tbl
		
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
					
					TOrderBy orderBy = sst.getOrderbyClause();
					if(orderBy != null) {
						for(int j = orderBy.getItems().size() - 1; j >= 0; j--)
							orderBy.removeOrderByItem(j);
					}
					
					System.out.println(sst.toString());
				}
			}
		}
	}

}
