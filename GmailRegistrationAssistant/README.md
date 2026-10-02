# Gmail Registration Assistant

Ứng dụng Android Kotlin tối giản để chuẩn bị thông tin và mở trang đăng ký Google chính thức.

## Tính năng
- Giao diện Android riêng.
- Trường tên, họ và tên người dùng mong muốn.
- Sao chép từng trường vào clipboard để tự dán trong trang Google.
- Không yêu cầu quyền Internet, không có máy chủ/API.
- Không thu thập mật khẩu, OTP hay dữ liệu xác minh.
- Người dùng tự xử lý CAPTCHA và tự hoàn tất đăng ký.

## Yêu cầu
- Android Studio với Android SDK 35 và JDK 17.
- Hoặc GitHub Actions để build APK.

## Build bằng Android Studio
1. Mở thư mục dự án trong Android Studio.
2. Chờ Gradle Sync.
3. Chọn **Build > Build APK(s)**.
4. APK debug thường nằm tại `app/build/outputs/apk/debug/app-debug.apk`.

## Build bằng GitHub Actions
Đẩy toàn bộ nội dung dự án lên repository GitHub. Workflow `.github/workflows/android.yml` sẽ tự build APK khi push hoặc chạy thủ công. Tải APK trong mục **Actions > workflow run > Artifacts**.

## Lưu ý
Ứng dụng không tự động gửi biểu mẫu đăng ký và không vượt qua CAPTCHA. Tài khoản phải được đăng ký trực tiếp trên trang Google và tuân thủ điều khoản của Google.
