package com.enochmoses.sample.code.utils;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csi.mos.hibernate.ConfigRef;

/**
 * @author ENOCH MOSES
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ConfigUtil {
    private static Log log = LogFactory.getLog(ConfigUtil.class);

    /**
     * Directory where the report templates are stored.
     */
    private static final String DEFAULT_REPORT_LOCATION = ".";

    /**
     * Directory where the generated reports are stored.
     */
    private static final String DEFAULT_REPORT_TEMP = ".";

    /**
     * Number of days until a password must be changed.
     */
    private static final int DEFAULT_PASSWORD_EXPIRATION = 90;

    /**
     * Number of old passwords to store for comparison.
     */
    private static final int DEFAULT_PASSWORD_RECENT = 6;

    /**
     * Number of concurrent active sessions.
     */
    private static final int DEFAULT_LOGIN_SESSIONS = 3;

    /**
     * Number of consecutive failed logins.
     */
    private static final int DEFAULT_LOGIN_ATTEMPTS = 5;

    /**
     * Gets the location where the report templates need to be stored.
     * 
     * @return the location where the report templates need to be stored
     * @throws HibernateException
     */
    public static String getReportLocation(Session hibernateSession)
            throws HibernateException {
        ConfigRef locationConfig = ConfigRef.findByName(hibernateSession,
                "report.location");
        String location;
        if (locationConfig == null) {
            location = DEFAULT_REPORT_LOCATION;
            log
                    .error("Configuration parameter report.location missing from CONFIG_REF table. Using default: ["
                            + DEFAULT_REPORT_LOCATION + "]");
        } else {
            location = locationConfig.getParamValue();
        }

        return location;
    }

    /**
     * Gets the location where the generated reports are stored.
     * 
     * @return the location where the generated reports are stored
     * @throws HibernateException
     */
    public static String getReportTemp(Session hibernateSession)
            throws HibernateException {
        ConfigRef tempConfig = ConfigRef.findByName(hibernateSession,
                "report.temp");
        String temp;
        if (tempConfig == null) {
            temp = DEFAULT_REPORT_TEMP;
            log
                    .error("Configuration parameter report.temp missing from CONFIG_REF table. Using default: ["
                            + DEFAULT_REPORT_TEMP + "]");
        } else {
            temp = tempConfig.getParamValue();
        }

        return temp;
    }

   
    }

}