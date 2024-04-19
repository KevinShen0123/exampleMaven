package com.example.goodapp;
import com.google.api.core.ApiService;
import com.google.api.core.ApiService.Listener;
import com.google.api.core.ApiService.State;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.SubscriptionName;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.cloud.pubsub.v1.Publisher;
/**
 * Hello world!
 *
 */
public class App 
{
    // Replace these variables with your actual project ID and topic name
    private static final String PROJECT_ID = "sharp-maxim-420706";
    private static final String TOPIC_ID = "KevinTopic";
    public static void main( String[] args ) throws Exception
    {
        System.out.println("exec started!!!11");
        pub();
        sub();
        System.out.println( "Hello World!" );
    }
    public static void pub() throws Exception {
        // Initialize Google Cloud Pub/Sub publisher
        ProjectTopicName topicName = ProjectTopicName.of(PROJECT_ID, TOPIC_ID);
        Publisher publisher = Publisher.newBuilder(topicName).build();
        // Create a message
        String message = "Hello, Pub/Sub!";
        ByteString data = ByteString.copyFromUtf8(message);
        PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

        // Publish the message
        publisher.publish(pubsubMessage);

        // Shutdown the publisher
        publisher.shutdown();
        System.out.println("hello publisherr");
    }
    public static void sub() throws Exception {
          // Initialize Google Cloud Pub/Sub subscriber
          ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(PROJECT_ID, "my-subscription");
          Subscriber subscriber = Subscriber.newBuilder(subscriptionName, new CustomeMessageReceiver()).build(); // Explicitly specify the type of SubscriberBuilder here
  
          // Start the subscriber
          subscriber.startAsync().awaitRunning();
  
          // Wait for the subscriber to process messages
          try{
            subscriber.awaitTerminated(30, TimeUnit.SECONDS);
          }catch(TimeoutException e){
            System.out.println("pub sub good after 30 seconds");
          }
    }
}
