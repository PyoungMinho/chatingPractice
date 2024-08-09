package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker //Stomp를 사용하기 위한 Ano
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat")
                .setAllowedOrigins("http://localhost:8090")
                .withSockJS();
    }

//    어플리케이션 내부에서 사용할 path지정

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/pub");
        registry.enableSimpleBroker("/sub");
        // setApplicationDestinationPrefixes -> Client에서 SEND요청을 처리(양방향)
        // enableSimpleBroker -> 해당 경로로 SimpleBrocker등록 -> SimpleBroker는 해당하는 경로를 SUBSCRIBE 하는 Client에게 메세지를 전달하는 간단한 작업을 수행
        // enableStompBrokerRelay -> SimpleBroker의 기능과 외부 Message Broker(RabbitMQ, ActiveMQ, ...)에 메세지를 전달하는 기능을 가짐
    }
}
