
import android.icu.util.Calendar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow

import androidx.compose.ui.graphics.ColorFilter

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale



data class WeatherData(
    val day: String,
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
                    ImageLayout()
                }
            }
        }
    }
}


//@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherForecast() {

    val dateFormat = SimpleDateFormat("dd", Locale.getDefault())
    val dayOfWeekFormat = SimpleDateFormat("EEE", Locale.getDefault())
    val calendar = Calendar.getInstance()
    val today = Date()

    calendar.time = today
    val currentDay = dayOfWeekFormat.format(calendar.time)

    val weekDays = mutableListOf<String>()
    val weekDates = mutableListOf<String>()
    var daysInWeek = 0

    while (daysInWeek < 7) {
        val dateOfWeek = dateFormat.format(calendar.time)
        val dayOfWeek = dayOfWeekFormat.format(calendar.time)
        weekDays.add(dayOfWeek)
        weekDates.add(dateOfWeek)

        calendar.add(Calendar.DAY_OF_MONTH, 1)
        daysInWeek++
    }
    val temperatures = mutableListOf("28", "31", "31", "32", "31" , "25" ,"21")
    val weatherList = remember {
        listOf(
            WeatherData(weekDays[0], R.drawable.sun, temperatures[0]),
            WeatherData(weekDays[1], R.drawable.cloud, temperatures[1]),
            WeatherData(weekDays[2], R.drawable.storm, temperatures[2]),
            WeatherData(weekDays[3], R.drawable.cloud, temperatures[3]),
            WeatherData(weekDays[4], R.drawable.rain, temperatures[4]),
            WeatherData(weekDays[5], R.drawable.cloud, temperatures[5]),
            WeatherData(weekDays[6], R.drawable.cloud, temperatures[6])
        )
    }

    LazyRow(
        content = {
            items(weatherList) { weatherData ->
                WeatherColumn(weatherData = weatherData, currentDay)
            }
        }
    )
}

@Composable
fun WeatherColumn(weatherData: WeatherData, currentDay: String) {

    val iconTint = if (weatherData.day == currentDay) Color.Red else Color.Black
    val textColor = if (weatherData.day == currentDay) Color.Red else Color.Black

    Column(
        modifier = Modifier
            .width(100.dp)
            .fillMaxHeight()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = weatherData.day, modifier = Modifier.padding(4.dp),
            color = textColor
        )
        // Display Icon using Image composable
        Image(
            painter = painterResource(id = weatherData.icon), // Use the custom icon resource
            contentDescription = null, // Provide a proper content description
            modifier = Modifier.size(48.dp),// Adjust the size of the icon as needed
            colorFilter = ColorFilter.tint(iconTint)
        )

        Text(
            text = weatherData.date,
            modifier = Modifier.padding(4.dp),
            color = textColor
        )
    }
}

@Composable
fun ImageLayout() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg2),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .height(500.dp)
        )
    }
}

//@RequiresApi(Build.VERSION_CODES.O)
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
                        .padding(16.dp)
                        ,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                        Text(
                            text = "MUMBAI",
                            fontSize = 18.sp,
                            color = Color.White,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.Build,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier

                        .fillMaxWidth()
                        .padding(16.dp)
                        ,

                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "28",
                        fontSize = 80.sp,
                        color = Color.White,
                    )
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "°C",
                            fontSize = 18.sp,
                            color = Color.White,
                        )
                        Text(
                            text = "SUNNY",
                            fontSize = 18.sp,
                            color = Color.White,
                        )
                    }
                    Spacer(modifier = Modifier.width(80.dp))
                    FormatDateTime()
                }
            }
        }
        WeatherForecast()
    }
}

@Composable
fun FormatDateTime() {

    val dateFormat = SimpleDateFormat("dd", Locale.getDefault())
    val dayOfWeekFormat = SimpleDateFormat("EEE", Locale.getDefault())
    val calendar = Calendar.getInstance()
    val today = Date()

    calendar.time = today
    val currentDate = dateFormat.format(calendar.time)
    val currentDay = dayOfWeekFormat.format(calendar.time)

    val currentTime = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())
    Column(
        verticalArrangement = Arrangement.SpaceBetween ,
        modifier = Modifier.padding(start=30.dp)
    ) {


        Text(
            text = "$currentDate Oct,$currentDay",
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = " $currentTime",
            fontSize = 18.sp,
            color = Color.Red,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun MainRow(city: String, humidity: String, temperature: String) {
    var isClicked by remember { mutableStateOf(false) }

    val backgroundColor = if (isClicked) {
        Color(0xFF834ECE) // Change to your desired color
    } else {
        Color.White // Default background color
    }
    val textColor = if (isClicked) {
        Color.Magenta // Text color when clicked
    } else {
        Color(0xFF713ABE)// Default text color
    }
    val cityColor = if (isClicked) {
        Color.White // Text color when clicked
    } else {
        Color.Black // Default text color
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(if (isClicked) Modifier.shadow(20.dp) else Modifier)

            .background(backgroundColor)

            .clickable {
                isClicked = !isClicked
            }
            .padding(24.dp)
            .height(60.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column{
            Text(
                text = city,
                fontSize = 24.sp,
                color = cityColor,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Medium,
            )
            Text(
                "Humidity: $humidity", color = Color(0xFF938F9B),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
        Text(
            temperature,
            color = textColor,
            fontWeight = FontWeight.Medium,

            )
    }
}

@Composable
fun LocationPage() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()

//                .border(width = 1.dp, color = Color.Blue)
        ) {
            Column(
                modifier = Modifier
                    .weight(3f)
                    .padding(10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly

            ) {
                Row(
                    modifier = Modifier

                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp)

                    )
                    Text(
                        "LOCATIONS",
                        fontSize = 22.sp,
                        modifier = Modifier.padding(start = 10.dp),
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier
                            .size(28.dp, 50.dp) // Set the initial size
                            .rotate(90f) // Rotate the icon 90 degrees clockwise
                            .padding(2.dp) // Add padding to maintain aspect ratio
                    )
                }

                Text(
                    "You are currently getting results " +
                            "for popular places from India",
                    modifier = Modifier.padding(14.dp),
                    color = Color.Black,

                    )
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color.LightGray, Color(0xFF713ABE)),
                    modifier = Modifier
                        .padding(16.dp)
                        .height(50.dp),


                    )
                {
                    Text(
                        "Choose Place",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )


                }
            }

            Column(
                modifier = Modifier
                    .weight(1.5f)
                    .fillMaxHeight()
                    .background(color = Color.LightGray),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(28.dp)

                )
                Text(
                    "ADD PLACE",
                    fontSize = 14.sp,
//                    modifier = Modifier.margin
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                )
            }
        }

        Divider(
            color = Color.Gray,
            thickness = 1.dp,
        )
        MainRow("Mumbai", "51%", "28, Sunny")
        MainRow("Indore", "35%", "24, Smoke")
        MainRow("Bhopal", "35%", "21, Clear")
    }
}


@Preview(showBackground = true)
@Composable
fun Preview2() {
    MyApplicationTheme {
//        WeatherForecast()
//        ImageLayout()
        MyBackgroundImage()
//        LocationPage()
//        MainRow("Bhopal", "35%", "21, Clear")

    }
}
