package co.ceiba.vigilante.businesslogicimpl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.vigilante.businesslogic.BusinessLogic;
import co.ceiba.vigilante.repository.VigilanteRepository;


@Service
public class BussinesLogicImpl implements BusinessLogic {

	@Autowired
	VigilanteRepository vigilanteRepository;
	
	@Override
	public boolean restriccionIngreso(String placa) {

		try {
		LocalDateTime fecha = LocalDateTime.now();
		String[] letras = vigilanteRepository.obtenerConfisysByName("restriccionPlaca").split("-");
		if (letras.length > 0) {
			for (String ss : letras) {
				if (placa.charAt(0) == ss.charAt(0) && ("MONDAY".equals(fecha.getDayOfWeek().name())
						|| "SUNDAY".equals(fecha.getDayOfWeek().name())))
					return true;
			}
		}
		return false;
	
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	@Override
	public long obtenerDiferenciaHoras(Date fechaIngreso, Date fechaSalida) {

		Calendar calFechaIngreso = Calendar.getInstance();
		Calendar calFechaRetiro = Calendar.getInstance();
		calFechaIngreso.setTime(fechaIngreso);
		calFechaRetiro.setTime(fechaSalida);

		long diff = calFechaRetiro.getTimeInMillis() - calFechaIngreso.getTimeInMillis();

		long diffMinutos = Math.abs(diff / (60 * 1000));
		long restominutos = diffMinutos % 60;

		long diffHoras = (diff / (60 * 60 * 1000));

		if (restominutos > 0)
			++diffHoras;

		return diffHoras;
	}

	@Override
	public float obtenerValorPagar(long horas, int tipoVehiculo, int cilindraje) {
		boolean hayHoras = true;
		float valor = 0;

		while (hayHoras) {

			if (horas <= 9) {
				if (tipoVehiculo == 0)
					valor += horas * 1000;
				else {
					valor += horas * 500;
				}
				hayHoras = false;
			} else if (horas > 9 && horas <= 24) {
				if (tipoVehiculo == 0) {
					valor += 8000;
				} else {
					valor += 4000;
				}
				hayHoras = false;
			} else {
				if (tipoVehiculo == 0)
					valor += 8000;
				else {
					valor += 4000;
				}
				horas -= 24;
			}

		}

		if (tipoVehiculo == 1 && cilindraje > 500)
			valor += 2000;

		return valor;
	}

	@Override
	public int cantidadLimiteVehiculos(int tipoVehiculo) {
		
		return 20;
	}

	@Override
	public String obtenerPropertiesByName(String nombre) {
		try {
		Properties p = new Properties();
		p.load(new FileInputStream("parqueadero.properties"));
		return (p.getProperty(nombre));
		
		}catch(Exception e) {
			System.out.println("Excepcion: "+e.getMessage());
		}
		return null;
	}

	@Override
	public void actualizarPropertiesByName(String nombre, String valor) {
		Properties p = new Properties();
		try {
			p.load(new FileInputStream("parqueadero.properties"));
			p.setProperty(nombre, valor);
			p.store(new FileOutputStream("parqueadero.properties"), null);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	

}