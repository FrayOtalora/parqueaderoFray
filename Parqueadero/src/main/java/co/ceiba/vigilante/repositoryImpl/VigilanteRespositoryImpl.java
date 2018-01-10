package co.ceiba.vigilante.repositoryImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Repository;

import co.ceiba.vigilante.component.ConexionMysql;
import co.ceiba.vigilante.dominio.Parking;
import co.ceiba.vigilante.repository.VigilanteRepository;

@Repository
public class VigilanteRespositoryImpl implements VigilanteRepository {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public boolean ingresarVehiculo(String placa, Date fechaIngreso, int tipoVehiculo, int cilindraje) {
		boolean res = false;
		try {

			String sql = "INSERT INTO parking (placa,tipoVehiculo,fechaIngreso,cilindraje)" + "VALUES ('" + placa
					+ "','" + "" + tipoVehiculo + "','" + "" + sdf.format(fechaIngreso) + "','" + "" + cilindraje
					+ "')";

			ConexionMysql.conectar();

			res = ConexionMysql.ejecutarActualizacionSQL(sql);

			ConexionMysql.desconectar();

			return res;
		} catch (Exception e) {
			throw new RuntimeException("Error al ejecutar script insert into parking. Excepcion: " + e.getMessage());

		}

	}

	@Override
	public Parking retirarVehiculo(String placa) {
		try {

			String sql = "SELECT p.* FROM parking p WHERE p.placa='" + placa + "'";

			ConexionMysql.conectar();

			ArrayList<String> res = ConexionMysql.getConsultaSQL3(sql);

			ConexionMysql.desconectar();

			if (!res.isEmpty()) {
				String[] park = res.get(0).split("&");
				// parking.setId(Integer.parseInt(park[0]));
				// parking.setFechaIngreso(java.sql.Timestamp.valueOf(park[3]));
			}

			return null;
		} catch (Exception e) {
			throw new RuntimeException("Error al ejecutar script select into parking. Excepcion: " + e.getMessage());

		}
	}

	@Override
	public boolean registrarPagoVehiculo(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String obtenerConfisysByName(String nombre) {
		
		return "A";
	}

}
