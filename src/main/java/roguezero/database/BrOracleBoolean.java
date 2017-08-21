package roguezero.database;

public enum BrOracleBoolean {
    S, N;

    public static BrOracleBoolean oracleBoolean(boolean valor) {
        return valor ? S : N;
    }
}
