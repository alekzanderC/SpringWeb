package mx.com.act.daoImpl;

import javax.sql.DataSource;

import mx.com.act.dao.AdminDao;
import mx.com.act.pojo.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

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

}
