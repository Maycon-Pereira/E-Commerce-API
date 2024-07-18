package com.api.commerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.commerce.domain.imagem.DadosDetalhamentoImagens;
import com.api.commerce.service.ImagemService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("produtoImagem")
public class ImagemController {

	@Autowired
	private ImagemService imagemService;
	
	@PostMapping("/upload-imagem")
    public void uploadImagem(@RequestParam("id") String id,
                             @RequestPart("imagem") MultipartFile[] imagem) throws Exception {
		imagemService.upload(imagem, id);
    }
	
	@GetMapping()
    public ResponseEntity<List<DadosDetalhamentoImagens>> listarImagens() {
        List<DadosDetalhamentoImagens> response = imagemService.listarImagens();
        return ResponseEntity.ok(response);
    }
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<DadosDetalhamentoImagens> excluir(@PathVariable String id) throws Exception {

		imagemService.excluirImagem(id);

		return ResponseEntity.noContent().build();
	}
}
