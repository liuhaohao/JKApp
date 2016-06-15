package com.jkapp.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;

public class IoUtils {

	private IoUtils() {
		throw new UnsupportedOperationException("can't instantiate class IoUtils");
	}

	public static boolean close(Closeable stream) {
		if(stream != null) {
			try {
				stream.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public static <T extends Serializable> T copyObject(Object obj) {
		if(obj == null) {
			return null;
		}
		ByteArrayOutputStream baos = null;
		ObjectOutputStream oos = null;
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			bais = new ByteArrayInputStream(baos.toByteArray());
			ois = new ObjectInputStream(bais);
			Object object = ois.readObject();
			return (T) object;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			close(oos);
			close(baos);
			close(ois);
			close(bais);
		}
	}
	
	public static OutputStream getOutputStream(String path) {
		return getOutputStream(path, true);
	}
	
	public static OutputStream getOutputStream(String path, boolean autoCreated) {
		if(!FileUtils.exist(path)) {
			if(!autoCreated || !FileUtils.createFile(path)) {
				return null;
			}
		}
		OutputStream os = null;
		try {
			os = new FileOutputStream(path);
			return os;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String inputStream2String(InputStream is) {
		return inputStream2String(is, null);
	}
	
	public static String inputStream2String(InputStream is, Charset charset) {
		if(is == null) {
			return null;
		}
		byte[] buffer = new byte[1024];
		int length;
		StringBuilder sb = new StringBuilder();
		try {
			while((length = is.read(buffer)) != -1) {
				sb.append(new String(buffer, 0, length, charset));
			}
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
