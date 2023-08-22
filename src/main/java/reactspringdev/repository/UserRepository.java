package reactspringdev.repository;

import reactspringdev.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    String update(UserUpdateDto userUpdateDto);

    User search(String userId);

    String delete(String userId);
}
