package roguezero.environment;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Version {

    public String getCurrentVersion() {
        String versao = "Development Version";
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("project.properties");

        if (resourceAsStream != null) {
            Properties prop = new Properties();
            try {
                prop.load(resourceAsStream);
            } catch (IOException e) {
                Logger.getAnonymousLogger().log(Level.OFF, e.getMessage(), e);
            }

            if (prop.get("version") != null) {
                versao = prop.get("version").toString() + " " + prop.get("timestamp").toString() + "UTC";
            }
        }

        return versao;
    }
}