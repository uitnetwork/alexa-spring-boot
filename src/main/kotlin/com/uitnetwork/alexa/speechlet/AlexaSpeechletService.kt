package com.uitnetwork.alexa.speechlet

import com.amazon.speech.json.SpeechletRequestEnvelope
import com.amazon.speech.speechlet.*
import com.uitnetwork.alexa.service.AlexaIntentRequestManager
import com.uitnetwork.alexa.service.IntentRequestTranslatorService
import com.uitnetwork.alexa.service.IntroductionService
import com.uitnetwork.alexa.service.TimeIntentProcessorService
import org.slf4j.LoggerFactory.getLogger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AlexaSpeechletService : SpeechletV2 {
    private val logger = getLogger(TimeIntentProcessorService::class.java)

    @Autowired
    private lateinit var introductionService: IntroductionService

    @Autowired
    private lateinit var intentRequestTranslatorService: IntentRequestTranslatorService

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

        val alexaIntentRequest = intentRequestTranslatorService.translate(requestEnvelope)
        return alexaIntentRequestManager.process(alexaIntentRequest)
    }

    override fun onSessionEnded(requestEnvelope: SpeechletRequestEnvelope<SessionEndedRequest>) {
        logger.info("onSessionEnded requestId={}, sessionId={}", requestEnvelope.request.requestId, requestEnvelope.session.sessionId)
    }
}
