package com.ssg.springex3.controller;


import com.ssg.springex3.dto.PageRequestDTO;
import com.ssg.springex3.dto.ProductDTO;
import com.ssg.springex3.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @RequestMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model){
        log.info("product list..................");
        if (bindingResult.hasErrors()){
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        model.addAttribute("responseDTO" ,productService.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void register(){
        log.info("product register .................. RequestMethod.GET 이당 ");
    }

    @PostMapping("/register")
    public String registerPost(@Valid ProductDTO productDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        log.info("post product registerPost ..................  ");

        if (bindingResult.hasErrors()){
            log.info(" hasErrors ~~~~~~~~~~~~~~~~~~~~~~ ");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }
        log.info("productDTO: " + productDTO );
        //service 에 등록 요청
        productService.register(productDTO);
        return "redirect:/todo/list";
    }

    @GetMapping({"/read", "/modify"})
    public void readModify(Long pno, PageRequestDTO pageRequestDTO, Model model){
        ProductDTO productDTO = productService.getOne(pno);
        log.info(productDTO);
        model.addAttribute("dto", productDTO);
    }

    @PostMapping("/modify")
    public String modify(@Valid ProductDTO productDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
            redirectAttributes.addAttribute("pno", productDTO.getPno() );
            return "redirect:/todo/modify";
        }
        log.info(productDTO);
        productService.modify(productDTO);
        return "redirect:/todo/list";
    }

    @PostMapping("/remove")
    public String remove(@Valid ProductDTO productDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){
        log.info("-------------remove------------------");
        productService.deleteOne(productDTO);
        return "redirect:/todo/list";
    }
    // Other methods remain unchanged...
}


