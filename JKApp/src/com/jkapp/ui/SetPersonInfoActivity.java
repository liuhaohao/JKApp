package com.jkapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.UpdateListener;

import com.jkapp.R;
import com.jkapp.models.config;
import com.jkapp.models.userInfo;

public class SetPersonInfoActivity extends Activity implements OnClickListener {

	private Button save, btnTopTitleLeft;
	private EditText nickname, email, Occupation;

	private userInfo user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_personinfo);

		initViews();
		setListener();
	}

	private void initViews() {
		Bmob.initialize(getApplicationContext(), config.applicationId);
		user = BmobUser.getCurrentUser(getApplicationContext(), userInfo.class);

		btnTopTitleLeft = (Button) findViewById(R.id.btnTopTitleLeft);
		save = (Button) findViewById(R.id.save);
		nickname = (EditText) findViewById(R.id.set_nickname);
		email = (EditText) findViewById(R.id.set_email);
		Occupation = (EditText) findViewById(R.id.Occupation);
		
		if (user.getNickName() != null)
			nickname.setText(user.getNickName());
		if (user.getEmail() != null)
			email.setText(user.getEmail());
		if (user.getOccupation() != null)
			Occupation.setText(user.getOccupation());

	}

	private void setListener() {
		btnTopTitleLeft.setOnClickListener(this);
		save.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.save:
			save();
			break;
		case R.id.btnTopTitleLeft:
			Intent it = new Intent(getApplicationContext(), MainActivity.class);
			startActivity(it);
			break;
		}
	}

	private void save() {
		String newNickname;
		String newemail;
		String newOccupation;
		newNickname = this.nickname.getText().toString();
		newemail = this.email.getText().toString();
		newOccupation = this.Occupation.getText().toString();

		if ("".equals(newNickname) || "".equals(newemail)
				|| "".equals(newOccupation)) {
			Toast.makeText(getApplicationContext(), "个人信息不能为空",
					Toast.LENGTH_SHORT).show();
			return;
		} else {

			userInfo newuser = new userInfo();
			newuser.setNickName(newNickname);
			newuser.setEmail(newemail);
			newuser.setOccupation(newOccupation);

			newuser.update(getApplicationContext(), user.getObjectId(),
					new UpdateListener() {

						@Override
						public void onSuccess() {
							Toast.makeText(getApplicationContext(),
									R.string.succeed_save, Toast.LENGTH_SHORT)
									.show();
							Intent it = new Intent(getApplicationContext(), MainActivity.class);
							startActivity(it);
						}

						@Override
						public void onFailure(int arg0, String arg1) {
							Toast.makeText(getApplicationContext(),
									R.string.failed_save + arg1,
									Toast.LENGTH_SHORT).show();
						}
					});
		}
	}
}
