val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val exposed_version: String by project
val hikari_version: String by project
val postgres_version: String by project
val flyway_version: String by project
val koin_version: String by project

plugins {
    application
    kotlin("jvm") version "1.5.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.5.0"

    //shadowJar
    id("com.github.johnrengelman.shadow") version "6.0.0"
    id("java")
}

group = "com.wsr"
version = "0.0.1"
project.setProperty("mainClassName", "com.wsr.ApplicationKt")

application {
    mainClass.set("com.wsr.ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {

    //サーバー側
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-serialization:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")

    //クライアント側
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-serialization:$ktor_version")

    //ログ
    implementation("ch.qos.logback:logback-classic:$logback_version")

    //テスト
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("junit:junit:4.12")

    //exposed
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jodatime:$exposed_version")

    //データベース
    implementation("com.h2database:h2:1.4.200")

    //hikari
    implementation("com.zaxxer:HikariCP:$hikari_version")

    //hikari用のpostgresのドライバー
    implementation("org.postgresql:postgresql:$postgres_version")

    //flyway
    implementation("org.flywaydb:flyway-core:$flyway_version")

    //HTML出力用
    implementation("io.ktor:ktor-html-builder:$ktor_version")
    implementation("io.ktor:ktor-freemarker:$ktor_version")

    //認証用
    implementation("io.ktor:ktor-auth:$ktor_version")

    //Koin
    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation("io.insert-koin:koin-logger-slf4j:$koin_version")
}
