import SwiftUI
import ComposeApp

import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView()
                .onOpenURL { url in
                    // Debug logging
                    print("🔗 iOS received URL: \(url.absoluteString)")
                    print("🔗 URL scheme: \(url.scheme ?? "no scheme")")
                    print("🔗 URL host: \(url.host ?? "no host")")
                    print("🔗 URL path: \(url.path)")
                    print("🔗 URL query: \(url.query ?? "no query")")

                    // Call the Kotlin handler
                    ExternalUriHandlerKt.handleDeepLinkUri(uri: url.absoluteString)
                    print("🔗 Called Kotlin handler with: \(url.absoluteString)")
                }
        }
    }
}