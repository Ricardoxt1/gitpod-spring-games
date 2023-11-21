package application.controller;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import application.model.Alternativas;
import application.model.Questoes;
import application.repository.AlternativaRepository;
import application.repository.QuestaoRepository;

import java.util.Set;

public class AlternativaController {

    @Autowired
    private AlternativaRepository alternativaRepository;

    @Autowired
    private QuestaoRepository questaoRepository;

    @RequestMapping("/list")
    public String list(Model ui) {
        ui.addAttribute("alternativas", alternativaRepository.findAll());
        return "alternativa/list";
    }

    @RequestMapping("/insert")
    public String insert(Model ui) {
        ui.addAttribute("questoes", questaoRepository.findAll());
        return "alternativa/insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(
            @RequestParam("texto") String texto,
            @RequestParam("correta") Boolean isCorreta,
            @RequestParam("questao") Long id_questao )
    {
        Alternativas alternativa = new Alternativas();
        alternativa.setTexto(texto);
        alternativa.setCorreta(isCorreta);
        alternativa.setQuestao(questaoRepository.findById(id_questao).get());
        alternativaRepository.save(alternativa);
        return "redirect:/alternativa/list";
    }

    @RequestMapping("/update")
    public String update(@RequestParam("id") Long id, Model ui) {
        Optional<Alternativas> alternativa = alternativaRepository.findById(id);
        if (alternativa.isPresent())    {
            ui.addAttribute("alternativa", alternativaRepository.findById(id).get());
            ui.addAttribute("questoes", questaoRepository.findAll());
            return "alternativa/update";
        }
        return "redirect:/alternativa/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(
            @RequestParam("id") Long id,
            @RequestParam("texto") String texto,
            @RequestParam("correta") Boolean isCorreta,
            @RequestParam("questao") Long[] id_questao)
    {
        Optional<Alternativas> alternativa = alternativaRepository.findById(id);

        if(alternativa.isPresent()) {
            alternativa.get().setTexto(texto);
            alternativa.get().setCorreta(isCorreta);

            Set<Questoes> updateQuestao = new HashSet<>();

            for (long p : id_questao){
                Optional<Questoes> questoes = questaoRepository.findById(p);
                if(questoes.isPresent()) {
                    updateQuestao.add(questoes.get());
                }
            }

            alternativa.get().setQuestao((Questoes) updateQuestao);
            alternativaRepository.save(alternativa.get());
        }
        return "redirect:/alternativa/list";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id") Long id, Model ui) {
        Optional<Alternativas> alternativa = alternativaRepository.findById(id);
        if(alternativa.isPresent()) {
            ui.addAttribute("alternativa", alternativa.get());
            return "alternativa/delete";
        }
        return "redirect:/alternativa/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Long id) {
        alternativaRepository.deleteById(id);
        return "redirect:/alternativa/list";
    }

}
