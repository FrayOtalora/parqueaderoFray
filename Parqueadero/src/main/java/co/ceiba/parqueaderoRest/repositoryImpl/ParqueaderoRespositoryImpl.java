package co.ceiba.parqueaderoRest.repositoryImpl;

import org.springframework.stereotype.Repository;

import co.ceiba.parqueaderoRest.repository.ParqueaderoRepository;
import co.ceiba.parqueaderoRest.dominio.Parking;


@Repository
public class ParqueaderoRespositoryImpl implements ParqueaderoRepository{

	@Override
	public boolean ingresarAuto(Parking parking) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retirarAuto(Parking parking) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean registrarPagoAuto(Parking parking) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}



}
