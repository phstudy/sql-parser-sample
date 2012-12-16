SQL-Parser-sample
=================
General SQL Parser (GSP)
http://www.sqlparser.com/

JavaCC
http://java.net/projects/javacc/


There are four eclipse projects:
* SplitSQL (GSP Library is required) 
* PlSQL
* RecombineSQL (GSP Library is required)
* jpa (Elcipse Maven Plugin & GSP are required)
   - CustomSqlServerDialect.java is alternative implementation via GSP for Hibernate SqlServer2005Dialect.java,
     it can generate correct pagination sql when column name includes 'fromFOO' and 'BARfrom'.
