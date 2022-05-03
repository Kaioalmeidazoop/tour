package com.example.tour.model

import javax.persistence.*

@Entity
data class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String? = null,
    val gender: String? = null,
    @Column(name = "CPF", nullable = false)
    val nationalRegistration: String? = null,
    val phone: Long? = null,
    val email: String? = null
) {
    companion object {
        fun updateClient(id: Long, client: Client) = Client(
            id = id,
            name = client.name,
            nationalRegistration = client.nationalRegistration,
            gender = client.gender,
            phone = client.phone,
            email = client.email
        )
    }
}