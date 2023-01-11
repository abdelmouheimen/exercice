package domain.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.exemple.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResourceNotFoundExceptionTest {

	@Test
	@DisplayName("[domain] - should throw execption with message 'Exception running the method'")
	void testSomethingShouldThrowException() {

		String expectedMessage = "Exception running the method";

		Throwable exception = assertThrows(ResourceNotFoundException.class, () -> {
			throw new ResourceNotFoundException(expectedMessage);
		});

		assertEquals(expectedMessage, exception.getMessage());
	}
}
