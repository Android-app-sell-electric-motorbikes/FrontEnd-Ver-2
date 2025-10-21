# ⚡ EVSaleApp – Ứng dụng bán xe máy điện

EVSaleApp là một ứng dụng Android giúp người dùng **mua, xem thông tin và quản lý xe điện** một cách tiện lợi.  
Ứng dụng được phát triển với **Java + XML**, kết hợp **Google Maps SDK** để hiển thị vị trí trạm sạc, **Firebase** để gửi thông báo, và **Retrofit + Hilt** để kết nối API.

---

## 🚀 Tính năng chính

### 👤 **Người dùng (Customer)**
- **Đăng ký / Đăng nhập**: Xác thực tài khoản người dùng.
- **Xem danh sách xe điện**: Hiển thị thông tin chi tiết, giá và hình ảnh từng mẫu xe.
- **Chi tiết sản phẩm**: Xem mô tả, đánh giá, thông số kỹ thuật xe.
- **Thêm vào giỏ hàng**: Quản lý giỏ hàng, tăng giảm số lượng sản phẩm.
- **Thanh toán / Billing**: Nhập địa chỉ, số điện thoại và xác nhận đơn hàng.
- **Xem trạm sạc (Map)**: Hiển thị vị trí các trạm sạc trên bản đồ Google Maps.
- **Chat với cửa hàng**: Gửi và nhận tin nhắn với admin (realtime chat).

---

### 🧑‍💼 **Nhân viên (Staff)**
- **Quản lý sản phẩm**: Thêm, sửa, xóa xe điện.
- **Quản lý đơn hàng**: Xem danh sách đơn hàng của khách.

---

### 👨‍💻 **Quản trị viên (Admin)**
- **Dashboard tổng quan**: Thống kê doanh số, sản phẩm, khách hàng.
- **Quản lý người dùng**: Thêm hoặc khóa tài khoản.

---

## 🗺️ Danh sách màn hình (UI)

| Màn hình | Tên file | Mô tả |
|-----------|-----------|-------|
| **Màn hình đăng nhập** | `LoginActivity` | Người dùng nhập email và mật khẩu |
| **Màn hình đăng ký** | `RegisterActivity` | Tạo tài khoản mới |
| **Trang chủ khách hàng** | `CustomerDashboardActivity` / `fragment_home.xml` | Danh sách xe và banner |
| **Chi tiết sản phẩm** | `ProductDetailActivity` | Xem chi tiết thông tin xe |
| **Giỏ hàng** | `CartFragment`, `CartAdapter` | Quản lý giỏ hàng, tính tổng tiền |
| **Thanh toán** | `BillingActivity` | Điền thông tin nhận hàng và thanh toán |
| **Bản đồ trạm sạc** | `MapActivity` | Hiển thị trạm sạc bằng Google Maps |
| **Chat với cửa hàng** | `ChatActivity`, `ChatAdapter` | Trò chuyện trực tuyến với admin |
| **Dashboard Admin/Staff** | `AdminDashboardActivity`, `StaffDashboardActivity` | Giao diện quản trị và nhân viên |

---

## 🧩 Cấu trúc thư mục chính

---

## 🧠 Công nghệ sử dụng

| Công nghệ | Mục đích |
|------------|----------|
| **Java / XML** | Ngôn ngữ lập trình chính |
| **Hilt (Dagger)** | Dependency Injection |
| **Retrofit + Gson + OkHttp** | Gọi API, xử lý dữ liệu JSON |
| **Glide / Picasso** | Load ảnh |
| **Google Maps SDK** | Hiển thị bản đồ và trạm sạc |
| **Firebase Cloud Messaging** | Gửi thông báo tới người dùng |
| **RecyclerView** | Hiển thị danh sách sản phẩm và tin nhắn |
| **ViewBinding** | Liên kết UI nhanh gọn |
| **Material Design 3** | Giao diện hiện đại và trực quan |

---

## ⚙️ Cài đặt & Chạy ứng dụng

### 1️⃣ Clone project
```bash
git clone https://github.com/Android-app-sell-electric-motorbikes/FrontEnd-Ver-2.git
cd FrontEnd-Ver-2


