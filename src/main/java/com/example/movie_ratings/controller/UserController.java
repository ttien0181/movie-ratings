package com.example.movie_ratings.controller;

import com.example.movie_ratings.dto.user.UserRequest;
import com.example.movie_ratings.entity.User;
import com.example.movie_ratings.service.UserService;
import com.example.movie_ratings.dto.APIResponse;
import com.example.movie_ratings.controller.base.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController extends BaseController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<APIResponse<User>> create(@RequestBody UserRequest userRequest) {
        System.out.println("1: "+userRequest);
        return okResponse(service.create(userRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<User>> getById(@PathVariable Long id) {
        return okResponse(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<User>>> getAll() {
        return okResponse(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<User>> update(@PathVariable Long id, @RequestBody User user) {
        return okResponse(service.update(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> delete(@PathVariable Long id) {
        service.delete(id);
        return okResponse("Deleted successfully");
    }
}
