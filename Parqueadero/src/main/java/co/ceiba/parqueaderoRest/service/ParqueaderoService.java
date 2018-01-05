package co.ceiba.parqueaderoRest.service;


import co.ceiba.parqueaderoRest.dominio.Auto;
import co.ceiba.parqueaderoRest.dominio.Moto;


public interface ParqueaderoService {

	boolean ingresarAuto(Auto auto) throws Exception;

	boolean ingresarMoto(Moto moto) throws Exception;
	
	boolean retirarAuto(Auto auto) throws Exception;
	
	boolean retirarMoto(Moto moto) throws Exception;
	
}
