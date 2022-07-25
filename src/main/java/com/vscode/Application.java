package com.vscode;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vscode.common.repo.DefaultRepoImpl;
import com.vscode.entity.login.LoginUserBean;

@SpringBootApplication
//@EnableJpaRepositories( basePackages = {"com.vscode.common.repo"}, repositoryBaseClass = DefaultRepoImpl.class)
public class Application {

//	@Autowired
//	private LoginUserRepo loginUserRepo;
//
////	@Autowired 
////	private StudentAttendanceRepo studentAttendanceRepo;
////	
////	@Autowired
////	private ArchiveRepo archiveRepo;
//
//	@PostConstruct
//	public void saveAll() {
//		loginUserRepo.save(new LoginUserBean("abc", "ut_ut"));
//	}
//
//	@GetMapping("/getUserIdAndPassword")
//	public List<LoginUserBean> getUserIdAndPassword() {
//		return (List<LoginUserBean>) loginUserRepo.findAll();
//	}

//	public List<StudentAttendanceBean> getAttendance() {
//		return studentAttendanceRepo.findAll();
//	}
//	
//	public List<StudentArchiveBean> getLeftStudents() {
//		return archiveRepo.findAll();
//	}

	public static void main(String[] args) {
try {
		SpringApplication.run(Application.class, args);
}catch(Exception e) {
	e.printStackTrace();
}
	}

//	@Override
//	public void run(String... args) throws Exception {
//		loginUserRepo.save(new LoginUserBean("abcdefg", "ut_ut"));
//	}

}
