package com.jkapp.utils;

import java.util.ArrayList;
import java.util.List;

import com.jkapp.bean.MainData;
import com.jkapp.models.*;

import android.content.Context;
import android.util.Log;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * 下载网络数据类
 * 
 */

public class DataSyncManager {

	public List<MainData> getMainDatas(Context context) {
		final List<MainData> list = new ArrayList<MainData>();
		final List<MainData> list2 = new ArrayList<MainData>();
		BmobQuery<PostInfo> query = new BmobQuery<PostInfo>();
		query.setLimit(100);
		query.findObjects(context, new FindListener<PostInfo>() {

			@Override
			public void onSuccess(List<PostInfo> postInfos) {
				// TODO Auto-generated method stub
				for (PostInfo postInfo : postInfos) {
					MainData mainData = new MainData();
					String str1 = postInfo.getObjectId();
					mainData.setObjectId(str1);
					String str2 = postInfo.getTitle();
					mainData.setTitle(str2);
					String str3 = postInfo.getMethod();
					mainData.setMethod(str3);
					String str4 = postInfo.getPublisher();
					mainData.setPublisher(str4);
					Log.d("Test", mainData.getTitle());
					list.add(mainData);

				}
				for (int i = list.size() - 1; i > 0; i--) {
					list2.add(list.get(i));
				}
			}

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub

			}
		});

		return list2;
	}
}
