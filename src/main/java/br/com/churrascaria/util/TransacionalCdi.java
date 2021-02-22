package br.com.churrascaria.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

//@InterceptorBinding
//@Priority(Interceptor.Priority.APPLICATION) // https://stackoverflow.com/a/17570943/4023351
//@Retention(RetentionPolicy.RUNTIME)
@InterceptorBinding
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TransacionalCdi {

}