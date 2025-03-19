package com.example.bookshelf.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookShelfApplication
import com.example.bookshelf.data.BookRepository
import com.example.bookshelf.model.BookItem
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface BooksUiState {
    data class Success(val books: List<BookItem>) : BooksUiState
    object Error : BooksUiState
    object Loading : BooksUiState
}

class BooksViewModel(private val bookRepository: BookRepository) : ViewModel() {

    var booksUiState: BooksUiState by mutableStateOf(BooksUiState.Loading)
        private set

    init {
        getBooks()
    }

    fun getBooks() {
        viewModelScope.launch {
            booksUiState = BooksUiState.Loading
            booksUiState = try {
                BooksUiState.Success(bookRepository.getBooks("android"))
            } catch (e: IOException) {
                BooksUiState.Error
            } catch (e: HttpException) {
                BooksUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as BookShelfApplication)
                val bookRepository = application.container.bookRepository
                BooksViewModel(bookRepository = bookRepository)
            }
        }
    }
}