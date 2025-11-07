plugins {
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "app.smart-parking-finder"
version = "0.1.0-Stage2"

repositories { mavenCentral() }

java {
    toolchain { languageVersion.set(JavaLanguageVersion.of(17)) } // يضمن 17 عند الكل
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.11.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

javafx {
    version = "21.0.3"
    modules("javafx.controls", "javafx.fxml")
}

application {
    mainClass.set("app.MainApp")
}

tasks.test { useJUnitPlatform() }
