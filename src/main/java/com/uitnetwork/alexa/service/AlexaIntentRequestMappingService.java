package com.uitnetwork.alexa.service;

import static com.uitnetwork.alexa.model.AlexaIntent.lookupAlexaIntent;
import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.IntentRequest;
import com.uitnetwork.alexa.model.AlexaIntent;
import com.uitnetwork.alexa.model.AlexaIntentRequest;

@Service
public class AlexaIntentRequestMappingService {
    private static final Logger logger = getLogger(TimeIntentService.class);

    public AlexaIntentRequest translate(SpeechletRequestEnvelope<IntentRequest> intentRequestEnvelope) {
        logger.info("Mapping for intentName: {}", intentRequestEnvelope.getRequest().getIntent().getName());

        AlexaIntent alexaIntent = lookupAlexaIntent(intentRequestEnvelope.getRequest().getIntent().getName());
        return new AlexaIntentRequest(alexaIntent, intentRequestEnvelope);
    }
}
