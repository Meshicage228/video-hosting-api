package ru.clevertec.springbootsessionstarter.clients;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignCustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        System.out.println();
        return null;
    }
}
