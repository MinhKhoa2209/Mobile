package com.example.bookshelf.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.R
import com.example.bookshelf.model.BookItem
import com.example.bookshelf.model.VolumeInfo
import com.example.bookshelf.model.ImageLinks
import com.example.bookshelf.ui.theme.BookShelfTheme
import com.example.bookshelf.viewmodel.BooksUiState

@Composable
fun BookScreen(
    booksUiState: BooksUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    when (booksUiState) {
        is BooksUiState.Loading -> LoadingScreen(modifier.size(200.dp))
        is BooksUiState.Success -> BooksListScreen(
            books = booksUiState.books,
            modifier = modifier.padding(dimensionResource(R.dimen.padding_medium)),
            contentPadding = contentPadding
        )

        else -> ErrorScreen(retryAction, modifier)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading),
        modifier = modifier
    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.loading_failed))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun BookShelfCard(book: BookItem, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = book.volumeInfo?.title ?: "No Title",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            val imageUrl = book.volumeInfo?.imageLinks?.thumbnail
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.bookshelf_image),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp),
                placeholder = painterResource(id = R.drawable.loading_img),
                error = painterResource(id = R.drawable.ic_broken_image)
            )
            Text(
                text = book.volumeInfo?.publishedDate ?: "Unknown Publish Date",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun BooksListScreen(
    books: List<BookItem>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = books,
            key = { book -> book.volumeInfo?.title ?: "Unknown" }
        ) { book ->
            BookShelfCard(book = book, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    BookShelfTheme {
        LoadingScreen(Modifier.fillMaxSize().size(200.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    BookShelfTheme {
        ErrorScreen({}, Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true)
@Composable
fun BooksListScreenPreview() {
    BookShelfTheme {
        val mockData = List(10) {
            BookItem(
                volumeInfo = VolumeInfo(
                    title = "Sample Book $it",
                    authors = listOf("Author $it"),
                    imageLinks = ImageLinks(thumbnail = "http://books.google.com/books/content?id=eR4kCQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api")
                )
            )
        }
        BooksListScreen(mockData, Modifier.fillMaxSize())
    }
}
