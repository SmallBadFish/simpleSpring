package com.yin.practice.simplespring.core.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.springframework.core.NestedIOException;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

/**
 * Resource接口的抽象类
 *
 */
public abstract class AbstractResource implements Resource {

	public boolean exists() {
		try {
			return getFile().exists();
		} catch (IOException ex) {
			try {
				InputStream is = getInputStream();
				is.close();
				return true;
			} catch (Throwable isEx) {
				return false;
			}
		}
	}

	public boolean isReadable() {
		return true;
	}

	public boolean isOpen() {
		return false;
	}

	/**
	 * 假设该资源不能被处理为URL,因此抛出FileNotFoudException;
	 */
	public URL getURL() throws IOException {
		throw new FileNotFoundException(getDescription() + " cannot be resolved to URL");
	}

	/**
	 * 该方法构建一个URI根据返回的URL
	 */
	public URI getURI() throws IOException {
		URL url = getURL();
		try {
			return ResourceUtils.toURI(url);
		} catch (URISyntaxException e) {
			throw new NestedIOException("Invalid URI [" + url + "]", e);
		}
	}

	/**
	 * 假设该资源不能被处理为绝对路径的文件,因此抛出FileNotFoundException异常
	 */
	public File getFile() throws IOException {
		throw new FileNotFoundException(getDescription() + " cannot be resolved to absolute file path");
	}

	/**
	 * 读取整个输入流并计算其长度
	 */
	public long contentLength() throws IOException {
		InputStream is = this.getInputStream();
		Assert.state(is != null, "resource input stream must not be null");
		try {

			long size = 0;
			byte[] buf = new byte[255];
			int read;
			while ((read = is.read(buf)) != -1) {
				size += read;
			}
			return size;
		} finally {
			try {
				is.close();
			} catch (IOException ex) {

			}
		}
	}

	public long lastModified() throws IOException {
		long lastModified = getFileForLastModifiedCheck().lastModified();
		if (lastModified == 0L) {
			throw new FileNotFoundException(getDescription()
					+ " cannot be resolved in the file system for resolving its last-modified timestamp");
		}
		return lastModified;
	}

	protected File getFileForLastModifiedCheck() throws IOException {
		return getFile();
	}

	/**
	 * 相对路径不能新建Resource对象
	 */
	public Resource createRelative(String relativePath) throws IOException {
		throw new FileNotFoundException("Cannot create a relative resource for " + getDescription());
	}

	/**
	 * 总是返回null,因为这个资源对象类型没有文件名(不是一个一个文件)
	 */
	public String getFilename() {
		return null;
	}

	@Override
	public String toString() {
		return getDescription();
	}

	@Override
	public boolean equals(Object obj) {
		return (obj == this || (obj instanceof Resource && ((Resource) obj).getDescription().equals(getDescription())));
	}

	@Override
	public int hashCode() {
		return getDescription().hashCode();
	}
}
