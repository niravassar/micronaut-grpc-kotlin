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

//class HelloWorldClient(channel: Channel?) {
//    private val blockingStub: GreeterBlockingStub
//
//    /** Construct client for accessing HelloWorld server using the existing channel.  */
//    init {
//        // 'channel' here is a Channel, not a ManagedChannel, so it is not this code's responsibility to
//        // shut it down.
//
//        // Passing Channels to code makes code easier to test and makes it easier to reuse Channels.
//        blockingStub = GreeterGrpc.newBlockingStub(channel)
//    }
//
//    /** Say hello to server.  */
//    fun greet(name: String) {
//        logger.info("Will try to greet $name ...")
//        val request = HelloRequest.newBuilder().setName(name).build()
//        val response: HelloReply
//        response = try {
//            blockingStub.sayHello(request)
//        } catch (e: StatusRuntimeException) {
//            logger.log(Level.WARNING, "RPC failed: {0}", e.status)
//            return
//        }
//        logger.info("Greeting: " + response.message)
//        println("Greeting: " + response.message)
//    }
//
//    companion object {
//        private val logger = Logger.getLogger(HelloWorldClient::class.java.name)
//
//        /**
//         * Greet server. If provided, the first element of `args` is the name to use in the
//         * greeting. The second argument is the target server.
//         */
//        @Throws(Exception::class)
//        @JvmStatic
//        fun main(args: Array<String>) {
//            var user = "world"
//            // Access a service running on the local machine on port 50051
//            var target = "localhost:50051"
//            // Allow passing in the user and target strings as command line arguments
//            if (args.size > 0) {
//                if ("--help" == args[0]) {
//                    System.err.println("Usage: [name [target]]")
//                    System.err.println("")
//                    System.err.println("  name    The name you wish to be greeted by. Defaults to $user")
//                    System.err.println("  target  The server to connect to. Defaults to $target")
//                    System.exit(1)
//                }
//                user = args[0]
//            }
//            if (args.size > 1) {
//                target = args[1]
//            }
//
//            // Create a communication channel to the server, known as a Channel. Channels are thread-safe
//            // and reusable. It is common to create channels at the beginning of your application and reuse
//            // them until the application shuts down.
//            //
//            // For the example we use plaintext insecure credentials to avoid needing TLS certificates. To
//            // use TLS, use TlsChannelCredentials instead.
//            val channel = Grpc.newChannelBuilder(target, InsecureChannelCredentials.create())
//                .build()
//            try {
//                val client = HelloWorldClient(channel)
//                client.greet(user)
//            } finally {
//                // ManagedChannels use resources like threads and TCP connections. To prevent leaking these
//                // resources the channel should be shut down when it will no longer be used. If it may be used
//                // again leave it running.
//                channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS)
//            }
//        }
//    }
//}