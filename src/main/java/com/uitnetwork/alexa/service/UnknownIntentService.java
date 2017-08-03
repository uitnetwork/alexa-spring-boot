package com.uitnetwork.alexa.service;

import static org.springframework.core.Ordered.LOWEST_PRECEDENCE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.amazon.speech.speechlet.SpeechletResponse;
import com.uitnetwork.alexa.model.AlexaIntentRequest;

@Order(LOWEST_PRECEDENCE)
@Service
public class UnknownIntentService extends AlexaIntentRequestService {
    public static final String UNKNOWN_MESSAGE = "I don't understand what you said. Please say again.";
    public static final String UNKNOWN_REPROMPT = "Please say again.";


    @Autowired
    private SpeechletResponseService speechletResponseService;

    @Override
    public SpeechletResponse doProcess(AlexaIntentRequest alexaIntentRequest) {
        return speechletResponseService.newAskResponse(UNKNOWN_MESSAGE, UNKNOWN_REPROMPT);
    }

    @Override
    public boolean canProcess(AlexaIntentRequest alexaIntentRequest) {
        return true;
    }
}
