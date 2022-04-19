package com.jhuguet.ThymeleafCRUDApplication.Infrastructure.Persistence.RepositoryImp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class UserServiceDeserializer implements Deserializer<UserServiceEvent> {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public UserServiceEvent deserialize(String s, byte[] bytes) {
        try{
            if (bytes != null){
                System.out.println("Serializing UserServiceEvent...");
                return objectMapper.readValue(new String(bytes, "UTF-8"),
                        new TypeReference<UserServiceEvent>() {});
            }else{
                System.out.println("Bytes data received is null.");
                return null;
            }
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing bytes[] to UserServiceEvent");
        }
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
