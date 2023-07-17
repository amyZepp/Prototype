package com.example.prototype.ui.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetPasswordOption
import androidx.credentials.GetPublicKeyCredentialOption
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.prototype.data.MainUiState
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "AuthViewModel"

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    private val _isSignedIn = MutableLiveData<Boolean>()
    val isSignedIn: LiveData<Boolean> get() = _isSignedIn

    fun callGetCredential(
        context: Context,
        activity: Activity,
        signInWithCredential: (Credential) -> Unit,
        handleGetCredentialFailure: (GetCredentialException) -> Unit,
    ) {
        val credentialManager = CredentialManager.create(context)

        val getPasswordOption = GetPasswordOption()

        val getPublicKeyCredentialOption = GetPublicKeyCredentialOption(
            requestJson = generateGetPasskeyRequestJsonFromServer(),
            // No need to fill this unless you are a browser and are making an origin-based request
            clientDataHash = null,
        )

        val request = GetCredentialRequest(
            credentialOptions = listOf(getPasswordOption, getPublicKeyCredentialOption)
        )

        // The API call will launch a credential selector UI for the user to pick a login credential.
        // It will be canceled if this coroutine scope is canceled. If you want the operation to persist
        // through your UI lifecycle (e.g. configuration changes), choose a coroutine scope that is
        // broader than your UI lifecycle (e.g. ViewModelScope)
        yourCoroutineScope.launch {
            try {
                val response = credentialManager.getCredential(
                    // Important: use an Activity context to ensure that the system credential selector
                    // ui is launched within the same activity stack to avoid undefined UI transition
                    // behavior.
                    context = activity,
                    request = request,
                )
                signInWithCredential(response.credential)
            } catch (e: GetCredentialException) {
                handleGetCredentialFailure(e)
            }
        }
    }

    private val yourCoroutineScope = MainScope()

    private fun generateGetPasskeyRequestJsonFromServer(): String {
        throw NotImplementedError(
            "Apps using this sample code should " +
                    "add a call here to generate the passkey request json from " +
                    "their own server"
        )
    }
}