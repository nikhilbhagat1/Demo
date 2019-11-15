package com.demo.demo.dao.impl;

import com.demo.demo.dao.AuthorRepository;
import com.demo.demo.model.Author;
import com.demo.demo.model.Book;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import java.awt.print.Pageable;
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

    public  Integer getBooksCount(String id){
        String sql = "select count(a) from Author a where a.bookId =: bookId";
        Long result = entityManager.createQuery(sql, Long.class).setParameter("bookId",id).getSingleResult();
        return (int) (long) result;
    }

    public Map<String, Object> getAuthorsByBook(String bookId, Integer pageSize, Integer pageNumber,String sortBy,String direction) {
        Map<String, Object> authorMap = new HashMap<>();
        Double countResult = getBooksCount(bookId).doubleValue();
        int lastPageNumber = (int) (Math.ceil(countResult / new Double(pageSize)));
        System.out.println("lastPageNumber +" +lastPageNumber);

        String sql = "from com.demo.demo.model.Author a where a.bookId =: bookId";
        if(sortBy!=null){
            sql +=" ORDER BY a."+sortBy+" ";
            if(direction!=null)
                sql += direction;
        }
        TypedQuery typedQuery = entityManager.createQuery(sql, Author.class);
        typedQuery.setParameter("bookId", bookId);
        int init =(pageNumber - 1) * pageSize;
        System.out.println("Init +" +init);
        System.out.println("pageSize +" +pageSize);
        typedQuery.setFirstResult(init);
        typedQuery.setMaxResults(pageSize);
        //Double countResult = new Double(typedQuery.getResultList().size());
        List<Author> authors=typedQuery.getResultList();
        if(authors!=null) {
            authorMap.put("authors", authors);
            authorMap.put("totalPages", lastPageNumber);
        }
        return authorMap;

    }

    public Map<String, Object> getAuthorsByBook(String bookId, Integer pageSize, Integer pageNumber){
        return getAuthorsByBook(bookId,pageSize,pageNumber,null,null);
    }





}
