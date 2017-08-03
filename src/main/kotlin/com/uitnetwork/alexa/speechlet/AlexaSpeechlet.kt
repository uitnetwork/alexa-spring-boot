package com.uitnetwork.alexa.speechlet

import com.amazon.speech.json.SpeechletRequestEnvelope
import com.amazon.speech.speechlet.*
import com.uitnetwork.alexa.service.SpeechletService
import org.slf4j.LoggerFactory.getLogger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AlexaSpeechlet : SpeechletV2 {
    private val logger = getLogger(AlexaSpeechlet::class.java)

    @Autowired
    private lateinit var speechletService: SpeechletService

    override fun onSessionStarted(requestEnvelope: SpeechletRequestEnvelope<SessionStartedRequest>) {
        logger.info("Delegating to SpeechletService: {}", speechletService)

        speechletService.onSessionStarted(requestEnvelope)
    }

    override fun onLaunch(requestEnvelope: SpeechletRequestEnvelope<LaunchRequest>): SpeechletResponse {
        return speechletService.onLaunch(requestEnvelope)
    }

    override fun onIntent(requestEnvelope: SpeechletRequestEnvelope<IntentRequest>): SpeechletResponse {
        return speechletService.onIntent(requestEnvelope)
    }

    override fun onSessionEnded(requestEnvelope: SpeechletRequestEnvelope<SessionEndedRequest>) {
        speechletService.onSessionEnded(requestEnvelope)
    }
}
