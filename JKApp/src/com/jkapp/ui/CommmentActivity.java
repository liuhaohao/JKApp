package com.jkapp.ui;

import com.jkapp.R;
import com.jkapp.bean.Comment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;

public class CommmentActivity extends Activity {
	private ListView commenmtlist;
	private EditText detail;
	private Button commit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Bmob.initialize(this, "e242efe23c9dabdd705a70b9774c2405");
		
		setContentView(R.layout.activity_listview);
		commenmtlist = (ListView) super.findViewById(R.id.commentlist);
		detail = (EditText) super.findViewById(R.id.content);
		commit = (Button) super.findViewById(R.id.commit);
	}

	public void submit(View view) {

		String mdetail = detail.getText().toString();
		// String mdetail="sdasdasdasdas";
		String muserid = "h0y3TTTB";
		String mname = "测试用";
		String manswer = "j4RpOOOS";
		String mcommenter = "C5gv222B";
		
		if (mdetail.equals("") || muserid.equals("") || mname.equals("") || manswer.equals("")
				|| mcommenter.equals("")) {
			Toast.makeText(CommmentActivity.this, "不能为空", Toast.LENGTH_LONG).show();
			return;
		}
        
		Comment commentobj = new Comment();
		commentobj.setDetail(mdetail);
		commentobj.setUserid(muserid);
		commentobj.setName(mname);
		// commentobj.setanswer(manswer);
		// commentobj.setcommenter(mcommenter);
		// commentobj.save(listener)
		commentobj.save(CommmentActivity.this, new SaveListener() {
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub/
				Toast.makeText(CommmentActivity.this, "评论失败", Toast.LENGTH_LONG).show();
		}

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(CommmentActivity.this, "评论成功", Toast.LENGTH_LONG).show();
			}

	});
		
		

	}
}
