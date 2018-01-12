package co.ceiba.vigilante.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.vigilante.businesslogic.VigilanteLogic;
import co.ceiba.vigilante.dominio.Parking;
import co.ceiba.vigilante.excepcion.VigilanteExcepcion;
import co.ceiba.vigilante.repository.VigilanteRepository;
import co.ceiba.vigilante.service.VigilanteService;

@Service
public class VigilanteServiceImpl implements VigilanteService {

	@Autowired
	VigilanteRepository vigilanteRepository;

	@Autowired
	VigilanteLogic businessLogic;

	@Override
	public void ingresarVehiculo(Parking parking) {
		try {

			if (parking == null)
				throw new VigilanteExcepcion("El objeto ingresa vacio");

			String nombreProperties = "";

			if (parking.getTipoVehiculo() == 0)
				nombreProperties = "autos";

			else if (parking.getTipoVehiculo() == 1){
				nombreProperties = "motos";
			}

			if (nombreProperties.isEmpty())
				throw new VigilanteExcepcion("El tipo de vehiculo no se maneja en el parqueadero");

			int cantidadVehiculos = Integer.parseInt(businessLogic.obtenerPropertiesByName(nombreProperties));

			if (cantidadVehiculos < businessLogic.obtenerConfisysCantidadLimiteVehiculos(parking.getTipoVehiculo())
					&& !businessLogic.restriccionIngreso(parking.getPlaca())) {

				parking.setFechaIngreso(new Date());

				vigilanteRepository.save(parking);

				businessLogic.actualizarPropertiesByName(nombreProperties, String.valueOf(++cantidadVehiculos));
			}

		} catch (Exception e) {
			throw new VigilanteExcepcion("Error al ingresar el vehiculo " + e.getMessage());

		}

	}

	@Override
	public Parking retirarVehiculo(String placa) {

		try {
			Parking park = vigilanteRepository.findByPlacaAndFechaSalida(placa);

			if (park == null)
				throw new VigilanteExcepcion("El objeto retornado de base de datos es vacio");

			park.setFechaSalida(new Date());
			park.setValorPago(businessLogic.obtenerValorPagar(
					businessLogic.obtenerDiferenciaHoras(park.getFechaIngreso(), park.getFechaSalida()),
					park.getTipoVehiculo(), park.getCilindraje()));

			return park;

		} catch (Exception e) {
			throw new VigilanteExcepcion("Error al guardar en base de datos" + e.getMessage());
		}

	}

	@Override
	public void registrarPagoVehiculo(Parking parking) {

		try {
			if (parking == null)
				throw new VigilanteExcepcion("El objeto ingresa vacio");

			String nombreProperties = "";

			if (parking.getTipoVehiculo() == 0)
				nombreProperties = "autos";

			else if (parking.getTipoVehiculo() == 1){
				nombreProperties = "motos";
			}

			int cantidadVehiculos = Integer.parseInt(businessLogic.obtenerPropertiesByName(nombreProperties));

			vigilanteRepository.save(parking);

			businessLogic.actualizarPropertiesByName(nombreProperties, String.valueOf(--cantidadVehiculos));

		} catch (Exception e) {
			throw new VigilanteExcepcion("Error al registrar pago del vehiculo");
		}
	}

}
