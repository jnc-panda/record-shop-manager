package record_shop.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import record_shop.manager.model.Album;
import record_shop.manager.service.RecordShopService;
import record_shop.manager.service.RecordShopServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recordShop")

public class RecordShopController {

    @Autowired
    RecordShopServiceImpl recordShopServiceImpl;


    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums() {
        List<Album> albums = recordShopServiceImpl.getAlbums();
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Album> postAlbum(@RequestBody Album album) {
        Album newAlbum = recordShopServiceImpl.insertAlbum(album);
        return new ResponseEntity<>(newAlbum, HttpStatus.CREATED);
    }

    @PutMapping({"/{album_id}"})
    public ResponseEntity<Album> updateAlbumById(@RequestBody Album album, @PathVariable Long album_id){
        Album albumToUpdate = recordShopServiceImpl.updateAlbum(album, album_id);
        return new ResponseEntity<>(albumToUpdate, HttpStatus.OK);
    }
}
