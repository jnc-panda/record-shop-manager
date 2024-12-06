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
    RecordShopService recordShopService;


    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums() {
        List<Album> albums = recordShopService.getAlbums();
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }
}
