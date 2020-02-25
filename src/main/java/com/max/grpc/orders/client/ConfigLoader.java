
package com.max.grpc.orders.client;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private final Logger logger = Logger.getLogger(ConfigLoader.class);
    private final String filepath;
    private Properties props;

    public ConfigLoader(String filepath) {
        this.filepath = filepath;
    }

    public void load() {
        try (var fileStream = new FileInputStream(filepath)) {
            props = new Properties();
            props.load(fileStream);
        }
        catch (FileNotFoundException ex) {
            var logString = String.format("File %s not found", filepath);
            logger.error(logString);
        }
        catch (IOException ex) {
            var logString = String.format("Failed to load %s : I/O Error", filepath);
            logger.error(logString);
        }
    }

    public int getGrpcServerPortOrDefault(int defaultPort) {
        return getPortOrDefault("server.grpc.port", defaultPort);
    }

    public int getRestServerPortOrDefault(int defaultPort) {
        return getPortOrDefault("server.rest.port", defaultPort);
    }

    private int getPortOrDefault(String configKey, int defaultPort) {
        if (props == null) {
            logger.error("Config not loaded, return default port instead");
            return defaultPort;
        }

        String fromConfig = props.getProperty(configKey);
        if (fromConfig == null) {
            logger.error("server.port variable not found, return default port instead");
            return defaultPort;
        }

        int result;
        try {
            result = Integer.parseInt(fromConfig);
        }
        catch (NumberFormatException ex) {
            logger.error("Illegal server port declared, return default instead");
            result = defaultPort;
        }
        return result;
    }
}
