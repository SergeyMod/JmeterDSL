package ru.helpdesk.helpers;

import java.io.IOException;
import java.util.Properties;

public class PropertiesHelper extends PropertyHelper {
    static public Properties readAdminLoginProperties() throws IOException {
        String path = "common/admin_login.properties";
        Properties properties = new Properties();
        Properties fileProperties = readFileProperties(path);
        properties.putAll(readCommonProperties());

        setProperty("ADMIN_LOGIN", "admin", fileProperties, properties);
        setProperty("ADMIN_PASS", "admin", fileProperties, properties);

        setProperty("THROUGHPUT", "45", fileProperties, properties);

        return properties;
    }
}
