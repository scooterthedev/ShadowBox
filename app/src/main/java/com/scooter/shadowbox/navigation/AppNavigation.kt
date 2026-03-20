package com.scooter.shadowbox.navigation


sealed class Screen(val router: String) {
        object Home : Screen("home")
        object Editor : Screen("editor?connectionId={connectionId}") {
            fun passID(id: String? = null): String {
                return if (id != null) "editor?connectionID=$id" else "editor"
            }
        }
}