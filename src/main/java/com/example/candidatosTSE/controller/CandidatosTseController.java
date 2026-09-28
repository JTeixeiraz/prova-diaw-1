package com.example.candidatosTSE.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.candidatosTSE.model.Candidato;
import com.example.candidatosTSE.service.CandidatosTseService;

@Controller
public class CandidatosTseController {

    private final CandidatosTseService candidatosTseService;

    public CandidatosTseController(CandidatosTseService candidatosTseService) {
        this.candidatosTseService = candidatosTseService;
    }

    @GetMapping("/")
    public String getCandidatos(
        @RequestParam (required = false)String genero, 
        @RequestParam (required = false)String  escolaridade,
        @RequestParam (required = false)Integer idadeMin,
        @RequestParam (required = false)Integer idadeMax,
        Model model){
        List<Candidato> candidatos;
        candidatosTseService.carregarCsv();
        candidatos = candidatosTseService.filtrarPerfil(genero, escolaridade, idadeMin, idadeMax);
        model.addAttribute("candidatos", candidatos);
        model.addAttribute("totalEncontrado", candidatos.size());
        model.addAttribute("genero", candidatosTseService.listarGeneros());
        model.addAttribute("escolaridade", candidatosTseService.listarEscolaridades());
        model.addAttribute("generoSelecionado", genero == null ? "" : genero);
        model.addAttribute("escolaridadeSelecionada", escolaridade == null ? "" : escolaridade);

        return "index";
    }
}
