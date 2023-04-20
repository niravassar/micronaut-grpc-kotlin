## Micronaut Grpc Kotlin

This project is a direct implementation of the Micronaut Grpc Java project but done in kotlin. Please reference this other
project for learning and implementation details:

https://github.com/niravassar/micronaut-grpc-java

## References and Tips

- This project was generated with this curl command

```shell
curl --location --request GET 'https://launch.micronaut.io/create/grpc/micronaut-grpc-kotlin?lang=KOTLIN&build=GRADLE' --output micronaut-grpc-kotlin.zip
```

- jUnit must be used with micronaut kotlin projects, so the test is used with Junit.
- Good reference for grpc kotlin, but it is littered with Android technology. I just focused on the simple examples with I could borrow and understand
  - https://github.com/grpc/grpc-kotlin/
- The translator in intellij from java to kotlin is helpful but pretty verbose. I trimmed the kotlin code down using skills I gained along the way. 
Therefore, use the translator as a guide, and push it further with your own skills. 