# Hệ thống Quản Lý Điểm Sinh Viên - D19CNPM3 EPU

Đề tài Bài tập lớn học phần **Lập trình Java**
Giảng viên hướng dẫn: **TS. Hoàng Xuân Hiển**
Khoa Điện toán và Công nghệ số năng lượng - Trường Đại học Điện Lực (EPU)

---

## 1. Giới thiệu dự án
Ứng dụng máy tính để bàn (Desktop Application) xây dựng bằng **Java Swing** theo kiến trúc phân lớp chuẩn mực (Layered Architecture). Hệ thống hỗ trợ quản lý toàn diện hồ sơ sinh viên, danh mục môn học đào tạo theo học chế tín chỉ, điểm thi các học phần, tự động tính toán điểm trung bình tích lũy GPA thang 4 và xếp loại học lực theo quy chế EPU.

### Nhóm sinh viên thực hiện (D19CNPM3):
1. **Phan Công Thành Đạt** (MSV: 24810310223) - Trưởng nhóm, Kiến trúc hệ thống & Phân hệ Sinh viên
2. **Lê Ngọc Quyền** (MSV: 24810310259) - Phân hệ Môn học & Lưu trữ dữ liệu Flat-file CSV/TXT
3. **Nguyễn Thành Nam** (MSV: 24810310230) - Phân hệ Điểm số & Nghiệp vụ Thống kê GPA
4. **Nguyễn Văn Tiến** (MSV: 24810310274) - Giao diện Báo cáo Thống kê, Tiện ích & Kiểm thử

---

## 2. Cấu trúc mã nguồn
```
QuanLyDiemSV/
├── src/
│   ├── app/Main.java                  # Điểm khởi chạy chương trình (SwingUtilities.invokeLater)
│   ├── model/                         # Thực thể đối tượng: Student, Subject, Grade
│   ├── manager/                       # Quản lý danh sách trong bộ nhớ: StudentManager, SubjectManager, GradeManager
│   ├── service/ReportService.java     # Thuật toán tính GPA thang 4, quy đổi điểm chữ, xếp loại học lực
│   ├── storage/FileStorage.java       # Tầng lưu trữ bền vững: Đọc/ghi tệp định dạng pipe-delimited UTF-8
│   ├── ui/                            # Giao diện Java Swing: MainFrame, StudentPanel, SubjectPanel, GradePanel, ReportPanel
│   └── util/InputValidator.java       # Tiện ích kiểm tra định dạng dữ liệu (Regex email, ngày sinh, điểm, tín chỉ)
├── data/                              # Tệp dữ liệu bền vững (students.txt, subjects.txt, grades.txt)
└── README.md                          # Tài liệu hướng dẫn sử dụng và kiểm thử
```

---

## 3. Hướng dẫn biên dịch và chạy chương trình

### Yêu cầu môi trường:
- Java Development Kit (JDK) phiên bản 17 hoặc 21 trở lên.
- Đã cấu hình biến môi trường `JAVA_HOME` và `PATH` trỏ tới thư mục `bin` của JDK.

### Lệnh biên dịch trên Windows (PowerShell / Command Prompt):
```powershell
# 1. Tạo thư mục bin để chứa bytecode đã biên dịch
New-Item -ItemType Directory -Force -Path bin

# 2. Biên dịch toàn bộ mã nguồn Java với mã hóa UTF-8
javac -encoding UTF-8 -d bin (Get-ChildItem -Path src -Filter *.java -Recurse | Select-Object -ExpandProperty FullName)

# 3. Khởi chạy ứng dụng
java -cp bin app.Main
```

---

## 4. Các tính năng cốt lõi đã hoàn thiện
1. **Quản lý Sinh viên:** Thêm, Sửa, Xóa, Tìm kiếm theo mã/tên, Sắp xếp thứ tự tự nhiên (Comparable) và theo tên tiếng Việt (Collator).
2. **Quản lý Môn học:** Quản lý danh mục học phần, kiểm soát số tín chỉ từ 1 đến 6.
3. **Quản lý Điểm & Xuất CSV:** Nhập và sửa điểm thang [0.0 - 10.0], xuất danh sách điểm ra định dạng CSV mở được trong Excel với mã hóa UTF-8 BOM.
4. **Báo cáo & Thống kê GPA:** Xem bảng điểm chi tiết cá nhân, quy đổi thang 10 sang thang 4 và điểm chữ (A, B+, B, C+, C, D+, D, F), bảng tổng hợp GPA toàn khóa và thống kê điểm bình quân từng môn học.
5. **Kiểm tra hợp lệ dữ liệu:** Bắt lỗi rỗng, kiểm tra định dạng email chuẩn RFC, ngày sinh ISO-8601 (yyyy-MM-dd) và miền điểm.
