package com.uitnetwork.alexa.service;

import static com.uitnetwork.alexa.model.AlexaIntent.MY_NAME_INTENT;
import static com.uitnetwork.alexa.util.AppConstants.SESSION_NAME;
import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.uitnetwork.alexa.model.AlexaIntentRequest;


@Service
public class MyNameIntentService extends AlexaIntentRequestService {
    private static final Logger logger = getLogger(TimeIntentService.class);

    private static final String SLOT_MY_NAME = "MY_NAME";

    public static final String MY_NAME_MESSAGE = "Hello %s. What can I do for you?";
    public static final String MY_NAME_PROMPT = "What can I do for you, %s?";

    @Autowired
    private SpeechletResponseService speechletResponseService;

    @Override
    public SpeechletResponse doProcess(AlexaIntentRequest alexaIntentRequest) {
        Slot nameSlot = alexaIntentRequest.getIntentRequestEnvelope().getRequest().getIntent().getSlot(SLOT_MY_NAME);
        String name = nameSlot.getValue();

        String message = String.format(MY_NAME_MESSAGE, name);
        String repromptMessage = String.format(MY_NAME_PROMPT, name);

        alexaIntentRequest.getIntentRequestEnvelope().getSession().setAttribute(SESSION_NAME, name);

        logger.info("Message: {} and repromptMessage: {}", message, repromptMessage);

        return speechletResponseService.newAskResponse(message, repromptMessage);
    }

    @Override
    public boolean canProcess(AlexaIntentRequest alexaIntentRequest) {
        return MY_NAME_INTENT == alexaIntentRequest.alexaIntent;
    }
}
