package scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import scheduler.entity.User;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
	Optional<User> findByUid(String email);
	Optional<User> findByUidAndProvider(String email, String provider);
}
