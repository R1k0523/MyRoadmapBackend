package ru.boringowl.myroadmap.application.services

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.boringowl.myroadmap.application.dto.RegisterData
import ru.boringowl.myroadmap.application.dto.RestorePasswordData
import ru.boringowl.myroadmap.application.persistence.UserRepo
import ru.boringowl.myroadmap.domain.User
import ru.boringowl.myroadmap.domain.UserRole
import ru.boringowl.myroadmap.infrastructure.jpa.JpaUser
import java.util.*

@Service
class UserService(
    val userRepo: UserRepo,
    val passwordEncoder: PasswordEncoder,
) {
    fun get(): List<User?> = 
        userRepo.findAll().toList().map { it.toUser() }

    fun get(id: UUID): User? = 
        userRepo.findById(id).orElse(null)?.toUser()

    fun add(data: RegisterData): User {
        require(!hasUser(data.username)) {"Пользователь с таким именем уже присутствует"}
        val user = JpaUser().apply {
            this.username = data.username
            this.email = data.email
            this.password = passwordEncoder.encode(data.password)
            this.role = UserRole.User
        }
        return userRepo.save(user).toUser()
    }

    fun updatePassword(id: UUID, data: RestorePasswordData) {
        val user: JpaUser? = userRepo.findById(id).orElse(null)
        require(user != null && user.password == passwordEncoder.encode(data.oldPassword)) {"Пароли не совпадают"}
        user.apply {
            this.password = data.newPassword
        }
        userRepo.save(user)
    }

    fun get(username: String): User = 
        userRepo.findByUsername(username)?.toUser() ?: throw Exception("Пользователь не найден")
    
    fun delete(id: UUID) {
        var user = userRepo.findById(id).orElse(null)
        require(user != null) { "Пользователь с таким именем не найден" }
        userRepo.delete(user)
    }

    fun checkCredentials(username: String, password: String): Boolean {
        var user = userRepo.findByUsername(username)
        return user != null && user.password == password
    }

    fun hasUser(username: String): Boolean = userRepo.existsByUsername(username)
}