package co.ceiba.parqueaderoRest.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.parqueaderoRest.consumer.ParqueaderoConsumer;
import co.ceiba.parqueaderoRest.service.ParqueaderoService;
import co.ceiba.parqueaderoUtilities.dominio.ConfiSys;
import co.ceiba.parqueaderoRest.dominio.Parking;
import co.ceiba.parqueaderoRest.dominio.Vehiculo;

@RestController
public class ParqueaderoController {

	@Autowired
	ParqueaderoService parqueaderoService;

	@Autowired
	ParqueaderoConsumer parqueaderoConsumer;

	/*
	 * Metodo encargado del ingreso de los vehiculos al parqueadero
	 * 
	 * @param ingresa objeto de tipo Auto
	 * 
	 * @return mensaje con datos de ingreso
	 */
	@RequestMapping(value = "/ingresarVehiculo", method = RequestMethod.POST)
	public String ingresarVehiculo(@RequestBody Vehiculo vehiculo) throws Exception {
		try {

			ConfiSys restriccionLetra = new ConfiSys("restriccionLetras", "A-R");
			ConfiSys limiteVehiculo = null;
			int cantidadVehiculos = 0;

			// Se crea un properties para mantener la cantidad de autos que hay en el
			// momento parqueados
			Properties p = new Properties();
			p.load(new FileInputStream("parqueadero.properties"));

			// Se obtiene la cantidad limite de vehiculos permitidos segun su tipo
			if (vehiculo.getTipo() == 0) {
				limiteVehiculo = new ConfiSys("limiteAutos", "");
				cantidadVehiculos = Integer.parseInt(p.getProperty("autos"));
			}

			else {
				limiteVehiculo = new ConfiSys("limiteMotos", "");
				cantidadVehiculos = Integer.parseInt(p.getProperty("motos"));
			}

			try {
				// Obtiene el valor de la cantidad limite para el parqueo de autos
				limiteVehiculo = parqueaderoConsumer.obtenerConfisysNombre(limiteVehiculo);
			} catch (Exception exc) {
				System.out.println(
						"Ocurrio un error al momento de consultar el confisys de limite de autos" + exc.getMessage());
				return "OCURRIO UN ERROR AL CONSULTAR EL CONFISYS";
			}

			// RECORRE PARA SABER SI LA PLCA CORRESPONDE A ALGUNA LETRA QUE TIENE
			// RESTRICCION
			if (cantidadVehiculos < Integer.parseInt(limiteVehiculo.getValor())) {
				Parking parking = new Parking(vehiculo, new Date());

				if (this.restriccionIngreso(parking, restriccionLetra))
					return "EL VEHICULO TIENE RESTRICCION PARA LOS DIAS LUNES Y DOMINGO";

				if (parqueaderoService.ingresarVehiculo(parking)) {
					p.setProperty("autos", String.valueOf(++cantidadVehiculos));
					p.store(new FileOutputStream("parqueadero.properties"), null);
					return "VEHICULO GUARDADO, QUE TENGA BUEN DIA";
				} else {
					return "Ocurrio un error al ingresar el vehiculo al parqueadero";
				}
			}

			return "NO HAY MAS CUPO";
		} catch (

		Exception e) {
			return "ocurrio una error. Excepcion: " + e.getMessage();
		}

	}

	@RequestMapping(value = "/retirarVehiculo", method = RequestMethod.POST, consumes = "application/json")
	public Parking retirarVehiculo(@RequestBody Vehiculo vehiculo) throws Exception {

		Parking parking = parqueaderoService.retirarVehiculo(vehiculo);

		long horas = this.obtenerDiferenciaHoras(parking);

		return this.obtenerValorPagar(horas, parking);

	}

	@RequestMapping(value = "/registrarPagoVehiculo", method = RequestMethod.POST)
	public boolean registrarPagoVehiculo(Parking parking) {

		System.out.println(parking.toString());

		return true;
	}

	private boolean restriccionIngreso(Parking parking, ConfiSys confisys) {

		String[] letras = confisys.getValor().split("-");
		if (letras.length > 0) {
			for (String ss : letras) {
				if (parking.getVehiculo().getPlaca().charAt(0) == ss.charAt(0)
						&& (parking.getFechaIngreso().getDay() == 0 || parking.getFechaIngreso().getDay() == 1))
					return true;
			}
		}
		return false;

	}

	public long obtenerDiferenciaHoras(Parking parking) {

		Calendar calFechaIngreso = Calendar.getInstance();
		Calendar calFechaRetiro = Calendar.getInstance();
		calFechaIngreso.setTime(parking.getFechaIngreso());
		calFechaRetiro.setTime(parking.getFechaRetiro());

		long diff = calFechaRetiro.getTimeInMillis() - calFechaIngreso.getTimeInMillis();

		// calcular la diferencia en minutos
		long diffMinutos = Math.abs(diff / (60 * 1000));
		long restominutos = diffMinutos % 60;

		long diffHoras = (diff / (60 * 60 * 1000));

		if (restominutos > 0)
			++diffHoras;

		return diffHoras;
	}

	public Parking obtenerValorPagar(long horas, Parking parking) {
		boolean hayHoras = true;
		float valor = 0;

		while (hayHoras) {

			if (horas <= 9) {
				if (parking.getVehiculo().getTipo() == 0)
					valor += horas * 1000;
				else {
					valor += horas * 500;
				}
				hayHoras = false;
			} else if (horas > 9 && horas <= 24) {
				if (parking.getVehiculo().getTipo() == 0) {
					valor += 8000;
				} else {
					valor += 4000;
				}
				hayHoras = false;
			} else {
				if (parking.getVehiculo().getTipo() == 0)
					valor += 8000;
				else {
					valor += 4000;
				}
				horas -= 24;
			}

		}
		parking.setValorPago(valor);
		return parking;
	}

}
