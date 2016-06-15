package com.jkapp;

import java.util.ArrayList;
import java.util.List;

import com.jkapp.db.OrmDatabaseHelper;

import android.app.Application;
import cn.bmob.v3.Bmob;

public class App extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
		init();
	}
	
	private void init() {
		
		//DB
		List<Class<?>> classList = new ArrayList<Class<?>>();
		OrmDatabaseHelper.setDBTClass(classList);
		//Bmob
		Bmob.initialize(getApplicationContext(), "e242efe23c9dabdd705a70b9774c2405");
		
	}
}