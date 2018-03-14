package com.Nico.TpFinal.Model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DaoRepOrd extends CrudRepository<RepOrd,Integer>{

	List<RepOrd> findByOrdenDeTrabajo(OrdenDeTrabajo ord);
}
