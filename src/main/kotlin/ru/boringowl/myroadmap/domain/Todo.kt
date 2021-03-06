package ru.boringowl.myroadmap.domain


import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
class Todo() {
    var todoId: UUID? = null
    var header = ""
    @JsonIgnore
    var user: User? = null
    var skills: List<SkillTodo>? = listOf()
    var ready: Int = 0
    var full: Int = 0
    constructor(todoId: UUID? = null) : this() { this.todoId = todoId }
}
