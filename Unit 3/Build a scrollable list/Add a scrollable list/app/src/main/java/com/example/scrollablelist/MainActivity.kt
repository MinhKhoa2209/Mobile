package com.example.scrollablelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scrollablelist.data.Datasource
import com.example.scrollablelist.model.Affirmation
import com.example.scrollablelist.ui.theme.ScrollableListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScrollableListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AffirmationList(affirmations = Datasource().loadAffirmations())
                }
            }
        }
    }
}

@Composable
fun AffirmationList(affirmations: List<Affirmation>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "List Item Card",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 20.dp),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.padding(8.dp)) {
            items(affirmations) { affirmation ->
                AffirmationCard(affirmation)
            }
        }
    }
}

@Composable
fun AffirmationCard(affirmation: Affirmation) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = affirmation.imageResourceId),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop

            )
            Text(
                text = stringResource(id = affirmation.stringResourceId),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp).align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAffirmationList() {
    ScrollableListTheme {
        AffirmationList(affirmations = Datasource().loadAffirmations())
    }
}
