package com.example.cityapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ImageCard(title: String, imageRes: Int? = null, onClick: () -> Unit = {}, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clickable(onClick = onClick)
    ) {
        Column {
            imageRes?.let { res ->
                Image(
                    painter = painterResource(id = res),
                    contentDescription = title,
                    modifier = Modifier.height(180.dp).fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
            Text(text = title, modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally))
        }
    }
}
