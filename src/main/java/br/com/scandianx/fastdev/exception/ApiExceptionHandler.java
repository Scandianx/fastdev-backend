package br.com.scandianx.fastdev.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ProblemDetail handleNotFound(RecursoNaoEncontradoException ex) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        pd.setType(URI.create("about:blank#recurso-nao-encontrado"));
        pd.setTitle("Recurso não encontrado");
        return pd;
    }

    @ExceptionHandler(NaoAutorizadoException.class)
    public ProblemDetail handleUnauthorized(NaoAutorizadoException ex) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
        pd.setType(URI.create("about:blank#nao-autorizado"));
        pd.setTitle("Não autorizado");
        return pd;
    }

    @ExceptionHandler(PermissaoNegadaException.class)
    public ProblemDetail handleForbidden(PermissaoNegadaException ex) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
        pd.setType(URI.create("about:blank#permissao-negada"));
        pd.setTitle("Permissão negada");
        return pd;
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ProblemDetail handleBusiness(RegraDeNegocioException ex) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
        pd.setType(URI.create("about:blank#regra-de-negocio"));
        pd.setTitle("Regra de negócio violada");
        return pd;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException ex) {
        String detail = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .findFirst()
                .orElse("Dados inválidos");
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, detail);
        pd.setType(URI.create("about:blank#validacao"));
        pd.setTitle("Erro de validação");
        return pd;
    }

    @ExceptionHandler(RuntimeException.class)
    public ProblemDetail handleGeneric(RuntimeException ex) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno do servidor");
        pd.setType(URI.create("about:blank#erro-interno"));
        pd.setTitle("Erro interno");
        return pd;
    }
}

