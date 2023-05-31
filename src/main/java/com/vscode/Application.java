package com.vscode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//@EnableJpaRepositories( basePackages = {"com.vscode.common.repo"}, repositoryBaseClass = DefaultRepoImpl.class)
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@Override
//	public void run(String... args) throws Exception {
//		loginUserRepo.save(new LoginUserBean("abcdefg", "ut_ut"));
//	}

}
