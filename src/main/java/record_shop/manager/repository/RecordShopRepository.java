package record_shop.manager.repository;

import org.springframework.data.repository.CrudRepository;
import record_shop.manager.model.Album;

public interface RecordShopRepository extends CrudRepository<Album, Long> {
}
