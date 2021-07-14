package com.system.domain.controller;

import com.system.domain.dto.ContactDTO;
import com.system.domain.models.Contact;
import com.system.domain.service.ContactService;
import com.system.swagger.SwaggerConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@CrossOrigin("https://contact-book-4e449.web.app")
@Api(tags = {SwaggerConfig.TAG_1})
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    
    @ApiOperation(value = "Lista os contatos", notes = "Lista todos os contatos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Contatos encontrados"),
            @ApiResponse(code = 404, message = "Contatos não encontrados"),
            @ApiResponse(code = 401, message = "Nao autorizado")
        })
    @GetMapping
    public ResponseEntity<List<ContactDTO>> findAll() {
        return new ResponseEntity<>(ContactDTO.convertToRequest(this.contactService.findByUser()),HttpStatus.OK);
    }
    
    @ApiOperation(value = "Encontra o contato", notes = "Encontra o contato pelo nome")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Contato encontrado"),
            @ApiResponse(code = 404, message = "Contato não encontrado"),
            @ApiResponse(code = 403, message = "Proibido"),
            @ApiResponse(code = 401, message = "Nao autorizado")
    })
    @GetMapping(value = "/{name}")
    public ResponseEntity<ContactDTO> findOne(@PathVariable("name") String name) {
           Contact contact =  this.contactService.findOne(name);
            return ResponseEntity.ok(ContactDTO.convertToRequest(contact));
    }

    
    @ApiOperation(value = "Criar contato", notes = "Permite criar um novo contato")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Contato criado"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 401, message = "Nao autorizado")
    })
    @PostMapping
    public ResponseEntity<ContactDTO> newContact(@RequestBody ContactDTO contact) {
        return new ResponseEntity<>(ContactDTO.convertToRequest(this.contactService.create(contact)), HttpStatus.CREATED);
    }
    @ApiOperation(value = "Atualiza contato", notes = "Edita dados do contato")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Contato atualizado"),
        @ApiResponse(code = 400, message = "Requisição inválida"),
        @ApiResponse(code = 401, message = "Nao autorizado")
    })
    @PutMapping
    public ResponseEntity<ContactDTO> updateContact(@RequestBody ContactDTO contact) {
        return new ResponseEntity<>(ContactDTO.convertToRequest(this.contactService.update(contact)),HttpStatus.OK);
    }
    @ApiOperation(value = "Exclui contato", notes="Exclui o contato")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Requisição inválida"),
        @ApiResponse(code = 401, message = "Nao autorizado")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> removeContact(@PathVariable("id") String id) {
        this.contactService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
}
