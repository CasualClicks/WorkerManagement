package com.officialshivam.HomeLab.WorkerManagement.repos;

import com.officialshivam.HomeLab.WorkerManagement.schemas.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    @Query(value = "SELECT * FROM attendance WHERE worker_uuid = :workerId AND date = :date", nativeQuery = true)
    Optional<Attendance> findByWorkerIdAndDate(@Param("workerId") String workerId, @Param("date") LocalDate date);

    @Query(value = "SELECT * FROM attendance WHERE worker_id = :workerId", nativeQuery = true)
    List<Attendance> findAllByWorkerId(@Param("workerId") Long workerId);

    @Query(value = "SELECT * FROM attendance WHERE worker_id = :workerId AND date BETWEEN :start AND :end", nativeQuery = true)
    List<Attendance> findAllByWorkerIdAndDateBetween(
            @Param("workerId") Long workerId,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end
    );
}
