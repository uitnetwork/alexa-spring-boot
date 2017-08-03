package com.uitnetwork.alexa.service;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.*;
import com.uitnetwork.alexa.model.AlexaIntentRequest;

@Service
public class SpeechletService implements SpeechletV2 {
    private static final Logger logger = getLogger(TimeIntentService.class);

    @Autowired
    private IntroductionService introductionService;

    @Autowired
    private AlexaIntentRequestMappingService alexaIntentRequestMappingService;

    @Autowired
    private AlexaIntentRequestManager alexaIntentRequestManager;

    @Override
    public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope) {
        logger.info("onSessionStarted requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(), requestEnvelope.getSession().getSessionId());
    }

    @Override
    public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> requestEnvelope) {
        logger.info("onLaunch requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(), requestEnvelope.getSession().getSessionId());
        return introductionService.createIntrodutionSpeechletResponse(requestEnvelope);
    }

    @Override
    public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        logger.info("onIntent requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(), requestEnvelope.getSession().getSessionId());

        AlexaIntentRequest alexaIntentRequest = alexaIntentRequestMappingService.translate(requestEnvelope);
        return alexaIntentRequestManager.process(alexaIntentRequest);
    }

    @Override
    public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> requestEnvelope) {
        logger.info("onSessionEnded requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(), requestEnvelope.getSession().getSessionId());
    }
}
