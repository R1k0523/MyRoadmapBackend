package ru.boringowl.myroadmap.infrastructure.security

import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import ru.boringowl.myroadmap.domain.UserRole
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtRequestFilter(
    private val userDetailsService: UserDetailsService,
    private val jwtUtils: JwtUtils,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val header: String? = request.getHeader(HttpHeaders.AUTHORIZATION)
        run {
            val prefix = "Bearer "
            if (header.isNullOrEmpty() || !header.startsWith(prefix)) return@run

            val token = header.substringAfter(prefix)
            val username = jwtUtils.extractUsername(token)
            val role = jwtUtils.extractRole(token)
            val userDetails = userDetailsService.loadUserByUsername(username)
            if (!jwtUtils.validateToken(token, userDetails)) return@run

            val authentication = UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                when (role) {
                    UserRole.valueOf(userDetails.authorities.first().authority) -> userDetails.authorities
                    else -> setOf(SimpleGrantedAuthority(role.name))
                }
            )
            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }
}
