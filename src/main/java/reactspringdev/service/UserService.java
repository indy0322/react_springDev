package reactspringdev.service;

import reactspringdev.domain.User;
import reactspringdev.repository.UserUpdateDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);

    String update(UserUpdateDto userUpdateDto);

    User search(String userId);

    String delete(String userId);
}
