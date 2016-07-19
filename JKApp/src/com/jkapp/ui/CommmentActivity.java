package com.jkapp.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;

import com.jkapp.R;
import com.jkapp.adapter.CommentAdapter;
import com.jkapp.models.Comment;
import com.jkapp.models.config;
import com.jkapp.models.userInfo;

public class CommmentActivity extends Activity {

	private ListView commentlist;
	private EditText detail;
	private Button commit;

	private TextView question;
	private TextView answer;
	private TextView publisher_name;
	private String postId, publisher, title, method;

	private CommentAdapter adapter;

	private List<Comment> commentdata = new ArrayList<Comment>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);

		init();
		setViews();
	}

	private void init() {
		Bmob.initialize(getApplicationContext(), config.applicationId);

		commentlist = (ListView) findViewById(R.id.commentlist);
		detail = (EditText) findViewById(R.id.content);
		commit = (Button) findViewById(R.id.commit);
		question = (TextView) findViewById(R.id.question);
		answer = (TextView) findViewById(R.id.answer);
		publisher_name = (TextView) findViewById(R.id.publisher_name);

		Intent it = super.getIntent();
		postId = it.getStringExtra("postId");
		publisher = it.getStringExtra("publisher");
		title = it.getStringExtra("title");
		method = it.getStringExtra("method");
	}

	private void setViews() {
		setPublisherName();
		this.question.setText(title);
		this.answer.setText(method);

		this.commit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				submit();
			}
		});
		setComments();
	}

	/*
	 * 根据postId 查询对应的评论信息
	 */
	private void setComments() {

		BmobQuery<Comment> query = new BmobQuery<Comment>();
		query.addWhereEqualTo("postId", postId);
		query.setLimit(50);
		query.findObjects(getApplicationContext(), new FindListener<Comment>() {

			@Override
			public void onSuccess(List<Comment> arg0) {
				if (arg0.size() == 0) {
				} else {
					adapter = new CommentAdapter(getApplicationContext(),
							R.layout.common_listview_item, arg0);
					commentlist.setAdapter(adapter);
				}
			}

			@Override
			public void onError(int arg0, String arg1) {
			}
		});
	}

	private void setPublisherName() {
		BmobQuery query = new BmobQuery<userInfo>();
		query.getObject(getApplicationContext(), publisher,
				new GetListener<userInfo>() {

					@Override
					public void onSuccess(userInfo arg0) {
						publisher_name.setText(arg0.getNickName());
					}

					@Override
					public void onFailure(int arg0, String arg1) {
					}

				});
	}

	/*
	 * 设置评论人的 名称，评论内容，被评论的帖子的id
	 */
	public void submit() {
		userInfo user = BmobUser.getCurrentUser(getApplicationContext(),
				userInfo.class);

		if ("".equals(detail.getText().toString())) {
			Toast.makeText(CommmentActivity.this, "不能为空！", Toast.LENGTH_LONG)
					.show();
			return;
		}
		final Comment commentobj = new Comment();

		commentobj.setName(user.getNickName());
		commentobj.setDetail(detail.getText().toString());
		commentobj.setPostId(postId);
		commentobj.save(CommmentActivity.this, new SaveListener() {

			@Override
			public void onFailure(int arg0, String arg1) {
				Toast.makeText(CommmentActivity.this, "评论失败", Toast.LENGTH_LONG)
						.show();
			}

			@Override
			public void onSuccess() {
				Toast.makeText(CommmentActivity.this, "评论成功", Toast.LENGTH_LONG)
						.show();

				// 评论成功后刷新评论listview
				setComments();
				detail.setText("");
			}

		});

	}
}
