package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme


class ComposeArticle : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                {
                    composeArticle()
                }
            }
        }
    }
}

@Composable
fun composeArticle() {
    Column(modifier = Modifier)
    {
        Image(
            painter = painterResource(R.drawable.bg_compose_background),
            contentDescription = null
        )
        Text(
            text = "Jetpack Compose Title",
            modifier = Modifier.padding(16.dp),
            fontSize = 24.sp
        )
        Text(
            text = "Jetpack Compose is a modern toolkit for building native Android UI." +
                    " Compose simplifies and accelerates UI development on Android with less code, " +
                    "powerful tools, and intuitive Kotlin APIs.",
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Justify
        )
        Text(
            text ="In this tutorial, you build a simple UI component with declarative functions. " +
                    "You call Compose functions to say what elements you want and the Compose " +
                    "compiler does the rest. Compose is built around Composable functions. " +
                    "These functions let you define your app\\'s UI programmatically because they " +
                    "" +
                    "let you describe how it should look and provide data dependencies, " +
                    "rather than focus on the process of the UI\\'s construction, such as initializing" +
                    " an element and then attaching it to a parent. To create a Composable function," +
                    " you add the @Composable annotation to the function name.",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun articlePreview() {
    MyApplicationTheme {
        composeArticle()
    }
}
