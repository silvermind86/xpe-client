package br.com.xpe.clientapi.control

import br.com.xpe.clientapi.model.service.ClientService
import br.com.xpe.clientapi.view.request.PostRequest
import br.com.xpe.clientapi.view.request.PutRequest
import br.com.xpe.clientapi.view.response.GetResponse
import br.com.xpe.clientapi.view.response.PostResponse
import br.com.xpe.clientapi.view.response.PutResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ClientController(
    private val clientService: ClientService
) {

    @PostMapping("/clients")
    fun save(
        @RequestBody body: PostRequest
    ): PostResponse =
        clientService.save(body.name, body.email).let {
            PostResponse(it.id, it.name, it.email)
        }

    @GetMapping("/clients")
    fun get(
        @RequestParam id: String? = null,
        @RequestParam name: String? = null,
        @RequestParam email: String? = null
    ): ResponseEntity<GetResponse> =
        clientService.get(id, name, email)?.let { client ->
            ResponseEntity.status(
                HttpStatus.OK
            ).body(
                GetResponse(
                    client.id,
                    client.name,
                    client.email,
                )
            )
        } ?: ResponseEntity.notFound().build()

    @PutMapping("/clients/{clientId}")
    fun put(
        @PathVariable("clientId") id: String,
        @RequestBody body: PutRequest
    ): ResponseEntity<PutResponse> =
        clientService.put(id, body.name, body.email)?.let { client ->
            ResponseEntity.status(
                HttpStatus.ACCEPTED
            ).body(
                PutResponse(
                    client.id,
                    client.name,
                    client.email,
                )
            )
        } ?: ResponseEntity.notFound().build()

    @DeleteMapping("/clients/{clientId}")
    fun delete(
        @PathVariable("clientId") id: String
    ): ResponseEntity<Unit?>? =
        clientService.delete(id)?.let { client ->
            ResponseEntity.accepted().build()
        }

    @GetMapping("/clients/count")
    fun count(): Long = clientService.count()

}
