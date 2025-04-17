package com.example.G_Scores.repository;


import com.example.G_Scores.model.DiemThi;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiemThiRepository extends JpaRepository<DiemThi, String> {

    @Query("SELECT d FROM DiemThi d ORDER BY COALESCE(d.toan, 0) + COALESCE(d.vatLi, 0) + COALESCE(d.hoaHoc, 0) DESC")
    List<DiemThi> findTop10ByGroupA(Pageable pageable);
}
