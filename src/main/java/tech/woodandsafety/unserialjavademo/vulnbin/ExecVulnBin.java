package tech.woodandsafety.unserialjavademo.vulnbin;

import java.io.IOException;
import java.io.Serializable;

public class ExecVulnBin implements Serializable {

    private final String command;

    public ExecVulnBin(String command) {
        this.command = command;
        System.out.println("try execute command " + command);
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
