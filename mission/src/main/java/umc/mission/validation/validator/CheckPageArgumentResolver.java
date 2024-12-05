package umc.mission.validation.validator;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import umc.mission.validation.annotation.CheckPage;

import java.lang.reflect.Parameter;

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
            throw new IllegalArgumentException("page가 0보다 작습니다.");
        }

        return page - 1;
    }
}
