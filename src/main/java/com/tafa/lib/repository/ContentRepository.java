package com.tafa.lib.repository;

import javax.swing.text.AbstractDocument.Content;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tafa.lib.entity.Contents;

@Repository
public interface ContentRepository extends JpaRepository<Contents, Long>{

}
