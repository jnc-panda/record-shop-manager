package record_shop.manager.repository;

import jakarta.inject.Inject;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;
import record_shop.manager.model.Album;
import record_shop.manager.model.Artist;
import record_shop.manager.model.Genre;
import record_shop.manager.service.RecordShopServiceImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RecordShopRepositoryTest {

    @Autowired
    RecordShopRepository recordShopRepository;

    @Test
    public void testRepoFindsAllAlbums() {

        Artist artist1 = new Artist("Porter Robinson");
        Album album1 = new Album("SMILE :D", 2024, Genre.Electronic, 10, artist1);

        recordShopRepository.save(album1);

        Iterable<Album> albums = recordShopRepository.findAll();

        assertThat(albums).hasSize(1);

    }
}
