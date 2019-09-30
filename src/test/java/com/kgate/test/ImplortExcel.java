package com.kgate.test;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.util.ResourceUtils;

public class ImplortExcel {

	public static void main(String[] args) throws FileNotFoundException {

		File file = ResourceUtils.getFile("classpath:/attendance.xml");
		System.out.println("FIle Path: " + file.getAbsolutePath());
	}
}
