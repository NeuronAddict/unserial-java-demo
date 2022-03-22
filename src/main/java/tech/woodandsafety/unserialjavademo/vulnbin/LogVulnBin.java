package tech.woodandsafety.unserialjavademo.vulnbin;

import java.io.*;

/**
 * Use this only with safe code, as CISO say.
 */
public class LogVulnBin implements Serializable {

    public LogVulnBin(String command) {
        org.apache.logging.log4j.LogManager.getLogger().info(String.format("Execute command : %s", command));
    }
}
