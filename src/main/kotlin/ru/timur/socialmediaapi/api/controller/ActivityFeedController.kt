package ru.timur.socialmediaapi.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.timur.socialmediaapi.core.model.PostModel
import ru.timur.socialmediaapi.core.service.PersonDetailsService
import ru.timur.socialmediaapi.core.service.PostService
import java.util.*
import kotlin.math.min

@RestController
@RequestMapping("/activitys")
@Tag(name = "ActivityFeed", description = "Auth management APIs")
@RequiredArgsConstructor
class ActivityFeedController(val postService: PostService, val personDetailsService: PersonDetailsService) {
    @GetMapping
    @Operation(summary = "Отображение последних постов")
    @SecurityRequirement(name = "bearerAuth")
    fun showActivity(
        @RequestParam(value = "offset", required = false) offset: Int,
        @RequestParam(value = "limit", required = false) limit: Int
    ): List<PostModel?> {
        var posts: MutableList<PostModel?> = ArrayList()
        for (integer in personDetailsService.getPersonPostsId()) {
            posts.addAll(postService.findAllByPersonId(integer!!))
        }
        posts = posts.sortedBy { it!!.dateOfCreate }.toMutableList()
        Collections.reverse(posts)
        return getPostsByPage(posts, offset, limit)
    }

    // Пагинация страниц
    fun getPostsByPage(posts: List<PostModel?>, pageNumber: Int, pageSize: Int): List<PostModel?> {
        val startIndex = pageNumber * pageSize
        val endIndex = min((startIndex + pageSize).toDouble(), posts.size.toDouble()).toInt()

        // Проверка на выход за пределы списка
        return if (startIndex >= posts.size) {
            ArrayList()
        } else posts.subList(startIndex, endIndex)

        // Получение подсписка с учетом пагинации
    }
}