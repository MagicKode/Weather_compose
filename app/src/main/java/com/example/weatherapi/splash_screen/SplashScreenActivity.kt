//package com.example.weatherapi.splash_screen
//
//import android.annotation.SuppressLint
//import android.content.Intent
//import android.content.res.Resources
//import android.content.res.Resources.Theme
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.animation.core.Animatable
//import androidx.compose.animation.core.Easing
//import androidx.compose.animation.core.animateDpAsState
//import androidx.compose.animation.core.tween
//import androidx.compose.animation.defaultDecayAnimationSpec
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.alpha
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
//import com.example.weatherapi.MainActivity
//import com.example.weatherapi.R
//import kotlinx.coroutines.delay
//
//@SuppressLint("CustomSplashScreen")
//class SplashScreenActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent(
//            The{
//                SplashScreen()
//            }
//        )
//    }
//
//    @Preview
//    @Composable
//    private fun SplashScreen() {
////        val alpha = remember {
////            Animatable(0f)
////        }
//        LaunchedEffect(key1 = true) {
////            alpha.animateTo(1f)
//            delay(2000)
//            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
//        }
////        Box(
////            modifier = Modifier.fillMaxSize(),
////            contentAlignment = Alignment.Center) {
////            Image(
////                modifier = Modifier.alpha(alpha.value),
////                painter = painterResource(id = R.drawable.weather_logo),
////                contentDescription = null
////            )
////        }
//    }
//}