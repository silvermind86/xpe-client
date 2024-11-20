package br.com.xpe.clientapi.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("client")
data class Client(
    @Id
    val id: String,
    val name: String,
    val email: String
)
