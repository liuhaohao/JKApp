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
		Bmob.initialize(getApplicationContext(), "c43189d2cd70b8d586b39240f582d143");
		
	}
}