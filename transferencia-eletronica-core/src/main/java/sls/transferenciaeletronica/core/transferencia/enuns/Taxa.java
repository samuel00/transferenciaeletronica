package sls.transferenciaeletronica.core.transferencia.enuns;

import sls.transferenciaeletronica.core.transferencia.interfaces.CalculoDeTaxa;

public enum Taxa implements CalculoDeTaxa {
	A {
		@Override
		public Double calcular(Double valor, Long quantidadeDeDias) {
			return (valor * 0.03) + 3.0;
		}
	},
	B {
		@Override
		public Double calcular(Double valor, Long quantidadeDeDias) {
			return 12.0 * quantidadeDeDias;
		}
	},
	C {
		@Override
		public Double calcular(Double valor, Long quantidadeDeDias) {
			if (quantidadeDeDias <= 20)
				return valor * 0.08;
			else if (quantidadeDeDias > 20 && quantidadeDeDias <= 30)
				return valor * 0.06;
			else if (quantidadeDeDias > 30 && quantidadeDeDias <= 40)
				return valor * 0.04;
			else if (quantidadeDeDias > 40 && valor > 1000)
				return valor * 0.02;
			return null;
		}
	}, VAZIA{
		@Override
		public Double calcular(Double valor, Long quantidadeDeDias) {
			return null;
		}
	};

}
