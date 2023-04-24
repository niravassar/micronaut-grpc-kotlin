package io.grpc.examples.helloworld

import io.grpc.Channel
import io.grpc.Grpc
import io.grpc.InsecureChannelCredentials
import micronaut.grpc.kotlin.NiravRequest
import micronaut.grpc.kotlin.NiravServiceGrpc

fun main(args: Array<String>) {
    var user = "Mr Nero Assar"
    var target = "localhost:50051"
    val channel = Grpc.newChannelBuilder(target, InsecureChannelCredentials.create()).build()
    val client = HelloWorldClient(channel)
    client.greet(user)
}

class HelloWorldClient(channel: Channel) {

    private val blockingStub : NiravServiceGrpc.NiravServiceBlockingStub

    init {
        blockingStub = NiravServiceGrpc.newBlockingStub(channel)
    }

    fun greet(name: String) {
        val request = NiravRequest.newBuilder().setName(name).build()
        val response = blockingStub.send(request)
        println("Greeting: " + response.message);
    }
}