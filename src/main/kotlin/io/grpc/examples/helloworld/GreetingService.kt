package io.grpc.examples.helloworld

import jakarta.inject.Singleton

@Singleton
class GreetingService {
    fun sayHello(name : String) = "Hello $name from Kotlin"
}