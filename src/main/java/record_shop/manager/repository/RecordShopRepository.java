package record_shop.manager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import record_shop.manager.model.Album;

@Repository
public interface RecordShopRepository extends CrudRepository <Album, Long> {
}
