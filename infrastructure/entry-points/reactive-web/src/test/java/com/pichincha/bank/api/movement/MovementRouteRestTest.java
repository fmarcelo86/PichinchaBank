package com.pichincha.bank.api.movement;

import com.pichincha.bank.api.movement.dto.MovementRequest;
import com.pichincha.bank.api.movement.mapper.MovementMapper;
import com.pichincha.bank.api.movement.validation.MovementValidator;
import com.pichincha.bank.model.movement.Movement;
import com.pichincha.bank.usecase.movement.MovementUseCase;
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
@ContextConfiguration(classes = {MovementRouterRest.class, MovementHandler.class})
@WebFluxTest
public class MovementRouteRestTest {
    private final String uriCustomer = "/api/v1/movement";
    @Autowired
    private ApplicationContext context;
    @MockBean
    private MovementUseCase useCase;
    @MockBean
    private MovementMapper mapper;
    @MockBean
    private MovementValidator movementValidator;
    @Autowired
    private WebTestClient testClient;

    @BeforeTestClass
    public void setUp() {
        testClient = WebTestClient.bindToApplicationContext(context).build();
    }

    @Test
    public void getAllMovementOk() {
        Movement movement = Movement.builder().ID(1).status(true).build();
        when(useCase.getAll()).thenReturn(Flux.just(movement));

        testClient.get()
                .uri(uriCustomer)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Movement.class)
                .contains(movement);

        verify(useCase).getAll();
    }

    @Test
    public void saveMovementOk() {
        Movement movement = Movement.builder().ID(1).status(true).build();
        MovementRequest request = MovementRequest.builder().ID(1).status(true).build();

        when(movementValidator.validateBody(request)).thenReturn(Mono.just(request));
        when(mapper.toEntity(request)).thenReturn(movement);
        when(useCase.save(movement)).thenReturn(Mono.just(movement));

        testClient.post()
                .uri(uriCustomer)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(Movement.class)
                .isEqualTo(movement);

        verify(movementValidator).validateBody(request);
        verify(mapper).toEntity(request);
        verify(useCase).save(movement);
    }
}