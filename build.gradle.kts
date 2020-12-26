import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.1"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	kotlin("jvm") version "1.4.21"
	kotlin("plugin.spring") version "1.4.21"
	kotlin("plugin.jpa") version "1.4.21"
	kotlin("plugin.allopen") version "1.3.61"
	application
	distribution
}

val graphqlVersion = "4.0.0-alpha.8"
val springGraphqlVersion = "8.1.0"
val kTorVersion: String = "1.4.1"
group = "com.buddie"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.Embeddable")
	annotation("javax.persistence.MappedSuperclass")
}

repositories {
	mavenCentral()
	jcenter()
}

dependencies {
	implementation("com.google.code.gson:gson:2.8.6")
	implementation("org.postgresql:postgresql:42.2.2")
	implementation("org.jetbrains.exposed", "exposed-core", "0.24.1")
	implementation("org.jetbrains.exposed", "exposed-dao", "0.24.1")
	implementation("org.jetbrains.exposed", "exposed-jdbc", "0.24.1")
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.boot:spring-boot-starter-hateoas")
	implementation("com.expediagroup", "graphql-kotlin-spring-server", graphqlVersion)
	implementation("com.expediagroup", "graphql-kotlin-schema-generator", graphqlVersion)
	implementation("com.expediagroup", "graphql-kotlin-federation", graphqlVersion)
	runtimeOnly("org.postgresql:postgresql")

	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(module = "junit")
		exclude(module = "mockito-core")
	}
	testImplementation("org.junit.jupiter:junit-jupiter-api")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
	testImplementation("com.ninja-squad:springmockk:1.1.3")
}


tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
