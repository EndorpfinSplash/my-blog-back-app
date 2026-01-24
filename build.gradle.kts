plugins {
    id("java")
    id("war")
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
}

tasks.war {
    archiveFileName.set("ROOT.war")
}

tasks.register<Copy>("deployWar") {
    dependsOn("war") // сначала собрать WAR
    from(tasks.named("war")) // взять артефакт
    into("C:/apache-tomcat-10.1.49/webapps") // путь к webapps Tomcat
}


group = "by.jdeveloper"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.springframework:spring-framework-bom:6.2.13"))

    implementation("org.springframework:spring-core:6.2.13")
    implementation("org.springframework:spring-context:6.2.13")
    implementation("org.springframework:spring-beans:6.2.13")

    // Spring Web (MVC)
    implementation("org.springframework:spring-web:6.2.13")
    implementation("org.springframework:spring-webmvc:6.2.13")

    // Spring Data JDBC
    implementation("org.springframework.data:spring-data-jdbc:4.0.1")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.2")

    implementation("org.postgresql:postgresql:42.7.3")
    testImplementation("com.h2database:h2:2.2.220")

    implementation("jakarta.servlet:jakarta.servlet-api:6.0.0")
    testImplementation("org.springframework:spring-test:6.2.13")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    compileOnly("org.projectlombok:lombok:1.18.42")
    annotationProcessor("org.projectlombok:lombok:1.18.42")

    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

    implementation("org.slf4j:slf4j-simple:2.0.12")

    compileOnly("jakarta.servlet:jakarta.servlet-api:6.0.0")
    testImplementation("org.springframework:spring-test:6.2.13")
    testImplementation("org.hamcrest:hamcrest:2.2")
    testImplementation("com.jayway.jsonpath:json-path:2.9.0")
    testImplementation("org.mockito:mockito-core:5.14.2")
//    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
//    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

tasks.test {
    useJUnitPlatform()
}