package com.jkapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.VerifySMSCodeListener;

import com.jkapp.R;
import com.jkapp.models.config;

public class RegisterActivity extends BaseActivity implements OnClickListener {

	private EditText r_phone, code;
	private Button get_code, verify_code;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		initView();

	}

	private void initView() {
		Bmob.initialize(getApplicationContext(), config.applicationId);
		
		((TextView) findViewById(R.id.tvTopTitleCenter)).setText("注册");
		r_phone = (EditText) findViewById(R.id.r_phone);
		code = (EditText) findViewById(R.id.code);
		get_code = (Button) findViewById(R.id.get_code);
		verify_code = (Button) findViewById(R.id.verify_code);

		get_code.setOnClickListener(this);
		verify_code.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.get_code:
			getCode();
			break;
		case R.id.verify_code:
			verifyCode();
			break;
		default:
			break;
		}
	}

	private void getCode() {
		if (!"".equals(r_phone.getText().toString())) {
			BmobSMS.requestSMSCode(RegisterActivity.this, r_phone.getText()
					.toString(), "注册验证码", new RequestSMSCodeListener() {

				@Override
				public void done(Integer arg0, BmobException arg1) {
					Toast.makeText(RegisterActivity.this, "获取验证码成功！",
							Toast.LENGTH_SHORT).show();
				}

			});
		}
	}

	private void verifyCode() {
		if ("".equals(code.getText().toString())) {
			Toast.makeText(RegisterActivity.this, "验证码为空！", Toast.LENGTH_SHORT)
					.show();
		} else {
			String code = this.code.getText().toString();
			BmobSMS.verifySmsCode(RegisterActivity.this, r_phone.getText()
					.toString(), code, new VerifySMSCodeListener() {

				@Override
				public void done(BmobException arg0) {
					// arg0==null 表示验证成功
					if (null == arg0) {
						Intent it = new Intent(RegisterActivity.this,
								SetRegisterInfo.class);
						it.putExtra("phone", RegisterActivity.this.r_phone
								.getText().toString());
						startActivity(it);
					} else {
						Toast.makeText(RegisterActivity.this, "验证码不正确！",
								Toast.LENGTH_SHORT).show();
					}
				}
			});
		}
	}
}
