package org.acme.repo;

import org.acme.entity.ReleaseItem;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReleaseItemRepository extends CrudRepository<ReleaseItem, UUID> {
}