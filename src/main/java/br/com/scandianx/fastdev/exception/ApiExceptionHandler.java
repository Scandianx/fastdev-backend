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
        String msg = ex.getMessage();
        ProblemDetail pd = (msg != null && !msg.isBlank())
                ? ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, msg)
                : ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pd.setType(URI.create("about:blank#recurso-nao-encontrado"));
        pd.setTitle("Recurso não encontrado");
        return pd;
    }

    @ExceptionHandler(NaoAutorizadoException.class)
    public ProblemDetail handleUnauthorized(NaoAutorizadoException ex) {
        String msg = ex.getMessage();
        ProblemDetail pd = (msg != null && !msg.isBlank())
                ? ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, msg)
                : ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        pd.setType(URI.create("about:blank#nao-autorizado"));
        pd.setTitle("Não autorizado");
        return pd;
    }

    @ExceptionHandler(PermissaoNegadaException.class)
    public ProblemDetail handleForbidden(PermissaoNegadaException ex) {
        String msg = ex.getMessage();
        ProblemDetail pd = (msg != null && !msg.isBlank())
                ? ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, msg)
                : ProblemDetail.forStatus(HttpStatus.FORBIDDEN);
        pd.setType(URI.create("about:blank#permissao-negada"));
        pd.setTitle("Permissão negada");
        return pd;
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ProblemDetail handleBusiness(RegraDeNegocioException ex) {
        String msg = ex.getMessage();
        ProblemDetail pd = (msg != null && !msg.isBlank())
                ? ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, msg)
                : ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pd.setType(URI.create("about:blank#regra-de-negocio"));
        pd.setTitle("Regra de negócio violada");
        return pd;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException ex) {
        String detail = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .findFirst()
                .orElse(null);
        ProblemDetail pd = (detail != null && !detail.isBlank())
                ? ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, detail)
                : ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setType(URI.create("about:blank#validacao"));
        pd.setTitle("Erro de validação");
        return pd;
    }

    @ExceptionHandler(RuntimeException.class)
    public ProblemDetail handleGeneric(RuntimeException ex) {
        String msg = ex.getMessage();
        ProblemDetail pd = (msg != null && !msg.isBlank())
                ? ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, msg)
                : ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pd.setType(URI.create("about:blank#erro-interno"));
        pd.setTitle("Erro interno");
        return pd;
    }
}

