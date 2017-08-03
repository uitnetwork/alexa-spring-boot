package com.uitnetwork.alexa.service;

import org.springframework.core.annotation.Order;

import com.amazon.speech.speechlet.SpeechletResponse;
import com.uitnetwork.alexa.model.AlexaIntentRequest;

@Order(1)
public abstract class AlexaIntentRequestService {

    public abstract SpeechletResponse doProcess(AlexaIntentRequest alexaIntentRequest);

    public abstract boolean canProcess(AlexaIntentRequest alexaIntentRequest);
}
