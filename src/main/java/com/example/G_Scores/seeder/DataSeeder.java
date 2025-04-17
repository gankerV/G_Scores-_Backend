package com.example.G_Scores.seeder;


import com.example.G_Scores.model.DiemThi;
import com.example.G_Scores.repository.DiemThiRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataSeeder {

    private final DiemThiRepository repository;

    public DataSeeder(DiemThiRepository repository) {
        this.repository = repository;
    }

    @PostConstruct // hàm gọi ngay khi object được tạo
    public void seed() {
        if (repository.count() > 0) return;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new ClassPathResource("dataset/diem_thi_thpt_2024.csv").getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            br.readLine();
            List<DiemThi> batch = new ArrayList<>();
            int BATCH_SIZE = 1000;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                DiemThi d = new DiemThi();
                d.setSbd(parts[0]);
                d.setToan(parse(get(parts, 1)));
                d.setNguVan(parse(get(parts, 2)));
                d.setNgoaiNgu(parse(get(parts, 3)));
                d.setVatLi(parse(get(parts, 4)));
                d.setHoaHoc(parse(get(parts, 5)));
                d.setSinhHoc(parse(get(parts, 6)));
                d.setLichSu(parse(get(parts, 7)));
                d.setDiaLi(parse(get(parts, 8)));
                d.setGdcd(parse(get(parts, 9)));
                d.setMaNgoaiNgu(get(parts, 10));

                batch.add(d);
                if (batch.size() >= BATCH_SIZE) {
                    repository.saveAll(batch);
                    batch.clear();
                }
            }
            if (!batch.isEmpty()) {
                repository.saveAll(batch);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Double parse(String value) {
        try {
            return value == null || value.isEmpty() ? null : Double.parseDouble(value);
        } catch (Exception e) {
            return null;
        }
    }

    private String get(String[] arr, int index) {
        return index < arr.length ? arr[index] : null;
    }
}
