# G_Scores - Hướng dẫn cấu hình Database (Local)

## 🧰 Yêu cầu

- Cài đặt **MySQL Server**
- Cài đặt **MySQL Workbench** hoặc công cụ tương tự để quản lý dữ liệu

---

## 🛠️ Tạo Database

1. Mở MySQL Workbench hoặc công cụ bạn chọn.
2. Kết nối tới MySQL server local (username mặc định là `root`, mật khẩu để trống hoặc do bạn đặt).
3. Chạy lệnh sau để tạo database:

```sql
CREATE DATABASE g_scores;
```

---

## ⚙️ Cấu hình file `application.properties` cho môi trường local

> Vì file `application.properties` được cấu hình để deploy sản phẩm và kết nối database tới Aiven, nên cần chỉnh sửa lại như sau để kết nối với database local:

### 🔧 `application.properties` hiện tại:

```properties
server.port=${PORT:8080}

# ========================
# Production (Aiven)
# ========================
spring.datasource.url=jdbc:mysql://mysql-2475b612-nvxanh75-d8c7.b.aivencloud.com:13824/g_scores?sslMode=REQUIRED
spring.datasource.username=avnadmin
spring.datasource.password=${DB_PASSWORD}

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Flyway
spring.flyway.enabled=true
spring.flyway.url=${spring.datasource.url}
spring.flyway.user=${spring.datasource.username}
spring.flyway.password=${DB_PASSWORD}
spring.flyway.baseline-on-migrate=false

# ========================
# Local (commented out)
# ========================
# spring.datasource.url=jdbc:mysql://localhost:3306/g_scores
# spring.datasource.username=root
# spring.datasource.password=3333
# spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# spring.flyway.baseline-on-migrate=false
# spring.flyway.url=jdbc:mysql://localhost:3306/g_scores
# spring.flyway.user=root
# spring.flyway.password=3333
```

### ✏️ Hãy xóa phần production và bỏ comment cài đặt bên dưới cho local, sau khi chỉnh sửa sẽ thành:

```properties
server.port=${PORT:8080}
spring.datasource.url=jdbc:mysql://localhost:3306/g_scores 
spring.datasource.username=root 
spring.datasource.password=your_password 
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver 

spring.flyway.baseline-on-migrate=false 
spring.flyway.url=jdbc:mysql://localhost:3306/g_scores 
spring.flyway.user=root 
spring.flyway.password=your_password
```

> 🔐 Lưu ý: thay `your_password` bằng mật khẩu thực tế của bạn cho user `root`.

---

## ✅ Ghi chú

- Đảm bảo MySQL server đang chạy ở port mặc định `3306`.
- Nếu bạn dùng Maven và thiếu driver MySQL, hãy thêm đoạn sau vào `pom.xml`:

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```

---

🎉 Sau khi hoàn tất các bước trên, bạn có thể chạy ứng dụng và kết nối với cơ sở dữ liệu local thành công.
