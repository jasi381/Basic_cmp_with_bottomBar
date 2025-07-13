package com.jasmeet.myapplication


/**
 * Singleton for handling external URIs (deep links) across platforms.
 * Based on official JetBrains documentation pattern.
 */
object ExternalUriHandler {
    var listener: ((String) -> Unit)? = null
        set(value) {
            field = value
            println("🔗 ExternalUriHandler: Listener ${if (value != null) "SET" else "CLEARED"}")
            // If we have a cached URI and now have a listener, process it
            cachedUri?.let { uri ->
                println("🔗 ExternalUriHandler: Processing cached URI: $uri")
                value?.invoke(uri)
                cachedUri = null
            }
        }

    private var cachedUri: String? = null

    /**
     * Called by platform-specific code when a deep link URI is received.
     * If no listener is set, the URI is cached until a listener becomes available.
     */
    fun handleUri(uri: String) {
        println("🔗 ExternalUriHandler.handleUri called with: $uri")

        listener?.let { listener ->
            println("🔗 ExternalUriHandler: Listener available, invoking immediately")
            listener.invoke(uri)
        } ?: run {
            println("🔗 ExternalUriHandler: No listener available, caching URI")
            cachedUri = uri
        }
    }
}

/**
 * Top-level function for easy access from Swift/iOS
 * This ensures Swift can call the deep link handler without dealing with object syntax
 */
fun handleDeepLinkUri(uri: String) {
    println("🔗 handleDeepLinkUri called with: $uri")
    ExternalUriHandler.handleUri(uri)
}