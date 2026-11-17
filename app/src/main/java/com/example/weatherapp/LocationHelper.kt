package com.example.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

@SuppressLint("MissingPermission")
fun getDeviceLocation(
    context: Context,
    onResult: (Double, Double) -> Unit,
    onError: (String) -> Unit
) {
    LocationServices.getFusedLocationProviderClient(context)
        .getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
        .addOnSuccessListener { location ->
            if (location != null) onResult(location.latitude, location.longitude)
            else onError("Location unavailable")
        }
        .addOnFailureListener { onError(it.message ?: "Location error") }
}

@Composable
fun rememberLocationRequester(viewModel: WeatherViewModel): () -> Unit {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { results ->
        if (results.values.any { it }) {
            getDeviceLocation(
                context,
                onResult = { lat, lon -> viewModel.loadMyLocation(lat, lon) },
                onError = { viewModel.showError(it) }
            )
        } else {
            viewModel.showError("Location permission denied")
        }
    }
    return {
        launcher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }
}