plugins {
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    java
}

group = "com.example"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot Starter Web for REST endpoints
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Dependency for handling file uploads
    implementation("org.springframework.boot:spring-boot-starter-tomcat")

    // Lombok (optional, for less boilerplate)
    implementation("org.projectlombok:lombok:1.18.28")

    // For unit testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17)) // Ensure you're using Java 17
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}