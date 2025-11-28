package com.example.movie_ratings.service.impl;

import com.example.movie_ratings.dto.user.UserRequest;
import com.example.movie_ratings.entity.User;
import com.example.movie_ratings.repository.UserRepository;
import com.example.movie_ratings.service.UserService;
import com.example.movie_ratings.util.ValueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    @Override
    public User create(UserRequest userRequest) {
        User user = ValueMapper.MAPPER.convertToUser(userRequest);
        // se them hash sau
        System.out.println(user);
        return repo.save(user);
    }

    @Override
    public User getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<User> getAll() {
        return repo.findAll();
    }

    @Override
    public User update(Long id, User newUser) {
        User old = getById(id);
        old.setUsername(newUser.getUsername());
        old.setEmail(newUser.getEmail());
        old.setPassword(newUser.getPassword());
        return repo.save(old);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public User setBanned(Long id, boolean banned) {
        User user = getById(id);
        user.setBanned(banned);
        return repo.save(user);
    }
}
