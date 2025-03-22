package com.example.twitter;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.internal.LambdaContainerHandler;
import com.amazonaws.serverless.proxy.spring.SpringBootProxyHandlerBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AuthServiceLambdaHandler implements RequestStreamHandler {
    private static final LambdaContainerHandler<InputStream, OutputStream> handler;

    static {
        try {
            handler = new SpringBootProxyHandlerBuilder()
                    .defaultProxy()
                    .asyncInit()
                    .springBootApplication(AuthServiceApplication.class)  // Tu clase principal
                    .buildAndInitialize();
        } catch (ContainerInitializationException e) {
            throw new RuntimeException("No se pudo inicializar Spring Boot en Lambda", e);
        }
    }

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
        handler.proxyStream(input, output, context);
    }
}