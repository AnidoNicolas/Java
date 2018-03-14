package com.Nico.TpFinal.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Nico.TpFinal.Model.DaoOrdenDeTrabajo;
import com.Nico.TpFinal.Model.DaoPropietario;
import com.Nico.TpFinal.Model.DaoRepOrd;
import com.Nico.TpFinal.Model.DaoRepuesto;
import com.Nico.TpFinal.Model.OrdenDeTrabajo;
import com.Nico.TpFinal.Model.Propietario;
import com.Nico.TpFinal.Model.RepOrd;
import com.Nico.TpFinal.Model.Repuesto;

@Controller
public class ModelController {

	@Autowired
	DaoOrdenDeTrabajo daoOrdenDeTrabajo;
	
	@Autowired
	DaoPropietario daoPropietario;
	
	@Autowired
	DaoRepOrd daoRepOrd;
	
	@Autowired
	DaoRepuesto daoRepuesto;
	
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public ModelAndView menuPrincipal(){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("menu");
		return modelAndView;
	}
	
	@RequestMapping(value="/cargaPropietario",method=RequestMethod.GET)
	public ModelAndView cargaPropietarioGET(){
		ModelAndView modelAndView=new ModelAndView();
		Propietario p=new Propietario();
		modelAndView.addObject("prop",p);
		modelAndView.setViewName("cargaPropietario");
		return modelAndView;
	}
	
	@RequestMapping(value="/cargaPropietario",method=RequestMethod.POST)
	public ModelAndView cargaPropietarioPOST(@ModelAttribute Propietario propietario){
		ModelAndView modelAndView=new ModelAndView();
		boolean existe=false;
		if(daoPropietario.exists(propietario.getDni())){
			existe=true;
		}
		if(existe==false){
			daoPropietario.save(propietario);
			modelAndView.setViewName("menu");
		}
		else{
			modelAndView.setViewName("usuarioInvalido");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/cargaOrdenDeTrabajo",method=RequestMethod.GET)
	public ModelAndView cargaOrdenDeTrabajoGET(){
		ModelAndView modelAndView=new ModelAndView();
		OrdenDeTrabajo ordenDeTrabajo=new OrdenDeTrabajo();
		Propietario propietario=new Propietario();
		modelAndView.addObject("ord",ordenDeTrabajo);
		modelAndView.addObject("prop",propietario);
		modelAndView.setViewName("cargaOrden");
		return modelAndView;
	}
	
	@RequestMapping(value="/cargaOrdenDeTrabajo",method=RequestMethod.POST)
	public ModelAndView cargaOrdenDeTrabajoPOST(@ModelAttribute OrdenDeTrabajo ordenDeTrabajo, @ModelAttribute Propietario propietario){
		ModelAndView modelAndView=new ModelAndView();
		
		if(daoPropietario.exists(propietario.getDni())==true){
			ordenDeTrabajo.setPropietario(daoPropietario.findOne(propietario.getDni()));
			modelAndView.addObject("ord",daoOrdenDeTrabajo.findOne((int) daoOrdenDeTrabajo.count()));
			modelAndView.addObject("listaRep",daoRepuesto.findAll());
			modelAndView.addObject("repOrd",new RepOrd());
			daoOrdenDeTrabajo.save(ordenDeTrabajo);
			modelAndView.setViewName("menu");	
		}
		else{
			modelAndView.setViewName("ordenInvalida");
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/listaOrdenes")
	public ModelAndView listaOrdenes(){
		ModelAndView modelAndView=new ModelAndView();
		Boolean aux=new Boolean(false);
		List<OrdenDeTrabajo>lstAbiertas=new ArrayList<OrdenDeTrabajo>();
		for(int i=1;i<=daoOrdenDeTrabajo.count();i++){
			if(aux.equals(daoOrdenDeTrabajo.findOne(i).isEstado())){
				lstAbiertas.add(daoOrdenDeTrabajo.findOne(i));
			}		
		}
		modelAndView.addObject("rep",new Repuesto());
		modelAndView.addObject("lstOrd", lstAbiertas);
		if(lstAbiertas.size()==0){
			modelAndView.setViewName("listaVacia");
		}
		else{
			modelAndView.setViewName("listaOrdenes");
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/agregarRepuesto", method=RequestMethod.GET)
	ModelAndView agregarRepuestoGET(@RequestParam ("idOrd")int idOrd){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("repOrd",new RepOrd());
		modelAndView.addObject("ord",daoOrdenDeTrabajo.findOne(idOrd));
		modelAndView.addObject("lstRep",daoRepuesto.findAll());
		modelAndView.setViewName("agregarRepuesto");
		return modelAndView;
	}
	
	@RequestMapping(value="/agregarRepuesto", method=RequestMethod.POST)
	ModelAndView agregarRepuestoPOST(@ModelAttribute RepOrd repOrd){
		ModelAndView mv=new ModelAndView();
		daoRepOrd.save(repOrd);
		mv.addObject("repOrd",repOrd);
		mv.setViewName("menu");
		return mv;
	}
	
	@RequestMapping(value="/cerrarOrden",method=RequestMethod.GET)
	ModelAndView cerrarOrdenGET(@RequestParam ("idOrd")int idOrd){
		ModelAndView mv=new ModelAndView();
		mv.addObject("repOrd",new RepOrd());
		float total=0;
		mv.addObject("ord",daoOrdenDeTrabajo.findOne(idOrd));
		mv.addObject("lstRep",daoRepuesto.findAll());
		

		for(RepOrd r: daoRepOrd.findAll()){
			if(r.getOrdenDeTrabajo().getId()==idOrd){
				total=total+r.calcularTotal();
			}
		}
		daoOrdenDeTrabajo.findOne(idOrd).setEstado(true);
		daoOrdenDeTrabajo.findOne(idOrd).setCosto(total);
		daoOrdenDeTrabajo.save(daoOrdenDeTrabajo.findOne(idOrd));
		mv.setViewName("ordenCerrada");
		return mv;
	}
	
	
	
	@RequestMapping(value="/listaOrdenesCerradas")
	public ModelAndView listaOrdenesCerradas(){
		ModelAndView modelAndView=new ModelAndView();
		List<OrdenDeTrabajo>lstCerradas=new ArrayList<OrdenDeTrabajo>();
		for(int i=1;i<=daoOrdenDeTrabajo.count();i++){
				if(daoOrdenDeTrabajo.findOne(i).isEstado()){
					lstCerradas.add(daoOrdenDeTrabajo.findOne(i));
				}		
		}
		modelAndView.addObject("rep",new Repuesto());
		modelAndView.addObject("lstCer", lstCerradas);
		modelAndView.setViewName("listaOrdenesCerradas");
		return modelAndView;
	}
	
	@RequestMapping(value="/detalle")
	public ModelAndView detallar(@RequestParam("idOrd")int idOrd){
		ModelAndView mv=new ModelAndView();
		boolean existe=false;
		int cantAux=0;
		List<RepOrd>lstRO=new ArrayList<RepOrd>();
		List<RepOrd>lstRO2=new ArrayList<RepOrd>();
		for(RepOrd r:daoRepOrd.findAll()){
			if(r.getOrdenDeTrabajo().getId()==idOrd){
				
				lstRO.add(r);		
			}
		}
		mv.addObject("repOrd",new RepOrd());
		mv.addObject("lstR",lstRO);
		mv.setViewName("detalle");
		return mv;
	}

	
	
	
	
}
