package com.pichincha.bank.api.account;

import com.pichincha.bank.model.account.Account;
import com.pichincha.bank.model.exception.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@RouterOperations({
        @RouterOperation(method = RequestMethod.POST, operation =
        @Operation(description = "Get all account", summary = "Get all account", operationId = "getAll", tags = "Account",
                responses = {
                        @ApiResponse(responseCode = "200", description = "Get account response", content = {
                                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                        schema = @Schema(implementation = Account.class))
                        }),
                        @ApiResponse(responseCode = "400", description = "Bad Request response", content = {
                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                        schema = @Schema(implementation = ErrorResponse.class))
                        })
                }))
})
public @interface PriceApiInfo {
}
