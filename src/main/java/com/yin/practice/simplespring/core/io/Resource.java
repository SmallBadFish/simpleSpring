package com.yin.practice.simplespring.core.io;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

public interface Resource extends InputStreamSource {
	boolean exists();

	boolean isReadable();

	boolean isOpen();

	URL getURL() throws IOException;

	URI getURI() throws IOException;

	long contentLength() throws IOException;

	long lastModified() throws IOException;

	Resource createRelative(String relativePath) throws IOException;

	String getFilename();

	File getFile() throws IOException;

	String getDescription();
}
