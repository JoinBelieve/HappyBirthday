package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    CreateCardApp()
                }
            }
        }
    }
}

/**
 * Create card app
 *
 */

@Composable
fun CreateCardApp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFDCF1E2)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(2f)
                .padding(20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .background(color = Color(0xFF3ddc84))
                    .width(150.dp)
                    .height(150.dp)
            )
            Text(
                text = "Jennifer Doe",
                fontSize = 35.sp,
                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
            )
            Text(
                text = "Android Developer Extraordinaire",
                fontSize = 18.sp,
                color = Color(0xFF009688),
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .weight(1f)
                .padding(30.dp)
        ) {
            repeat(3) {
                CreateRomContent(text = "  +00() 0000 0000")
            }
        }
    }

}

@Composable
fun CreateRomContent(text: String) {
    Row(modifier = Modifier.padding(8.dp)) {
        Icon(
            painter = painterResource(id = androidx.core.R.drawable.ic_call_answer),
            contentDescription = null,
            tint = Color(0xFF009688),
            modifier = Modifier
                .width(20.dp)
                .height(20.dp)
        )
        Text(text = text, color = Color(0xFF0D0E0D))
    }
}

@Composable
fun ComposeQuadrantApp() {
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.weight(1f)) {
            ComposableInfoCard(
                title = "Text composable",
                content = "Displays text and follows the recommended Material Design guidelines.",
                backgroundColor = Color(0xFFEADDFF),
                modifier = Modifier.weight(1f)
            )
            ComposableInfoCard(
                title = "Image composable",
                content = "Creates a composable that lays out and draws a given Painter class object.",
                backgroundColor = Color(0xFFD0BCFF),
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            ComposableInfoCard(
                title = "Row composable",
                content = "A layout composable that places its children in a horizontal sequence.",
                backgroundColor = Color(0xFFB69DF8),
                modifier = Modifier.weight(1f)
            )
            ComposableInfoCard(
                title = "Column composable",
                content = "A layout composable that places its children in a vertical sequence.",
                backgroundColor = Color(0xFFF6EDFF),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun ComposableInfoCard(
    title: String, content: String, modifier: Modifier = Modifier, backgroundColor: Color
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = content, textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    HappyBirthdayTheme {
        CreateCardApp()
    }
}