package ru.timur.socialmediaapi.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import lombok.RequiredArgsConstructor
import org.modelmapper.ModelMapper
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import ru.timur.socialmediaapi.api.dto.PostDTO
import ru.timur.socialmediaapi.api.dto.PostUpdateDTO
import ru.timur.socialmediaapi.core.model.PostModel
import ru.timur.socialmediaapi.core.service.PostService

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
class PostsController(val postService: PostService, val modelMapper: ModelMapper) {

    @Operation(summary = "Создание поста")
    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    fun create(@RequestBody postDTO: @Valid PostDTO?, bindingResult: BindingResult): PostModel {
        if (bindingResult.hasErrors()) {
            val errors: MutableMap<String, String?> = HashMap()
            for (error in bindingResult.fieldErrors) {
                errors[error.field] = error.defaultMessage
            }
        }
        return postService.create(converteToPost(postDTO), SecurityContextHolder.getContext().authentication)
    }

    @Operation(summary = "Вывод всех постов")
    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    fun showAll(): List<PostModel> {
        return postService.showAll()
    }

    @Operation(summary = "Изменение поста")
    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    fun update(
        @PathVariable("id") id: Int,
        @RequestBody postUpdateDTO: @Valid PostUpdateDTO?,
        bindingResult: BindingResult
    ): PostModel {
        if (bindingResult.hasErrors()) {
            val errors: MutableMap<String, String?> = HashMap()
            for (error in bindingResult.fieldErrors) {
                errors[error.field] = error.defaultMessage
            }
        }
        return postService.update(converteToPost(postUpdateDTO), id)
    }

    @Operation(summary = "Удаление поста")
    @DeleteMapping("/delete/{id}")
    @SecurityRequirement(name = "bearerAuth")
    fun delete(@PathVariable id: Int): PostModel? {
        return postService.delete(postService.findById(id))
    }

    private fun converteToPost(postDTO: PostDTO?): PostModel {
        return modelMapper.map(postDTO, PostModel::class.java)
    }

    private fun converteToPost(postDTO: PostUpdateDTO?): PostModel {
        return modelMapper.map(postDTO, PostModel::class.java)
    }
}