SQL-Parser-sample
=================
General SQL Parser (GSP)
http://www.sqlparser.com/

JavaCC
http://java.net/projects/javacc/


There are fours eclipse projects
1. SplitSQL (GSP Library is required)
2. PlSQL
3. RecombineSQL (GSP Library is required)
4. jpa (Elcipse Maven Plugin & GSP are required)
   - CustomSqlServerDialect.java is alternative implementation via GSP for Hibernate SqlServer2005Dialect.java,
     it can generate correct pagination sql when column name includes 'fromFOO' and 'BARfrom'.