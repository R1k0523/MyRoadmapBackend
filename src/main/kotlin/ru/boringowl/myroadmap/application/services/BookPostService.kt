package ru.boringowl.myroadmap.application.services

import org.springframework.stereotype.Service
import ru.boringowl.myroadmap.application.persistence.BookPostRepo
import ru.boringowl.myroadmap.domain.BookPost
import ru.boringowl.myroadmap.domain.Route
import ru.boringowl.myroadmap.infrastructure.jpa.JpaBookPost
import ru.boringowl.myroadmap.infrastructure.jpa.JpaRoute
import java.util.*

@Service
class BookPostService(val bookRepo: BookPostRepo) : BaseService<BookPost, JpaBookPost, UUID>(bookRepo) {
    override fun toJpa(dto: BookPost): JpaBookPost? = JpaBookPost(dto)
    override fun toDto(jpa: JpaBookPost?): BookPost? = jpa?.toBookPost()
    override fun getId(dto: BookPost): UUID? = dto.bookPostId
    fun addByRoute(dto: BookPost, route: Route) {
        val jpa = toJpa(dto)
        jpa?.let {
            it.route = JpaRoute(route)
            bookRepo.save(it)
        }
    }
    fun isExists(desc: String) = bookRepo.existsByDescription(desc)
    fun getByRoute(routeId: Int) = bookRepo.findAllByRoute_RouteId(routeId).map { toDto(it)!! }
}
