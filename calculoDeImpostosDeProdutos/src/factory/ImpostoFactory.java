package factory;

import imposto.ICMS;
import imposto.ISS;
import imposto.IPI;
import imposto.PIS;
import imposto.Imposto;

public class ImpostoFactory {
    public static Imposto criarImposto(TipoImposto tipo) {
        switch (tipo) {
            case ICMS:
                return new ICMS();
            case PIS:
                return new PIS();
            case IPI:
                return new IPI();
            case ISS:
                return new ISS();
            default:
                throw new IllegalArgumentException("Tipo de imposto inválido.");
        }
    }
}
