package co.ceiba.vigilante.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.ceiba.vigilante.dominio.Confisys;

public interface ConfisysRepository extends CrudRepository<Confisys, Long> {

	@Query("Select c from confisys c where c.descripcion=?1")
	Confisys findByDescripcion(String descripcion);

}
