package com.dulfinne.randomgame.virtualservice.filter;

import com.dulfinne.randomgame.virtualservice.util.CommonConstants;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TimeZoneFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      FilterChain filterChain
  ) throws ServletException, IOException {
    String timezone = request.getHeader(CommonConstants.HEADER_TIMEZONE);

    RequestAttributes attrs = RequestContextHolder.currentRequestAttributes();
    attrs.setAttribute(CommonConstants.TIMEZONE_FIELD, timezone, RequestAttributes.SCOPE_REQUEST);

    filterChain.doFilter(request, response);
  }
}

