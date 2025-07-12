package com.jasmeet.myapplication

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform