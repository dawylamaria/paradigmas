package imposto;

import model.Produto;

public class ISS implements Imposto{
    @Override
    public double calcular(Produto produto) {
        if(produto.isServico()){
            return produto.getValor() * 0.22;
        }
        return 0;
    }
}
