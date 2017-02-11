package pl.com.knopers.lazycat.io;

/*
 * Stream utils helper
 * Author: Mateusz "Knopers" Knop 
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamUtils
{

	/**
	 * Copy len bytes from input to output stream.
	 *
	 * @param in an input stream
	 * @param out an output stream
	 * @param len number of bytes to copy
	 * @throws IOException
	 */
	public static void copyFor(InputStream in, OutputStream out, long len) throws IOException
	{
		int b;
		long c = 0;
		while ( ++c <= len && (b = in.read()) != -1)
			out.write(b);
	}

	/**
	 * Copy bytes from input stream to output stream until end with buffer.
	 *
	 * @param bufferSize size of byte array
	 * @param in an input stream
	 * @param out an output stream
	 * @throws IOException
	 */
	public static void copyWithToEnd(short bufferSize, InputStream in, OutputStream out) throws IOException
	{
		int b = 0;
		byte[] buff = new byte[bufferSize];

		while ((b = in.read(buff)) != -1)
		{
			out.write(buff, 0, b);
		}
	}

	/**
	 * Copy len bytes from input stream to output stream with buffer.
	 *
	 * @param bufferSize size of byte array
	 * @param in an input stream
	 * @param out an output stream
	 * @param len number of bytes to copy
	 * @throws IOException
	 */
	public static void copyWithFor(short bufferSize, InputStream in, OutputStream out, long len) throws IOException
	{
		int b = 0;
		byte[] buff = new byte[bufferSize];
		long c = 0;

		while ((c += b) < len && (b = in.read(buff)) != -1)
		{
			out.write(buff, 0, b);
		}
	}

	/**
	 * Copy bytes from input stream to output stream until \r\n with buffer.
	 *
	 * @param bufferSize size of byte array
	 * @param in an input stream
	 * @param out an output stream
	 * @throws IOException
	 */
	public static void copyWithTillRN(short bufferSize, InputStream in, OutputStream out) throws IOException
	{
		int b = 0;
		byte[] buff = new byte[bufferSize];

		while ((b = in.read(buff)) != -1)
		{
			out.write(buff, 0, b);
			if(buff[b-1] == 10 && buff[b-2] == 13 && buff[b-3] == 10 && buff[b-4] == 13)
				break;
		}
	}

	/**
	 * Copy bytes from input stream to output stream until end.
	 *
	 * @param in an input stream
	 * @param out an output stream
	 * @throws IOException
	 */
	public static void copyToEnd(InputStream in, OutputStream out) throws IOException
	{
		int b;
		while ((b = in.read()) != -1)
		{
			System.out.print(b + ", ");
			out.write(b);
		}
	}

	/**
	 * Read line from input stream.
	 *
	 * @param in an input stream
	 *
	 * @return red line
	 *
	 * @throws IOException
	 */
	public static String readLine(InputStream in) throws IOException
	{
		StringBuffer data = new StringBuffer("");
		int c;
		while ((c = in.read()) != -1)
		{
			if((c == 10) || (c == 13))
				break;
			else
				data.appendCodePoint(c);
		}
		if(c == -1 && data.length() == 0)
			return null;
		else if(c == 13)
		{
			in.mark(1);
			if(in.read() != 10)
				in.reset();
		}

		return data.toString();
	}
}