package com.example.G_Scores.service;

import com.example.G_Scores.dto.DiemThiDTO;
import com.example.G_Scores.dto.ReportDTO;
import com.example.G_Scores.model.DiemThi;

import java.util.List;
import java.util.Optional;

public interface IDiemThiService {

    Optional<DiemThiDTO> getBySbd(String sbd);

    List<ReportDTO> generateSubjectReport();

    List<DiemThiDTO> getTop10GroupA();
}