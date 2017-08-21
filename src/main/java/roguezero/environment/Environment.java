package roguezero.environment;

import org.apache.commons.lang3.StringUtils;

public class Environment {

    private Environment() { }

    public static boolean isProduction() {
        return StringUtils.equals(System.getProperty("TIPO_AMBIENTE"), "producao");
    }

    public static String testEmail() {
        return System.getProperty("EMAIL_TESTE");
    }

}
