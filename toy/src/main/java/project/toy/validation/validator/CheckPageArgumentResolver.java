package project.toy.validation.validator;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import project.toy.apiPayload.code.status.ErrorStatus;
import project.toy.apiPayload.exception.handler.PageHandler;
import project.toy.validation.annotation.CheckPage;

@Component
public class CheckPageArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CheckPage.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String parameterName = parameter.getParameterName();
        String paramPage = webRequest.getParameter(parameterName);

        Integer page = Integer.valueOf(paramPage);

        if (page <= 0) {
            throw new PageHandler(ErrorStatus.PAGE_OUT_OF_RANGE);
        }

        return page - 1;
    }
}
