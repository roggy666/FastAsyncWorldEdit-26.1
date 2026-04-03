plugins {
    base
}
tasks.named("build") {
    subprojects.forEach {
        dependsOn("${it.path}:build")
    }
}
