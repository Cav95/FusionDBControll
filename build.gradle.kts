plugins {
    // Apply the java plugin to add support for Java
    java

    // Apply the application plugin to add support for building a CLI application
    application

    // Task to create a fat JAR
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("org.danilopianini.gradle-java-qa") version "1.75.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.mysql:mysql-connector-j:9.3.0")
    implementation("org.apache.commons:commons-lang3:3.17.0")
    testImplementation("org.assertj:assertj-core:3.27.3")

    // SLF4J for logging abstraction
    implementation("org.slf4j:slf4j-api:2.0.16")
    // Logback backend for SLF4J
    runtimeOnly("ch.qos.logback:logback-classic:1.5.12")

    // JUnit API and testing engine
    val jUnitVersion = "5.11.3"
    // when dependencies share the same version, grouping in a val helps to keep them in sync
    testImplementation("org.junit.jupiter:junit-jupiter-api:$jUnitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jUnitVersion")

    // https://mvnrepository.com/artifact/com.microsoft.sqlserver/mssql-jdbc
        implementation("com.microsoft.sqlserver:mssql-jdbc:13.2.0.jre11")

    // https://mvnrepository.com/artifact/org.apache.commons/commons-text
     implementation("org.apache.commons:commons-text:1.14.0")

     // https://mvnrepository.com/artifact/com.toedter/jcalendar
        implementation("com.toedter:jcalendar:1.4")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.compileJava {
    options.encoding = "UTF-8"
}

application {
    mainClass = "descriptionupdate.DescriptionUpdateMain"
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events(*org.gradle.api.tasks.testing.logging.TestLogEvent.values())
        showStandardStreams = true
    }
}
