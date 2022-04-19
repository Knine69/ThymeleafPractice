package com.jhuguet.ThymeleafCRUDApplication.Infrastructure.Persistence.RepositoryImp;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import java.util.Map;

@Slf4j
public class UserServiceSerializer implements Serializer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String s, Object event) {
        try{
            if (event != null){
                System.out.println("Serializing UserServiceEvent...");
                return objectMapper.writeValueAsBytes(event);
            }else{
                System.out.println("Event is null.");
                return null;
            }
        } catch (Exception e) {
            throw new SerializationException("Error when serializing UserServiceEvent to bytes[]");
        }
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
