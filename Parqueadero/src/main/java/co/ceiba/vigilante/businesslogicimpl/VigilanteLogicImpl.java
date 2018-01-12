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

import co.ceiba.vigilante.businesslogic.VigilanteLogic;
import co.ceiba.vigilante.excepcion.VigilanteExcepcion;
import co.ceiba.vigilante.repository.ConfisysRepository;

@Service
public class VigilanteLogicImpl implements VigilanteLogic {

	static final String PARQUEADERO_PROPERTIES = "parqueadero.properties";
	static final String RESTRICCION_PLACA = "restriccionPlaca";

	@Autowired
	ConfisysRepository confisysRepository;

	@Override
	public boolean restriccionIngreso(String placa) {
		try {
			if (!placa.isEmpty()) {
				LocalDateTime fecha = LocalDateTime.now();
				String letra = this.obtenerConfisysRestriccionPlaca();
				return (placa.charAt(0) == letra.charAt(0) && ("MONDAY".equals(fecha.getDayOfWeek().name())
						|| "SUNDAY".equals(fecha.getDayOfWeek().name())));

			}

			throw new VigilanteExcepcion("La placa llego vacia");

		} catch (RuntimeException e) {
			throw new VigilanteExcepcion("Se genero un eror en el metodo restriccionIngreso. " + e.getMessage());
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
				valor += (tipoVehiculo == 0) ? horas * 1000 : horas * 500;

				hayHoras = false;
			} else if (horas > 9 && horas <= 24) {
				valor += (tipoVehiculo == 0) ? 8000 : 4000;
				hayHoras = false;
			} else {
				valor += (tipoVehiculo == 0) ? 8000 : 4000;
				horas -= 24;
			}

		}

		if (tipoVehiculo == 1 && cilindraje > 500)
			valor += 2000;

		return valor;
	}

	@Override
	public int obtenerConfisysCantidadLimiteVehiculos(int tipoVehiculo) {
		try {
			String descripcion = "";

			if (tipoVehiculo == 0)
				descripcion = "autos";
			else {
				descripcion = "motos";
			}

			return Integer.parseInt(confisysRepository.findByDescripcion(descripcion).getValor());
		} catch (Exception e) {
			throw new VigilanteExcepcion("Error al consultar el congisys para cantidad e vehiculos." + e.getMessage());
		}
	}

	@Override
	public String obtenerPropertiesByName(String nombre) {
		try {
			if (!nombre.isEmpty()) {
				Properties p = new Properties();
				p.load(new FileInputStream(PARQUEADERO_PROPERTIES));
				if (p.getProperty(nombre) != null)
					return p.getProperty(nombre);
				else {
					throw new VigilanteExcepcion("La propiedad no existe.");
				}
			}

			throw new VigilanteExcepcion("Excepcion: El nombre de la propiedad viene vacio.");

		} catch (Exception e) {
			throw new VigilanteExcepcion("Excepcion al obtener el archivo properties. " + e.getMessage());
		}

	}

	@Override
	public void actualizarPropertiesByName(String nombre, String valor) {
		Properties p = new Properties();
		try {
			if (!nombre.isEmpty() && !valor.isEmpty()) {
				p.load(new FileInputStream(PARQUEADERO_PROPERTIES));
				if (p.getProperty(nombre) != null) {
					p.setProperty(nombre, valor);
					p.store(new FileOutputStream(PARQUEADERO_PROPERTIES),
							"modificacion archivo para propiedad: " + nombre);
					return;
				} else {
					throw new VigilanteExcepcion("La propiedad no existe.");
				}
			}

			throw new VigilanteExcepcion("Excepcion: El nombre o valor de la propiedad viene vacio.");

		} catch (IOException e) {
			throw new VigilanteExcepcion("Excepcion al actualizar el archivo properties. " + e.getMessage());
		}

	}

	@Override
	public String obtenerConfisysRestriccionPlaca() {
		return confisysRepository.findByDescripcion(RESTRICCION_PLACA).getValor();
	}

}
