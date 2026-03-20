package com.scooter.shadowbox.data

import java.util.UUID

data class SSHConnection(
    val id: String = UUID.randomUUID().toString(),

    val name: String,
    val host: String,
    val port: Int = 22,
    val username: String,
    val password: String? = null,
    val privateKeyPath: String? = null
)
