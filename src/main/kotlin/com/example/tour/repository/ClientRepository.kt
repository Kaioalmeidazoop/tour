package com.example.tour.repository

import com.example.tour.model.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : CrudRepository<Client, Long> {
    fun findByEmail(email: String): Client
}