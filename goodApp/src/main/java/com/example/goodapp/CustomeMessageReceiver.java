package com.example.goodapp;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.pubsub.v1.PubsubMessage;
import com.google.api.core.ApiFuture;
public class CustomeMessageReceiver implements MessageReceiver {
    
    @Override
    public void receiveMessage(PubsubMessage message, AckReplyConsumer consumer) {
        // Handle incoming message
        System.out.println("Received message: " + message.getData().toStringUtf8());
        
        // Acknowledge the message to indicate it has been processed
         // Acknowledge the message to indicate it has been processed
         consumer.ack(); // Acknowledge the message
        
         // Print something after acknowledging the message
         System.out.println("Message acknowledged successfully");
    }
}
