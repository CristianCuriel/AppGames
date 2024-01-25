package com.prueba.appgames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.prueba.appgames.app.data.Routes.Routes
import com.prueba.appgames.app.ui.viewmodel.GameViewModel
import com.prueba.appgames.app.ui.views.MyScreen2
import com.prueba.appgames.app.ui.views.NavManager
import com.prueba.appgames.app.ui.views.component.Search
import com.prueba.appgames.ui.theme.AppGamesTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppGamesTheme {

                Scaffold(
                    containerColor = Color(0xFF141414),
                    modifier = Modifier.fillMaxSize(),
                    topBar = { MyTopAppBard() }

                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {

                        val navigationController = rememberNavController()

                        NavHost(
                            navController = navigationController,
                            startDestination = Routes.Screen1.route
                        ){
                            composable(Routes.Screen1.route) { NavManager(GameViewModel(), navigationController) }
                            composable(
                                Routes.Screen2.route,
                                arguments = listOf(navArgument("name") { type = NavType.StringType })
                            ) { backStackEntrey ->
                                val a: String? = backStackEntrey.arguments?.getString("name")
                                MyScreen2(navigationController, a!!)
                            }
                        }

                        //
                        //NavManager(GameViewModel())
                    }

                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBard() {

    TopAppBar(
        navigationIcon = {
            Text(
                text = "RAWG",
                fontSize = 22.sp,
                fontWeight = FontWeight.Black,
                color = Color.White,
                textAlign = TextAlign.Center,
                letterSpacing = 6.sp,
                modifier = Modifier.padding(end = 8.dp)

            )
        }, title = {
            Search()
        }, colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF151515)),
        actions = {

            // Icono de perfil (con imagen de ejemplo)
            Image(
                painter = painterResource(id = R.drawable.user), // Asegúrate de tener un recurso drawable para el icono del perfil
                contentDescription = "Profile",
                modifier = Modifier
                    .size(40.dp)
                    .padding(horizontal = 4.dp)
                    .clip(CircleShape) // Para hacer la imagen redonda
            )

            Icon(
                imageVector = Icons.Filled.Menu,
                tint = Color.White,
                modifier = Modifier.size(42.dp, 42.dp),
                contentDescription = "Localized description"
            )
        }
    )

}


