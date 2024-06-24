package com.example.backend.controller;


import com.example.backend.DTO.ChatDTO;
import com.example.backend.model.ChatMessage;
import com.example.backend.model.User;
import com.example.backend.service.ChatService;
import com.example.backend.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final UserService userService;

    @PostMapping("/send")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Void> sendMessage(HttpServletRequest request,
            @RequestBody ChatMessage message) {
        var user = userService.getUserFromRequest(request);
        chatService.sendMessage(user, message);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ChatDTO> createChat(HttpServletRequest request,
                                              @RequestParam String chatName) {
        var user = userService.getUserFromRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(chatService.createChat(user, chatName));
    }

    @GetMapping("/queues")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<String>> getQueues(HttpServletRequest request) {
        var user = userService.getUserFromRequest(request);
        return ResponseEntity.ok(chatService.getAllUserQueues(user));
    }
    @GetMapping("/exchanges")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<ChatDTO>> getExchanges(HttpServletRequest request) {
        var user = userService.getUserFromRequest(request);
        return ResponseEntity.ok(chatService.getAllUserChatRooms(user));
    }

    @PostMapping("/add")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<User> addUser(HttpServletRequest request, @RequestParam Long chatId, @RequestParam String email){
        var user = userService.getUserFromRequest(request);
        return ResponseEntity.ok(chatService.addNewUser(user, chatId, email));
    }
    @DeleteMapping("/remove")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Void> removeUser(HttpServletRequest request, @RequestParam Long chatId, @RequestParam Long userId){
        var user = userService.getUserFromRequest(request);
        chatService.removeUserFromChat(user, chatId, userId);
        return ResponseEntity.ok().build();
    }

}
