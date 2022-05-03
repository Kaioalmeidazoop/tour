package com.example.tour.service

import com.example.tour.model.Client
import com.example.tour.repository.ClientRepository

class ClientService(
    private val clientRepository: ClientRepository
){
    fun saveClient(client: Client){
        clientRepository.save(client)
    }

    fun getClientById(id: Long): Client? {
        return clientRepository.findById(id).orElseGet(null)
    }

    fun deleteClient(id: Long) {
        clientRepository.deleteById(id)
    }

    fun updateClient(id: Long, client: Client) {
        val updateClient = Client.updateClient(id, client)
        saveClient(updateClient)
    }

    fun findClientByEmail(email: String): Client {
        return clientRepository.findByEmail(email)
    }

    fun getAllClients(): List<Client> {
        return clientRepository.findAll().toList()
    }

    fun count(): Long = clientRepository.count()

}