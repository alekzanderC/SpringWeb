package mx.com.act;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		admin.setNombre("Miguel Angel Cervantes");
		admin.setCargo("Admin");
		admin.setFechaCreacion(ts);
		
		try {
			
			//SAVE ADMIN
			adminDao.save(admin);
			
			//FIND ALL ADMINS
			List<Admin> admins = adminDao.findAll();
			System.out.println("ADMINS ON BD: ");
			for (Admin adm : admins) {
				System.out.println(adm);
			}
			
			//FIND ADMIN BY ID
			Admin adm = adminDao.findById(1);
			System.out.println("ADMIN FOUND BY ID 1: "+adm);
			
			//FIND BY NAME
			List<Admin> adminName = adminDao.findByName("al"); 
			for (Admin admin2 : adminName) {
				System.out.println("CONSULTED BY NAME (al): "+admin2);
			}
			
			//UPDATE ADMIN
			Admin adminUpdate = new Admin();
			adminUpdate.setIdAdmin(1);
			adminUpdate.setNombre("Guisselle Cervantes");
			adminUpdate.setCargo("Administradora");
			adminDao.update(adminUpdate);
			
			//FIND ALL ADMINS FOR UPDATED ADMINS
			List<Admin> adminsUpdate = adminDao.findAll(); System.out.println("ADMINS AFTER UPDATE: ");
			for (Admin admUp : adminsUpdate) {
				System.out.println(admUp);
			}	
			
			//DELETE ADMIN
			adminDao.delete(2);
			
			//FIND ALL ADMINS FOR DELETED ADMINS
			List<Admin> adminsDelet = adminDao.findAll(); 
			System.out.println("ADMINS AFTER DELETION: ");
			for (Admin admDe : adminsDelet) {
				System.out.println(admDe);
			}
			
			//INSERT A BATCH
			List<Admin> administradores = new ArrayList<>();
			administradores.add(new Admin("Pedro", "jefe de ingenieria", ts));
			administradores.add(new Admin("Eduardo Diaz", "Lider de proyecto", ts));
			administradores.add(new Admin("Juan Loman", "Lider Usa", ts));
			
			int[] vals = adminDao.saveAll(administradores);
			
			for (int i : vals) {
				System.out.println("Fichas afectadas para la operación: "+i);
			}
			
			//FIND ALL ADMINS AFTER ADMINS BATCH
			List<Admin> adminsBatch = adminDao.findAll(); 
			System.out.println("ADMINS AFTER BATCH: ");
			for (Admin adminBa : adminsBatch) {
				System.out.println(adminBa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
