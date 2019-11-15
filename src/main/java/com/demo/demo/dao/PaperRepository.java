package com.demo.demo.dao;

import com.demo.demo.model.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperRepository extends JpaRepository<Paper,String> {
}
