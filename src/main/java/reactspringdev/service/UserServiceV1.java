package reactspringdev.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactspringdev.domain.User;
import reactspringdev.repository.UserRepository;
import reactspringdev.repository.UserRepositoryV1;
import reactspringdev.repository.UserUpdateDto;

import java.util.List;
import java.util.Optional;

//@Service
@RequiredArgsConstructor
public class UserServiceV1 implements UserService {

    private final UserRepository userRepository;

    @Override
    public User save(User user){
        return userRepository.save(user);
    }

    @Override
    public String update(UserUpdateDto updateDto){
        return userRepository.update(updateDto);
    }

    @Override
    public User search(String userId){
       return userRepository.search(userId);
    }

    @Override
    public String delete(String userId){
        return userRepository.delete(userId);
    }
}
