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
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

import com.jkapp.R;
import com.jkapp.models.userInfo;

public class LoginActivity extends Activity implements OnClickListener {

	private EditText userId, password;
	private Button login;
	private TextView failedToLogin, newUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		userInfo user = BmobUser.getCurrentUser(getApplicationContext(), userInfo.class);
		if(user!=null){
			Intent it = new Intent(LoginActivity.this,MainActivity.class);
			this.startActivity(it);
		}
		initView();
		setListener();
	}

	private void initView() {
		((TextView) findViewById(R.id.tvTopTitleCenter)).setText("登录");

		userId = (EditText) findViewById(R.id.userId);
		password = (EditText) findViewById(R.id.password);
		login = (Button) findViewById(R.id.login);
		failedToLogin = (TextView) findViewById(R.id.resetPassword);
		newUser = (TextView) findViewById(R.id.register);
	}

	private void setListener() {
		this.login.setOnClickListener(this);
		this.newUser.setOnClickListener(this);
		this.failedToLogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.login:
			login();
			break;
		case R.id.resetPassword:
			resetPassword();
			break;
		case R.id.register:
			Register();
			break;
		}
	}

	
	private void resetPassword() {
		Intent intent = new Intent(this, ResetPasswordActivity.class);
		startActivity(intent);
	}

	private void login() {

		String phone = this.userId.getText().toString();
		String password = this.password.getText().toString();

		if ("".equals(phone) || "".equals(password)) {
			Toast.makeText(this, "手机号或者密码为空！", Toast.LENGTH_SHORT).show();
			return;
		} else {
			BmobUser.loginByAccount(this, phone, password,
					new LogInListener<userInfo>() {
						@Override
						public void done(userInfo arg0, BmobException arg1) {
							if (arg1 == null) {
								Toast.makeText(getApplicationContext(), "登陆成功",
										Toast.LENGTH_SHORT).show();

								Intent intent = new Intent(LoginActivity.this,
										MainActivity.class);
								startActivity(intent);
							} else {
								Toast.makeText(getApplicationContext(),
										"手机号或者密码有无误！"+arg1, Toast.LENGTH_SHORT)
										.show();
							}
						}
					});

		}

	}

	private void Register() {
		Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
		this.startActivity(intent);
	}

}
