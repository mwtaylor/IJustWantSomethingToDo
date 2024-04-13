buildscript {
    extra.apply {
        set("room_version", "2.6.1")
        set("lifecycle_version", "2.7.0")
        set("arch_version", "2.2.0")
    }
}

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
}
