package mx.com.act.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import mx.com.act.dao.AdminDao;
import mx.com.act.pojo.Admin;
import mx.com.act.pojo.AdminRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("adminDao")
public class AdminDaoImpl implements AdminDao {

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public boolean save(Admin admin) {

//		MapSqlParameterSource paramMap = new MapSqlParameterSource();
//		paramMap.addValue("nombre", admin.getNombre());
//		paramMap.addValue("cargo", admin.getCargo());
//		paramMap.addValue("fechaCreacion", admin.getFechaCreacion());
		
		BeanPropertySqlParameterSource paramMap = new BeanPropertySqlParameterSource(
				admin);// ES NECESARIO QUE LOS NOMBRES ESTEN MAPEADOS IDENTICOS A BD

		return jdbcTemplate.update("insert into admin(nombre,cargo,fechaCreacion) values(:nombre,:cargo,:fechaCreacion)",
						            paramMap) == 1;
	}

	@Override
	public List<Admin> findAll() {
		
		return jdbcTemplate.query("select * from admin", new RowMapper<Admin>() {

			@Override
			public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
				Admin admin = new Admin();
				
				admin.setCargo(rs.getString("cargo"));
				admin.setNombre(rs.getString("nombre"));
				admin.setFechaCreacion(rs.getTimestamp("fechaCreacion"));
				admin.setIdAdmin(rs.getInt("idAdmin"));
				
				return admin;
			}
			
		});
	}
	
	@Override
	public Admin findById(int id) {
		return jdbcTemplate.queryForObject("select * from admin where idAdmin=:idAd", 
				new MapSqlParameterSource("idAd", id), new AdminRowMapper());		
	}
	
	@Override
	public List<Admin> findByName(String nombre) {
		return jdbcTemplate.query("select * from admin where nombre like :nombre", 
				new MapSqlParameterSource("nombre","%"+nombre+"%"), new AdminRowMapper()); 
	}

	@Override
	public boolean update(Admin admin) {
		return jdbcTemplate.update("update admin set nombre=:nombre, cargo=:cargo, fechaCreacion=:fechaCreacion where idAdmin=:idAdmin", 
				new BeanPropertySqlParameterSource(admin)) == 1;
	}

	@Override
	public boolean delete(int id) {
		
		return jdbcTemplate.update("delete from admin where idAdmin=:idAd", new MapSqlParameterSource("idAd",id)) == 1;
	}

	@Transactional
	@Override
	public int[] saveAll(List<Admin> admins) {
		SqlParameterSource[	] batchArgs = SqlParameterSourceUtils.createBatch(admins.toArray());
		return jdbcTemplate.batchUpdate("insert into admin(idAdmin,nombre,cargo,fechaCreacion) "
								+ "values(:idAdmin, :nombre,:cargo,:fechaCreacion)",
								batchArgs);		
	}
}
