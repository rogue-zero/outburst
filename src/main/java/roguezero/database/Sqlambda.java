package roguezero.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Sqlambda {

    private Sqlambda() { }

    public static String sql(String... args) {
        return String.join(" ", args);
    }

    public static String as(String column, String alias) {
        return String.format("%s as %s", column, alias);
    }

    public static String as(Map.Entry<String, String> entry) {
        return as(entry.getKey(), entry.getValue());
    }

    public static String select(String... columns) {
        return String.format("select %s", String.join(", ", columns));
    }

    public static String select(Map<String, String> aliasedColunns) {
        return select(
                aliasedColunns.entrySet().stream().map(
                        Sqlambda::as
                ).toArray(
                        String[]::new
                )
        );
    }

    public static String from(String table) {
        return String.format("from %s", table);
    }

    public static String where(String clause) {
        return String.format("where %s", clause);
    }

    public static String insertInto(String table, String... columns) {
        return columns != null && columns.length > 0
                ? String.format("insert into %s (%s)", table, String.join(", ", columns))
                : String.format("insert into %s", table);
    }

    public static String values(String... values) {
        return String.format("values (%s)", String.join(", ", values));
    }

    public static String update(String table) {
        return String.format("update %s", table);
    }

    public static String set(Map<String, String> setters) {
        List<String> set = setters.entrySet().stream().map(
                entry -> String.format(
                        "%s = %s",
                        entry.getKey(),
                        entry.getValue()
                )
        ).collect(Collectors.toList());
        return String.format("set %s", String.join(", ", set));
    }

    public static String mergeInto(String table, Map<String, String> setters, String postClause) {
        return postClause != null
                ? sql(update(table), set(setters), postClause)
                : sql(
                insertInto(
                        table,
                        setters.keySet().toArray(new String[setters.keySet().size()])
                ),
                values(
                        setters.values().toArray(new String[setters.values().size()])
                )
        );
    }

    public static String mergeInto(String table, Map<String, String> setters) {
        return mergeInto(table, setters, null);
    }

    public static String mergeInto(String table, String[] columns, String postClause) {
        Map<String, String> setters = new ArrayList<>(Arrays.asList(columns))
                .stream()
                .collect(
                        Collectors.toMap(column -> column, column -> String.format(":%s", column))
                );
        return mergeInto(table, setters, postClause);
    }

    public static String mergeInto(String table, String[] columns) {
        return mergeInto(table, columns, null);
    }

}
