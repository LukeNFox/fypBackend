package dive;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiverRepository extends JpaRepository<Diver, Integer> {
    List<Diver> findByDiveId_DiveId(Integer diveId);
}