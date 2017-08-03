package com.uitnetwork.alexa.config

import com.amazon.speech.speechlet.servlet.SpeechletServlet
import com.uitnetwork.alexa.speechlet.AlexaSpeechlet
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AlexaConfig {
    companion object {
        const val ALEXA_URL = "/alexa"
    }

    @Autowired
    private lateinit var alexaSpeechlet: AlexaSpeechlet

    @Bean
    fun registerServlet(): ServletRegistrationBean {
        val speechletServlet = SpeechletServlet()
        speechletServlet.setSpeechlet(alexaSpeechlet)

        val servletRegistrationBean = ServletRegistrationBean(speechletServlet, ALEXA_URL)
        return servletRegistrationBean
    }
}
