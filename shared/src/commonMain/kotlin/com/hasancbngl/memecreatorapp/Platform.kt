package com.hasancbngl.memecreatorapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform