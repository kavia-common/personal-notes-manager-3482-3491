androidApplication {
    namespace = "org.example.app"

    dependencies {
        implementation("org.apache.commons:commons-text:1.11.0")
        implementation(project(":utilities"))
        // Jetpack Compose Material3 UI dependencies
        implementation("androidx.activity:activity-compose:1.8.2")
        implementation("androidx.compose.material3:material3:1.2.0")
        implementation("androidx.compose.material:material-icons-extended:1.5.4")
        implementation("androidx.compose.foundation:foundation:1.5.4")
        implementation("androidx.compose.foundation:foundation-layout:1.5.4")
        implementation("androidx.compose.ui:ui:1.5.4")
        implementation("androidx.compose.ui:ui-graphics:1.5.4")
        implementation("androidx.compose.runtime:runtime:1.5.4")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
        implementation("androidx.compose.ui:ui-tooling-preview:1.5.4")
        implementation("androidx.compose.runtime:runtime-livedata:1.5.4")
        implementation("com.google.code.gson:gson:2.10.1")
    }
}
