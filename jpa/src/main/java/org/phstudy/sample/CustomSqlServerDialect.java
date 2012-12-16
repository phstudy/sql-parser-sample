package org.phstudy.sample;

import org.hibernate.dialect.SQLServer2005Dialect;

import gudusoft.gsqlparser.EDbVendor;
import gudusoft.gsqlparser.ESqlStatementType;
import gudusoft.gsqlparser.TCustomSqlStatement;
import gudusoft.gsqlparser.TGSqlParser;
import gudusoft.gsqlparser.nodes.TOrderBy;
import gudusoft.gsqlparser.nodes.TResultColumnList;
import gudusoft.gsqlparser.nodes.TTableList;
import gudusoft.gsqlparser.nodes.TWhereClause;
import gudusoft.gsqlparser.stmt.TSelectSqlStatement;


public class CustomSqlServerDialect extends SQLServer2005Dialect {

	@Override
	public String getLimitString(String querySqlString, boolean hasOffset) {
		StringBuilder sb = new StringBuilder();
	
		TGSqlParser sqlparser = new TGSqlParser(EDbVendor.dbvmssql);
		sqlparser.sqltext = querySqlString;		
		int ret = sqlparser.parse();
		if (ret == 0) {
			for (int i = 0; i < sqlparser.sqlstatements.size(); i++) {
				TCustomSqlStatement st = sqlparser.sqlstatements.get(i);
				 
				if(st.sqlstatementtype == ESqlStatementType.sstselect) {
					TSelectSqlStatement sst = (TSelectSqlStatement)st;
					TOrderBy orderbyClause = sst.getOrderbyClause();
					TResultColumnList select = sst.getResultColumnList();
					TTableList table = sst.tables;
					TWhereClause where = sst.getWhereClause();
					
					String orderby = "ORDER BY CURRENT_TIMESTAMP";
					if(sst.getOrderbyClause() != null)
						orderby = orderbyClause.toString();
					
					sb.append("WITH query AS (select ");
					sb.append(select.toString());
					
					sb.append(", ROW_NUMBER() OVER (");
					sb.append(orderby);
					
					sb.append(") as __hibernate_row_nr__");
					sb.append(table.size() > 0 ? " from " + table.toString() : "");
					if(where != null)
						sb.append(" ").append(where.toString());
					
					sb.append(") SELECT * FROM query ");
					sb.append("WHERE __hibernate_row_nr__ >= ? AND __hibernate_row_nr__ < ?");
				}
			}
		}
		return sb.toString();
	}
}
