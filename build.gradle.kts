import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("net.minecraftforge.gradle") version "5.1.+"
    `maven-publish`
}

version = "1.0"
group = "com.algorithmlx"

evaluationDependsOnChildren()

val mc_version: String by project
val forge_version: String by project

val coroutines_version: String by project
val serialization_version: String by project

val shadow: Configuration by configurations.creating {
    exclude("org.jetbrains", "annotations")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    withSourcesJar()
}

jarJar.enable()

configurations {
    apiElements {
        artifacts.clear()
    }
    runtimeElements {
        setExtendsFrom(emptySet())

        artifacts.clear()
        outgoing.artifact(tasks.jarJar)
    }
    minecraftLibrary {
        extendsFrom(shadow)
    }
}

minecraft {
    mappings("official", mc_version)

    runs {
        create("client") {
            workingDirectory(project.file("run"))

            property("forge.logging.markers", "SCAN,LOADING,CORE")
            property("forge.logging.console.level", "debug")
        }

        create("server") {
            workingDirectory(project.file("run/server"))

            property("forge.logging.markers", "SCAN,LOADING,CORE")
            property("forge.logging.console.level", "debug")
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    minecraft("net.minecraftforge:forge:$mc_version-$forge_version")

    shadow("org.jetbrains.kotlin:kotlin-reflect:${kotlin.coreLibrariesVersion}")
    shadow("org.jetbrains.kotlin:kotlin-stdlib:${kotlin.coreLibrariesVersion}")
    shadow("org.jetbrains.kotlin:kotlin-stdlib-common:${kotlin.coreLibrariesVersion}")
    shadow("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutines_version}")
    shadow("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:${coroutines_version}")
    shadow("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${coroutines_version}")
    shadow("org.jetbrains.kotlinx:kotlinx-serialization-core:${serialization_version}")
    shadow("org.jetbrains.kotlinx:kotlinx-serialization-json:${serialization_version}")

    implementation(kotlin("stdlib"))
}

tasks {
    jar {
        enabled = false
    }

    jarJar.configure {
        from(shadow.map(::zipTree).toTypedArray())
        manifest {
            attributes(
                    "Automatic-Module-Name" to "liquidkt",
                    "FMLModType" to "LANGPROVIDER"
            )
        }
    }

    whenTaskAdded {
        if (name == "reobfJar") {
            enabled = false
        }

        if (name == "prepareRuns") {
            doFirst {
                sourceSets.main.get().output.files.forEach(File::mkdirs)
            }
        }
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    assemble {
        dependsOn(jarJar)
    }
}

publishing {
    publications {
        register<MavenPublication>("maven") {
            suppressAllPomMetadataWarnings()
            from(components["java"])
        }
    }

    repositories {
        maven {
            url = uri(rootProject.properties["mavenUrl"].toString())
            credentials {
                username = rootProject.properties["mavenUsername"].toString()
                password = rootProject.properties["mavenPassword"].toString()
            }
        }
    }
}

fun DependencyHandler.minecraft(
        dependencyNotation: Any
): Dependency? = add("minecraft", dependencyNotation)

fun DependencyHandler.library(
        dependencyNotation: Any
): Dependency? = add("library", dependencyNotation)

fun <T> Property<T>.provider(value: T) {
    set(value)
}