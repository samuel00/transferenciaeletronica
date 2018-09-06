package br.gov.pa.sefa.transferenciaeletronica.api.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ServicosEsefa {

    final Logger logger = LoggerFactory.getLogger(ServicosEsefa.class);

    static final String ESEFA_AUTHORIZATION = "Authorization";
    static final String ESEFA_ACCESS_TOKEN = "access_token";

    public String getLogin(HttpServletRequest request) {
        Map<String, Object> usuarioMap = getUsuarioMap(request);
        if (usuarioMap == null) {
            return null;
        }
        return usuarioMap.get("sub").toString();
    }

    public String getNomeUsuario(HttpServletRequest request) {
        Map<String, Object> usuarioMap = getUsuarioMap(request);
        if (usuarioMap == null) {
            return null;
        }
        return usuarioMap.get("name").toString();
    }

    public Map<String, Object> getUsuarioMap(HttpServletRequest request) {
        Map<String, Object> map;
        try {
            String json = getUsuarioJSON(request);
            if (json == null) {
                return null;
            }
            // Transformar json em map
            map = new ObjectMapper().readValue(json, new TypeReference<HashMap<String, Object>>() {
            });
        } catch (IOException e) {
            logger.error(e.getMessage());
            map = null;
        }
        return map;
    }

    public String getUsuarioJSON(HttpServletRequest request) {

        String tokenJWT = getTokenJWTPeloRequest(request);
        // tokenJWT:
        // eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE0ODkwODk5MDUsImV4cCI6MTQ4OTExMTUwNSwicmVmcmVzaFRpbWUiOjE0ODkxMDc5MDU2NTIsInN1YiI6Ijk5MDQyNDQwMDEiLCJuYW1lIjoiRXJuYW5pIGRvcyBTYW50b3MgSmFjb2IgSnVuaW9yIn0.1fmgIvah4ujHIUTws5yEMFdNSocsrWgWByBlhmDYe1E
        if (tokenJWT == null) {
            return null;
        }
        String decoded = new String(Base64Utils.decodeFromString(tokenJWT.split("\\.")[1]));
        // decoded: {"iat":1489089905,"exp":1489111505,"refreshTime":1489107905652,"sub":"9904244001","name":"Ernani dos Santos Jacob Junior"}
        return decoded;
    }

    public String getTokenJWTPeloRequest(HttpServletRequest request) {
        String token = request.getHeader(ESEFA_AUTHORIZATION);
        if (token == null || token.isEmpty()) {
            String accessToken = (String) request.getParameter(ESEFA_ACCESS_TOKEN);
            if (accessToken != null && !accessToken.isEmpty()) {
                token = "Bearer " + accessToken;
            }
        }
        return token;
    }

}
