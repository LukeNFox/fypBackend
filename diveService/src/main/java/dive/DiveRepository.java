package dive;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DiveRepository extends JpaRepository<Dive, Integer> {

}