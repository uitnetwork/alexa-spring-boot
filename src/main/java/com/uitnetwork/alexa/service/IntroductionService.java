package com.uitnetwork.alexa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.SpeechletResponse;

@Service
public class IntroductionService {
    public static final String INTRODUCTION_MESSAGE = "Welcome to the Alexa Skills Kit, you can start by saying your name";
    public static final String INTRODUCTION_REPROMPT = "Tell me your name!";

    @Autowired
    private SpeechletResponseService speechletResponseService;

    public SpeechletResponse createIntrodutionSpeechletResponse(SpeechletRequestEnvelope<LaunchRequest> launchRequestEnvelope) {
        return speechletResponseService.newAskResponse(INTRODUCTION_MESSAGE, INTRODUCTION_REPROMPT);
    }
}
