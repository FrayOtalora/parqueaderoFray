package co.ceiba.vigilante.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.vigilante.businesslogic.BusinessLogic;
import co.ceiba.vigilante.dominio.Parking;
import co.ceiba.vigilante.dominio.Vehiculo;
import co.ceiba.vigilante.repository.VigilanteRepository;
import co.ceiba.vigilante.service.VigilanteService;

@Service
public class VigilanteServiceImpl implements VigilanteService {

	@Autowired
	VigilanteRepository vigilanteRepository;

	@Autowired
	BusinessLogic businessLogic;

	@Override
	public void ingresarVehiculo(Vehiculo vehiculo) throws Exception {
		try {

			String nombreProperties = (vehiculo.getTipo() == 0) ? "autos" : "motos";

			int cantidadVehiculos = Integer.parseInt(businessLogic.obtenerPropertiesByName(nombreProperties));
			System.out.println("Cantidad de vehiculos " + cantidadVehiculos);

			if (cantidadVehiculos < businessLogic.cantidadLimiteVehiculos(vehiculo.getTipo())
					&& !businessLogic.restriccionIngreso(vehiculo.getPlaca()))
				vigilanteRepository.ingresarVehiculo(vehiculo.getPlaca(), new Date(), vehiculo.getTipo(),
						vehiculo.getCilindraje());

			businessLogic.actualizarPropertiesByName(nombreProperties, String.valueOf(++cantidadVehiculos));

			
		} catch (Exception e) {
			System.out.println("Error al guardar en base de datos");

		}

	}

	@Override
	public void registrarPagoVehiculo(Parking parking) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public Parking retirarVehiculo(Vehiculo vehiculo) {

		try {
			Parking park = new Parking();
			park.setVehiculo(vehiculo);
			vigilanteRepository.retirarVehiculo(vehiculo.getPlaca());
			park.setFechaRetiro(new Date());
			return park;

		} catch (Exception e) {
			throw new RuntimeException("Error al guardar en base de datos" + e.getMessage());
		}

	}

}
