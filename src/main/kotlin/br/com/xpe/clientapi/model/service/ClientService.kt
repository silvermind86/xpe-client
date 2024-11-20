package br.com.xpe.clientapi.model.service

import br.com.xpe.clientapi.model.dto.DeletedClient
import br.com.xpe.clientapi.model.dto.NewClient
import br.com.xpe.clientapi.model.dto.ViewClient


interface ClientService {

    fun save(name: String, email: String): NewClient

    fun get(id: String?, name: String?, email: String?): ViewClient?

    fun put(id: String, name: String, email: String): ViewClient?

    fun delete(id: String): DeletedClient?

    fun count(): Long

}
