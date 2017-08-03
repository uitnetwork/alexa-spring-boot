package com.uitnetwork.alexa.util;

import static com.uitnetwork.alexa.util.AppConstants.ENV_VARIABLE_SUPPORTED_APP_IDS;
import static com.uitnetwork.alexa.util.AppConstants.SUPPORTED_APP_ID_SEPERATOR;
import static java.lang.System.getenv;
import static java.util.Arrays.asList;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;

import com.uitnetwork.alexa.service.TimeIntentService;

public class AppUtils {
    private static final Logger logger = getLogger(TimeIntentService.class);


    public static final Set<String> getSupportedAppIds() {
        String supportedAppIdsString = getenv(ENV_VARIABLE_SUPPORTED_APP_IDS);

        String[] supportedAppIdArray = supportedAppIdsString.split(SUPPORTED_APP_ID_SEPERATOR);
        HashSet<String> supportedAppIds = new HashSet<>(asList(supportedAppIdArray));

        logger.info("Supported App Ids are: {}", supportedAppIdsString);
        return supportedAppIds;
    }
}
