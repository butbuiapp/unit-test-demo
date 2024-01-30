package service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TodoBusinessImplMockTest {

	@Test
	public void testRetrieveTodosRelatedToSpring_usingAMock() {
		TodoService todoService = mock(TodoService.class);
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
											  "Learn Spring", 
											  "Learn to Dance");
		when(todoService.retrieveTodos("user")).thenReturn(allTodos);
		
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("user");
		
		assertEquals(2, todos.size());
	}
	
	@Test
	public void testRetrieveTodosRelatedToSpring_usingAMock_notFound() {
		TodoService todoService = mock(TodoService.class);
		List<String> allTodos = Arrays.asList("Learn MVC",
											  "Learn react", 
											  "Learn to Dance");
		when(todoService.retrieveTodos("user")).thenReturn(allTodos);
		
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("user");
		
		assertEquals(0, todos.size());
	}

}
