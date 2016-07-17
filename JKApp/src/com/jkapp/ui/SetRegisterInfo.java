package com.jkapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;

import com.jkapp.R;
import com.jkapp.models.config;
import com.jkapp.models.userInfo;

public class SetRegisterInfo extends Activity {

	private Button submit;
	private EditText nickName;
	private EditText r_phone, r_password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setregisterinfo);

		initViews();
		this.submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				submit();
			}
		});
	}

	private void initViews() {
		Bmob.initialize(getApplicationContext(), config.applicationId);

		((TextView) findViewById(R.id.tvTopTitleCenter)).setText("注册");
		this.submit = (Button) findViewById(R.id.submit);
		this.r_phone = (EditText) findViewById(R.id.r_phone);
		this.r_password = (EditText) findViewById(R.id.r_password);
		this.nickName = (EditText) findViewById(R.id.r_nickName);

		Intent it = getIntent();
		this.r_phone.setText(it.getStringExtra("phone"));

	}

	private void submit() {

		String nickname = this.nickName.getText().toString();
		String phone = this.r_phone.getText().toString();
		String password = this.r_password.getText().toString();

		if ("".equals(nickname) || "".equals(phone) || "".equals(password)) {
			return;
		} else {

			userInfo user = new userInfo();

			user.setUsername(phone);
			user.setPassword(password);
			user.setNickName(nickname);
			user.setMobilePhoneNumber(phone);
			
			user.signUp(getApplicationContext(), new SaveListener() {
				@Override
				public void onSuccess() {
					Toast.makeText(getApplicationContext(), "注册成功！",
							Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(SetRegisterInfo.this,
							LoginActivity.class);
					startActivity(intent);
				}

				@Override
				public void onFailure(int arg0, String arg1) {
					Toast.makeText(getApplicationContext(), "注册失败！" + arg1,
							Toast.LENGTH_SHORT).show();
					Log.i("failedtoregister", arg1);
					SetRegisterInfo.this.nickName.setText(arg1);
				}
			});
			
		}
	}
}
