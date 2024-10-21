package com.dicoding.mushroom.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.mushroom.R
import com.dicoding.mushroom.ui.theme.MushroomTheme


@Composable
fun MushroomItem (
    name: String,
    image: Int,
    edible: String,
    description: String,
    modifier: Modifier = Modifier
){
    Row(
        modifier
            .padding(start = 10.dp, top = 4.dp, end = 16.dp, bottom = 4.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier) {

            Image(
                painter = painterResource(image),
                contentDescription = name,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(110.dp)
                    .clip(MaterialTheme.shapes.small)
                    .padding(end = 8.dp)
            )
            Box(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.small)
                    .padding(8.dp)
                    .align(Alignment.BottomEnd)
                    .background(
                        MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
                    )
            )
        }

        Column {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = edible,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                modifier = modifier
                    .padding(bottom = 4.dp)
            )
            Text(
                text = description,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Normal,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }

}

@Composable
@Preview(showBackground = true)
fun MushroomItemPreview() {
    MushroomTheme {
        MushroomItem( "Jamur Tiram", R.drawable.jamurtiram1, "Edible", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
    }
}