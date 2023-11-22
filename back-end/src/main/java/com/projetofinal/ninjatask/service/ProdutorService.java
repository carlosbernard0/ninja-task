package com.projetofinal.ninjatask.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetofinal.ninjatask.dto.TarefaLogDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProdutorService {

    @Value(value ="${kafka.topic}")
    private String topico;

    private final KafkaTemplate<String, String> KafkaTemplate;

    public void enviarMensagemAoTopico(TarefaLogDTO tarefaLogDTO) throws JsonProcessingException {

        String mensagemStr = new ObjectMapper().writeValueAsString(tarefaLogDTO);
        MessageBuilder<String> stringMessageBuilder = MessageBuilder.withPayload(mensagemStr)
                .setHeader(KafkaHeaders.TOPIC,topico)
                .setHeader(KafkaHeaders.KEY, UUID.randomUUID().toString());

        Message<String> mensagemParaKafka = stringMessageBuilder.build();

        CompletableFuture<SendResult<String, String>> future = KafkaTemplate.send(mensagemParaKafka);

        future.whenComplete(new BiConsumer<SendResult<String, String>, Throwable>() {
            @Override
            public void accept(SendResult<String, String> stringStringSendResult, Throwable throwable) {
                if(throwable != null){
                    log.info("ocorrreu um erro ao enviar a mensagem: {}, exception:{}", mensagemStr, throwable.getMessage());
                }else{
                    ProducerRecord<String, String> record = stringStringSendResult.getProducerRecord();
                    log.info("Mensagem enviada com sucesso: {} - {}", record.key(), record.value());
                }
            }
        });
    }
}
