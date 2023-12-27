package merlan.pco.ui.navigation.model

sealed class AppRouting(val route: String) {
    data object StartingRoute : AppRouting("starting_route")
    data object ContentRoute : AppRouting("content_route")
}