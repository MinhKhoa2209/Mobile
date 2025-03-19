package com.example.businesscardapp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BusinessCard() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
            BasicInfo()
            Spacer(modifier = Modifier.height(32.dp))
            InfoContact()
        }
    }
}

@Composable
fun BasicInfo() {
    Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.anime),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Minh Khoa",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )
        Text(
            fontFamily = FontFamily.Monospace,
            text = "Student",
            color = Color.Gray,
            fontSize = 20.sp
        )
    }
}

@Composable
fun InfoContact() {
    Column(modifier = Modifier.padding(16.dp)) {
        ContactItem(Icons.Default.Phone, "+00 (00) 000 000")
        ContactItem(Icons.Default.Share, "@socialmediahandle")
        ContactItem(Icons.Default.Email, "email@domain.com")
    }
}

@Composable
fun ContactItem(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String) {
    Row(modifier = Modifier.padding(vertical = 8.dp)) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = icon,
            contentDescription = null,
            tint = Color.Black
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, fontSize = 18.sp, color = Color.Black)
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    BusinessCard()
}