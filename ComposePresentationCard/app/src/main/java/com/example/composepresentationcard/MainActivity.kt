package com.example.composepresentationcard

import com.example.composepresentationcard.ui.theme.AndroidDarkGreen;
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composepresentationcard.ui.theme.ComposePresentationCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePresentationCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PresentationCardComponent()
                }
            }
        }
    }
}

@Composable
fun PresentationCardComponent(){
    Column(
        modifier =  Modifier
            .background(Color(0xFFD2E8D4))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Bottom
        ) {
            MainSection()
        }

        Column(
            modifier = Modifier
                .padding(bottom = 48.dp)
                .weight(1f),
            verticalArrangement = Arrangement.Bottom
        ) {
            ContactSection()
        }
    }
}

@Composable
fun MainSection(modifier : Modifier = Modifier){
    val image = painterResource(id = R.drawable.android_logo)
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Image(
            modifier = Modifier
                .width(128.dp)
                .background(Color(0xFF073042)),
            painter = image,
            contentDescription = stringResource(R.string.logo_description)
        )
        Text(
            text = stringResource(id = R.string.presentation_name),
            fontSize = 48.sp,
            color = Color.Black
        )
        Text(
            text = stringResource(id = R.string.presentation_title),
            color = AndroidDarkGreen,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ContactSection(modifier : Modifier = Modifier){

    Column( verticalArrangement = Arrangement.spacedBy(16.dp)) {
        ContactItem(
            icon = Icons.Filled.Phone,
            description = stringResource(R.string.phone_icon_description),
            contactInformation = stringResource(R.string.phone_number)
        )
        ContactItem(
            icon = Icons.Filled.Share,
            description = stringResource(R.string.social_media_icon_description),
            contactInformation = stringResource(R.string.social_media_handler)
        )
        ContactItem(
            icon = Icons.Filled.Email,
            description = stringResource(R.string.email_icon_description),
            contactInformation = stringResource(R.string.email)
        )
    }


}

@Composable
fun ContactItem(modifier : Modifier = Modifier, icon: ImageVector, description: String? = null, contactInformation: String){
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Icon(
            imageVector = icon,
            contentDescription = description,
            tint = Color(0xFF147646)
        )
        Text(
            text = contactInformation,
            color = Color.Black
        )
    }
}

@Composable
@Preview(showBackground = true)
fun MainSectionPreview(){
    ComposePresentationCardTheme {
        MainSection()
    }
}

@Composable
fun ContactItemPreview(){
    ComposePresentationCardTheme {
        ContactItem(
            icon = Icons.Filled.Email,
            contactInformation = "adrianpinto34@gmail.com"
        )
    }
}


@Composable
fun ContactSectionPreview(){
    ComposePresentationCardTheme{
        ContactSection()
    }
}

@Preview(showBackground = true)
@Composable
fun PresentationCardPreview() {
    ComposePresentationCardTheme {
        PresentationCardComponent()
    }
}