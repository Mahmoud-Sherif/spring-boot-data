package springbootdata.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import springbootdata.errorhandlling.NotFoundException;
import springbootdata.model.Author;
import springbootdata.repository.AuthorDataRepo;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {


    @Mock
    AuthorDataRepo repo;

    AuthorService services;


    @BeforeEach
    void setUp() {
        this.services = new AuthorServiceImpl(repo);
    }
    
    
    @Test
    void testGetUserById_RequiredAuthorID_ReturnAuthor() {
        Author author = new Author(5L, "Mahmoud");
        Mockito.when(repo.findById(author.getId())).thenReturn(Optional.of(author));
        Author result = services.getAuthorById(author.getId());
        Assertions.assertEquals(author, result);
    }

    @Test
    void testGetUserById_RequiredAuthorID_ReturnNotFoundException() {
        Author author = new Author(6L, "Mahmoud");
        // mocking method may work correclty and don't throw not found exception because
        // you already mocked repo.findById(author.getId()) in eariler test
        Mockito.when(repo.findById(author.getId())).thenThrow(NotFoundException.class);
        Assertions.assertThrows(NotFoundException.class, () -> services.getAuthorById(author.getId()));
    }


    @Test
    void testAddAuthor_RequiredAuthor_ReturnAuthor() {
        Author author = new Author(5L, "Mahmoud");
        Mockito.when(repo.save(author)).thenReturn(author);
        Author result = services.addAuthor(author);
        Assertions.assertEquals(author, result);
    }

}
