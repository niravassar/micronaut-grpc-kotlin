package io.grpc.examples.helloworld

import io.grpc.stub.StreamObserver
import jakarta.inject.Singleton
import micronaut.grpc.kotlin.NiravReply
import micronaut.grpc.kotlin.NiravRequest
import micronaut.grpc.kotlin.NiravServiceGrpc

@Singleton
class NiravEndPoint(private val greetingService: GreetingService) : NiravServiceGrpc.NiravServiceImplBase() {

    override fun send(request: NiravRequest?, responseObserver: StreamObserver<NiravReply>?) {
        val message = greetingService.sayHello(request?.name ?: "Someone")
        val reply = NiravReply.newBuilder().setMessage(message).build()
        println("The reply is $reply")
        responseObserver!!.onNext(reply)
        responseObserver.onCompleted()
    }
}