package com.example.tour.controller

import com.example.tour.facade.ClientFacade
import com.example.tour.model.Client
import com.example.tour.model.ResponseJSON
import com.example.tour.service.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@RestController
@RequestMapping(value = ["/clients"])
class ClientController(
    private val clientFacade: ClientFacade
) {

    @GetMapping("/{id}")
    fun getId(@PathVariable id: Long): ResponseEntity<Client?> {
        var client = clientFacade.getClientById(id)
        var status = if (client == null) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(client, status)
    }

    @PostMapping()
    fun save(@RequestBody client: Client): ResponseEntity<ResponseJSON>{
        clientFacade.saveClient(client)
        val responseJSON = ResponseJSON("Created Client")
        return ResponseEntity(responseJSON, HttpStatus.CREATED)
    }
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if(clientFacade.getClientById(id) != null){
            status = HttpStatus.ACCEPTED
            clientFacade.deleteClient(id)
        }
        return ResponseEntity(Unit, status)
    }
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody client: Client): ResponseEntity<Unit>{
        var status = HttpStatus.NOT_FOUND
        if(clientFacade.getClientById(id) != null){
            clientFacade.updateClient(id, client)
            status = HttpStatus.ACCEPTED
        }
        return ResponseEntity(Unit, status)
    }
    @GetMapping()
    fun getAll(): ResponseEntity<List<Client>> {
        val listClients = clientFacade.getAllClients()
        val status = if(listClients.size == 0) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(listClients, status)
    }
    @GetMapping("/count")
    fun count(): ResponseEntity<Long> =
        ResponseEntity.ok().body(clientFacade.count())
}