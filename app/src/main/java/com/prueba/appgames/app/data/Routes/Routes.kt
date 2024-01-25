package com.prueba.appgames.app.data.Routes

sealed class Routes(val route:String){
    object Screen1:Routes("screen1")
    object Screen2:Routes("screen2/{idGame}"){
        fun createRoute(idGame: Int) = "screen2/${idGame}"
    }

}