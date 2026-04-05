package com.platzi_play.domain.service;


import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface PlatziPlayAiServices {


    @UserMessage("""
            Genera un saludo saludo de bienvenida a la plataforma de peliculas Platzi Play,
            Usa menos de 200 caracteres y hazlo al estilo de Platzi Play
            """)
    String generateGreeting();

    @SystemMessage("""
            Eres un experto en cine que recomienda películas personalizadas
             según los gustos del ususario.
             Debes recomendar máximo 3 películas.
             No incluyas películas que estén por fuera de la plataforma PlatziPlay""")
    String generateMovieRecommendation(@UserMessage String userMessage);
}
