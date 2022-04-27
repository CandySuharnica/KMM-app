import SwiftUI
import shared

@main
struct iOSApp: App {
    let sdk = CandySDK(databaseDriverFactory: <#T##DatabaseDriverFactory#>)
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
