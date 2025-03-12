package com.example.activitylifecycle


import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.activitylifecycle.model.Dessert
import com.example.activitylifecycle.ui.theme.ActivityLifecycleTheme
import com.example.activitylifecycle.data.DataSource

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate Called")
        setContent {
            ActivityLifecycleTheme {
                Surface(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
                    DessertClickerApp(DataSource.dessertList)
                }
            }
        }
    }

    override fun onStart() { super.onStart(); Log.d(TAG, "onStart Called") }
    override fun onResume() { super.onResume(); Log.d(TAG, "onResume Called") }
    override fun onRestart() { super.onRestart(); Log.d(TAG, "onRestart Called") }
    override fun onPause() { super.onPause(); Log.d(TAG, "onPause Called") }
    override fun onStop() { super.onStop(); Log.d(TAG, "onStop Called") }
    override fun onDestroy() { super.onDestroy(); Log.d(TAG, "onDestroy Called") }
}

fun determineDessertToShow(desserts: List<Dessert>, dessertsSold: Int) =
    desserts.lastOrNull { dessertsSold >= it.startProductionAmount } ?: desserts.first()

private fun shareSoldDessertsInformation(context: Context, dessertsSold: Int, revenue: Int) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, context.getString(R.string.share_text, dessertsSold, revenue))
        type = "text/plain"
    }
    try {
        ContextCompat.startActivity(context, Intent.createChooser(intent, null), null)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(context, context.getString(R.string.sharing_not_available), Toast.LENGTH_LONG).show()
    }
}

@Composable
private fun DessertClickerApp(desserts: List<Dessert>) {
    var revenue by remember  { mutableStateOf(0) }
    var dessertsSold by remember  { mutableStateOf(0) }
    var currentDessert by remember { mutableStateOf(desserts.first()) }

    Scaffold(
        topBar = {
            val context = LocalContext.current
            val layoutDirection = LocalLayoutDirection.current
            DessertClickerAppBar {
                shareSoldDessertsInformation(context, dessertsSold, revenue)
            }
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            DessertClickerScreen(revenue, dessertsSold, currentDessert.imageId) {
                revenue += currentDessert.price
                dessertsSold++
                currentDessert = determineDessertToShow(desserts, dessertsSold)
            }
        }
    }
}

@Composable
private fun DessertClickerAppBar(onShareButtonClicked: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.primary).padding(dimensionResource(R.dimen.padding_medium)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(stringResource(R.string.app_name), color = MaterialTheme.colorScheme.onPrimary, style = MaterialTheme.typography.titleLarge)
        IconButton(onClick = onShareButtonClicked) {
            Icon(Icons.Filled.Share, contentDescription = stringResource(R.string.share), tint = MaterialTheme.colorScheme.onPrimary)
        }
    }
}

@Composable
fun DessertClickerScreen(revenue: Int, dessertsSold: Int, @DrawableRes dessertImageId: Int, onDessertClicked: () -> Unit) {
    Box {
        Image(painter = painterResource(R.drawable.bakery_back), contentDescription = null, contentScale = ContentScale.Crop)
        Column {
            Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                Image(
                    painter = painterResource(dessertImageId),
                    contentDescription = null,
                    modifier = Modifier.size(dimensionResource(R.dimen.image_size)).align(Alignment.Center).clickable { onDessertClicked() },
                    contentScale = ContentScale.Crop
                )
            }
            TransactionInfo(revenue, dessertsSold)
        }
    }
}

@Composable
private fun TransactionInfo(revenue: Int, dessertsSold: Int) {
    Column(modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer).fillMaxWidth().padding(dimensionResource(R.dimen.padding_medium))) {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Text(stringResource(R.string.dessert_sold), style = MaterialTheme.typography.titleLarge)
            Text(dessertsSold.toString(), style = MaterialTheme.typography.titleLarge)
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Text(stringResource(R.string.total_revenue), style = MaterialTheme.typography.headlineMedium)
            Text("$$revenue", style = MaterialTheme.typography.headlineMedium, textAlign = TextAlign.Right)
        }
    }
}

@Preview
@Composable
fun MyDessertClickerAppPreview() {
    ActivityLifecycleTheme { DessertClickerApp(listOf(Dessert(R.drawable.cupcake, 5, 0))) }
}
