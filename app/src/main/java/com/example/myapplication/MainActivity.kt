//package com.example.myapplication
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.background
//import androidx.compose.ui.graphics.Color
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.*
//import com.example.myapplication.ui.theme.MyApplicationTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//
//            MyApplicationTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    LocationView()
//                }
//            }
//        }
//    }
//}
//
//
//@Composable
//fun LocationView(){
//    Column(
//        Modifier
//            .padding(16.dp)
//            .fillMaxWidth()
//            .background(color = Color.White)
//    )
//    {
//        Text("Alfred Sisley")
//        Text("3 minutes ago")
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(100.dp)
////                .padding(16.dp)
//                .background(color = Color.LightGray)
//                ,
////            horizontalArrangement = Arrangement.Center ,
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//
//            Column(
//                modifier = Modifier
//                    .padding(16.dp)
//            ){
//                Text("Indore")
//                Text("humidity : 33%")
//            }
//            Text (
//                "24,Smoke",
//                modifier = Modifier
//                    .padding(16.dp),
//                color = Color.Blue
////                color = MaterialTheme.colorScheme.primary
//            )
//        }
//
//
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MyApplicationTheme {
//        LocationView()
//    }
//}

import android.icu.util.Calendar
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.text.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.myapplication.R
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


data class WeatherData(
    val day: String,
//    val weatherIcon: Int, // Resource ID for weather icon
    val icon: Int,
    val date: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    WeatherForecast()
                    ImageLayout()
                }
            }
        }
    }
}


@Composable
fun WeatherForecast() {
    val dateFormat = SimpleDateFormat("dd", Locale.getDefault())
    val calendar = Calendar.getInstance()

    val weekDates = (0 until 7).map {
        val dayOfMonth = dateFormat.format(calendar.time)
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        dayOfMonth
    }
    val weatherList = remember {
        listOf(
//            WeatherData("SAT", R.drawable.ic_cloudy, "21"),
//            WeatherData("SUN", R.drawable.ic_sunny, "22"),
//            WeatherData("MON", R.drawable.ic_rainy, "23"),
//            WeatherData("TUE", R.drawable.ic_cloudy, "24"),
//            WeatherData("WED", R.drawable.ic_sunny, "25"),
//            WeatherData("THU", R.drawable.ic_rainy, "26"),
//            WeatherData("FRI", R.drawable.ic_cloudy, "27")
            WeatherData("SAT",R.drawable.sun,weekDates[0]),
            WeatherData("SUN",R.drawable.cloud ,weekDates[1]),
            WeatherData("MON",R.drawable.storm,weekDates[2]),
            WeatherData("TUE",R.drawable.cloud ,weekDates[3]),
            WeatherData("WED",R.drawable.rain ,weekDates[4]),
            WeatherData("THU",R.drawable.cloud,weekDates[5]),
            WeatherData("FRI",R.drawable.cloud, weekDates[6])
        )
    }

    LazyRow(
        content = {
            items(weatherList) { weatherData ->
                WeatherColumn(weatherData = weatherData)
            }
        }
    )
}

@Composable
fun WeatherColumn(weatherData: WeatherData) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .fillMaxHeight()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(text = weatherData.day, modifier = Modifier.padding(4.dp))
        // Display Icon using Image composable
        Image(
            painter = painterResource(id = weatherData.icon), // Use the custom icon resource
            contentDescription = null, // Provide a proper content description
            modifier = Modifier.size(48.dp) // Adjust the size of the icon as needed
        )

        Text(text = weatherData.date, modifier = Modifier.padding(4.dp))
    }
}

@Composable
fun ImageLayout(){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally ,
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg2) ,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .height(500.dp)
        )
    }
}
@Composable
fun MyBackgroundImage() {
    // Background image resource
    val backgroundImageRes = R.drawable.bg2

    Column(
        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(650.dp)
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = backgroundImageRes),
                contentDescription = null, // Provide an appropriate description
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .alpha(0.8f)
            )
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            color = Color.Red,
//                        shape =
                        )
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    Text(
                        text = "MUMBAI",
                        fontSize = 18.sp,
                        color = Color.White,
                    )
                }
                Row(
                    modifier = Modifier

                        .fillMaxWidth()
//                        .height(200.dp)
                        .border(
                            width = 2.dp,
                            color = Color.Red,
//                        shape =
                        )
                        .padding(16.dp),

                verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "28",
                        fontSize = 72.sp,
                        color = Color.White,
                    )
                    Column (
//                        modifier = Modifier.size(100.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                             "°C",
                            fontSize = 16.sp,
                            color = Color.White,
                        )
                        Text(
                            text = "SUNNY",
                            fontSize = 16.sp,
                            color = Color.White,
                        )
                    }
                    Spacer(modifier = Modifier.width(170.dp))
                    Column (
//                        modifier = Modifier.size(100.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    )
                    {
                        Text(
                            text = "Date",
                            fontSize = 18.sp,
                            color = Color.White,
                        )
                        Text(
                            text = "Time",
                            fontSize = 18.sp,
                            color = Color.Red,
                        )
                    }
                }

            }

            Text(
                text = "Welcome to My App",
                style = MaterialTheme.typography.displayLarge,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )


        }
        WeatherForecast()
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
//        WeatherForecast()
//        ImageLayout()
        MyBackgroundImage()
    }
}
