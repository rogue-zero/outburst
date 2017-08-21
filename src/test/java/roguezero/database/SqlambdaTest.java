package roguezero.database;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static roguezero.database.Sqlambda.*;

public class SqlambdaTest {

    @Test
    public void sqlambdaShouldAbstractSqlQueries() {
        String generatedSql = sql(select("*"), from ("table"), where("1 = 1"));
        String expectedSql = "select * from table where 1 = 1";

        assertEquals(expectedSql, generatedSql);
    }

    @Test
    public void sqlambdaShouldAbstractSqlInserts() {
        String generatedSql = sql(insertInto("table", "column1", "column2"), values("value1", "value2"));
        String expectedSql = "insert into table (column1, column2) values (value1, value2)";

        assertEquals(expectedSql, generatedSql);
    }

    @Test
    public void sqlambdaShouldAbstractSqlUpdates() {
        Map<String, String> setters = new HashMap<>();
        setters.put("column1", "value1");
        setters.put("column2", "value2");

        String generatedSql = sql(
            update("table"),
            set(setters),
            where("1 = 1")
        );
        String expectedSql = "update table set column1 = value1, column2 = value2 where 1 = 1";

        assertEquals(expectedSql, generatedSql);
    }

    @Test
    public void sqlambdaShouldAbstractMergeOperations() {
        Map<String, String> columns = new HashMap<>();
        columns.put("column1", "value1");
        columns.put("column2", "value2");

        String generatedUpdateSql = mergeInto("table", columns, where("1 = 1"));
        String expectedUpdateSql = "update table set column1 = value1, column2 = value2 where 1 = 1";


        String generatedInsertSql = mergeInto("table", columns);
        String expectedInsertSql = "insert into table (column1, column2) values (value1, value2)";

        assertEquals(expectedUpdateSql, generatedUpdateSql);
        assertEquals(expectedInsertSql, generatedInsertSql);
    }

    @Test
    public void sqlambdaShouldAbstractMergeOperationsAndAutoCreateBindValuesForColumn() {
        String generatedUpdateSql = mergeInto("table", new String[]{ "column1", "column2" }, where("1 = 1"));
        String expectedUpdateSql = "update table set column1 = :column1, column2 = :column2 where 1 = 1";


        String generatedInsertSql = mergeInto("table", new String[]{ "column1", "column2" });
        String expectedInsertSql = "insert into table (column1, column2) values (:column1, :column2)";

        assertEquals(expectedUpdateSql, generatedUpdateSql);
        assertEquals(expectedInsertSql, generatedInsertSql);
    }

    @Test
    public void asBuilderShouldGenerateSimpleAliasStatements() {
        assertEquals("column1 as c1", as("column1", "c1"));
    }

    @Test
    public void selectBuilderShouldGenerateSimpleSelectStatements() {
        assertEquals("select *", select("*"));
    }

    @Test
    public void selectBuilderShouldGenerateAliasedStatements() {
        Map<String, String> columns = new HashMap<>();
        columns.put("column1", "c1");
        columns.put("column2", "c2");

        assertEquals("select column1 as c1, column2 as c2", select(columns));
    }

    @Test
    public void selectBuilderShouldGenerateMultipleColumnsSelectStatements() {
        assertEquals("select column1, column2", select("column1", "column2"));
    }

    @Test
    public void whereBuilderShouldGenerateSimpleWhereStatements() {
        assertEquals("where 1 = 1", where("1 = 1"));
    }

    @Test
    public void insertBuilderShouldGenerateSimpleInsertStatements() {
        assertEquals("insert into table", insertInto("table"));
    }

    @Test
    public void insertBuilderShouldAllowSpecficColumnNames() {
        assertEquals(
                "insert into table (column1, column2)",
                insertInto("table", "column1", "column2")
        );
    }

    @Test
    public void valuesBuilderShouldGenerateSimpleValuesStatements() {
        assertEquals(
                "values (value1, value2)",
                values("value1", "value2")
        );
    }

    @Test
    public void updateBuilderShouldGenerateSimpleUpdateStatements() {
        assertEquals(
                "update table",
                update("table")
        );
    }
}
