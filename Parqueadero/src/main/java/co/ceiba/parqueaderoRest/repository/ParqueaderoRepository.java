package co.ceiba.parqueaderoRest.repository;

import co.ceiba.parqueaderoRest.dominio.Parking;

public interface ParqueaderoRepository {

	boolean ingresarAuto(Parking parking) throws Exception;

	boolean retirarAuto(Parking parking) throws Exception;

	boolean registrarPagoAuto(Parking parking) throws Exception;
	
}