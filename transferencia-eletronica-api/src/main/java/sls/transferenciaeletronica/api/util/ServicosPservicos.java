package sls.transferenciaeletronica.api.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ServicosPservicos {

    final Logger logger = LoggerFactory.getLogger(ServicosEsefa.class);

    static final String _CONST_AUTENTICADOR_TOKEN_ = "_const_autenticador_token_";

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
            return map;
        } catch (IOException e) {
            logger.error(e.getMessage());
            map = null;
        }
        return map;
    }

    public String getUsuarioJSON(HttpServletRequest request) {

        String tokenJWT = getTokenJWTPeloRequest(request);
        // tokenJWT:
        // eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE0ODk0MTMyOTAsImV4cCI6MTQ4OTQ5OTY5MCwic3ViIjoiOTQ2NTc3NTAyOTEiLCJuYW1lIjoiU0VGSU5IQSIsInBlcmZpcyI6W3siaWQiOiIyNCIsImRlc2NyaWNhbyI6IlBFU1NPQSBGSVNJQ0EifSx7ImlkIjoiMSIsImRlc2NyaWNhbyI6IlFVQURSTyBTT0NJRVTDgVJJTyJ9LHsiaWQiOiIzMCIsImRlc2NyaWNhbyI6IlRFTVBMQVRFIn1dfQ.4w1puAZk9ae6qO8bAIe4SEctPbRkcjU9Oh-x81UNKGA
        if (tokenJWT == null) {
            return null;
        }
        String decoded = new String(Base64Utils.decodeFromString(tokenJWT.split("\\.")[1]));
        // decoded: {"iat":1489413290,"exp":1489499690,"sub":"94657750291","name":"SEFINHA","perfis":[{"id":"24","descricao":"PESSOA FISICA"},{"id":"1","descricao":"QUADRO
        // SOCIETÁRIO"}]}
        return decoded;
    }

    public String getTokenJWTPeloRequest(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        String token = null;
        if (request.getCookies() != null) {
            final Cookie cookies[] = request.getCookies();
            for (final Cookie cookie : cookies) {
                if (cookie.getName().equals(_CONST_AUTENTICADOR_TOKEN_)) {
                    token = cookie.getValue();
                }
            }
        }
        return token;
    }

}
