package ru.boringowl.myroadmap.application.persistence

import org.springframework.data.repository.CrudRepository
import ru.boringowl.myroadmap.infrastructure.jpa.*
import java.util.*

interface BookRepo : CrudRepository<JpaBookInfo, UUID> {
}

