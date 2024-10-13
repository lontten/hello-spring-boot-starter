import com.vanniktech.maven.publish.SonatypeHost

plugins {
    java
    id("java-library")
    id("com.vanniktech.maven.publish") version "0.29.0"
}
group = "com.lontten.canal"
version = "1.1.1.RELEASE"
//version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

mavenPublishing {
//    publishToMavenCentral(SonatypeHost.DEFAULT)
    // or when publishing to https://s01.oss.sonatype.org
//    publishToMavenCentral(SonatypeHost.S01)
    // or when publishing to https://central.sonatype.com/
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()

    coordinates(rootProject.group.toString(), rootProject.name, rootProject.version.toString())

    pom {
        name = rootProject.name
        description = "demo starter ."
        inceptionYear = "2024"
        url = "https://github.com/lontten/hello-spring-boot-starter/"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                distribution = "http://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = "lontten"
                name = "lontten"
                url = "https://github.com/lontten/"
            }
        }
        scm {
            url = "https://github.com/lontten/hello-spring-boot-starter/"
            connection = "scm:git:git://github.com/lontten/hello-spring-boot-starter.git"
            developerConnection = "scm:git:ssh://github.com:lontten/hello-spring-boot-starter.git"
        }
    }
}


repositories {
    maven { url = uri("https://repo.maven.apache.org/maven2") }

    maven { url = uri("https://jitpack.io") }
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
    implementation(libs.guava)
    implementation("com.github.hiwepy:canal-spring-boot-starter:3.3.x.20240823.RELEASE")
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.register("pushToMaven") {
    doFirst {

    }
    dependsOn("clean")
}
tasks.register("hello") {
    doLast {
        println(project.name)
        println(name)
        println(rootProject.name)
        println("Hello!")
    }
}

tasks.register("greet") {
    doLast {
        println("How are you?")
        println(group)
        println(project.group)
        println(rootProject.group)
    }
    dependsOn("hello")
}