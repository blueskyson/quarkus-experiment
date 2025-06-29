package org.acme.repo;

import java.util.List;
import java.util.UUID;

import org.acme.entity.Release;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ReleaseRepository extends CrudRepository<Release, UUID> {
    @Query("""
        SELECT r.id, r.taskNum, r.version, i.id, i.taskNum, i.description
        FROM Release r
        LEFT JOIN ReleaseItem i ON i.release = r
    """)
    List<Object[]> findReleaseJoinItems();
}

