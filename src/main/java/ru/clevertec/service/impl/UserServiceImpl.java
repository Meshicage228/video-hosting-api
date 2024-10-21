package ru.clevertec.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.dto.user.CreateUserDto;
import ru.clevertec.dto.user.CreatedUserDto;
import ru.clevertec.dto.user.UpdateUserDto;
import ru.clevertec.dto.user.UpdatedUserDto;
import ru.clevertec.entity.UserEntity;
import ru.clevertec.exception.UserNotFoundException;
import ru.clevertec.mapper.UserMapper;
import ru.clevertec.repository.UserRepository;
import ru.clevertec.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public CreatedUserDto saveUser(CreateUserDto userDto) {
        UserEntity entity = userMapper.toEntity(userDto);
        UserEntity save = userRepository.save(entity);
        return userMapper.toDto(save);
    }

    @Override
    @Transactional
    public UpdatedUserDto fullUpdateUser(Long userId, UpdateUserDto userDto) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.valueOf(userId)));

        UserEntity updatedUser = userMapper.update(userEntity, userDto);

        return userMapper.toUpdatedDto(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional
    public UpdatedUserDto patchUpdateUser(Long userId, UpdateUserDto updateDto) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.valueOf(userId)));

        UserEntity patchUpdated = userMapper.patchUpdate(userEntity, updateDto);

        return userMapper.toUpdatedDto(patchUpdated);
    }
}
