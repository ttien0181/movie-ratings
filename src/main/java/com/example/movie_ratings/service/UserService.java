package com.example.movie_ratings.service;

import com.example.movie_ratings.dto.user.UserRequest;
import com.example.movie_ratings.entity.User;

import java.util.List;

public interface UserService {

    User create(UserRequest userRequest);

    User getById(Long id);

    List<User> getAll();

    User update(Long id, User user);

    void delete(Long id);

    User setBanned(Long id, boolean banned);
}
