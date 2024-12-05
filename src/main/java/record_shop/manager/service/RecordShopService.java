package record_shop.manager.service;

import org.springframework.stereotype.Service;
import record_shop.manager.model.Album;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public interface RecordShopService {

    List<Album> getAlbums();
    Album insertAlbum(Album album);
    Album updateAlbum(Album album);
    Optional<Album> findAlbumById();
    HashMap<String, Boolean> deleteAlbum();
}
