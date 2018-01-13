// Author: Kevin L
// https://github.com/kle510
// November 2016

import java.io.IOException;

/*
 * InvalidDataException Class
 * 
 * Reports bad input data from user. 
 * Can be used for command line arguments specific to the GenText class.
 * 
 */

public class InvalidDataException extends IOException

{
	public InvalidDataException() {

	}

	public InvalidDataException(String message) {
		super(message);
	}
}