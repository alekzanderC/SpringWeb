package mx.com.act;

import java.sql.Timestamp;
import java.util.Date;

import mx.com.act.dao.AdminDao;
import mx.com.act.pojo.Admin;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_config.xml");
		AdminDao adminDao = (AdminDao) applicationContext.getBean("adminDao");
		
		Timestamp ts = new Timestamp(new Date().getTime());
		
		Admin admin = new Admin();
		admin.setNombre("Alejandro Cervantes");
		admin.setCargo("Administrador");
		admin.setFechaCreacion(ts);
		
		if(adminDao.save(admin)){
			System.out.println("Admin Salvado correctamente ...");
		}else{
			System.out.println("Error al insertar administrador ...");
		}
		
	}

}
