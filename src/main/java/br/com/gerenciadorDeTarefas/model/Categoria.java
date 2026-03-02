package main.java.br.com.gerenciadorDeTarefas.model;

public enum Categoria {
    ESTUDOS(1),
    TRABALHO(2),
    CASA(3),
    FINANCAS(4),
    SAUDE(5),
    PESSOAL(6);

    private final int codigo;

    public int getCodigo() {
        return codigo;
    }

    Categoria(int codigo) {
        this.codigo = codigo;
    }

    public static Categoria fromCodigo(int codigo) {
        Categoria[] categorias = Categoria.values();

        for (int i = 0; i < categorias.length; i++) {
            if (categorias[i].getCodigo() == codigo) {
                return categorias[i];
            }
        }

        throw new IllegalArgumentException("Código inválido: " + codigo);
    }

}
