# EVSaleApp — Android EV Sales Shop

**Mô tả:**  
EVSaleApp là ứng dụng Android cho bán sản phẩm xe điện (EV). Người dùng có thể duyệt sản phẩm, xem chi tiết, thêm vào giỏ, thanh toán, chat với cửa hàng, xem bản đồ cửa hàng và nhận thông báo.

---

## Tính năng chính
1. **Authentication** — Đăng ký / Đăng nhập (username, password, email, phone, address).  
2. **Product list** — Lấy dữ liệu từ API, hiển thị ảnh, tên, giá, mô tả ngắn; hỗ trợ sort/filter.  
3. **Product detail** — Nhiều ảnh, specs, chọn số lượng, Add to Cart.  
4. **Cart** — Quản lý sản phẩm trong giỏ, update quantity, remove, tổng tiền động.  
5. **Billing** — Tích hợp thanh toán (VNPay / PayPal / ZaloPay) qua backend.  
6. **Notification (Cart badge)** — Hiển thị số lượng mặt hàng; sử dụng FCM + NotificationCompat.  
7. **Map** — Google Maps hiển thị cửa hàng, mở chỉ đường.  
8. **Chat** — Chat real-time (Firebase Firestore/Realtime DB) hoặc custom socket.  

---

## Cấu trúc chính (tóm tắt từ repo)
- `app/src/main/AndroidManifest.xml`
- `app/src/main/java/com/evsaleapp/...`:
  - `ui/` (fragments & activities: `ProductListFragment`, `ProductDetailActivity`, `CartFragment`, `ChatActivity`, `MapActivity`, `LoginActivity`, `RegisterActivity`, `BillingActivity`, v.v.)
  - `data/api/` (`ApiClient`, `ApiService`)
  - `data/repository/` (`ProductRepository`, `CartManager`, `SharedPrefManager`)
  - `model/` (`Product`, `ChatMessage`, v.v.)
  - `utils/` (`CartManager`, helpers)
- `res/layout/` (activity/fragment/item layouts)
- `res/drawable/`, `res/mipmap/`, `res/anim/`, `res/values/` (colors, strings, themes)
- `build.gradle`, `gradle.properties`, `local.properties`

---

## Yêu cầu môi trường
- Android Studio Bumblebee/Chipmunk hoặc mới hơn
- JDK 11+
- Android SDK (compileSdkVersion >= 33 đề nghị)
- Google Maps API key (nếu dùng bản đồ)
- Firebase project (nếu dùng Firebase Chat/FCM)
- Backend REST API (URL cấu hình trong `local.properties` hoặc `gradle.properties`)

---

## Cài đặt & chạy
1. Clone project:
   ```bash
   git clone <your-repo-url>
   cd <project-folder>
