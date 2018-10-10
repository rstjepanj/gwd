package hr.tvz.gwd.db;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.tvz.gwd.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	public User findByUsername(String username);
	public Integer countByUsername(String username);
	public Integer countByEmail(String email);
	public User findByToken(String token);
}