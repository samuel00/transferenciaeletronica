package sls.transferenciaeletronica.manager.repositorio;

import org.springframework.stereotype.Repository;
import sls.transferenciaeletronica.core.comum.BaseRepositorio;
import sls.transferenciaeletronica.manager.entidade.IPBloqueado;

@Repository public class IPBloqueadoRepositorio
		extends BaseRepositorio<IPBloqueado, Object> {

    public boolean getIPBloqueado(String ipBloqueadoID) {
	IPBloqueado ipBloqueado = buscarPorId(Long.valueOf(ipBloqueadoID));
	return null != ipBloqueado;
    }
}
