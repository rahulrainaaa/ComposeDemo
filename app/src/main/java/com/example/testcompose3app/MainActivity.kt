package com.example.testcompose3app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.testcompose3app.ui.theme.TestCompose3AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uri = intent.dataString
        setContent {
            TestCompose3AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "check") {

                        composable("profile") {
                            Button(onClick = {
                                navController.navigate("myapp://testing.app?name=MyFullName&age=23")
                            }) {
                                Text(text = "Check")
                            }
                        }

                        val deeplinkCheck = "myapp://testing.app?name={name}&age={age}"
                        composable(
                            route = deeplinkCheck,
                            arguments = listOf(
                                navArgument("name") {
                                    type = NavType.StringType
                                    defaultValue = "---"
                                },
                                navArgument("age") {
                                    type = NavType.StringType
                                    defaultValue = "---"
                                }
                            ),
                            deepLinks = listOf(
                                navDeepLink {
                                    uriPattern = deeplinkCheck
                                }
                            )
                        ) {

                            val name = it.arguments?.getString("name")
                            val age = it.arguments?.getString("age")
                            Column {
                                Text(text = "name = $name")
                                Text(text = "age = $age")
                                Button(onClick = {
                                    navController.navigate("profile")
                                }) {
                                    Text(text = "Show Profile")
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}