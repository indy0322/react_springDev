package reactspringdev.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactspringdev.domain.User;

import java.util.List;
import java.util.Optional;

//@Repository
@Transactional
@RequiredArgsConstructor
public class UserRepositoryV1 implements UserRepository {

    private final SpringDataJpaUserRepository repository;

    @Override
    public User save(User user){
        return repository.save(user);
    }

    @Override
    public String update(UserUpdateDto userUpdateDto){
        repository.updateUser(userUpdateDto.getUserId(), userUpdateDto.getUserPasswd(), userUpdateDto.getSearchId());
        return "갱신 완료";
    }

    @Override
    public User search(String userId){
        return repository.findByUsers(userId);
    }

    @Override
    public String delete(String userId){
        repository.delete(userId);
        return "삭제 완료";
    }
}
