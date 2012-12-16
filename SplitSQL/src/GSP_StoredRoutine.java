import gudusoft.gsqlparser.EDbVendor;
import gudusoft.gsqlparser.ESqlStatementType;
import gudusoft.gsqlparser.TCustomSqlStatement;
import gudusoft.gsqlparser.TGSqlParser;
import gudusoft.gsqlparser.nodes.TParameterDeclaration;
import gudusoft.gsqlparser.stmt.mysql.TMySQLCreateFunction;

public class GSP_StoredRoutine {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TGSqlParser sqlparser = new TGSqlParser(EDbVendor.dbvmysql);
		sqlparser.sqltext = "CREATE FUNCTION `add`(`arg0` double, `arg1` double)" +
				            "    RETURNS double" + 
				            " BEGIN " +
				            "    RETURN arg0 + arg1;" +
				            " END;";
		
		int ret = sqlparser.parse();
		if (ret == 0) {
			for (int i = 0; i < sqlparser.sqlstatements.size(); i++) {
				TCustomSqlStatement st = sqlparser.sqlstatements.get(i);
				if (st.sqlstatementtype == ESqlStatementType.sstmysqlcreatefunction) {
					TMySQLCreateFunction create_st = (TMySQLCreateFunction) st;
					for (int j = 0; j < create_st.getParameterDeclarations().size(); j++) {
						TParameterDeclaration param = create_st.getParameterDeclarations().getParameterDeclarationItem(j);
						System.out.println("Data Type: " + param.getDataType());
						System.out.println("Default Value: " + param.getDefaultValue());
						System.out.println("Parameter Name: " + param.getParameterName());
						System.out.println();
					}
				}
			}
		}
	}

}
