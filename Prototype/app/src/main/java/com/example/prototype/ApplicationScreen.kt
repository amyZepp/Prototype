package com.example.prototype

import android.app.Activity
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.prototype.ui.AccountRecoveryScreen
import com.example.prototype.ui.HelpScreen
import com.example.prototype.ui.HomeScreen
import com.example.prototype.ui.LearnMoreScreen
import com.example.prototype.ui.MainMenuScreen
import com.example.prototype.ui.OtherOptionsScreen
import com.example.prototype.ui.PlaceholderScreen
import com.example.prototype.ui.SettingsScreen
import com.example.prototype.ui.ShrineAppScreen
import com.example.prototype.ui.SignInWithPasskeyScreen
import com.example.prototype.ui.SignInWithPasswordScreen
import com.example.prototype.ui.SignOutScreen
import com.example.prototype.ui.SignUpWithPasskeyScreen
import com.example.prototype.ui.SignUpWithPasswordScreen
import com.example.prototype.ui.viewmodel.AuthViewModel
import kotlinx.coroutines.CoroutineScope

private const val TAG = "ApplicationScreen"

enum class ApplicationScreen(@StringRes val title: Int){
    AccountRecovery(title = R.string.account_recovery),
    Help(title = R.string.help),
    Home(title = R.string.home),
    LearnMore(title = R.string.learn_more),
    MainMenu(title = R.string.main_menu),
    OtherOptions(title = R.string.other_options),
    Placeholder(title = R.string.todo),
    Settings(title = R.string.settings),
    ShrineApp(title = R.string.app_name),
    SignInWithPasskey(title = R.string.sign_in),
    SignInWithPassword(title = R.string.sign_in),
    SignOut(title = R.string.sign_out),
    SignUpWithPasskey(title = R.string.sign_up),
    SignUpWithPassword(title = R.string.sign_up),
}

@Composable
fun PrototypeAppBar(
    @StringRes currentScreenTitle: Int,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(
            stringResource(currentScreenTitle),
            style = TextStyle()
        ) },
        modifier = modifier.height(25.dp),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun PrototypeApp(
    scope: CoroutineScope,
    authViewModel: AuthViewModel
) {
    val activity = LocalContext.current as Activity
    val uiState = authViewModel.appState
    // Create NavController
    val navController = rememberNavController()
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = ApplicationScreen.valueOf(
        backStackEntry?.destination?.route ?: ApplicationScreen.SignInWithPasskey.name
    )
    Scaffold(
        topBar = {
            PrototypeAppBar(
                currentScreenTitle = currentScreen.title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
            )
        }
    ) { innerPadding ->
        val showScreen: String = if (uiState.isSignedIn) {
            ApplicationScreen.MainMenu.name
        } else {
            ApplicationScreen.Home.name
        }
        NavHost(
            navController = navController,
            startDestination = showScreen,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = ApplicationScreen.Help.name) {
                HelpScreen()
            }
            composable(route = ApplicationScreen.AccountRecovery.name) {
                AccountRecoveryScreen(
                    onAccountRecoveryButtonClicked = {
                        navController.navigate(ApplicationScreen.Placeholder.name)
                    },
                )
            }

            composable(route = ApplicationScreen.Home.name) {
                HomeScreen(
                    onSignInRequest = {
                        navController.navigate(ApplicationScreen.SignInWithPasskey.name)
                    },
                    onRegisterRequest = {
                        navController.navigate(ApplicationScreen.SignUpWithPasskey.name)
                    },
                    onRegisterResponse = {},
                    proceedButtonClicked = {
                        navController.navigate(ApplicationScreen.MainMenu.name)
                    },
                )
            }
            composable(route = ApplicationScreen.LearnMore.name) {
                LearnMoreScreen()
            }

            composable(route = ApplicationScreen.MainMenu.name) {
                MainMenuScreen(
                    onGoToTheAppClicked = {
                        navController.navigate(ApplicationScreen.ShrineApp.name)
                    },
                    onSettingsButtonClicked = {
                        navController.navigate(ApplicationScreen.Settings.name)
                    },
                    onHelpButtonClicked = {
                        navController.navigate(ApplicationScreen.Help.name)
                    },
                    onSignOutButtonClicked = {
                        navController.navigate(ApplicationScreen.SignOut.name)
                    },
                )
            }
            composable(route = ApplicationScreen.OtherOptions.name) {
                OtherOptionsScreen(
                    onSignUpWithPasskeyButtonClicked = {
                        authViewModel.signUpWithPasskey(activity,scope)
                    },
                    onSignUpWithPasswordButtonClicked = {
                        navController.navigate(ApplicationScreen.Placeholder.name)
                    },
                    onSignUpWithPhoneButtonClicked = {
                        navController.navigate(ApplicationScreen.Placeholder.name)
                    },
                    onLearnMoreClicked = {
                        navController.navigate(ApplicationScreen.LearnMore.name)
                    }
                )
            }

            composable(route = ApplicationScreen.Placeholder.name) {
                PlaceholderScreen()
            }

            composable(route = ApplicationScreen.Settings.name) {
                SettingsScreen(
                    onCreatePasskeyClicked = {
                        navController.navigate(ApplicationScreen.Placeholder.name)
                    },
                    onChangePasswordClicked = {
                        navController.navigate(ApplicationScreen.Placeholder.name)
                    },
                    onHelpClicked = {
                        navController.navigate(ApplicationScreen.Help.name)
                    }
                )
            }

            composable(route = ApplicationScreen.ShrineApp.name) {
                ShrineAppScreen()
            }

            composable(route = ApplicationScreen.SignInWithPasskey.name) {
                SignInWithPasskeyScreen(
                    onSignInWithPasskeyClicked = {
                        //authViewModel.signInWithPasskey(activity,scope)
                        navController.navigate(ApplicationScreen.MainMenu.name)
                    },
                    onTroubleClicked = {
                        navController.navigate(ApplicationScreen.AccountRecovery.name)
                    }
                )
            }

            composable(route = ApplicationScreen.SignInWithPassword.name) {
                SignInWithPasswordScreen(
                    onSignInButtonClicked = {
                        navController.navigate(ApplicationScreen.Placeholder.name)
                    },
                    onSignUpButtonClicked = {
                        navController.navigate(ApplicationScreen.SignUpWithPasskey.name)
                    },
                )
            }

            composable(route = ApplicationScreen.SignOut.name) {
                SignOutScreen()
            }

            composable(route = ApplicationScreen.SignUpWithPasskey.name) {
                SignUpWithPasskeyScreen(
                    onRegisterButtonClicked = {
                        authViewModel.signUpWithPasskey(activity,scope)
                        //navController.navigate(ApplicationScreen.Placeholder.name)
                    },
                    onSignUpWithOtherMethodClicked = {
                        //authViewModel.signUpWithPasskey(activity, scope)
                       //navController.navigate(ApplicationScreen.SignUpWithPassword.name)
                        navController.navigate(ApplicationScreen.OtherOptions.name)
                    }
                )
            }

            composable(route = ApplicationScreen.SignUpWithPassword.name) {
                SignUpWithPasswordScreen(
                    onSignUpWithPasswordButtonClicked = {
                        navController.navigate(ApplicationScreen.Placeholder.name)
                    },
                )
            }
        }
    }
}