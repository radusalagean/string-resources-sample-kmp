import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        Main_commonKt.application(p: IOSPlatform())
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
