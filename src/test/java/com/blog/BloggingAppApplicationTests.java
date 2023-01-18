package com.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blog.repositories.UserRepo;

@SpringBootTest
class BloggingAppApplicationTests {

	@Autowired
	private UserRepo repo;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void rapoTest() {
		String className = repo.getClass().getName();
		Package package1 = repo.getClass().getPackage();
		System.out.println(className);
		System.out.println(package1.getName());
		
	}

}
