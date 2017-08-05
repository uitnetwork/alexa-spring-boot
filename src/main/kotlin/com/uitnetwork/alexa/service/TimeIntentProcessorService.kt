package com.uitnetwork.alexa.service

import com.amazon.speech.speechlet.SpeechletResponse
import com.uitnetwork.alexa.model.Intent.TIME_INTENT
import com.uitnetwork.alexa.model.IntentRequest
import com.uitnetwork.alexa.util.AppConstants.Companion.SESSION_NAME
import org.slf4j.LoggerFactory.getLogger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalTime.now
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Service
class TimeIntentProcessorService : IntentRequestProcessorService {
    private val logger = getLogger(TimeIntentProcessorService::class.java)

    companion object {
        private val SINGAPORE_ZONE_ID = ZoneId.of("Asia/Singapore")
        private val TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm")

        val TIME_MESSAGE = "It's <say-as interpret-as=\"time\">%s</say-as> in Singapore, %s"
    }

    @Autowired
    private lateinit var speechletResponseService: SpeechletResponseService

    override fun doProcess(intentRequest: IntentRequest): SpeechletResponse {
        val sessionName = intentRequest.originalRequestEnvelope.session.getAttribute(SESSION_NAME)
        logger.info("SessionName: {}", sessionName)
        val name = sessionName as String

        val currentTime = now(SINGAPORE_ZONE_ID)

        val ssmlMessage = String.format(TIME_MESSAGE, currentTime.format(TIME_FORMATTER), name)
        logger.info("SSMLMessage: {}", ssmlMessage)

        return speechletResponseService.newTellResponse(ssmlMessage)
    }

    override fun canProcess(intentRequest: IntentRequest): Boolean {
        return TIME_INTENT == intentRequest.intent
    }
}
