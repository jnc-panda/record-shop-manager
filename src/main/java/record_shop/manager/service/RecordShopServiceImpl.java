package record_shop.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import record_shop.manager.model.Album;
import record_shop.manager.repository.RecordShopRepository;

import java.util.*;

@Service
public class RecordShopServiceImpl implements RecordShopService {

    @Autowired
    RecordShopRepository recordShopRepository;

    @Override
    public List<Album> getAlbums() {
        List<Album> albums = new ArrayList<>();
        recordShopRepository.findAll().forEach(albums::add);
        return albums;
    }

    @Override
    public Album insertAlbum(Album album) {
        return recordShopRepository.save(album);
    }

    @Override
    public Album updateAlbum(Album album, Long id) {
        Album updateableAlbum = recordShopRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No such album with id: " + id));

        updateableAlbum.setArtist(album.getArtist());
        updateableAlbum.setName(album.getName());
        updateableAlbum.setGenre(album.getGenre());
        updateableAlbum.setReleaseYear(album.getReleaseYear());
        updateableAlbum.setStockCount(album.getStockCount());

        return recordShopRepository.save(updateableAlbum);
    }

    @Override
    public Optional<Album> findAlbumById(Long id) {
        return recordShopRepository.findById(id);
    }

    @Override
    public HashMap<String, Boolean> deleteAlbumById(Long id) {
        Album album = recordShopRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Album with id: " + id + " not found"));

        recordShopRepository.delete(album);
        HashMap<String, Boolean> status = new HashMap<>();
        status.put("deleted", Boolean.TRUE);

        return status;
    }
}
