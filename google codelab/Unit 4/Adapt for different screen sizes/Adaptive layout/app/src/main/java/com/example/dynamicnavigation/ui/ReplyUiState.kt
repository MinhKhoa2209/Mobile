package com.example.dynamicnavigation.ui

import com.example.dynamicnavigation.data.Email
import com.example.dynamicnavigation.data.MailboxType
import com.example.dynamicnavigation.data.local.LocalEmailsDataProvider

data class ReplyUiState(
    val mailboxes: Map<MailboxType, List<Email>> = emptyMap(),
    val currentMailbox: MailboxType = MailboxType.Inbox,
    val currentSelectedEmail: Email = LocalEmailsDataProvider.defaultEmail,
    val isShowingHomepage: Boolean = true
) {
    val currentMailboxEmails: List<Email> by lazy { mailboxes[currentMailbox]!! }
}
