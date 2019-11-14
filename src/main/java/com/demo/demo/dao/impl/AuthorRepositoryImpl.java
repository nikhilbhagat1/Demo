package com.demo.demo.dao.impl;

import com.demo.demo.dao.AuthorRepository;
import com.demo.demo.model.Author;
import com.demo.demo.model.Book;
import org.hibernate.Criteria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;


@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Author> getAuthors() {
        try {
            TypedQuery<Author> typedQuery = entityManager.createQuery(" from com.demo.demo.model.Author a", Author.class);
            return typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public String save(Author author) {
        try {
            entityManager.persist(author);
            return author.getId();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public Map<String, Object> getAuthorsByBook(String bookId, Integer pageSize, Integer pageNumber) {

        Map<String, Object> authorMap = new HashMap<>();
        String sql = "from com.demo.demo.model.Author a where a.bookId =: bookId";
        TypedQuery typedQuery = entityManager.createQuery(sql, Author.class);
        typedQuery.setParameter("bookId", bookId);
        Double countResult = new Double(typedQuery.getResultList().size());
        int lastPageNumber = (int) (Math.ceil(countResult / new Double(pageSize)));
        typedQuery.setFirstResult((lastPageNumber - 1) * pageSize);
        typedQuery.setMaxResults(pageSize);
        authorMap.put("authors", typedQuery.getResultList());
        authorMap.put("totalPages", countResult);
        return authorMap;

    }

}
