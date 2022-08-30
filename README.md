### Expected Behavior

In our Micronaut/Picocli based command line application, we have multiple abstract classes containing fields annotated with `@Inject`. These abstract classes are used as base classes for concrete Picocli `@Command` implementations. We'd expect Micronaut to properly inject these fields with the corresponding `@Singleton` instances.

### Actual Behaviour

* If there are any custom annotations (defined in our own com.fortify.* package) present in either the abstract base class or the concrete command implementation, the fields annotated with `@Inject` get injected properly.
* If there are only standard annotations (from java.lang, picocli, Jackson, ...) present in the class hierarchy, the fields annotated with `@Inject` do not get injected and remain null.

### Steps To Reproduce

1. Clone sample project https://github.com/rsenden/micronaut-inject-issue
2. Run `./gradlew clean shadowJar`
3. Run `java -jar build/libs/mncli.jar annotated-superclass`; outputs `Singleton called`
4. Run `java -jar build/libs/mncli.jar annotated-subclass`; outputs `Singleton called`
5. Run `java -jar build/libs/mncli.jar field-annotation`; outputs `Singleton called`
6. Run `java -jar build/libs/mncli.jar no-annotation`; fails with NPE because singleton was not injected
7. Run `java -jar build/libs/mncli.jar standard-annotation`; fails with NPE because singleton was not injected

### Environment Information

- Operating system: Ubuntu 22.04.1 LTS running under Windows Subsystem for Linux 2 (WSL2)
- Java version: Openjdk version "11.0.16" 2022-07-19

### Example Application

https://github.com/rsenden/micronaut-inject-issue

### Micronaut Version

3.4.2
