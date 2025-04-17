package com.example.G_Scores.service;

import com.example.G_Scores.dto.DiemThiDTO;
import com.example.G_Scores.dto.ReportDTO;
import com.example.G_Scores.model.DiemThi;
import com.example.G_Scores.repository.DiemThiRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DiemThiService implements IDiemThiService {

    private final DiemThiRepository repository;
    private final ModelMapper modelMapper;

    public DiemThiService(DiemThiRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<DiemThiDTO> getBySbd(String sbd) {
        return repository.findById(sbd)
                .map(diemThi -> modelMapper.map(diemThi, DiemThiDTO.class));
    }

    @Override
    public List<ReportDTO> generateSubjectReport() {
        List<DiemThi> all = repository.findAll();
        String[] subjects = {"toan", "nguVan", "ngoaiNgu", "vatLi", "hoaHoc", "sinhHoc", "lichSu", "diaLi", "gdcd"};

        List<ReportDTO> reports = new ArrayList<>();
        for (String subject : subjects) {
            Function<DiemThi, Double> getter = switch (subject) {
                case "toan" -> DiemThi::getToan;
                case "nguVan" -> DiemThi::getNguVan;
                case "ngoaiNgu" -> DiemThi::getNgoaiNgu;
                case "vatLi" -> DiemThi::getVatLi;
                case "hoaHoc" -> DiemThi::getHoaHoc;
                case "sinhHoc" -> DiemThi::getSinhHoc;
                case "lichSu" -> DiemThi::getLichSu;
                case "diaLi" -> DiemThi::getDiaLi;
                case "gdcd" -> DiemThi::getGdcd;
                default -> d -> null;
            };

            Map<String, Long> grouped = all.stream()
                    .map(getter)
                    .filter(Objects::nonNull)
                    .collect(Collectors.groupingBy(score -> {
                        if (score >= 8) return "1";
                        if (score >= 6) return "2";
                        if (score >= 4) return "3";
                        return "4";
                    }, Collectors.counting()));

            reports.add(new ReportDTO(
                    subject,
                    grouped.getOrDefault("1", 0L),
                    grouped.getOrDefault("2", 0L),
                    grouped.getOrDefault("3", 0L),
                    grouped.getOrDefault("4", 0L)
            ));
        }
        return reports;
    }

    @Override
    public List<DiemThiDTO> getTop10GroupA() {
        Pageable pageable = PageRequest.of(0, 10);
        return repository.findTop10ByGroupA(pageable).stream()
                .map(d -> modelMapper.map(d, DiemThiDTO.class))
                .collect(Collectors.toList());
    }
}
