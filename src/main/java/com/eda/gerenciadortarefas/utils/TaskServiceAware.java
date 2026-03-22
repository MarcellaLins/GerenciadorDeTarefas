package com.eda.gerenciadortarefas.utils;

import com.eda.gerenciadortarefas.service.TaskService;

/*
 * contrato de injeção de dependência para
 * os controllers utilizarem a mesma instância de serviço
 */

public interface TaskServiceAware {

    // injeta a instância de TaskService no controller
    void setTaskService(TaskService taskService);
}