package sls.transferenciaeletronica.core.transferencia.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class FormatadorUtil {	
	
	public static LocalDate localdatePadraoBrasil(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = data.format(formatter);
        return LocalDate.parse(dataFormatada, formatter);
	}

}
