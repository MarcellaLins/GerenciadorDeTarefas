package com.eda.gerenciadortarefas.model;

// representa as categorias de tarefas (valores exibidos em português)
public enum Category {

    ESTUDOS(0),
    TRABALHO(1),
    CASA(2),
    FINANCAS(3),
    SAUDE(4),
    PESSOAL(5);

    // código numérico associado à categoria
    private final int code;

    Category(int code) {
        this.code = code;
    }

    // retorna o código da categoria
    public int getCode() {
        return code;
    }

    // converte um código inteiro para a categoria correspondente
    public static Category fromCode(int code) {
        for (Category category : Category.values()) {
            if (category.code == code) {
                return category;
            }
        }

        // falha explícita se o código não existir
        throw new IllegalArgumentException("Invalid category code: " + code);
    }
}