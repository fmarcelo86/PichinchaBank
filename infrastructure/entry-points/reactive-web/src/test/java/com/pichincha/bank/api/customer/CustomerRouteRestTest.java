package com.pichincha.bank.api.customer;

import com.pichincha.bank.api.costumer.CustomerHandler;
import com.pichincha.bank.api.costumer.CustomerRouterRest;
import com.pichincha.bank.api.costumer.dto.CustomerRequest;
import com.pichincha.bank.api.costumer.mapper.CustomerMapper;
import com.pichincha.bank.api.costumer.validation.CustomerValidator;
import com.pichincha.bank.model.customer.Customer;
import com.pichincha.bank.usecase.customer.CustomerUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_CLASS;

@DirtiesContext(classMode = BEFORE_CLASS)
@ContextConfiguration(classes = {CustomerRouterRest.class, CustomerHandler.class})
@WebFluxTest
public class CustomerRouteRestTest {
    private final String uriCustomer = "/api/v1/customer";
    @Autowired
    private ApplicationContext context;
    @MockBean
    private CustomerUseCase useCase;
    @MockBean
    private CustomerMapper mapper;
    @MockBean
    private CustomerValidator customerValidator;
    @Autowired
    private WebTestClient testClient;

    @BeforeTestClass
    public void setUp() {
        testClient = WebTestClient.bindToApplicationContext(context).build();
    }

    @Test
    public void getAllCustomerOk() {
        Customer customer = Customer.builder().ID(1).password("pass").Status(true).build();
        when(useCase.getAll()).thenReturn(Flux.just(customer));

        testClient.get()
                .uri(uriCustomer)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Customer.class)
                .contains(customer);

        verify(useCase).getAll();
    }

    @Test
    public void saveCustomerOk() {
        Customer customer = Customer.builder().ID(1).password("pass").Status(true).build();
        CustomerRequest request = CustomerRequest.builder().ID(1).password("pass").Status(true).build();

        when(customerValidator.validateBody(request)).thenReturn(Mono.just(request));
        when(mapper.toEntity(request, Customer.builder().build())).thenReturn(customer);
        when(useCase.save(customer)).thenReturn(Mono.just(customer));

        testClient.post()
                .uri(uriCustomer)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(Customer.class)
                .isEqualTo(customer);

        verify(customerValidator).validateBody(request);
        verify(mapper).toEntity(request, Customer.builder().build());
        verify(useCase).save(customer);
    }
}