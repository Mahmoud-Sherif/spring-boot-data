package springbootdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootdata.model.Author;
import springbootdata.repository.AuthorDataRepo;

import java.util.Optional;

@Service("authorServiceImpl")
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorDataRepo repo;


    @Override
    public Author getAuthorById(Long id) {
        Optional<Author> author = repo.findById(id);
        return author.orElse(null);
    }

    @Override
    public Author addAuthor(Author author) {
        return repo.save(author);
    }


}