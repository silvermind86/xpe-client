package br.com.xpe.clientapi.model.service.impl

import br.com.xpe.clientapi.model.dto.DeletedClient
import br.com.xpe.clientapi.model.dto.NewClient
import br.com.xpe.clientapi.model.dto.ViewClient
import br.com.xpe.clientapi.model.entity.Client
import br.com.xpe.clientapi.model.repository.ClientRepository
import br.com.xpe.clientapi.model.service.ClientService
import org.springframework.stereotype.Component
import java.util.Optional
import java.util.UUID
import kotlin.jvm.optionals.getOrNull

@Component
class ClientServiceImpl(
    private val clientRepository: ClientRepository
): ClientService {

    override fun save(
        name: String,
        email: String
    ): NewClient =
        clientRepository.save(
            Client(
                UUID.randomUUID().toString(),
                name,
                email
            )
        ).let { client -> NewClient(client.id,client.name,client.email) }

    override fun get(
        id: String?,
        name: String?,
        email: String?
    ): ViewClient? = when {
        id != null -> clientRepository.findById(id)
        name != null -> clientRepository.findByName(name)
        email != null -> clientRepository.findByEmail(email)
        else -> Optional.empty<Client>()
    }.let { optional ->
        if (optional.isEmpty) {
            null
        } else {
            ViewClient(
                optional.get().id,
                optional.get().name,
                optional.get().email,
            )
        }
    }

    override fun put(
        id: String,
        name: String,
        email: String
    ): ViewClient? =
        clientRepository.findById(id).getOrNull()?.let { client ->
            clientRepository.save(
                client.copy(
                    name = name,
                    email = email
                )
            ).let { savedClient ->
                ViewClient(
                    savedClient.id,
                    savedClient.name,
                    savedClient.email
                )
            }
        }

    override fun delete(
        id: String
    ): DeletedClient? =
        clientRepository.findById(id).getOrNull()?.let { client ->
            clientRepository.delete(client)
            DeletedClient(id)
        }

    override fun count(): Long =
        clientRepository.count()

}
