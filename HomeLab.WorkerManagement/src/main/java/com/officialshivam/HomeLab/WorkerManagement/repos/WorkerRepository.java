package com.officialshivam.HomeLab.WorkerManagement.repos;

import com.officialshivam.HomeLab.WorkerManagement.schemas.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
    @Query(value = "SELECT * FROM workers WHERE uuid = ?1", nativeQuery = true)
    Optional<Worker> findByUuid(String uuid);
    @Query(value = "SELECT * FROM workers WHERE id = ?1", nativeQuery = true)
    Optional<Worker> findById(Long id);
    @Query(value = "SELECT * FROM workers WHERE name = ?1", nativeQuery = true)
    List<Worker> findByName(String name);
    @Query(value = "SELECT * FROM workers WHERE is_active = true", nativeQuery = true)
    List<Worker> findActiveWorkers();
}
