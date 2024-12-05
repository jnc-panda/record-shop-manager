package record_shop.manager.service;

import record_shop.manager.model.Album;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface RecordShopService {

    List<Album> getAlbums();
    Album insertAlbum(Album album);
    Album updateAlbum(Album album);
    Optional<Album> findAlbumById(Long id);
    HashMap<String, Boolean> deleteAlbumById(Long id);
}
