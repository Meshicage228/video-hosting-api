package ru.clevertec.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.dto.UserDto;
import ru.clevertec.entity.UserEntity;
import ru.clevertec.exception.UserNotFoundException;
import ru.clevertec.mapper.UserMapper;
import ru.clevertec.repository.UserRepository;
import ru.clevertec.service.UserService;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Optional<UserEntity> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        UserEntity entity = userMapper.toEntity(userDto);
        UserEntity save = userRepository.save(entity);
        return userMapper.toDto(save);
    }

    @Override
    @Transactional
    public void fullUpdateUser(UUID userId, UserDto userDto) {
        UserEntity userEntity = getUserById(userId)
                .orElseThrow(UserNotFoundException::new);

       userMapper.update(userEntity, userDto);
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }
}
