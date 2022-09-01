# Kotlin-SpringBoot-Rsocket-Simple-Tutorial
The following was discovered as part of building this project:

* For request-response run:
`rsc tcp://localhost:8181 --route greetings -d "{\"name\":\"Nmicra\"}"`
* To test the error-handling run:
`rsc tcp://localhost:8181 --stream --route gree.nmicra`
* To test the streaming run:
    `rsc tcp://localhost:8181 --stream --route gree.Nmicra`
