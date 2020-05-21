package sls.transferenciaeletronica.manager.repositorio;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sls.transferenciaeletronica.core.comum.BaseRepositorio;
import sls.transferenciaeletronica.manager.entidade.Requisicao;

import java.util.List;

@Repository
public class AspectManagerRepositorio extends BaseRepositorio<Requisicao, Long> {

        @Transactional
	public void persistiRequisicao(Requisicao object) {
		salvar(object);
	}

	@SuppressWarnings("unchecked")
	public List<Requisicao> getRequisicoes() {
		return listAll();
	}

}
