package it.ninebee.web.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import it.ninebee.web.exceptions.HttpException;
import it.ninebee.web.exceptions.RestException;
/**
 * Controller advice para utilização de envelope rest 
 * @author Ninebee
 *
 */
//@ControllerAdvice(annotations=RestController.class)
public class RestEnvelopeControllerAdvise extends DefaultHandlerExceptionResolver implements ResponseBodyAdvice<Object>{
	
	private static boolean RETURN_OK_ALWAYS = false; 
	private static Logger logger  = LoggerFactory.getLogger(RestEnvelopeControllerAdvise.class);
	
	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType,
			MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConvertType,
			ServerHttpRequest request, ServerHttpResponse response) {
		if(body instanceof RestEnvelope){
			return body;
		}else{
			RestEnvelope env = new RestEnvelope();
			env.setData(body);
			env.setStatus(HttpStatus.OK.value());
			return env;
		}	
	}
	
	@Override
	public boolean supports(MethodParameter type,
			Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}
	
	@ExceptionHandler({
		HttpMessageNotReadableException.class})
	public static ResponseEntity<Object> handleBadRequest(Exception ex,  HttpServletRequest request) {
	    return handleExceptionInternal(ex, HttpStatus.BAD_REQUEST, request);    
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	  public static ResponseEntity<Object> handleNotFoundExceptions(Exception ex,  HttpServletRequest request) {
	    return handleExceptionInternal(ex, HttpStatus.NOT_FOUND, request);    
	  }
	 
	@ExceptionHandler({HttpException.class})
	  public static ResponseEntity<Object> handleHttpExceptions(Exception ex,  HttpServletRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		if(ex instanceof HttpException){
			status = ((HttpException) ex).getHttpStatus();
		}
		if(status==null) status = HttpStatus.INTERNAL_SERVER_ERROR;
	    return handleExceptionInternal(ex, status, request);    
	  }
	
	@ExceptionHandler(Exception.class)
	  public static ResponseEntity<Object> handleOtherExceptions(Exception ex,  HttpServletRequest request) {
		logger.trace("Erro na requisicao",ex);
	    return handleExceptionInternal(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);    
	  }
	
//	 @ExceptionHandler(BusinessException.class)
//	  public static ResponseEntity<Object> handleBusinessExceptions(Exception ex,  HttpServletRequest request) {
//	    return handleExceptionInternal(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);    
//	  }
	 
	 private static ResponseEntity<Object> handleExceptionInternal(Exception ex,
			 HttpStatus httpStatus) {
		return handleExceptionInternal(ex, httpStatus, null);
	}
	 
	 protected static ResponseEntity<Object> handleExceptionInternal(Exception ex,
				 HttpStatus httpStatus, final HttpServletRequest request) {
		if(RETURN_OK_ALWAYS) httpStatus = HttpStatus.OK;
		RestEnvelope envelope = envelopeException(ex, request, httpStatus);
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		ResponseEntity<Object> resp = new ResponseEntity<Object>(envelope, headers, httpStatus);
//		logRequestBody(request);
		return resp;
	}

//	 Não é possível ler o corpo da requisição neste momento. Teria que fazer um filtro que fizesse um wrap da request. No way! 
//	private static void logRequestBody(HttpServletRequest request){
//		
//		if(logger.isTraceEnabled()){
//			String body;
//			try {
//				body = request.getReader().lines().collect(Collectors.joining());
//				logger.trace(body);
//			} catch (Exception e) {
//				logger.trace("Error reading request content",e);
//			}	
//		}
//	}
	 
	private static RestEnvelope envelopeException(Exception ex, HttpServletRequest request, HttpStatus httpStatus){
		RestEnvelope envelope = new RestEnvelope();
		
		RestException rex = getRestException(ex);
		rex.setHttpRequest(request);
		envelope.setError(rex);
		
		envelope.setStatus(httpStatus.value());
		return envelope;
	}
	 
	private static RestException getRestException(Exception ex){
		RestException rex = null;
		if(ex instanceof RestException){
			rex = (RestException) ex;
		}else{
			rex = new RestException(ex);
		}
		return rex;
	} 
	 
	protected static ResponseEntity<Object> handleExceptionInternalWebRequest(Exception ex,
			HttpStatus status,	WebRequest request) {
		RestEnvelope envelope = new RestEnvelope();
		
		RestException rex = getRestException(ex);
		//rex.setWebRequest(request);
		envelope.setError(rex);
		
		envelope.setStatus(status.value());
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		ResponseEntity<Object> resp = new ResponseEntity<Object>(envelope, headers, status);
		return resp;
	}

	/**
	 * Este é o mapemaneto das exceções
	 * @param ex
	 * @return
	 */
	private HttpStatus getHttpStatusForException(Exception ex){
		Map<Class<?>, HttpStatus>  map = new HashMap<Class<?>, HttpStatus>();
		map.put(MethodArgumentNotValidException.class, HttpStatus.BAD_REQUEST);
		map.put(MethodArgumentTypeMismatchException.class, HttpStatus.BAD_REQUEST);
		map.put(MethodArgumentNotValidException.class, HttpStatus.BAD_REQUEST);
		map.put(NoHandlerFoundException.class, HttpStatus.NOT_FOUND);
		
		for (Map.Entry<Class<?>, HttpStatus> entry : map.entrySet()) {
			Class<?> clazz = entry.getKey();
			HttpStatus httpStatus = entry.getValue();
			if(clazz.isAssignableFrom(ex.getClass())){
				return httpStatus;
			}
		}
		return null;
	}
	
	@Override
	protected boolean shouldApplyTo(HttpServletRequest req, Object hendle) {
		try{
			String accept = req.getHeader(HttpHeaders.ACCEPT);
			//verificar cabeçalho da requisição
			if(accept.indexOf("application/json")>-1){
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handle, Exception ex) {
		
		HttpStatus httpStatus = getHttpStatusForException(ex);
		if(httpStatus==null) return null;
		if(RETURN_OK_ALWAYS) httpStatus = HttpStatus.OK;
		
		RestException rex = getRestException(ex);
		rex.setIncludeCause(false);
		RestEnvelope envelope = envelopeException(rex, request, httpStatus);
		ModelAndView mav = new ModelAndView();
		mav.setView(new MappingJackson2JsonView());
		mav.addObject(envelope);
		return mav;
	}

//	@Override
//	public int getOrder() {
//		return Integer.MIN_VALUE;
//	}

}
