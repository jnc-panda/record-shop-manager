package record_shop.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import record_shop.manager.model.Album;
import record_shop.manager.service.RecordShopService;
import record_shop.manager.service.RecordShopServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    @GetMapping({"/{albumId}"})
    public ResponseEntity<?> getAllAlbumById(@PathVariable Long albumId) {
       Optional<Album> album = recordShopServiceImpl.findAlbumById(albumId);
       if(!album.isPresent()) {
           return new ResponseEntity<>("Album not found, please check id.", HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<>(album, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Album> postAlbum(@RequestBody Album album) {
        Album newAlbum = recordShopServiceImpl.insertAlbum(album);
        return new ResponseEntity<>(newAlbum, HttpStatus.CREATED);
    }

    @PutMapping({"/{albumId}"})
    public ResponseEntity<Album> updateAlbumById(@RequestBody Album album, @PathVariable Long albumId){
        Album albumToUpdate = recordShopServiceImpl.updateAlbum(album, albumId);
        return new ResponseEntity<>(albumToUpdate, HttpStatus.ACCEPTED);
    }

    @DeleteMapping({"/{albumId}"})
    public ResponseEntity<HashMap<String, Boolean>> deleteAlbumById(@PathVariable(required = true) Long albumId) {
    return new ResponseEntity<>(recordShopServiceImpl.deleteAlbumById(albumId), HttpStatus.NO_CONTENT);
    }
}

