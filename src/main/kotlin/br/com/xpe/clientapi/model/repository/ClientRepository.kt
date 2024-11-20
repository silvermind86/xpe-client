package br.com.xpe.clientapi.model.repository

import br.com.xpe.clientapi.model.entity.Client
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.Optional

interface ClientRepository: MongoRepository<Client, String>{

    fun findByName(name: String): Optional<Client>

    fun findByEmail(email: String): Optional<Client>

}
