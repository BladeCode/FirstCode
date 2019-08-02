package org.incoder.uiwidget.code;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.Toast;

import org.incoder.uiwidget.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CodeActivity extends AppCompatActivity {

    @BindView(R.id.edit_solid)
    CodeEditText editSolid;
    @BindView(R.id.edit_underline)
    CodeEditText editUnderline;
    @BindView(R.id.edit_hollow)
    CodeEditText editHollow;
    @BindView(R.id.rb_password_yes)
    RadioButton rbPasswordYes;
    @BindView(R.id.rb_password_no)
    RadioButton rbPasswordNo;
    @BindView(R.id.rb_cursor_yes)
    RadioButton rbCursorYes;
    @BindView(R.id.rb_cursor_no)
    RadioButton rbCursorNo;

    @OnClick({R.id.rb_password_yes, R.id.rb_password_no})
    void passwordType() {
        if (rbPasswordYes.isChecked()) {
            editSolid.setPassword(true);
            editUnderline.setPassword(true);
            editHollow.setPassword(true);
        } else {
            editSolid.setPassword(false);
            editUnderline.setPassword(false);
            editHollow.setPassword(false);
        }
    }

    @OnClick({R.id.rb_cursor_yes, R.id.rb_password_no})
    void cursorShow() {
        if (rbCursorYes.isChecked()) {
            editSolid.setShowCursor(true);
            editUnderline.setShowCursor(true);
            editHollow.setShowCursor(true);
        } else {
            editSolid.setShowCursor(false);
            editUnderline.setShowCursor(false);
            editHollow.setShowCursor(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        ButterKnife.bind(this);

        editSolid.setPassword(true);
        editUnderline.setPassword(true);
        editHollow.setPassword(true);

        editSolid.setShowCursor(true);
        editUnderline.setShowCursor(true);
        editHollow.setShowCursor(true);

        editSolid.setTextChangedListener(new CodeEditText.TextChangedListener() {
            @Override
            public void textChanged(CharSequence changeText) {

            }

            @Override
            public void textCompleted(CharSequence text) {
                Toast.makeText(CodeActivity.this, "input content:" + text, Toast.LENGTH_SHORT).show();
            }
        });
        editUnderline.setTextChangedListener(new CodeEditText.TextChangedListener() {
            @Override
            public void textChanged(CharSequence changeText) {

            }

            @Override
            public void textCompleted(CharSequence text) {
                Toast.makeText(CodeActivity.this, "input content:" + text, Toast.LENGTH_SHORT).show();
            }
        });
        editHollow.setTextChangedListener(new CodeEditText.TextChangedListener() {
            @Override
            public void textChanged(CharSequence changeText) {

            }

            @Override
            public void textCompleted(CharSequence text) {
                Toast.makeText(CodeActivity.this, "input content:" + text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
