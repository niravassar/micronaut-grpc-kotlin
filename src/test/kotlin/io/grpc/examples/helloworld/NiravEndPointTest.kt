package io.grpc.examples.helloworld

import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import io.micronaut.grpc.server.GrpcServerChannel
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import micronaut.grpc.kotlin.NiravRequest
import micronaut.grpc.kotlin.NiravServiceGrpc
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@MicronautTest
internal class NiravEndpointTest {
    @Inject
    var blockingStub: NiravServiceGrpc.NiravServiceBlockingStub? = null

    @Test
    fun testNiravStuff() {
        val request: NiravRequest = NiravRequest.newBuilder().setName("Nirav").build()
        assertEquals("Hello Nirav from Kotlin", blockingStub?.send(request)?.getMessage() ?: "")
    }
}

@Factory
internal class Clients {
    @Bean
    fun blockingStub(@GrpcChannel(GrpcServerChannel.NAME) channel: ManagedChannel?): NiravServiceGrpc.NiravServiceBlockingStub {
        return NiravServiceGrpc.newBlockingStub(channel)
    }
}