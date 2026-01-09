package com.example.myapplication.recipereader.presentation.contacts

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.recipereader.data.repository.ContactsRepository
import com.example.myapplication.recipereader.domain.model.Contact
import com.example.myapplication.recipereader.util.matchesInitials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContactsViewModel(
    private val repository: ContactsRepository
) : ViewModel() {
    private companion object {
        private const val TAG = "ContactsViewModel"
    }

    private val _state = MutableStateFlow(ContactsUiState())
    val state: StateFlow<ContactsUiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ContactsUiEffect>()
    val effect: SharedFlow<ContactsUiEffect> = _effect.asSharedFlow()

    private var allContacts: List<Contact> = emptyList()

    init {
        loadContacts()
    }

    fun onEvent(event: ContactsUiEvent) {
        when (event) {
            ContactsUiEvent.LoadContacts -> loadContacts()
            is ContactsUiEvent.OnSearchQueryChange -> {
                val queryText = event.query.text
                val shouldFilter = event.query.composition == null
                val filtered = if (shouldFilter) {
                    filterContacts(allContacts, queryText)
                } else {
                    _state.value.items
                }
                Log.d(
                    TAG,
                    "Search query='${queryText}' composing=${!shouldFilter} results=${filtered.size}"
                )
                _state.value = _state.value.copy(
                    searchQuery = event.query,
                    items = filtered
                )
            }
            is ContactsUiEvent.OnContactClick -> {
                viewModelScope.launch {
                    _effect.emit(ContactsUiEffect.NavigateToDetail(event.id))
                }
            }
            is ContactsUiEvent.OnToggleFavorite -> {
                toggleFavorite(event.id, event.isFavorite)
            }
        }
    }

    private fun loadContacts() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.loadContacts()
            allContacts = result.contacts
            Log.d(TAG, "Loaded contacts=${allContacts.size} usingMock=${result.isUsingMock}")
            _state.update { current ->
                val filtered = filterContacts(allContacts, current.searchQuery.text)
                current.copy(
                    hasPermission = result.hasPermission,
                    isUsingMock = result.isUsingMock,
                    items = filtered
                )
            }
        }
    }

    private fun toggleFavorite(contactId: String, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setFavorite(contactId, isFavorite)
            allContacts = allContacts.map { contact ->
                if (contact.id == contactId) contact.copy(isFavorite = isFavorite) else contact
            }
            _state.update { current ->
                val filtered = filterContacts(allContacts, current.searchQuery.text)
                current.copy(items = filtered)
            }
        }
    }

    private fun filterContacts(contacts: List<Contact>, query: String): List<Contact> {
        if (query.isBlank()) return contacts
        return contacts.filter { contact ->
            matchesInitials(contact.name, query) ||
                contact.phoneNumber.contains(query, ignoreCase = true)
        }
    }
}
