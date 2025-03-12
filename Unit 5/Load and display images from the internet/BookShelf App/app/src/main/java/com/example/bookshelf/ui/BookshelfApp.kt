package com.example.bookshelf.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bookshelf.viewmodel.BooksViewModel

@Composable
fun BookshelfApp(viewModel: BooksViewModel = hiltViewModel()) {
    val books by viewModel.books.collectAsState()

    LaunchedEffect(Unit) { viewModel.fetchBooks("android") }

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(books.size) { index ->
            val book = books[index]
            Column(modifier = Modifier.padding(8.dp)) {
                book.volumeInfo.imageLinks?.thumbnail?.let { url ->
                    Image(
                        painter = rememberImagePainter(url),
                        contentDescription = book.volumeInfo.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.height(150.dp).fillMaxWidth()
                    )
                }
                Text(text = book.volumeInfo.title, modifier = Modifier.padding(8.dp))
            }
        }
    }
}
