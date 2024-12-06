package record_shop.manager.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import record_shop.manager.model.Album;
import record_shop.manager.model.Artist;
import record_shop.manager.model.Genre;
import record_shop.manager.service.RecordShopServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@SpringBootTest
public class RecordShopControllerTest {

    @Mock
    private RecordShopServiceImpl mockRecordShopServiceImpl;

    @InjectMocks
    private RecordShopController mockRecordShopController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup(){
        mockMvcController = MockMvcBuilders.standaloneSetup(mockRecordShopController).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void testGetAlbumsReturnsAllAlbums() throws Exception {

        List<Album> albums = new ArrayList<>();
        albums.add(new Album("Paths", 2023, Genre.Electronic, 5, new Artist("G Jones")));
        albums.add(new Album("The Dude", 1981, Genre.RnB, 11, new Artist("Quincy Jones")));
        albums.add(new Album("Fear of the Dark", 1992, Genre.Metal, 99, new Artist("Iron Maiden")));

        when(mockRecordShopServiceImpl.getAlbums()).thenReturn(albums);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/recordShop"))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Paths"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("The Dude"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name").value("Fear of the Dark"));
    }

    @Test
    public void testPostAlbumsInsertsAlbum() throws Exception, JsonProcessingException {

        Artist artist1 = new Artist("G Jones");
        Album album1 = new Album("Paths", 2023, Genre.Electronic, 5, artist1);

        when(mockRecordShopServiceImpl.insertAlbum(album1)).thenReturn(album1);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.post("/api/v1/recordShop")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(album1)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    public void testUpdateAlbumUpdatesAlbum() throws Exception, JsonProcessingException {

        Artist artist1 = new Artist("G Jones");
        Album album1 = new Album("Paths", 2023, Genre.Electronic, 5, artist1);
        Album album2 = new Album("The Dude", 1981, Genre.RnB, 11, new Artist("Quincy Jones"));

        when(mockRecordShopServiceImpl.updateAlbum(album2, 1L)).thenReturn(album2);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.put("/api/v1/recordShop/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(album2)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

    }

    @Test
    public void testDeleteAlbumDeletesAlbum() throws Exception, JsonProcessingException {

        List<Album> albums = new ArrayList<>();
        Album album1 = new Album("Paths", 2023, Genre.Electronic, 5, new Artist("G Jones"));
        Album album2 = new Album("The Dude", 1981, Genre.RnB, 11, new Artist("Quincy Jones"));
        albums.add(album1);
        albums.add(album2);

        HashMap<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        when(mockRecordShopServiceImpl.deleteAlbumById(1L)).thenReturn(response);


        this.mockMvcController.perform(
                        MockMvcRequestBuilders.delete("/api/v1/recordShop/1"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

    }

}
//        Album album2 = new Album("The Dude", 1981, Genre.RnB, 11, new Artist("Quincy Jones"));
//        Album album3 = new Album("Fear of the Dark", 1992, Genre.Metal, 99, new Artist("Iron Maiden"));