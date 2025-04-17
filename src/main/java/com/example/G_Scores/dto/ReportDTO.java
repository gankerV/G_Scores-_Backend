package com.example.G_Scores.dto;

public class ReportDTO {
    private String subject;
    private long level1; // >=8
    private long level2; // 6 - 7.99
    private long level3; // 4 - 5.99
    private long level4; // < 4

    public ReportDTO() {
    }

    public ReportDTO(String subject, long level1, long level2, long level3, long level4) {
        this.subject = subject;
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
        this.level4 = level4;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public long getLevel1() {
        return level1;
    }

    public void setLevel1(long level1) {
        this.level1 = level1;
    }

    public long getLevel2() {
        return level2;
    }

    public void setLevel2(long level2) {
        this.level2 = level2;
    }

    public long getLevel3() {
        return level3;
    }

    public void setLevel3(long level3) {
        this.level3 = level3;
    }

    public long getLevel4() {
        return level4;
    }

    public void setLevel4(long level4) {
        this.level4 = level4;
    }
}
