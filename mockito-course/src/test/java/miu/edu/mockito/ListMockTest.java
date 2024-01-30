package miu.edu.mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.junit.Test;

public class ListMockTest {

	@Test
	public void testMockListSize() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(2);
		
		assertEquals(2, listMock.size());
		assertEquals(2, listMock.size());
	}
	
	@Test
	public void testMockListSize_returnMultipleValues() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(2).thenReturn(3);
		
		assertEquals(2, listMock.size());
		assertEquals(3, listMock.size());
	}

	@Test
	public void testMockListGet() {
		List listMock = mock(List.class);
		when(listMock.get(0)).thenReturn("test");
		
		assertEquals("test", listMock.get(0));
		assertEquals(null, listMock.get(1)); //if not mock, return default value
	}
	
	@Test
	public void testMockListGetAny() {
		List listMock = mock(List.class);
		// argument matcher
		when(listMock.get(anyInt())).thenReturn("test");
		
		assertEquals("test", listMock.get(0));
		assertEquals("test", listMock.get(1));
	}
	
	@Test(expected=RuntimeException.class)
	public void testMockListGet_ThrowException() {
		List listMock = mock(List.class);
		// argument matcher
		when(listMock.get(anyInt())).thenThrow(new RuntimeException(""));
		
		listMock.get(0);
	}
}
