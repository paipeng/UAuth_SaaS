package com.paipeng.saas.uauth.tenant.repository;

import com.paipeng.saas.uauth.tenant.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    @Query(value = "select r.* from record as r left join code as c on r.code_id=c.id left join task as t on c.task_id=t.id WHERE t.id =?1 order by r.id desc", nativeQuery = true)
    List<Record> findAllByTaskId(Long taskId);
}
