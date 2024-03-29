plugins {
    id("gradlebuild.distribution.api-java")
    id("gradlebuild.publish-public-libraries")
}

description = "Common shared annotations"

gradlebuildJava.usedInWorkers()

dependencies {
    compileOnly(libs.jetbrainsAnnotations)

    api(libs.jsr305)
}
