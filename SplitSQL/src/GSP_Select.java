import gudusoft.gsqlparser.EDbVendor;
import gudusoft.gsqlparser.ESqlStatementType;
import gudusoft.gsqlparser.TCustomSqlStatement;
import gudusoft.gsqlparser.TGSqlParser;
import gudusoft.gsqlparser.nodes.TResultColumnList;
import gudusoft.gsqlparser.nodes.TTableList;
import gudusoft.gsqlparser.nodes.TWhereClause;
import gudusoft.gsqlparser.stmt.TSelectSqlStatement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GSP_Select {

	public static void main(String[] args) throws IOException {

		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	    String sql = bufferRead.readLine();
	    
		TGSqlParser sqlparser = new TGSqlParser(EDbVendor.dbvmysql);
		sqlparser.sqltext = sql;		
		int ret = sqlparser.parse();
		if (ret == 0) {
			for (int i = 0; i < sqlparser.sqlstatements.size(); i++) {
				TCustomSqlStatement st = sqlparser.sqlstatements.get(i);
				 
				if(st.sqlstatementtype == ESqlStatementType.sstselect) {
					TSelectSqlStatement sst = (TSelectSqlStatement)st;
					TResultColumnList select = sst.getResultColumnList();
					TTableList table = sst.tables;
					TWhereClause where= sst.getWhereClause();

					
					// get statement table count
//					System.out.println("table count: " + table.size());
					
					// list all select columns
//					for(int j = 0; j < select.size(); j++) {
//						System.out.println(select.getResultColumn(j).getExpr().toString());
//					}
					
					// add select column
//					select.addResultColumn("newCol");
//					System.out.println(sst.toString());
					
					// remove select column
//					select.removeResultColumn(1);
//					System.out.println(sst.toString());
					
					// set operations
//					System.out.println(sst.isCombinedQuery());
//					sst.getLeftStmt();
//					sst.getRightStmt();
					
					// add where clause
//					sst.addWhereClause("a = b");
//					System.out.println(sst.toString());
					
					// add constraint to where clause
//					where.getCondition().addANDCondition("a = b");
//					System.out.println(sst.toString());
					
				}
			}
		} else {
			System.out.println(sqlparser.getErrormessage());
		}
	}

}
