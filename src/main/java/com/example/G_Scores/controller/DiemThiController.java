package com.example.G_Scores.controller;

import com.example.G_Scores.dto.DiemThiDTO;
import com.example.G_Scores.dto.ReportDTO;
import com.example.G_Scores.service.IDiemThiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/scores")
public class DiemThiController {

    private final IDiemThiService service;

    public DiemThiController(IDiemThiService service) {
        this.service = service;
    }

    @GetMapping("/{sbd}")
    public ResponseEntity<Object> getBySbd(@PathVariable String sbd) {
        return ResponseEntity.ok(service.getBySbd(sbd));
    }

    // Trả về báo cáo môn học dưới dạng List<ReportDTO>
    @GetMapping("/report")
    public ResponseEntity<List<ReportDTO>> report() {
        return ResponseEntity.ok(service.generateSubjectReport());
    }

    // Trả về 10 học sinh đầu nhóm A dưới dạng List<DiemThiDTO>
    @GetMapping("/top10/groupA")
    public ResponseEntity<List<DiemThiDTO>> top10GroupA() {
        return ResponseEntity.ok(service.getTop10GroupA());
    }
}
