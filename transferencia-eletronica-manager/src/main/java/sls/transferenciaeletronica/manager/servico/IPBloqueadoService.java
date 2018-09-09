package sls.transferenciaeletronica.manager.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sls.transferenciaeletronica.manager.repositorio.IPBloqueadoRepositorio;

@Service
public class IPBloqueadoService {
	
	@Autowired
	private IPBloqueadoRepositorio ipBloqueadoRepositorio;
	
	public boolean getIPBloqueado(String ipBloqueado){
		return ipBloqueadoRepositorio.getIPBloqueado(ipBloqueado);
	}

}
