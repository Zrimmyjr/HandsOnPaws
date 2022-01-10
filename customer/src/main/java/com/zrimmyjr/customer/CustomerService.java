package com.zrimmyjr.customer;

import com.zrimmyjr.amqp.RabbitMQMessageProducer;
import com.zrimmyjr.clients.fraud.FraudCheckResponse;
import com.zrimmyjr.clients.fraud.FraudClient;
import com.zrimmyjr.clients.notification.NotificationClient;
import com.zrimmyjr.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .build();
        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
        if(fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("Fraudster!");
        }
        NotificationRequest notificationRequest = new NotificationRequest(
                        customer.getId(),
                        String.format("Hey %s, it works!", customer.getFirstName()),
                        "Test"
                );
        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );

    }
}
