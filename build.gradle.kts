import com.google.protobuf.gradle.id

plugins {
  java
  id("org.springframework.boot") version "3.5.5"
  id("io.spring.dependency-management") version "1.1.7"
  id("com.google.protobuf") version "0.9.5"
}

val springCloudVersion by extra("2025.0.0")
val springGrpcVersion by extra("0.11.0")

group = "com.dulfinne.randomgame"
version = "0.0.1-SNAPSHOT"
description = "transaction-service"

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

repositories {
  mavenCentral()
}

val grpcVersion = "1.75.0"
val protocVersion = "3.25.5"
val grpcClientVersion = "3.1.0.RELEASE"

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-validation")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.projectlombok:lombok")
  implementation("net.devh:grpc-server-spring-boot-starter:$grpcClientVersion")
  implementation("org.springframework.cloud:spring-cloud-stream-binder-kafka")

  annotationProcessor("org.projectlombok:lombok")

  runtimeOnly("org.postgresql:postgresql")
  runtimeOnly("io.grpc:grpc-netty-shaded:$grpcVersion")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

protobuf {
  protoc {
    artifact = "com.google.protobuf:protoc:$protocVersion"
  }
  plugins {
    id("grpc") {
      artifact = "io.grpc:protoc-gen-grpc-java:$grpcVersion"
    }
  }
  generateProtoTasks {
    all().forEach {
      it.plugins {
        id("grpc")
      }
    }
  }
}

dependencyManagement {
  imports {
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    mavenBom("org.springframework.grpc:spring-grpc-dependencies:$springGrpcVersion")
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}
