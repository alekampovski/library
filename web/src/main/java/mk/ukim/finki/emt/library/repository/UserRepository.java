package mk.ukim.finki.emt.library.repository;

import mk.ukim.finki.emt.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
