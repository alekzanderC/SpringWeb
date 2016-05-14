package mx.com.act.dao;

import java.util.List;

import mx.com.act.pojo.Admin;

public interface AdminDao {

	/**
	 * Persiste un administrador
	 * @param admin
	 * @return boolean
	 */
	public boolean save(Admin admin );
	
	/**
	 * Consulta administradores
	 * @return List<Admin>
	 */
	public List<Admin> findAll();
	
	/**
	 * Consulta administrador por Id
	 * @param id
	 * @return Admin
	 */
	public Admin findById(int id);
	
	/**
	 * Consulta administrador por nombre
	 * @param nombre
	 * @return List<Admin>
	 */
	public List<Admin> findByName(String nombre);
	
	/**
	 * Actualiza administrador
	 * @param admin
	 * @return boolean
	 */
	public boolean update(Admin admin);
	
	/**
	 * Elimina administrador por id
	 * @param id
	 * @return boolean
	 */
	public boolean delete(int id);
}
