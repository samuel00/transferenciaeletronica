package sls.transferenciaeletronica.core.transferencia.enuns;

import java.util.Optional;

import sls.transferenciaeletronica.core.transferencia.interfaces.CalculoDeTaxa;

public enum Taxa implements CalculoDeTaxa {
	A {
		@Override
		public Optional<Double> calcularValor(Double valor, Long quantidadeDeDias) {
			return Optional.of((valor * 0.03) + 3.0);
		}
	},
	B {
		@Override
		public Optional<Double> calcularValor(Double valor, Long quantidadeDeDias) {
			return Optional.of(12.0 * quantidadeDeDias);
		}
	},
	C {
		@Override
		public Optional<Double> calcularValor(Double valor, Long quantidadeDeDias) {
			if (quantidadeDeDias <= 20)
				return Optional.of(valor * 0.08);
			else if (quantidadeDeDias > 20 && quantidadeDeDias <= 30)
				return Optional.of(valor * 0.06);
			else if (quantidadeDeDias > 30 && quantidadeDeDias <= 40)
				return Optional.of(valor * 0.04);
			else if (quantidadeDeDias > 40 && valor > 1000)
				return Optional.of(valor * 0.02);
			return Optional.empty();
		}
	}

}
