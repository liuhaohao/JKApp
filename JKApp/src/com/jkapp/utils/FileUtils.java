package com.jkapp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.text.TextUtils;

public class FileUtils {

	private FileUtils() {
		throw new UnsupportedOperationException("can't instantiate class FileUtils");
	}
	
	public static boolean exist(String path) {
		if(TextUtils.isEmpty(path)) {
			return false;
		}
		return exist(new File(path));
	}
	
	public static boolean exist(File file) {
		if(file == null) {
			return false;
		}
		return file.exists();
	}
	
	public static boolean isFile(String path) {
		if(!exist(path)) {
			return false;
		}
		File f = new File(path);
		return f.isFile();
	}
	
	public static boolean isDirectory(String path) {
		if(!exist(path)) {
			return false;
		}
		File f = new File(path);
		return f.isDirectory();
	}
	
	public static boolean isHidden(String path) {
		if(!exist(path)) {
			return false;
		}
		File f = new File(path);
		return f.isHidden();
	}
	
	public static String[] listFile(String path) {
		if(!isDirectory(path)) {
			return null;
		}
		File f = new File(path);
		return f.list();
	}

	public static boolean isEmptyDirectory(String path) {
		String[] childs = listFile(path);
		if(childs != null && childs.length > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static long getSize(String path) {
		return calculateSize(path);
	}
	
	//递归计算目录大小
	private static long calculateSize(String path) {
		if(isDirectory(path)) {
			String[] childs = listFile(path);
			if(childs != null) {
				long count = 0;
				for(String p : childs) {
					count += calculateSize(p);
				}
				return count;
			} else {
				return 0;
			}
		} else if(isFile(path)) {
			return getFileSize(path);
		} else {
			return 0;
		}
	}
	
	public static long getFileSize(String path) {
		if(isFile(path)) {
			InputStream is = null;
			try {
				is = new FileInputStream(path);
				return is.available();
			} catch (IOException e) {
				e.printStackTrace();
				return 0;
			} finally {
				IoUtils.close(is);
			}
		} else {
			return 0;
		}
	}
	
	public static void delete(String path) {
		if(isDirectory(path)) {
			deleteDirectory(path);
		} else if(isFile(path)) {
			deleteFile(path);
		}
	}
	
	//删除目录
	private static void deleteDirectory(String path) {
		if(isDirectory(path)) {
			if(isEmptyDirectory(path)) {
				deleteFile(path);
			} else {
				String[] childs = listFile(path);
				if(childs != null) {
					for(String p : childs) {
						deleteDirectory(p);
					}
				} else {
					
				}
			}
		} else if(isFile(path)) {
			deleteFile(path);
		} else {
			
		}
	}
	
	public static boolean deleteFile(String path) {
		if(isFile(path)) {
			File f = new File(path);
			return f.delete();
		}
		return false;
	}
	
	public static void clear(String path) {
		if(isDirectory(path)) {
			String[] childs = listFile(path);
			if(childs != null) {
				for(String p : childs) {
					delete(p);
				}
			}
		} else if(isFile(path)) {
			clearFile(path);
		}
	}
	
	public static boolean clearFile(String path) {
		if(isFile(path)) {
			delete(path);
			return createFile(path);
		}
		return false;
	}
	
	public static boolean createFile(String path) {
		File f = new File(path);
		String parent = f.getParent();
		if(exist(parent) || createDirectory(parent)) {
			try {
				return f.createNewFile();	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static boolean createDirectory(String path) {
		if(!TextUtils.isEmpty(path)) {
			File f = new File(path);
			return f.mkdirs();
		} else {
			return false;
		}
	}
}
