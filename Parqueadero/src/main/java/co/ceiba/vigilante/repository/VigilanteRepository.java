package co.ceiba.vigilante.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.ceiba.vigilante.dominio.Parking;

public interface VigilanteRepository extends CrudRepository<Parking, Long>{


	@Query("select p from parking p where p.placa=?1 and p.fechaSalida IS NULL")
	Parking findByPlacaAndFechaSalida(String placa);


	
}