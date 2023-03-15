package com.blog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.services.FileServicess;

@Service
public class FileServicessImpl implements FileServicess {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		// get the original file name
		String originalFilename = file.getOriginalFilename();
		System.out.println("Original File name:: " + originalFilename);

		// random name generator file
		String randomID = UUID.randomUUID().toString();
		String fileName1 = randomID.concat(originalFilename.substring(originalFilename.lastIndexOf(".")));

		String filePath = path + File.separator + fileName1;

		File file2 = new File(path);

		if (!file2.exists()) {
			file2.mkdir();
		}
		Files.copy(file.getInputStream(), Paths.get(filePath));

		return fileName1;
	}

	@Override
	public InputStream getResources(String path, String fileName) throws FileNotFoundException {
		String fullpath = path+File.separator + fileName;
		System.out.println("Full path of the file public InputStream getResources(String path, String fileName) throws FileNotFoundException  "+fullpath);
		InputStream inputStream = new FileInputStream(fullpath);
		return inputStream;
	}

}
