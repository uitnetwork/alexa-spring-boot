package com.uitnetwork.alexa.service

import com.amazon.speech.json.SpeechletRequestEnvelope
import com.amazon.speech.speechlet.LaunchRequest
import com.amazon.speech.speechlet.SpeechletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class IntroductionService {
    companion object {
        val INTRODUCTION_MESSAGE = "Welcome to the Alexa Skills Kit, you can start by saying your name"
        val INTRODUCTION_REPROMPT = "Tell me your name!"
    }

    @Autowired
    private lateinit var speechletResponseService: SpeechletResponseService

    fun createIntrodutionSpeechletResponse(launchRequestEnvelope: SpeechletRequestEnvelope<LaunchRequest>): SpeechletResponse {
        return speechletResponseService.newAskResponse(INTRODUCTION_MESSAGE, INTRODUCTION_REPROMPT)
    }
}
