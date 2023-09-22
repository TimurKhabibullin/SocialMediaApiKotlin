package ru.timur.socialmediaapi.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import lombok.RequiredArgsConstructor
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import ru.timur.socialmediaapi.api.dto.MessageDTO
import ru.timur.socialmediaapi.core.model.FriendRequestModel
import ru.timur.socialmediaapi.core.model.FriendshipModel
import ru.timur.socialmediaapi.core.model.MessageModel
import ru.timur.socialmediaapi.core.service.FriendRequestService
import ru.timur.socialmediaapi.core.service.FriendshipService
import ru.timur.socialmediaapi.core.service.MessageService
import ru.timur.socialmediaapi.core.service.PersonDetailsService

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
class FriendController(val friendRequestService: FriendRequestService, val friendshipService: FriendshipService, val messageService: MessageService, val modelMapper: ModelMapper, val personDetailsService: PersonDetailsService) {

    @Operation(summary = "Отправка заявки в друзья")
    @PostMapping("/requests")
    @SecurityRequirement(name = "bearerAuth")
    fun sendFriendRequest(
        @RequestParam(value = "senderId", required = false) senderId: Int?,
        @RequestParam(value = "recipientId", required = false) recipientId: Int?
    ): FriendRequestModel = friendRequestService.sendFriendRequest(senderId!!, recipientId!!)


    @Operation(summary = "Принятие заявки в друзья")
    @PostMapping("/requests/{requestId}/accept")
    @SecurityRequirement(name = "bearerAuth")
    fun acceptFriendRequest(@PathVariable(value = "requestId", required = false) requestId: Int?): FriendRequestModel = friendRequestService.acceptFriendRequest(friendRequestService.getFriendRequestById(requestId!!).id!!)

    @Operation(summary = "Отклонение заявки в друзья")
    @PostMapping("/requests/{requestId}/reject")
    @SecurityRequirement(name = "bearerAuth")
    fun rejectFriendRequest(@PathVariable(value = "requestId", required = false) requestId: Int?): FriendRequestModel = friendRequestService.rejectFriendRequest(friendRequestService.getFriendRequestById(requestId!!).id!!)

    @Operation(summary = "Удаление заявки в друзья")
    @DeleteMapping("/requests/{requestId}")
    @SecurityRequirement(name = "bearerAuth")
    fun removeRequest(@PathVariable(value = "requestId", required = false) requestId: Int?): FriendRequestModel? = friendRequestService.remove(friendRequestService.getFriendRequestById(requestId!!))

    @Operation(summary = "Удаление дружбы")
    @DeleteMapping("/friendships/{friendshipId}")
    @SecurityRequirement(name = "bearerAuth")
    fun removeFriendship(@PathVariable(value = "friendshipId", required = false) friendshipId: Int?): FriendshipModel = friendshipService.removeFriendship(friendshipService.getFriendshipById(friendshipId!!))

    @Operation(summary = "Отправка сообщения")
    @PostMapping("/messages")
    @SecurityRequirement(name = "bearerAuth")
    fun sendMessage(@RequestBody messageDTO: @Valid MessageDTO?, bindingResult: BindingResult): ResponseEntity<*> {
        if (bindingResult.hasErrors()) {
            val errors: MutableMap<String, String?> = HashMap()
            for (error in bindingResult.fieldErrors) {
                errors[error.field] = error.defaultMessage
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body<Map<String, String?>>(errors)
        }
        return ResponseEntity.status(HttpStatus.OK).body(messageService.sendMessage(converteToMessage(messageDTO)))
    }


    @Operation(summary = "Получение сообщений")
    @GetMapping("/messages")
    @SecurityRequirement(name = "bearerAuth")
    fun getMessages(
        @RequestParam(value = "user1Id", required = false) user1Id: Int?,
        @RequestParam(value = "user2Id", required = false) user2Id: Int?
    ): List<MessageModel> = messageService.getMessages(
            personDetailsService.findByPersonId(user1Id!!), personDetailsService.findByPersonId(user2Id!!))

    private fun converteToMessage(messageDTO: MessageDTO?): MessageModel = modelMapper.map(messageDTO, MessageModel::class.java)
}