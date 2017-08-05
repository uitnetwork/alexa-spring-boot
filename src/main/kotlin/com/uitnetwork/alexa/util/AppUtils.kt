package com.uitnetwork.alexa.util

import com.uitnetwork.alexa.service.TimeIntentService
import com.uitnetwork.alexa.util.AppConstants.Companion.ENV_VARIABLE_SUPPORTED_APP_IDS
import com.uitnetwork.alexa.util.AppConstants.Companion.SUPPORTED_APP_ID_SEPERATOR
import org.slf4j.LoggerFactory.getLogger
import java.lang.System.getenv
import java.util.*
import java.util.Arrays.asList

object AppUtils {
    private val logger = getLogger(TimeIntentService::class.java)


    val supportedAppIds: Set<String>
        get() {
            val supportedAppIdsString = getenv(ENV_VARIABLE_SUPPORTED_APP_IDS)

            val supportedAppIdArray = supportedAppIdsString.split(SUPPORTED_APP_ID_SEPERATOR.toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
            val supportedAppIds = HashSet<String>(asList<String>(*supportedAppIdArray))

            logger.info("Supported App Ids are: {}", supportedAppIdsString)
            return supportedAppIds
        }
}
