package com.jkapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.ResetPasswordByCodeListener;

import com.jkapp.R;

public class ResetPasswordActivity extends Activity implements OnClickListener {

	private EditText reset_phone;
	private Button get_reset_code;
	private EditText reset_code;
	private Button verify_reset_code;
	private EditText newpassword;
	private Button commit;

	String code, phone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resetpassword);

		init();
		setListener();
	}

	private void init() {

		((TextView) findViewById(R.id.tvTopTitleCenter)).setText("修改密码");

		reset_phone = (EditText) findViewById(R.id.reset_phone);
		get_reset_code = (Button) findViewById(R.id.get_reset_code);
		reset_code = (EditText) findViewById(R.id.reset_code);
		verify_reset_code = (Button) findViewById(R.id.verify_reset_code);
		newpassword = (EditText) findViewById(R.id.new_password);
		commit = (Button) findViewById(R.id.commit);
	}

	private void setListener() {
		this.get_reset_code.setOnClickListener(this);
		this.verify_reset_code.setOnClickListener(this);
		this.commit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.get_reset_code:
			getResetCode();
			break;
		case R.id.verify_reset_code:
			verifyResetCode();
			break;
		case R.id.commit:
			commit();
			break;
		default:
			break;
		}
	}

	private void commit() {
		String newpassword1 = this.newpassword.getText().toString();
		if ("".equals(newpassword)) {
			return;
		} else {
			BmobUser.resetPasswordBySMSCode(getApplicationContext(), code,
					newpassword1, new ResetPasswordByCodeListener() {
						@Override
						public void done(BmobException arg0) {
							if (arg0 == null) {
								Toast.makeText(getApplicationContext(),
										"密码重置成功！", Toast.LENGTH_SHORT).show();
								Intent intent = new Intent(
										ResetPasswordActivity.this,
										LoginActivity.class);
								startActivity(intent);
							} else {
								Toast.makeText(getApplicationContext(),
										"密码重置失败！！", Toast.LENGTH_SHORT).show();
							}
						}
					});
		}
	}

	private void verifyResetCode() {
		code = this.reset_code.getText().toString();
		if ("".equals(code)) {
			Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
			return ;
		} else {
			ResetPasswordActivity.this.newpassword.setVisibility(View.VISIBLE);
			ResetPasswordActivity.this.commit.setVisibility(View.VISIBLE);
		}
	}

	private void getResetCode() {

		phone = this.reset_phone.getText().toString();
		if ("".equals(phone)) {
			Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		BmobSMS.requestSMSCode(this, phone, "密码重置",
				new RequestSMSCodeListener() {
					@Override
					public void done(Integer arg0, BmobException arg1) {
						Toast.makeText(ResetPasswordActivity.this, "获取验证码成功！",
								Toast.LENGTH_SHORT).show();
					}
				});
	}

}
