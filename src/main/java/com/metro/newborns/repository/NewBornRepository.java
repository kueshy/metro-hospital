package com.metro.newborns.repository;

import com.metro.newborns.entities.NewBorn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewBornRepository extends JpaRepository<NewBorn, Long> {

    @Query(value = "SELECT * FROM metro_newborns n  WHERE UPPER(n.child_name) LIKE CONCAT('%',UPPER(:child_name),'%')", nativeQuery = true)
    List<NewBorn> findByChild_nameContainingIgnoreCase(@Param("child_name") String child_name);
}
