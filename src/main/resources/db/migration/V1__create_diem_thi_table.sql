
DROP TABLE IF EXISTS diem_thi;
CREATE TABLE diem_thi (
    sbd VARCHAR(10) PRIMARY KEY,
    toan DECIMAL(5,2) NULL,
    ngu_van DECIMAL(5,2) NULL,
    ngoai_ngu DECIMAL(5,2) NULL,
    vat_li DECIMAL(5,2) NULL,
    hoa_hoc DECIMAL(5,2) NULL,
    sinh_hoc DECIMAL(5,2) NULL,
    lich_su DECIMAL(5,2) NULL,
    dia_li DECIMAL(5,2) NULL,
    gdcd DECIMAL(5,2) NULL,
    ma_ngoai_ngu VARCHAR(2) NULL
);
