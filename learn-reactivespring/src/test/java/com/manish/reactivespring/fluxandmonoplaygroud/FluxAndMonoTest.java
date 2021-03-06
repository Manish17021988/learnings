/*
 * Copyright (c) 2021. This Program is sole property of Manish Kumar written on 15/05/21, 4:44 PM.
 */

package com.manish.reactivespring.fluxandmonoplaygroud;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxAndMonoTest {

    @Test
    public void testFlux() {
        Flux<String> stringFlux = Flux.just("Manish", "Alok", "Pratyush", "Pravin")
                .concatWith(Flux.error(new RuntimeException("Error Occured")))
                .concatWith(Flux.just("After Exception"))
                .log();

        stringFlux.subscribe(System.out::println,
                (e) -> System.out.println("Exception is::" + e),
                () -> System.out.println("Completed"));
    }

    @Test
    public void testFlux_withElement() {
        Flux<String> stringFlux = Flux.just("Manish", "Alok", "Pratyush", "Pravin")
                .log();
        StepVerifier.create(stringFlux)
                .expectNext("Manish")
                .expectNext("Alok")
                .expectNext("Pratyush")
                .expectNext("Pravin")
                .verifyComplete();
    }

    @Test
    public void testFlux_verifyCount() {
        Flux<String> stringFlux = Flux.just("Manish", "Alok", "Pratyush", "Pravin")
                .log();
        StepVerifier.create(stringFlux)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    public void testFlux_withElement1() {
        Flux<String> stringFlux = Flux.just("Manish", "Alok", "Pratyush", "Pravin")
                .log();
        StepVerifier.create(stringFlux)
                .expectNext("Manish", "Alok", "Pratyush", "Pravin")
                .verifyComplete();
    }

    @Test
    public void testFlux_verifyError() {
        Flux<String> stringFlux = Flux.just("Manish", "Alok", "Pratyush", "Pravin")
                .concatWith(Flux.error(new RuntimeException("Error Occured")))
                .log();
        StepVerifier.create(stringFlux)
                .expectNext("Manish", "Alok", "Pratyush", "Pravin")
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    public void testFlux_verifyErrorMessage() {
        Flux<String> stringFlux = Flux.just("Manish", "Alok", "Pratyush", "Pravin")
                .concatWith(Flux.error(new RuntimeException("Error Occured")))
                .log();
        StepVerifier.create(stringFlux)
                .expectNext("Manish", "Alok", "Pratyush", "Pravin")
                .expectErrorMessage("Error Occured")
                .verify();
    }

    @Test
    public void monoTest() {
        Mono<String> stringMono = Mono.just("Manish")
                .log();
        stringMono.subscribe(System.out::println);
    }

    @Test
    public void monoTest_value() {
        Mono<String> stringMono = Mono.just("Manish")
                .log();
        StepVerifier.create(stringMono)
                .expectNext("Manish")
                .verifyComplete();
    }

}
