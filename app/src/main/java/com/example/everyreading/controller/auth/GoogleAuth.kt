package com.example.everyreading.controller.auth

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.everyreading.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class GoogleAuth {
    @Composable
    fun GoogleLoginButton(activity: Activity, navController: NavController, onGoogleSignInResult: (GoogleSignInAccount) -> Unit) {
        var user by remember { mutableStateOf(Firebase.auth.currentUser) }
        val launcher = rememberFirebaseAuthLauncher(
            onAuthComplete = { result ->
                user = result.user
                Log.d("Login", "Login bem-sucedido. Navegando para a tela inicial.")
                navController.navigate("MainScreen")
            },
            onAuthError = {
                user = null
            }
        )
        val token = stringResource(id = R.string.default_web_client_id)
        val context = LocalContext.current

        Box(
            modifier = Modifier.clickable {
                val gso =
                    GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(token)
                        .requestEmail()
                        .build()
                val googleSignInClient = GoogleSignIn.getClient(context, gso)
                launcher.launch(googleSignInClient.signInIntent)
            }
        ) {
            Icon(
                painter = painterResource(R.drawable.google_icon_foreground),
                contentDescription = "Google Login Button",
                tint = Color.Unspecified
            )
        }
    }

    @Composable
    private fun rememberFirebaseAuthLauncher(
        onAuthComplete: (AuthResult) -> Unit,
        onAuthError: (ApiException) -> Unit
    ): ManagedActivityResultLauncher<Intent, ActivityResult> {
        val scope = rememberCoroutineScope()
        return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.d("GoogleAuth", "account $account")
                val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
                scope.launch {
                    val authResult = Firebase.auth.signInWithCredential(credential).await()
                    onAuthComplete(authResult)
                }
            } catch (e: ApiException) {
                Log.d("GoogleAuth", e.toString())
                onAuthError(e)
            }
        }
    }

}

