package com.officialshivam.HomeLab.WorkerManagement.repos;


import com.officialshivam.HomeLab.WorkerManagement.schemas.Extra_Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ExtraTaskRepository extends JpaRepository<Extra_Tasks, Long> {

    @Query(value = "SELECT * FROM extra_tasks WHERE worker_uuid = :workerUuid", nativeQuery = true)
    List<Extra_Tasks> findAllByWorkerUuid(@Param("workerUuid") String workerUuid);

    @Query(value = "SELECT * FROM extra_tasks WHERE task_id = :taskId", nativeQuery = true)
    Optional<Extra_Tasks> findByTaskId(@Param("taskId") String taskId);

    @Modifying
    @Query(value = "UPDATE extra_tasks SET is_completed = :isCompleted, row_updation_date_time = NOW() WHERE task_id = :taskId", nativeQuery = true)
    void updateTaskStatusById(@Param("taskId") String taskId, @Param("isCompleted") boolean isCompleted);
}
