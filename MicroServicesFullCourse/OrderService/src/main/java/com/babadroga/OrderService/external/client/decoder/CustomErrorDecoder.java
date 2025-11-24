package com.babadroga.OrderService.external.client.decoder;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CstomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        return null;
    }
}
