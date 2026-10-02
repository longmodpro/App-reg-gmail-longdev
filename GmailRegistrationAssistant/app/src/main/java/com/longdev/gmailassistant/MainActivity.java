package com.longdev.gmailassistant;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText firstName, lastName, username;
    private final int ink = Color.rgb(32, 54, 91);
    private final int muted = Color.rgb(75, 83, 96);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView scroll = new ScrollView(this);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(22), dp(24), dp(22), dp(28));
        root.setBackgroundColor(Color.rgb(247, 249, 252));
        scroll.addView(root);
        setContentView(scroll);

        TextView title = new TextView(this);
        title.setText("Gmail Registration Assistant");
        title.setTextSize(24);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setTextColor(ink);
        root.addView(title);

        TextView intro = new TextView(this);
        intro.setText("Chuẩn bị thông tin rồi mở trang đăng ký Google. Bạn tự nhập và gửi biểu mẫu trên trang Google.");
        intro.setTextSize(15);
        intro.setTextColor(muted);
        intro.setPadding(0, dp(10), 0, dp(18));
        root.addView(intro);

        firstName = addField(root, "Tên", "Nhập tên");
        lastName = addField(root, "Họ", "Nhập họ");
        username = addField(root, "Tên người dùng mong muốn (không bắt buộc)", "Ví dụ: tenban");

        Button open = makeButton("Mở trang đăng ký Google");
        open.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://accounts.google.com/signup"));
            try {
                startActivity(intent);
            } catch (Exception e) {
                toast("Không tìm thấy trình duyệt.");
            }
        });
        root.addView(open);

        TextView copyTitle = new TextView(this);
        copyTitle.setText("Sao chép thông tin");
        copyTitle.setTextSize(18);
        copyTitle.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        copyTitle.setTextColor(ink);
        copyTitle.setPadding(0, dp(22), 0, dp(8));
        root.addView(copyTitle);

        addCopyButton(root, "Sao chép tên", "Tên", firstName);
        addCopyButton(root, "Sao chép họ", "Họ", lastName);
        addCopyButton(root, "Sao chép tên người dùng", "Tên người dùng", username);

        TextView note = new TextView(this);
        note.setText("LƯU Ý BẢO MẬT\n"
                + "• Không nhập mật khẩu, OTP hoặc mã khôi phục vào ứng dụng này.\n"
                + "• Dữ liệu chỉ dùng trong màn hình hiện tại; ứng dụng không gửi dữ liệu đến máy chủ.\n"
                + "• Tên người dùng chỉ là gợi ý; Google sẽ kiểm tra tính khả dụng.\n"
                + "• Nếu gặp CAPTCHA hoặc xác minh, hãy tự hoàn tất trên trang Google.\n"
                + "• Bạn tự kiểm tra thông tin và tự gửi biểu mẫu đăng ký.");
        note.setTextSize(14);
        note.setTextColor(muted);
        note.setPadding(dp(14), dp(14), dp(14), dp(14));
        note.setBackgroundColor(Color.rgb(232, 238, 247));
        LinearLayout.LayoutParams noteParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        noteParams.topMargin = dp(18);
        root.addView(note, noteParams);
    }

    private EditText addField(LinearLayout root, String label, String hint) {
        TextView tv = new TextView(this);
        tv.setText(label);
        tv.setTextSize(14);
        tv.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tv.setTextColor(ink);
        LinearLayout.LayoutParams labelParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        labelParams.topMargin = dp(8);
        root.addView(tv, labelParams);

        EditText edit = new EditText(this);
        edit.setSingleLine(true);
        edit.setHint(hint);
        edit.setTextSize(16);
        edit.setPadding(dp(12), dp(8), dp(12), dp(8));
        root.addView(edit, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        return edit;
    }

    private Button makeButton(String text) {
        Button b = new Button(this);
        b.setText(text);
        b.setAllCaps(false);
        return b;
    }

    private void addCopyButton(LinearLayout root, String label, String clipLabel, EditText field) {
        Button b = makeButton(label);
        b.setOnClickListener(v -> {
            String value = field.getText().toString().trim();
            if (value.isEmpty()) {
                toast("Hãy nhập thông tin trước.");
                return;
            }
            copy(clipLabel, value);
        });
        root.addView(b);
    }

    private void copy(String label, String value) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.setPrimaryClip(ClipData.newPlainText(label, value));
        toast("Đã sao chép " + label + ".");
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private int dp(int value) {
        return (int) (value * getResources().getDisplayMetrics().density + 0.5f);
    }
}
