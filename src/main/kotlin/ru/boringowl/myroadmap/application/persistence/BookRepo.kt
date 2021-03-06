package ru.boringowl.myroadmap.application.persistence

import org.springframework.data.jpa.repository.JpaRepository
import ru.boringowl.myroadmap.infrastructure.jpa.JpaBookInfo
import java.util.*

interface BookRepo : JpaRepository<JpaBookInfo, UUID> {
}

