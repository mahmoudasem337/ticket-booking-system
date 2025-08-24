package com.asem.inventoryservice.Repository;

import com.asem.inventoryservice.Entity.Performer;
import com.asem.inventoryservice.Entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformerRepository extends JpaRepository<Performer, Long> {
}
