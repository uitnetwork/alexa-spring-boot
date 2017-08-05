package com.uitnetwork.alexa.service

import com.amazon.speech.json.SpeechletRequestEnvelope
import com.amazon.speech.speechlet.*
import org.slf4j.LoggerFactory.getLogger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SpeechletService : SpeechletV2 {
    private val logger = getLogger(TimeIntentService::class.java)

    @Autowired
    private lateinit var introductionService: IntroductionService

    @Autowired
    private lateinit var alexaIntentRequestMappingService: AlexaIntentRequestMappingService

    @Autowired
    private lateinit var alexaIntentRequestManager: AlexaIntentRequestManager

    override fun onSessionStarted(requestEnvelope: SpeechletRequestEnvelope<SessionStartedRequest>) {
        logger.info("onSessionStarted requestId={}, sessionId={}", requestEnvelope.request.requestId, requestEnvelope.session.sessionId)
    }

    override fun onLaunch(requestEnvelope: SpeechletRequestEnvelope<LaunchRequest>): SpeechletResponse {
        logger.info("onLaunch requestId={}, sessionId={}", requestEnvelope.request.requestId, requestEnvelope.session.sessionId)
        return introductionService.createIntrodutionSpeechletResponse(requestEnvelope)
    }

    override fun onIntent(requestEnvelope: SpeechletRequestEnvelope<IntentRequest>): SpeechletResponse {
        logger.info("onIntent requestId={}, sessionId={}", requestEnvelope.request.requestId, requestEnvelope.session.sessionId)

        val alexaIntentRequest = alexaIntentRequestMappingService.translate(requestEnvelope)
        return alexaIntentRequestManager.process(alexaIntentRequest)
    }

    override fun onSessionEnded(requestEnvelope: SpeechletRequestEnvelope<SessionEndedRequest>) {
        logger.info("onSessionEnded requestId={}, sessionId={}", requestEnvelope.request.requestId, requestEnvelope.session.sessionId)
    }
}
