package co.ceiba.vigilante.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.vigilante.businesslogic.BusinessLogic;
import co.ceiba.vigilante.dominio.Parking;
import co.ceiba.vigilante.dominio.Vehiculo;
import co.ceiba.vigilante.excepcion.VigilanteExcepcion;
import co.ceiba.vigilante.repository.VigilanteRepository;
import co.ceiba.vigilante.service.VigilanteService;

@Service
public class VigilanteServiceImpl implements VigilanteService {

	@Autowired
	VigilanteRepository vigilanteRepository;

	@Autowired
	BusinessLogic businessLogic;

	@Override
	public void ingresarVehiculo(Vehiculo vehiculo) {
		try {

			String nombreProperties = (vehiculo.getTipo() == 0) ? "autos" : "motos";

			int cantidadVehiculos = Integer.parseInt(businessLogic.obtenerPropertiesByName(nombreProperties));

			if (cantidadVehiculos < businessLogic.cantidadLimiteVehiculos(vehiculo.getTipo())
					&& !businessLogic.restriccionIngreso(vehiculo.getPlaca()))
				vigilanteRepository.ingresarVehiculo(vehiculo.getPlaca(), new Date(), vehiculo.getTipo(),
						vehiculo.getCilindraje());

			businessLogic.actualizarPropertiesByName(nombreProperties, String.valueOf(++cantidadVehiculos));

		} catch (Exception e) {
			throw new VigilanteExcepcion("Error al ingresar el vehiculo " + e.getMessage());

		}

	}

	@Override
	public void registrarPagoVehiculo(Parking parking) {
		// TODO Auto-generated method stub
	}

	@Override
	public Parking retirarVehiculo(String placa) {

		try {
			Parking park;
			park = vigilanteRepository.retirarVehiculo(placa);
			park.setFechaRetiro(new Date());
			park.setValorPago(businessLogic.obtenerValorPagar(
					businessLogic.obtenerDiferenciaHoras(park.getFechaIngreso(), park.getFechaRetiro()),
					park.getVehiculo().getTipo(), park.getVehiculo().getCilindraje()));

			return park;

		} catch (Exception e) {
			throw new VigilanteExcepcion("Error al guardar en base de datos" + e.getMessage());
		}

	}

}
