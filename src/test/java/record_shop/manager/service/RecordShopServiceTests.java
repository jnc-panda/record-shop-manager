package record_shop.manager.service;

import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import record_shop.manager.model.Album;
import record_shop.manager.model.Artist;
import record_shop.manager.model.Genre;
import record_shop.manager.repository.RecordShopRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@DataJpaTest
public class RecordShopServiceTests {

    @Mock
    RecordShopRepository recordShopRepository ;

    @InjectMocks
    RecordShopServiceImpl recordShopServiceImpl;

    @Test
    @DisplayName("getAlbums responds with a list of all albums")
    void testGetAllAlbums() {
        //ARRANGE
        List<Album> albums = new ArrayList<>();
        Artist artist1 = new Artist("G Jones");
        Album album1 = new Album("Paths", 2023, Genre.Electronic, 2, artist1);

        Artist artist2 = new Artist("Iron Maiden");
        Album album2 = new Album("Fear of the Dark", 1992, Genre.Metal, 13, artist2);

        albums.add(album1);
        albums.add(album2);

        when(recordShopRepository.findAll()).thenReturn(albums);

        //ACT
        List<Album> actualResult = recordShopServiceImpl.getAlbums();

        //ASSERT
        assertThat(actualResult).hasSize(2);
//        assertThat(actualResult).isEqualTo(albums);
        assertThat(actualResult).isEqualTo(albums);
    }



    @Test
    @Description("insertAlbum correctly adds an album to the database")
    void testInsertAlbum() {
        //ARRANGE
        Artist artist1 = new Artist("G Jones");
        Album album1 = new Album("Paths", 2023, Genre.Electronic, 2, artist1);

        when(recordShopRepository.save(album1)).thenReturn(album1);

        //ACT
        Album actualResult = recordShopServiceImpl.insertAlbum(album1);

        //ASSERT
        assertThat(actualResult).isEqualTo(album1);
    }



}
