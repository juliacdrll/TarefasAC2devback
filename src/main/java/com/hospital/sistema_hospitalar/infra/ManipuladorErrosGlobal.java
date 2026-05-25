package com.hospital.sistema_hospitalar.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ManipuladorErrosGlobal {

    // 1. Captura erros de regras de negócio (como "Paciente não encontrado")
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroResposta> tratarErroNegocio(RuntimeException ex) {
        ErroResposta erro = new ErroResposta(
                HttpStatus.NOT_FOUND.value(),
                "Recurso Não Encontrado",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    // 2. Captura erros de digitação na URL (ex: passar letras no lugar do ID)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroResposta> tratarErroArgumentoInvalido(MethodArgumentTypeMismatchException ex) {
        String mensagem = String.format("O parâmetro '%s' recebeu o valor '%s' que é inválido. Esperava-se o tipo '%s'.",
                ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
        
        ErroResposta erro = new ErroResposta(
                HttpStatus.BAD_REQUEST.value(),
                "Requisição Inválida (Bad Request)",
                mensagem
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    // 3. Captura qualquer outro erro inesperado
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResposta> tratarErroInesperado(Exception ex) {
        ErroResposta erro = new ErroResposta(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro Interno no Servidor",
                "Ocorreu um erro interno inesperado no sistema. Por favor, tente novamente mais tarde."
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}