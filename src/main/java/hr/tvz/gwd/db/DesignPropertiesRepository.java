package hr.tvz.gwd.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.tvz.gwd.model.DesignProperties;
import hr.tvz.gwd.model.User;

public interface DesignPropertiesRepository extends JpaRepository<DesignProperties, Integer>{
	public Integer countByUser(User user); 
	public List<DesignProperties> findAllByUser(User user);
}
