package com.parallel.calls.proj2.classes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long>{

	List<AppUser> findByIdIn(List<Long> ids);
}
