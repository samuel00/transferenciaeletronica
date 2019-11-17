package sls.transferenciaeletronica.core.transferencia.interfaces;

import java.util.Optional;

public interface CalculoDeTaxa {
	
	public Optional<Double> calcularValor(Double valor, Long quantidadeDeDias);
}
