package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier){
   val artworks: Array<Artwork> = arrayOf(
       Artwork( title = "After the rain", artist = "Theodore Rousseau", year = 1850, image = R.drawable.after_the_rain__1850__theodore_rousseau),
       Artwork( title = "A North East Headland", artist = "Childe Hassam", year = 1901, image = R.drawable.a_north_east_headland__1901__childe_hassam),
       Artwork( title = "The Seine", artist = "Henry Ossawa Tanner", year = 1902, image = R.drawable.the_seine__1902_henry_ossawa_tanner),
       Artwork( title = "Vase of Flowers", artist = "Paul Cezanne", year = 1903, image = R.drawable.vase_of_flowers__1903_paul_cezanne),
       Artwork( title = "Waterloo Bridge, London at Dusk", artist = "Claude Monet", year = 1904, image = R.drawable.waterloo_bridge__london__at_dusk__1904__claude_monet)
   )

    var currentArtworkIndex by remember { mutableIntStateOf(0) }
    val artwork =  artworks[currentArtworkIndex]
    Column(
        modifier = modifier
            .statusBarsPadding()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            Modifier.weight(5f),
            verticalArrangement = Arrangement.Center

        ) {
            ArtworkImage(artwork = artwork)
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            ArtworkCard(artwork = artwork)
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(bottom = 24.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            ArtSpaceButtons(
                onPreviousButtonClicked = {
                    currentArtworkIndex = if(currentArtworkIndex - 1 > 0)
                        currentArtworkIndex - 1
                    else
                        artworks.size - 1
                },
                onNextButtonClicked = { currentArtworkIndex = (currentArtworkIndex + 1) % artworks.size }
            )
        }
    }
}

@Composable
fun ArtworkImage( modifier : Modifier = Modifier, artwork: Artwork ){
    Surface(
        color = Color(0xFFFEFBFF),
        shadowElevation = 8.dp
    ) {
        Image(
            modifier = Modifier.padding(24.dp),
            painter = painterResource(id = artwork.image),
            contentDescription = null
        )
    }
}

@Composable
fun ArtworkCard( modifier : Modifier = Modifier, artwork: Artwork){
    Surface(
        color = Color(0xFFE8EAED),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(text = artwork.title, fontSize = 24.sp, color = Color.Black)
            Text(text = buildAnnotatedString
                {
                    pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                    append(artwork.artist)
                    pop()
                    append(" (${artwork.year})")
                    toAnnotatedString()
                },
                color = Color.Black
            )
        }
    }
}

@Composable
fun ArtSpaceButtons(
    modifier : Modifier = Modifier,
    onPreviousButtonClicked : () -> Unit,
    onNextButtonClicked : () -> Unit
){
    Row(
        modifier = modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ){
       ArtSpaceButton(
           text = R.string.previous_button_text,
           onClick = onPreviousButtonClicked
       )
        ArtSpaceButton(
            text = R.string.next_button_text,
            onClick = onNextButtonClicked
        )
    }
}

@Composable
fun ArtSpaceButton(
    modifier : Modifier = Modifier,
    @StringRes text : Int,
    onClick : () -> Unit){
    Button(
        modifier = Modifier
            .wrapContentWidth(Alignment.CenterHorizontally, true)
            .width(148.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF495D92))
    ) {
        Text( text = stringResource(text), color = Color.White )
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview(){
    ArtSpaceTheme {
        ArtSpaceApp(modifier = Modifier.fillMaxSize())
    }
}
