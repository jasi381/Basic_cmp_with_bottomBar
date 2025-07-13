package com.jasmeet.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        // Handle deep link from initial intent
        handleDeepLink(intent)

        setContent {
            App()
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)

        //handle deeplink when app is already running
        handleDeepLink(intent)
    }

    private fun handleDeepLink(intent: Intent?){
        if(intent?.action == Intent.ACTION_VIEW){
            intent.data?.toString()?.let { uri->
                //Pass the URI to our cross-platform handler

                ExternalUriHandler.handleUri(uri)
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}