/*
 * Copyright 2020 Amazon.com, Inc. or its affiliates.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 */
package com.amazonaws.todo_crud_microservice.todo;

import com.amazonaws.todo_crud_microservice.todo.model.Todo;
import org.junit.jupiter.api.Test;

import javax.validation.ValidationException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TodoValidatorTest {

    @Test
    public void testValidateTodo_ValidTodo_NoException() {
        Todo todo = new Todo();
        todo.setTask("Test task");
        todo.setDescription("Test description");
        todo.setCompleted(false);

        assertDoesNotThrow(() -> TodoValidator.validateTodo(todo));
    }

    @Test
    public void testValidateTodo_NullTask_ThrowsValidationException() {
        Todo todo = new Todo();
        todo.setTask(null);
        todo.setDescription("Test description");
        todo.setCompleted(false);

        assertThatThrownBy(() -> TodoValidator.validateTodo(todo))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("task must not be null");
    }

    @Test
    public void testValidateTodo_NullDescription_ThrowsValidationException() {
        Todo todo = new Todo();
        todo.setTask("Test task");
        todo.setDescription(null);
        todo.setCompleted(false);

        assertThatThrownBy(() -> TodoValidator.validateTodo(todo))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("description must not be null");
    }

    @Test
    public void testValidateTodo_BothNullFields_ThrowsValidationException() {
        Todo todo = new Todo();
        todo.setTask(null);
        todo.setDescription(null);
        todo.setCompleted(false);

        assertThatThrownBy(() -> TodoValidator.validateTodo(todo))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Invalid Todo:");
    }
}
