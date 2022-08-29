package com.piroworkz.architectcoders.app.data.remote

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.piroworkz.architectcoders.app.ui.common.tryCatch
import com.piroworkz.architectcoders.domain.Error
import kotlinx.coroutines.tasks.await

suspend fun FirebaseFirestore.getCollection(path: String): QuerySnapshot? =
    tryCatch { collection(path).get().await() }
        .fold(
            ifLeft = { null },
            ifRight = { it }
        )

suspend fun FirebaseFirestore.getDocumentById(
    path: String,
    doc: String
): DocumentSnapshot? =
    tryCatch { collection(path).document(doc).get().await() }.fold(
        ifLeft = { null },
        ifRight = { it }
    )

suspend fun <T : Any> FirebaseFirestore.saveDocument(
    path: String,
    doc: String,
    entry: T
): Error? =
    tryCatch { collection(path).document(doc).set(entry).await() != null }
        .fold(
            ifLeft = { it },
            ifRight = { null }
        )