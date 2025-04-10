package com.example.sharpeningapp.ui.screens.DetailsScreen

sealed class DetailsTab(title: String) {
    object Skills : DetailsTab("Skills")
    object Activities : DetailsTab("Activities")
}