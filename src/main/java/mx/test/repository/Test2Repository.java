package mx.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.test.entity.Test2;

@Repository
public interface Test2Repository extends CrudRepository<Test2, Integer> {
	
	@Query("SELECT i FROM Test2 i WHERE i.nir = :nir AND i.serie = :serie AND i.numIni <= :numero AND i.numFin >= :numero")
	List<Test2> getValidNumber(@Param("nir") Integer nir,
			@Param("serie") Integer serie, 
			@Param("numero") Integer numero);

}
