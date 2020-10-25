package com.spj.authorization.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
@RequiredArgsConstructor
public class EnvironmentConfig {

    private final Environment environment;

    private String port;
    private String hostname;

    /**
     * Get port.
     *
     * @return
     */
    public String getPort() {
        if (port == null) port = System.getenv("local.server.port");
        return port;
    }

    /**
     * Get port, as Integer.
     *
     * @return
     */
    public Integer getPortAsInt() {
        return Integer.valueOf(getPort());
    }

    /**
     * Get hostname.
     *
     * @return
     */
    public String getHostname() throws UnknownHostException {
        // TODO ... would this cache cause issue, when network env change ???
        if (hostname == null) hostname = InetAddress.getLocalHost().getHostAddress();
        return hostname;
    }

    public String getServerUrlPrefi() throws UnknownHostException {
        return "http://" + getHostname() + ":" + getPort();
    }

    /**
     * @return the environment
     */
    public Environment getEnvironment() {
        return environment;
    }

    /**
     * @return the datasourceUsername
     */
    public String getDatasourceUsername() {
        return System.getenv("DATA_SOURCE_USERNAME");
    }

    /**
     * @return the datasourcePassword
     */
    public String getDatasourcePassword() {
        return System.getenv("DATA_SOURCE_PASSWORD");
    }

    /**
     * @return the jwtSigningKey
     */
    public String getJwtSigningKey() {
        return System.getenv("JWT_SIGNING_KEY");
    }
}
