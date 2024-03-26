package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.apiPreguntaSC.ApiPreguntaSC;
import com.example.demo.apiPreguntaSC.IPreguntaSCApi;
import com.example.demo.apiPreguntaVF.ApiPreguntaVF;
import com.example.demo.apiPreguntaVF.IPreguntaVFApi;
import com.example.demo.entity.PreguntaSC;
import com.example.demo.entity.PreguntaVF;
import com.example.demo.exception.ErrorAskNoSave;
import com.example.demo.exception.ErrorAskNoUpdate;
import com.example.demo.paginator.PageRender;
import com.example.demo.service.IPreguntaSCService;
import com.example.demo.service.IPreguntaVFService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;

@Controller
public class PreguntaController {

	@Autowired
	private IPreguntaVFService preguntaVFService;

	@Autowired
	private IPreguntaVFApi serviceapiVF;
	
	
	@Autowired
	private IPreguntaSCApi serviceapiSC;

	@Autowired
	private IPreguntaSCService preguntaSCService;


	// PARA LA ETIQUETA TITTLE DE TODAS LAS PAGINAS
	@ModelAttribute(name = "title")
	public String getTitulo() {
		return "App Test";
	}
	

	// METODOS GENERALES 
	
//	@GetMapping("/pruebaoscuro")
//	public String pruebaOscuro(Model model) {
//		model.addAttribute("titulo", "Prueba oscuro");
//		return "pruebaoscuro";
//	}
	
	//ejemplo session
	@GetMapping("/listaUsuarios")
    public String algunaPagina(HttpSession session, Model model) {
        // Recupera el nombre de usuario de la sesión HTTP
        String username = (String) session.getAttribute("username");
        // Puedes hacer lo que necesites con el nombre de usuario, como pasarlo a la vista
        model.addAttribute("username", username);
        return "usuariosLogeados";
    }
	
	

	@GetMapping("/home")
	public String home(Model model) {
		preguntaVFService.clearSet();
		preguntaSCService.clearSet();
//		
//		 boolean modoOscuro = session.getAttribute("modoOscuro") != null ? (boolean) session.getAttribute("modoOscuro") : false;
		List<String> opciones = Arrays.asList("Test ERP", "Test JS", "Test API", "Test SQL");
		model.addAttribute("opciones", opciones);

		return "index";
	}
	

	@GetMapping("/contenido")
	public String contenido(Model model) {
		preguntaVFService.clearSet();
		preguntaSCService.clearSet();
		List<String> opciones = Arrays.asList("Test ERP", "Test JS", "Test API", "Test SQL");
		model.addAttribute("opciones", opciones);

		return "contenidoTest";
	}

	@GetMapping("/opcionesDeTest")
	public String mostrandoOpcionesDeTest(Model model, @RequestParam(name = "opcion") String opcion) {
		
		preguntaVFService.clearSet();
		preguntaSCService.clearSet();

		model.addAttribute("opcion", opcion);
		if ("Test ERP".equalsIgnoreCase(opcion)) {
			model.addAttribute("encabezado", opcion);
			return "TestERP";
		} else if ("Test JS".equalsIgnoreCase(opcion)) {
			return "testJs";
		} else if ("Test API".equalsIgnoreCase(opcion)) {
			return "testApi";
		} else if("Test SQL".equalsIgnoreCase(opcion)) {
			return "testSQL";
		} else {
			return "";
		}

	}

	// TEMA APIS EN PAQUETE PRUEBAAPI
	// LISTAR LAS PREGUNTAS ACCEDIENDO A MI API PROPIA
	// PAGINADO METODOS
	
	
	@GetMapping("/listarVF")
	public String listarVF(@RequestParam(defaultValue = "0") int page, Model model) {
		preguntaVFService.clearSet();
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<PreguntaVF> preguntas = preguntaVFService.listarPaginadoVF(pageRequest);
		PageRender<PreguntaVF> pageRender = new PageRender<>("/listarVF", preguntas);
		model.addAttribute("titulo", "Listado de preguntas V-F");
		model.addAttribute("preguntas", preguntas);
		model.addAttribute("page", pageRender);
		return "listarVF";
	}

	@GetMapping("/listarSC")
	public String listarSC(@RequestParam(defaultValue = "0") int page, Model model) {
		preguntaSCService.clearSet();
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<PreguntaSC> preguntas = preguntaSCService.listarPaginadoSC(pageRequest);
		PageRender<PreguntaSC> pageRender = new PageRender<>("/listarSC", preguntas);
		model.addAttribute("titulo", "Listado de preguntas Single-Choice");
		model.addAttribute("preguntas", preguntas);
		model.addAttribute("page", pageRender);
		return "listarSC";
	}

//	@GetMapping("/listarprueba")
//	public String getListadoPre(Model model) {
//		model.addAttribute("titulo", "Listado de preguntas");
//		model.addAttribute("preguntas", serviceapi.getAllAsk()); 
//
//		return "listar";
//	}

	// GET AÑADIR PREGUNTA
	@GetMapping("/formaddSC")
	public String addPreguntaSC(Model model) {

		model.addAttribute("titulo", "Añade una pregunta Single-Choice");
		return "addPreguntaSC";
	}

	// GET AÑADIR PREGUNTA
	@GetMapping("/formaddVF")
	public String addPreguntaVF(Model model) {

		model.addAttribute("titulo", "Añade una pregunta V-F");
		return "addPreguntaVF";
	}

	// GET EDITAR PREGUNTA
	@GetMapping("/editVF/{id}")
	public String editPreguntaVF(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute("pregunta", serviceapiVF.getAllByIdVF(id));
		model.addAttribute("titulo", "Edita la pregunta V-F");
		return "editPreguntaVF";
	}

	@GetMapping("/editSC/{id}")
	public String editPreguntaSC(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute("pregunta", serviceapiSC.getAllByIdSC(id));
		model.addAttribute("titulo", "Edita la pregunta Single-Choice");
		return "editPreguntaSC";
	}

	// POST PARA EDITAR LA PREGUNTA
	@PostMapping("/editVF")
	public String postEditVF(@RequestParam(name = "pregunta-edit", defaultValue = "null") String preguntaEdit,
			@RequestParam(name = "respuestaCorrecta-edit") String respuestaCorrectaEdit, Model model,
			@RequestParam(name = "idPregunta") Long idPregunta) throws ErrorAskNoUpdate { 

		String preguntaEditada = preguntaEdit;
		String respuestaCorrectaEditada = respuestaCorrectaEdit;
		String[] opciones = { "verdadero", "falso" };
		Long idEdit = idPregunta;
		
		ApiPreguntaVF preguntaedit = new ApiPreguntaVF(idEdit, preguntaEditada, opciones, respuestaCorrectaEditada);

		if (serviceapiVF.updateAskFormVF(preguntaedit) == true) {
			return "redirect:/listarVF"; 
		} else { 
			return ""; 
		}
	}

	@PostMapping("/editSC")
	public String postEditSC(@RequestParam(name = "pregunta-edit", defaultValue = "null") String preguntaEdit,
			@RequestParam(name = "respuestaCorrecta-edit") String respuestaCorrectaEdit,
			@RequestParam(name = "opcion1-edit") String opcion1Edit,
			@RequestParam(name = "opcion2-edit") String opcion2Edit,
			@RequestParam(name = "opcion3-edit") String opcion3Edit,
			@RequestParam(name = "opcion4-edit") String opcion4Edit, Model model,
			@RequestParam(name = "idPregunta") Long idPregunta) throws ErrorAskNoUpdate {

		String preguntaEditada = preguntaEdit;
		String respuestaCorrectaEditada = respuestaCorrectaEdit;
		String[] opciones = { opcion1Edit, opcion2Edit, opcion3Edit, opcion4Edit };
		Long idEdit = idPregunta;

		ApiPreguntaSC preguntaedit = new ApiPreguntaSC(idEdit, preguntaEditada, opciones, respuestaCorrectaEditada);

		if (serviceapiSC.updateAskFormSC(preguntaedit) == true) {
			return "redirect:/listarSC";
		} else {
			return "";
		}
	}

	// POST PARA GUARDAR UNA PREGUNTA
	@PostMapping("/saveVF")
	public String saveaPreguntaVF(@RequestParam(name = "pregunta-new", defaultValue = "null") String preguntaNew,
			@RequestParam(name = "respuestaCorrecta-new", defaultValue = "null") String respuestaCorrectaNew,
			Model model) throws ErrorAskNoSave {
		String preguntaNueva = preguntaNew;
		String respuestaCorrectaNueva = respuestaCorrectaNew;
		String[] opciones = { "verdadero", "falso" };

		if (serviceapiVF.saveAskFormVF(preguntaNueva, opciones, respuestaCorrectaNueva) == true) {
			return "redirect:/listarVF";
		} else {
			return "";
		}

	}

	// POST PARA GUARDAR UNA PREGUNTA
	@PostMapping("/saveSC")
	public String savePreguntaSC(@RequestParam(name = "pregunta-new", defaultValue = "null") String preguntaNew,
			@RequestParam(name = "opcion1-new", defaultValue = "null") String opcion1New,
			@RequestParam(name = "opcion2-new", defaultValue = "null") String opcion2New,
			@RequestParam(name = "opcion3-new", defaultValue = "null") String opcion3New,
			@RequestParam(name = "opcion4-new", defaultValue = "null") String opcion4New,
			@RequestParam(name = "respuestaCorrecta-new", defaultValue = "null") String respuestaCorrectaNew,
			Model model) throws ErrorAskNoSave {

		String preguntaNueva = preguntaNew;
		String respuestaCorrectaNueva = respuestaCorrectaNew;
		String[] opciones = { opcion1New, opcion2New, opcion3New, opcion4New };

		if (serviceapiSC.saveAskFormSC(preguntaNueva, opciones, respuestaCorrectaNueva) == true) {
			return "redirect:/listarSC";
		}

		return "";

	}

	@GetMapping("/deleteVF/{id}")
	public String DeletePreguntaVF(@PathVariable(name = "id") Long id, Model model) {

		if (serviceapiVF.deleteByIdVF(id) == true) {
			return "redirect:/listarVF";
		}

		return "";
	}

	@GetMapping("/deleteSC/{id}")
	public String DeletePreguntaSC(@PathVariable(name = "id") Long id, Model model) {

		if (serviceapiSC.deleteByIdSC(id) == true) {
			return "redirect:/listarSC";
		}

		return "";
	}

	// PREGUNTAS V-F
	@GetMapping("/preguntavf")
	public String getPreguntaVF(Model model) {

		PreguntaVF preguntaAleatoriaVF = preguntaVFService.getMuchasPreguntasVF();

		if (preguntaAleatoriaVF.getId() == -1) {
			model.addAttribute("testFin", "Test finalizado");
			return "vistasVF/testFinVF";
		}
		 
		if(preguntaAleatoriaVF.getId() == 0) {
			model.addAttribute("mensajeNull", "No hay preguntas en este momento");
			return "vistasVF/testNullVF";
		}

		model.addAttribute("preguntas", preguntaAleatoriaVF);
		return "vistasVF/preguntasVFTest";
	}

	@PostMapping("/preguntavf-post")
	public String procesarFormularioVF(@RequestParam(name = "respuesta") String respuesta,
			Model model, @RequestParam(name = "idPregunta") Long idPregunta){

//		if (respuesta.equalsIgnoreCase("null")) {
//			preguntaVFService.clearSetId(idPregunta);
//			throw new ErrorOptionTestNull("Debes de seleccionar una opción");
//		}
		
		model.addAttribute("acertada", respuesta
				.equalsIgnoreCase(preguntaVFService.getPreguntaByIdVF(idPregunta).orElse(null).getRespuestaCorrecta()));
		return "vistasVF/resultadoVF";
	}

	// PREGUNTAS SINGLE CHOICE

	@GetMapping("/preguntasc")
	public String getPreguntaSC(Model model) {
		PreguntaSC preguntaAleatoriaSC = preguntaSCService.getMuchasPreguntasSC();

		if (preguntaAleatoriaSC.getId() == -1) {
			model.addAttribute("testFin", "Test finalizado");
			return "vistasSC/testFinSC";
		}
		
		if(preguntaAleatoriaSC.getId() == 0) {
			model.addAttribute("mensajeNull", "No hay preguntas en este momento");
			return "vistasSC/testNullSC";
		}

		model.addAttribute("preguntas", preguntaAleatoriaSC);
		return "vistasSC/preguntaTestSC";
	}

	@PostMapping("/preguntasc-post")
	public String procesarFormularioSC(Model model,
			@RequestParam(name = "respuesta", required = false, defaultValue = "error-noclick") String respuesta,
			@RequestParam(name = "idPregunta") Long idPregunta) {

//		if ("error-noclick".equalsIgnoreCase(respuesta)) {
//			preguntaSCService.clearSetId(idPregunta);
//			model.addAttribute("noRespondida", "No elegiste ninguna opción");
//			return "vistasSC/preguntaNoRespondidaSC";
//		}

		model.addAttribute("acertada", respuesta
				.equalsIgnoreCase(preguntaSCService.getPreguntaByIdSC(idPregunta).orElse(null).getRespuestaCorrecta()));
		return "vistasSC/resultadoSC";
	}
	
	//SUBIDA DE ARCHIVOS
	
	@GetMapping("/uploadForm")
    public String showUploadForm() {
        return "uploadForm";
    }
	
	@PostMapping("/upload")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Seleccione un archivo para subir."; 
        }

        try {
            // aqui Obtengo la ruta a la carpeta static
            String uploadDir = "src/main/resources/static/";

            // copio el archivo al directorio de carga
            Path copyLocation = Paths.get(uploadDir + File.separator + file.getOriginalFilename());
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

            return "Archivo subido correctamente: " + file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al subir el archivo.";
        }
    }

	
	
}
