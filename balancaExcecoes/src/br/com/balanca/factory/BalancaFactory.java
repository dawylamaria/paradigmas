// BalancaFactory.java
package br.com.balanca.factory;

import br.com.balanca.enums.TipoBalanca;
import br.com.balanca.interfaces.IBalanca;
import br.com.balanca.services.FilizolaSmart;
import br.com.balanca.services.ToledoMGV6;
import br.com.balanca.services.UranoIntegra;
import br.com.balanca.exceptions.BalancaNaoSuportadaException;

public class BalancaFactory {
    public static IBalanca getBalanca(TipoBalanca tipo) throws BalancaNaoSuportadaException {
        switch (tipo) {
            case FINIZOLA_SMART:
                return new FilizolaSmart();
            case TOLEDO_MGV6:
                return new ToledoMGV6();
            case URANO_INTEGRA:
                return new UranoIntegra();
            default:
                throw new BalancaNaoSuportadaException("Tipo de balança não suportada: " + tipo);
        }
    }
}
